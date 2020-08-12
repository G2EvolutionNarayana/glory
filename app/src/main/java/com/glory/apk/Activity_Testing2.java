package com.glory.apk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;


public class Activity_Testing2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__testing2);
        Log.e("testing","oncreate2");

    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.e("testing","onstart2");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("testing","onrestart2");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("testing","onresume2");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("testing","onpause2");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("testing","onstop2");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("testing","ondestroy2");
    }

}
