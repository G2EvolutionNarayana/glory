package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.glory.apk.R;
import com.glory.apk.Retrofit.Api;
import com.glory.apk.Retrofit.ApiClient;
import com.glory.apk.Retrofit.FAQs.FaqsJson;
import com.glory.apk.Utilites.sharedPrefs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_HelpDesk extends AppCompatActivity {


    Dialog dialog;

    String strfaqquestion;
    EditText editquestion;
    String strregisteredtoken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__help_desk);

        SharedPreferences prefuserdata3 = getSharedPreferences(sharedPrefs.Pref, 0);
        strregisteredtoken = prefuserdata3.getString(sharedPrefs.Pref_token, "");

        dialog = new Dialog(Activity_HelpDesk.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);


        LinearLayout linearemailid = (LinearLayout) findViewById(R.id.linearemailid);
        LinearLayout linearfaq = (LinearLayout) findViewById(R.id.linearfaq);


        linearemailid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strmail = "Hello@glory5.in";
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", strmail, null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Glory5");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });

        linearfaq.setOnClickListener(new View.OnClickListener() {
            @Override



            public void onClick(View v) {
//                dialogfaq();
                Intent intent=new Intent(Activity_HelpDesk.this,FrequentlyAskedQuestionsActivity.class);
                startActivity(intent);
            }
        });

    }

    private void dialogfaq() {
        dialog.setContentView(R.layout.dialog_faq);

        ImageView signupcancel = (ImageView) dialog.findViewById(R.id.imgcancel);
        editquestion = (EditText) dialog.findViewById(R.id.editquestion);
        TextView textsubmit = (TextView) dialog.findViewById(R.id.textsubmit);


        textsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strfaqquestion = editquestion.getText().toString();

                if (strfaqquestion == null || strfaqquestion.length() == 0){
                    editquestion.setError("Enter Question");
                    // Toast.makeText(Activity_Balance.this, "Enter Amount", Toast.LENGTH_SHORT).show();
                }else{



                    RetrofitUploadFAQ(strfaqquestion);
                   /* ConnectionDetector cd = new ConnectionDetector(getActivity());
                    if (cd.isConnectingToInternet()) {
                        new WalletUser().execute();
                    } else {
                        Toast.makeText(getActivity(), "Internet Connection Not Available Enable Internet And Try Again", Toast.LENGTH_LONG).show();
                    }*/


                }
            }
        });


        signupcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void RetrofitUploadFAQ(final String faq) {
        ProgressDialog pProgressDialog;
        pProgressDialog = new ProgressDialog(Activity_HelpDesk.this);
        pProgressDialog.setMessage("Please Wait ...");
        pProgressDialog.setIndeterminate(false);
        pProgressDialog.setCancelable(false);
        pProgressDialog.show();

        //call retrofit
        //making api call
        Api api = ApiClient.getClient().create(Api.class);
        Call<FaqsJson> login = api.faquploadjson(faq,"Bearer "+strregisteredtoken);

        login.enqueue(new Callback<FaqsJson>() {
            @Override
            public void onResponse(Call<FaqsJson> call, Response<FaqsJson> response) {
                pProgressDialog.dismiss();
                Log.e("testing","status = "+response.body().getStatus());
                Log.e("testing","response = "+response.body().getResponse().getType());

                if (response.body().getStatus() == null || response.body().getStatus().length() == 0){

                }else if (response.body().getStatus().equals("success")) {
                    if (response.body().getResponse() == null ){

                    }else if (response.body().getResponse().getCode().equals("200")){
                        dialog.dismiss();
                        Toast.makeText(Activity_HelpDesk.this, "Uploaded Successfully", Toast.LENGTH_LONG).show();
                       /* Intent intent = new Intent(Activity_Profile.this, MainActivity.class);
                        startActivity(intent);
                        finish();*/


                    }else{
                        Toast.makeText(Activity_HelpDesk.this, "Something went wrong", Toast.LENGTH_LONG).show();
                    }





                   /*

                    Intent intent=new Intent(Activity_Event_Details.this,Activity_Event_Details.class);
                    startActivity(intent);
                    finish();

*/




                    //  Toast.makeText(Activity_Event_Details.this, message, Toast.LENGTH_SHORT).show();


                } else  {
                    Log.e("testing","error");
                    Toast.makeText(Activity_HelpDesk.this, "Something went wrong", Toast.LENGTH_LONG).show();
                }



            }

            @Override
            public void onFailure(Call<FaqsJson> call, Throwable t) {
                Toast.makeText(Activity_HelpDesk.this,t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                pProgressDialog.dismiss();

            }
        });





    }

}
