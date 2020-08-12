package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.glory.apk.R;
import com.glory.apk.Utilites.ConnectionDetector;
import com.glory.apk.Utilites.EndUrls;
import com.glory.apk.Utilites.JSONParserNew;
import com.glory.apk.app.Config;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Activity_Signup extends AppCompatActivity {

    Button submit;
    TextView login;
    String temp_user, regId;
    EditText editusername, editmailid, editmobileno, editpassword, editrefferalcode, editFullname;

    JSONParserNew jsonParser = new JSONParserNew();
    String str_full_name, str_user_name, str_phone_number, str_email, str_password, strreferralcode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        login = (TextView) findViewById(R.id.login);
        submit = (Button) findViewById(R.id.submit);
        editFullname = (EditText) findViewById(R.id.editFullname);

        editusername = (EditText) findViewById(R.id.editusername);
        editmailid = (EditText) findViewById(R.id.editmailid);
        editmobileno = (EditText) findViewById(R.id.editmobileno);
        editpassword = (EditText) findViewById(R.id.editpassword);
        editrefferalcode = (EditText) findViewById(R.id.editrefferalcode);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_Signup.this, Activity_Login.class);
                startActivity(intent);
                finish();
            }
        });
    }


    public void validation() {

        String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.+[a-z]+||[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.+[a-z]+\\.+[a-z]+";
        str_full_name = editFullname.getText().toString().trim();
        str_user_name = editusername.getText().toString().trim();
        str_phone_number = editmobileno.getText().toString().trim();
        str_email = editmailid.getText().toString().trim();
        str_password = editpassword.getText().toString().trim();
        strreferralcode = editrefferalcode.getText().toString().trim();

        if (str_full_name == null || str_full_name.equals("null") || str_full_name.length() == 0) {
            editusername.setError("Please Enter Valid Full name");
        } else if (str_user_name == null || str_user_name.equals("null") || str_user_name.length() == 0) {
            editusername.setError("Please Enter Valid Username");
        } else if (str_phone_number == null || str_phone_number.equals("null") || str_phone_number.length() != 10) {
            editmobileno.setError("Please Enter Valid Mobile number");
        } else if (str_email.matches(emailPattern) && str_email.length() > 0) {

            if (str_password == null || str_password.equals("null") || str_password.length() == 0) {
                editpassword.setError("Please Enter Valid Password");
            } else if (strreferralcode == null || strreferralcode.trim().equals("null")) {
                editpassword.setError("password should mimimum 8 letters");

            } else {
                ConnectionDetector cd = new ConnectionDetector(Activity_Signup.this);
                if (cd.isConnectingToInternet()) {

                    new LoadRegister().execute();


                } else {

                    Toast.makeText(Activity_Signup.this, "Internet Connection Not Available Enable Internet And Try Again", Toast.LENGTH_LONG).show();

                }
            }

        } else {
            editmailid.setError("Please Enter Valid Email Id");
        }
    }

    public class LoadRegister extends AsyncTask<String, String, String>
            //implements RemoveClickListner
    {


        String status;
        String response;
        String strresponse;
        String strcode, strtype, strmessage;

        private ProgressDialog pDialog;
        Integer intcartcount = 0;
        String strregisteredtoken;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Activity_Signup.this);
            pDialog.setMessage("Please Wait ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        public String doInBackground(String... args) {

            //  product_details_lists = new ArrayList<Product_list>();

            // Retrieve JSON Objects from the given URL address
            List<NameValuePair> userpramas = new ArrayList<NameValuePair>();

            String paramsheader = "Bearer " + "sddf";
//            public static final String Signup_fcm_token = "fcm_token";

            SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
            regId = pref.getString("regId", null);
            userpramas.add(new BasicNameValuePair(EndUrls.Signup_fullname, str_full_name));
            userpramas.add(new BasicNameValuePair(EndUrls.Signup_name, str_user_name));
            userpramas.add(new BasicNameValuePair(EndUrls.Signup_emailid, str_email));
            userpramas.add(new BasicNameValuePair(EndUrls.Signup_phone, str_phone_number));
            userpramas.add(new BasicNameValuePair(EndUrls.Signup_password, str_password));
            userpramas.add(new BasicNameValuePair(EndUrls.Signup_referalcode, strreferralcode));
            userpramas.add(new BasicNameValuePair(EndUrls.Signup_fcm_token, regId));
            userpramas.add(new BasicNameValuePair(EndUrls.Signup_Device_type, "a"));
            Log.e("testing", "userpramas = " + userpramas);
            String strurl = EndUrls.Signup_URL;
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
                    String data = json.getString("data");
                    JSONObject dataobject = new JSONObject(data);
                    strregisteredtoken = dataobject.getString("register_exp");
                    temp_user = dataobject.getString("temp_user_id");
                    if (status.equals("success")) {
                        status = json.getString("status");
                        strresponse = json.getString("response");
                      /*  String arrayresponse = json.getString("data");
                        JSONObject  jsonobjectdata = new JSONObject(arrayresponse);
                        Log.e("testing", "adapter value=" + arrayresponse);
                        validuser_id = jsonobjectdata.getString("user_id");
*/
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
                Intent intent = new Intent(Activity_Signup.this, Activity_SignupOtp.class);
//                SharedPreferences prefuserdata =  Activity_Signup.this.getSharedPreferences("registerOtp", 0);
//                SharedPreferences.Editor prefeditor =prefuserdata.edit();
//                prefeditor.putString("strregisteredtoken",""+strregisteredtoken);
//                prefeditor.commit();
                intent.putExtra("strregisteredtoken", strregisteredtoken);
                intent.putExtra("temp_user", temp_user);
                intent.putExtra("ScreenName", "ActivitySignUp");

                intent.putExtra("str_user_name", str_user_name);
                startActivity(intent);


            } else {
                Toast.makeText(Activity_Signup.this, strmessage, Toast.LENGTH_SHORT).show();


            }


        }


    }


}
