package com.glory.apk.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.glory.apk.Activity.Activity_SelectPlayer;
import com.glory.apk.Adapter.HomeTeamAdapter;
import com.glory.apk.Adapter.PlayerViewAdapter;
import com.glory.apk.MainActivity;
import com.glory.apk.Model.PlayersList.PlayersListHomeTeam;
import com.glory.apk.Model.PlayersList.PlayersMain;
import com.glory.apk.R;
import com.glory.apk.Retrofit.Api;
import com.glory.apk.Retrofit.ApiClient;
import com.glory.apk.Utilites.OnDataPass;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.glory.apk.Activity.Activity_SelectPlayer.MainmatchId;
import static com.glory.apk.Activity.Activity_SelectPlayer.matchFinalId;
import static com.glory.apk.Activity.Activity_SelectPlayer.packageName;


public class Team_1_Fragment extends Fragment implements  HomeTeamAdapter.onItemClick{
    //    private List<PlayerDetails> playerDetailsList;
    private PlayerViewAdapter playerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private ProgressDialog pDialog;
    private HomeTeamAdapter homeTeamAdapter;
    public static List<PlayersListHomeTeam> playerDetailsList = new ArrayList<>();
    mydataBack mCallback;
    Activity_SelectPlayer mActivity;
    private String matchd;
    OnDataPass dataPasser;
    HomeTeamAdapter.onItemClick mCallBack;
    Button xBtncallMain;
    LinearLayout xLinLayMain;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_team_1, container, false);
//        Bundle bundle = this.getArguments();
        mActivity = (Activity_SelectPlayer) getActivity();
        mCallBack=this;
//        mActivity.setAboutDataListener(getContext());
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            matchd = bundle.getString("matchId", "");
        }
        Log.e("testing", "match id :+" + matchFinalId);
        xBtncallMain=(Button)root.findViewById(R.id.xBtncallMain);
        xLinLayMain=(LinearLayout)root.findViewById(R.id.xLinLayMain);
        xBtncallMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        recyclerView = (RecyclerView) root.findViewById(R.id.rv_players);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        homePlayersList();
        return root;
    }

    private void homePlayersList() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");

        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Please Wait ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        Api api = ApiClient.getClient().create(Api.class);
        Log.e("testing", "MainmatchId = " + MainmatchId);

        Call<PlayersMain> login = api.PlayersList(MainmatchId);

        login.enqueue(new Callback<PlayersMain>() {
            @Override
            public void onResponse(Call<PlayersMain> call, Response<PlayersMain> response) {
                pDialog.dismiss();


                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {
                    Log.e("testing", "status = " + response.body().getStatus());
                    Log.e("testing", "response = " + response.body().getResponse().getType());
                    //  Log.e("testing","response = "+response.body().getData().getPageContent());

                    Log.e("testing", "response = " + response.body());
                } else if (response.body().getStatus().equals("success")) {
                    if (response.body().getResponse() == null) {

                    } else if (response.body().getResponse().getType().equals("data_found")) {
                        Log.e("testing", "dateumList.size = " + response.body().getPlayersData().getPlayersListHomeTeam());
                        playerDetailsList = response.body().getPlayersData().getPlayersListHomeTeam();

                        pDialog.dismiss();

                        if (playerDetailsList.size()==0){
                            xLinLayMain.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                            Log.e("testing", "INVISIBLE ");

                        }else {
                            xLinLayMain.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                            for (int i = 0; i < playerDetailsList.size(); i++) {
                                PlayersListHomeTeam playerDetails = playerDetailsList.get(i);
                                playerDetails.setSelected(false);

                            }
                            homeTeamAdapter = new HomeTeamAdapter(getActivity(), playerDetailsList, packageName,mCallBack);
                            recyclerView.setAdapter(homeTeamAdapter);

                        }
                        pDialog.dismiss();


                    } else {
//                        Toast.makeText(getContext(), response.body().getResponse().getType(), Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();

                    }

                } else {
                    Log.e("testing", "error");
                    pDialog.dismiss();
//                    Toast.makeText(getContext(), response.body().getResponse().getType(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<PlayersMain> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                pDialog.dismiss();

            }
        });
    }

    public void method() {
        mCallback.bringBackString(playerDetailsList);

    }


    @Override
    public void onResume() {
        super.onResume();
//        Log.e("testing", "onResume");
//        if (homeTeamAdapter == null || recyclerView.getAdapter() == null) {
//            homeTeamAdapter = new HomeTeamAdapter(getActivity(), playerDetailsList);
//            recyclerView.setAdapter(homeTeamAdapter);
//        } else {
//            homeTeamAdapter.notifyDataSetChanged();
//        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("testing", "onPause");

    }

//    public void onAttach(@NonNull Activity_SelectPlayer activity) {
//        activity_selectPlayer = activity;
//        super.onAttach(activity);
//
//        mCallback = (mydataBack) activity_selectPlayer;
//    }


    //    @Override
//    public void onDataReceived(String fdgsd) {
//        packageName=fdgsd;
//        Toast.makeText(getContext(),packageName, Toast.LENGTH_SHORT).show();
//
//
//    }
    public void passData(String finalValue,String home,String opposite,Double credits) {
        dataPasser.onDataPass(finalValue,home,opposite,credits);
    }

    @Override
    public void onItemClick(String finalValue, String homeValue, String OppositeValue,Double credits) {
        passData(finalValue,homeValue,OppositeValue,credits);

    }

    public interface mydataBack {
        void bringBackString(List<PlayersListHomeTeam> list);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dataPasser = (OnDataPass) context;

    }
}

