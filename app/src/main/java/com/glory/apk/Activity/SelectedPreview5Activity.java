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
import com.glory.apk.R;
import com.glory.apk.Utilites.StaticUtils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SelectedPreview5Activity extends AppCompatActivity {
    TextView xTvHitterPoints, xTvPlayer2Points, xTvPlayer3Points, xTvPlayer4Points, xTvPlayer5Points;
    Button xBtnHitterName, xBtnPlayer2Name, xBtnPlayer3Name, xBtnPlayer4Name, xBtnPlayer5Name;
    ImageView xMainHitter, player2, player3, player4, xPlayer5,xIvPowerCross;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_preview5);
        mInitWidgets();
    }

    private void mInitWidgets() {
         xIvPowerCross=(ImageView)findViewById(R.id.xIvPowerCross);

        xIvPowerCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

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

        Bundle args = getIntent().getBundleExtra("BUNDLE");
        xBtnHitterName.setText(args.getString(StaticUtils.HITTER_NAME));
//        Log.e("testing",args.getString(StaticUtils.HITTER_IMAGE));
        if (args.getString(StaticUtils.HITTER_IMAGE)== null || args.getString(StaticUtils.HITTER_IMAGE).length() == 0) {
            Glide.with(getApplicationContext())
                    .load((R.drawable.player)).placeholder(R.drawable.player)
                    .into(xMainHitter);
            Log.e("testing", "getImageUrl = " + "null");

        } else {
            Glide.with(getApplicationContext())
                    .load(Uri.parse(args.getString(StaticUtils.HITTER_IMAGE).toString()))
                    .error(R.drawable.player)
                    .into(xMainHitter);
            Log.e("testing", "getImageUrl = " + "image");

        }
        ArrayList<FinalPlayerSelectionModel> object = (ArrayList<FinalPlayerSelectionModel>) args.getSerializable("ARRAYLIST");

        for (int i = 0; i < object.size(); i++) {
            FinalPlayerSelectionModel homePlayersSelected = object.get(i);
//            previewPlayers.add(new PreviewModel(homePlayersSelected.getName(), homePlayersSelected.getImage(), homePlayersSelected.getId()));


           if (i == 0) {
                xTvPlayer2Points.setText("0");
                xBtnPlayer2Name.setText(homePlayersSelected.getName());
                if (homePlayersSelected.getImage() == null || homePlayersSelected.getImage().toString().length() == 0) {
                    Glide.with(getApplicationContext())
                            .load((R.drawable.player)).placeholder(R.drawable.player)
                            .into(player2);
                    Log.e("testing", "getImageUrl = " + "null");

                } else {
                    Glide.with(getApplicationContext())
                            .load(Uri.parse(homePlayersSelected.getImage()))
                            .error(R.drawable.player)
                            .into(player2);
                    Log.e("testing", "getImageUrl = " + "image");

                }
            } else if (i == 1) {
                xTvPlayer3Points.setText("0");
                xBtnPlayer3Name.setText(homePlayersSelected.getName());
                if (homePlayersSelected.getImage() == null || homePlayersSelected.getImage().toString().length() == 0) {
                    Glide.with(getApplicationContext())
                            .load((R.drawable.player)).placeholder(R.drawable.player)
                            .into(player3);
                    Log.e("testing", "getImageUrl = " + "null");

                } else {
                    Glide.with(getApplicationContext())
                            .load(Uri.parse(homePlayersSelected.getImage()))
                            .error(R.drawable.player)
                            .into(player3);
                    Log.e("testing", "getImageUrl = " + "image");

                }
            } else if (i == 2) {
                xTvPlayer4Points.setText("0");
                xBtnPlayer4Name.setText(homePlayersSelected.getName());
                if (homePlayersSelected.getImage()== null || homePlayersSelected.getImage().toString().length() == 0) {
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
            } else if (i == 3) {
                xTvPlayer5Points.setText("0");
                xBtnPlayer5Name.setText(homePlayersSelected.getName());
                if (homePlayersSelected.getImage()== null || homePlayersSelected.getImage().toString().length() == 0) {
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
            }
        }
    }
}
