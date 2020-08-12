package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.glory.apk.Model.VerifyPhoneAccountModel.VeifyPhoneAccountExample;
import com.glory.apk.R;
import com.glory.apk.Retrofit.Api;
import com.glory.apk.Retrofit.ApiClient;
import com.glory.apk.Utilites.sharedPrefs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyPhoneAccountActivity extends AppCompatActivity {
    Button butsubmit;
    EditText editPhone;
    String viewuseremail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone_account);
        mInitWidgets();
    }

    private void mInitWidgets() {
        butsubmit=findViewById(R.id.butsubmit);
        editPhone=findViewById(R.id.editPhone);
        butsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editPhone.getText().toString().isEmpty()||editPhone.getText().toString().length()==0){
                    Toast.makeText(getApplicationContext(),"Please Enter Phone Number",Toast.LENGTH_LONG).show();

                }else if (editPhone.getText().toString().length()!=10){
                    Toast.makeText(getApplicationContext(),"Please Enter Correct Phone Number",Toast.LENGTH_LONG).show();

                }else {

                    matchesList();
                }


            }
        });

    }
    private void matchesList() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");
        final ProgressDialog pDialog;

        pDialog = new ProgressDialog(VerifyPhoneAccountActivity.this);
        pDialog.setMessage("Please Wait ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        Api api = ApiClient.getClient().create(Api.class);
        viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);

        Log.e("testing", "status = " + editPhone.getText().toString());

        Call<VeifyPhoneAccountExample> login = api.VerifyPhoneAcount(editPhone.getText().toString(), viewuseremail);

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

                        Intent intent =new Intent(VerifyPhoneAccountActivity.this, VerifyPhoneActivity.class);
//                        intent.putExtra("strregisteredtoken",response.body().getData().getOtp());
                        intent.putExtra("ScreenName","VerifyPhoneAccountActivity");
                        Log.e("testing", "response = " + response.body().getData().getOtp());
                        intent.putExtra("strregisteredtoken",editPhone.getText().toString());

                        startActivity(intent);
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
