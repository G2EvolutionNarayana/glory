package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.glory.apk.Model.AboutUs.AboutExample;
import com.glory.apk.R;
import com.glory.apk.Retrofit.Api;
import com.glory.apk.Retrofit.ApiClient;
import com.glory.apk.Utilites.JSONParser;
import com.glory.apk.Utilites.sharedPrefs;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    EditText editname, editemail, editfullname;

    TextView textdob, textgender;
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
        editfullname = (EditText) findViewById(R.id.editfullname);

        editname = (EditText) findViewById(R.id.editname);
        editemail = (EditText) findViewById(R.id.editemail);
        textmobileno = (EditText) findViewById(R.id.textmobileno);
        textdob = (TextView) findViewById(R.id.textdob);

        butsubmit = (Button) findViewById(R.id.butsubmit);
        textgender = (TextView) findViewById(R.id.textgender);

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


                        if (String.valueOf(response.body().getAboutData().getFullname()).equals("null")) {
                            textmobileno.setVisibility(View.GONE);

                        } else {
                            textmobileno.setClickable(false);
                            editfullname.setText(Html.fromHtml(response.body().getAboutData().getFullname().toString()));
                        }


                        if (String.valueOf(response.body().getAboutData().getName()).equals("null")) {
                        } else {
                            editname.setText(Html.fromHtml(response.body().getAboutData().getName().toString()));

                        }


                        if (response.body().getAboutData().getEmail() != null) {
                            editemail.setText(Html.fromHtml(response.body().getAboutData().getEmail()));
                        }

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
