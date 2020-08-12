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

import com.glory.apk.Model.UpdatePassword.UpdatePasswordExample;
import com.glory.apk.R;
import com.glory.apk.Retrofit.Api;
import com.glory.apk.Retrofit.ApiClient;
import com.glory.apk.Utilites.sharedPrefs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityUpdatePassword extends AppCompatActivity {
    Button logsubmit;
    EditText editoldpassword, editnewpassword, editconfirmpassword;
    String stroldpassword, strnewpassword, strconfirmpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        mInitWidgets();
    }

    private void mInitWidgets() {

        logsubmit = (Button) findViewById(R.id.logsubmit);
        editoldpassword = (EditText) findViewById(R.id.editoldpassword);
        editnewpassword = (EditText) findViewById(R.id.editnewpassword);
        editconfirmpassword = (EditText) findViewById(R.id.editconfirmpassword);
        logsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }

    private void submit() {

//        stroldpassword = editoldpassword.getText().toString();
        strnewpassword = editnewpassword.getText().toString();
        strconfirmpassword = editconfirmpassword.getText().toString();

        if (strnewpassword == null || strnewpassword.trim().length() == 0 || strnewpassword.trim().equals("null")) {
            Toast.makeText(ActivityUpdatePassword.this, "Please Enter New Password", Toast.LENGTH_SHORT).show();
        } else if (strconfirmpassword == null || strconfirmpassword.trim().length() == 0 || strconfirmpassword.trim().equals("null")) {

            Toast.makeText(ActivityUpdatePassword.this, "Please Enter Confirm Password", Toast.LENGTH_SHORT).show();

        } else {
            if (strnewpassword.trim().equals(strconfirmpassword.trim())) {

//                Log.e("testing","success");
//                RetrofitChangePassword(stroldpassword, strnewpassword);
                ChangePassword();
                // new LoadChangePassword().execute();
            } else {

                Toast.makeText(ActivityUpdatePassword.this, "Password Mismatch", Toast.LENGTH_SHORT).show();

            }
        }

    }

    private void ChangePassword() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");
        final ProgressDialog pDialog;

        pDialog = new ProgressDialog(ActivityUpdatePassword.this);
        pDialog.setMessage("Please Wait ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        Api api = ApiClient.getClient().create(Api.class);
        Call<UpdatePasswordExample> login = api.PasswordUpadte(getIntent().getStringExtra("userId"), strnewpassword);
        Log.e("testing", "response array = " + getIntent().getStringExtra("userId")+strnewpassword);


        login.enqueue(new Callback<UpdatePasswordExample>() {
            @Override
            public void onResponse(Call<UpdatePasswordExample> call, Response<UpdatePasswordExample> response) {
                pDialog.dismiss();
                Log.e("testing", "status = " + response.body().getStatus());
                Log.e("testing", "response = " + response.body().getUpdatePasswordResponse().getType());
                //  Log.e("testing","response = "+response.body().getData().getPageContent());

                Log.e("testing", "response = " + response.body());

                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    if (response.body().getUpdatePasswordResponse() == null) {

                    } else if (response.body().getUpdatePasswordResponse().getType().equals("update_success")) {
                        pDialog.dismiss();
                        Intent intent = new Intent(ActivityUpdatePassword.this, Activity_Login.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        sharedPrefs.savepref(getApplicationContext(), sharedPrefs.Pref_userId, getIntent().getStringExtra("userId"));
                        startActivity(intent);
                        finish();
                        Toast.makeText(getApplicationContext(), response.body().getUpdatePasswordResponse().getMessage(), Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getUpdatePasswordResponse().getType(), Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();

                    }

                } else {
                    Log.e("testing", "error");
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.body().getUpdatePasswordResponse().getType(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpdatePasswordExample> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                pDialog.dismiss();

            }
        });
    }
}
