package com.android.glory.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.glory.Adapter.CompletedAdapter;
import com.android.glory.Adapter.UpcomingAdapter;
import com.android.glory.Model.CompletedContestModel.CompletedContestDatum;
import com.android.glory.Model.CompletedContestModel.CompletedContestExample;
import com.android.glory.Model.Pending.PendingDatum;
import com.android.glory.Model.Pending.PendingExample;
import com.android.glory.R;
import com.android.glory.Retrofit.Api;
import com.android.glory.Retrofit.ApiClient;
import com.android.glory.Utilites.StaticUtils;
import com.android.glory.Utilites.sharedPrefs;

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

        Call<CompletedContestExample> login = api.CompletedContestList(matchId,viewuseremail);
        login.enqueue(new Callback<CompletedContestExample>() {
            @Override
            public void onResponse(Call<CompletedContestExample> call, Response<CompletedContestExample> response) {
                pDialog.dismiss();
                Log.e("testing", "status = " + response.body().getStatus());
                Log.e("testing", "response = " + response.body().getCompletedContestResponse().getMessage());
                //  Log.e("testing","response = "+response.body().getData().getPageContent());
                Log.e("testing", "response = " + response.body());
                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    if (response.body().getCompletedContestFilters() == null) {

                    } else if (response.body().getCompletedContestResponse().getType().equals("data_found")) {
//                        Log.e("testing", "dateumList.size = " + dateumList.size());
//                        dateumList=response.body().getData();
                        List<CompletedContestDatum> data = response.body().getData();
                        completedAdapter = new CompletedAdapter(getApplicationContext(), data);
                        xCmpRecyclerView.setAdapter(completedAdapter);
                        pDialog.dismiss();

                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getCompletedContestResponse().getType(), Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();

                    }

                } else {
                    Log.e("testing", "error");
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.body().getCompletedContestResponse().getType(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CompletedContestExample> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                pDialog.dismiss();

            }
        });

    }


}
