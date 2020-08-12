package com.glory.apk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class Activity_Testing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__testing);
        Log.e("testing","oncreate");

        Button butsubmit = (Button) findViewById(R.id.butsubmit);
        butsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Testing.this, Activity_Testing2.class);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("testing","onstart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("testing","onrestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("testing","onresume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("testing","onpause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("testing","onstop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("testing","ondestroy");
    }

}
