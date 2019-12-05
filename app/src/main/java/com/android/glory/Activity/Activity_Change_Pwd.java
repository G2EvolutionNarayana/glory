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
import android.widget.EditText;
import android.widget.Toast;

import com.android.glory.MainActivity;
import com.android.glory.R;
import com.android.glory.Utilites.EndUrls;
import com.android.glory.Utilites.JSONParser;
import com.android.glory.Utilites.sharedPrefs;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

        if (stroldpassword == null || stroldpassword.trim().length() == 0 || stroldpassword.trim().equals("null")){
            Toast.makeText(Activity_Change_Pwd.this, "Please Enter old Password", Toast.LENGTH_SHORT).show();
        }else if (strnewpassword == null || strnewpassword.trim().length() == 0 || strnewpassword.trim().equals("null")){
            Toast.makeText(Activity_Change_Pwd.this, "Please Enter New Password", Toast.LENGTH_SHORT).show();
        }else if (strconfirmpassword == null || strconfirmpassword.trim().length() == 0 || strconfirmpassword.trim().equals("null")){

            Toast.makeText(Activity_Change_Pwd.this, "Please Enter Confirm Password", Toast.LENGTH_SHORT).show();

        }else{
            if (strnewpassword.trim().equals(strconfirmpassword.trim())){

                Log.e("testing","success");
                new LoadChangePassword().execute();
            }else{

                Toast.makeText(Activity_Change_Pwd.this, "Password Mismatch", Toast.LENGTH_SHORT).show();

            }
        }

    }

    public class LoadChangePassword extends AsyncTask<String, String, String>
            //implements RemoveClickListner
    {


        String status;
        String response;
        String strresponse;
        String strcode, strtype, strmessage;

        String strfirst_name, strdob, strmobile_no, stremail;

        private ProgressDialog pDialog;
        Integer intcartcount = 0;
        String validuser_id;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Activity_Change_Pwd.this);
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


              userpramas.add(new BasicNameValuePair(EndUrls.ChangePassword_OldPassword, stroldpassword));
              userpramas.add(new BasicNameValuePair(EndUrls.ChangePassword_NewPassword, strnewpassword));

            Log.e("testing", "paramsheader = " + paramsheader);
            Log.e("testing", "userpramas = " + userpramas);

            String strurl = EndUrls.ChangePassword_URL;
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
                    // strmessage = jsonobject.getString("message");
                  /*  if (status.equals("success")) {

                        status = json.getString("status");
                        strresponse = json.getString("response");
                        String strcontent = json.getString("data");

                        JSONObject  jsonobjectcontent = new JSONObject(strcontent);
                        strfirst_name = jsonobjectcontent.getString("first_name");



                    }else{

                    }*/
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


                Intent intent = new Intent(Activity_Change_Pwd.this, MainActivity.class);
                startActivity(intent);
                finish();



            }
            else {
                Toast.makeText(Activity_Change_Pwd.this, strmessage, Toast.LENGTH_SHORT).show();


            }



        }



    }




}
