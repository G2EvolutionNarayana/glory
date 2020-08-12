package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.glory.apk.Model.VerifyOtpPhoneActivity.VerifyOtpPhoneExample;
import com.glory.apk.Model.VerifyPhoneAccountModel.VeifyPhoneAccountExample;
import com.glory.apk.R;
import com.glory.apk.Retrofit.Api;
import com.glory.apk.Retrofit.ApiClient;
import com.glory.apk.Utilites.sharedPrefs;
import com.chaos.view.PinView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyPhoneActivity extends AppCompatActivity {
    PinView firstPinView;
    String strotp;
    Button butsubmit;
    TextView textresend;
    String viewuseremail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);
        mInitWidgets();
    }

    private void mInitWidgets() {
        firstPinView = (PinView) findViewById(R.id.firstPinView);
        firstPinView.setItemCount(4);
        firstPinView.setAnimationEnable(true);// start animation when adding text
        firstPinView.setCursorVisible(false);
        firstPinView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                strotp = firstPinView.getText().toString().trim();
            }
        });
        butsubmit = (Button) findViewById(R.id.butsubmit);
        butsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strotp = firstPinView.getText().toString().trim();
                if (strotp == null || strotp.trim().equals("null") || strotp.trim().length() == 0) {
                    firstPinView.setError("Please Enter Valid OTP");
                } else {
                    Log.e("testing", "firstPinView = " + firstPinView);
                    VerifyOtp();

                }
            }
        });

        textresend = (TextView) findViewById(R.id.textresend);

        textresend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                matchesList();

            }
        });
    }
    private void VerifyOtp() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");
        final ProgressDialog pDialog;
        pDialog = new ProgressDialog(VerifyPhoneActivity.this);
        pDialog.setMessage("Please Wait ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        Api api = ApiClient.getClient().create(Api.class);
        viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);
        Log.e("testing", "status = " + getIntent().getStringExtra("strregisteredtoken"));
        Log.e("testing", "status = " + viewuseremail);
        Log.e("testing", "status = " + strotp);


        Call<VerifyOtpPhoneExample> login = api.VerifyPhoneOtpAcount(getIntent().getStringExtra("strregisteredtoken"), viewuseremail,strotp);

        login.enqueue(new Callback<VerifyOtpPhoneExample>() {
            @Override
            public void onResponse(Call<VerifyOtpPhoneExample> call, Response<VerifyOtpPhoneExample> response) {
                pDialog.dismiss();
                Log.e("testing", "status = " + response.body().getStatus());
                Log.e("testing", "response = " + response.body().getVerifyOtpPhoneResponse().getMessage());
                //  Log.e("testing","response = "+response.body().getData().getPageContent());
                Log.e("testing", "response = " + response.body());
                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    if (response.body().getVerifyOtpPhoneResponse().getType().equals("verify_success")) {
//                        Log.e("testing", "dateumList.size = " + dateumList.size());
//                        dateumList=response.body().getData();
                        Intent intent = new Intent(VerifyPhoneActivity.this, AccountVerifyActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                        Toast.makeText(getApplicationContext(), response.body().getVerifyOtpPhoneResponse().getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                        pDialog.dismiss();

                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getVerifyOtpPhoneResponse().getMessage(), Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();
                    }

                } else {
                    Log.e("testing", "error");
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.body().getVerifyOtpPhoneResponse().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<VerifyOtpPhoneExample> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                pDialog.dismiss();

            }
        });
    }


    private void matchesList() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");
        final ProgressDialog pDialog;
        pDialog = new ProgressDialog(VerifyPhoneActivity.this);
        pDialog.setMessage("Please Wait ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        Api api = ApiClient.getClient().create(Api.class);
        viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);
        Call<VeifyPhoneAccountExample> login = api.VerifyPhoneAcount(getIntent().getStringExtra("strregisteredtoken"), viewuseremail);

        login.enqueue(new Callback<VeifyPhoneAccountExample>() {
            @Override
            public void onResponse(Call<VeifyPhoneAccountExample> call, Response<VeifyPhoneAccountExample> response) {
                pDialog.dismiss();
                Log.e("testing", "status = " + response.body().getStatus());
                Log.e("testing", "response = " + response.body().getResponse().getMessage());
                //  Log.e("testing","response = "+response.body().getData().getPageContent());
                Log.e("testing", "response = " + response.body());
                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    if (response.body().getResponse().getType().equals("verify_success")) {

                        Toast.makeText(getApplicationContext(), response.body().getResponse().getMessage(), Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();
                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getResponse().getMessage(), Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();
                    }

                } else {
                    Log.e("testing", "error");
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.body().getResponse().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<VeifyPhoneAccountExample> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                pDialog.dismiss();

            }
        });
    }
}
