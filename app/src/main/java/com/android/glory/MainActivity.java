package com.android.glory;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.glory.Activity.Activity_Balance;
import com.android.glory.Activity.Activity_Change_Pwd;
import com.android.glory.Activity.Activity_FantasyPointSystem;
import com.android.glory.Activity.Activity_InfoSettings;
import com.android.glory.Activity.Activity_Navigation;
import com.android.glory.Activity.Activity_Profile;
import com.android.glory.Activity.Activity_Rewards;
import com.android.glory.Activity.Activity_Splash;
import com.android.glory.Activity.VerifyPanCardActivity;
import com.android.glory.Fragment.FragmentNotification;
import com.android.glory.Fragment.Fragment_Home;
import com.android.glory.Fragment.Fragment_MyMatches;
import com.android.glory.Model.AboutUs.AboutExample;
import com.android.glory.Model.Logout.LogoutExample;
import com.android.glory.Model.PancardUpload.PancardExample;
import com.android.glory.Retrofit.Api;
import com.android.glory.Retrofit.ApiClient;
import com.android.glory.Retrofit.Logout.LogoutJson;
import com.android.glory.Retrofit.UpdateProfile.UpdateprofileJson;
import com.android.glory.Utilites.EndUrls;
import com.android.glory.Utilites.JSONParser;
import com.android.glory.Utilites.sharedPrefs;
import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawer;
    ImageView imageViewprofile;
    NavigationView navigationView;
    private ProgressDialog dialog;

    LinearLayout linearhome, linearmatches, linearmore,linearnotifications;

    JSONParser jsonParser = new JSONParser();

    String strregisteredtoken;

    TextView textwallet,drawer_titleemail;
    TextView drawer_title_name;
    Boolean isPermissionGranted = false;
    private int GALLERY = 1, CAMERA = 2;
    Bitmap bitmap;
    private ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

       // toolbar.setLogo(R.drawable.signupwithfb);
        String strtitle = "";
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>" + strtitle + "</font>"));

        SharedPreferences prefuserdata3 = getSharedPreferences(sharedPrefs.Pref, 0);
        strregisteredtoken = prefuserdata3.getString(sharedPrefs.Pref_token, "");


     /*   if (Build.VERSION.SDK_INT >= 11)
            toolbar.setBackgroundDrawable(getResources().getDrawable(
                    R.drawable.borderlogin));
        if (Build.VERSION.SDK_INT >= 21)
            toolbar.setElevation(0);*/

        drawer_title_name=(TextView)findViewById(R.id.drawer_title_name);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        //setupToolBar();
        navigationView = (NavigationView) findViewById(R.id.navigation);
        //navigationView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        navigationView.setNavigationItemSelectedListener(this);

        mFragmentManager = getSupportFragmentManager();

        textwallet = (TextView) findViewById(R.id.textwallet);
        imageViewprofile = (ImageView) findViewById(R.id.imageViewprofile);

        drawer_titleemail = (TextView) findViewById(R.id.drawer_titleemail);

        linearhome = (LinearLayout) findViewById(R.id.linearhome);
        linearmatches = (LinearLayout) findViewById(R.id.linearmatches);
        linearnotifications=(LinearLayout) findViewById(R.id.linearnotifications);
        linearmore = (LinearLayout) findViewById(R.id.linearmore);

        Fragment_Home fragment2 = new Fragment_Home();
        FragmentTransaction fragmentTransaction2 =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction2.replace(R.id.fragment_container, fragment2);
        fragmentTransaction2.commit();

        linearhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        linearmatches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_MyMatches fragment1 = new Fragment_MyMatches();
                FragmentTransaction fragmentTransaction3 =
                        getSupportFragmentManager().beginTransaction();
                fragmentTransaction3.replace(R.id.fragment_container, fragment1);
                fragmentTransaction3.commit();
            }
        });
        linearnotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentNotification fragment1 = new FragmentNotification();
                FragmentTransaction fragmentTransaction3 =
                        getSupportFragmentManager().beginTransaction();
                fragmentTransaction3.replace(R.id.fragment_container, fragment1);
                fragmentTransaction3.commit();
            }
        });
        linearmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity_Navigation.class);
                startActivity(intent);
            }
        });

        imageViewprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (requestMultiplePermissions()) {
                    showPictureDialog();

                }else {
                }
                
            }
        });


        Activity_Profile();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if ((sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Wallet_Amount)==null ||sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Wallet_Amount).isEmpty())){
            textwallet.setText("\u20B9"+"0");

        }else {
            textwallet.setText("\u20B9"+" "+sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Wallet_Amount));
            Log.e("testing", "status = " + sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Wallet_Amount));

        }
    }

    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    matchesList();

                } catch (IOException e) {
                    e.printStackTrace();
//                    Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            bitmap = (Bitmap) data.getExtras().get("data");
            matchesList();
        }
    }

    private String convertToString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    private void matchesList() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");

        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage("Please Wait ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        String image = convertToString();

        Api api = ApiClient.getClient().create(Api.class);
        String viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);
        Call<UpdateprofileJson> login = api.UpdateImage(viewuseremail, image);

