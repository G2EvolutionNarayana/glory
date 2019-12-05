package com.android.glory.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.android.glory.MainActivity;
import com.android.glory.R;
import com.android.glory.Utilites.EndUrls;
import com.android.glory.Utilites.JSONParser;
import com.android.glory.Utilites.sharedPrefs;
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
    String strregisteredtoken;

    JSONParser jsonParser = new JSONParser();

    TextView textresend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);// hide statusbar of Android
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_otp);

        SharedPreferences prefuserdata3 = getSharedPreferences("registerOtp", 0);
        strregisteredtoken = prefuserdata3.getString("strregisteredtoken", "");


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


                strotp=firstPinView.getText().toString().trim();
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
                if(strotp==null || strotp.trim().equals("null") || strotp.trim().length()==0)
                {
                    firstPinView.setError("Please Enter Valid OTP");
                }else{
                    Log.e("testing","firstPinView = "+firstPinView);

                    new LoadRegisterOtp().execute();

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

            String paramsheader = "Bearer "+strregisteredtoken;


            userpramas.add(new BasicNameValuePair(EndUrls.SignupOTP_URL_OTP, strotp));

            Log.e("testing", "paramsheader = " + paramsheader);
            Log.e("testing", "userpramas = " + userpramas);

            String strurl = EndUrls.SignupOTP_URL;
            Log.e("testing", "strurl = " + strurl);
            JSONObject json = jsonParser.makeHttpRequest(strurl, "POST", userpramas, paramsheader);

            Log.e("testing", "json result = " + json);

            if (json == null) {

            } else {
                Log.e("testing", "jon2222222222222");
                try {


                    status = json.getString("status");
                    strresponse = json.getString("response");
                    JSONObject  jsonobject = new JSONObject(strresponse);
                    strcode = jsonobject.getString("code");
                    strtype = jsonobject.getString("type");
                    strmessage = jsonobject.getString("message");
                    if (status.equals("success")) {

                        status = json.getString("status");
                        strresponse = json.getString("response");
                        String strcontent = json.getString("content");

                        JSONObject  jsonobjectcontent = new JSONObject(strcontent);
                        validuser_id = jsonobjectcontent.getString("user_id");
                      /*  String arrayresponse = json.getString("data");

                        JSONObject  jsonobjectdata = new JSONObject(arrayresponse);
                        Log.e("testing", "adapter value=" + arrayresponse);

                        validuser_id = jsonobjectdata.getString("user_id");
*/

                    }else{

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

            if (status == null || status.trim().length() == 0 || status.equals("null")){

            }else if (status.equals("success")) {


                Intent intent =new Intent(Activity_SignupOtp.this, MainActivity.class);
                SharedPreferences prefuserdata =  Activity_SignupOtp.this.getSharedPreferences(sharedPrefs.Pref, 0);
                SharedPreferences.Editor prefeditor =prefuserdata.edit();
                prefeditor.putString(sharedPrefs.Pref_userId,""+validuser_id);

                prefeditor.commit();

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();



            }
            else {
                Toast.makeText(Activity_SignupOtp.this, strmessage, Toast.LENGTH_SHORT).show();


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

            String paramsheader = "Bearer "+strregisteredtoken;


          //  userpramas.add(new BasicNameValuePair(EndUrls.SignupOTP_URL_OTP, strotp));

            Log.e("testing", "userpramas = " + userpramas);

            String strurl = EndUrls.SignupOTPResend_URL;
            Log.e("testing", "strurl = " + strurl);
            JSONObject json = jsonParser.makeHttpRequest(strurl, "POST", userpramas, paramsheader);

            Log.e("testing", "json result = " + json);

            if (json == null) {

            } else {
                Log.e("testing", "jon2222222222222");
                try {


                    status = json.getString("status");
                    strresponse = json.getString("response");
                    JSONObject  jsonobject = new JSONObject(strresponse);
                    strcode = jsonobject.getString("code");
                    strtype = jsonobject.getString("type");
                    strmessage = jsonobject.getString("message");
                    if (status.equals("success")) {

                        status = json.getString("status");
                        strresponse = json.getString("response");
                       /* String strcontent = json.getString("content");

                        JSONObject  jsonobjectcontent = new JSONObject(strcontent);
                        validuser_id = jsonobjectcontent.getString("user_id");*/
                      /*  String arrayresponse = json.getString("data");

                        JSONObject  jsonobjectdata = new JSONObject(arrayresponse);
                        Log.e("testing", "adapter value=" + arrayresponse);

                        validuser_id = jsonobjectdata.getString("user_id");
*/

                    }else{

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

            if (status == null || status.trim().length() == 0 || status.equals("null")){

            }else if (status.equals("success")) {
                Toast.makeText(Activity_SignupOtp.this, strmessage, Toast.LENGTH_SHORT).show();

              /*  Intent intent =new Intent(Activity_SignupOtp.this, MainActivity.class);
                sharedPrefs prefuserdata =  Activity_SignupOtp.this.getSharedPreferences("registerUser", 0);
                sharedPrefs.Editor prefeditor =prefuserdata.edit();
                prefeditor.putString("validuser_id",""+validuser_id);

                prefeditor.commit();
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
*/


            }
            else {
                Toast.makeText(Activity_SignupOtp.this, strmessage, Toast.LENGTH_SHORT).show();


            }



        }



    }

}
