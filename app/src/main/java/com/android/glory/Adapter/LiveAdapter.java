package com.android.glory.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.glory.Activity.LivePreview5MainActivity;
import com.android.glory.Activity.LivePreview7MainActivity;
import com.android.glory.Model.LiveContestListModel.LiveContestDatum;
import com.android.glory.R;
import com.android.glory.Utilites.StaticUtils;

import java.util.ArrayList;
import java.util.List;

public class LiveAdapter extends RecyclerView.Adapter<LiveAdapter.LiveAdapterChild> {
    private Context mcontext;
    ArrayList<String> entryFreeList;
    ArrayList<String> teamNameList;
    List<LiveContestDatum> data;
    String matchId;


    public LiveAdapter(Context applicationContext, List<LiveContestDatum> data, String matchId) {
        this.mcontext=applicationContext;
        this.data=data;
        this.matchId=matchId;
    }
    @NonNull
    @Override
    public LiveAdapter.LiveAdapterChild onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.item_adapter_live, parent, false);
        return new LiveAdapterChild(view);
    }
    @Override
    public void onBindViewHolder(@NonNull LiveAdapter.LiveAdapterChild holder, int position) {
        LiveContestDatum dataList = data.get(position);
        holder.xTvEntryFeee.setText("\u20B9" + dataList.getLiveContestContest().getEntryFee().toString());
        holder.xTvprizee.setText("\u20B9" + dataList.getLiveContestContest().getPrizeAmount().toString());
        holder.xTvTeamName1e.setText(dataList.getTeamName());
        holder.xTvTeamPonints1e.setText(dataList.getTeam1Points().toString());

        if(dataList.getTeam1Points()==dataList.getTeam2Points()){
            holder.xTvSpot1e.setText("#0");
            holder.xTvSpot2e.setText("#0");
        }else if (dataList.getTeam1Points()>dataList.getTeam2Points()){
            holder.xTvSpot1e.setText("#1");
            holder.xTvSpot2e.setText("#2");
        }else {
            holder.xTvSpot1e.setText("#2");
            holder.xTvSpot2e.setText("#1");
        }


        if (dataList.getLiveContestPair().getId().isEmpty()|| dataList.getLiveContestPair().getId().length() == 0) {
            holder.xLinLayMain2e.setVisibility(View.GONE);
        } else {
            holder.xLinLayMain2e.setVisibility(View.VISIBLE);
            holder.xTvTeamName2e.setText(dataList.getLiveContestPair().getTeamName());
            holder.xTvTeamPonints2e.setText(dataList.getTeam2Points().toString());
//            holder.xTvSpot2e.setText("#2");
        }
        holder.xLinLayMain1e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataList.getPackage().getLimit().toString().equals("5")){
                    Intent intent=new Intent(mcontext, LivePreview5MainActivity.class);
                    intent.putExtra(StaticUtils.CONTEST_USERID,String.valueOf(dataList.getId()));
                    Log.e("testing","ContestUserId = "+dataList.getId().toString());
                    holder.xTvTeamPonints1e.setText(dataList.getTeam1Points().toString());
                    intent.putExtra(StaticUtils.TOTAL_POINTS,dataList.getTeam1Points().toString());
                    intent.putExtra("matchId",matchId);
                    mcontext.startActivity(intent);
                }else {
                    Intent intent=new Intent(mcontext, LivePreview7MainActivity.class);
                    intent.putExtra(StaticUtils.CONTEST_USERID,dataList.getId().toString());
                    intent.putExtra("matchId",matchId);
                    intent.putExtra(StaticUtils.TOTAL_POINTS,dataList.getTeam1Points().toString());
                    mcontext.startActivity(intent);
                }
            }
        });

        holder.xLinLayMain2e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataList.getPackage().getLimit().toString().equals("5")){
                    Intent intent=new Intent(mcontext, LivePreview5MainActivity.class);
                    intent.putExtra(StaticUtils.CONTEST_USERID,dataList.getLiveContestPair().getId().toString());
                    intent.putExtra("matchId",matchId);
                    intent.putExtra(StaticUtils.TOTAL_POINTS,dataList.getTeam2Points().toString());

                    mcontext.startActivity(intent);
                }else {
                    Intent intent=new Intent(mcontext, LivePreview7MainActivity.class);
                    intent.putExtra(StaticUtils.CONTEST_USERID,dataList.getLiveContestPair().getId().toString());
                    intent.putExtra("matchId",matchId);
                    intent.putExtra(StaticUtils.TOTAL_POINTS,dataList.getTeam2Points().toString());

                    mcontext.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class LiveAdapterChild extends RecyclerView.ViewHolder{
        TextView xTvEntryFeee, xTvprizee;
        TextView xTvTeamName1e, xTvTeamName2e, xTvSpot2e, xTvSpot1e, xTvTeamPonints2e, xTvTeamPonints1e;
        LinearLayout xLinLayMain1e, xLinLayMain2e;

        public LiveAdapterChild(@NonNull View itemView) {
            super(itemView);
            xTvEntryFeee = (TextView) itemView.findViewById(R.id.xTvEntryFeee);
            xTvprizee = (TextView) itemView.findViewById(R.id.xTvprizee);
            xTvTeamName1e = (TextView) itemView.findViewById(R.id.xTvTeamName1e);
            xTvTeamName2e = (TextView) itemView.findViewById(R.id.xTvTeamName2e);
            xTvSpot1e = (TextView) itemView.findViewById(R.id.xTvSpot1e);
            xTvSpot2e = (TextView) itemView.findViewById(R.id.xTvSpot2e);
            xTvTeamPonints2e = (TextView) itemView.findViewById(R.id.xTvTeamPonints2e);
            xTvTeamPonints1e = (TextView) itemView.findViewById(R.id.xTvTeamPonints1e);
            xLinLayMain1e = (LinearLayout) itemView.findViewById(R.id.xLinLayMain1e);
            xLinLayMain2e = (LinearLayout) itemView.findViewById(R.id.xLinLayMain2e);
        }
    }
}
