package com.glory.apk.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.glory.apk.Model.PackagesList.PackageData;
import com.glory.apk.Model.PackagesList.PackageList;
import com.glory.apk.R;
import com.glory.apk.Retrofit.Api;
import com.glory.apk.Retrofit.ApiClient;
import com.glory.apk.Utilites.StaticUtils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_CricketList extends AppCompatActivity {

    LinearLayout layout5, layout7;
    ImageView xIv5Side, xIv7Side;
    List<PackageData> dateumList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cricket_list);

        layout5 = (LinearLayout) findViewById(R.id.lay_5_side);
        layout7 = (LinearLayout) findViewById(R.id.lay_7_side);

        xIv5Side = (ImageView) findViewById(R.id.xIv5Side);
        xIv7Side = (ImageView) findViewById(R.id.xIv7Side);
        Log.e("testing", "homeFlag =" + getIntent().getStringExtra(StaticUtils.HOME_FLAG));

        layout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGotoPlayersList("5", "1");


            }
        });
        layout7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mGotoPlayersList("7", "2");

            }
        });
    }

    private void mGotoPlayersList(String s, String packageId) {
        Intent intent = new Intent(Activity_CricketList.this, Activity_SelectPlayer.class);
        intent.putExtra(StaticUtils.MATCH_ID, getIntent().getStringExtra(StaticUtils.MATCH_ID));
        intent.putExtra(StaticUtils.HOME_TEAM_SHORT_NAME, getIntent().getStringExtra(StaticUtils.HOME_TEAM_SHORT_NAME));
        intent.putExtra(StaticUtils.HOME_TEAM_FULL_NAME, getIntent().getStringExtra(StaticUtils.HOME_TEAM_FULL_NAME));
        intent.putExtra(StaticUtils.OPPOS_TEAM_SHORT_NAME, getIntent().getStringExtra(StaticUtils.OPPOS_TEAM_SHORT_NAME));
        intent.putExtra(StaticUtils.OPPOS_TEAM_FULL_NAME, getIntent().getStringExtra(StaticUtils.OPPOS_TEAM_FULL_NAME));
        intent.putExtra(StaticUtils.HOME_FLAG, getIntent().getStringExtra(StaticUtils.HOME_FLAG));
        intent.putExtra(StaticUtils.OPPOS_FLAG, getIntent().getStringExtra(StaticUtils.OPPOS_FLAG));
        intent.putExtra(StaticUtils.PACKAGE_ID, packageId);
        Log.e("testing", "Packageid " + packageId);

        intent.putExtra(StaticUtils.PACKAGE, s);
        startActivity(intent);
    }



    @Override
    public void onBackPressed() {
        StaticUtils.FINAL_COUNT = 0;
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        StaticUtils.FINAL_COUNT = 0;
        StaticUtils.OppoTeamcount = 0;
        StaticUtils.HomeTeamcount = 0;
        StaticUtils.homePlayers.clear();
        StaticUtils.opposePlayers.clear();
    }
}
