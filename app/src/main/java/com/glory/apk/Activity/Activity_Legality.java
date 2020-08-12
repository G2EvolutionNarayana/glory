package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.glory.apk.Model.TermsModel.Example;
import com.glory.apk.R;
import com.glory.apk.Retrofit.Api;
import com.glory.apk.Retrofit.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Activity_Legality extends AppCompatActivity {
    private ProgressDialog wallatedialog;
    private TextView texttitle,textdesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legality);

        ImageView imgcancel = (ImageView) findViewById(R.id.imgcancel);
         texttitle = (TextView) findViewById(R.id.texttitle);
        textdesc = (TextView) findViewById(R.id.textdesc);

        imgcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        String strdesc = "\u2022 Glory~5 offers fun features and services which is all considered as game of skills. We do not promote or encourage or support betting or gambling in any manner. This game of ours can be defined as a platform where individuals show their sports skills and knowledge by participating in any online fantasy game. They play a dominant role rather than mere luck of the individuals. Using their own skills, training, knowledge on sports help them in participation and winning. \n" +
//                "\n" +
//                "\u2022 Except certain states, Fantasy sports is considered to be legal all over the world. The users form their own team and are allocated points based on On-field performance of their chosen players which makes the fantasy sports entirely based on Game Of Skills. \n" +
//                "\n" +
//                "\u2022 The contest(s) and program(s) offered by GLORY~5 gives its users power to form their own fantasy team in which total of 5/7 (based on the contest choosed by the user) players are to be selected prior the match begins and then the formed team will be allotted points on the basis of the real life performance by the playing 5/7. The points are awarded as explained in point system page. The winner is decided on whose individual team has topped the contest. \n" +
//                "\n" +
//                "\u2022 GLORY~5 is completely legal as it is entirely dependent on skills, knowledge and attention of the users. \n" +
//                "\n" +
//                "\u2022 Except certain states such as Sikkim, Assam, Odisha, Telangana and Nagaland fantasy sports platforms are considered to be legal. The Public Gaming Act (“P.G.A, 1867”) is recognised to be the primary legality driving prevalence of gambling on India. \n" +
//                "\n" +
//                "\u2022 The keeping of common gaming house and the act of “public gambling” is defined as a criminal and punishable act in India by “The P.G.A”. The P.G.A provides an exception to fantasy sports and provisions and punishments of P.G.A shall not be applicable to users of GLORY~5 who use their skills and knowledge as primary tool. \n" +
//                "\n" +
//                "\u2022 The game of skills and its laws are unclear to some of the states in India. On this context the fantasy sports platforms are also unclear whether to provide the services to these states or not. ";
//
//
//        TextView textdesc = (TextView) findViewById(R.id.textdesc);
//        textdesc.setText(strdesc);
        Activity_Leagality();
    }
    private void Activity_Leagality() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");

        wallatedialog = new ProgressDialog(Activity_Legality.this);
        wallatedialog.setMessage("Please Wait ...");
//        wallatedialog.setIndeterminate(false);
        wallatedialog.setCancelable(false);
        wallatedialog.show();


        Api api = ApiClient.getClient().create(Api.class);
        Call<com.glory.apk.Model.TermsModel.Example> login = api.TermsCall("legality");
        login.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<com.glory.apk.Model.TermsModel.Example> call, Response<Example> response) {
                wallatedialog.dismiss();

                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    if (response.body().getResponse() == null) {

                    } else if (response.body().getResponse().getType().equals("data_found")) {

                        textdesc.setText(response.body().getData().get(0).getContentDescription());
                        texttitle.setText(response.body().getData().get(0).getContentTitle());

                    } else {
                        Toast.makeText(Activity_Legality.this, response.body().getResponse().getType(), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Log.e("testing", "error");
                    wallatedialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.body().getResponse().getType(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(getApplicationContext(), String.valueOf(t.getLocalizedMessage()), Toast.LENGTH_SHORT).show();
                wallatedialog.dismiss();

            }
        });
    }

}
