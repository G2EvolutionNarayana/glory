package com.android.glory;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
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
import com.android.glory.Fragment.Fragment_Home;
import com.android.glory.Utilites.EndUrls;
import com.android.glory.Utilites.JSONParser;
import com.android.glory.Utilites.sharedPrefs;
import com.google.android.material.navigation.NavigationView;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawer;
    NavigationView navigationView;

    LinearLayout linearmore;

    JSONParser jsonParser = new JSONParser();

    String strregisteredtoken;

    TextView textwallet;
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


        linearmore = (LinearLayout) findViewById(R.id.linearmore);
        Fragment_Home fragment2 = new Fragment_Home();
        FragmentTransaction fragmentTransaction2 =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction2.replace(R.id.fragment_container, fragment2);
        fragmentTransaction2.commit();

        linearmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity_Navigation.class);
                startActivity(intent);
            }
        });
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
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

                        new LoadLogout().execute();


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
    public class LoadLogout extends AsyncTask<String, String, String>
            //implements RemoveClickListner
    {


        String status;
        String response;
        String strresponse;
        String strcode, strtype, strmessage;

        private ProgressDialog pDialog;
        Integer intcartcount = 0;
        String validuser_id;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please Wait ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();


        }

        public String doInBackground(String... args) {

            //  product_details_lists = new ArrayList<Product_list>();

            // Retrieve JSON Objects from the given URL address
            List<NameValuePair> userpramas = new ArrayList<NameValuePair>();

            String paramsheader = "Bearer "+strregisteredtoken;


          //  userpramas.add(new BasicNameValuePair(EndUrls.SignupOTP_URL_OTP, strotp));

            Log.e("testing", "paramsheader = " + paramsheader);
            Log.e("testing", "userpramas = " + userpramas);

            String strurl = EndUrls.Logout_URL;
            Log.e("testing", "strurl = " + strurl);
            JSONObject json = jsonParser.makeHttpRequest(strurl, "POST", userpramas, paramsheader);

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
                 /*   if (status.equals("success")) {

                        status = json.getString("status");
                        strresponse = json.getString("response");
                        String strcontent = json.getString("content");

                        JSONObject  jsonobjectcontent = new JSONObject(strcontent);
                        validuser_id = jsonobjectcontent.getString("user_id");
                      *//*  String arrayresponse = json.getString("data");

                        JSONObject  jsonobjectdata = new JSONObject(arrayresponse);
                        Log.e("testing", "adapter value=" + arrayresponse);

                        validuser_id = jsonobjectdata.getString("user_id");
*//*

                    }else{

                    }*/
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



                SharedPreferences sharedPreferences = getSharedPreferences(sharedPrefs.Pref, 0);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.clear();
                edit.commit();

                //Starting login activity
                Intent intent =new Intent(MainActivity.this, Activity_Splash.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();


            }
            else {
                Toast.makeText(MainActivity.this, strmessage, Toast.LENGTH_SHORT).show();


            }



        }



    }

}
