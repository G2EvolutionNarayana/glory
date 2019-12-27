package com.android.glory.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.glory.MainActivity;
import com.android.glory.R;
import com.android.glory.Retrofit.Api;
import com.android.glory.Retrofit.ApiClient;
import com.android.glory.Retrofit.Login.LoginJson;
import com.android.glory.Utilites.EndUrls;
import com.android.glory.Utilites.JSONParserNew;
import com.android.glory.Utilites.sharedPrefs;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Login extends AppCompatActivity {

    Button logsubmit;

    TextView signup,forgetpasswd,logmob,logpassword;

    JSONParserNew jsonParser = new JSONParserNew();

    String strmobileno, strpassword;

    //-------------------Googleplus---------------------------
    //a constant for detecting the login intent result
    private static final int RC_SIGN_IN = 234;

    //Tag for the logs optional
    private static final String TAG = "simplifiedcoding";

    //creating a GoogleSignInClient object
    GoogleSignInClient mGoogleSignInClient;

    //And also a Firebase Auth object
    FirebaseAuth mAuth;
    //-------------------Googleplus---------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logmob = findViewById(R.id.logmob);
        logpassword = findViewById(R.id.logpassword);
        signup = (TextView) findViewById(R.id.signup);
        forgetpasswd = (TextView) findViewById(R.id.forgetpasswd);

        logsubmit = (Button) findViewById(R.id.logsubmit);

        logsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                strmobileno = logmob.getText().toString();
                strpassword = logpassword.getText().toString();

                if(strmobileno==null || strmobileno.equals("null") || strmobileno.length()!=10){
                    logmob.setError("Please Enter Valid Mobile number");
                }else if(strpassword==null || strpassword.equals("null") || strpassword.length()==0){
                    logpassword.setError("Please Enter Valid Password");
                }else{

                    RetrofitLogin(strmobileno, strpassword);
                  //  new LoadLogin().execute();

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

        //Then we need a GoogleSignInOptions object
        //And we need to build it as below
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        //Then we will get the GoogleSignInClient object from GoogleSignIn class
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //Now we will attach a click listener to the sign_in_button
        //and inside onClick() method we are calling the signIn() method that will open
        //google sign in intent
        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        //------------------------------Googleplus-----------------------------------------


    }

    //------------------------------Googleplus-----------------------------------------
    @Override
    protected void onStart() {
        super.onStart();

        //if the user is already signed in
        //we will close this activity
        //and take the user to profile activity
        if (mAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(this, Activity_GoogleProfille.class));
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //if the requestCode is the Google Sign In code that we defined at starting
        if (requestCode == RC_SIGN_IN) {

            //Getting the GoogleSignIn Task
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                //Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);

                //authenticating with firebase
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Toast.makeText(Activity_Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
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

                           Log.e("testing","idToken = "+acct.getIdToken());
                           Log.e("testing","name = "+acct.getDisplayName());
                           Log.e("testing","email = "+acct.getEmail());
                           Log.e("testing","photoUri = "+acct.getPhotoUrl());
                           Log.e("testing","account = "+acct.getAccount());
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

        //starting the activity for result
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

            String paramsheader = "Bearer "+"sddf";


            //   userpramas.add(new BasicNameValuePair(EndUrls.Signup_registering_by, "user"));
            userpramas.add(new BasicNameValuePair(EndUrls.Login_mobileno, strmobileno));
            userpramas.add(new BasicNameValuePair(EndUrls.Login_Password, strpassword));

            Log.e("testing", "userpramas = " + userpramas);

            String strurl = EndUrls.Login_URL;
            Log.e("testing", "strurl = " + strurl);
            JSONObject json = jsonParser.makeHttpRequest(strurl, "POST", userpramas);

            Log.e("testing", "json result = " + json);

            if (json == null) {

            } else {
                Log.e("testing", "jon2222222222222");
                try {


                    status = json.getString("status");
                    strresponse = json.getString("response");
                    JSONObject  jsonobject = new JSONObject(strresponse);
                    strcode = jsonobject.getString("code");
                    strtype = jsonobject.getString("type");
                    strmessage = jsonobject.getString("message");
                    strregisteredtoken = jsonobject.getString("token");
                    if (status.equals("success")) {

                        status = json.getString("status");
                        strresponse = json.getString("response");



                        String arrayresponse = json.getString("data");

                        JSONObject  jsonobjectdata = new JSONObject(arrayresponse);
                        Log.e("testing", "adapter value=" + arrayresponse);

                        validuser_id = jsonobjectdata.getString("id");

                    }else{

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

            if (status == null || status.trim().length() == 0 || status.equals("null")){

            }else if (status.equals("success")) {


                Intent intent =new Intent(Activity_Login.this, MainActivity.class);
                SharedPreferences prefuserdata =  Activity_Login.this.getSharedPreferences(sharedPrefs.Pref, 0);
                SharedPreferences.Editor prefeditor =prefuserdata.edit();
                prefeditor.putString(sharedPrefs.Pref_token,""+strregisteredtoken);
                prefeditor.putString(sharedPrefs.Pref_userId,""+validuser_id);

                prefeditor.commit();
                startActivity(intent);
                finish();



            }
            else {
                Toast.makeText(Activity_Login.this, strmessage, Toast.LENGTH_SHORT).show();


            }



        }



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
        Call<LoginJson> login = api.loginjson(mobileno,password);

        login.enqueue(new Callback<LoginJson>() {
            @Override
            public void onResponse(Call<LoginJson> call, Response<LoginJson> response) {
                 pProgressDialog.dismiss();
                Log.e("testing","status = "+response.body().getStatus());
                Log.e("testing","response = "+response.body().getResponse().getType());

                if (response.body().getStatus() == null || response.body().getStatus().length() == 0){

                }else if (response.body().getStatus().equals("success")) {
                    if (response.body().getResponse() == null ){

                    }else if (response.body().getResponse().getType().equals("login_success")){


                        Intent intent =new Intent(Activity_Login.this, MainActivity.class);
                        SharedPreferences prefuserdata =  Activity_Login.this.getSharedPreferences(sharedPrefs.Pref, 0);
                        SharedPreferences.Editor prefeditor =prefuserdata.edit();
                        prefeditor.putString(sharedPrefs.Pref_token,""+response.body().getResponse().getToken());
                        prefeditor.putString(sharedPrefs.Pref_userId,""+response.body().getData().getId());

                        prefeditor.commit();
                        startActivity(intent);
                        finish();

                    }else{
                        Toast.makeText(Activity_Login.this, response.body().getResponse().getMessage(), Toast.LENGTH_SHORT).show();
                    }





                   /*

                    Intent intent=new Intent(Activity_Event_Details.this,Activity_Event_Details.class);
                    startActivity(intent);
                    finish();

*/




                    //  Toast.makeText(Activity_Event_Details.this, message, Toast.LENGTH_SHORT).show();


                } else  {
                    Log.e("testing","error");
                    Toast.makeText(Activity_Login.this, response.body().getResponse().getMessage(), Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFailure(Call<LoginJson> call, Throwable t) {
                Toast.makeText(Activity_Login.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                  pProgressDialog.dismiss();

            }
        });





    }
}
