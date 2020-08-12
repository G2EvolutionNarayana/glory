package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.glory.apk.Adapter.CompletedAdapter;
import com.glory.apk.Model.CompletedDataModel.Datum;
import com.glory.apk.R;
import com.glory.apk.Retrofit.Api;
import com.glory.apk.Retrofit.ApiClient;
import com.glory.apk.Utilites.StaticUtils;
import com.glory.apk.Utilites.sharedPrefs;
import com.glory.apk.Model.CompletedDataModel.Example;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompletedActivity extends AppCompatActivity {
    RecyclerView xCmpRecyclerView;
    CompletedAdapter completedAdapter;
    String matchId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed);
        mInitWidgets();
        mCallContestList();
    }
    private void mInitWidgets() {
        matchId=getIntent().getStringExtra(StaticUtils.MATCH_ID);
        xCmpRecyclerView=(RecyclerView)findViewById(R.id.xCmpRecyclerView);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        xCmpRecyclerView.setLayoutManager(mLayoutManager2);
    }

    private void mCallContestList() {
        final ProgressDialog pDialog;

        Log.e("testing", "strregisteredtoken = " + "matchesList");
        pDialog = new ProgressDialog(CompletedActivity.this);
        pDialog.setMessage("Please Wait ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        String viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);

        Api api = ApiClient.getClient().create(Api.class);
        Log.e("testing", "matchId = " + matchId);

        Call<Example> login = api.CompletedContestList(matchId,viewuseremail);
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
                    if (response.body().getFilters() == null) {

                    } else if (response.body().getResponse().getType().equals("data_found")) {
//                        Log.e("testing", "dateumList.size = " + dateumList.size());
//                        dateumList=response.body().getData();
                        List<Datum> data = response.body().getData();
                        completedAdapter = new CompletedAdapter(CompletedActivity.this, data);
                        xCmpRecyclerView.setAdapter(completedAdapter);
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
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                pDialog.dismiss();

            }
        });

    }


}
