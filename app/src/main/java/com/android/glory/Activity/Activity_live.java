package com.android.glory.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.glory.Adapter.Adapter_MyMatches;
import com.android.glory.Adapter.LiveAdapter;
import com.android.glory.Adapter.UpcomingAdapter;
import com.android.glory.Model.LiveContestListModel.LiveContestDatum;
import com.android.glory.Model.LiveContestListModel.LiveContestExample;
import com.android.glory.Model.Pending.PendingDatum;
import com.android.glory.Model.Pending.PendingExample;
import com.android.glory.R;
import com.android.glory.Retrofit.Api;
import com.android.glory.Retrofit.ApiClient;
import com.android.glory.Utilites.StaticUtils;
import com.android.glory.Utilites.sharedPrefs;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
        mInitWidgets();
        mCallContestList();
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




        xLiveRecyclerView=(RecyclerView)findViewById(R.id.xLiveRecyclerView);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        xLiveRecyclerView.setLayoutManager(mLayoutManager2);
//        liveAdapter = new LiveAdapter(getApplicationContext(),EntryFreeList,TeamNameList);
//        xLiveRecyclerView.setAdapter(liveAdapter);
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
                        liveAdapter = new LiveAdapter(getApplicationContext(), data,response.body().getLiveContestFilters().getMatchId());
                        xLiveRecyclerView.setAdapter(liveAdapter);
                        pDialog.dismiss();

                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getLiveContestResponse().getType(), Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();

                    }

                } else {
                    Log.e("testing", "error");
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.body().getLiveContestResponse().getType(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LiveContestExample> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                pDialog.dismiss();

            }
        });

    }
}
