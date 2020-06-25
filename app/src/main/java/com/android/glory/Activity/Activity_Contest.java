package com.android.glory.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.android.glory.Adapter.ContestAdapter;
import com.android.glory.MainActivity;
import com.android.glory.Model.Contest.ContestDatum;
import com.android.glory.Model.Contest.ContestExample;
import com.android.glory.Model.EnterContest.EnterContestExample;
import com.android.glory.Model.FinalPlayerSelectionModel;
import com.android.glory.Model.PaymentModel.PaymentExample;
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

public class Activity_Contest extends AppCompatActivity implements ContestAdapter.OnItemClick {
    private List<ContestDatum> contestList;

    private ContestAdapter contestAdapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private ContestAdapter.OnItemClick mCallBack;
    private ArrayList<String> SelectedPlayers;
    Dialog dialog;
    Dialog Wallatedialog;
    String PackageId;
    String MatchId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest);
        PackageId = getIntent().getStringExtra(StaticUtils.PACKAGE_ID);
        MatchId = getIntent().getStringExtra(StaticUtils.MATCH_ID);

        Wallatedialog = new Dialog(Activity_Contest.this);
        Wallatedialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mCallBack = this;
        recyclerView = (RecyclerView) findViewById(R.id.rv_contest);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        matchesList();
    }

    private void matchesList() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");
        ProgressDialog pDialog;

        pDialog = new ProgressDialog(Activity_Contest.this);
        pDialog.setMessage("Please Wait ...");
//        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        String viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);

        Api api = ApiClient.getClient().create(Api.class);
        Call<ContestExample> login = api.ContestList(MatchId, viewuseremail);

        login.enqueue(new Callback<ContestExample>() {
            @Override
            public void onResponse(Call<ContestExample> call, Response<ContestExample> response) {
                pDialog.dismiss();
                Log.e("testing", "status = " + response.body().getStatus());
                Log.e("testing", "response = " + response.body().getContestResponse().getMessage());
                //  Log.e("testing","response = "+response.body().getData().getPageContent());
                Log.e("testing", "response = " + response.body());
                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    if (response.body().getContestFilters() == null) {

                    } else if (response.body().getContestResponse().getType().equals("data_found")) {
//                        Log.e("testing", "dateumList.size = " + dateumList.size());
//                        dateumList=response.body().getData();
                        contestList = response.body().getData();
                        contestAdapter = new ContestAdapter(getApplicationContext(), response.body().getData(), mCallBack);
                        recyclerView.setAdapter(contestAdapter);
                        pDialog.dismiss();

                    } else {
//                        Toast.makeText(getApplicationContext(), response.body().getContestResponse().getType(), Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();

                    }

                } else {
                    Log.e("testing", "error");
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.body().getContestResponse().getType(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ContestExample> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                pDialog.dismiss();

            }
        });
    }

    @Override
    public void onClicKListner(int position) {
        ContestDatum contest = contestList.get(position);
        String ContestId = contest.getId().toString();
        String entryFee = contest.getEntryFee().toString();

        finalContest(ContestId, entryFee);
    }

    private void finalContest(String ContestId, String entryFee) {

        Log.e("testing", "strregisteredtoken = " + "matchesList");
        ProgressDialog pDialog;
        pDialog = new ProgressDialog(Activity_Contest.this);
        pDialog.setMessage("Please Wait ...");
//        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        String matchId = getIntent().getStringExtra(StaticUtils.MATCH_ID);
        Log.e("testing", "matchId = " + matchId);

        String viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);
        Log.e("testing", "viewuseremail = " + viewuseremail);
        Log.e("testing", "PackageId = " + PackageId);
        Log.e("testing", "ContestId = " + ContestId);
        Log.e("testing", "entryFee = " + entryFee);

        String hitterId = getIntent().getStringExtra(StaticUtils.HITTERID);
        Log.e("testing", "hitterId = " + hitterId);

        Bundle args = getIntent().getBundleExtra("BUNDLE");
        ArrayList<FinalPlayerSelectionModel> object = (ArrayList<FinalPlayerSelectionModel>) args.getSerializable("ARRAYLIST");
        Log.e("testing", "object = " + object.size());

        SelectedPlayers = new ArrayList<>();
        for (int i = 0; i < object.size(); i++) {
            FinalPlayerSelectionModel homePlayersSelected = object.get(i);

            Log.e("testing", "object = " + String.valueOf(homePlayersSelected.getId()));

            SelectedPlayers.add(String.valueOf(homePlayersSelected.getId()));
        }
        Api api = ApiClient.getClient().create(Api.class);
        Call<PaymentExample> login = api.PaymentList(matchId, ContestId, viewuseremail, PackageId, entryFee, hitterId, SelectedPlayers);

        login.enqueue(new Callback<PaymentExample>() {
            @Override
            public void onResponse(Call<PaymentExample> call, Response<PaymentExample> response) {
                pDialog.dismiss();
                //       Log.e("testing", "status = " + response.body().getStatus());
                Log.e("testing", "response = " + response.body().getPaymentResponse().getMessage());
                //  Log.e("testing","response = "+response.body().getData().getPageContent());
                Log.e("testing", "response = " + response.body());
                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {


                } else if (response.body().getStatus().equals("success")) {
                    Toast.makeText(getApplicationContext(), "Contest joined successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);


                } else {

                    if (response.body().getPaymentResponse().getType().equals("validation_errors")) {
                        Toast.makeText(getApplicationContext(), response.body().getPaymentResponse().getType(), Toast.LENGTH_SHORT).show();
                        Log.e("testing", "response = " + response.body().getPaymentResponse().getType());

                    } else {
                        Log.e("testing", "response = " + response.body().getPaymentResponse().getType());
                        Wallatedialog.setContentView(R.layout.low_wallate_amout);
                        Wallatedialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                        TextView editquestion = (TextView) Wallatedialog.findViewById(R.id.textwalletsubmit);


                        editquestion.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(), Activity_Balance.class);
                                startActivity(intent);
                                Wallatedialog.cancel();

                            }
                        });

                        Wallatedialog.show();

                    }
                }
            }

            @Override
            public void onFailure(Call<PaymentExample> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                pDialog.dismiss();

            }
        });
    }
}
