package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.glory.apk.Adapter.LiveAdapter;
import com.glory.apk.Model.LiveContestListModel.LiveContestDatum;
import com.glory.apk.Model.LiveContestListModel.LiveContestExample;
import com.glory.apk.R;
import com.glory.apk.Retrofit.Api;
import com.glory.apk.Retrofit.ApiClient;
import com.glory.apk.Utilites.StaticUtils;
import com.glory.apk.Utilites.sharedPrefs;
import com.glory.apk.Model.LiveScore.Example;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_live extends AppCompatActivity {
    RecyclerView xLiveRecyclerView;
    LiveAdapter liveAdapter;
    ArrayList<String>  liveArrayList;
    ArrayList<String>  EntryFreeList;
    ArrayList<String>  TeamNameList;
    String matchId;
    TextView xTvHomeScore,xTvHomeOvers,xTvOppositeScore,xAwayOver,xTvHomeTeamName,xTvOppositeTeamName;
    ImageView xIvRefresh;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
        mInitWidgets();
        mCallContestList();
        mCallLiveScore();
    }

    private void mInitWidgets() {
        liveArrayList=new ArrayList<>();
        EntryFreeList=new ArrayList<>();
        TeamNameList=new ArrayList<>();
        EntryFreeList.add("40");
        EntryFreeList.add("40");
        EntryFreeList.add("40");
        EntryFreeList.add("40");
        TeamNameList.add("Narayana 123456");
        TeamNameList.add("Narayana 123456");
        matchId = getIntent().getStringExtra(StaticUtils.MATCH_ID);

        xTvHomeScore=(TextView) findViewById(R.id.xTvHomeScore);
        xTvHomeOvers=(TextView) findViewById(R.id.xTvHomeOvers);
        xTvOppositeScore=(TextView) findViewById(R.id.xTvOppositeScore);
        xAwayOver=(TextView) findViewById(R.id.xAwayOver);
        xTvHomeTeamName=(TextView) findViewById(R.id.xTvHomeTeamName);
        xTvOppositeTeamName=(TextView) findViewById(R.id.xTvOppositeTeamName);

        xTvHomeTeamName.setText(getIntent().getStringExtra(StaticUtils.HOME_TEAM_SHORT_NAME));
        xTvOppositeTeamName.setText(getIntent().getStringExtra(StaticUtils.OPPOS_TEAM_SHORT_NAME));

        xLiveRecyclerView=(RecyclerView)findViewById(R.id.xLiveRecyclerView);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        xLiveRecyclerView.setLayoutManager(mLayoutManager2);
        xIvRefresh=(ImageView)findViewById(R.id.xIvRefresh);
//        liveAdapter = new LiveAdapter(getApplicationContext(),EntryFreeList,TeamNameList);
//        xLiveRecyclerView.setAdapter(liveAdapter);
        xIvRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallContestList();
                mCallLiveScore();
            }
        });
    }

    private void mCallContestList() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");
        final ProgressDialog pDialog;

        pDialog = new ProgressDialog(Activity_live.this);
        pDialog.setMessage("Please Wait ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        String viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);

        Api api = ApiClient.getClient().create(Api.class);
        Log.e("testing", "matchId = " + matchId);
        Log.e("testing", "viewuseremail = " + viewuseremail);

        Call<LiveContestExample> login = api.LivePlayersDetails(matchId, viewuseremail);

//        Call<LiveContestExample> login = api.LivePlayersDetails(matchId, viewuseremail);
        login.enqueue(new Callback<LiveContestExample>() {
            @Override
            public void onResponse(Call<LiveContestExample> call, Response<LiveContestExample> response) {
                pDialog.dismiss();
                Log.e("testing", "status = " + response.body().getStatus());
                Log.e("testing", "response = " + response.body().getLiveContestResponse().getMessage());
                //  Log.e("testing","response = "+response.body().getData().getPageContent());
                Log.e("testing", "response = " + response.body());
                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    if (response.body().getLiveContestFilters() == null) {

                    } else if (response.body().getLiveContestResponse().getType().equals("data_found")) {
//                        Log.e("testing", "dateumList.size = " + dateumList.size());
//                        dateumList=response.body().getData();
                        List<LiveContestDatum> data = response.body().getData();
                        liveAdapter = new LiveAdapter(Activity_live.this, data,response.body().getLiveContestFilters().getMatchId());
                        xLiveRecyclerView.setAdapter(liveAdapter);
                        pDialog.dismiss();

                    } else {
                        Toast.makeText(Activity_live.this, response.body().getLiveContestResponse().getType(), Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();

                    }

                } else {
                    Log.e("testing", "error");
                    pDialog.dismiss();
                    Toast.makeText(Activity_live.this, response.body().getLiveContestResponse().getType(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LiveContestExample> call, Throwable t) {
                Toast.makeText(Activity_live.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                pDialog.dismiss();

            }
        });

    }

    private void mCallLiveScore() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");
        final ProgressDialog pDialog;

        pDialog = new ProgressDialog(Activity_live.this);
        pDialog.setMessage("Please Wait ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        String viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);

        Api api = ApiClient.getClient().create(Api.class);
        Log.e("testing", "matchId = " + matchId);
        Log.e("testing", "viewuseremail = " + viewuseremail);

        Call<Example> login = api.LiveScore(matchId);

//        Call<LiveContestExample> login = api.LivePlayersDetails(matchId, viewuseremail);
        login.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                pDialog.dismiss();
                Log.e("testing", "status = " + response.body().getStatus());
                Log.e("testing", "response = " + response.body().getResponse().getMessage());
                //  Log.e("testing","response = "+response.body().getData().getPageContent());
                Log.e("testing", "response = " + response.body());
                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    if (response.body().getData() == null) {

                    } else if (response.body().getResponse().getType().equals("data_found")) {

                        xTvHomeScore.setText(response.body().getData().get(0).getHomeScore()+"/"+response.body().getData().get(0).getHomeWickets());
                        xTvOppositeScore.setText(response.body().getData().get(0).getAwayScore()+"/"+response.body().getData().get(0).getAwayWickets());
                        xTvHomeOvers.setText(response.body().getData().get(0).getHomeOvers());
                        xAwayOver.setText(response.body().getData().get(0).getAwayOvers());
                        pDialog.dismiss();

                    } else {
//                        Toast.makeText(Activity_live.this, response.body().getResponse().getType(), Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();

                    }

                } else {
                    Log.e("testing", "error");
                    pDialog.dismiss();
//                    Toast.makeText(Activity_live.this, response.body().getResponse().getType(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(Activity_live.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                pDialog.dismiss();

            }
        });

    }

}
