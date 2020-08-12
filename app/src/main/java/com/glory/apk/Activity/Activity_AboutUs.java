package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.glory.apk.R;
import com.glory.apk.Utilites.JSONParser;
import com.glory.apk.Utilites.sharedPrefs;

public class Activity_AboutUs extends AppCompatActivity {

    private ProgressDialog dialog;

    JSONParser jsonParser = new JSONParser();
    String strregisteredtoken;

    TextView textdesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__about_us);
        SharedPreferences prefuserdata3 = getSharedPreferences(sharedPrefs.Pref, 0);
        strregisteredtoken = prefuserdata3.getString(sharedPrefs.Pref_token, "");

        textdesc = (TextView) findViewById(R.id.textdesc);



    }





}
