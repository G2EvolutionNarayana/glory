package com.android.glory.Activity;

import androidx.appcompat.app.AppCompatActivity;

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

import com.android.glory.Model.PlayersListModel.Play_listExample;
import com.android.glory.Model.PlayersListModel.Player__ListDatum;
import com.android.glory.Model.PlayersListModel.SelectedUser;
import com.android.glory.R;
import com.android.glory.Retrofit.Api;
import com.android.glory.Retrofit.ApiClient;
import com.android.glory.Utilites.StaticUtils;
import com.android.glory.Utilites.sharedPrefs;
import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Preview5MainActivity extends AppCompatActivity {
    TextView xTvHitterPoints, xTvPlayer2Points, xTvPlayer3Points, xTvPlayer4Points, xTvPlayer5Points;
    Button xBtnHitterName, xBtnPlayer2Name, xBtnPlayer3Name, xBtnPlayer4Name, xBtnPlayer5Name;
    ImageView xMainHitter, player2, player3, player4, xPlayer5;
    String ContestUserId;
    String packageid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_5_main);
        mInitWidgets();
        mCallContestList();
    }

    private void mInitWidgets() {
        ContestUserId=getIntent().getStringExtra(StaticUtils.CONTEST_ID);
        Log.e("testing","ContestUserId = "+ContestUserId);
        Button xBtnEdit=(Button)findViewById(R.id.xBtnEdit);
        Button xBtnBack=(Button)findViewById(R.id.xBtnBack);
        ImageView xIvCross5=(ImageView)findViewById(R.id.xIvCross5);
        xIvCross5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        xBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),EditPlayerActivity.class);
                intent.putExtra(StaticUtils.CONTEST_ID,ContestUserId);
                intent.putExtra(StaticUtils.PACKAGE_ID,packageid);

                startActivity(intent);
            }
        });
        xBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        xBtnHitterName = (Button) findViewById(R.id.xBtnHitterName);
        xTvHitterPoints = (TextView) findViewById(R.id.xTvHitterPoints);
        xMainHitter = (ImageView) findViewById(R.id.xMainHitter);

        xBtnPlayer2Name = (Button) findViewById(R.id.xBtnPlayer2Name);
        xTvPlayer2Points = (TextView) findViewById(R.id.xTvPlayer2Points);
        player2 = (ImageView) findViewById(R.id.player2);

        xBtnPlayer3Name = (Button) findViewById(R.id.xBtnPlayer3Name);
        xTvPlayer3Points = (TextView) findViewById(R.id.xTvPlayer3Points);
        player3 = (ImageView) findViewById(R.id.player3);

        xBtnPlayer4Name = (Button) findViewById(R.id.xBtnPlayer4Name);
        xTvPlayer4Points = (TextView) findViewById(R.id.xTvPlayer4Points);
        player4 = (ImageView) findViewById(R.id.player4);

        xBtnPlayer5Name = (Button) findViewById(R.id.xBtnPlayer5Name);
        xTvPlayer5Points = (TextView) findViewById(R.id.xTvPlayer5Points);
        xPlayer5 = (ImageView) findViewById(R.id.xPlayer5);
    }

    private void mCallContestList() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");
        final ProgressDialog pDialog;

        pDialog = new ProgressDialog(Preview5MainActivity.this);
        pDialog.setMessage("Please Wait ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        String viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);

        Api api = ApiClient.getClient().create(Api.class);
//        intent.putExtra("ContestUserId",dataList.getPendingPair().getId());
        Call<Play_listExample> login = api.UpComingPlayersDetails(ContestUserId);
        login.enqueue(new Callback<Play_listExample>() {
            @Override
            public void onResponse(Call<Play_listExample> call, Response<Play_listExample> response) {
                pDialog.dismiss();
                Log.e("testing", "status = " + response.body().getStatus());
                Log.e("testing", "response = " + response.body().getResponse().getMessage());
                //  Log.e("testing","response = "+response.body().getData().getPageContent());
                Log.e("testing", "response = " + response.body());
                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    if (response.body().getPlaylistFilters() == null) {

                    } else if (response.body().getResponse().getType().equals("data_found")) {
                        List<Player__ListDatum> list = response.body().getData();
                        Log.e("testing", "list = " + list);

                        for (int i = 0; i < list.size(); i++) {
                            Player__ListDatum playerList = list.get(i);
                            packageid=playerList.getPackageId().toString();
                            playerList.getSelectedUsers().size();
                            Log.e("testing", "playerList.getSelectedUsers().size() = " + playerList.getSelectedUsers().size());

                            if (i == 0) {

                                for (int j = 0; j < playerList.getSelectedUsers().size(); j++) {
                                    SelectedUser play_listPlayer = playerList.getSelectedUsers().get(j);
                                    if (j == 0) {
                                        xTvHitterPoints.setText("0");
                                        xBtnHitterName.setText(playerList.getSelectedUsers().get(j).getPlaylistPlayer().getFullName());
                                        if (playerList.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL() == null || playerList.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL().toString().length() == 0) {
                                            Glide.with(getApplicationContext())
                                                    .load((R.drawable.player)).placeholder(R.drawable.player)
                                                    .into(xMainHitter);
                                            Log.e("testing", "getImageUrl = " + "null");

                                        } else {
                                            Glide.with(getApplicationContext())
                                                    .load(Uri.parse(playerList.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL().toString()))
                                                    .error(R.drawable.player)
                                                    .into(xMainHitter);
                                            Log.e("testing", "getImageUrl = " + "image");

                                        }
                                    } else if (j == 1) {
                                        xTvPlayer2Points.setText("0");
                                        xBtnPlayer2Name.setText(playerList.getSelectedUsers().get(j).getPlaylistPlayer().getFullName());
                                        if (playerList.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL() == null || playerList.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL().toString().length() == 0) {
                                            Glide.with(getApplicationContext())
                                                    .load((R.drawable.player)).placeholder(R.drawable.player)
                                                    .into(player2);
                                            Log.e("testing", "getImageUrl = " + "null");

                                        } else {
                                            Glide.with(getApplicationContext())
                                                    .load(Uri.parse(playerList.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL().toString()))
                                                    .error(R.drawable.player)
                                                    .into(player2);
                                            Log.e("testing", "getImageUrl = " + "image");

                                        }
                                    } else if (j == 2) {
                                        xTvPlayer3Points.setText("0");
                                        xBtnPlayer3Name.setText(playerList.getSelectedUsers().get(j).getPlaylistPlayer().getFullName());
                                        if (playerList.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL() == null || playerList.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL().toString().length() == 0) {
                                            Glide.with(getApplicationContext())
                                                    .load((R.drawable.player)).placeholder(R.drawable.player)
                                                    .into(player3);
                                            Log.e("testing", "getImageUrl = " + "null");

                                        } else {
                                            Glide.with(getApplicationContext())
                                                    .load(Uri.parse(playerList.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL().toString()))
                                                    .error(R.drawable.player)
                                                    .into(player3);
                                            Log.e("testing", "getImageUrl = " + "image");

                                        }
                                    } else if (j == 3) {
                                        xTvPlayer4Points.setText("0");
                                        xBtnPlayer4Name.setText(playerList.getSelectedUsers().get(j).getPlaylistPlayer().getFullName());
                                        if (playerList.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL() == null || playerList.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL().toString().length() == 0) {
                                            Glide.with(getApplicationContext())
                                                    .load((R.drawable.player)).placeholder(R.drawable.player)
                                                    .into(player4);
                                            Log.e("testing", "getImageUrl = " + "null");

                                        } else {
                                            Glide.with(getApplicationContext())
                                                    .load(Uri.parse(playerList.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL().toString()))
                                                    .error(R.drawable.player)
                                                    .into(player4);
                                            Log.e("testing", "getImageUrl = " + "image");

                                        }
                                    } else if (j == 4) {
                                        xTvPlayer5Points.setText("0");
                                        xBtnPlayer5Name.setText(playerList.getSelectedUsers().get(j).getPlaylistPlayer().getFullName());
                                        if (playerList.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL() == null || playerList.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL().toString().length() == 0) {
                                            Glide.with(getApplicationContext())
                                                    .load((R.drawable.player)).placeholder(R.drawable.player)
                                                    .into(xPlayer5);
                                            Log.e("testing", "getImageUrl = " + "null");

                                        } else {
                                            Glide.with(getApplicationContext())
                                                    .load(Uri.parse(playerList.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL().toString()))
                                                    .error(R.drawable.player)
                                                    .into(xPlayer5);
                                            Log.e("testing", "getImageUrl = " + "image");

                                        }
                                    }


                                }

                            }
                        }
                        pDialog.dismiss();

                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getResponse().getType(), Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();
                    }

                } else {
                    Log.e("testing", "error");
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.body().getResponse().getType(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Play_listExample> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                pDialog.dismiss();
            }
        });

    }

}
