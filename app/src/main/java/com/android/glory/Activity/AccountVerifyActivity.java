package com.android.glory.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.glory.Model.VerifyEmailModel.VerifyEmailExample;
import com.android.glory.Model.WithdrawStatusModel.WithdrawStatusExample;
import com.android.glory.R;
import com.android.glory.Retrofit.Api;
import com.android.glory.Retrofit.ApiClient;
import com.android.glory.Utilites.sharedPrefs;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountVerifyActivity extends AppCompatActivity {
    TextView xTvMailVerify, xTvMobileVerify, xTvPanCardVerify, xTvBankVerify;
    RelativeLayout xRelayHide;
    String viewuseremail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_verify);
        mInitWidgets();
//        matchesList();
    }

    private void mInitWidgets() {
        xTvMailVerify = findViewById(R.id.xTvMailVerify);
        xTvMobileVerify = findViewById(R.id.xTvMobileVerify);
        xTvPanCardVerify = findViewById(R.id.xTvPanCardVerify);
        xTvBankVerify = findViewById(R.id.xTvBankVerify);
        xRelayHide = findViewById(R.id.xRelayHide);

        xTvMailVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (xTvMailVerify.getText().toString().equals("Verified")) {

                } else {
                    Intent intent = new Intent(getApplicationContext(), VerifyEmailAccountActivity.class);
                    startActivity(intent);
                }

            }
        });

        xTvMobileVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (xTvMobileVerify.getText().toString().equals("Verified")) {

                } else {
                    Intent intent = new Intent(getApplicationContext(), VerifyPhoneAccountActivity.class);
                    startActivity(intent);
                }

            }
        });
        xTvPanCardVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (xTvPanCardVerify.getText().toString().equals("Verify")) {
                    Intent intent = new Intent(getApplicationContext(), VerifyPanCardActivity.class);
                    startActivity(intent);
                } else if (xTvPanCardVerify.getText().toString().equals("Rejected")) {
                    Intent intent = new Intent(getApplicationContext(), VerifyPanCardActivity.class);
                    startActivity(intent);
                } else if (xTvPanCardVerify.getText().toString().equals("Verified")) {
                    Intent intent = new Intent(getApplicationContext(), VerifyPanCardActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), VerifyPanCardActivity.class);
                    startActivity(intent);
                }
            }
        });
        xTvBankVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!xTvMailVerify.getText().toString().equals("Verified") || xTvMobileVerify.getText().toString().equals("Verified") || xTvPanCardVerify.getText().toString().equals("Verified")) {
                    xRelayHide.setVisibility(View.VISIBLE);
                } else {
                    Intent intent = new Intent(getApplicationContext(), BankDetailsVerifeyActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void matchesList() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");
        ProgressDialog ProgressDialog;
        ProgressDialog = new ProgressDialog(AccountVerifyActivity.this);
        ProgressDialog.setMessage("Please Wait ...");
        ProgressDialog.setIndeterminate(false);
        ProgressDialog.setCancelable(false);
        ProgressDialog.show();

        Api api = ApiClient.getClient().create(Api.class);
        viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);


        Call<WithdrawStatusExample> login = api.WithDrawVerifyList(viewuseremail);
//272301513240 icic0002723
        login.enqueue(new Callback<WithdrawStatusExample>() {
            @Override
            public void onResponse(Call<WithdrawStatusExample> call, Response<WithdrawStatusExample> response) {

                ProgressDialog.dismiss();

                Log.e("testing", "status = " + response.body().getStatus());
                Log.e("testing", "response = " + response.body().getWithdrawStatusResponse().getMessage());
                //  Log.e("testing","response = "+response.body().getData().getPageContent());
                Log.e("testing", "response = " + response.body());
                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    if (response.body().getWithdrawStatusResponse().getType().equals("data_found")) {

                        if (response.body().getData().getEmail() == null) {

                            xTvMailVerify.setText("Verify");
                            xTvMailVerify.setTextColor(Color.parseColor("#FFFFFF"));

                        } else {
                            if (response.body().getData().getEmail().getVerified() == 0) {

                                xTvMailVerify.setText("Pending");
                                xTvMailVerify.setTextColor(Color.parseColor("#FFFFFF"));

                            } else if (response.body().getData().getEmail().getVerified() == 1) {
                                xTvMailVerify.setText("Verified");
                                xTvMailVerify.setTextColor(Color.parseColor("#22AD0C"));
                            } else {
                                xTvMailVerify.setText("Rejected");
                                xTvMailVerify.setTextColor(Color.parseColor("#F34235"));
                            }

                        }
                        if (response.body().getData().getPhone() == null) {
                            xTvMobileVerify.setText("Verify");
                            xTvMobileVerify.setTextColor(Color.parseColor("#FFFFFF"));
                        } else {
                            if (response.body().getData().getPhone().getVerified() == 0) {

                                xTvMobileVerify.setText("Pending");
                                xTvMobileVerify.setTextColor(Color.parseColor("#FFFFFF"));

                            } else if (response.body().getData().getPhone().getVerified() == 1) {
                                xTvMobileVerify.setText("Verified");
                                xTvMobileVerify.setTextColor(Color.parseColor("#22AD0C"));
                            } else {
                                xTvMobileVerify.setText("Rejected");
                                xTvMobileVerify.setTextColor(Color.parseColor("#F34235"));
                            }
                        }
                        if (response.body().getData().getPancard() == null) {
                            xTvPanCardVerify.setText("Verify");
                            xTvPanCardVerify.setTextColor(Color.parseColor("#FFFFFF"));
                        } else {
                            if (response.body().getData().getEmail().getVerified() == 0) {

                                xTvPanCardVerify.setText("Pending");
                                xTvPanCardVerify.setTextColor(Color.parseColor("#FFFFFF"));

                            } else if (response.body().getData().getEmail().getVerified() == 1) {
                                xTvPanCardVerify.setText("Verified");
                                xTvPanCardVerify.setTextColor(Color.parseColor("#22AD0C"));
                            } else {
                                xTvPanCardVerify.setText("Rejected");
                                xTvPanCardVerify.setTextColor(Color.parseColor("#F34235"));
                            }
                        }
                        ProgressDialog.dismiss();

                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getWithdrawStatusResponse().getMessage(), Toast.LENGTH_SHORT).show();
                        ProgressDialog.dismiss();
                    }
                } else {
                    Log.e("testing", "error");
                    ProgressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.body().getWithdrawStatusResponse().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WithdrawStatusExample> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                ProgressDialog.dismiss();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        matchesList();
    }

}
