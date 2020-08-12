package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.glory.apk.R;


public class CompletedPreviewActivity extends AppCompatActivity {
    ProgressDialog pDialog;
    TextView xTvHitterPoints, xTvPlayer2Points, xTvPlayer3Points, xTvPlayer4Points, xTvPlayer5Points;
    Button xBtnHitterName, xBtnPlayer2Name, xBtnPlayer3Name, xBtnPlayer4Name, xBtnPlayer5Name;
    ImageView xMainHitter, player2, player3, player4, xPlayer5;
    ImageView xIvCross;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_preview);
        mInitWidgets();
//        mCallContestList();
    }

    private void mInitWidgets() {
        xIvCross = (ImageView) findViewById(R.id.xIvCross);
        xBtnHitterName = (Button) findViewById(R.id.xBtnHitterName);
        xTvHitterPoints = (TextView) findViewById(R.id.xTvHitterPoints);
        xMainHitter = (ImageView) findViewById(R.id.xMainHitter);

        xBtnPlayer2Name = (Button) findViewById(R.id.xBtnPlayer2Name);
        xTvPlayer2Points = (TextView) findViewById(R.id.xTvPlayer2Points);
        player2 = (ImageView) findViewById(R.id.player2);

        xBtnPlayer3Name = (Button) findViewById(R.id.xBtnPlayer3Name);
        xTvPlayer3Points = (TextView) findViewById(R.id.xTvPlayer3Points);
        player3 = (ImageView) findViewById(R.id.player3);

        xBtnPlayer4Name = (Button) findViewById(R.id.xBtnPlayer4Name);
        xTvPlayer4Points = (TextView) findViewById(R.id.xTvPlayer4Points);
        player4 = (ImageView) findViewById(R.id.player4);

        xBtnPlayer5Name = (Button) findViewById(R.id.xBtnPlayer5Name);
        xTvPlayer5Points = (TextView) findViewById(R.id.xTvPlayer5Points);
        xPlayer5 = (ImageView) findViewById(R.id.xPlayer5);

        xIvCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

//    private void mCallContestList() {
//        Log.e("testing", "strregisteredtoken = " + "matchesList");
//        pDialog = new ProgressDialog(CompletedPreviewActivity.this);
//        pDialog.setMessage("Please Wait ...");
//        pDialog.setIndeterminate(false);
//        pDialog.setCancelable(false);
//        pDialog.show();
//        String viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);
//
//        Api api = ApiClient.getClient().create(Api.class);
//
//        Call<LivePointsExample> login = api.LivePlayerPoints("52", "3");
//        login.enqueue(new Callback<LivePointsExample>() {
//            @Override
//            public void onResponse(Call<LivePointsExample> call, Response<LivePointsExample> response) {
//                pDialog.dismiss();
//                Log.e("testing", "status = " + response.body().getStatus());
//                Log.e("testing", "response = " + response.body().getLivePointsResponse().getMessage());
//                //  Log.e("testing","response = "+response.body().getData().getPageContent());
//                Log.e("testing", "response = " + response.body());
//                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {
//
//                } else if (response.body().getStatus().equals("success")) {
//                    if (response.body().getLivePointsFilters() == null) {
//
//                    } else if (response.body().getLivePointsResponse().getType().equals("data_found")) {
//                        List<LivePointsDatum> list = response.body().getData();
//                        for (int j = 0; j < list.size(); j++) {
//                            LivePointsDatum playerListDatum = list.get(j);
//
//                            if (j == 0) {
//                                if (!playerListDatum.getLivePointsPlayerScore().getPowerHitterPoints().isEmpty())
//                                    xTvHitterPoints.setText(playerListDatum.getLivePointsPlayerScore().getPowerHitterPoints());
//                                else
//                                    xTvHitterPoints.setText("0");
//                                xBtnHitterName.setText(playerListDatum.getLivePointsPlayer().getFirstName());
//                                if (playerListDatum.getLivePointsPlayer().getImageURL() == null || playerListDatum.getLivePointsPlayer().getImageURL().toString().length() == 0) {
//                                    Glide.with(getApplicationContext())
//                                            .load((R.drawable.player)).placeholder(R.drawable.player)
//                                            .into(xMainHitter);
//                                    Log.e("testing", "getImageUrl = " + "null");
//
//                                } else {
//                                    Glide.with(getApplicationContext())
//                                            .load(Uri.parse(playerListDatum.getLivePointsPlayer().getImageURL().toString()))
//                                            .error(R.drawable.icon1)
//                                            .into(xMainHitter);
//                                    Log.e("testing", "getImageUrl = " + "image");
//
//                                }
//                            } else if (j == 1) {
//                                if (!playerListDatum.getLivePointsPlayerScore().getNormalPlayerPoints().isEmpty())
//                                    xTvPlayer2Points.setText(playerListDatum.getLivePointsPlayerScore().getNormalPlayerPoints());
//                                else
//                                    xTvPlayer2Points.setText("0");
//
//                                xBtnPlayer2Name.setText(playerListDatum.getLivePointsPlayer().getFirstName());
//                                if (playerListDatum.getLivePointsPlayer().getImageURL() == null || playerListDatum.getLivePointsPlayer().getImageURL().toString().length() == 0) {
//                                    Glide.with(getApplicationContext())
//                                            .load((R.drawable.player)).placeholder(R.drawable.player)
//                                            .into(xMainHitter);
//                                    Log.e("testing", "getImageUrl = " + "null");
//
//                                } else {
//                                    Glide.with(getApplicationContext())
//                                            .load(Uri.parse(playerListDatum.getLivePointsPlayer().getImageURL().toString()))
//                                            .error(R.drawable.icon1)
//                                            .into(xMainHitter);
//                                    Log.e("testing", "getImageUrl = " + "image");
//
//                                }
//                            } else if (j == 2) {
//
//                                if (!playerListDatum.getLivePointsPlayerScore().getNormalPlayerPoints().isEmpty())
//                                    xTvPlayer3Points.setText(playerListDatum.getLivePointsPlayerScore().getNormalPlayerPoints());
//                                else
//                                    xTvPlayer3Points.setText("0");
//                                xBtnPlayer3Name.setText(playerListDatum.getLivePointsPlayer().getFirstName());
//                                if (playerListDatum.getLivePointsPlayer().getImageURL() == null || playerListDatum.getLivePointsPlayer().getImageURL().toString().length() == 0) {
//                                    Glide.with(getApplicationContext())
//                                            .load((R.drawable.player)).placeholder(R.drawable.player)
//                                            .into(xMainHitter);
//                                    Log.e("testing", "getImageUrl = " + "null");
//
//                                } else {
//                                    Glide.with(getApplicationContext())
//                                            .load(Uri.parse(playerListDatum.getLivePointsPlayer().getImageURL().toString()))
//                                            .error(R.drawable.icon1)
//                                            .into(xMainHitter);
//                                    Log.e("testing", "getImageUrl = " + "image");
//
//                                }
//                            } else if (j == 3) {
//
//
//                                if (!playerListDatum.getLivePointsPlayerScore().getNormalPlayerPoints().isEmpty())
//                                    xTvPlayer4Points.setText(playerListDatum.getLivePointsPlayerScore().getNormalPlayerPoints());
//                                else
//                                    xTvPlayer4Points.setText("0");
//                                xBtnPlayer4Name.setText(playerListDatum.getLivePointsPlayer().getFirstName());
//                                if (playerListDatum.getLivePointsPlayer().getImageURL() == null || playerListDatum.getLivePointsPlayer().getImageURL().toString().length() == 0) {
//                                    Glide.with(getApplicationContext())
//                                            .load((R.drawable.player)).placeholder(R.drawable.player)
//                                            .into(xMainHitter);
//                                    Log.e("testing", "getImageUrl = " + "null");
//
//                                } else {
//                                    Glide.with(getApplicationContext())
//                                            .load(Uri.parse(playerListDatum.getLivePointsPlayer().getImageURL().toString()))
//                                            .error(R.drawable.icon1)
//                                            .into(xMainHitter);
//                                    Log.e("testing", "getImageUrl = " + "image");
//
//                                }
//                            } else if (j == 4) {
//                                if (!playerListDatum.getLivePointsPlayerScore().getNormalPlayerPoints().isEmpty())
//                                    xTvPlayer5Points.setText(playerListDatum.getLivePointsPlayerScore().getNormalPlayerPoints());
//                                else
//                                    xTvPlayer5Points.setText("0");
//                                xBtnPlayer5Name.setText(playerListDatum.getLivePointsPlayer().getFirstName());
//                                if (playerListDatum.getLivePointsPlayer().getImageURL() == null || playerListDatum.getLivePointsPlayer().getImageURL().toString().length() == 0) {
//                                    Glide.with(getApplicationContext())
//                                            .load((R.drawable.player)).placeholder(R.drawable.player)
//                                            .into(xMainHitter);
//                                    Log.e("testing", "getImageUrl = " + "null");
//
//                                } else {
//                                    Glide.with(getApplicationContext())
//                                            .load(Uri.parse(playerListDatum.getLivePointsPlayer().getImageURL().toString()))
//                                            .error(R.drawable.icon1)
//                                            .into(xMainHitter);
//                                    Log.e("testing", "getImageUrl = " + "image");
//
//                                }
//                            }
//
//
//                        }
//                    }
//                    pDialog.dismiss();
//
//                } else {
//                    Toast.makeText(getApplicationContext(), response.body().getLivePointsResponse().getType(), Toast.LENGTH_SHORT).show();
//                    pDialog.dismiss();
//
//                }
//
//            }
//
//
//            @Override
//            public void onFailure(Call<LivePointsExample> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                pDialog.dismiss();
//            }
//        });
//
//    }
}