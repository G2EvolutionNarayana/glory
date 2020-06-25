package com.android.glory.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.glory.Adapter.PowerHitterAdapter2;
import com.android.glory.Adapter.PowerHitterAdpter;
import com.android.glory.MainActivity;
import com.android.glory.Model.EditTeamListModel.EditTeamDatum;
import com.android.glory.Model.EditTeamListModel.EditTeamExample;
import com.android.glory.Model.FinalPlayerSelectionModel;
import com.android.glory.Model.PowerHitterModel;
import com.android.glory.Model.UpdatePlayersModel.UpdatePlayersExample;
import com.android.glory.R;
import com.android.glory.Retrofit.Api;
import com.android.glory.Retrofit.ApiClient;
import com.android.glory.Utilites.StaticUtils;
import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditHitterSelectionActivity extends AppCompatActivity implements  PowerHitterAdpter.OnItemClick  {
    private PowerHitterAdpter powerHitterAdpter;
    private PowerHitterAdapter2 powerHitterAdpter2;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;    private ProgressDialog pDialog;

    public ArrayList<PowerHitterModel> powerHitterList;
    public ArrayList<PowerHitterModel> powerHitterList2;

    ImageView XIvHome, xIvOppose;
    TextView xTvHomeTeamName, xTvOpposTeamName,xTvHomeCount,xTvOppositeCount;
    Button contin,PowerHitter;
    String hitterId;
    PowerHitterAdpter.OnItemClick mCallBack;
    private ArrayList<String> SelectedPlayers;
    String hitterName,hitterImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_hitter_selection);
        PowerHitterAdpter.count = -1;
        powerHitterList = new ArrayList<>();
        powerHitterList2 = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.rv_hitter);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        Bundle args = getIntent().getBundleExtra("BUNDLE");
        mCallBack=this;

        XIvHome = (ImageView) findViewById(R.id.XIvHome);
        xIvOppose = (ImageView) findViewById(R.id.xIvOppose);
        xTvHomeTeamName = (TextView) findViewById(R.id.xTvHomeTeamName);
        xTvOpposTeamName = (TextView) findViewById(R.id.xTvOpposTeamName);
        xTvHomeTeamName.setText(args.getString(StaticUtils.HOME_TEAM_SHORT_NAME));
        xTvOpposTeamName.setText(args.getString(StaticUtils.OPPOS_TEAM_SHORT_NAME));
        xTvHomeCount=(TextView) findViewById(R.id.xTvHomeCount);
        xTvOppositeCount=(TextView) findViewById(R.id.xTvOppositeCount);
        xTvHomeCount.setText(args.getString(StaticUtils.HOME_TEAM_COUNT));
        xTvOppositeCount.setText(args.getString(StaticUtils.OPPOSITE_TEAM_COUNT));
        PowerHitter=(Button)findViewById(R.id.PowerHitter);



        if (args.getString(StaticUtils.HOME_FLAG) == null || args.getString(StaticUtils.HOME_FLAG).length() == 0) {
            Glide.with(getApplicationContext())
                    .load((R.drawable.ind)).placeholder(R.drawable.ind)
                    .into(XIvHome);
            Log.e("testing", "getImageUrl = " + "null");

        } else {
            Glide.with(getApplicationContext())
                    .load(Uri.parse(args.getString(StaticUtils.HOME_FLAG)))
                    .error(R.drawable.ind)
                    .into(XIvHome);
            Log.e("testing", "getImageUrl = " + "image");
        }
        if (args.getString(StaticUtils.OPPOS_FLAG) == null || args.getString(StaticUtils.OPPOS_FLAG).length() == 0) {
            Glide.with(getApplicationContext())
                    .load((R.drawable.pak)).placeholder(R.drawable.pak)
                    .into(xIvOppose);
            Log.e("testing", "getImageUrl = " + "null");

        } else {
            Glide.with(getApplicationContext())
                    .load(Uri.parse(args.getString(StaticUtils.OPPOS_FLAG)))
                    .error(R.drawable.pak)
                    .into(xIvOppose);
            Log.e("testing", "getImageUrl = " + "image");
        }
        Log.e("testing", "homeFlag =" + args.getString(StaticUtils.HOME_FLAG));
        ArrayList<FinalPlayerSelectionModel> object = (ArrayList<FinalPlayerSelectionModel>) args.getSerializable("ARRAYLIST");
        ArrayList<FinalPlayerSelectionModel> object2 = (ArrayList<FinalPlayerSelectionModel>) args.getSerializable("ARRAYLIST");

        SelectedPlayers = new ArrayList<>();
        for (int i = 0; i < object.size(); i++) {
            FinalPlayerSelectionModel homePlayersSelected = object.get(i);

            Log.e("testing", "object = " + String.valueOf(homePlayersSelected.getId()));
            SelectedPlayers.add(String.valueOf(homePlayersSelected.getId()));
        }
        for (int i = 0; i < object.size(); i++) {
            FinalPlayerSelectionModel homePlayersSelected = object.get(i);
            powerHitterList.add(new PowerHitterModel(homePlayersSelected.getName(), homePlayersSelected.getImage(), homePlayersSelected.getId(), false));
        }
        powerHitterAdpter = new PowerHitterAdpter(getApplicationContext(), powerHitterList,mCallBack);
        recyclerView.setAdapter(powerHitterAdpter);
        contin = (Button) findViewById(R.id.contin);
        contin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < powerHitterList.size(); i++) {
                    PowerHitterModel powerHitterModel = powerHitterList.get(i);
                    if (powerHitterModel.getPowerHitter()) {
                        matchesList();
                    }
                }

            }
        });
        PowerHitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("testing", getIntent().getStringExtra(StaticUtils.PACKAGE_ID));

                if (hitterName.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please select the Hitter", Toast.LENGTH_LONG).show();

                } else {
                    for (int i = 0; i < object2.size(); i++) {
                        FinalPlayerSelectionModel homePlayersSelected = object2.get(i);
                        if (hitterId.equals(String.valueOf(homePlayersSelected.getId()))) {
                            object2.remove(i);

                        } else {
                            powerHitterList2.add(new PowerHitterModel(homePlayersSelected.getName(), homePlayersSelected.getImage(), homePlayersSelected.getId(), false));

                        }
                    }

                        if (getIntent().getStringExtra(StaticUtils.PACKAGE_ID).equals("1")) {

                            Intent intent = new Intent(EditHitterSelectionActivity.this, SelectedPreview5Activity.class);
                            Bundle args = new Bundle();
                            args.putSerializable("ARRAYLIST", (Serializable) object2);
                            args.putString(StaticUtils.HITTER_IMAGE, hitterImage);
                            args.putString(StaticUtils.HITTER_NAME, hitterName);
                            intent.putExtra("BUNDLE", args);
                            startActivity(intent);

                        } else {

                            Intent intent = new Intent(EditHitterSelectionActivity.this, SelectedPreview7Activity.class);
                            Bundle args = new Bundle();
                            args.putSerializable("ARRAYLIST", (Serializable) object2);
                            intent.putExtra("BUNDLE", args);
                            args.putString(StaticUtils.HITTER_IMAGE, hitterImage);
                            args.putString(StaticUtils.HITTER_NAME, hitterName);
                            startActivity(intent);

                        }
                    }
                }



        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        powerHitterList.clear();
    }



    @Override
    public void onClicKListner(int position) {
        PowerHitterModel playerDetails = powerHitterList.get(position);

        hitterId=String.valueOf(playerDetails.getId());
        hitterName=playerDetails.getName();
        hitterImage=playerDetails.getImage();
        Log.e("testing","hitterId "+hitterId);

    }

    private void matchesList() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");

        pDialog = new ProgressDialog(EditHitterSelectionActivity.this);
        pDialog.setMessage("Please Wait ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();


        Api api = ApiClient.getClient().create(Api.class);
        Call<UpdatePlayersExample> login = api.UpdatePlayers("53",hitterId,SelectedPlayers);

        Log.e("testing", "hitterId = " + hitterId);


        login.enqueue(new Callback<UpdatePlayersExample>() {
            @Override
            public void onResponse(Call<UpdatePlayersExample> call, Response<UpdatePlayersExample> response) {
                pDialog.dismiss();
                Log.e("testing", "status = " + response.body().getStatus());
                Log.e("testing", "response = " + response.body().getUpdatePlayersResponse().getMessage());
                //  Log.e("testing","response = "+response.body().getData().getPageContent());
                Log.e("testing", "response = " + response.body());
                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                  if (response.body().getUpdatePlayersResponse().getType().equals("save_success")) {
//                        Log.e("testing", "dateumList.size = " + dateumList.size());
//                        dateumList=response.body().getData();
                      pDialog.dismiss();
                      Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                      startActivity(intent);


                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getUpdatePlayersResponse().getType(), Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();

                    }

                } else {
                    Log.e("testing", "error");
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.body().getUpdatePlayersResponse().getType(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpdatePlayersExample> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "t.getLocalizedMessage()", Toast.LENGTH_SHORT).show();
                pDialog.dismiss();

            }
        });
    }
}