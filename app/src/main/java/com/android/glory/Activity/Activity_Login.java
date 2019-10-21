package com.android.glory.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.glory.MainActivity;
import com.android.glory.R;

public class Activity_Login extends AppCompatActivity {

    Button logsubmit;

    TextView signup,forgetpasswd,logmob,logpassword;

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

              /*  if(logmob.getText().toString().trim().isEmpty() || logmob.getText().toString().trim().contains("null")
                || logmob.getText().toString().trim().length() >=10 || logmob.getText().toString().trim().length() <= 13)
                {
                    logmob.setError("Please enter valid mobile number");
                }else if(logpassword.getText().toString().trim().isEmpty() || logpassword.getText().toString().trim().contains("null") ||
                        logpassword.getText().toString().trim().length() >= 8 || logpassword.getText().toString().trim().length() <= 12)
                {
                    logpassword.setError("Please enter valid password");
                }else {
                    Intent intent = new Intent(Activity_Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }*/
                Intent intent = new Intent(Activity_Login.this, MainActivity.class);
                startActivity(intent);
                finish();



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

    }
}
