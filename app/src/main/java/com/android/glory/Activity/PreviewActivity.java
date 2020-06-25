package com.android.glory.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.glory.Adapter.PowerHitterAdpter;
import com.android.glory.Adapter.PreviewAdapter;
import com.android.glory.Model.FinalPlayerSelectionModel;
import com.android.glory.Model.HomePlayersSelected;
import com.android.glory.Model.OpposPlayerSelected;
import com.android.glory.Model.PowerHitterModel;
import com.android.glory.Model.PreviewModel;
import com.android.glory.Pojo.PlayerDetails;
import com.android.glory.R;
import com.android.glory.Utilites.StaticUtils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import static com.android.glory.Utilites.StaticUtils.opposePlayers;

public class PreviewActivity extends AppCompatActivity {
    private List<PlayerDetails> playerDetailsList;
    private PreviewAdapter previewAdpter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView xRvPreview;
    Button contin;
    LinearLayout linearLayout2;
    ArrayList<PreviewModel> previewPlayers;
    ImageView XIvHome, xIvOppose;
    TextView xTvHomeTeamName, xTvOpposTeamName,xTvOppositeCount,xTvHomeCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_fragment);
        mInitWidgets();
    }

    private void mInitWidgets() {
        xRvPreview = (RecyclerView) findViewById(R.id.xRvPreview);
        linearLayout2 = (LinearLayout) findViewById(R.id.linearLayout2);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        XIvHome = (ImageView) findViewById(R.id.XIvHome);
        xIvOppose = (ImageView) findViewById(R.id.xIvOppose);
        xTvHomeTeamName = (TextView) findViewById(R.id.xTvHomeTeamName);
        xTvOpposTeamName = (TextView) findViewById(R.id.xTvOpposTeamName);
        xTvHomeCount= (TextView) findViewById(R.id.xTvHomeCount);
        xTvOppositeCount= (TextView) findViewById(R.id.xTvOppositeCount);

        Bundle args = getIntent().getBundleExtra("BUNDLE");

//        Log.e("testing","shortName ="getIntent().getStringExtra(StaticUtils.HOME_TEAM_SHORT_NAME));
        xTvHomeTeamName.setText(args.getString(StaticUtils.HOME_TEAM_SHORT_NAME));
        xTvOpposTeamName.setText(args.getString(StaticUtils.OPPOS_TEAM_SHORT_NAME));
        xTvHomeCount.setText(args.getString(StaticUtils.HOME_TEAM_COUNT));
        xTvOppositeCount.setText(args.getString(StaticUtils.OPPOSITE_TEAM_COUNT));

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
        Log.e("testing", "homeFlag =" + getIntent().getStringExtra(StaticUtils.HOME_FLAG));
        xRvPreview.setLayoutManager(linearLayoutManager);
        previewPlayers = new ArrayList<>();
        ArrayList<FinalPlayerSelectionModel> object = (ArrayList<FinalPlayerSelectionModel>) args.getSerializable("ARRAYLIST");

        for (int i = 0; i < object.size(); i++) {
            FinalPlayerSelectionModel homePlayersSelected = object.get(i);
            previewPlayers.add(new PreviewModel(homePlayersSelected.getName(), homePlayersSelected.getImage(), homePlayersSelected.getId()));
        }
//        for (int i=0;i<StaticUtils.homePlayers.size();i++){
//            HomePlayersSelected homePlayersSelected=StaticUtils.homePlayers.get(i);
//            previewPlayers.add(new PreviewModel(homePlayersSelected.getName(),homePlayersSelected.getImage(),homePlayersSelected.getId()));
//        }
//        for (int i = 0; i< opposePlayers.size(); i++){
//            OpposPlayerSelected opposPlayerSelected=StaticUtils.opposePlayers.get(i);
//            previewPlayers.add(new PreviewModel(opposPlayerSelected.getName(),opposPlayerSelected.getImage(),opposPlayerSelected.getId()));
//        }
        previewAdpter = new PreviewAdapter(getApplicationContext(), previewPlayers);
        xRvPreview.setAdapter(previewAdpter);
        contin = (Button) findViewById(R.id.contin);
        contin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
