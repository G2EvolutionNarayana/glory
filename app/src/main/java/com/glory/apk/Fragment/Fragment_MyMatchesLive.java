package com.glory.apk.Fragment;

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

import com.glory.apk.Activity.Activity_live;
import com.glory.apk.Adapter.MymatchesLiveAdapter;
import com.glory.apk.MainActivity;
import com.glory.apk.Model.LiveMyMatchesModel.LiveMyMatchesDatum;
import com.glory.apk.Model.LiveMyMatchesModel.LiveMyMatchesExample;
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

import static com.facebook.FacebookSdk.getApplicationContext;

public class Fragment_MyMatchesLive extends Fragment implements MymatchesLiveAdapter.OnItemClickcourses{

    RecyclerView recyclercricket;

    MymatchesLiveAdapter cricket_Adapter;
    MymatchesLiveAdapter.OnItemClickcourses mCallback2;
    private List<LiveMyMatchesDatum> courses_offered_list;
    String matchId, homeTeamShortName, oppsShortName, homeTeamFullName, oppsFullName,homeflag,oppositeFlag;
    String viewuseremail;
    Button xBtncallMain;
    LinearLayout xLinLayMain;
    public Fragment_MyMatchesLive() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment__my_matches_live, container, false);
        courses_offered_list = new ArrayList<>();
        viewuseremail = sharedPrefs.getPreferences(getContext(), sharedPrefs.Pref_userId);
        recyclercricket = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        recyclercricket.setHasFixedSize(true);
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
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclercricket.setLayoutManager(mLayoutManager2);
        mCallback2 = this;
        matchesList();
        return rootView;
    }

    private void matchesList() {

        Log.e("testing","strregisteredtoken = "+"matchesList");

        final ProgressDialog pProgressDialog;
        pProgressDialog = new ProgressDialog(getActivity());
        pProgressDialog.setMessage("Please Wait ...");
        pProgressDialog.setIndeterminate(false);
        pProgressDialog.setCancelable(false);
        pProgressDialog.show();

        Api api = ApiClient.getClient().create(Api.class);
      //  Call<Example> login = api.MyMatchesList("LIVE",viewuseremail);
        viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);


        Call<LiveMyMatchesExample> login = api.MyMatchesListLive("started",viewuseremail);

        login.enqueue(new Callback<LiveMyMatchesExample >() {
            @Override
            public void onResponse(Call<LiveMyMatchesExample > call, Response<LiveMyMatchesExample > response) {
                pProgressDialog.dismiss();
                Log.e("testing","status = "+response.body().getStatus());
                Log.e("testing","response = "+response.body().getLiveMyMatchesResponse().getType());
                //  Log.e("testing","response = "+response.body().getData().getPageContent());

                if (response.body().getStatus() == null || response.body().getStatus().length() == 0){

                }else if (response.body().getStatus().equals("success")) {
                    if (response.body().getLiveMyMatchesResponse() == null ){

                    }else if (response.body().getLiveMyMatchesResponse().getType().equals("data_found")){
                        //                    List allItemscricket2 = response.body().getData();


                        courses_offered_list = response.body().getData();
                        if (courses_offered_list.size()==0){
                            xLinLayMain.setVisibility(View.VISIBLE);
                            recyclercricket.setVisibility(View.GONE);
                        }else {
                            xLinLayMain.setVisibility(View.GONE);
                            recyclercricket.setVisibility(View.VISIBLE);
                            cricket_Adapter = new MymatchesLiveAdapter(getActivity(),response.body().getData(),mCallback2);
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
                    recyclercricket.setVisibility(View.GONE);
                }



            }

            @Override
            public void onFailure(Call<LiveMyMatchesExample> call, Throwable t) {
                Toast.makeText(getActivity(),t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                pProgressDialog.dismiss();

            }
        });
    }

    @Override
    public void OnItemClickcourses(int pos) {
        final LiveMyMatchesDatum follow = courses_offered_list.get(pos);

        matchId = follow.getId().toString();
        homeTeamShortName = follow.getLiveMyMatchesHometeam().getShortName();
        homeTeamFullName = follow.getLiveMyMatchesHometeam().getName();

        oppsShortName = follow.getLiveMyMatchesAwayteam().getShortName();
        oppsFullName = follow.getLiveMyMatchesAwayteam().getName();
        homeflag = follow.getLiveMyMatchesHometeam().getLogoUrl();
        oppositeFlag = follow.getLiveMyMatchesAwayteam().getLogoUrl();
        Intent intent = new Intent(getActivity(), Activity_live.class);
        intent.putExtra(StaticUtils.MATCH_ID,matchId);
        intent.putExtra(StaticUtils.HOME_TEAM_SHORT_NAME,homeTeamShortName);
        intent.putExtra(StaticUtils.OPPOS_TEAM_SHORT_NAME,oppsShortName);

        startActivity(intent);
    }
}
