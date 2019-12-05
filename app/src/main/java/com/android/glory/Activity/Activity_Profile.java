package com.android.glory.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.glory.MainActivity;
import com.android.glory.R;
import com.android.glory.Retrofit.Aboutus.AboutusJson;
import com.android.glory.Retrofit.Api;
import com.android.glory.Retrofit.ApiClient;
import com.android.glory.Retrofit.Profile.ProfileJson;
import com.android.glory.Utilites.EndUrls;
import com.android.glory.Utilites.JSONParser;
import com.android.glory.Utilites.sharedPrefs;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Profile extends AppCompatActivity {


    JSONParser jsonParser = new JSONParser();
    String strregisteredtoken;

    EditText editname, editemail, editmobileno, editdob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__profile);
     //  getApplication().setDisplayShowHomeEnabled(true);

        SharedPreferences prefuserdata3 = getSharedPreferences(sharedPrefs.Pref, 0);
        strregisteredtoken = prefuserdata3.getString(sharedPrefs.Pref_token, "");

        editname = (EditText) findViewById(R.id.editname);
        editemail = (EditText) findViewById(R.id.editemail);
        editmobileno = (EditText) findViewById(R.id.editmobileno);
        editdob = (EditText) findViewById(R.id.editdob);

        RetrofitProfile();

       // new LoadProfile().execute();



    }

    public class LoadProfile extends AsyncTask<String, String, String>
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
            pDialog = new ProgressDialog(Activity_Profile.this);
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

            Log.e("testing", "paramsheader = " + paramsheader);
            Log.e("testing", "userpramas = " + userpramas);

            String strurl = EndUrls.ProfileDetails_URL;
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
                    if (status.equals("success")) {

                        status = json.getString("status");
                        strresponse = json.getString("response");
                        String strcontent = json.getString("data");



                        JSONObject  jsonobjectcontent = new JSONObject(strcontent);
                        strfirst_name = jsonobjectcontent.getString("first_name");
                        strdob = jsonobjectcontent.getString("dob");
                        strmobile_no = jsonobjectcontent.getString("mobile_no");
                        stremail = jsonobjectcontent.getString("email");


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




                editname.setText(strfirst_name);
                editdob.setText(strdob);
                editmobileno.setText(strmobile_no);
                editemail.setText(stremail);


            }
            else {
                Toast.makeText(Activity_Profile.this, strmessage, Toast.LENGTH_SHORT).show();


            }



        }



    }
    private void RetrofitProfile() {

        Log.e("testing","strregisteredtoken = "+strregisteredtoken);

        final ProgressDialog pProgressDialog;
        pProgressDialog = new ProgressDialog(Activity_Profile.this);
        pProgressDialog.setMessage("Please Wait ...");
        pProgressDialog.setIndeterminate(false);
        pProgressDialog.setCancelable(false);
        pProgressDialog.show();

        //call retrofit
        //making api call
        Api api = ApiClient.getClient().create(Api.class);
        Call<ProfileJson> login = api.profilejson("Bearer "+strregisteredtoken);

        login.enqueue(new Callback<ProfileJson>() {
            @Override
            public void onResponse(Call<ProfileJson> call, Response<ProfileJson> response) {
                pProgressDialog.dismiss();
                Log.e("testing","status = "+response.body().getStatus());
                Log.e("testing","response = "+response.body().getResponse().getType());

                if (response.body().getStatus() == null || response.body().getStatus().length() == 0){

                }else if (response.body().getStatus().equals("success")) {
                    if (response.body().getResponse() == null ){

                    }else if (response.body().getResponse().getType().equals("data_available")){

                        editname.setText(response.body().getData().getFirstName());
                        editdob.setText(response.body().getData().getDob());
                        editmobileno.setText(response.body().getData().getMobileNo());
                        editemail.setText(response.body().getData().getEmail());



                    }else{
                        Toast.makeText(Activity_Profile.this, response.body().getResponse().getType(), Toast.LENGTH_SHORT).show();
                    }





                   /*

                    Intent intent=new Intent(Activity_Event_Details.this,Activity_Event_Details.class);
                    startActivity(intent);
                    finish();

*/




                    //  Toast.makeText(Activity_Event_Details.this, message, Toast.LENGTH_SHORT).show();


                } else  {
                    Log.e("testing","error");
                    Toast.makeText(Activity_Profile.this, response.body().getResponse().getType(), Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFailure(Call<ProfileJson> call, Throwable t) {
                Toast.makeText(Activity_Profile.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                pProgressDialog.dismiss();

            }
        });





    }

}
