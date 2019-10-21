package com.android.glory.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.android.glory.MainActivity;
import com.android.glory.R;

public class Activity_SignupOtp extends AppCompatActivity {

    Button butsubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);// hide statusbar of Android
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_otp);

        butsubmit = (Button) findViewById(R.id.butsubmit);
        butsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_SignupOtp.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
