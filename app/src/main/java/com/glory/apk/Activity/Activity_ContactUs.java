package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.glory.apk.MainActivity;
import com.glory.apk.R;
import com.glory.apk.Retrofit.Api;
import com.glory.apk.Retrofit.ApiClient;
import com.glory.apk.Retrofit.Contactus.contactusjson;
import com.glory.apk.Utilites.sharedPrefs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_ContactUs extends AppCompatActivity {

    EditText editname, editemailid, editnumber, editmessage;
    String streditname, streditemailid, streditnumber, streditmessage;
    Button submit;
    String strregisteredtoken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__contact_us);

        SharedPreferences prefuserdata3 = getSharedPreferences(sharedPrefs.Pref, 0);
        strregisteredtoken = prefuserdata3.getString(sharedPrefs.Pref_token, "");

        editname = (EditText) findViewById(R.id.editname);
        editemailid = (EditText) findViewById(R.id.editemailid);
        editnumber = (EditText) findViewById(R.id.editnumber);
        editmessage = (EditText) findViewById(R.id.editmessage);

        submit = (Button) findViewById(R.id.submit);

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    streditname = editname.getText().toString();
                    streditemailid = editemailid.getText().toString();
                    streditnumber = editnumber.getText().toString();
                    streditmessage = editmessage.getText().toString();

                    if (streditname == null || streditname.trim().length() == 0){
                        Toast.makeText(Activity_ContactUs.this, "Please Enter Name", Toast.LENGTH_LONG).show();
                    }else if (streditemailid == null || streditemailid.trim().length() == 0){
                        Toast.makeText(Activity_ContactUs.this, "Please Enter Email", Toast.LENGTH_LONG).show();
                    }else if (streditnumber == null || streditnumber.trim().length() == 0){
                        Toast.makeText(Activity_ContactUs.this, "Please Enter Telephone Number", Toast.LENGTH_LONG).show();
                    }else if (streditmessage == null || streditmessage.trim().length() == 0){
                        Toast.makeText(Activity_ContactUs.this, "Please Enter Message", Toast.LENGTH_LONG).show();
                    }else{
                        RetrofitUploadContact(streditname, streditemailid, streditnumber, streditmessage);
                    }

                }
            });

    }


    private void RetrofitUploadContact(final String name, String email, String number, String message) {
        ProgressDialog pProgressDialog;
        pProgressDialog = new ProgressDialog(Activity_ContactUs.this);
        pProgressDialog.setMessage("Please Wait ...");
        pProgressDialog.setIndeterminate(false);
        pProgressDialog.setCancelable(false);
        pProgressDialog.show();

        //call retrofit
        //making api call
        Api api = ApiClient.getClient().create(Api.class);

        Log.e("testing","Bearer = "+strregisteredtoken);
        Log.e("testing","message = "+message);
        Log.e("testing","number = "+number);
        Log.e("testing","email = "+email);
        Log.e("testing","name = "+name);
        String viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);

        Call<contactusjson> login = api.contactusjson(name,email, number, message,viewuseremail);


        login.enqueue(new Callback<contactusjson>() {
            @Override
            public void onResponse(Call<contactusjson> call, Response<contactusjson> response) {
                pProgressDialog.dismiss();
                Log.e("testing","status = "+response.body().getStatus());
                Log.e("testing","response = "+response.body().getResponse().getType());

                if (response.body().getStatus() == null || response.body().getStatus().length() == 0){

                }else if (response.body().getStatus().equals("success")) {
                    if (response.body().getResponse() == null ){

                    }else if (response.body().getResponse().getCode().equals("200")){

                        Toast.makeText(Activity_ContactUs.this, "Uploaded Successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Activity_ContactUs.this, MainActivity.class);
                        startActivity(intent);
                        finish();


                    }else{
                        Toast.makeText(Activity_ContactUs.this, "Something went wrong", Toast.LENGTH_LONG).show();
                    }

                } else  {
                    Log.e("testing","error");
                    Toast.makeText(Activity_ContactUs.this, "Something went wrong", Toast.LENGTH_LONG).show();
                }



            }

            @Override
            public void onFailure(Call<contactusjson> call, Throwable t) {
                Toast.makeText(Activity_ContactUs.this,t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                pProgressDialog.dismiss();

            }
        });





    }

}
