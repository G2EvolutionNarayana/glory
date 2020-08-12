package com.glory.apk.Fragment;

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

import com.glory.apk.Activity.EditPlayerActivity;
import com.glory.apk.Adapter.EditAwayTeamAdapter;
import com.glory.apk.Adapter.PlayerViewAdapter;
import com.glory.apk.MainActivity;
import com.glory.apk.Model.EditPlayersListModel.EditPlayerAwayTeam;
import com.glory.apk.Model.EditPlayersListModel.EditPlayerExample;
import com.glory.apk.R;
import com.glory.apk.Retrofit.Api;
import com.glory.apk.Retrofit.ApiClient;
import com.glory.apk.Utilites.OnDataPass;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.glory.apk.Activity.Activity_SelectPlayer.matchFinalId;
import static com.glory.apk.Activity.EditPlayerActivity.ContestUserId;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditTeam_2Fragment extends Fragment implements EditAwayTeamAdapter.onItemClick {
    private PlayerViewAdapter playerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private EditAwayTeamAdapter homeTeamAdapter;
    public static List<EditPlayerAwayTeam> playerDetailsList = new ArrayList<>();
    EditTeam_2Fragment.mydataBack mCallback;
    EditPlayerActivity mActivity;
    private String matchd;
    OnDataPass dataPasser;
    EditAwayTeamAdapter.onItemClick mCallBack;
    Button xBtncallMain;
    LinearLayout xLinLayMain;
    public EditTeam_2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  root= inflater.inflate(R.layout.fragment_edit_team_2, container, false);


//        Bundle bundle = this.getArguments();
        mActivity = (EditPlayerActivity) getActivity();
        mCallBack=this;
//        mActivity.setAboutDataListener(getContext());
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            matchd = bundle.getString("matchId", "");
        }
        Log.e("testing", "match id :+" + matchFinalId);
        recyclerView = (RecyclerView) root.findViewById(R.id.rv_Opposite_players);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
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
        Call<EditPlayerExample> login = api.EditPlayerList(ContestUserId);
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
                        Log.e("testing", "dateumList.size = " + response.body().getData().get(0).getEditPlayerMatch().getEditPlayerAwayTeam());
                        playerDetailsList =response.body().getData().get(0).getEditPlayerMatch().getEditPlayerAwayTeam();


                        if (playerDetailsList.size()==0){
                            xLinLayMain.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        }else {
                            xLinLayMain.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                            String awayTeamCount="0";
                            awayTeamCount=response.body().getData().get(0).getAwayTeamCount();
                            homeTeamAdapter = new EditAwayTeamAdapter(getActivity(), playerDetailsList, response.body().getData().get(0).getPackageId(),mCallBack,awayTeamCount,0);
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

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("testing", "onPause");

    }


    public void passData(String finalValue,String home,String opposite,Double credits) {
        dataPasser.onDataPass(finalValue,home,opposite,credits);
    }

    @Override
    public void onItemClick(String finalValue, String homeValue, String OppositeValue,Double credits) {
        passData(finalValue,homeValue,OppositeValue,credits);

    }

    public interface mydataBack {
        void bringBackString(List<EditPlayerAwayTeam> list);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dataPasser = (OnDataPass) context;

    }
}
