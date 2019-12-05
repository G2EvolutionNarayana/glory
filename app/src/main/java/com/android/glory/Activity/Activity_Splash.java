package com.android.glory.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.android.glory.MainActivity;
import com.android.glory.R;
import com.android.glory.Utilites.sharedPrefs;

public class Activity_Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);// hide statusbar of Android
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        threadcalling();
    }



    private void threadcalling() {

        // StartSmartAnimation.startAnimation(logotxt.findViewById(R.id.logotxt), AnimationType.ZoomIn, 1000, 0, true, 100);
        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{



                    SharedPreferences prefuserdata = getSharedPreferences(sharedPrefs.Pref, 0);
                    String viewuseremail = prefuserdata.getString(sharedPrefs.Pref_userId, "");

                    Log.e("userId","userid====splash"+viewuseremail);

                    if (viewuseremail.equals("") || viewuseremail.equals("null") || viewuseremail.equals(null) || viewuseremail.equals("0")) {

                        Intent intent = new Intent(Activity_Splash.this, Activity_Login.class);
                        startActivity(intent);
                        finish();

                    } else {

                        Intent intent2 = new Intent(Activity_Splash.this, MainActivity.class);
                        startActivity(intent2);
                        finish();

                    }


                }}
        };
        timerThread.start();

    }

}
