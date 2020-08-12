package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.glory.apk.Model.LivePointsDataModel.LivePointsDatum;
import com.glory.apk.Model.LivePointsDataModel.LivePointsExample;
import com.glory.apk.R;
import com.glory.apk.Retrofit.Api;
import com.glory.apk.Retrofit.ApiClient;
import com.glory.apk.Utilites.StaticUtils;
import com.glory.apk.Utilites.sharedPrefs;
import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LivePreview7MainActivity extends AppCompatActivity {

    TextView xTvHiiterPoints77, xTvPlayer2Points7, xTvPlayer3Points7, xTvPlayer4Points7, xTvPlayer5Points7,xTvPlayer6Points7,xTvPlayer7Points7,xTvTotalPoints,xTvTeamName;
    Button xBtnHitterName7, xBtnPlayer2Name7, xBtnPlayer3Name7, xBtnPlayer4Name7, xBtnPlayer5Name7,xBtnPlayer6Name7,xBtnPlayer7Name7;
    ImageView xMainHitter7, player27, player37, player47, xPlayer57,xPlayer67,xPlayer77;
    ImageView xIvCross7,xIv7Refress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_preview7_main);
        mInitWidgets();
        mCallContestList();
    }
    private void mInitWidgets() {
        xTvTotalPoints = (TextView) findViewById(R.id.xTvTotalPoints);
        xTvTotalPoints.setText(getIntent().getStringExtra(StaticUtils.TOTAL_POINTS));
        xIvCross7=(ImageView)findViewById(R.id.xIvCross7);
        xBtnHitterName7 = (Button) findViewById(R.id.xBtnHitterName7);
        xTvHiiterPoints77 = (TextView) findViewById(R.id.xTvHiiterPoints77);
        xMainHitter7 = (ImageView) findViewById(R.id.xMainHitter7);

        xBtnPlayer2Name7 = (Button) findViewById(R.id.xBtnPlayer2Name7);
        xTvPlayer2Points7 = (TextView) findViewById(R.id.xTvPlayer2points7);
        player27 = (ImageView) findViewById(R.id.player27);

        xBtnPlayer3Name7 = (Button) findViewById(R.id.xBtnPlayer3Name7);
        xTvPlayer3Points7 = (TextView) findViewById(R.id.xTvPlayer3points7);
        player37 = (ImageView) findViewById(R.id.player37);

        xBtnPlayer4Name7 = (Button) findViewById(R.id.xBtnPlayer4Name7);
        xTvPlayer4Points7 = (TextView) findViewById(R.id.xTvPlayer4points7);
        player47 = (ImageView) findViewById(R.id.player47);

        xBtnPlayer5Name7 = (Button) findViewById(R.id.xBtnPlayer5Name7);
        xTvPlayer5Points7 = (TextView) findViewById(R.id.xTvPlayer5points7);
        xPlayer57 = (ImageView) findViewById(R.id.player57);

        xBtnPlayer6Name7 = (Button) findViewById(R.id.xBtnPlayer6Name7);
        xTvPlayer6Points7 = (TextView) findViewById(R.id.xTvPlayer6points7);
        xPlayer67 = (ImageView) findViewById(R.id.player67);

        xBtnPlayer7Name7 = (Button) findViewById(R.id.xBtnPlayer7Name7);
        xTvPlayer7Points7 = (TextView) findViewById(R.id.xTvPlayer7points7);
        xPlayer77 = (ImageView) findViewById(R.id.player77);
        xIv7Refress=(ImageView)findViewById(R.id.xIv7Refress);
        xTvTeamName=(TextView)findViewById(R.id.xTvTeamName);

        xTvTeamName.setText(getIntent().getStringExtra("TeamName"));

        if (getIntent().getStringExtra("ScreenName").equals("LiveAdapter"))
            xIv7Refress.setVisibility(View.VISIBLE);
        else  xIv7Refress.setVisibility(View.GONE);

        xIvCross7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        xIv7Refress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallContestList();

            }
        });


    }

    private void mCallContestList() {
        Log.e("testing", "strregisteredtoken = " + "matchesList");
        final ProgressDialog pDialog;

        pDialog = new ProgressDialog(LivePreview7MainActivity.this);
        pDialog.setMessage("Please Wait ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        String viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);
        String ContestUserId=getIntent().getStringExtra(StaticUtils.CONTEST_USERID);
        Log.e("testing","ContestUserId = "+ContestUserId);
        String matchId=getIntent().getStringExtra("matchId");
        Log.e("testing","matchId = "+matchId);
        Api api = ApiClient.getClient().create(Api.class);

        Call<LivePointsExample> login = api.LivePlayerPoints(ContestUserId,matchId);
        login.enqueue(new Callback<LivePointsExample>() {
            @Override
            public void onResponse(Call<LivePointsExample> call, Response<LivePointsExample> response) {
                pDialog.dismiss();
                Log.e("testing", "status = " + response.body().getStatus());
                Log.e("testing", "response = " + response.body().getLivePointsResponse().getMessage());
                //  Log.e("testing","response = "+response.body().getData().getPageContent());
                Log.e("testing", "response = " + response.body());
                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    if (response.body().getLivePointsFilters() == null) {

                    } else if (response.body().getLivePointsResponse().getType().equals("data_found")) {
                        List<LivePointsDatum> list = response.body().getData();
                        for (int j = 0; j < list.size(); j++) {
                            LivePointsDatum playerListDatum = list.get(j);

                            if (j == 0) {
                                if (!playerListDatum.getLivePointsPlayerScore().getPowerHitterPoints().isEmpty())
                                xTvHiiterPoints77.setText(playerListDatum.getLivePointsPlayerScore().getPowerHitterPoints());

                                else
                                    xTvHiiterPoints77.setText("0");
//                                xTvTotalPoints.setText(String.valueOf());

                                xBtnHitterName7.setText(playerListDatum.getLivePointsPlayer().getFullName());
                                if (playerListDatum.getLivePointsPlayer().getImageURL() == null || playerListDatum.getLivePointsPlayer().getImageURL().toString().length() == 0) {
                                    Glide.with(getApplicationContext())
                                            .load((R.drawable.player)).placeholder(R.drawable.player)
                                            .into(xMainHitter7);
                                    Log.e("testing", "getImageUrl = " + "null");

                                } else {
                                    Glide.with(getApplicationContext())
                                            .load(Uri.parse( playerListDatum.getLivePointsPlayer().getImageURL().toString()))
                                            .error(R.drawable.player)
                                            .into(xMainHitter7);
                                    Log.e("testing", "getImageUrl = " + "image");

                                }
                            } else if (j == 1) {
                                if (!playerListDatum.getLivePointsPlayerScore().getNormalPlayerPoints().isEmpty())
                                    xTvPlayer2Points7.setText(playerListDatum.getLivePointsPlayerScore().getNormalPlayerPoints());
                                else
                                    xTvPlayer2Points7.setText("0");

                                xBtnPlayer2Name7.setText(playerListDatum.getLivePointsPlayer().getFullName());
                                if (playerListDatum.getLivePointsPlayer().getImageURL() == null || playerListDatum.getLivePointsPlayer().getImageURL().toString().length() == 0) {
                                    Glide.with(getApplicationContext())
                                            .load((R.drawable.player)).placeholder(R.drawable.player)
                                            .into(player27);
                                    Log.e("testing", "getImageUrl = " + "null");

                                } else {
                                    Glide.with(getApplicationContext())
                                            .load(Uri.parse(playerListDatum.getLivePointsPlayer().getImageURL().toString()))
                                            .error(R.drawable.player)
                                            .into(player27);
                                    Log.e("testing", "getImageUrl = " + "image");

                                }
                            } else if (j == 2) {

                                if (!playerListDatum.getLivePointsPlayerScore().getNormalPlayerPoints().isEmpty())
                                    xTvPlayer3Points7.setText(playerListDatum.getLivePointsPlayerScore().getNormalPlayerPoints());
                                else
                                    xTvPlayer3Points7.setText("0");
                                xBtnPlayer3Name7.setText(playerListDatum.getLivePointsPlayer().getFullName());
                                if (playerListDatum.getLivePointsPlayer().getImageURL()== null || playerListDatum.getLivePointsPlayer().getImageURL().toString().length() == 0) {
                                    Glide.with(getApplicationContext())
                                            .load((R.drawable.player)).placeholder(R.drawable.player)
                                            .into(player37);
                                    Log.e("testing", "getImageUrl = " + "null");

                                } else {
                                    Glide.with(getApplicationContext())
                                            .load(Uri.parse(playerListDatum.getLivePointsPlayer().getImageURL().toString()))
                                            .error(R.drawable.player)
                                            .into(player37);
                                    Log.e("testing", "getImageUrl = " + "image");

                                }
                            } else if (j == 3) {


                                if (!playerListDatum.getLivePointsPlayerScore().getNormalPlayerPoints().isEmpty())
                                    xTvPlayer4Points7.setText(playerListDatum.getLivePointsPlayerScore().getNormalPlayerPoints());
                                else
                                    xTvPlayer4Points7.setText("0");
                                xBtnPlayer4Name7.setText(playerListDatum.getLivePointsPlayer().getFullName());
                                if (playerListDatum.getLivePointsPlayer().getImageURL() == null || playerListDatum.getLivePointsPlayer().getImageURL().toString().length() == 0) {
                                    Glide.with(getApplicationContext())
                                            .load((R.drawable.player)).placeholder(R.drawable.player)
                                            .into(player47);
                                    Log.e("testing", "getImageUrl = " + "null");

                                } else {
                                    Glide.with(getApplicationContext())
                                            .load(Uri.parse(playerListDatum.getLivePointsPlayer().getImageURL().toString()))
                                            .error(R.drawable.player)
                                            .into(player47);
                                    Log.e("testing", "getImageUrl = " + "image");

                                }
                            } else if (j == 4) {
                                if (!playerListDatum.getLivePointsPlayerScore().getNormalPlayerPoints().isEmpty())
                                    xTvPlayer5Points7.setText(playerListDatum.getLivePointsPlayerScore().getNormalPlayerPoints());
                                else
                                    xTvPlayer5Points7.setText("0");
                                xBtnPlayer5Name7.setText(playerListDatum.getLivePointsPlayer().getFullName());
                                if (playerListDatum.getLivePointsPlayer().getImageURL() == null ||playerListDatum.getLivePointsPlayer().getImageURL().toString().length() == 0) {
                                    Glide.with(getApplicationContext())
                                            .load((R.drawable.player)).placeholder(R.drawable.player)
                                            .into(xPlayer57);
                                    Log.e("testing", "getImageUrl = " + "null");

                                } else {
                                    Glide.with(getApplicationContext())
                                            .load(Uri.parse(playerListDatum.getLivePointsPlayer().getImageURL().toString()))
                                            .error(R.drawable.player)
                                            .into(xPlayer57);
                                    Log.e("testing", "getImageUrl = " + "image");

                                }
                            }else if (j == 5) {
                                if (!playerListDatum.getLivePointsPlayerScore().getNormalPlayerPoints().isEmpty())
                                    xTvPlayer6Points7.setText(playerListDatum.getLivePointsPlayerScore().getNormalPlayerPoints());
                                else
                                    xTvPlayer6Points7.setText("0");
                                xBtnPlayer6Name7.setText(playerListDatum.getLivePointsPlayer().getFullName());
                                if (playerListDatum.getLivePointsPlayer().getImageURL() == null ||playerListDatum.getLivePointsPlayer().getImageURL().toString().length() == 0) {
                                    Glide.with(getApplicationContext())
                                            .load((R.drawable.player)).placeholder(R.drawable.player)
                                            .into(xPlayer67);
                                    Log.e("testing", "getImageUrl = " + "null");

                                } else {
                                    Glide.with(getApplicationContext())
                                            .load(Uri.parse(playerListDatum.getLivePointsPlayer().getImageURL().toString()))
                                            .error(R.drawable.player)
                                            .into(xPlayer67);
                                    Log.e("testing", "getImageUrl = " + "image");

                                }
                            }else if (j == 6) {
                                if (!playerListDatum.getLivePointsPlayerScore().getNormalPlayerPoints().isEmpty())
                                    xTvPlayer7Points7.setText(playerListDatum.getLivePointsPlayerScore().getNormalPlayerPoints());
                                else
                                    xTvPlayer7Points7.setText("0");
                                xBtnPlayer7Name7.setText(playerListDatum.getLivePointsPlayer().getFullName());
                                if (playerListDatum.getLivePointsPlayer().getImageURL() == null ||playerListDatum.getLivePointsPlayer().getImageURL().toString().length() == 0) {
                                    Glide.with(getApplicationContext())
                                            .load((R.drawable.player)).placeholder(R.drawable.player)
                                            .into(xPlayer77);
                                    Log.e("testing", "getImageUrl = " + "null");

                                } else {
                                    Glide.with(getApplicationContext())
                                            .load(Uri.parse(playerListDatum.getLivePointsPlayer().getImageURL().toString()))
                                            .error(R.drawable.player)
                                            .into(xPlayer77);
                                    Log.e("testing", "getImageUrl = " + "image");

                                }
                            }




                        }
                    }
                    pDialog.dismiss();

                } else {
                    Toast.makeText(getApplicationContext(), response.body().getLivePointsResponse().getType(), Toast.LENGTH_SHORT).show();
                    pDialog.dismiss();

                }

            }



            @Override
            public void onFailure(Call<LivePointsExample> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                pDialog.dismiss();
            }
        });

    }
}