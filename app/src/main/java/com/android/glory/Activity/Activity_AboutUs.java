package com.android.glory.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.glory.MainActivity;
import com.android.glory.R;
import com.android.glory.Retrofit.Aboutus.AboutusJson;
import com.android.glory.Retrofit.Api;
import com.android.glory.Retrofit.ApiClient;
import com.android.glory.Retrofit.Logout.LogoutJson;
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

public class Activity_AboutUs extends AppCompatActivity {


    JSONParser jsonParser = new JSONParser();
    String strregisteredtoken;

    TextView textdesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__about_us);

        SharedPreferences prefuserdata3 = getSharedPreferences(sharedPrefs.Pref, 0);
        strregisteredtoken = prefuserdata3.getString(sharedPrefs.Pref_token, "");

        textdesc = (TextView) findViewById(R.id.textdesc);

        // new LoadAboutus().execute();

        RetrofitAboutus();

    }

    public class LoadAboutus extends AsyncTask<String, String, String>
            //implements RemoveClickListner
    {


        String status;
        String response;
        String strresponse;
        String strpage_content;
        String strcode, strtype, strmessage;

        private ProgressDialog pDialog;
        Integer intcartcount = 0;
        String validuser_id;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Activity_AboutUs.this);
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

            String strurl = EndUrls.Aboutus_URL;
            Log.e("testing", "strurl = " + strurl);
            JSONObject json = jsonParser.makeHttpRequest(strurl, "GET", userpramas, paramsheader);

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
                   // strmessage = jsonobject.getString("message");
                    if (status.equals("success")) {

                        status = json.getString("status");
                        strresponse = json.getString("response");
                        String strcontent = json.getString("data");

                        JSONObject  jsonobjectcontent = new JSONObject(strcontent);
                        strpage_content = jsonobjectcontent.getString("page_content");


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

                textdesc.setText(Html.fromHtml(strpage_content));





            }
            else {
                Toast.makeText(Activity_AboutUs.this, strmessage, Toast.LENGTH_SHORT).show();


            }



        }



    }


    private void RetrofitAboutus() {

        Log.e("testing","strregisteredtoken = "+strregisteredtoken);

        final ProgressDialog pProgressDialog;
        pProgressDialog = new ProgressDialog(Activity_AboutUs.this);
        pProgressDialog.setMessage("Please Wait ...");
        pProgressDialog.setIndeterminate(false);
        pProgressDialog.setCancelable(false);
        pProgressDialog.show();

        //call retrofit
        //making api call
        Api api = ApiClient.getClient().create(Api.class);
        Call<AboutusJson> login = api.aboutusjson("Bearer "+strregisteredtoken);

        login.enqueue(new Callback<AboutusJson>() {
            @Override
            public void onResponse(Call<AboutusJson> call, Response<AboutusJson> response) {
                pProgressDialog.dismiss();
                Log.e("testing","status = "+response.body().getStatus());
                Log.e("testing","response = "+response.body().getResponse().getType());
                Log.e("testing","response = "+response.body().getData().getPageContent());

                if (response.body().getStatus() == null || response.body().getStatus().length() == 0){

                }else if (response.body().getStatus().equals("success")) {
                    if (response.body().getResponse() == null ){

                    }else if (response.body().getResponse().getType().equals("about_us_success")){

                        textdesc.setText(Html.fromHtml(response.body().getData().getPageContent()));

                    }else{
                        Toast.makeText(Activity_AboutUs.this, response.body().getResponse().getType(), Toast.LENGTH_SHORT).show();
                    }





                   /*

                    Intent intent=new Intent(Activity_Event_Details.this,Activity_Event_Details.class);
                    startActivity(intent);
                    finish();

*/




                    //  Toast.makeText(Activity_Event_Details.this, message, Toast.LENGTH_SHORT).show();


                } else  {
                    Log.e("testing","error");
                    Toast.makeText(Activity_AboutUs.this, response.body().getResponse().getType(), Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFailure(Call<AboutusJson> call, Throwable t) {
                Toast.makeText(Activity_AboutUs.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                pProgressDialog.dismiss();

            }
        });





    }

}
