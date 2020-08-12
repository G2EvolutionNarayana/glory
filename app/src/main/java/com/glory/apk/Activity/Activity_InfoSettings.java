package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.glory.apk.Model.AboutUs.AboutExample;
import com.glory.apk.R;
import com.glory.apk.Retrofit.Api;
import com.glory.apk.Retrofit.ApiClient;
import com.glory.apk.Retrofit.UpdateProfile.UpdateprofileJson;
import com.glory.apk.Utilites.JSONParser;
import com.glory.apk.Utilites.sharedPrefs;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_InfoSettings extends AppCompatActivity {
    String[] Gender1 = {"Select Gender", "Male", "Female"};
    String[] Gender2 = {"Male", "Female"};
    String[] Gender3 = {"Female", "Male"};

    JSONParser jsonParser = new JSONParser();
    String strregisteredtoken;

    EditText editname, editemail, editfullname;

    EditText textmobileno;
    EditText textdob;

    Button butsubmit;

    Integer mYear, mMonth, mDay, mWeek;
    String date11 = "";
    String date22 = "";

    String strname, strmail, strspinner, strdob;

    Spinner spinner;
    String strspinnerposition;
    String strfullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__info_settings);
        SharedPreferences prefuserdata3 = getSharedPreferences(sharedPrefs.Pref, 0);
        strregisteredtoken = prefuserdata3.getString(sharedPrefs.Pref_token, "");

        editname = (EditText) findViewById(R.id.editname);
        editemail = (EditText) findViewById(R.id.editemail);
        textmobileno = (EditText) findViewById(R.id.textmobileno);
        textdob = (EditText) findViewById(R.id.textdob);
        butsubmit = (Button) findViewById(R.id.butsubmit);
        editfullname = (EditText) findViewById(R.id.editfullname);


        spinner = (Spinner) findViewById(R.id.xGenderSpinner);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Gender1);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(aa);


        // new LoadProfile().execute();

        Activity_Profile();

        butsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submit();
            }
        });
