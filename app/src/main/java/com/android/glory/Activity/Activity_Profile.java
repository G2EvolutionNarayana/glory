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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.glory.MainActivity;
import com.android.glory.Model.AboutUs.AboutExample;
import com.android.glory.R;
import com.android.glory.Retrofit.Api;
import com.android.glory.Retrofit.ApiClient;
import com.android.glory.Retrofit.UpdateProfile.UpdateprofileJson;
import com.android.glory.Utilites.EndUrls;
import com.android.glory.Utilites.JSONParser;
import com.android.glory.Utilites.sharedPrefs;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Profile extends AppCompatActivity {
    String[] Gender1 = {"Select Gender", "Male", "Female"};
    String[] Gender2 = {"Male", "Female"};
    String[] Gender3 = {"Female", "Male"};

    JSONParser jsonParser = new JSONParser();
    String strregisteredtoken;

    EditText editname, editemail;

    TextView  textdob,textgender;
    EditText textmobileno;

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
        textmobileno = (EditText) findViewById(R.id.textmobileno);
        textdob = (TextView) findViewById(R.id.textdob);

        butsubmit = (Button) findViewById(R.id.butsubmit);
        textgender= (TextView) findViewById(R.id.textgender);

        Activity_Profile();

        spinner = (Spinner) findViewById(R.id.xGenderSpinner);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Gender1);
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

