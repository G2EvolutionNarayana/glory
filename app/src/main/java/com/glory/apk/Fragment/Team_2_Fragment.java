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

import com.glory.apk.Adapter.OppositeTeamAdapter;
import com.glory.apk.MainActivity;
import com.glory.apk.Model.PlayersList.PlayersAwayTeam;
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
import static com.glory.apk.Activity.Activity_SelectPlayer.packageName;

public class Team_2_Fragment extends Fragment implements OppositeTeamAdapter.onItemClick{
//    private List<PlayerDetails> playerDetailsList;
//    private PlayerViewAdapter playerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView rv_Opposite_players;
    private OppositeTeamAdapter oppositeTeamAdapter;
    public static List<PlayersAwayTeam> playerDetailsList;
    OnDataPass dataPasser;
    OppositeTeamAdapter.onItemClick mCallBack;
    Button xBtncallMain;
    LinearLayout xLinLayMain;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  root= inflater.inflate(R.layout.fragment_team_2, container, false);
        playerDetailsList=new ArrayList<>();
        rv_Opposite_players = (RecyclerView) root.findViewById(R.id.rv_Opposite_players);
        linearLayoutManager = new LinearLayoutManager(getContext());
        rv_Opposite_players.setLayoutManager(linearLayoutManager);
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
        oppositePlayersList();
        mCallBack=this;

        // Inflate the layout for this fragment
        return root;
    }
    private void oppositePlayersList() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");
        final ProgressDialog pDialog;

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
                Log.e("testing", "status = " + response.body().getStatus());
                Log.e("testing", "response = " + response.body().getResponse().getType());
                //  Log.e("testing","response = "+response.body().getData().getPageContent());

                Log.e("testing", "response = " + response.body());

                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    if (response.body().getResponse() == null) {

                    } else if (response.body().getResponse().getType().equals("data_found")) {
                        Log.e("testing", "response = " + response.body().getPlayersData().getPlayersAwayTeam());
                        playerDetailsList=response.body().getPlayersData().getPlayersAwayTeam();

                        if (playerDetailsList.size()==0){
                            xLinLayMain.setVisibility(View.VISIBLE);
                            rv_Opposite_players.setVisibility(View.GONE);
                        }else {
                            xLinLayMain.setVisibility(View.GONE);
                            rv_Opposite_players.setVisibility(View.VISIBLE);
                            for (int i = 0; i < playerDetailsList.size(); i++) {
                                PlayersAwayTeam playerDetails = playerDetailsList.get(i);
                                playerDetails.setSelected(false);
                            }
                            oppositeTeamAdapter = new OppositeTeamAdapter(getActivity(),playerDetailsList,packageName,mCallBack);
                            rv_Opposite_players.setAdapter(oppositeTeamAdapter);
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

    @Override
    public void onResume() {
        super.onResume();
//            if (oppositeTeamAdapter == null || rv_Opposite_players.getAdapter() == null) {
//                oppositeTeamAdapter = new OppositeTeamAdapter(getActivity(), playerDetailsList);
//                rv_Opposite_players.setAdapter(oppositeTeamAdapter);
//            } else {
//                oppositeTeamAdapter.notifyDataSetChanged();
//            }

    }
    public void passData(String finalValue,String home,String opposite,Double credits) {
        dataPasser.onDataPass(finalValue,home,opposite,credits);
    }
    @Override
    public void onItemClick(String finalValue, String homeValue, String OppositeValue,Double credits) {
        passData(finalValue,homeValue,OppositeValue,credits);

    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dataPasser = (OnDataPass) context;

    }
}
