package com.android.glory.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

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

import com.android.glory.Activity.Activity_CricketList;
import com.android.glory.Activity.CompletedActivity;
import com.android.glory.Adapter.Adapter_MyMatches;
import com.android.glory.Adapter.MyMatchesCompletedAdapter;
import com.android.glory.MainActivity;
import com.android.glory.Model.MyMatches.Datum;
import com.android.glory.Model.MyMatches.Example;
import com.android.glory.Model.MyMatchesCompleted.MyMatchesCompletedDatum;
import com.android.glory.Model.MyMatchesCompleted.MyMatchesCompletedExample;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class  Fragment_MyMatchesCompleted extends Fragment implements MyMatchesCompletedAdapter.OnItemClickcourses{

    RecyclerView recyclercricket;
    MyMatchesCompletedAdapter cricket_Adapter;
    MyMatchesCompletedAdapter.OnItemClickcourses mCallback2;
    private List<MyMatchesCompletedDatum> courses_offered_list;
    String matchId, homeTeamShortName, oppsShortName, homeTeamFullName, oppsFullName,homeflag,oppositeFlag;
    String viewuseremail;
    Button xBtncallMain;
    LinearLayout xLinLayMain;
    public Fragment_MyMatchesCompleted() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment__my_matches_completed, container, false);
        recyclercricket = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        courses_offered_list = new ArrayList<>();
        viewuseremail = sharedPrefs.getPreferences(getContext(), sharedPrefs.Pref_userId);
        recyclercricket.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclercricket.setLayoutManager(mLayoutManager2);
        xBtncallMain=(Button)rootView.findViewById(R.id.xBtncallMain);
        xLinLayMain=(LinearLayout)rootView.findViewById(R.id.xLinLayMain);
        xBtncallMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        mCallback2 = this;
        matchesList();
        return rootView;

    }
    @Override
    public void OnItemClickcourses(int pos) {
        final MyMatchesCompletedDatum follow = courses_offered_list.get(pos);

        matchId = follow.getMatchId().toString();
        homeTeamShortName = follow.getMyMatchesCompletedHometeam().getShortName();
        homeTeamFullName = follow.getMyMatchesCompletedHometeam().getName();

        oppsShortName = follow.getMyMatchesCompletedAwayteam().getShortName();
        oppsFullName = follow.getMyMatchesCompletedAwayteam().getName();
        String dsfjdskg=follow.getMyMatchesCompletedHometeam().getLogoUrl();
        Log.e("testing","homeFlag ="+dsfjdskg);

        homeflag = follow.getMyMatchesCompletedHometeam().getLogoUrl();
        oppositeFlag = follow.getMyMatchesCompletedAwayteam().getLogoUrl();
        Log.e("testing","homeFlag ="+homeflag);

        Intent intent = new Intent(getActivity(), CompletedActivity.class);
        intent.putExtra(StaticUtils.MATCH_ID,matchId);

        startActivity(intent);

    }
    private void matchesList() {

        Log.e("testing","strregisteredtoken = "+"matchesList");

        final ProgressDialog pProgressDialog;
        pProgressDialog = new ProgressDialog(getActivity());
        pProgressDialog.setMessage("Please Wait ...");
        pProgressDialog.setIndeterminate(false);
        pProgressDialog.setCancelable(false);
        pProgressDialog.show();
        viewuseremail = sharedPrefs.getPreferences(getContext(), sharedPrefs.Pref_userId);

        Api api = ApiClient.getClient().create(Api.class);
        Call<MyMatchesCompletedExample> login = api.MyMatchesListCompleted("COMPLETED",viewuseremail);

        login.enqueue(new Callback<MyMatchesCompletedExample >() {
            @Override
            public void onResponse(Call<MyMatchesCompletedExample > call, Response<MyMatchesCompletedExample > response) {
                pProgressDialog.dismiss();
                Log.e("testing","status = "+response.body().getStatus());
                Log.e("testing","response = "+response.body().getMyMatchesCompletedResponse().getType());
                //  Log.e("testing","response = "+response.body().getData().getPageContent());

                if (response.body().getStatus() == null || response.body().getStatus().length() == 0){

                }else if (response.body().getStatus().equals("success")) {
                    if (response.body().getMyMatchesCompletedResponse() == null ){

                    }else if (response.body().getMyMatchesCompletedResponse().getType().equals("data_found")){
                        //                    List allItemscricket2 = response.body().getData();
                        courses_offered_list = response.body().getData();
                        if (courses_offered_list.size()==0){
                            xLinLayMain.setVisibility(View.VISIBLE);
                            recyclercricket.setVisibility(View.GONE);
                        }else {
                            xLinLayMain.setVisibility(View.GONE);
                            recyclercricket.setVisibility(View.VISIBLE);
                            cricket_Adapter = new MyMatchesCompletedAdapter(getActivity(),response.body().getData(),mCallback2);
                            recyclercricket.setAdapter(cricket_Adapter);
                        }
                        pProgressDialog.dismiss();

                    }else{
                        pProgressDialog.dismiss();
                        xLinLayMain.setVisibility(View.VISIBLE);
                        recyclercricket.setVisibility(View.GONE);
                    }

                } else  {
                    Log.e("testing","error");
                    xLinLayMain.setVisibility(View.VISIBLE);
                    recyclercricket.setVisibility(View.GONE);                }
            }

            @Override
            public void onFailure(Call<MyMatchesCompletedExample> call, Throwable t) {
                Toast.makeText(getActivity(),t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                pProgressDialog.dismiss();

            }
        });
    }

}