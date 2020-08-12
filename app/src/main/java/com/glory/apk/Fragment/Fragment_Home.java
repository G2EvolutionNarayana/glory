package com.glory.apk.Fragment;


import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.glory.apk.MainActivity;
import com.glory.apk.Model.AboutUs.AboutExample;
import com.glory.apk.R;
import com.glory.apk.Retrofit.Api;
import com.glory.apk.Retrofit.ApiClient;
import com.glory.apk.Utilites.sharedPrefs;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

public class Fragment_Home extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ProgressDialog dialog;
    TextView textwallet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) rootView.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        textwallet = (TextView) getActivity().findViewById(R.id.textwallet);

        Activity_Profile();
        return rootView;
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new Fragment_Cricket(), "Cricket");
        // adapter.addFragment(new Fragment_Tab1(), "Kabadi");
        // adapter.addFragment(new Fragment_Tab1(), "Football");

        viewPager.setAdapter(adapter);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override

        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }


    }

    private void Activity_Profile() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");

        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Please Wait ...");
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.show();

//        Api api = ApiClient.getClient().create(Api.class);
//        Call<AboutExample> login = api.PlayersList("1");


        String viewuseremail = sharedPrefs.getPreferences(getContext(), sharedPrefs.Pref_userId);
        Log.e("testing", "viewuseremail = " + viewuseremail);

        Api api = ApiClient.getClient().create(Api.class);
        Call<AboutExample> login = api.aboutusjson(viewuseremail);
        login.enqueue(new Callback<AboutExample>() {
            @Override
            public void onResponse(Call<AboutExample> call, Response<AboutExample> response) {
                dialog.dismiss();


                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    Log.e("testing", "status = " + response.body().getStatus());
                    Log.e("testing", "response = " + response.body().getAboutResponse().getType());
                    //  Log.e("testing","response = "+response.body().getData().getPageContent());

                    Log.e("testing", "response = " + response.body());
                    if (response.body().getAboutResponse() == null) {

                    } else if (response.body().getAboutResponse().getType().equals("data_available")) {

                        if ((response.body().getAboutData().getWalletAmount() == null)) {
                            textwallet.setText("\u20B9" + "0");

                        } else {
                            textwallet.setText(("\u20B9" + " " + String.valueOf(response.body().getAboutData().getWalletAmount())));
                            sharedPrefs.savepref(getContext(), sharedPrefs.Wallet_Amount, response.body().getAboutData().getWalletAmount().toString());

                        }

                        if ((response.body().getAboutData().getWinning_amount() == null)) {
                            sharedPrefs.savepref(getApplicationContext(), sharedPrefs.AVAILABLE_WITHDRAW, "0");

                        } else {
                            sharedPrefs.savepref(getApplicationContext(), sharedPrefs.AVAILABLE_WITHDRAW, response.body().getAboutData().getWinning_amount().toString());

                        }

                        if ((response.body().getAboutData().getDeposited_amount() == null)) {
                            sharedPrefs.savepref(getApplicationContext(), sharedPrefs.AVAILABLE_DEPOSIT,"0");

                        } else {
                            sharedPrefs.savepref(getApplicationContext(), sharedPrefs.AVAILABLE_DEPOSIT, response.body().getAboutData().getDeposited_amount().toString());

                        }

                    } else {
                        Toast.makeText(getContext(), response.body().getAboutResponse().getType(), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Log.e("testing", "error");
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.body().getAboutResponse().getType(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<AboutExample> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        });
    }

}
