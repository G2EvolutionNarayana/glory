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

import com.glory.apk.Activity.CompletedActivity;
import com.glory.apk.Adapter.MyMatchesCompletedAdapter;
import com.glory.apk.MainActivity;
import com.glory.apk.Model.MyMatchesCompleted.MyMatchesCompletedDatum;
import com.glory.apk.Model.MyMatchesCompleted.MyMatchesCompletedExample;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_MyMatchesCompleted extends Fragment implements MyMatchesCompletedAdapter.OnItemClickcourses {

    RecyclerView recyclercricket;
    MyMatchesCompletedAdapter cricket_Adapter;
    MyMatchesCompletedAdapter.OnItemClickcourses mCallback2;
    private List<MyMatchesCompletedDatum> Maincourses_offered_list = new ArrayList<>();
    String matchId, homeTeamShortName, oppsShortName, homeTeamFullName, oppsFullName, homeflag, oppositeFlag;
    String viewuseremail;
    Button xBtncallMain;
    LinearLayout xLinLayMain;
    int page;
    int limit = 5;
    private boolean loading = true;
    private boolean notifySetChanged=false;
    LinearLayoutManager mLayoutManager;

    int pastVisiblesItems, visibleItemCount, totalItemCount;
    ArrayList<MyMatchesCompletedDatum> myMatchesCompletedData = new ArrayList();

    public Fragment_MyMatchesCompleted() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment__my_matches_completed, container, false);
        page = 1;
        notifySetChanged=false;
        Maincourses_offered_list.clear();
        recyclercricket = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        viewuseremail = sharedPrefs.getPreferences(getContext(), sharedPrefs.Pref_userId);
        recyclercricket.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclercricket.setLayoutManager(mLayoutManager);
        xBtncallMain = (Button) rootView.findViewById(R.id.xBtncallMain);
        xLinLayMain = (LinearLayout) rootView.findViewById(R.id.xLinLayMain);
        xBtncallMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        mCallback2 = this;
        matchesList(page, limit);
//        recyclercricket.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                if (scrollY==v.getMeasuredHeight()-v.getMeasuredHeight())
//            }
//        });
        recyclercricket.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            page++;

                            Log.v("...", "Last Item Wow !");
                            matchesList(page, limit);

                        }
                    }
                }
            }
        });

        return rootView;

    }

    @Override
    public void OnItemClickcourses(int pos) {
        final MyMatchesCompletedDatum follow = Maincourses_offered_list.get(pos);

        matchId = follow.getId().toString();
        homeTeamShortName = follow.getMyMatchesCompletedHometeam().getShortName();
        homeTeamFullName = follow.getMyMatchesCompletedHometeam().getName();

        oppsShortName = follow.getMyMatchesCompletedAwayteam().getShortName();
        oppsFullName = follow.getMyMatchesCompletedAwayteam().getName();

        homeflag = follow.getMyMatchesCompletedHometeam().getLogoUrl();
        oppositeFlag = follow.getMyMatchesCompletedAwayteam().getLogoUrl();

        Intent intent = new Intent(getActivity(), CompletedActivity.class);
        intent.putExtra(StaticUtils.MATCH_ID, matchId);

        startActivity(intent);


    }

    private void matchesList(int pagecount, int limit) {

        Log.e("testing", "strregisteredtoken = " + "matchesList");

        final ProgressDialog pProgressDialog;
        pProgressDialog = new ProgressDialog(getActivity());
        pProgressDialog.setMessage("Please Wait ...");
        pProgressDialog.setIndeterminate(false);
        pProgressDialog.setCancelable(false);
        pProgressDialog.show();
        viewuseremail = sharedPrefs.getPreferences(getContext(), sharedPrefs.Pref_userId);

        Api api = ApiClient.getClient().create(Api.class);
        Call<MyMatchesCompletedExample> login = api.MyMatchesListCompleted("completed", viewuseremail, pagecount, limit, "required");

        login.enqueue(new Callback<MyMatchesCompletedExample>() {
            @Override
            public void onResponse(Call<MyMatchesCompletedExample> call, Response<MyMatchesCompletedExample> response) {
                pProgressDialog.dismiss();
                if (!response.isSuccessful()) {
                    xLinLayMain.setVisibility(View.GONE);
                    recyclercricket.setVisibility(View.VISIBLE);
                    Log.e("testing", "status = " + "testing");

                } else if (response.body().getStatus().equals("success")) {
                    if (response.body().getMyMatchesCompletedResponse() == null) {

                    } else if (response.body().getMyMatchesCompletedResponse().getType().equals("data_found")) {

                        Log.e("testing", "status = " + response.body().getStatus());
                        Log.e("testing", "response = " + response.body().getMyMatchesCompletedResponse().getType());

                        for (int i = 0; i < response.body().getData().size(); i++) {
                            Maincourses_offered_list.add(response.body().getData().get(i));
                        }
                        if (Maincourses_offered_list.size() == 0) {
                            xLinLayMain.setVisibility(View.VISIBLE);
                            recyclercricket.setVisibility(View.GONE);
                        } else {
                            xLinLayMain.setVisibility(View.GONE);
                            recyclercricket.setVisibility(View.VISIBLE);
                            if (!notifySetChanged) {
                                notifySetChanged=true;
                                cricket_Adapter = new MyMatchesCompletedAdapter(getActivity(), Maincourses_offered_list, mCallback2);
                                recyclercricket.setAdapter(cricket_Adapter);
                            } else {
                                cricket_Adapter.notifyDataSetChanged();
                            }


                        }
                        loading = true;
                        pProgressDialog.dismiss();

                    } else {
                        pProgressDialog.dismiss();

                    }
                } else {


                    if (Maincourses_offered_list.size() == 0) {
                        xLinLayMain.setVisibility(View.VISIBLE);
                        recyclercricket.setVisibility(View.GONE);
                    } else {
                        xLinLayMain.setVisibility(View.GONE);
                        recyclercricket.setVisibility(View.VISIBLE);

                    }
                    pProgressDialog.dismiss();


                }
            }

            @Override
            public void onFailure(Call<MyMatchesCompletedExample> call, Throwable t) {
                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                pProgressDialog.dismiss();
            }
        });
    }
}