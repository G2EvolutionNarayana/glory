package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.glory.apk.Fragment.EditTeam_1Fragment;
import com.glory.apk.Fragment.EditTeam_2Fragment;
import com.glory.apk.Model.EditPlayersListModel.EditPlayerAwayTeam;
import com.glory.apk.Model.EditPlayersListModel.EditPlayerHomeTeam;
import com.glory.apk.Model.EditTeamListModel.EditTeamDatum;
import com.glory.apk.Model.EditTeamListModel.EditTeamExample;
import com.glory.apk.Model.FinalPlayerSelectionModel;
import com.glory.apk.Model.OpposPlayerSelected;
import com.glory.apk.R;
import com.glory.apk.Retrofit.Api;
import com.glory.apk.Retrofit.ApiClient;
import com.glory.apk.Utilites.OnDataPass;
import com.glory.apk.Utilites.StaticUtils;
import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPlayerActivity extends AppCompatActivity implements OnDataPass {
    Button contin, xBtnPreview;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static String matchFinalId;
    TextView xTvHomeTeamName, xTvOpposTeamName,xTvCredits,xTvMax;
    public TextView xTvFinalCount, xTvHomeCount, xTvOppositeCount;
    ImageView XIvHome, xIvOppose;
    private ArrayList<FinalPlayerSelectionModel> finalPlayerList;
    public  String packageName;
    public static String MainmatchId;
    Dialog goBackDialog;
    String homeTeamUrl,awayTeamUrl,homeTeamName,oppositeTeamName;
    public static String ContestUserId,packageId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_player);
        StaticUtils.EditCREDITS5=40.0;
        StaticUtils.EditCREDITS7=55.0;
        StaticUtils.Edit_FINAL_COUNT = 0;
        StaticUtils.Edit_HomeTeamcount = 0;
        StaticUtils.Edit_OppoTeamcount = 0;
//        packageName="5";
        ContestUserId=getIntent().getStringExtra(StaticUtils.CONTEST_ID);
        Log.e("testing","ContestUserId "+ContestUserId);
        packageId=getIntent().getStringExtra(StaticUtils.PACKAGE_ID);
        goBackDialog = new Dialog(EditPlayerActivity.this);
        goBackDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setAboutDataListener(getIntent().getStringExtra(StaticUtils.PACKAGE));
   //     matchFinalId = StaticUtils.MATCH_ID + getIntent().getStringExtra(StaticUtils.MATCH_ID);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        xBtnPreview = (Button) findViewById(R.id.xBtnPreview);
        XIvHome = (ImageView) findViewById(R.id.XIvHome);
        xTvFinalCount = (TextView) findViewById(R.id.xTvFinalCount);
        xTvHomeCount = (TextView) findViewById(R.id.xTvHomeCount);
        xTvOppositeCount = (TextView) findViewById(R.id.xTvOppositeCount);
        xTvCredits=(TextView)findViewById(R.id.xTvCredits);
        xTvMax=(TextView) findViewById(R.id.xTvMax);

        xIvOppose = (ImageView) findViewById(R.id.xIvOppose);
