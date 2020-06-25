package com.android.glory.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.glory.Activity.Activity_CricketList;
import com.android.glory.Adapter.Adapter_Cricket;
import com.android.glory.Adapter.Adapter_MyMatches;
import com.android.glory.Model.MyMatches.Datum;
import com.android.glory.Model.MyMatches.Example;
import com.android.glory.Pojo.Entity_Cricket;
import com.android.glory.R;
import com.android.glory.Retrofit.Api;
import com.android.glory.Retrofit.ApiClient;
import com.android.glory.Retrofit.CricketList.Match;
import com.android.glory.Retrofit.CricketList.cricketlistjson;
import com.android.glory.Utilites.ConnectionDetector;
import com.android.glory.Utilites.EndUrls;
import com.android.glory.Utilites.JSONParser;
import com.android.glory.Utilites.StaticUtils;
import com.android.glory.Utilites.sharedPrefs;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Cricket extends Fragment implements Adapter_MyMatches.OnItemClickcourses{

    RecyclerView recyclercricket;
    Adapter_MyMatches cricket_Adapter;
    Adapter_MyMatches.OnItemClickcourses mCallback2;
    private List<Datum> courses_offered_list;
    String matchId, homeTeamShortName, oppsShortName, homeTeamFullName, oppsFullName,homeflag,oppositeFlag;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_cricket, container, false);
        recyclercricket = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        courses_offered_list = new ArrayList<>();

        recyclercricket.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclercricket.setLayoutManager(mLayoutManager2);
        mCallback2 = this;
        matchesList();
        return rootView;
    }

    @Override
    public void OnItemClickcourses(int pos) {
        final Datum follow = courses_offered_list.get(pos);
        matchId = follow.getId().toString();
        homeTeamShortName = follow.getHometeam().getShortName();
        homeTeamFullName = follow.getHometeam().getName();

        oppsShortName = follow.getAwayteam().getShortName();
        oppsFullName = follow.getAwayteam().getName();

        homeflag = follow.getHometeam().getLogoUrl();
        oppositeFlag = follow.getAwayteam().getLogoUrl();
        Intent intent = new Intent(getActivity(), Activity_CricketList.class);
        intent.putExtra(StaticUtils.MATCH_ID,matchId);
        intent.putExtra(StaticUtils.HOME_TEAM_SHORT_NAME,homeTeamShortName);
        intent.putExtra(StaticUtils.HOME_TEAM_FULL_NAME,homeTeamFullName);
        intent.putExtra(StaticUtils.OPPOS_TEAM_SHORT_NAME,oppsShortName);
        intent.putExtra(StaticUtils.OPPOS_TEAM_FULL_NAME,oppsFullName);
        intent.putExtra(StaticUtils.HOME_FLAG,homeflag);
        intent.putExtra(StaticUtils.OPPOS_FLAG,oppositeFlag);
        startActivity(intent);

    }

    private void matchesList() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");

        final ProgressDialog pProgressDialog;
        pProgressDialog = new ProgressDialog(getActivity());
        pProgressDialog.setMessage("Please Wait ...");
//        pProgressDialog.setIndeterminate(false);
        pProgressDialog.setCancelable(false);
        pProgressDialog.show();

        Api api = ApiClient.getClient().create(Api.class);
        Call<Example> login = api.MatchesList("UPCOMING");

        login.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                pProgressDialog.dismiss();
                Log.e("testing", "status = " + response.body().getStatus());
                Log.e("testing", "response = " + response.body().getResponse().getType());
                  Log.e("testing","response = "+response.body());

                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    if (response.body().getResponse() == null) {

                    } else if (response.body().getResponse().getType().equals("data_found")) {
                        //                    List allItemscricket2 = response.body().getData();
                        courses_offered_list = response.body().getData();
                        cricket_Adapter = new Adapter_MyMatches(getActivity(), response.body().getData(), mCallback2);
                        recyclercricket.setAdapter(cricket_Adapter);
                        pProgressDialog.dismiss();

                    } else {
                        Toast.makeText(getActivity(), response.body().getResponse().getType(), Toast.LENGTH_SHORT).show();
                        pProgressDialog.dismiss();

                    }

                } else {
                    Log.e("testing", "error");
                    Toast.makeText(getActivity(), response.body().getResponse().getType(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                pProgressDialog.dismiss();

            }
        });


    }

}

