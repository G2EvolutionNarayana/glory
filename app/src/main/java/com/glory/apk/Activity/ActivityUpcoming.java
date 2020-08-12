package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.glory.apk.Adapter.UpcomingAdapter;


import com.glory.apk.Model.Pending.PendingDatum;
import com.glory.apk.Model.Pending.PendingExample;
import com.glory.apk.R;
import com.glory.apk.Retrofit.Api;
import com.glory.apk.Retrofit.ApiClient;
import com.glory.apk.Utilites.JSONParserNew;
import com.glory.apk.Utilites.StaticUtils;
import com.glory.apk.Utilites.sharedPrefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityUpcoming extends AppCompatActivity {
    RecyclerView xLiveRecyclerView;
    UpcomingAdapter upComingAdapter;
    ArrayList<String> liveArrayList;
    ArrayList<String> EntryFreeList;
    ArrayList<String> TeamNameList;
    String matchId;
    JSONParserNew jsonParser = new JSONParserNew();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming);

        mInitWidgets();
        mCallContestList();
    }

    private void mCallContestList() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");
        final ProgressDialog pDialog;
        pDialog = new ProgressDialog(ActivityUpcoming.this);
        pDialog.setMessage("Please Wait ...");
//        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        String viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);

        Api api = ApiClient.getClient().create(Api.class);

        Call<PendingExample> login = api.UpComingPlayersDetails(matchId, viewuseremail);
        login.enqueue(new Callback<PendingExample>() {
            @Override
            public void onResponse(Call<PendingExample> call, Response<PendingExample> response) {
                pDialog.dismiss();
                Log.e("testing", "status = " + response.body().getStatus());
                Log.e("testing", "response = " + response.body().getResponse().getMessage());
                //  Log.e("testing","response = "+response.body().getData().getPageContent());
                Log.e("testing", "response = " + response.body());
                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    if (response.body().getPendingFilters() == null) {

                    } else if (response.body().getResponse().getType().equals("data_found")) {
//                        Log.e("testing", "dateumList.size = " + dateumList.size());
//                        dateumList=response.body().getData();
                        List<PendingDatum> data = response.body().getData();
                        upComingAdapter = new UpcomingAdapter(ActivityUpcoming.this, data);
                        xLiveRecyclerView.setAdapter(upComingAdapter);
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
            public void onFailure(Call<PendingExample> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                pDialog.dismiss();

            }
        });

    }


    private void mInitWidgets() {
        liveArrayList = new ArrayList<>();
        EntryFreeList = new ArrayList<>();
        TeamNameList = new ArrayList<>();
        EntryFreeList.add("40");
        EntryFreeList.add("40");
        EntryFreeList.add("40");
        EntryFreeList.add("40");
        TeamNameList.add("Narayana 123456");
        TeamNameList.add("Narayana 123456");
        matchId = getIntent().getStringExtra(StaticUtils.MATCH_ID);

        xLiveRecyclerView = (RecyclerView) findViewById(R.id.xLiveRecyclerView);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        xLiveRecyclerView.setLayoutManager(mLayoutManager2);
//        liveAdapter = new LiveAdapter(getApplicationContext(),EntryFreeList,TeamNameList);
//        xLiveRecyclerView.setAdapter(liveAdapter);
    }

}
