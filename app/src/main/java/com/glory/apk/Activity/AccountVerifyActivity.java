package com.glory.apk.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.appevents.ml.Utils;
import com.glory.apk.R;
import com.glory.apk.Retrofit.Api;
import com.glory.apk.Retrofit.ApiClient;
import com.glory.apk.Utilites.StaticUtils;
import com.glory.apk.Utilites.sharedPrefs;
import com.glory.apk.Model.WithdrawVerificationModel.Example;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountVerifyActivity extends AppCompatActivity {
    TextView xTvMailVerify, xTvMobileVerify, xTvPanCardVerify, xTvBankVerify, xTvReson;
    RelativeLayout xRelayHide, xRelayEmailVerify, xRelayPhoneVerify, xRelayPanVerify, xRelayBankVerify;
    ScrollView xScroLLMain;
    String viewuseremail, panName, panNumber, pandob, panSate, bankName, bankAccount, bankIfsc, bankid, panid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_verify);
        mInitWidgets();

    }

    private void mInitWidgets() {
        bankName = "";
        bankAccount = "";
        bankIfsc = "";
        panName = "";
        panNumber = "";
        pandob = "";
        panSate = "";
        bankid="";
        panid="";
        xTvMailVerify = findViewById(R.id.xTvMailVerify);
        xTvMobileVerify = findViewById(R.id.xTvMobileVerify);
        xTvPanCardVerify = findViewById(R.id.xTvPanCardVerify);
        xTvBankVerify = findViewById(R.id.xTvBankVerify);
        xRelayHide = findViewById(R.id.xRelayHide);
        xScroLLMain = findViewById(R.id.xScroLLMain);
        xTvReson = findViewById(R.id.xTvReson);
        xRelayEmailVerify = findViewById(R.id.xRelayEmailVerify);
        xRelayPhoneVerify = findViewById(R.id.xRelayPhoneVerify);
        xRelayPanVerify = findViewById(R.id.xRelayPanVerify);
        xRelayBankVerify = findViewById(R.id.xRelayBankVerify);


        xRelayEmailVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (xTvMailVerify.getText().toString().equals("Verified")) {
                    Toast.makeText(getApplicationContext(), "Already Email verified", Toast.LENGTH_LONG).show();

                } else {
                    Intent intent = new Intent(getApplicationContext(), VerifyEmailAccountActivity.class);
                    startActivity(intent);
                }

            }
        });

        xRelayPhoneVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (xTvMobileVerify.getText().toString().equals("Verified")) {
                    Toast.makeText(getApplicationContext(), "Already PhoneNumber verified", Toast.LENGTH_LONG).show();

                } else {
                    Intent intent = new Intent(getApplicationContext(), VerifyPhoneAccountActivity.class);
                    startActivity(intent);
                }

            }
        });
        xRelayPanVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (xTvPanCardVerify.getText().toString().equals("Verify")) {
                    mCallPanverify();

                } else if (xTvPanCardVerify.getText().toString().equals("Rejected")) {
                    mCallPanverify();
                } else if (xTvPanCardVerify.getText().toString().equals("Verified")) {
                    Toast.makeText(getApplicationContext(), "Already panCard verified", Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(getApplicationContext(), "Verification still not completed", Toast.LENGTH_LONG).show();

                }
            }
        });
        xRelayBankVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!xTvMailVerify.getText().toString().equals("Verified") || !xTvMobileVerify.getText().toString().equals("Verified") || !xTvPanCardVerify.getText().toString().equals("Verified")) {
                    xRelayHide.setVisibility(View.VISIBLE);
                    xTvReson.setVisibility(View.GONE);

                } else {

                    if (xTvBankVerify.getText().toString().equals("Verify")) {
                        mCallBankVerify();
                    } else if (xTvBankVerify.getText().toString().equals("Rejected")) {
                        mCallBankVerify();
                    } else if (xTvBankVerify.getText().toString().equals("Verified")) {

                        Toast.makeText(getApplicationContext(), "Already Bank Account verified", Toast.LENGTH_LONG).show();
                    } else {

                        Toast.makeText(getApplicationContext(), "Verification still not completed", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void mCallBankVerify() {
        Intent intent = new Intent(getApplicationContext(), BankDetailsVerifeyActivity.class);
        intent.putExtra(StaticUtils.BRANCH_NAME, bankName);
        intent.putExtra(StaticUtils.ACCOUNT_NUMBER, bankAccount);
        intent.putExtra(StaticUtils.IFSC_CODE, bankIfsc);
        intent.putExtra(StaticUtils.BANK_ID, bankid);
        startActivity(intent);
    }

    private void mCallPanverify() {
        Intent intent = new Intent(getApplicationContext(), VerifyPanCardActivity.class);
        intent.putExtra(StaticUtils.PANNAME, panName);
        intent.putExtra(StaticUtils.PANNUMBER, panNumber);
        intent.putExtra(StaticUtils.PANDOB, pandob);
        intent.putExtra(StaticUtils.PANSTATE, panSate);
        intent.putExtra(StaticUtils.PANID, panid);
        startActivity(intent);
    }

    private void matchesList() {
        ProgressDialog ProgressDialog;
        ProgressDialog = new ProgressDialog(AccountVerifyActivity.this);
        ProgressDialog.setMessage("Please Wait ...");
        ProgressDialog.setIndeterminate(false);
        ProgressDialog.setCancelable(false);
        ProgressDialog.show();

        Api api = ApiClient.getClient().create(Api.class);
        viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);

        Call<Example> login = api.WithDrawVerifyList(viewuseremail);
//        Call<Example> login = api.WithDrawVerifyList("44");

        login.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                ProgressDialog.dismiss();

                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {


                    Log.e("testing", "status = " + response.body().getStatus());
                    Log.e("testing", "response = " + response.body().getResponse().getMessage());
                    Log.e("testing", "response = " + response.body());

                    if (response.body().getResponse().getType().equals("data_found")) {

                        if (response.body().getData().getEmail() != null && response.body().getData().getPhone() != null && response.body().getData().getPancard() != null && response.body().getData().getBankDetails() != null) {

                            if (response.body().getData().getEmail().getVerified() == 1 && response.body().getData().getPhone().getVerified() == 1 && response.body().getData().getBankDetails().getVerified() == 1 && response.body().getData().getPancard().getVerified() == 1) {
                                Intent intent = new Intent(getApplicationContext(), FinalWithDrawAmountActivity.class);
                                bankName = response.body().getData().getBankDetails().getAccountName();
                                bankAccount = response.body().getData().getBankDetails().getAccountNumber();
                                bankIfsc = response.body().getData().getBankDetails().getIfscCode();
                                intent.putExtra(StaticUtils.BRANCH_NAME, bankName);
                                intent.putExtra(StaticUtils.ACCOUNT_NUMBER, bankAccount);
                                startActivity(intent);
                                finish();
                            } else {
                                mCallVerify(response);
                            }
                        } else {
                            mCallVerify(response);

                        }
                        ProgressDialog.dismiss();

                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getResponse().getMessage(), Toast.LENGTH_SHORT).show();
                        ProgressDialog.dismiss();
                    }
                } else {
                    ProgressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.body().getResponse().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                ProgressDialog.dismiss();
            }
        });
    }

    private void mCallVerify(Response<Example> response) {
        xScroLLMain.setVisibility(View.VISIBLE);

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

            if (response.body().getData().getPancard().getVerified() == 0) {
                xTvPanCardVerify.setText("Pending");
                xTvPanCardVerify.setTextColor(Color.parseColor("#FFFFFF"));
            } else if (response.body().getData().getPancard().getVerified() == 1) {
                xTvPanCardVerify.setText("Verified");
                xTvPanCardVerify.setTextColor(Color.parseColor("#22AD0C"));
            } else {
                xTvPanCardVerify.setText("Rejected");
                if (response.body().getData().getPancard().getReject_reason() != null) {
                    xTvReson.setText(response.body().getData().getPancard().getReject_reason());
                    xRelayHide.setVisibility(View.GONE);
                    xTvReson.setVisibility(View.VISIBLE);

                }
                panName = response.body().getData().getPancard().getName();
                panNumber = response.body().getData().getPancard().getPanNumber();
                pandob = response.body().getData().getPancard().getDob();
                panSate = response.body().getData().getPancard().getState();
                panid = String.valueOf(response.body().getData().getPancard().getId());
                xTvPanCardVerify.setTextColor(Color.parseColor("#F34235"));
            }
        }


        if (response.body().getData().getBankDetails() == null) {
            xTvBankVerify.setText("Verify");
            xTvBankVerify.setTextColor(Color.parseColor("#FFFFFF"));
        } else {

            if (response.body().getData().getBankDetails().getVerified() == 0) {

                xTvBankVerify.setText("Pending");
                xTvBankVerify.setTextColor(Color.parseColor("#FFFFFF"));

            } else if (response.body().getData().getBankDetails().getVerified() == 1) {
                xTvBankVerify.setText("Verified");
                xTvBankVerify.setTextColor(Color.parseColor("#22AD0C"));
            } else {
                xTvBankVerify.setText("Rejected");
                if (response.body().getData().getBankDetails().getReject_reason() != null) {
                        xTvReson.setText(response.body().getData().getBankDetails().getReject_reason());
                        xRelayHide.setVisibility(View.GONE);
                        xTvReson.setVisibility(View.VISIBLE);
                }
                bankName = response.body().getData().getBankDetails().getAccountName();
                bankAccount = response.body().getData().getBankDetails().getAccountNumber();
                bankIfsc = response.body().getData().getBankDetails().getIfscCode();
                bankid = String.valueOf(response.body().getData().getBankDetails().getId());
                Log.e("testing","testing " +bankid);
                xTvBankVerify.setTextColor(Color.parseColor("#F34235"));
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        matchesList();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("AVAILABLE_WITHDRAW", sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.AVAILABLE_WITHDRAW).toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
