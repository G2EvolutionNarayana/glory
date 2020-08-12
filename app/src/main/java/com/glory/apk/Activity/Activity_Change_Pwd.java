package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.glory.apk.Model.ChangePassword.ChangeExample;
import com.glory.apk.R;
import com.glory.apk.Retrofit.Api;
import com.glory.apk.Retrofit.ApiClient;
import com.glory.apk.Utilites.JSONParser;
import com.glory.apk.Utilites.sharedPrefs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Change_Pwd extends AppCompatActivity {

    JSONParser jsonParser = new JSONParser();

    String strregisteredtoken;

    EditText editoldpassword, editnewpassword, editconfirmpassword;
    String stroldpassword, strnewpassword, strconfirmpassword;
    Button logsubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__change__pwd);

        SharedPreferences prefuserdata3 = getSharedPreferences(sharedPrefs.Pref, 0);
        strregisteredtoken = prefuserdata3.getString(sharedPrefs.Pref_token, "");


        editoldpassword = (EditText) findViewById(R.id.editoldpassword);
        editnewpassword = (EditText) findViewById(R.id.editnewpassword);
        editconfirmpassword = (EditText) findViewById(R.id.editconfirmpassword);

        logsubmit = (Button) findViewById(R.id.logsubmit);
        logsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });


    }

    private void submit() {

        stroldpassword = editoldpassword.getText().toString();
        strnewpassword = editnewpassword.getText().toString();
        strconfirmpassword = editconfirmpassword.getText().toString();

        if (stroldpassword == null || stroldpassword.trim().length() == 0 || stroldpassword.trim().equals("null")) {
            Toast.makeText(Activity_Change_Pwd.this, "Please Enter old Password", Toast.LENGTH_SHORT).show();
        } else if (strnewpassword == null || strnewpassword.trim().length() == 0 || strnewpassword.trim().equals("null")) {
            Toast.makeText(Activity_Change_Pwd.this, "Please Enter New Password", Toast.LENGTH_SHORT).show();
        } else if (strconfirmpassword == null || strconfirmpassword.trim().length() == 0 || strconfirmpassword.trim().equals("null")) {

            Toast.makeText(Activity_Change_Pwd.this, "Please Enter Confirm Password", Toast.LENGTH_SHORT).show();

        } else {
            if (strnewpassword.trim().equals(strconfirmpassword.trim())) {
                ChangePassword();
            } else {

                Toast.makeText(Activity_Change_Pwd.this, "Password Mismatch", Toast.LENGTH_SHORT).show();

            }
        }

    }


    private void ChangePassword() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");
        ProgressDialog pDialog;
        pDialog = new ProgressDialog(Activity_Change_Pwd.this);
        pDialog.setMessage("Please Wait ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        Api api = ApiClient.getClient().create(Api.class);
        Call<ChangeExample> login = api.ChangePassword(stroldpassword, sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId).toString(), strnewpassword);

        login.enqueue(new Callback<ChangeExample>() {
            @Override
            public void onResponse(Call<ChangeExample> call, Response<ChangeExample> response) {
                pDialog.dismiss();
                Log.e("testing", "status = " + response.body().getStatus());
                Log.e("testing", "response = " + response.body().getChangeResponse().getType());
                Log.e("testing", "response = " + response.body());

                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    if (response.body().getChangeResponse() == null) {

                    } else if (response.body().getChangeResponse().getType().equals("update_success")) {

                        Toast.makeText(getApplicationContext(), response.body().getChangeResponse().getMessage(), Toast.LENGTH_SHORT).show();


                        pDialog.dismiss();

                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getChangeResponse().getMessage(), Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();

                    }

                } else {
                    Log.e("testing", "error");
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.body().getChangeResponse().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ChangeExample> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                pDialog.dismiss();

            }
        });
    }
}
