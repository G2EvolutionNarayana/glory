package com.android.glory.Activity;

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

import com.android.glory.Model.PackagesList.PackageData;
import com.android.glory.Model.PackagesList.PackageList;
import com.android.glory.R;
import com.android.glory.Retrofit.Api;
import com.android.glory.Retrofit.ApiClient;
import com.android.glory.Utilites.StaticUtils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.glory.Utilites.StaticUtils.FINAL_COUNT;
import static com.android.glory.Utilites.StaticUtils.HomeTeamcount;
import static com.android.glory.Utilites.StaticUtils.OppoTeamcount;

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
        matchesList();
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

    private void matchesList() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");
        ProgressDialog pDialog;

        pDialog = new ProgressDialog(Activity_CricketList.this);
        pDialog.setMessage("Please Wait ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        Api api = ApiClient.getClient().create(Api.class);
        Call<PackageList> login = api.PackagesList();

        login.enqueue(new Callback<PackageList>() {
            @Override
            public void onResponse(Call<PackageList> call, Response<PackageList> response) {
                pDialog.dismiss();
                Log.e("testing", "status = " + response.body().getStatus());
                Log.e("testing", "response = " + response.body().getResponse().getType());
                //  Log.e("testing","response = "+response.body().getData().getPageContent());

                Log.e("testing", "response = " + response.body());

                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    if (response.body().getResponse() == null) {

                    } else if (response.body().getResponse().getType().equals("data_found")) {
                        Log.e("testing", "dateumList.size = " + dateumList.size());
                        dateumList = response.body().getData();
                        for (int i = 0; i < dateumList.size(); i++) {

                            Log.e("testing", "dateumList = " + "dateumList");
                            PackageData list = dateumList.get(i);
                            Log.e("testing", "list = " + "list");

                            if (i == 0) {
                                if (list.getImageUrl() == null || list.getImageUrl().toString().length() == 0) {
                                    Glide.with(getApplicationContext())
                                            .load((R.drawable.side_5)).placeholder(R.drawable.side_5)
                                            .into(xIv5Side);
                                    Log.e("testing", "getImageUrl = " + "null");

                                } else {
                                    Glide.with(getApplicationContext())
                                            .load(Uri.parse(list.getImageUrl().toString()))
                                            .error(R.drawable.icon1)
                                            .into(xIv5Side);
                                    Log.e("testing", "getImageUrl = " + "image");

                                }
                            } else {
                                if (list.getImageUrl() == null || list.getImageUrl().toString().length() == 0) {
                                    Glide.with(getApplicationContext())
                                            .load(R.drawable.side_7)
                                            .error(R.drawable.side_7)
                                            .into(xIv7Side);
                                } else {
                                    Glide.with(getApplicationContext())
                                            .load(Uri.parse(list.getImageUrl().toString()))
                                            .error(R.drawable.icon1)
                                            .into(xIv7Side);
                                }
                            }
                        }

                        pDialog.dismiss();

                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getResponse().getType(), Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();

                    }

                } else {
                    Log.e("testing", "error");
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.body().getResponse().getType(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PackageList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                pDialog.dismiss();

            }
        });
    }

    @Override
    public void onBackPressed() {
        FINAL_COUNT = 0;
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        FINAL_COUNT = 0;
        OppoTeamcount = 0;
        HomeTeamcount = 0;
        StaticUtils.homePlayers.clear();
        StaticUtils.opposePlayers.clear();
    }
}
