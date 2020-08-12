package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.glory.apk.Model.Forgot.PasswordMain;
import com.glory.apk.R;
import com.glory.apk.Retrofit.Api;
import com.glory.apk.Retrofit.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityForgetPassword extends AppCompatActivity implements View.OnClickListener {
    TextView logmob;
    Button logsubmit;
    String otpToken, userId;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        mInitWidgets();

    }

    private void mInitWidgets() {
        logmob = (EditText) findViewById(R.id.logmob);
        logsubmit=(Button)findViewById(R.id.logsubmit);
        logsubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logsubmit: {
                matchesList();
            }
        }
    }

    private void matchesList() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");

        pDialog = new ProgressDialog(ActivityForgetPassword.this);
        pDialog.setMessage("Please Wait ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        Log.e("testing", "strregisteredtoken = " + "matchesList");

        String editText=logmob.getText().toString().trim();
        Log.e("testing", "editText = " + editText);
        Api api = ApiClient.getClient().create(Api.class);
        Call<PasswordMain> login = api.ForgotPassword(editText);

        login.enqueue(new Callback<PasswordMain>() {
            @Override
            public void onResponse(Call<PasswordMain> call, Response<PasswordMain> response) {
                pDialog.dismiss();
                Log.e("testing", "status = " + response.body());
                Log.e("testing", "response = " + response.body().getPasswordResponse().getMessage());
                //  Log.e("testing","response = "+response.body().getData().getPageContent());
                Log.e("testing", "response = " + response.body());

                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    if (response.body().getPasswordResponse() == null) {

                    } else if (response.body().getPasswordResponse().getType().equals("send_success")) {
                        otpToken = response.body().getPasswordData().getForgotExp().toString();
                        userId = response.body().getPasswordData().getUserId().toString();
                        Intent intent = new Intent(getApplicationContext(), Activity_SignupOtp.class);
                        intent.putExtra("ScreenName", "ForgotPassword");
                        intent.putExtra("strregisteredtoken",otpToken);
                        intent.putExtra("temp_user",userId);
                        startActivity(intent);
                        pDialog.dismiss();

                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getPasswordResponse().getMessage(), Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();

                    }

                } else {
                    Log.e("testing", "error");
                    Toast.makeText(getApplicationContext(), response.body().getPasswordResponse().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PasswordMain> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                pDialog.dismiss();

            }
        });
    }
}
