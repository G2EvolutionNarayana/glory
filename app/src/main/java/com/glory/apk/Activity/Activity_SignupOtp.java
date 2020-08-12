package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.glory.apk.MainActivity;
import com.glory.apk.R;
import com.glory.apk.Utilites.EndUrls;
import com.glory.apk.Utilites.JSONParserNew;
import com.glory.apk.Utilites.sharedPrefs;
import com.chaos.view.PinView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Activity_SignupOtp extends AppCompatActivity {

    Button butsubmit;

    PinView firstPinView;
    String strotp;
    String strregisteredtoken, temp_user;
    String userId;
    String str_user_name;
    JSONParserNew jsonParser = new JSONParserNew();
    TextView textresend;
    String ScreenName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);// hide statusbar of Android
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_otp);

//        SharedPreferences prefuserdata3 = getSharedPreferences("registerOtp", 0);
//        strregisteredtoken = prefuserdata3.getString("strregisteredtoken", "");
        strregisteredtoken = getIntent().getStringExtra("strregisteredtoken");
        ScreenName = getIntent().getStringExtra("ScreenName");
        Log.e("testing", "strregisteredtoken =" + strregisteredtoken);
        temp_user = getIntent().getStringExtra("temp_user");
        str_user_name = getIntent().getStringExtra("str_user_name");
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

        textresend = (TextView) findViewById(R.id.textresend);

        textresend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoadRegisterOtpResend().execute();
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
                    if (ScreenName.equals("ForgotPassword")) {
                        new ForgotPassword().execute();
                    } else {
                        new LoadRegisterOtp().execute();
                    }
                }
            }
        });
    }

    public class LoadRegisterOtp extends AsyncTask<String, String, String>
            //implements RemoveClickListner
    {
        String status;
        String response;
        String strresponse;
        String strcode, strtype, strmessage;

        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Activity_SignupOtp.this);
            pDialog.setMessage("Please Wait ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        public String doInBackground(String... args) {

            //  product_details_lists = new ArrayList<Product_list>();

            // Retrieve JSON Objects from the given URL address
            List<NameValuePair> userpramas = new ArrayList<NameValuePair>();

            String paramsheader = "Bearer " + strregisteredtoken;


            userpramas.add(new BasicNameValuePair("register_exp", strotp));
            userpramas.add(new BasicNameValuePair("temp_user_id", temp_user));


            Log.e("testing", "userpramas = " + userpramas);

            String strurl = "https://glory5.in/glory5/api/user/authentication/signup/verify";
            Log.e("testing", "strurl = " + strurl);
            JSONObject json = jsonParser.makeHttpRequest(strurl, "POST", userpramas);


            Log.e("testing", "json result = " + json);

            if (json == null) {
                Log.e("testing", "json result = " + json);

            } else {
                Log.e("testing", "jon2222222222222");
                try {
                    if (json.getString("status").equals("success")) {
                        status = json.getString("status");
                        String response = json.getString("response");
                        JSONObject responceObject = new JSONObject(response);

                        strmessage = responceObject.getString("message");
                        strtype = responceObject.getString("type");
                        if (strtype.equals("verify_success")){
                            String data = json.getString("data");
                            JSONObject dataObject = new JSONObject(data);
                            userId = dataObject.getString("user_id");
                            Log.e("testing", "usersssId = " + userId);

                        }



                    } else {

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return response;
        }

        @Override
        protected void onPostExecute(String responce) {
            super.onPostExecute(responce);


            pDialog.dismiss();

            if (status == null || status.trim().length() == 0 || status.equals("null")) {

            } else if (status.equals("success")) {

                if (ScreenName.equals("ForgotPassword")) {
                    Intent intent = new Intent(Activity_SignupOtp.this, MainActivity.class);
                    sharedPrefs.savepref(getApplicationContext(), sharedPrefs.Pref_userId, userId);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                    Toast.makeText(Activity_SignupOtp.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Activity_SignupOtp.this, MainActivity.class);
                    Log.e("testing", "userId = " + userId);

                    sharedPrefs.savepref(getApplicationContext(), sharedPrefs.Pref_userId, userId);
                    sharedPrefs.savepref(getApplicationContext(), sharedPrefs.Social_id, "0");

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                    Toast.makeText(Activity_SignupOtp.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                }
            } else {
                if (strtype.equals("verify_failure")) {
                    Toast.makeText(Activity_SignupOtp.this, "Please Enter Correct Otp", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(Activity_SignupOtp.this, "Otp Expired Please Send again ", Toast.LENGTH_SHORT).show();

                }


            }


        }


    }


    public class LoadRegisterOtpResend extends AsyncTask<String, String, String>
            //implements RemoveClickListner
    {
        String status;
        String response;
        String strresponse;
        String strcode, strtype, strmessage;

        private ProgressDialog pDialog;
        Integer intcartcount = 0;
        String validuser_id;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Activity_SignupOtp.this);
            pDialog.setMessage("Please Wait ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();


        }

        public String doInBackground(String... args) {

            //  product_details_lists = new ArrayList<Product_list>();

            // Retrieve JSON Objects from the given URL address
            List<NameValuePair> userpramas = new ArrayList<NameValuePair>();

            String paramsheader = "Bearer " + strregisteredtoken;


            userpramas.add(new BasicNameValuePair(EndUrls.SignupOTP_temp_resend_user, temp_user));
            userpramas.add(new BasicNameValuePair(EndUrls.SignupOTP_URL_name, str_user_name));

            Log.e("testing", "userpramas = " + userpramas);

            String strurl = EndUrls.SignupOTPResend_URL;
            Log.e("testing", "strurl = " + strurl);
            JSONObject json = jsonParser.makeHttpRequest(strurl, "POST", userpramas);

            Log.e("testing", "json result = " + json);

            if (json == null) {

            } else {
                Log.e("testing", "jon2222222222222");
                try {
                    status = json.getString("status");
                    strresponse = json.getString("response");
                    JSONObject jsonobject = new JSONObject(strresponse);
                    strcode = jsonobject.getString("code");
                    strtype = jsonobject.getString("type");
                    strmessage = jsonobject.getString("message");
                    if (status.equals("success")) {
                        status = json.getString("status");
                        strresponse = json.getString("response");
                    } else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


            return response;


        }

        @Override
        protected void onPostExecute(String responce) {
            super.onPostExecute(responce);


            pDialog.dismiss();

            if (status == null || status.trim().length() == 0 || status.equals("null")) {

            } else if (status.equals("success")) {

            } else {
                Toast.makeText(Activity_SignupOtp.this, strmessage, Toast.LENGTH_SHORT).show();
            }
        }


    }

    public class ForgotPassword extends AsyncTask<String, String, String>
            //implements RemoveClickListner
    {
        String status;
        String response;
        String strresponse;
        String strcode, strtype, strmessage;

        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Activity_SignupOtp.this);
            pDialog.setMessage("Please Wait ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        public String doInBackground(String... args) {

            List<NameValuePair> userpramas = new ArrayList<NameValuePair>();
            String paramsheader = "Bearer " + strregisteredtoken;
            userpramas.add(new BasicNameValuePair(EndUrls.Forgot_password_Forgot, strregisteredtoken));
            userpramas.add(new BasicNameValuePair(EndUrls.Forgot_password_user_id, temp_user));
            Log.e("testing", "userpramas = " + userpramas);
            String strurl = EndUrls.Forgot_password_url;

            Log.e("testing", "strurl = " + strurl);
            JSONObject json = jsonParser.makeHttpRequest(strurl, "POST", userpramas);


            Log.e("testing", "json result = " + json);

            if (json == null) {
                Log.e("testing", "json result = " + json);

            } else {
                Log.e("testing", "jon2222222222222");
                try {

                    status = json.getString("status");
                    strresponse = json.getString("response");
                    JSONObject jsonobject = new JSONObject(strresponse);
                    strcode = jsonobject.getString("code");
                    strtype = jsonobject.getString("type");
                    strmessage = jsonobject.getString("message");
                    if (status.equals("success")) {
                        status = json.getString("status");
                        strresponse = json.getString("response");
                        String data = json.getString("data");
                        JSONObject dataObject = new JSONObject(data);
                        userId = dataObject.getString("user_id");


                    } else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            return response;
        }


        @Override
        protected void onPostExecute(String responce) {
            super.onPostExecute(responce);


            pDialog.dismiss();

            if (status == null || status.trim().length() == 0 || status.equals("null")) {

            } else if (status.equals("success")) {

                if (ScreenName.equals("ForgotPassword")) {
                    Intent intent = new Intent(Activity_SignupOtp.this, ActivityUpdatePassword.class);
                    //      sharedPrefs.savepref(getApplicationContext(), sharedPrefs.Pref_userId, userId);
                    intent.putExtra("userId", userId);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                    Toast.makeText(Activity_SignupOtp.this, "Reset Password screen is pending", Toast.LENGTH_SHORT).show();
                }


            } else {
                if (strtype.equals("verify_failure")) {
                    Toast.makeText(Activity_SignupOtp.this, "Please Enter Correct Otp", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(Activity_SignupOtp.this, "Otp Expired Please Send again ", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }
}
