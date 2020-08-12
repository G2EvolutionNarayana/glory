package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
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

import com.glory.apk.Fragment.Team_1_Fragment;
import com.glory.apk.Fragment.Team_2_Fragment;
import com.glory.apk.Model.FinalPlayerSelectionModel;
import com.glory.apk.Model.OpposPlayerSelected;
import com.glory.apk.Model.PlayersList.PlayersAwayTeam;
import com.glory.apk.Model.PlayersList.PlayersListHomeTeam;
import com.glory.apk.R;
import com.glory.apk.Utilites.OnDataPass;
import com.glory.apk.Utilites.StaticUtils;
import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Activity_SelectPlayer extends AppCompatActivity implements OnDataPass {

    Button contin, xBtnPreview;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static String matchFinalId;
    TextView xTvHomeTeamName, xTvOpposTeamName, xTvMax, xTvCreditsLeft;
    public TextView xTvFinalCount, xTvHomeCount, xTvOppositeCount;
    ImageView XIvHome, xIvOppose;
    private ArrayList<FinalPlayerSelectionModel> finalPlayerList;
    public static String packageName;
    public static String MainmatchId;
    Dialog goBackDialog;

//    private OnAboutDataReceivedListener mAboutDataListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_player);
        goBackDialog = new Dialog(Activity_SelectPlayer.this);
        goBackDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        StaticUtils.CREDITS5 = 40.0;
        StaticUtils.CREDITS7 = 55.0;

//        setAboutDataListener(getIntent().getStringExtra(StaticUtils.PACKAGE));
        matchFinalId = StaticUtils.MATCH_ID + getIntent().getStringExtra(StaticUtils.MATCH_ID);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        xBtnPreview = (Button) findViewById(R.id.xBtnPreview);
        XIvHome = (ImageView) findViewById(R.id.XIvHome);
        xTvFinalCount = (TextView) findViewById(R.id.xTvFinalCount);
        xTvHomeCount = (TextView) findViewById(R.id.xTvHomeCount);
        xTvOppositeCount = (TextView) findViewById(R.id.xTvOppositeCount);
        xTvMax = (TextView) findViewById(R.id.xTvMax);

        xIvOppose = (ImageView) findViewById(R.id.xIvOppose);
        xTvCreditsLeft = (TextView) findViewById(R.id.xTvCreditsLeft);


        packageName = getIntent().getStringExtra(StaticUtils.PACKAGE);
        MainmatchId = getIntent().getStringExtra(StaticUtils.MATCH_ID);
        if (packageName.equals("5")) {
            xTvMax.setText("Max" + " 3 " + "players from a team");
            xTvCreditsLeft.setText("40.0");

        } else {
            xTvMax.setText("Max" + " 4 " + "players from a team");
            xTvCreditsLeft.setText("55.0");

        }

        if (getIntent().getStringExtra(StaticUtils.HOME_FLAG) == null || getIntent().getStringExtra(StaticUtils.HOME_FLAG).length() == 0) {
            Glide.with(getApplicationContext())
                    .load((R.drawable.default_team_logo)).placeholder(R.drawable.default_team_logo)
                    .into(XIvHome);
            Log.e("testing", "getImageUrl = " + "null");

        } else {
            Glide.with(getApplicationContext())
                    .load(Uri.parse(getIntent().getStringExtra(StaticUtils.HOME_FLAG)))
                    .error(R.drawable.default_team_logo)
                    .into(XIvHome);
            Log.e("testing", "getImageUrl = " + "image");
        }
        if (getIntent().getStringExtra(StaticUtils.OPPOS_FLAG) == null || getIntent().getStringExtra(StaticUtils.OPPOS_FLAG).length() == 0) {
            Glide.with(getApplicationContext())
                    .load((R.drawable.default_team_logo)).placeholder(R.drawable.default_team_logo)
                    .into(xIvOppose);
            Log.e("testing", "getImageUrl = " + "null");

        } else {
            Glide.with(getApplicationContext())
                    .load(Uri.parse(getIntent().getStringExtra(StaticUtils.OPPOS_FLAG)))
                    .error(R.drawable.default_team_logo)
                    .into(xIvOppose);
            Log.e("testing", "getImageUrl = " + "image");
        }
        Log.e("testing", "homeFlag =" + getIntent().getStringExtra(StaticUtils.HOME_FLAG));

        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);
        xTvHomeTeamName = (TextView) findViewById(R.id.xTvHomeTeamName);
        xTvOpposTeamName = (TextView) findViewById(R.id.xTvOpposTeamName);
        xTvHomeTeamName.setText(getIntent().getStringExtra(StaticUtils.HOME_TEAM_SHORT_NAME));
        xTvOpposTeamName.setText(getIntent().getStringExtra(StaticUtils.OPPOS_TEAM_SHORT_NAME));

        contin = (Button) findViewById(R.id.contin);
        contin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new Team_1_Fragment().method();
                finalPlayerList = new ArrayList<>();
