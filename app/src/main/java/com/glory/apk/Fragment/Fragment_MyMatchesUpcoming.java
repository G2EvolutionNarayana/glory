package com.glory.apk.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.glory.apk.Activity.ActivityUpcoming;
import com.glory.apk.Adapter.MymatchesUpcomingAdapter;
import com.glory.apk.MainActivity;
import com.glory.apk.Model.MyMatchesUpComing.MyMatchesUpComingDatum;
import com.glory.apk.Model.MyMatchesUpComing.MyMatchesUpComingExample;
import com.glory.apk.R;
import com.glory.apk.Retrofit.Api;
import com.glory.apk.Retrofit.ApiClient;
import com.glory.apk.Utilites.StaticUtils;
import com.glory.apk.Utilites.sharedPrefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_MyMatchesUpcoming extends Fragment implements MymatchesUpcomingAdapter.OnItemClickcourses {

    RecyclerView recyclercricket;

    MymatchesUpcomingAdapter cricket_Adapter;
    MymatchesUpcomingAdapter.OnItemClickcourses mCallback2;
    private List<MyMatchesUpComingDatum> courses_offered_list;
    String matchId, homeTeamShortName, oppsShortName, homeTeamFullName, oppsFullName,homeflag,oppositeFlag;
    String viewuseremail;
    Button xBtncallMain;
    LinearLayout xLinLayMain;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_mymatchesupcoming, container, false);
        recyclercricket = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        courses_offered_list = new ArrayList<>();
         viewuseremail = sharedPrefs.getPreferences(getContext(), sharedPrefs.Pref_userId);
        xBtncallMain=(Button)rootView.findViewById(R.id.xBtncallMain);
        xLinLayMain=(LinearLayout)rootView.findViewById(R.id.xLinLayMain);
        xBtncallMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        });
        recyclercricket.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclercricket.setLayoutManager(mLayoutManager2);
        mCallback2 = this;
        matchesList();
        return rootView;
    }


    @Override
    public void OnItemClickcourses(int pos) {
        final MyMatchesUpComingDatum follow = courses_offered_list.get(pos);
        matchId = follow.getId().toString();
        homeTeamShortName = follow.getMyMatchesUpComingHometeam().getShortName();
        homeTeamFullName = follow.getMyMatchesUpComingHometeam().getName();

        oppsShortName = follow.getMyMatchesUpComingAwayteam().getShortName();
        oppsFullName = follow.getMyMatchesUpComingAwayteam().getName();

        homeflag = follow.getMyMatchesUpComingHometeam().getLogoUrl();
        oppositeFlag = follow.getMyMatchesUpComingAwayteam().getLogoUrl();
        Intent intent = new Intent(getActivity(), ActivityUpcoming.class);
        intent.putExtra(StaticUtils.MATCH_ID,matchId);
//        intent.putExtra(StaticUtils.HOME_TEAM_SHORT_NAME,homeTeamShortName);
//        intent.putExtra(StaticUtils.HOME_TEAM_FULL_NAME,homeTeamFullName);
//        intent.putExtra(StaticUtils.OPPOS_TEAM_SHORT_NAME,oppsShortName);
//        intent.putExtra(StaticUtils.OPPOS_TEAM_FULL_NAME,oppsFullName);
//        intent.putExtra(StaticUtils.HOME_FLAG,homeflag);
//        intent.putExtra(StaticUtils.OPPOS_FLAG,oppositeFlag);
        startActivity(intent);

    }

    private void matchesList() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");

        final ProgressDialog pProgressDialog;
        pProgressDialog = new ProgressDialog(getActivity());
        pProgressDialog.setMessage("Please Wait ...");
        pProgressDialog.setIndeterminate(false);
        pProgressDialog.setCancelable(false);
        pProgressDialog.show();

        Api api = ApiClient.getClient().create(Api.class);
        Call<MyMatchesUpComingExample> login = api.MyMatchesListUpcoming("notstarted",viewuseremail);
        Log.e("testing", "viewuseremail = " + viewuseremail);


        login.enqueue(new Callback<MyMatchesUpComingExample>() {
            @Override
            public void onResponse(Call<MyMatchesUpComingExample> call, Response<MyMatchesUpComingExample> response) {
                pProgressDialog.dismiss();
                Log.e("testing", "status = " + response.body().getStatus());
                Log.e("testing", "response = " + response.body().getMyMatchesUpComingResponse( ).getType());
                //  Log.e("testing","response = "+response.body().getData().getPageContent());
                Log.e("testing", "Response = " + response.body());

                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    if (response.body().getMyMatchesUpComingResponse() == null) {

                    } else if (response.body().getMyMatchesUpComingResponse().getType().equals("data_found")) {
                        //                    List allItemscricket2 = response.body().getData();
                        courses_offered_list = response.body().getData();
                        if (courses_offered_list.size()==0){
                            xLinLayMain.setVisibility(View.VISIBLE);
                            recyclercricket.setVisibility(View.GONE);
                        }else {
                            xLinLayMain.setVisibility(View.GONE);
                            recyclercricket.setVisibility(View.VISIBLE);
                            cricket_Adapter = new MymatchesUpcomingAdapter(getActivity(), response.body().getData(), mCallback2);
                            recyclercricket.setAdapter(cricket_Adapter);
                        }

                        pProgressDialog.dismiss();

                    } else {
                        pProgressDialog.dismiss();
                        xLinLayMain.setVisibility(View.VISIBLE);
                        recyclercricket.setVisibility(View.GONE);
                    }

                } else {
                    Log.e("testing", "error");
                    xLinLayMain.setVisibility(View.VISIBLE);
                    recyclercricket.setVisibility(View.GONE);
                }


            }

            @Override
            public void onFailure(Call<MyMatchesUpComingExample> call, Throwable t) {
                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                pProgressDialog.dismiss();

            }
        });


    }

}
