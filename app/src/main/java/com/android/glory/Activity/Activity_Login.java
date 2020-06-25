package com.android.glory.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.glory.MainActivity;
import com.android.glory.Model.LoginEmail.LoginExample;
import com.android.glory.R;
import com.android.glory.Retrofit.Api;
import com.android.glory.Retrofit.ApiClient;
import com.android.glory.Retrofit.Login.LoginJson;
import com.android.glory.Utilites.EndUrls;
import com.android.glory.Utilites.JSONParserNew;
import com.android.glory.Utilites.sharedPrefs;
import com.android.glory.app.Config;
import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.provider.ContactsContract.Intents.Insert.EMAIL;

public class Activity_Login extends AppCompatActivity {

    Button logsubmit;

    TextView signup, forgetpasswd, logmob, logpassword;

    JSONParserNew jsonParser = new JSONParserNew();
    Image image;
    private ImageView imgPreview;


    String strmobileno, strpassword, regId;

    //-------------------Googleplus---------------------------
    //a constant for detecting the login intent result
    private static final int RC_SIGN_IN = 234;

    //Tag for the logs optional
    private static final String TAG = "simplifiedcoding";

    //creating a GoogleSignInClient object
    GoogleSignInClient mGoogleSignInClient;

    //And also a Firebase Auth object
    FirebaseAuth mAuth;
    ImageView xRelayGmail, fb;
    //-------------------Googleplus--------------------------q-
    String personName;
    String personGivenName;
    String personFamilyName;
    String personEmail;
    String personId;
    String personPhoto;
    private LoginButton loginButton;
    String encodedImage;

    Context context;
    CallbackManager callbackManager;
    AccessToken access_token;
    GraphRequest request;
    Bitmap bitmap;
    private String email, facebook_uid, first_name, last_name, social_id, name, picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        if(account!=null){
//        }
        imgPreview = (ImageView) findViewById(R.id.imgPreview);
        logmob = findViewById(R.id.logmob);
        logpassword = findViewById(R.id.logpassword);
        signup = (TextView) findViewById(R.id.signup);
        forgetpasswd = (TextView) findViewById(R.id.forgetpasswd);
        logsubmit = (Button) findViewById(R.id.logsubmit);
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        xRelayGmail = findViewById(R.id.xRelayGmail);
        fb = findViewById(R.id.fb);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginButton.performClick();

            }
        });