//                for (int i=0;i<homePlayers.size();i++){
//                    HomePlayersSelected list=homePlayers.get(i);
//                   if (list.getSelected()){
//                       FinalPlayerSelectionModel finalPlayerSelectionModel=new FinalPlayerSelectionModel(list.getName(),list.getImage(),list.getId());
//                       finalPlayerList.add(finalPlayerSelectionModel);
//                   }
//                }
                for (int i = 0; i < Team_1_Fragment.playerDetailsList.size(); i++) {
                    PlayersListHomeTeam playerDetails = Team_1_Fragment.playerDetailsList.get(i);
                    String fdgds = playerDetails.getMatchId().toString();
                    if (playerDetails.getSelected()) {
                        FinalPlayerSelectionModel finalPlayerSelectionModel;
                        if (playerDetails.getPlayer().getPlayerType() != null) {
                            finalPlayerSelectionModel = new FinalPlayerSelectionModel(playerDetails.getPlayer().getFullName(), playerDetails.getPlayer().getImageURL(), playerDetails.getPlayer().getId(), playerDetails.getPlayer().getPlayerType().toString());

                        } else {
                            finalPlayerSelectionModel = new FinalPlayerSelectionModel(playerDetails.getPlayer().getFullName(), playerDetails.getPlayer().getImageURL(), playerDetails.getPlayer().getId(), "");
                        }
                        finalPlayerList.add(finalPlayerSelectionModel);
                    }
                }

                for (int i = 0; i < Team_2_Fragment.playerDetailsList.size(); i++) {
                    PlayersAwayTeam playerDetails = Team_2_Fragment.playerDetailsList.get(i);
                    if (playerDetails.getSelected()) {

                        FinalPlayerSelectionModel finalPlayerSelectionModel;
                        if (playerDetails.getPlayer().getPlayerType() != null) {
                            finalPlayerSelectionModel = new FinalPlayerSelectionModel(playerDetails.getPlayer().getFullName(), String.valueOf(playerDetails.getPlayer().getImageURL()), playerDetails.getPlayer().getId(), playerDetails.getPlayer().getPlayerType().toString());

                        } else {
                            finalPlayerSelectionModel = new FinalPlayerSelectionModel(playerDetails.getPlayer().getFullName(), String.valueOf(playerDetails.getPlayer().getImageURL()), playerDetails.getPlayer().getId(), "");

                        }

                        finalPlayerList.add(finalPlayerSelectionModel);
                    }
                }
                Log.e("testing", "opposePlayers =" + finalPlayerList.size());

