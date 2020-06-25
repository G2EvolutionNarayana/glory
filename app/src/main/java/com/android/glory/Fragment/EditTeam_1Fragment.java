package com.android.glory.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.glory.Activity.Activity_SelectPlayer;
import com.android.glory.Activity.EditPlayerActivity;
import com.android.glory.Adapter.EditAwayTeamAdapter;
import com.android.glory.Adapter.EditHomeTeamAdapter;
import com.android.glory.Adapter.HomeTeamAdapter;
import com.android.glory.Adapter.PlayerViewAdapter;
import com.android.glory.MainActivity;
import com.android.glory.Model.EditPlayersListModel.EditPlayerExample;
import com.android.glory.Model.EditPlayersListModel.EditPlayerHomeTeam;
import com.android.glory.Model.PlayersList.PlayersListHomeTeam;
import com.android.glory.Model.PlayersList.PlayersMain;
import com.android.glory.R;
import com.android.glory.Retrofit.Api;
import com.android.glory.Retrofit.ApiClient;
import com.android.glory.Utilites.OnDataPass;
import com.android.glory.Utilites.StaticUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.glory.Activity.Activity_SelectPlayer.matchFinalId;
import static com.android.glory.Activity.Activity_SelectPlayer.packageName;
import static com.android.glory.Activity.EditPlayerActivity.ContestUserId;
import static com.android.glory.Utilites.StaticUtils.Edit_FINAL_COUNT;
import static com.android.glory.Utilites.StaticUtils.Edit_HomeTeamcount;


public class EditTeam_1Fragment extends Fragment implements EditHomeTeamAdapter.onItemClick {
    private PlayerViewAdapter playerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private EditHomeTeamAdapter homeTeamAdapter;
    public static List<EditPlayerHomeTeam> playerDetailsList = new ArrayList<>();
    mydataBack mCallback;
    EditPlayerActivity mActivity;
    private String matchd;
    OnDataPass dataPasser;
    EditHomeTeamAdapter.onItemClick mCallBack;
    Button xBtncallMain;
    LinearLayout xLinLayMain;

    public EditTeam_1Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_edit_team_1, container, false);
//        Bundle bundle = this.getArguments();
        mActivity = (EditPlayerActivity) getActivity();
        mCallBack=this;
//        mActivity.setAboutDataListener(getContext());
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            matchd = bundle.getString("matchId", "");
        }
        Log.e("testing", "match id :+" + matchFinalId);
        recyclerView = (RecyclerView) root.findViewById(R.id.rv_players);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        xBtncallMain=(Button)root.findViewById(R.id.xBtncallMain);
        xLinLayMain=(LinearLayout)root.findViewById(R.id.xLinLayMain);
        xBtncallMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        homePlayersList();
        return root;
    }

    private void homePlayersList() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");
        final ProgressDialog pDialog;

        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Please Wait ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        Api api = ApiClient.getClient().create(Api.class);
        Call<EditPlayerExample> login = api.EditPlayerList("53");
        Log.e("testing", "EditPlayerActivity.ContestUserId = " + ContestUserId);

        login.enqueue(new Callback<EditPlayerExample>() {
            @Override
            public void onResponse(Call<EditPlayerExample> call, Response<EditPlayerExample> response) {
                pDialog.dismiss();
                Log.e("testing", "status = " + response.body().getStatus());
                Log.e("testing", "response = " + response.body().getEditPlayerResponse().getType());
                //  Log.e("testing","response = "+response.body().getData().getPageContent());

                Log.e("testing", "response = " + response.body());

                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    if (response.body().getEditPlayerResponse() == null) {

                    } else if (response.body().getEditPlayerResponse().getType().equals("data_found")) {
                        Log.e("testing", "dateumList.size = " + response.body().getData().get(0).getEditPlayerMatch().getEditPlayerHomeTeam());
                        playerDetailsList =response.body().getData().get(0).getEditPlayerMatch().getEditPlayerHomeTeam();

                        for (int i=0;i<playerDetailsList.size();i++){
                            EditPlayerHomeTeam playerDetails = playerDetailsList.get(i);
                            Log.e("testing","Package id ="+response.body().getData().get(0).getPackageId());

                            if (response.body().getData().get(0).getPackageId()==1){
                                    if (playerDetails.getIsselected()==1){
                                        Edit_FINAL_COUNT=Edit_FINAL_COUNT+1;
                                        Edit_HomeTeamcount=Edit_HomeTeamcount+1;
                                        StaticUtils.EditCREDITS5=StaticUtils.EditCREDITS5-Double.parseDouble(playerDetails.getCredits());
                                        Log.e("testing","lestb ="+StaticUtils.EditCREDITS5);

                                    }
                                } else {

                                    if (playerDetails.getIsselected()==1){
                                        Edit_FINAL_COUNT=Edit_FINAL_COUNT+1;
                                        Edit_HomeTeamcount=Edit_HomeTeamcount+1;

                                        StaticUtils.EditCREDITS7=StaticUtils.EditCREDITS7-Double.parseDouble(playerDetails.getCredits());

                                    }
                                }
                        }
                        String HomeTeam="0";
                        HomeTeam=response.body().getData().get(0).getHomeTeamCount();
                        if (playerDetailsList.size()==0){
                            xLinLayMain.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        }else {
                            xLinLayMain.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);

                            String awayTeamCount="0";
                            awayTeamCount=response.body().getData().get(0).getAwayTeamCount();
                            homeTeamAdapter = new EditHomeTeamAdapter(getActivity(), playerDetailsList, response.body().getData().get(0).getPackageId(),mCallBack,HomeTeam);
                            recyclerView.setAdapter(homeTeamAdapter);
                        }
                        pDialog.dismiss();

                    } else {
                        Toast.makeText(getContext(), response.body().getEditPlayerResponse().getType(), Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();

                    }

                } else {
                    Log.e("testing", "error");
                    pDialog.dismiss();
                    Toast.makeText(getContext(), response.body().getEditPlayerResponse().getType(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<EditPlayerExample> call, Throwable t) {
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
        void bringBackString(List<EditPlayerHomeTeam> list);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dataPasser = (OnDataPass) context;

    }
}
