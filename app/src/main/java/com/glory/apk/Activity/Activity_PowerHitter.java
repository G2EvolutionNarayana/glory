package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.glory.apk.Adapter.PowerHitterAdpter;
import com.glory.apk.Model.FinalPlayerSelectionModel;
import com.glory.apk.Model.PowerHitterModel;
import com.glory.apk.R;
import com.glory.apk.Utilites.StaticUtils;
import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;

public class Activity_PowerHitter extends AppCompatActivity implements PowerHitterAdpter.OnItemClick {

    private PowerHitterAdpter powerHitterAdpter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    public ArrayList<PowerHitterModel> powerHitterList;
    public ArrayList<PowerHitterModel> powerHitterList2;

    ImageView XIvHome, xIvOppose;
    TextView xTvHomeTeamName, xTvOpposTeamName, xTvHomeCount, xTvOppositeCount;
    Button contin, button3;
    String hitterId;
    String hitterName, hitterImage;
    PowerHitterAdpter.OnItemClick mCallBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_hitter);
        PowerHitterAdpter.count = -1;
        powerHitterList = new ArrayList<>();
        powerHitterList2 = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.rv_hitter);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        Bundle args = getIntent().getBundleExtra("BUNDLE");
        mCallBack = this;
        hitterName = "";

        XIvHome = (ImageView) findViewById(R.id.XIvHome);
        xIvOppose = (ImageView) findViewById(R.id.xIvOppose);
        xTvHomeTeamName = (TextView) findViewById(R.id.xTvHomeTeamName);
        xTvOpposTeamName = (TextView) findViewById(R.id.xTvOpposTeamName);
        xTvHomeTeamName.setText(args.getString(StaticUtils.HOME_TEAM_SHORT_NAME));
        xTvOpposTeamName.setText(args.getString(StaticUtils.OPPOS_TEAM_SHORT_NAME));
        xTvHomeCount = (TextView) findViewById(R.id.xTvHomeCount);
        xTvOppositeCount = (TextView) findViewById(R.id.xTvOppositeCount);
        xTvHomeCount.setText(args.getString(StaticUtils.HOME_TEAM_COUNT));
        xTvOppositeCount.setText(args.getString(StaticUtils.OPPOSITE_TEAM_COUNT));
        button3 = (Button) findViewById(R.id.button3);

        if (args.getString(StaticUtils.HOME_FLAG) == null || args.getString(StaticUtils.HOME_FLAG).length() == 0) {
            Glide.with(getApplicationContext())
                    .load((R.drawable.default_team_logo)).placeholder(R.drawable.default_team_logo)
                    .into(XIvHome);
            Log.e("testing", "getImageUrl = " + "null");

        } else {
            Glide.with(getApplicationContext())
                    .load(Uri.parse(args.getString(StaticUtils.HOME_FLAG)))
                    .error(R.drawable.default_team_logo)
                    .into(XIvHome);
            Log.e("testing", "getImageUrl = " + "image");
        }
        if (args.getString(StaticUtils.OPPOS_FLAG) == null || args.getString(StaticUtils.OPPOS_FLAG).length() == 0) {
            Glide.with(getApplicationContext())
                    .load((R.drawable.default_team_logo)).placeholder(R.drawable.default_team_logo)
                    .into(xIvOppose);
            Log.e("testing", "getImageUrl = " + "null");

        } else {
            Glide.with(getApplicationContext())
                    .load(Uri.parse(args.getString(StaticUtils.OPPOS_FLAG)))
                    .error(R.drawable.default_team_logo)
                    .into(xIvOppose);
            Log.e("testing", "getImageUrl = " + "image");
        }
        Log.e("testing", "homeFlag =" + args.getString(StaticUtils.HOME_FLAG));
        ArrayList<FinalPlayerSelectionModel> object = (ArrayList<FinalPlayerSelectionModel>) args.getSerializable("ARRAYLIST");
        ArrayList<FinalPlayerSelectionModel> object2 = (ArrayList<FinalPlayerSelectionModel>) args.getSerializable("ARRAYLIST");

        for (int i = 0; i < object.size(); i++) {
            FinalPlayerSelectionModel homePlayersSelected = object.get(i);
            powerHitterList.add(new PowerHitterModel(homePlayersSelected.getName(), homePlayersSelected.getImage(), homePlayersSelected.getId(), false,homePlayersSelected.getPlayerType()));
        }
        powerHitterAdpter = new PowerHitterAdpter(getApplicationContext(), powerHitterList, mCallBack);
        recyclerView.setAdapter(powerHitterAdpter);
        contin = (Button) findViewById(R.id.contin);
        contin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (hitterName.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please select the Hitter", Toast.LENGTH_LONG).show();

                }else {
                    for (int i = 0; i < powerHitterList.size(); i++) {
                        PowerHitterModel powerHitterModel = powerHitterList.get(i);
                        if (powerHitterModel.getPowerHitter()) {
                            Intent intent = new Intent(Activity_PowerHitter.this, Activity_Contest.class);
                            Bundle args = new Bundle();
                            args.putSerializable("ARRAYLIST", (Serializable) object);
                            intent.putExtra("BUNDLE", args);
                            intent.putExtra(StaticUtils.MATCH_ID, getIntent().getStringExtra(StaticUtils.MATCH_ID));
                            intent.putExtra(StaticUtils.PACKAGE_ID, getIntent().getStringExtra(StaticUtils.PACKAGE_ID));
                            intent.putExtra(StaticUtils.HITTERID, hitterId);
                            args.putString(StaticUtils.HOME_FLAG, getIntent().getStringExtra(StaticUtils.HOME_FLAG));
                            args.putString(StaticUtils.OPPOS_FLAG, getIntent().getStringExtra(StaticUtils.OPPOS_FLAG));
                            args.putString(StaticUtils.HOME_TEAM_SHORT_NAME, getIntent().getStringExtra(StaticUtils.HOME_TEAM_SHORT_NAME));
                            args.putString(StaticUtils.OPPOS_TEAM_SHORT_NAME, getIntent().getStringExtra(StaticUtils.OPPOS_TEAM_SHORT_NAME));
                            args.putString(StaticUtils.HOME_TEAM_COUNT, xTvHomeCount.getText().toString());
                            args.putString(StaticUtils.OPPOSITE_TEAM_COUNT, xTvOppositeCount.getText().toString());
                            startActivity(intent);
                        }
                    }
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("testing", getIntent().getStringExtra(StaticUtils.PACKAGE_ID));

                if (hitterName.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please select the Hitter", Toast.LENGTH_LONG).show();

                } else {
                    for (int i = 0; i < object2.size(); i++) {
                        FinalPlayerSelectionModel homePlayersSelected = object2.get(i);
                        if (hitterId.equals(String.valueOf(homePlayersSelected.getId()))) {
                            object2.remove(i);

                        } else {
                            powerHitterList2.add(new PowerHitterModel(homePlayersSelected.getName(), homePlayersSelected.getImage(), homePlayersSelected.getId(), false,homePlayersSelected.getPlayerType()));

                        }
                    }

                        if (getIntent().getStringExtra(StaticUtils.PACKAGE_ID).equals("1")) {

                            Intent intent = new Intent(Activity_PowerHitter.this, SelectedPreview5Activity.class);
                            Bundle args = new Bundle();
                            args.putSerializable("ARRAYLIST", (Serializable) object2);
                            args.putString(StaticUtils.HITTER_IMAGE, hitterImage);
                            args.putString(StaticUtils.HITTER_NAME, hitterName);
                            intent.putExtra("BUNDLE", args);
                            startActivity(intent);

                        } else {

                            Intent intent = new Intent(Activity_PowerHitter.this, SelectedPreview7Activity.class);
                            Bundle args = new Bundle();
                            args.putSerializable("ARRAYLIST", (Serializable) object2);
                            intent.putExtra("BUNDLE", args);
                            args.putString(StaticUtils.HITTER_IMAGE, hitterImage);
                            args.putString(StaticUtils.HITTER_NAME, hitterName);
                            startActivity(intent);

                        }
                    }
                }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        powerHitterList.clear();
    }

    @Override
    public void onClicKListner(int position) {
        PowerHitterModel playerDetails = powerHitterList.get(position);
        hitterId = String.valueOf(playerDetails.getId());
        hitterName = playerDetails.getName();
        hitterImage = playerDetails.getImage();
        Log.e("testing", "hitterId " + hitterId);

    }
}