//                for (int i = 0; i < StaticUtils.opposePlayers.size(); i++) {
//                    OpposPlayerSelected list = StaticUtils.opposePlayers.get(i);
//                    if (list.getSelected()) {
//                        FinalPlayerSelectionModel finalPlayerSelectionModel = new FinalPlayerSelectionModel(list.getName(), list.getImage(), list.getId());
//                        finalPlayerList.add(finalPlayerSelectionModel);
//                    }
//                }
                Log.e("testing", "opposePlayers =" + finalPlayerList.size());

                if (packageName.equals("5")) {
                    if (finalPlayerList.size() == 5) {
                        Intent intent = new Intent(Activity_SelectPlayer.this, Activity_PowerHitter.class);
                        Bundle args = new Bundle();
                        args.putSerializable("ARRAYLIST", (Serializable) finalPlayerList);
                        intent.putExtra("BUNDLE", args);
                        intent.putExtra(StaticUtils.MATCH_ID, getIntent().getStringExtra(StaticUtils.MATCH_ID));
                        intent.putExtra(StaticUtils.PACKAGE_ID, getIntent().getStringExtra(StaticUtils.PACKAGE_ID));
                        args.putString(StaticUtils.HOME_FLAG, getIntent().getStringExtra(StaticUtils.HOME_FLAG));
                        args.putString(StaticUtils.OPPOS_FLAG, getIntent().getStringExtra(StaticUtils.OPPOS_FLAG));
                        args.putString(StaticUtils.HOME_TEAM_SHORT_NAME, getIntent().getStringExtra(StaticUtils.HOME_TEAM_SHORT_NAME));
                        args.putString(StaticUtils.OPPOS_TEAM_SHORT_NAME, getIntent().getStringExtra(StaticUtils.OPPOS_TEAM_SHORT_NAME));
                        args.putString(StaticUtils.HOME_TEAM_COUNT, xTvHomeCount.getText().toString());
                        args.putString(StaticUtils.OPPOSITE_TEAM_COUNT, xTvOppositeCount.getText().toString());
                        Log.e("testing", "Packageid " + getIntent().getStringExtra(StaticUtils.PACKAGE_ID));
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Maximum 5 Players you can select", Toast.LENGTH_LONG).show();
                    }
                } else {
                    if (finalPlayerList.size() == 7) {
                        Intent intent = new Intent(Activity_SelectPlayer.this, Activity_PowerHitter.class);
                        Bundle args = new Bundle();
                        args.putSerializable("ARRAYLIST", (Serializable) finalPlayerList);
                        intent.putExtra("BUNDLE", args);
                        intent.putExtra(StaticUtils.MATCH_ID, getIntent().getStringExtra(StaticUtils.MATCH_ID));
                        intent.putExtra(StaticUtils.PACKAGE_ID, getIntent().getStringExtra(StaticUtils.PACKAGE_ID));
//                        Toast.makeText(getApplicationContext(),getIntent().getStringExtra(StaticUtils.PACKAGE_ID),Toast.LENGTH_LONG).show();
                        args.putString(StaticUtils.HOME_FLAG, getIntent().getStringExtra(StaticUtils.HOME_FLAG));
                        args.putString(StaticUtils.OPPOS_FLAG, getIntent().getStringExtra(StaticUtils.OPPOS_FLAG));
                        args.putString(StaticUtils.HOME_TEAM_SHORT_NAME, getIntent().getStringExtra(StaticUtils.HOME_TEAM_SHORT_NAME));
                        args.putString(StaticUtils.OPPOS_TEAM_SHORT_NAME, getIntent().getStringExtra(StaticUtils.OPPOS_TEAM_SHORT_NAME));
                        args.putString(StaticUtils.HOME_TEAM_COUNT, xTvHomeCount.getText().toString());
                        args.putString(StaticUtils.OPPOSITE_TEAM_COUNT, xTvOppositeCount.getText().toString());
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
                for (int i = 0; i < Team_1_Fragment.playerDetailsList.size(); i++) {
                    PlayersListHomeTeam playerDetails = Team_1_Fragment.playerDetailsList.get(i);
                    if (playerDetails.getSelected()) {
                        FinalPlayerSelectionModel finalPlayerSelectionModel;
                        if (playerDetails.getPlayer().getPlayerType() != null) {
                            finalPlayerSelectionModel = new FinalPlayerSelectionModel(playerDetails.getPlayer().getFullName(), String.valueOf(playerDetails.getPlayer().getImageURL()), playerDetails.getPlayer().getId(), playerDetails.getPlayer().getPlayerType().toString());

                        } else {
                            finalPlayerSelectionModel = new FinalPlayerSelectionModel(playerDetails.getPlayer().getFullName(), String.valueOf(playerDetails.getPlayer().getImageURL()), playerDetails.getPlayer().getId(), "");

                        }
                        finalPlayerList.add(finalPlayerSelectionModel);
                    }
                }

                for (int i = 0; i < Team_2_Fragment.playerDetailsList.size(); i++) {
                    PlayersAwayTeam playerDetails = Team_2_Fragment.playerDetailsList.get(i);
                    if (playerDetails.getSelected()) {
                        FinalPlayerSelectionModel finalPlayerSelectionModel;
                        if (playerDetails.getPlayer().getPlayerType() != null) {
                            finalPlayerSelectionModel = new FinalPlayerSelectionModel(playerDetails.getPlayer().getFullName(), String.valueOf(playerDetails.getPlayer().getImageURL()), playerDetails.getPlayer().getId(), playerDetails.getPlayer().getPlayerType().toString());

                        } else {
                            finalPlayerSelectionModel = new FinalPlayerSelectionModel(playerDetails.getPlayer().getFullName(), String.valueOf(playerDetails.getPlayer().getImageURL()), playerDetails.getPlayer().getId(), "");

                        }
                        finalPlayerList.add(finalPlayerSelectionModel);
                    }
                }
                if (packageName.equals("5")) {
                    Intent intent = new Intent(Activity_SelectPlayer.this, GroundPreviewActivity5.class);
                    Bundle args = new Bundle();
                    args.putSerializable("ARRAYLIST", (Serializable) finalPlayerList);
                    args.putString(StaticUtils.HOME_FLAG, getIntent().getStringExtra(StaticUtils.HOME_FLAG));
                    args.putString(StaticUtils.OPPOS_FLAG, getIntent().getStringExtra(StaticUtils.OPPOS_FLAG));
                    args.putString(StaticUtils.HOME_TEAM_SHORT_NAME, getIntent().getStringExtra(StaticUtils.HOME_TEAM_SHORT_NAME));
                    args.putString(StaticUtils.OPPOS_TEAM_SHORT_NAME, getIntent().getStringExtra(StaticUtils.OPPOS_TEAM_SHORT_NAME));
                    args.putString(StaticUtils.HOME_TEAM_COUNT, xTvHomeCount.getText().toString());
                    args.putString(StaticUtils.OPPOSITE_TEAM_COUNT, xTvOppositeCount.getText().toString());
                    intent.putExtra("BUNDLE", args);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(Activity_SelectPlayer.this, GroundPreviewActivity7.class);
                    Bundle args = new Bundle();
                    args.putSerializable("ARRAYLIST", (Serializable) finalPlayerList);
                    args.putString(StaticUtils.HOME_FLAG, getIntent().getStringExtra(StaticUtils.HOME_FLAG));
                    args.putString(StaticUtils.OPPOS_FLAG, getIntent().getStringExtra(StaticUtils.OPPOS_FLAG));
                    args.putString(StaticUtils.HOME_TEAM_SHORT_NAME, getIntent().getStringExtra(StaticUtils.HOME_TEAM_SHORT_NAME));
                    args.putString(StaticUtils.OPPOS_TEAM_SHORT_NAME, getIntent().getStringExtra(StaticUtils.OPPOS_TEAM_SHORT_NAME));
                    args.putString(StaticUtils.HOME_TEAM_COUNT, xTvHomeCount.getText().toString());
                    args.putString(StaticUtils.OPPOSITE_TEAM_COUNT, xTvOppositeCount.getText().toString());
                    intent.putExtra("BUNDLE", args);
                    startActivity(intent);
                }

            }
        });
    }


    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Team_1_Fragment(), getIntent().getStringExtra(StaticUtils.HOME_TEAM_FULL_NAME).toString());
        adapter.addFragment(new Team_2_Fragment(), getIntent().getStringExtra(StaticUtils.OPPOS_TEAM_FULL_NAME).toString());
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onDataPass(String finalValue, String home, String opposite, Double credits) {
        if (packageName.equals("5")) {
            xTvFinalCount.setText(finalValue + "/5");
            xTvHomeCount.setText(home);
            xTvOppositeCount.setText(opposite);
            xTvCreditsLeft.setText(String.valueOf(credits));

        } else {
            xTvFinalCount.setText(finalValue + "/7");
            xTvHomeCount.setText(home);
            xTvOppositeCount.setText(opposite);
            xTvCreditsLeft.setText(String.valueOf(credits));

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
//        dialog!!.setCancelable(false)
        goBackDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView editquestion = (TextView) goBackDialog.findViewById(R.id.textContinue);

        ImageView imgcancel = (ImageView) goBackDialog.findViewById(R.id.xIvCancel);

        editquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticUtils.FINAL_COUNT = 0;
                StaticUtils.OppoTeamcount = 0;
                StaticUtils.HomeTeamcount = 0;
                StaticUtils.CREDITS5 = 40.0;
                StaticUtils.CREDITS7 = 55.0;

                goBackDialog.cancel();

                Activity_SelectPlayer.super.onBackPressed();
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

    @Override
    protected void onResume() {
        super.onResume();
//        OppoTeamcount = 0;
//        HomeTeamcount = 0;
//        StaticUtils.CREDITS5=40.0;
//        StaticUtils.CREDITS7=51.0;

    }

}