//        textdob.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                startdatepicker();
//            }
//        });

        TextWatcher tw = new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().equals(current)) {
                    String clean = charSequence.toString().replaceAll("[^\\d.]|\\.", "");
                    String cleanC = current.replaceAll("[^\\d.]|\\.", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int j = 2; j <= cl && j < 6; j += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8) {
                        clean = clean + ddmmyyyy.substring(clean.length());
                    } else {
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day = Integer.parseInt(clean.substring(0, 2));
                        int mon = Integer.parseInt(clean.substring(2, 4));
                        int year = Integer.parseInt(clean.substring(4, 8));

                        mon = mon < 1 ? 1 : mon > 12 ? 12 : mon;
                        cal.set(Calendar.MONTH, mon - 1);
                        year = (year < 1900) ? 1900 : (year > 2100) ? 2100 : year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE)) ? cal.getActualMaximum(Calendar.DATE) : day;
                        clean = String.format("%02d%02d%02d", day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));
                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    textdob.setText(current);
                    textdob.setSelection(sel < current.length() ? sel : current.length());

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }


        };

        textdob.addTextChangedListener(tw);

    }


    private void submit() {

        strname = editname.getText().toString();
        strmail = editemail.getText().toString();
        strdob = textdob.getText().toString();
        strdob = textdob.getText().toString();
        strfullname = editfullname.getText().toString();


        Log.e("testing", "strname = " + strname);
        Log.e("testing", "strmail = " + strmail);
        Log.e("testing", "strdob = " + strdob);

        if (strfullname == null || strfullname.trim().length() == 0 || strfullname.trim().equals("0") || strfullname.trim().equals("0")) {
            Toast.makeText(Activity_InfoSettings.this, "Please Enter Fullname", Toast.LENGTH_SHORT).show();
        } else if (strname == null || strname.trim().length() == 0 || strname.trim().equals("0") || strname.trim().equals("0")) {
            Toast.makeText(Activity_InfoSettings.this, "Please Enter Username", Toast.LENGTH_SHORT).show();
        } else if (strmail == null || strmail.trim().length() == 0 || strmail.trim().equals("0") || strmail.trim().equals("0")) {
            Toast.makeText(Activity_InfoSettings.this, "Please Enter Email Id", Toast.LENGTH_SHORT).show();
        } else if (textmobileno == null || textmobileno.getText().toString().trim().length() != 10) {
            Toast.makeText(Activity_InfoSettings.this, "Please Enter Phone number", Toast.LENGTH_SHORT).show();
        } else if (strdob == null || strdob.trim().length() == 0 || strdob.trim().equals("0") || strdob.trim().equals("0")) {
            Toast.makeText(Activity_InfoSettings.this, "Please Select Date", Toast.LENGTH_SHORT).show();
        } else {
//            Activity_UpdateProfile(strname, strmail, strdob, strspinnerposition);

            submit2();

        }

    }

    private void submit2() {

        strspinner = spinner.getSelectedItem().toString();
        Log.e("testing", "strspinner = " + strspinner);
        if (strspinner == null || strspinner.trim().length() == 0 || strspinner.trim().equals("0") || strspinner.trim().equals("0")) {
            Toast.makeText(Activity_InfoSettings.this, "Please Select Gender", Toast.LENGTH_SHORT).show();
        } else if (strspinner.trim().equals("Select Gender")) {
            Toast.makeText(Activity_InfoSettings.this, "Please Select Gender", Toast.LENGTH_SHORT).show();
        } else if (strspinner.trim().equals("Male")) {
            strspinnerposition = "Male";
//            RetrofitUpdateProfile(strname,strmail,strdob,strspinnerposition);
            Activity_UpdateProfile(strname, strmail, strdob, strspinnerposition);
        } else if (strspinner.trim().equals("Female")) {
            strspinnerposition = "Female";
//            RetrofitUpdateProfile(strname,strmail,strdob,strspinnerposition);
            Activity_UpdateProfile(strname, strmail, strdob, strspinnerposition);
        } else {
            Toast.makeText(Activity_InfoSettings.this, "Please Select Gender", Toast.LENGTH_SHORT).show();
        }

    }

    private void startdatepicker() {

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mWeek = c.get(Calendar.WEEK_OF_MONTH);
        // Launch Date Picker Dialog
        DatePickerDialog dpd = new DatePickerDialog(Activity_InfoSettings.this,
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
                                Toast.makeText(Activity_InfoSettings.this, "Please select correct date", Toast.LENGTH_SHORT).show();
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
        dialog = new ProgressDialog(Activity_InfoSettings.this);
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
                            editname.setClickable(true);
                            editname.setEnabled(true);
                            editname.setFocusable(true);
                        } else {
                            editname.setClickable(false);
                            editname.setFocusable(false);
                            editname.setEnabled(false);
                            editname.setText(Html.fromHtml(response.body().getAboutData().getName().toString()));

                        }

                        if (String.valueOf(response.body().getAboutData().getFullname()).equals("null")) {


                        } else {

                            editfullname.setText(Html.fromHtml(response.body().getAboutData().getFullname().toString()));
                        }

                        if (response.body().getAboutData().getEmail() != null) {
                            editemail.setText(Html.fromHtml(response.body().getAboutData().getEmail()));

                        }

                        if (String.valueOf(response.body().getAboutData().getPhone()).equals("null")) {
                            textmobileno.setClickable(true);
                            textmobileno.setEnabled(true);
                            textmobileno.setFocusable(true);

                        } else {
                            textmobileno.setClickable(false);
                            textmobileno.setFocusable(false);
                            textmobileno.setEnabled(false);

                            textmobileno.setText(Html.fromHtml(response.body().getAboutData().getPhone().toString()));

                        }

                        if (String.valueOf(response.body().getAboutData().getDob()).equals("null")) {

                        } else {


                            DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                            DateFormat targetFormat = new SimpleDateFormat("dd/MM/yyyy");
                            Date date = null;
                            try {
                                date = originalFormat.parse(response.body().getAboutData().getDob().toString());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String formattedDate = targetFormat.format(date);
                            textdob.setText(Html.fromHtml(String.valueOf(formattedDate)));

                        }

                        if (String.valueOf(response.body().getAboutData().getGender()).equals("null")) {
                            spinner.setSelection(0);

                        } else {
                            if (String.valueOf(response.body().getAboutData().getGender()).equals("Male")) {

                                spinner.setSelection(1);

                            } else {
                                spinner.setSelection(2);
                                Log.e("testing", String.valueOf(response.body().getAboutData().getGender()));

                            }

                        }
                    } else {
                        Toast.makeText(Activity_InfoSettings.this, response.body().getAboutResponse().getType(), Toast.LENGTH_SHORT).show();
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

    private void Activity_UpdateProfile(final String username, String email, String dod, String gender) {

        Log.e("testing", "strregisteredtoken = " + "matchesList");
        final ProgressDialog dialog;
        dialog = new ProgressDialog(Activity_InfoSettings.this);
        dialog.setMessage("Please Wait ...");
//        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.show();

        String viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);
        Log.e("testing", "viewuseremail = " + viewuseremail);

        DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = originalFormat.parse(dod);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = targetFormat.format(date);
        Api api = ApiClient.getClient().create(Api.class);
        //       Call<UpdateprofileJson> login = api.profilupdatejson(username,email,dod,gender,"empty","empty","empty","empty","Bearer "+strregisteredtoken);
        Log.e("testing", "dod = " + dod);
        Log.e("testing", "gender = " + gender);
        Log.e("testing", "formattedDate = " + formattedDate);
        Log.e("testing", "viewuseremail = " + viewuseremail);
        Log.e("testing", "email = " + email);
        Log.e("testing", "username = " + username);

        Call<UpdateprofileJson> login = api.updateProfile(viewuseremail, email, strfullname, username, dod, gender, textmobileno.getText().toString());
        login.enqueue(new Callback<UpdateprofileJson>() {
            @Override
            public void onResponse(Call<UpdateprofileJson> call, Response<UpdateprofileJson> response) {
                dialog.dismiss();

                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    if (response.body().getResponse() == null) {

                    } else if (response.body().getResponse().getType().equals("update_success")) {
//                        finish();
//                        startActivity(getIntent());
                        Toast.makeText(getApplicationContext(), response.body().getResponse().getMessage(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getResponse().getMessage(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                    }
                } else {
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.body().getResponse().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<UpdateprofileJson> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        });
    }


}























































































