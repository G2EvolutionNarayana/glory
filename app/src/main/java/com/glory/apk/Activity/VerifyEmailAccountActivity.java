package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.glory.apk.Model.VerifyEmailModel.VerifyEmailExample;
import com.glory.apk.R;
import com.glory.apk.Retrofit.Api;
import com.glory.apk.Retrofit.ApiClient;
import com.glory.apk.Utilites.sharedPrefs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyEmailAccountActivity extends AppCompatActivity {
    EditText editemail;
    Button butsubmit;
    String viewuseremail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_email_account);
        mInitWidgets();

    }

    private void mInitWidgets() {
        editemail=(EditText)findViewById(R.id.editemail);
        butsubmit=(Button)findViewById(R.id.butsubmit);
        butsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.+[a-z]+||[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.+[a-z]+\\.+[a-z]+";

                if (editemail.getText().toString().length()==0|| editemail.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter Email Address",Toast.LENGTH_LONG).show();

                }else if (!editemail.getText().toString().matches(emailPattern) ){
                    Toast.makeText(getApplicationContext(),"Please Enter Correct Email Address",Toast.LENGTH_LONG).show();

                }else {
                    matchesList();
                }
            }
        });
    }
    private void matchesList() {
        final ProgressDialog pDialog;

        Log.e("testing", "strregisteredtoken = " + "matchesList");
        pDialog = new ProgressDialog(VerifyEmailAccountActivity.this);
        pDialog.setMessage("Please Wait ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        Api api = ApiClient.getClient().create(Api.class);
        viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);


        Call<VerifyEmailExample> login = api.VerifyEmailAcount(editemail.getText().toString(), viewuseremail);

        login.enqueue(new Callback<VerifyEmailExample>() {
            @Override
            public void onResponse(Call<VerifyEmailExample> call, Response<VerifyEmailExample> response) {
                pDialog.dismiss();
                Log.e("testing", "status = " + response.body().getStatus());
                Log.e("testing", "response = " + response.body().getResponse().getMessage());
                //  Log.e("testing","response = "+response.body().getData().getPageContent());
                Log.e("testing", "response = " + response.body());
                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                 if (response.body().getResponse().getType().equals("verify_success")) {
//                        Log.e("testing", "dateumList.size = " + dateumList.size());
//                        dateumList=response.body().getData();
                     Toast.makeText(getApplicationContext(), "Link Sent to email to verify account", Toast.LENGTH_SHORT).show();
                     finish();
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
            public void onFailure(Call<VerifyEmailExample> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                pDialog.dismiss();

            }
        });
    }

}
