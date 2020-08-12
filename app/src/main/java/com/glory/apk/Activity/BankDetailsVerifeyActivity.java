package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.glory.apk.Model.VerifyBankDetailsActivity.VerifyBankDetailsExample;
import com.glory.apk.R;
import com.glory.apk.Retrofit.Api;
import com.glory.apk.Retrofit.ApiClient;
import com.glory.apk.Utilites.StaticUtils;
import com.glory.apk.Utilites.sharedPrefs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BankDetailsVerifeyActivity extends AppCompatActivity {
    EditText accountname,xEtIfscCode,xEtAccountNumber;
    Button butVerify;
    private ProgressDialog pDialog;
    String viewuseremail,bankid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_details_verifey);
        mInitWidgets();
    }
    private void mInitWidgets() {
        accountname=(EditText)findViewById(R.id.accountname);
        xEtIfscCode=(EditText)findViewById(R.id.xEtIfscCode);
        xEtAccountNumber=(EditText)findViewById(R.id.xEtAccountNumber);
        accountname.setText(getIntent().getStringExtra(StaticUtils.BRANCH_NAME));
        xEtIfscCode.setText(getIntent().getStringExtra(StaticUtils.IFSC_CODE));
        xEtAccountNumber.setText(getIntent().getStringExtra(StaticUtils.ACCOUNT_NUMBER));
        bankid=getIntent().getStringExtra(StaticUtils.BANK_ID);
        Log.e("testing", bankid);

        butVerify=(Button)findViewById(R.id.butVerify);
        butVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (accountname.getText().toString().isEmpty()||accountname.getText().toString().length()==0){
                    Toast.makeText(getApplicationContext(), "Please Enter Account Name", Toast.LENGTH_SHORT).show();

                }else if (xEtAccountNumber.getText().toString().isEmpty()||xEtAccountNumber.getText().toString().length()==0){
                    Toast.makeText(getApplicationContext(), "Please Enter Account Number", Toast.LENGTH_SHORT).show();

                }else if (xEtIfscCode.getText().toString().isEmpty()||xEtIfscCode.getText().toString().length()==0){
                    Toast.makeText(getApplicationContext(), "Please Enter Ifsc Code", Toast.LENGTH_SHORT).show();

                }else {
                    matchesList();
                }
            }
        });
    }
    private void matchesList() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");
        final ProgressDialog pDialog;

        pDialog = new ProgressDialog(BankDetailsVerifeyActivity.this);
        pDialog.setMessage("Please Wait ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        Api api = ApiClient.getClient().create(Api.class);
        viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);


        Call<VerifyBankDetailsExample> login = api.VerifyBankAccount(accountname.getText().toString(), xEtAccountNumber.getText().toString(),xEtIfscCode.getText().toString(),viewuseremail,bankid);

        login.enqueue(new Callback<VerifyBankDetailsExample>() {
            @Override
            public void onResponse(Call<VerifyBankDetailsExample> call, Response<VerifyBankDetailsExample> response) {
                pDialog.dismiss();
                Log.e("testing", "status = " + response.body().getStatus());
                Log.e("testing", "response = " + response.body().getVerifyBankDetailsResponse().getMessage());
                //  Log.e("testing","response = "+response.body().getData().getPageContent());
                Log.e("testing", "response = " + response.body());
                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    if (response.body().getVerifyBankDetailsResponse().getType().equals("save_success")) {
                        finish();
                        Toast.makeText(getApplicationContext(), response.body().getVerifyBankDetailsResponse().getMessage(), Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();

                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getVerifyBankDetailsResponse().getMessage(), Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();
                    }

                } else {
                    Log.e("testing", "error");
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.body().getVerifyBankDetailsResponse().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<VerifyBankDetailsExample> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                pDialog.dismiss();

            }
        });
    }
}
