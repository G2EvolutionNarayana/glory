package com.android.glory;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.glory.Activity.Activity_Balance;
import com.android.glory.Activity.Activity_FantasyPointSystem;
import com.android.glory.Activity.Activity_InfoSettings;
import com.android.glory.Activity.Activity_Navigation;
import com.android.glory.Activity.Activity_PointSystem;
import com.android.glory.Activity.Activity_Profile;
import com.android.glory.Activity.Activity_Rewards;
import com.android.glory.Activity.Activity_Splash;
import com.android.glory.Fragment.Fragment_Home;
import com.android.glory.Utilites.CommanMethods;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawer;
    NavigationView navigationView;

    LinearLayout linearmore;


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

                        //Getting out sharedpreferences
                        SharedPreferences sharedPreferences = getSharedPreferences(CommanMethods.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = sharedPreferences.edit();
/*

                        //Puting the value false for loggedin
                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);

                        //Putting blank value to email
                        editor.putString(Config.EMAIL_SHARED_PREF, "");
*/

                        //Saving the sharedpreferences
                        editor.commit();

                        //Starting login activity
                        Intent intent = new Intent(MainActivity.this, Activity_Splash.class);
                        startActivity(intent);
                        finish();
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
}
