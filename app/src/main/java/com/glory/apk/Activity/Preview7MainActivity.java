package com.glory.apk.Activity;

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

import com.glory.apk.Model.PlayersListModel.Play_listExample;
import com.glory.apk.Model.PlayersListModel.Player__ListDatum;
import com.glory.apk.Model.PlayersListModel.SelectedUser;
import com.glory.apk.R;
import com.glory.apk.Retrofit.Api;
import com.glory.apk.Retrofit.ApiClient;
import com.glory.apk.Utilites.StaticUtils;
import com.glory.apk.Utilites.sharedPrefs;
import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Preview7MainActivity extends AppCompatActivity {
    TextView xTvHitterPoints7, xTvPlayer2Points7, xTvPlayer3Points7, xTvPlayer4Points7, xTvPlayer5Points7, xTv6Points7, xTv7Points7;
    Button xBtnHitterName7, xBtnPlayer2Name7, xBtnPlayer3Name7, xBtnPlayer4Name7, xBtnPlayer5Name7, xBtnPlayer6Name, xBtnPlayer7Name;
    ImageView xMainHitter7, player2, player3, player4, xPlayer5, player6, player7;
    String ContestUserId;
    String packageid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview7_main);
        mInitWidgets();
        mCallContestList();
    }

    private void mInitWidgets() {
        ContestUserId=getIntent().getStringExtra("ContestUserId");
        Log.e("testing","ContestUserId = "+ContestUserId);
        ImageView xIvCross5 = (ImageView) findViewById(R.id.xIvCross7);
        Button xBtnBack = (Button) findViewById(R.id.xBtnBack);
        Button xBtnEdit = (Button) findViewById(R.id.xBtnEdit);
        xBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditPlayerActivity.class);
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
        xIvCross5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        xBtnHitterName7 = (Button) findViewById(R.id.xBtnHitterName7);
        xTvHitterPoints7 = (TextView) findViewById(R.id.xTvHitterPoints7);
        xMainHitter7 = (ImageView) findViewById(R.id.xMainHitter7);

        xBtnPlayer2Name7 = (Button) findViewById(R.id.xBtnPlayer2Name);
        xTvPlayer2Points7 = (TextView) findViewById(R.id.xTv2Points7);
        player2 = (ImageView) findViewById(R.id.player2);

        xBtnPlayer3Name7 = (Button) findViewById(R.id.xBtnPlayer3Name);
        xTvPlayer3Points7 = (TextView) findViewById(R.id.xTv3Points7);
        player3 = (ImageView) findViewById(R.id.player3);

        xBtnPlayer4Name7 = (Button) findViewById(R.id.xBtnPlayer4Name);
        xTvPlayer4Points7 = (TextView) findViewById(R.id.xTv4Points7);
        player4 = (ImageView) findViewById(R.id.player4);

        xBtnPlayer5Name7 = (Button) findViewById(R.id.xBtnPlayer5Name);
        xTvPlayer5Points7 = (TextView) findViewById(R.id.xTv5Points7);
        xPlayer5 = (ImageView) findViewById(R.id.player5);

        xBtnPlayer6Name = (Button) findViewById(R.id.xBtnPlayer6Name);
        xTv6Points7 = (TextView) findViewById(R.id.xTv6Points7);
        player6 = (ImageView) findViewById(R.id.player6);

        xBtnPlayer7Name = (Button) findViewById(R.id.xBtnPlayer7Name);
        xTv7Points7 = (TextView) findViewById(R.id.xTv7Points7);
        player7 = (ImageView) findViewById(R.id.player7);
    }

    private void mCallContestList() {
        final ProgressDialog pDialog;

        Log.e("testing", "strregisteredtoken = " + "matchesList");
        pDialog = new ProgressDialog(Preview7MainActivity.this);
        pDialog.setMessage("Please Wait ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        String viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);
        String ContestUserId = getIntent().getStringExtra("ContestUserId");
        Log.e("testing", "ContestUserId = " + ContestUserId);
        Api api = ApiClient.getClient().create(Api.class);
//        Call<Play_listExample> login = api.UpComingPlayersDetails(getIntent().getStringExtra("ContestUserId"));

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
                        for (int i = 0; i < list.size(); i++) {
                            Player__ListDatum playerListDatum = list.get(i);
                            packageid=String.valueOf(playerListDatum.getPackageId());

                            if (i == 0) {

                                for (int j = 0; j < playerListDatum.getSelectedUsers().size(); j++) {
                                    SelectedUser play_listPlayer = playerListDatum.getSelectedUsers().get(j);

                                    if (j == 0) {
                                        xTvHitterPoints7.setText("0");

                                        xBtnHitterName7.setText(playerListDatum.getSelectedUsers().get(j).getPlaylistPlayer().getFullName());
                                        if (playerListDatum.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL() == null || playerListDatum.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL().toString().length() == 0) {
                                            Glide.with(getApplicationContext())
                                                    .load((R.drawable.player)).placeholder(R.drawable.player)
                                                    .into(xMainHitter7);
                                            Log.e("testing", "getImageUrl = " + "null");

                                        } else {
                                            Glide.with(getApplicationContext())
                                                    .load(Uri.parse(playerListDatum.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL().toString()))
                                                    .error(R.drawable.player)
                                                    .into(xMainHitter7);
                                            Log.e("testing", "getImageUrl = " + "image");

                                        }
                                    } else if (j == 1) {
                                        xTvPlayer2Points7.setText("0");
                                        xBtnPlayer2Name7.setText(play_listPlayer.getPlaylistPlayer().getFullName());
                                        if (playerListDatum.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL() == null || playerListDatum.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL().toString().length() == 0) {
                                            Glide.with(getApplicationContext())
                                                    .load((R.drawable.player)).placeholder(R.drawable.player)
                                                    .into(player2);
                                            Log.e("testing", "getImageUrl = " + "null");

                                        } else {
                                            Glide.with(getApplicationContext())
                                                    .load(Uri.parse(playerListDatum.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL().toString()))
                                                    .error(R.drawable.player)
                                                    .into(player2);
                                            Log.e("testing", "getImageUrl = " + "image");

                                        }
                                    } else if (j == 2) {
                                        xTvPlayer3Points7.setText("0");
                                        xBtnPlayer3Name7.setText(play_listPlayer.getPlaylistPlayer().getFullName());
                                        if (playerListDatum.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL() == null || playerListDatum.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL().toString().length() == 0) {
                                            Glide.with(getApplicationContext())
                                                    .load((R.drawable.player)).placeholder(R.drawable.player)
                                                    .into(player3);
                                            Log.e("testing", "getImageUrl = " + "null");

                                        } else {
                                            Glide.with(getApplicationContext())
                                                    .load(Uri.parse(playerListDatum.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL().toString()))
                                                    .error(R.drawable.player)
                                                    .into(player3);
                                            Log.e("testing", "getImageUrl = " + "image");

                                        }
                                    } else if (j == 3) {
                                        xTvPlayer4Points7.setText("0");
                                        xBtnPlayer4Name7.setText(play_listPlayer.getPlaylistPlayer().getFullName());
                                        if (playerListDatum.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL() == null || playerListDatum.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL().toString().length() == 0) {
                                            Glide.with(getApplicationContext())
                                                    .load((R.drawable.player)).placeholder(R.drawable.player)
                                                    .into(player4);
                                            Log.e("testing", "getImageUrl = " + "null");

                                        } else {
                                            Glide.with(getApplicationContext())
                                                    .load(Uri.parse(playerListDatum.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL().toString()))
                                                    .error(R.drawable.player)
                                                    .into(player4);
                                            Log.e("testing", "getImageUrl = " + "image");

                                        }
                                    } else if (j == 4) {
                                        xTvPlayer5Points7.setText("0");
                                        xBtnPlayer5Name7.setText(play_listPlayer.getPlaylistPlayer().getFullName());
                                        if (playerListDatum.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL() == null || playerListDatum.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL().toString().length() == 0) {
                                            Glide.with(getApplicationContext())
                                                    .load((R.drawable.player)).placeholder(R.drawable.player)
                                                    .into(xPlayer5);
                                            Log.e("testing", "getImageUrl = " + "null");

                                        } else {
                                            Glide.with(getApplicationContext())
                                                    .load(Uri.parse(playerListDatum.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL().toString()))
                                                    .error(R.drawable.player)
                                                    .into(xPlayer5);
                                            Log.e("testing", "getImageUrl = " + "image");

                                        }
                                    } else if (j == 5) {
                                        xTv6Points7.setText("0");
                                        xBtnPlayer6Name.setText(play_listPlayer.getPlaylistPlayer().getFullName());
                                        if (playerListDatum.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL() == null || playerListDatum.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL().toString().length() == 0) {
                                            Glide.with(getApplicationContext())
                                                    .load((R.drawable.player)).placeholder(R.drawable.player)
                                                    .into(player6);
                                            Log.e("testing", "getImageUrl = " + "null");

                                        } else {
                                            Glide.with(getApplicationContext())
                                                    .load(Uri.parse(playerListDatum.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL().toString()))
                                                    .error(R.drawable.player)
                                                    .into(player6);
                                            Log.e("testing", "getImageUrl = " + "image");

                                        }
                                    } else if (j == 6) {
                                        xTv7Points7.setText("0");
                                        xBtnPlayer7Name.setText(play_listPlayer.getPlaylistPlayer().getFullName());
                                        if (playerListDatum.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL() == null || playerListDatum.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL().toString().length() == 0) {
                                            Glide.with(getApplicationContext())
                                                    .load((R.drawable.player)).placeholder(R.drawable.player)
                                                    .into(player7);
                                            Log.e("testing", "getImageUrl = " + "null");

                                        } else {
                                            Glide.with(getApplicationContext())
                                                    .load(Uri.parse(playerListDatum.getSelectedUsers().get(j).getPlaylistPlayer().getImageURL().toString()))
                                                    .error(R.drawable.player)
                                                    .into(player7);
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
