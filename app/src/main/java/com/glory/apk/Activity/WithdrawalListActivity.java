package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.glory.apk.Adapter.WithdrawListAdapter;
import com.glory.apk.R;
import com.glory.apk.Retrofit.Api;
import com.glory.apk.Retrofit.ApiClient;
import com.glory.apk.Utilites.sharedPrefs;
import com.glory.apk.Model.WithdrawalList.Example;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WithdrawalListActivity extends AppCompatActivity {

    RecyclerView xRecyclerViewWithdraw;
    TextView xTvNoWithdraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal_list);
        mInitwidgets();
    }

    private void mInitwidgets() {
        xRecyclerViewWithdraw = (RecyclerView) findViewById(R.id.xRecyclerViewWithdraw);
        xRecyclerViewWithdraw.setHasFixedSize(true);
        xRecyclerViewWithdraw.setLayoutManager(new LinearLayoutManager(this));
        xTvNoWithdraw = (TextView) findViewById(R.id.xTvNoWithdraw);
        mWithDrawList();
    }

    private void mWithDrawList() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");
        ProgressDialog pDialog;
        pDialog = new ProgressDialog(WithdrawalListActivity.this);
        pDialog.setMessage("Please Wait ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        Api api = ApiClient.getClient().create(Api.class);
        Call<com.glory.apk.Model.WithdrawalList.Example> login = api.WithdrawList(sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId).toString());

        login.enqueue(new Callback<com.glory.apk.Model.WithdrawalList.Example>() {
            @Override
            public void onResponse(Call<com.glory.apk.Model.WithdrawalList.Example> call, Response<com.glory.apk.Model.WithdrawalList.Example> response) {


                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    Log.e("testing", "status = " + response.body().getStatus());
                    Log.e("testing", "response = " + response.body().getResponse().getType());
                    //  Log.e("testing","response = "+response.body().getData().getPageContent());

                    Log.e("testing", "response = " + response.body());
                    if (response.body().getResponse() == null) {

                    } else if (response.body().getResponse().getType().equals("data_found")) {
                        xTvNoWithdraw.setVisibility(View.GONE);
                        xRecyclerViewWithdraw.setVisibility(View.VISIBLE);

                        xRecyclerViewWithdraw.setAdapter(new WithdrawListAdapter(getApplicationContext(), response.body().getData()));
                        pDialog.dismiss();

                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getResponse().getType(), Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();
                        xTvNoWithdraw.setVisibility(View.VISIBLE);
                        xRecyclerViewWithdraw.setVisibility(View.GONE);
                    }

                } else {
                    Log.e("testing", "error");
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Withdrawal list is empty", Toast.LENGTH_SHORT).show();
                    xTvNoWithdraw.setVisibility(View.VISIBLE);
                    xRecyclerViewWithdraw.setVisibility(View.GONE);
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