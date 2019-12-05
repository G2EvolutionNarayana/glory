package com.android.glory.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.glory.MainActivity;
import com.android.glory.R;
import com.android.glory.Utilites.EndUrls;
import com.android.glory.Utilites.JSONParserNew;
import com.android.glory.Utilites.sharedPrefs;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Activity_Login extends AppCompatActivity {

    Button logsubmit;

    TextView signup,forgetpasswd,logmob,logpassword;

    JSONParserNew jsonParser = new JSONParserNew();

    String strmobileno, strpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logmob = findViewById(R.id.logmob);
        logpassword = findViewById(R.id.logpassword);
        signup = (TextView) findViewById(R.id.signup);
        forgetpasswd = (TextView) findViewById(R.id.forgetpasswd);

        logsubmit = (Button) findViewById(R.id.logsubmit);

        logsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                strmobileno = logmob.getText().toString();
                strpassword = logpassword.getText().toString();

                if(strmobileno==null || strmobileno.equals("null") || strmobileno.length()!=10){
                    logmob.setError("Please Enter Valid Mobile number");
                }else if(strpassword==null || strpassword.equals("null") || strpassword.length()==0){
                    logpassword.setError("Please Enter Valid Password");
                }else{
                    new LoadLogin().execute();

                }






            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_Login.this, Activity_Signup.class);
                startActivity(intent);
            }
        });

        forgetpasswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_Login.this, ActivityForgetPassword.class);
                startActivity(intent);
            }
        });

    }

    public class LoadLogin extends AsyncTask<String, String, String>
            //implements RemoveClickListner
    {


        String status;
        String response;
        String strresponse;
        String validuser_id;
        String strcode, strtype, strmessage;

        private ProgressDialog pDialog;
        Integer intcartcount = 0;
        String strregisteredtoken;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Activity_Login.this);
            pDialog.setMessage("Please Wait ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();


        }

        public String doInBackground(String... args) {

            //  product_details_lists = new ArrayList<Product_list>();

            // Retrieve JSON Objects from the given URL address
            List<NameValuePair> userpramas = new ArrayList<NameValuePair>();

            String paramsheader = "Bearer "+"sddf";


            //   userpramas.add(new BasicNameValuePair(EndUrls.Signup_registering_by, "user"));
            userpramas.add(new BasicNameValuePair(EndUrls.Login_mobileno, strmobileno));
            userpramas.add(new BasicNameValuePair(EndUrls.Login_Password, strpassword));

            Log.e("testing", "userpramas = " + userpramas);

            String strurl = EndUrls.Login_URL;
            Log.e("testing", "strurl = " + strurl);
            JSONObject json = jsonParser.makeHttpRequest(strurl, "POST", userpramas);

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
                    strregisteredtoken = jsonobject.getString("token");
                    if (status.equals("success")) {

                        status = json.getString("status");
                        strresponse = json.getString("response");



                        String arrayresponse = json.getString("data");

                        JSONObject  jsonobjectdata = new JSONObject(arrayresponse);
                        Log.e("testing", "adapter value=" + arrayresponse);

                        validuser_id = jsonobjectdata.getString("id");

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


                Intent intent =new Intent(Activity_Login.this, MainActivity.class);
                SharedPreferences prefuserdata =  Activity_Login.this.getSharedPreferences(sharedPrefs.Pref, 0);
                SharedPreferences.Editor prefeditor =prefuserdata.edit();
                prefeditor.putString(sharedPrefs.Pref_token,""+strregisteredtoken);
                prefeditor.putString(sharedPrefs.Pref_userId,""+validuser_id);

                prefeditor.commit();
                startActivity(intent);
                finish();



            }
            else {
                Toast.makeText(Activity_Login.this, strmessage, Toast.LENGTH_SHORT).show();


            }



        }



    }




}
