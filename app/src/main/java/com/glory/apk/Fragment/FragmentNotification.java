package com.glory.apk.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.glory.apk.Adapter.NotificationAdapter;
import com.glory.apk.Model.NotificationModel.NotificationExample;
import com.glory.apk.R;
import com.glory.apk.Retrofit.Api;
import com.glory.apk.Retrofit.ApiClient;
import com.glory.apk.Utilites.sharedPrefs;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentNotification extends Fragment {
    private View mRootview;
    private RecyclerView xNotiRecyclerView;
    private RelativeLayout xRelayNoNotification;

    private LinearLayoutManager linearLayoutManager;
    private NotificationAdapter notificationAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootview=inflater.inflate(R.layout.fragment_coming_soon, container, false);
        return mRootview;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mInitwidgets();
        mCallPaymentToken();
    }

    private void mInitwidgets() {
        xRelayNoNotification=mRootview.findViewById(R.id.xRelayNoNotification);
        xNotiRecyclerView=mRootview.findViewById(R.id.xNotiRecyclerView);
        linearLayoutManager = new LinearLayoutManager(getContext());
        xNotiRecyclerView.setLayoutManager(linearLayoutManager);

    }

    private void mCallPaymentToken() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");
        final ProgressDialog pDialog;

        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Please Wait ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        String viewuseremail = sharedPrefs.getPreferences(getContext(), sharedPrefs.Pref_userId);
        Log.e("testing", "status = " + viewuseremail);


        Api api = ApiClient.getClient().create(Api.class);

        Call<NotificationExample> login = api.GetNotification(viewuseremail);

//        Call<LiveContestExample> login = api.LivePlayersDetails(matchId, viewuseremail);
        login.enqueue(new Callback<NotificationExample>() {
            @Override
            public void onResponse(Call<NotificationExample> call, Response<NotificationExample> response) {
                pDialog.dismiss();
                Log.e("testing", "status = " + response.body().getStatus());
                Log.e("testing", "response = " + response.body().getNotificationResponse().getMessage());
                //  Log.e("testing","response = "+response.body().getData().getPageContent());
                Log.e("testing", "response = " + response.body());
                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    if (response.body().getData().size()==0){
                        xRelayNoNotification.setVisibility(View.VISIBLE);
                        xNotiRecyclerView.setVisibility(View.GONE);
                    }else {
                        xRelayNoNotification.setVisibility(View.GONE);
                        xNotiRecyclerView.setVisibility(View.VISIBLE);
                        notificationAdapter=new NotificationAdapter(getContext(),response.body().getData());
                        xNotiRecyclerView.setAdapter(notificationAdapter);
                    }

                } else {
                    Log.e("testing", "error");
                    pDialog.dismiss();
//                    Toast.makeText(getContext(), response.body().getNotificationResponse().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NotificationExample> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                pDialog.dismiss();

            }
        });

    }
}