//        Call<UpdateprofileJson> login = api.PancardImageUpload(image);
        login.enqueue(new Callback<UpdateprofileJson>() {
            @Override
            public void onResponse(Call<UpdateprofileJson> call, Response<UpdateprofileJson> response) {
                pDialog.dismiss();
                Log.e("testing", "status = " + response.body());

                //       Log.e("testing", "status = " + response.body().getStatus());
                Log.e("testing", "response = " + response.body().getResponse().getMessage());
                //  Log.e("testing","response = "+response.body().getData().getPageContent());
                Log.e("testing", "response = " + response.body());
                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {

                        if (response.body().getResponse() == null) {

                        } else if (response.body().getResponse().getType().equals("update_success")) {
                            Activity_Profile();
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(), response.body().getResponse().getType(), Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getApplicationContext(), response.body().getResponse().getType(), Toast.LENGTH_SHORT).show();
                            dialog.dismiss();

                        }

                } else {
                    Log.e("testing", "error");
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.body().getResponse().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpdateprofileJson> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e("testing", "response = " + t.getLocalizedMessage());

                pDialog.dismiss();

            }
        });
    }

    private boolean requestMultiplePermissions() {
        isPermissionGranted = false;
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted

                        if (report.areAllPermissionsGranted()) {

                            isPermissionGranted = true;
                            //  Toast.makeText(getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        } else {
                            isPermissionGranted = true;

                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings

                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
        return isPermissionGranted;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Fragment_Home fragment2 = new Fragment_Home();
            FragmentTransaction fragmentTransaction2 =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction2.replace(R.id.fragment_container, fragment2);
            fragmentTransaction2.commit();
            Log.e("testing", "paramsheader = " + fragment2);

        }else if(id == R.id.nav_profile)
        {
            Intent intent = new Intent(MainActivity.this, Activity_Profile.class);
            startActivity(intent);
        }else if(id == R.id.nav_ChangePassword)
        {
            Intent intent = new Intent(MainActivity.this, Activity_Change_Pwd.class);
            startActivity(intent);
        }else if(id == R.id.nav_balance)
        {
            Intent intent = new Intent(MainActivity.this, Activity_Balance.class);
            startActivity(intent);
        }else if(id == R.id.nav_rewards)
        {
            Intent intent = new Intent(MainActivity.this, Activity_Rewards.class);
            startActivity(intent);
        }else if(id == R.id.nav_info)
        {
            Intent intent = new Intent(MainActivity.this, Activity_InfoSettings.class);
            startActivity(intent);
        }else if(id == R.id.nav_pointsystem)
        {
            Intent intent = new Intent(MainActivity.this, Activity_FantasyPointSystem.class);
            startActivity(intent);
        }
        else if(id == R.id.logout)
        {

            logout();


        }
       /* else if(id == R.id.nav_password)
        {
            Intent intent = new Intent(MainActivity.this, Activity_Change_Pwd.class);
            startActivity(intent);
        }

        else if(id == R.id.nav_tc)
        {
            Intent intent = new Intent(MainActivity.this, Activity_TC.class);
            startActivity(intent);
        }
        else if(id == R.id.nav_pp)
        {
            Intent intent = new Intent(MainActivity.this, Activity_PP.class);
            startActivity(intent);
        }


        else if(id == R.id.nav_aboutus)
        {
            Intent intent = new Intent(MainActivity.this, Activity_AboutUs.class);
            startActivity(intent);
        }
        else if(id == R.id.nav_contactus)
        {
            Intent intent = new Intent(MainActivity.this, Activity_ContactUs.class);
            startActivity(intent);
        }
        else if(id == R.id.nav_logout)
        {
            logout();
        }*/



        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Logout function
    private void logout() {
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //new LoadLogout().execute();
                        RetrofitLogin();

                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }


    private void RetrofitLogin() {

        Log.e("testing","strregisteredtoken = "+strregisteredtoken);

        final ProgressDialog pProgressDialog;
        pProgressDialog = new ProgressDialog(MainActivity.this);
        pProgressDialog.setMessage("Please Wait ...");
        pProgressDialog.setIndeterminate(false);
        pProgressDialog.setCancelable(false);
        pProgressDialog.show();

        //call retrofit
        //making api call
        String viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);
        String social_id = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Social_id);

        Api api = ApiClient.getClient().create(Api.class);
        Call<LogoutExample> login = api.logoutjson(social_id,viewuseremail);

        login.enqueue(new Callback<LogoutExample>() {
            @Override
            public void onResponse(Call<LogoutExample> call, Response<LogoutExample> response) {
                pProgressDialog.dismiss();
                Log.e("testing","status = "+response.body().getStatus());
                Log.e("testing","response = "+response.body().getResponse().getType());

                if (response.body().getStatus() == null || response.body().getStatus().length() == 0){

                }else if (response.body().getStatus().equals("success")) {
                    if (response.body().getResponse() == null ){

                    }else if (response.body().getStatus().equals("success")){
                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                        sharedPreferences.edit().remove(sharedPrefs.Pref_userId).commit();


                        //Starting login activity
                        Intent intent =new Intent(MainActivity.this, Activity_Splash.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();

                    }else{
                        Toast.makeText(MainActivity.this, response.body().getResponse().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } else  {
                    Log.e("testing","error");
                    Toast.makeText(MainActivity.this, response.body().getResponse().getMessage(), Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFailure(Call<LogoutExample> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                pProgressDialog.dismiss();

            }
        });





    }
    private void Activity_Profile() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");

        dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Please Wait ...");
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.show();

//        Api api = ApiClient.getClient().create(Api.class);
//        Call<AboutExample> login = api.PlayersList("1");


        String viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);
        Log.e("testing", "viewuseremail = " + viewuseremail);

        Api api = ApiClient.getClient().create(Api.class);
        Call<AboutExample> login = api.aboutusjson(viewuseremail);
        login.enqueue(new Callback<AboutExample>() {
            @Override
            public void onResponse(Call<AboutExample> call, Response<AboutExample> response) {
                dialog.dismiss();
                Log.e("testing", "status = " + response.body().getStatus());
                Log.e("testing", "response = " + response.body().getAboutResponse().getType());
                //  Log.e("testing","response = "+response.body().getData().getPageContent());

                Log.e("testing", "response = " + response.body());

                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    if (response.body().getAboutResponse() == null) {

                    } else if (response.body().getAboutResponse().getType().equals("data_available")) {

                        drawer_title_name.setText(Html.fromHtml(response.body().getAboutData().getName()));
                        Log.e("testing", "response = " +response.body().getAboutData().getName());

                        drawer_titleemail.setText(Html.fromHtml(response.body().getAboutData().getEmail()));
                        Log.e("testing Image",String.valueOf(response.body().getAboutData().getImage()));

                        if (String.valueOf(response.body().getAboutData().getImage()).equals("null") || response.body().getAboutData().getImage() .toString().length() == 0) {
                            Glide.with(getApplicationContext())
                                    .load((R.drawable.user)).placeholder(R.drawable.user)
                                    .into(imageViewprofile);
                            Log.e("testing", "getImageUrl = " + "null");

                        } else {
                            Glide.with(getApplicationContext())
                                    .load(Uri.parse(response.body().getAboutData().getImage().toString()))
                                    .error(R.drawable.user)
                                    .into(imageViewprofile);
                            Log.e("testing", "getImageUrl = " + "image");
                            sharedPrefs.savepref(getApplicationContext(), sharedPrefs.Profile_Image, response.body().getAboutData().getImage().toString());

                        }
                        
                        if ((response.body().getAboutData().getWalletAmount()==null)){
                            textwallet.setText("\u20B9"+"0");

                        }else {
                            textwallet.setText(("\u20B9"+" "+response.body().getAboutData().getWalletAmount().toString()));
                            sharedPrefs.savepref(getApplicationContext(), sharedPrefs.Wallet_Amount, response.body().getAboutData().getWalletAmount().toString());

                        }
//                        textdob.setText(Html.fromHtml(response.body().getAboutData().get()));
//                        editname.setText(Html.fromHtml(response.body().getAboutData().getName()));

                    } else {
                        Toast.makeText(MainActivity.this, response.body().getAboutResponse().getType(), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Log.e("testing", "error");
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.body().getAboutResponse().getType(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<AboutExample> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        });
    }

}