//        fb login
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //   info.setText("User ID: " + loginResult.getAccessToken().getUserId() + "\n" + "Auth Token: " + loginResult.getAccessToken().getToken());

                access_token = loginResult.getAccessToken();
                Log.d("response access_token", access_token.toString());

                request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        JSONObject json = response.getJSONObject();
                        try {
                            if (json != null) {
                                Log.d("response", json.toString());
                                try {
                                    email = json.getString("email");
                                } catch (Exception e) {
                                    //                              Toast.makeText(context, "Sorry!!! Your email is not verified on facebook.", Toast.LENGTH_LONG).show();
                                    return;
                                }

                                facebook_uid = json.getString("id");
                                social_id = json.getString("id");
                                first_name = json.getString("first_name");
                                last_name = json.getString("last_name");
                                name = json.getString("name");
                                //                             nameTv.setText(name+"");

                                picture = "https://graph.facebook.com/" + facebook_uid + "/picture?type=large";
                                Log.d("response", " picture" + picture);
                                //                           Picasso.with(context).load(picture).placeholder(R.mipmap.ic_launcher).into(userIv);

                                //                             mPb.setVisibility(View.GONE);
                                FaceBookLogin();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("response problem", "problem" + e.getMessage());
                        }
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,first_name,last_name,link,email,picture");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
//                info.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException e) {
//                info.setText("Login attempt failed.");
            }
        });


        logsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                strmobileno = logmob.getText().toString();
                strpassword = logpassword.getText().toString();

                if (strmobileno == null || strmobileno.equals("null") || strmobileno.length() != 10) {
                    logmob.setError("Please Enter Valid Mobile number");
                } else if (strpassword == null || strpassword.equals("null") || strpassword.length() == 0) {
                    logpassword.setError("Please Enter Valid Password");
                } else {

                    // RetrofitLogin(strmobileno, strpassword);
                    new LoadLogin().execute();
                    ;


                }


            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_Login.this, Activity_Signup.class);
                startActivity(intent);
            }
        });

        forgetpasswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_Login.this, ActivityForgetPassword.class);
                startActivity(intent);
            }
        });

        //------------------------Googleplus------------------------------
        //first we intialized the FirebaseAuth object
        mAuth = FirebaseAuth.getInstance();

        xRelayGmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();

            }
        });
        //------------------------------Googleplus-----------------------------------------


    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }

    private void updateUI(GoogleSignInAccount acct) {
        Log.e("testing", "came");
        Log.e("testing", String.valueOf(acct));

//        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (acct != null) {
            Log.e("testing", "not Null");

            personName = acct.getDisplayName();
            personGivenName = acct.getGivenName();
            personFamilyName = acct.getFamilyName();
            personEmail = acct.getEmail();
            personId = acct.getId();
            personPhoto = String.valueOf(acct.getPhotoUrl());
            Log.e("testing", String.valueOf(personPhoto));

//            Glide.with(getApplicationContext())
//                    .load(Uri.parse(String.valueOf(personPhoto)))
//                    .error(R.drawable.user)
//                    .into(imgPreview);

//            imgPreview.setDrawingCacheEnabled(true);

//            imgPreview.setImageURI(personPhoto);
//            bitmap = imgPreview.getDrawingCache();
            //         bitmap = BitmapFactory.decodeResource(getResources(), R.id.imgPreview);

            //   bitmap = ((BitmapDrawable) imgPreview.getDrawable()).getBitmap();

//                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), personPhoto);


            matchesList();
        }

    }

    private String encodeImage(Bitmap selectedImage) {
        Log.e("testing", String.valueOf(selectedImage));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encImage;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
        Log.e("testing", "OnActivityResult");

        //if the requestCode is the Google Sign In code that we defined at starting
        if (requestCode == RC_SIGN_IN) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            Log.e("testing", "handleSignInResult");

            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            personName = account.getDisplayName();

            personGivenName = account.getGivenName();

            personFamilyName = account.getFamilyName();

            personEmail = account.getEmail();

            personId = account.getId();

            personPhoto = String.valueOf(account.getPhotoUrl());

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }


    private void firebaseAuthWithGoogle(final GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        //getting the auth credential
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);

        //Now using firebase we are signing in the user here
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Toast.makeText(Activity_Login.this, "User Signed In", Toast.LENGTH_SHORT).show();

                            Log.e("testing", "idToken = " + acct.getIdToken());
                            Log.e("testing", "name = " + acct.getDisplayName());
                            Log.e("testing", "email = " + acct.getEmail());
                            Log.e("testing", "photoUri = " + acct.getPhotoUrl());
                            Log.e("testing", "account = " + acct.getAccount());
                            // startActivity(new Intent(Activity_Login.this, Activity_GoogleProfille.class));
                            startActivity(new Intent(Activity_Login.this, MainActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(Activity_Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }


    //this method is called on click
    private void signIn() {
        //getting the google signin intent
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    //------------------------------Googleplus-----------------------------------------

    public class LoadLogin extends AsyncTask<String, String, String>
            //implements RemoveClickListner
    {


        String status;
        String response;
        String strresponse;
        String validuser_id;
        String strcode, strtype, strmessage;
        private ProgressDialog pDialog;
        Integer intcartcount = 0;
        String strregisteredtoken;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Activity_Login.this);
            pDialog.setMessage("Please Wait ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();


        }

        public String doInBackground(String... args) {

            //  product_details_lists = new ArrayList<Product_list>();

            // Retrieve JSON Objects from the given URL address
            List<NameValuePair> userpramas = new ArrayList<NameValuePair>();

            String paramsheader = "Bearer " + "sddf";
            SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
            regId = pref.getString("regId", null);

            //   userpramas.add(new BasicNameValuePair(EndUrls.Signup_registering_by, "user"));
            userpramas.add(new BasicNameValuePair("emailphone", strmobileno));
            userpramas.add(new BasicNameValuePair("password", strpassword));
            userpramas.add(new BasicNameValuePair("firebase_token", "1234"));
            userpramas.add(new BasicNameValuePair("login_type", "normal"));
            userpramas.add(new BasicNameValuePair("fcm_token", regId));
//            userpramas.add(new BasicNameValuePair(EndUrls.Signup_Device_type, "a"));
            Log.e("testing", "userpramas = " + userpramas);

            String strurl = EndUrls.Login_URL;
            Log.e("testing", "strurl = " + strurl);
            JSONObject json = jsonParser.makeHttpRequest(strurl, "POST", userpramas);

            Log.e("testing", "json result = " + json);

            if (json == null) {
                Log.e("testing", "json result = " + json);
            } else {
                Log.e("testing", "jon2222222222222");
                try {
                    Log.e("testing", "json = " + json);


                    status = json.getString("status");
                    strresponse = json.getString("response");
                    JSONObject jsonobject = new JSONObject(strresponse);
                    strcode = jsonobject.getString("code");
                    strtype = jsonobject.getString("type");
                    strmessage = jsonobject.getString("message");
//                    strregisteredtoken = jsonobject.getString("token");
                    if (status.equals("success")) {
                        Log.e("testing", "status = " + status);

                        status = json.getString("status");
                        strresponse = json.getString("response");
                        String arrayresponse = json.getString("data");
                        JSONObject jsonobjectdata = new JSONObject(arrayresponse);
                        Log.e("testing", "adapter value=" + arrayresponse);
                        validuser_id = jsonobjectdata.getString("user_id");
                        String useremail = jsonobjectdata.getString("user_email");
                        String userPhone = jsonobjectdata.getString("user_phone");
                        String userName = jsonobjectdata.getString("user_name");

                    } else {
                        Log.e("testing", "status = " + status);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


            return response;


        }

        @Override
        protected void onPostExecute(String responce) {
            super.onPostExecute(responce);
            pDialog.dismiss();

            if (status == null || status.trim().length() == 0 || status.equals("null")) {

            } else if (status.equals("success")) {

                Intent intent = new Intent(Activity_Login.this, MainActivity.class);
                sharedPrefs.savepref(getApplicationContext(), sharedPrefs.Social_id, "0");
                sharedPrefs.savepref(getApplicationContext(), sharedPrefs.Pref_userId, validuser_id);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();

                Toast.makeText(Activity_Login.this, "Successfully Login", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(Activity_Login.this, strmessage, Toast.LENGTH_SHORT).show();


            }


        }


    }

    private void matchesList() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");
        final ProgressDialog pDialog;
        pDialog = new ProgressDialog(Activity_Login.this);
        pDialog.setMessage("Please Wait ...");
//        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        Api api = ApiClient.getClient().create(Api.class);
        if (personName == null) {
            personName = "";
        }
        if (personEmail == null) {
            personEmail = "";
        }
        if (personId == null) {
            personId = "";
        }
        String encodeImage = "";
        if (personPhoto == null) {
            personPhoto = "";
//            encodeImage = encodeImage(bitmap);

        }
        Log.e("testing", "personName = " + personName);
        Log.e("testing", "personEmail = " + personEmail);
        Log.e("testing", "personId = " + personId);
        Log.e("testing", "personPhoto = " + personPhoto);


//        viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        regId = pref.getString("regId", null);
        Log.e("testing", "status = " + regId);

        Call<LoginExample> login = api.LoginWithEmail(personPhoto, regId, personName, personEmail, personId, "google", "a");

        login.enqueue(new Callback<LoginExample>() {
            @Override
            public void onResponse(Call<LoginExample> call, Response<LoginExample> response) {
                pDialog.dismiss();
                Log.e("testing", "status = " + response.body().getStatus());
                Log.e("testing", "response = " + response.body().getLoginResponse().getMessage());
                //  Log.e("testing","response = "+response.body().getData().getPageContent());
                Log.e("testing", "response = " + response.body());
                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    Log.e("testing", "error");

                    if (response.body().getLoginResponse().getType().equals("login_success")) {
                        Intent intent = new Intent(Activity_Login.this, MainActivity.class);
                        sharedPrefs.savepref(getApplicationContext(), sharedPrefs.Pref_userId, response.body().getData().getUserId().toString());
                        sharedPrefs.savepref(getApplicationContext(), sharedPrefs.Social_id, "1");
                        signOut();

                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                        Toast.makeText(Activity_Login.this, "Successfully Login", Toast.LENGTH_SHORT).show();

                        pDialog.dismiss();

                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getLoginResponse().getMessage(), Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();
                    }
                } else {
                    Log.e("testing", "error");
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.body().getLoginResponse().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginExample> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                pDialog.dismiss();

            }
        });
    }

    private void FaceBookLogin() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");
        ProgressDialog pDialog;
        pDialog = new ProgressDialog(Activity_Login.this);
        pDialog.setMessage("Please Wait ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        Api api = ApiClient.getClient().create(Api.class);
        if (email == null) {
            personName = "";
        }
        if (name == null) {
            name = "";
        }
        if (facebook_uid == null) {
            facebook_uid = "";
        }
        if (picture == null) {
            picture = "";
        }
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        regId = pref.getString("regId", null);

        Log.e("testing", "status = " + email);
        Log.e("testing", "status = " + name);
        Log.e("testing", "status = " + facebook_uid);


//        viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);


        Call<LoginExample> login = api.LoginWithEmail(picture, regId, personName, email, facebook_uid, "facebook", "a");

        login.enqueue(new Callback<LoginExample>() {
            @Override
            public void onResponse(Call<LoginExample> call, Response<LoginExample> response) {
                pDialog.dismiss();
                Log.e("testing", "status = " + response.body().getStatus());
                Log.e("testing", "response = " + response.body().getLoginResponse().getMessage());
                //  Log.e("testing","response = "+response.body().getData().getPageContent());
                Log.e("testing", "response = " + response.body());
                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    Log.e("testing", "error");

                    if (response.body().getLoginResponse().getType().equals("login_success")) {
                        Intent intent = new Intent(Activity_Login.this, MainActivity.class);
                        sharedPrefs.savepref(getApplicationContext(), sharedPrefs.Pref_userId, response.body().getData().getUserId().toString());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                        Toast.makeText(Activity_Login.this, "Successfully Login", Toast.LENGTH_SHORT).show();

                        pDialog.dismiss();

                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getLoginResponse().getMessage(), Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();
                    }
                } else {
                    Log.e("testing", "error");
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.body().getLoginResponse().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginExample> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                pDialog.dismiss();

            }
        });
    }

    private void RetrofitLogin(final String mobileno, String password) {
        final ProgressDialog pProgressDialog;
        pProgressDialog = new ProgressDialog(Activity_Login.this);
        pProgressDialog.setMessage("Please Wait ...");
        pProgressDialog.setIndeterminate(false);
        pProgressDialog.setCancelable(false);
        pProgressDialog.show();

        //call retrofit
        //making api call
        Api api = ApiClient.getClient().create(Api.class);
        Call<LoginJson> login = api.loginjson(mobileno, password);

        login.enqueue(new Callback<LoginJson>() {
            @Override
            public void onResponse(Call<LoginJson> call, Response<LoginJson> response) {
                pProgressDialog.dismiss();
                Log.e("testing", "status = " + response.body().getStatus());
                Log.e("testing", "response = " + response.body().getResponse().getType());

                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    if (response.body().getResponse() == null) {

                    } else if (response.body().getResponse().getType().equals("login_success")) {

                        Intent intent = new Intent(Activity_Login.this, MainActivity.class);
                        SharedPreferences prefuserdata = Activity_Login.this.getSharedPreferences(sharedPrefs.Pref, 0);
                        SharedPreferences.Editor prefeditor = prefuserdata.edit();
                        prefeditor.putString(sharedPrefs.Pref_token, "" + response.body().getResponse().getToken());
                        prefeditor.putString(sharedPrefs.Pref_userId, "" + response.body().getData().getId());
                        prefeditor.commit();
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(Activity_Login.this, response.body().getResponse().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Log.e("testing", "error");
                    Toast.makeText(Activity_Login.this, response.body().getResponse().getMessage(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<LoginJson> call, Throwable t) {
                Toast.makeText(Activity_Login.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                pProgressDialog.dismiss();

            }
        });


    }
}
