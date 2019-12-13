package com.android.glory.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.glory.MainActivity;
import com.android.glory.R;
import com.android.glory.Retrofit.Aboutus.AboutusJson;
import com.android.glory.Retrofit.Api;
import com.android.glory.Retrofit.ApiClient;
import com.android.glory.Retrofit.ChangePassword.ChangePasswordJson;
import com.android.glory.Retrofit.Profile.ProfileJson;
import com.android.glory.Retrofit.UpdateProfile.UpdateprofileJson;
import com.android.glory.Utilites.EndUrls;
import com.android.glory.Utilites.JSONParser;
import com.android.glory.Utilites.sharedPrefs;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Profile extends AppCompatActivity{

    String[] Gender1 = {"Select Gender", "Male", "Female"};
    String[] Gender2 = {"Male", "Female"};
    String[] Gender3 = {"Female", "Male"};

    JSONParser jsonParser = new JSONParser();
    String strregisteredtoken;

    EditText editname, editemail;

    TextView textmobileno, textdob;

    Button butsubmit;

    Integer mYear, mMonth, mDay, mWeek;
    String date11 = "";
    String date22 = "";

    String strname, strmail, strspinner, strdob;

    Spinner spinner;
    String strspinnerposition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__profile);
     //  getApplication().setDisplayShowHomeEnabled(true);

        SharedPreferences prefuserdata3 = getSharedPreferences(sharedPrefs.Pref, 0);
        strregisteredtoken = prefuserdata3.getString(sharedPrefs.Pref_token, "");

        editname = (EditText) findViewById(R.id.editname);
        editemail = (EditText) findViewById(R.id.editemail);
        textmobileno = (TextView) findViewById(R.id.textmobileno);
        textdob = (TextView) findViewById(R.id.textdob);

        butsubmit = (Button) findViewById(R.id.butsubmit);

        RetrofitProfile();

        spinner = (Spinner) findViewById(R.id.spinner);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,Gender1);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(aa);


        // new LoadProfile().execute();


        butsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submit();

            }
        });
        textdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startdatepicker();
            }
        });
    }



    private void submit() {

        strname = editname.getText().toString();
        strmail = editemail.getText().toString();
        strdob = textdob.getText().toString();

        Log.e("testing","strname = "+strname);
        Log.e("testing","strmail = "+strmail);
        Log.e("testing","strdob = "+strdob);

        if (strname == null || strname.trim().length() == 0 || strname.trim().equals("0") || strname.trim().equals("0")){
            Toast.makeText(Activity_Profile.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
        }else if (strmail == null || strmail.trim().length() == 0 || strmail.trim().equals("0") || strmail.trim().equals("0")){
            Toast.makeText(Activity_Profile.this, "Please Enter Email Id", Toast.LENGTH_SHORT).show();
        }else if (strdob == null || strdob.trim().length() == 0 || strdob.trim().equals("0") || strdob.trim().equals("0")){
            Toast.makeText(Activity_Profile.this, "Please Select Date", Toast.LENGTH_SHORT).show();
        }else{

            submit2();

        }

    }
    private void submit2() {



        strspinner = spinner.getSelectedItem().toString();


        Log.e("testing","strspinner = "+strspinner);

        if (strspinner == null || strspinner.trim().length() == 0 || strspinner.trim().equals("0") || strspinner.trim().equals("0")){
            Toast.makeText(Activity_Profile.this, "Please Select Gender", Toast.LENGTH_SHORT).show();
        }else if (strspinner.trim().equals("Select Gender")){
            Toast.makeText(Activity_Profile.this, "Please Select Gender", Toast.LENGTH_SHORT).show();
        }else if (strspinner.trim().equals("Male")){
            strspinnerposition = "1";
            RetrofitUpdateProfile(strname,strmail,strdob,strspinnerposition);
        }else if (strspinner.trim().equals("Female")){
            strspinnerposition = "2";
            RetrofitUpdateProfile(strname,strmail,strdob,strspinnerposition);
        }else{
            Toast.makeText(Activity_Profile.this, "Please Select Gender", Toast.LENGTH_SHORT).show();
        }

    }
    private void startdatepicker() {

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mWeek = c.get(Calendar.WEEK_OF_MONTH);
        // Launch Date Picker Dialog
        DatePickerDialog dpd = new DatePickerDialog(Activity_Profile.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        String strfromdate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        Log.e("testing","strfromdate = "+strfromdate);
                        //  date1 = Fromdate.getText().toString().trim();
                        date11 = strfromdate;
                        // date22 = textselectto.getText().toString().trim();

                        int diffInDays2 = 0;

                        SimpleDateFormat dfDate2  = new SimpleDateFormat("dd//MM//yyyy");
                        java.util.Date d3 = null;
                        java.util.Date d4 = null;
                        Calendar cal2 = Calendar.getInstance();
                        try {
                            d3 = dfDate2.parse(date22);
                            d4 = dfDate2.parse(date11);//Returns 15/10/2012
                        } catch (java.text.ParseException e) {
                            e.printStackTrace();
                        }

                        if (d3 == null || d4 == null){
                            // Display Selected date in textbox
                            textdob.setText(dayOfMonth+"/" + (monthOfYear + 1) + "/" +year );
                            textdob.setTextColor(getResources().getColor(R.color.black));
                            Log.e("testing", "event_date" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(year, monthOfYear, dayOfMonth);
                            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                            Log.e("testing", "week of day = " + dayOfWeek);


                        }else{
                            diffInDays2 = (int) ((d3.getTime() - d4.getTime())/ (1000 * 60 * 60 * 24));
                            System.out.println(diffInDays2);
                            Log.e("testing","date difference  = "+diffInDays2);

                            if (diffInDays2 < 0) {
                                Toast.makeText(Activity_Profile.this, "Please select correct date", Toast.LENGTH_SHORT).show();
                            } else {


                                // Display Selected date in textbox
                                textdob.setText(dayOfMonth+"/" + (monthOfYear + 1) + "/" +year );
                                textdob.setTextColor(getResources().getColor(R.color.black));
                                Log.e("testing", "event_date" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, monthOfYear, dayOfMonth);
                                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                                Log.e("testing", "week of day = " + dayOfWeek);

                            }
                        }
                    }
                }, mYear, mMonth, mDay);

        dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        dpd.show();
    }


    public class LoadProfile extends AsyncTask<String, String, String>
            //implements RemoveClickListner
    {


        String status;
        String response;
        String strresponse;
        String strcode, strtype, strmessage;

        String strfirst_name, strdob, strmobile_no, stremail, strgender;

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
                        strgender = jsonobjectcontent.getString("gender");


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
                textdob.setText(strdob);
                textmobileno.setText(strmobile_no);
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

                        editname.setText(response.body().getData().getUsername());
                        textdob.setText(response.body().getData().getDob());
                        textmobileno.setText(response.body().getData().getMobileNo());
                        editemail.setText(response.body().getData().getEmail());

                        String strgender = response.body().getData().getGender();

                        if (strgender == null || strgender.trim().length() == 0 || strgender.trim().equals("null") || strgender.trim().equals("0")){
                            ArrayAdapter aa = new ArrayAdapter(Activity_Profile.this,android.R.layout.simple_spinner_item,Gender1);
                            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            //Setting the ArrayAdapter data on the Spinner
                            spinner.setAdapter(aa);
                        }else if (strgender.trim().equals("1")){
                            ArrayAdapter aa = new ArrayAdapter(Activity_Profile.this,android.R.layout.simple_spinner_item,Gender2);
                            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            //Setting the ArrayAdapter data on the Spinner
                            spinner.setAdapter(aa);
                        }else if (strgender.trim().equals("2")){
                            ArrayAdapter aa = new ArrayAdapter(Activity_Profile.this,android.R.layout.simple_spinner_item,Gender3);
                            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            //Setting the ArrayAdapter data on the Spinner
                            spinner.setAdapter(aa);
                        }else{
                            ArrayAdapter aa = new ArrayAdapter(Activity_Profile.this,android.R.layout.simple_spinner_item,Gender1);
                            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            //Setting the ArrayAdapter data on the Spinner
                            spinner.setAdapter(aa);
                        }

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

    private void RetrofitUpdateProfile(final String username, String email, String dod, String gender) {
        final ProgressDialog pProgressDialog;
        pProgressDialog = new ProgressDialog(Activity_Profile.this);
        pProgressDialog.setMessage("Please Wait ...");
        pProgressDialog.setIndeterminate(false);
        pProgressDialog.setCancelable(false);
        pProgressDialog.show();

        //call retrofit
        //making api call
        Api api = ApiClient.getClient().create(Api.class);
        Call<UpdateprofileJson> login = api.profilupdatejson(username,email,dod,gender,"empty","empty","empty","empty","Bearer "+strregisteredtoken);

        login.enqueue(new Callback<UpdateprofileJson>() {
            @Override
            public void onResponse(Call<UpdateprofileJson> call, Response<UpdateprofileJson> response) {
                pProgressDialog.dismiss();
                Log.e("testing","status = "+response.body().getStatus());
                Log.e("testing","response = "+response.body().getResponse().getType());

                if (response.body().getStatus() == null || response.body().getStatus().length() == 0){

                }else if (response.body().getStatus().equals("success")) {
                    if (response.body().getResponse() == null ){

                    }else if (response.body().getResponse().getType().equals("update_success")){

                        Toast.makeText(Activity_Profile.this, response.body().getResponse().getMessage(), Toast.LENGTH_SHORT).show();
                       /* Intent intent = new Intent(Activity_Profile.this, MainActivity.class);
                        startActivity(intent);
                        finish();*/


                    }else{
                        Toast.makeText(Activity_Profile.this, response.body().getResponse().getMessage(), Toast.LENGTH_SHORT).show();
                    }





                   /*

                    Intent intent=new Intent(Activity_Event_Details.this,Activity_Event_Details.class);
                    startActivity(intent);
                    finish();

*/




                    //  Toast.makeText(Activity_Event_Details.this, message, Toast.LENGTH_SHORT).show();


                } else  {
                    Log.e("testing","error");
                    Toast.makeText(Activity_Profile.this, response.body().getResponse().getMessage(), Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFailure(Call<UpdateprofileJson> call, Throwable t) {
                Toast.makeText(Activity_Profile.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                pProgressDialog.dismiss();

            }
        });





    }


}