//        packageName = getIntent().getStringExtra(StaticUtils.PACKAGE);
//        MainmatchId = getIntent().getStringExtra(StaticUtils.MATCH_ID);

        Log.e("testing", "homeFlag =" + getIntent().getStringExtra(StaticUtils.HOME_FLAG));

        tabLayout = (TabLayout) findViewById(R.id.tab);
        xTvHomeTeamName = (TextView) findViewById(R.id.xTvHomeTeamName);
        xTvOpposTeamName = (TextView) findViewById(R.id.xTvOpposTeamName);
        contin = (Button) findViewById(R.id.contin);
        matchesList();
        contin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalPlayerList = new ArrayList<>();

                for (int i = 0; i < EditTeam_1Fragment.playerDetailsList.size(); i++) {
                    EditPlayerHomeTeam playerDetails = EditTeam_1Fragment.playerDetailsList.get(i);
                    if (playerDetails.getIsselected()==1) {
                        FinalPlayerSelectionModel finalPlayerSelectionModel;
                        if (playerDetails.getEditPlayerPlayer().getPlayerType()!=null){
                            finalPlayerSelectionModel = new FinalPlayerSelectionModel(playerDetails.getEditPlayerPlayer().getFullName(), String.valueOf(playerDetails.getEditPlayerPlayer().getImageURL()), playerDetails.getEditPlayerPlayer().getId(),playerDetails.getEditPlayerPlayer().getPlayerType().toString());

                        }else {
                            finalPlayerSelectionModel = new FinalPlayerSelectionModel(playerDetails.getEditPlayerPlayer().getFullName(), String.valueOf(playerDetails.getEditPlayerPlayer().getImageURL()), playerDetails.getEditPlayerPlayer().getId(),"");

                        }
                        finalPlayerList.add(finalPlayerSelectionModel);
                    }
                }

                for (int i = 0; i < EditTeam_2Fragment.playerDetailsList.size(); i++) {
                    EditPlayerAwayTeam playerDetails = EditTeam_2Fragment.playerDetailsList.get(i);
                    if (playerDetails.getIsselected()==1) {
                        FinalPlayerSelectionModel finalPlayerSelectionModel;

                        if (playerDetails.getPlayer().getPlayerType()!=null){
                            finalPlayerSelectionModel = new FinalPlayerSelectionModel(playerDetails.getPlayer().getFullName(), String.valueOf(playerDetails.getPlayer().getImageURL()), playerDetails.getPlayer().getId(),playerDetails.getPlayer().getPlayerType().toString());

                        }else {
                            finalPlayerSelectionModel = new FinalPlayerSelectionModel(playerDetails.getPlayer().getFullName(), String.valueOf(playerDetails.getPlayer().getImageURL()), playerDetails.getPlayer().getId(),"");

                        }
                        finalPlayerList.add(finalPlayerSelectionModel);
                    }
                }
                Log.e("testing", "opposePlayers =" + finalPlayerList.size());

                Log.e("testing", "opposePlayers =" + finalPlayerList.size());

                if (packageId.equals("1")) {
                    if (finalPlayerList.size() == 5) {
                        Intent intent = new Intent(EditPlayerActivity.this, EditHitterSelectionActivity.class);
                        Bundle args = new Bundle();
                        args.putSerializable("ARRAYLIST", (Serializable) finalPlayerList);
                        intent.putExtra("BUNDLE", args);
                        intent.putExtra(StaticUtils.MATCH_ID, ContestUserId);
                        intent.putExtra(StaticUtils.PACKAGE_ID, getIntent().getStringExtra(StaticUtils.PACKAGE_ID));

                        args.putString(StaticUtils.HOME_FLAG, homeTeamUrl);
                        args.putString(StaticUtils.OPPOS_FLAG, awayTeamUrl);
                        args.putString(StaticUtils.HOME_TEAM_SHORT_NAME, xTvHomeTeamName.getText().toString());
                        args.putString(StaticUtils.OPPOS_TEAM_SHORT_NAME,xTvOpposTeamName.getText().toString());

                        args.putString(StaticUtils.HOME_TEAM_COUNT,xTvHomeCount.getText().toString());
                        args.putString(StaticUtils.OPPOSITE_TEAM_COUNT,xTvOppositeCount.getText().toString());
                        Log.e("testing","Packageid "+ getIntent().getStringExtra(StaticUtils.PACKAGE_ID));


                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Maximum 5 Players you can select", Toast.LENGTH_LONG).show();
                    }
                } else {
                    if (finalPlayerList.size() == 7) {
                        Intent intent = new Intent(EditPlayerActivity.this, EditHitterSelectionActivity.class);
                        Bundle args = new Bundle();
                        args.putSerializable("ARRAYLIST", (Serializable) finalPlayerList);
                        intent.putExtra("BUNDLE", args);
                        intent.putExtra(StaticUtils.MATCH_ID, ContestUserId);
                        intent.putExtra(StaticUtils.PACKAGE_ID, getIntent().getStringExtra(StaticUtils.PACKAGE_ID));

                        args.putString(StaticUtils.HOME_FLAG, homeTeamUrl);
                        args.putString(StaticUtils.OPPOS_FLAG, awayTeamUrl);
                        args.putString(StaticUtils.HOME_TEAM_SHORT_NAME, xTvHomeTeamName.getText().toString());
                        args.putString(StaticUtils.OPPOS_TEAM_SHORT_NAME,xTvOpposTeamName.getText().toString());
                        args.putString(StaticUtils.HOME_TEAM_COUNT,xTvHomeCount.getText().toString());
                        args.putString(StaticUtils.OPPOSITE_TEAM_COUNT,xTvOppositeCount.getText().toString());
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Maximum 7 Players you can select", Toast.LENGTH_LONG).show();
                    }
                }


            }
        });
        xBtnPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalPlayerList = new ArrayList<>();
                for (int i = 0; i < EditTeam_1Fragment.playerDetailsList.size(); i++) {
                    EditPlayerHomeTeam playerDetails = EditTeam_1Fragment.playerDetailsList.get(i);
                    if (playerDetails.getIsselected()==1) {
                        FinalPlayerSelectionModel finalPlayerSelectionModel;

                        if (playerDetails.getEditPlayerPlayer().getPlayerType()!=null){

                            finalPlayerSelectionModel = new FinalPlayerSelectionModel(playerDetails.getEditPlayerPlayer().getFullName(), String.valueOf(playerDetails.getEditPlayerPlayer().getImageURL()), playerDetails.getEditPlayerPlayer().getId(),playerDetails.getEditPlayerPlayer().getPlayerType().toString());

                        }else {
                            finalPlayerSelectionModel = new FinalPlayerSelectionModel(playerDetails.getEditPlayerPlayer().getFullName(), String.valueOf(playerDetails.getEditPlayerPlayer().getImageURL()), playerDetails.getEditPlayerPlayer().getId(),"");

                        }
                        finalPlayerList.add(finalPlayerSelectionModel);
                    }
                }

                for (int i = 0; i < EditTeam_2Fragment.playerDetailsList.size(); i++) {
                    EditPlayerAwayTeam playerDetails = EditTeam_2Fragment.playerDetailsList.get(i);
                    if (playerDetails.getIsselected()==1) {
                        FinalPlayerSelectionModel finalPlayerSelectionModel;

                        if (playerDetails.getPlayer().getPlayerType()!=null){
                            finalPlayerSelectionModel = new FinalPlayerSelectionModel(playerDetails.getPlayer().getFullName(), String.valueOf(playerDetails.getPlayer().getImageURL()), playerDetails.getPlayer().getId(),playerDetails.getPlayer().getPlayerType().toString());

                        }else {
                            finalPlayerSelectionModel = new FinalPlayerSelectionModel(playerDetails.getPlayer().getFullName(), String.valueOf(playerDetails.getPlayer().getImageURL()), playerDetails.getPlayer().getId(),"");

                        }
                        finalPlayerList.add(finalPlayerSelectionModel);
                    }
                }

                if (packageId.equals("1")){
                    Intent intent = new Intent(EditPlayerActivity.this, GroundPreviewActivity5.class);
                    Bundle args = new Bundle();
                    args.putSerializable("ARRAYLIST", (Serializable) finalPlayerList);
                    args.putString(StaticUtils.HOME_FLAG, getIntent().getStringExtra(StaticUtils.HOME_FLAG));
                    args.putString(StaticUtils.OPPOS_FLAG, getIntent().getStringExtra(StaticUtils.OPPOS_FLAG));
                    args.putString(StaticUtils.HOME_TEAM_SHORT_NAME, getIntent().getStringExtra(StaticUtils.HOME_TEAM_SHORT_NAME));
                    args.putString(StaticUtils.OPPOS_TEAM_SHORT_NAME, getIntent().getStringExtra(StaticUtils.OPPOS_TEAM_SHORT_NAME));
                    args.putString(StaticUtils.HOME_TEAM_COUNT,xTvHomeCount.getText().toString());
                    args.putString(StaticUtils.OPPOSITE_TEAM_COUNT,xTvOppositeCount.getText().toString());
                    intent.putExtra("BUNDLE", args);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(EditPlayerActivity.this, GroundPreviewActivity7.class);
                    Bundle args = new Bundle();
                    args.putSerializable("ARRAYLIST", (Serializable) finalPlayerList);
                    args.putString(StaticUtils.HOME_FLAG, getIntent().getStringExtra(StaticUtils.HOME_FLAG));
                    args.putString(StaticUtils.OPPOS_FLAG, getIntent().getStringExtra(StaticUtils.OPPOS_FLAG));
                    args.putString(StaticUtils.HOME_TEAM_SHORT_NAME, getIntent().getStringExtra(StaticUtils.HOME_TEAM_SHORT_NAME));
                    args.putString(StaticUtils.OPPOS_TEAM_SHORT_NAME, getIntent().getStringExtra(StaticUtils.OPPOS_TEAM_SHORT_NAME));
                    args.putString(StaticUtils.HOME_TEAM_COUNT,xTvHomeCount.getText().toString());
                    args.putString(StaticUtils.OPPOSITE_TEAM_COUNT,xTvOppositeCount.getText().toString());
                    intent.putExtra("BUNDLE", args);
                    startActivity(intent);
                }

            }
        });
    }



    private void setupViewPager(ViewPager viewPager) {

        EditPlayerActivity.ViewPagerAdapter adapter = new EditPlayerActivity.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new EditTeam_1Fragment(), homeTeamName.toString());
        adapter.addFragment(new EditTeam_2Fragment(), oppositeTeamName.toString());
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onDataPass(String finalValue, String home, String opposite, Double credits) {
        if (packageId.equals("1")) {
            xTvFinalCount.setText(finalValue + "/5");
            xTvHomeCount.setText(home);
            String dds=String.valueOf(StaticUtils.Edit_FINAL_COUNT);

            xTvOppositeCount.setText(opposite);
            xTvCredits.setText(String.valueOf( String.format("%.1f", credits)));

            xTvMax.setText( "Max "+finalValue+ "players from a team");

        } else {
            xTvFinalCount.setText(finalValue + "/7");
            xTvHomeCount.setText(home);
            xTvOppositeCount.setText(opposite);
            xTvCredits.setText(String.valueOf( String.format("%.1f", credits)));
            xTvMax.setText( "Max "+finalValue+ "players from a team");

        }
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

    @Override
    public void onBackPressed() {

        goBackDialog.setContentView(R.layout.team_cancel_dialog);
        goBackDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        TextView textContinue = (TextView) goBackDialog.findViewById(R.id.textContinue);

        ImageView imgcancel = (ImageView) goBackDialog.findViewById(R.id.xIvCancel);

        textContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticUtils.Edit_FINAL_COUNT = 0;
                StaticUtils.Edit_HomeTeamcount = 0;
                StaticUtils.Edit_OppoTeamcount = 0;
                goBackDialog.cancel();

                EditPlayerActivity.super.onBackPressed();
            }
        });
        imgcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBackDialog.cancel();

            }
        });
        Window window = goBackDialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        goBackDialog.setCancelable(false);
        goBackDialog.show();

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        StaticUtils.Edit_HomeTeamcount = 0;
//        StaticUtils.Edit_OppoTeamcount = 0;
//
//    }
    private void matchesList() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");
        final ProgressDialog pDialog;
        pDialog = new ProgressDialog(EditPlayerActivity.this);
        pDialog.setMessage("Please Wait ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        Log.e("testing", "ContestUserId = " + ContestUserId);

        Api api = ApiClient.getClient().create(Api.class);
        Call<EditTeamExample> login = api.EditMatchesList(getIntent().getStringExtra(StaticUtils.MATCH_ID));

        login.enqueue(new Callback<EditTeamExample>() {
            @Override
            public void onResponse(Call<EditTeamExample> call, Response<EditTeamExample> response) {
                pDialog.dismiss();
                Log.e("testing", "status = " + response.body().getStatus());
                Log.e("testing", "response = " + response.body().getEditTeamResponse().getMessage());
                //  Log.e("testing","response = "+response.body().getData().getPageContent());
                Log.e("testing", "response = " + response.body());
                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    if (response.body().getEditTeamFilters() == null) {

                    } else if (response.body().getEditTeamResponse().getType().equals("data_found")) {
//                        Log.e("testing", "dateumList.size = " + dateumList.size());
//                        dateumList=response.body().getData();

                        List<EditTeamDatum> contestList = response.body().getData();
                        xTvHomeTeamName.setText(contestList.get(0).getEditTeamHometeam().getShortName());
                        homeTeamName=contestList.get(0).getEditTeamHometeam().getName();

                        if (contestList.get(0).getEditTeamHometeam().getLogoUrl() == null || contestList.get(0).getEditTeamHometeam().getLogoUrl().length() == 0) {
                            Glide.with(getApplicationContext())
                                    .load((R.drawable.default_team_logo)).placeholder(R.drawable.default_team_logo)
                                    .into(XIvHome);
                            Log.e("testing", "getImageUrl = " + "null");
                        } else {
                            homeTeamUrl=contestList.get(0).getEditTeamHometeam().getLogoUrl();
                            Glide.with(getApplicationContext())
                                    .load(Uri.parse(contestList.get(0).getEditTeamHometeam().getLogoUrl()))
                                    .error(R.drawable.default_team_logo)
                                    .into(XIvHome);
                            Log.e("testing", "getImageUrl = " + "image");
                        }
                        xTvOpposTeamName.setText(contestList.get(0).getEditTeamAwayteam().getShortName());
                        oppositeTeamName=contestList.get(0).getEditTeamAwayteam().getName();

                        if (contestList.get(0).getEditTeamAwayteam().getLogoUrl() == null || contestList.get(0).getEditTeamAwayteam().getLogoUrl().length() == 0) {
                            Glide.with(getApplicationContext())
                                    .load((R.drawable.default_team_logo)).placeholder(R.drawable.default_team_logo)
                                    .into(xIvOppose);
                            Log.e("testing", "getImageUrl = " + "null");

                        } else {
                            awayTeamUrl=contestList.get(0).getEditTeamAwayteam().getLogoUrl();

                            Glide.with(getApplicationContext())
                                    .load(Uri.parse(contestList.get(0).getEditTeamAwayteam().getLogoUrl()))
                                    .error(R.drawable.default_team_logo)
                                    .into(xIvOppose);
                            Log.e("testing", "getImageUrl = " + "image");
                        }
                        setupViewPager(viewPager);
                        tabLayout.setupWithViewPager(viewPager);

                        pDialog.dismiss();

                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getEditTeamResponse().getType(), Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();

                    }

                } else {
                    Log.e("testing", "error");
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.body().getEditTeamResponse().getType(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EditTeamExample> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                pDialog.dismiss();

            }
        });
    }


}
