package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.glory.apk.Model.FinalPlayerSelectionModel;
import com.bumptech.glide.Glide;
import com.glory.apk.R;

import java.util.ArrayList;

public class GroundPreviewActivity7 extends AppCompatActivity {
    TextView xTvHitterPoints7, xTvPlayer2Points7, xTvPlayer3Points7, xTvPlayer4Points7, xTvPlayer5Points7, xTv6Points7, xTv7Points7;
    Button xBtnHitterName7, xBtnPlayer2Name7, xBtnPlayer3Name7, xBtnPlayer4Name7, xBtnPlayer5Name7, xBtnPlayer6Name, xBtnPlayer7Name;
    ImageView xMainHitter7, player2, player3, player4, xPlayer5, player6, player7,xIvCross7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ground_preview7);
        mInitWidgets();
    }

    private void mInitWidgets() {
         xIvCross7=(ImageView)findViewById(R.id.xIvCross7);

        xIvCross7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        xBtnHitterName7 = (Button) findViewById(R.id.xBtnHitterName7);
        xTvHitterPoints7 = (TextView) findViewById(R.id.xTvHitterPoints7);
        xMainHitter7 = (ImageView) findViewById(R.id.xMainHitter7);

        xBtnPlayer2Name7 = (Button) findViewById(R.id.xBtnPlayer2Name);
        xTvPlayer2Points7 = (TextView) findViewById(R.id.xTv2Points7);
        player2 = (ImageView) findViewById(R.id.player2);

        xBtnPlayer3Name7 = (Button) findViewById(R.id.xBtnPlayer3Name);
        xTvPlayer3Points7 = (TextView) findViewById(R.id.xTv3Points7);
        player3 = (ImageView) findViewById(R.id.player3);

        xBtnPlayer4Name7 = (Button) findViewById(R.id.xBtnPlayer4Name);
        xTvPlayer4Points7 = (TextView) findViewById(R.id.xTv4Points7);
        player4 = (ImageView) findViewById(R.id.player4);

        xBtnPlayer5Name7 = (Button) findViewById(R.id.xBtnPlayer5Name);
        xTvPlayer5Points7 = (TextView) findViewById(R.id.xTv5Points7);
        xPlayer5 = (ImageView) findViewById(R.id.player5);

        xBtnPlayer6Name = (Button) findViewById(R.id.xBtnPlayer6Name);
        xTv6Points7 = (TextView) findViewById(R.id.xTv6Points7);
        player6 = (ImageView) findViewById(R.id.player6);

        xBtnPlayer7Name = (Button) findViewById(R.id.xBtnPlayer7Name);
        xTv7Points7 = (TextView) findViewById(R.id.xTv7Points7);
        player7 = (ImageView) findViewById(R.id.player7);
        Bundle args = getIntent().getBundleExtra("BUNDLE");
        ArrayList<FinalPlayerSelectionModel> object = (ArrayList<FinalPlayerSelectionModel>) args.getSerializable("ARRAYLIST");

        for (int i = 0; i < object.size(); i++) {
            FinalPlayerSelectionModel homePlayersSelected = object.get(i);

            if (i == 0) {
                xTvHitterPoints7.setText("0");
                xBtnHitterName7.setText(homePlayersSelected.getName());
                if (homePlayersSelected.getImage() == null || homePlayersSelected.getImage().toString().length() == 0) {
                    Glide.with(getApplicationContext())
                            .load((R.drawable.player)).placeholder(R.drawable.player)
                            .into(xMainHitter7);
                    Log.e("testing", "getImageUrl = " + "null");

                } else {
                    Glide.with(getApplicationContext())
                            .load(Uri.parse(homePlayersSelected.getImage().toString()))
                            .error(R.drawable.player)
                            .into(xMainHitter7);
                    Log.e("testing", "getImageUrl = " + "image");

                }
            } else if (i == 1) {
                xTvPlayer2Points7.setText("0");
                xBtnPlayer2Name7.setText(homePlayersSelected.getName());
                if (homePlayersSelected.getImage() == null ||homePlayersSelected.getImage().toString().length() == 0) {
                    Glide.with(getApplicationContext())
                            .load((R.drawable.player)).placeholder(R.drawable.player)
                            .into(player2);
                    Log.e("testing", "getImageUrl = " + "null");

                } else {
                    Glide.with(getApplicationContext())
                            .load(Uri.parse(homePlayersSelected.getImage().toString()))
                            .error(R.drawable.player)
                            .into(player2);
                    Log.e("testing", "getImageUrl = " + "image");

                }
            } else if (i == 2) {
                xTvPlayer3Points7.setText("0");
                xBtnPlayer3Name7.setText(homePlayersSelected.getName());
                if (homePlayersSelected.getImage() == null || homePlayersSelected.getImage().toString().length() == 0) {
                    Glide.with(getApplicationContext())
                            .load((R.drawable.player)).placeholder(R.drawable.player)
                            .into(player3);
                    Log.e("testing", "getImageUrl = " + "null");

                } else {
                    Glide.with(getApplicationContext())
                            .load(Uri.parse(homePlayersSelected.getImage().toString()))
                            .error(R.drawable.player)
                            .into(player3);
                    Log.e("testing", "getImageUrl = " + "image");

                }
            } else if (i == 3) {
                xTvPlayer4Points7.setText("0");
                xBtnPlayer4Name7.setText(homePlayersSelected.getName());
                if (homePlayersSelected.getImage() == null || homePlayersSelected.getImage().toString().length() == 0) {
                    Glide.with(getApplicationContext())
                            .load((R.drawable.player)).placeholder(R.drawable.player)
                            .into(player4);
                    Log.e("testing", "getImageUrl = " + "null");

                } else {
                    Glide.with(getApplicationContext())
                            .load(Uri.parse(homePlayersSelected.getImage().toString()))
                            .error(R.drawable.player)
                            .into(player4);
                    Log.e("testing", "getImageUrl = " + "image");

                }
            } else if (i == 4) {
                xTvPlayer5Points7.setText("0");
                xBtnPlayer5Name7.setText(homePlayersSelected.getName());
                if (homePlayersSelected.getImage() == null || homePlayersSelected.getImage().toString().length() == 0) {
                    Glide.with(getApplicationContext())
                            .load((R.drawable.player)).placeholder(R.drawable.player)
                            .into(xPlayer5);
                    Log.e("testing", "getImageUrl = " + "null");

                } else {
                    Glide.with(getApplicationContext())
                            .load(Uri.parse(homePlayersSelected.getImage().toString()))
                            .error(R.drawable.player)
                            .into(xPlayer5);
                    Log.e("testing", "getImageUrl = " + "image");

                }
            } else if (i == 5) {
                xTv6Points7.setText("0");
                xBtnPlayer6Name.setText(homePlayersSelected.getName());
                if (homePlayersSelected.getImage()== null || homePlayersSelected.getImage().toString().length() == 0) {
                    Glide.with(getApplicationContext())
                            .load((R.drawable.player)).placeholder(R.drawable.player)
                            .into(player6);
                    Log.e("testing", "getImageUrl = " + "null");

                } else {
                    Glide.with(getApplicationContext())
                            .load(Uri.parse(homePlayersSelected.getImage().toString()))
                            .error(R.drawable.player)
                            .into(player6);
                    Log.e("testing", "getImageUrl = " + "image");

                }
            } else if (i == 6) {
                xTv7Points7.setText("0");
                xBtnPlayer7Name.setText(homePlayersSelected.getName());
                if (homePlayersSelected.getImage() == null || homePlayersSelected.getImage().toString().length() == 0) {
                    Glide.with(getApplicationContext())
                            .load((R.drawable.player)).placeholder(R.drawable.player)
                            .into(player7);
                    Log.e("testing", "getImageUrl = " + "null");

                } else {
                    Glide.with(getApplicationContext())
                            .load(Uri.parse(homePlayersSelected.getImage().toString()))
                            .error(R.drawable.player)
                            .into(player7);
                    Log.e("testing", "getImageUrl = " + "image");

                }
            }
        }
    }
}