//                startdatepicker();
            }
        });
    }


    private void submit() {

        strname = editname.getText().toString();
        strmail = editemail.getText().toString();
        strdob = textdob.getText().toString();

        Log.e("testing", "strname = " + strname);
        Log.e("testing", "strmail = " + strmail);
        Log.e("testing", "strdob = " + strdob);

        if (strname == null || strname.trim().length() == 0 || strname.trim().equals("0") || strname.trim().equals("0")) {
            Toast.makeText(Activity_Profile.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
        } else if (strmail == null || strmail.trim().length() == 0 || strmail.trim().equals("0") || strmail.trim().equals("0")) {
            Toast.makeText(Activity_Profile.this, "Please Enter Email Id", Toast.LENGTH_SHORT).show();
        } else {

            submit2();

        }

//         else if (strdob == null || strdob.trim().length() == 0 || strdob.trim().equals("0") || strdob.trim().equals("0")) {
//            Toast.makeText(Activity_Profile.this, "Please Selec t Date", Toast.LENGTH_SHORT).show();
//        }

    }

    private void submit2() {


        strspinner = spinner.getSelectedItem().toString();


        Log.e("testing", "strspinner = " + strspinner);

        if (strspinner == null || strspinner.trim().length() == 0 || strspinner.trim().equals("0") || strspinner.trim().equals("0")) {
            Toast.makeText(Activity_Profile.this, "Please Select Gender", Toast.LENGTH_SHORT).show();
        } else if (strspinner.trim().equals("Select Gender")) {
            Toast.makeText(Activity_Profile.this, "Please Select Gender", Toast.LENGTH_SHORT).show();
        } else if (strspinner.trim().equals("Male")) {
            strspinnerposition = "1";
//            RetrofitUpdateProfile(strname,strmail,strdob,strspinnerposition);
//            Activity_UpdateProfile(strname, strmail, strdob, strspinnerposition);
        } else if (strspinner.trim().equals("Female")) {
            strspinnerposition = "2";
//            RetrofitUpdateProfile(strname,strmail,strdob,strspinnerposition);
//            Activity_UpdateProfile(strname, strmail, strdob, strspinnerposition);
        } else {
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
                        Log.e("testing", "strfromdate = " + strfromdate);
                        //  date1 = Fromdate.getText().toString().trim();
                        date11 = strfromdate;
                        // date22 = textselectto.getText().toString().trim();

                        int diffInDays2 = 0;

                        SimpleDateFormat dfDate2 = new SimpleDateFormat("dd//MM//yyyy");
                        java.util.Date d3 = null;
                        java.util.Date d4 = null;
                        Calendar cal2 = Calendar.getInstance();
                        try {
                            d3 = dfDate2.parse(date22);
                            d4 = dfDate2.parse(date11);//Returns 15/10/2012
                        } catch (java.text.ParseException e) {
                            e.printStackTrace();
                        }

                        if (d3 == null || d4 == null) {
                            // Display Selected date in textbox
                            textdob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            textdob.setTextColor(getResources().getColor(R.color.black));
                            Log.e("testing", "event_date" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(year, monthOfYear, dayOfMonth);
                            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                            Log.e("testing", "week of day = " + dayOfWeek);


                        } else {
                            diffInDays2 = (int) ((d3.getTime() - d4.getTime()) / (1000 * 60 * 60 * 24));
                            System.out.println(diffInDays2);
                            Log.e("testing", "date difference  = " + diffInDays2);

                            if (diffInDays2 < 0) {
                                Toast.makeText(Activity_Profile.this, "Please select correct date", Toast.LENGTH_SHORT).show();
                            } else {


                                // Display Selected date in textbox
                                textdob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
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


    private void Activity_Profile() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");
        final ProgressDialog dialog;

        dialog = new ProgressDialog(Activity_Profile.this);
        dialog.setMessage("Please Wait ...");
//        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.show();

//        Api api = ApiClient.getClient().create(Api.class);
//        Call<AboutExample> login = api.PlayersList("1");


        String viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);
        Log.e("testing", "viewuseremail = " + viewuseremail);

        Api api = ApiClient.getClient().create(Api.class);
        Call<AboutExample> login = api.aboutusjson(viewuseremail);
        login.enqueue(new Callback<AboutExample>() {
            @Override
            public void onResponse(Call<AboutExample> call, Response<AboutExample> response) {
                dialog.dismiss();
                Log.e("testing", "status = " + response.body().getStatus());
                Log.e("testing", "response = " + response.body().getAboutResponse().getType());
                //  Log.e("testing","response = "+response.body().getData().getPageContent());

                Log.e("testing", "response = " + response.body());

                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    if (response.body().getAboutResponse() == null) {

                    } else if (response.body().getAboutResponse().getType().equals("data_available")) {


                        if (String.valueOf(response.body().getAboutData().getName()).equals("null")) {
                        } else {
                            editname.setText(Html.fromHtml(response.body().getAboutData().getName().toString()));

                        }
                        editemail.setText(Html.fromHtml(response.body().getAboutData().getEmail()));

                        if (String.valueOf(response.body().getAboutData().getPhone()).equals("null")) {

                        } else {
                            textmobileno.setClickable(false);
                            textmobileno.setText(Html.fromHtml(response.body().getAboutData().getPhone().toString()));

                        }
//                        textdob.setText(Html.fromHtml(response.body().getAboutData().get()));
//                        editname.setText(Html.fromHtml(response.body().getAboutData().getName()));
                        if (String.valueOf(response.body().getAboutData().getDob()).equals("null")) {
                            textdob.setVisibility(View.GONE);
                        } else {
                            DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                            DateFormat targetFormat = new SimpleDateFormat("dd/MM/yyyy");
                            Date date = null;
                            textdob.setVisibility(View.VISIBLE);

                            try {
                                date = originalFormat.parse(String.valueOf(response.body().getAboutData().getDob()));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String formattedDate = targetFormat.format(date);
                            textdob.setText(Html.fromHtml(String.valueOf(formattedDate)));

                        }

                        if (String.valueOf(response.body().getAboutData().getGender()).equals("null")) {
                            textgender.setVisibility(View.GONE);


                        } else {
                            if (String.valueOf(response.body().getAboutData().getGender()).equals("Male")) {
                                textgender.setVisibility(View.VISIBLE);
                                textgender.setText("Male");

                            } else {
                                textgender.setVisibility(View.VISIBLE);
                                textgender.setText("Female");

                                Log.e("testing", String.valueOf(response.body().getAboutData().getGender()));

                            }

                        }
                    } else {
                        Toast.makeText(Activity_Profile.this, response.body().getAboutResponse().getType(), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Log.e("testing", "error");
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.body().getAboutResponse().getType(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<AboutExample> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        });
    }


}
