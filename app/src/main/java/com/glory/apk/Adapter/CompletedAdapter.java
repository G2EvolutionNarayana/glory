package com.glory.apk.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.glory.apk.Activity.CompletedActivity;
import com.glory.apk.Activity.LivePreview5MainActivity;
import com.glory.apk.Activity.LivePreview7MainActivity;
import com.glory.apk.Model.MyMatches.Datum;
import com.glory.apk.R;
import com.glory.apk.Utilites.StaticUtils;

import java.util.List;

public class CompletedAdapter extends RecyclerView.Adapter<CompletedAdapter.FilterViewHolder> {

    private Context mCtx;
    //we are storing all the products in a list
    private List<Datum> courses_offered_list;
    private Adapter_MyMatches.OnItemClickcourses mCallback1;
    String qty;
    String sub_category_id;
    private List<com.glory.apk.Model.CompletedDataModel.Datum> data;


//    public CompletedAdapter(Context activity, List<Datum> data, Adapter_MyMatches.OnItemClickcourses mCallback1) {
//        this.mCtx = activity;
//        this.courses_offered_list = data;
//        this.mCallback1 = mCallback1;
//    }

//    public CompletedAdapter(Context mCtx, List<Datum> data) {
//        this.mCtx = mCtx;
//        this.data = data;
//    }

    public CompletedAdapter(CompletedActivity mCtx, List<com.glory.apk.Model.CompletedDataModel.Datum> data) {
        this.mCtx = mCtx;
        this.data = data;
    }


    @Override
    public CompletedAdapter.FilterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.completed_match1, parent, false);
        return new FilterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CompletedAdapter.FilterViewHolder holder, final int position) {
        com.glory.apk.Model.CompletedDataModel.Datum follow = data.get(position);

        if (follow.getContest().getPrizeAmount() != null)
            holder.xTvWonMoney.setText("YOU WON \u20B9" + follow.getContest().getPrizeAmount());

        if (follow.getTeamName() != null) {
            holder.xTvTeamName1e.setText(follow.getTeamName());
            holder.xTvName.setText(follow.getTeamName());

        }

        if (follow.getPair() == null) {
            holder.xLinLayMain2e.setVisibility(View.GONE);
        } else {
            holder.xLinLayMain2e.setVisibility(View.VISIBLE);

            if (follow.getPair().getTeamName() != null) {
                holder.xTvTeamName2e.setText(follow.getPair().getTeamName());

            }
        }


        if (follow.getTeam1Points().toString() != null)
            holder.xTvTeamPonints1e.setText(follow.getTeam1Points().toString() + " pts");

        if (follow.getTeam2Points().toString() != null)
            holder.xTvTeamPonints2e.setText(follow.getTeam2Points().toString() + " pts");

        if (follow.getContest().getEntryFee() != (null))
            holder.xTvEntryFee.setText("\u20B9" + follow.getContest().getEntryFee());

        if (follow.getTeam1Points() == follow.getTeam2Points()) {
            holder.xTvSpot1e.setText("#0");
            holder.xTvSpot2e.setText("#0");
        } else if (follow.getTeam1Points() > follow.getTeam2Points()) {
            holder.xTvSpot1e.setText("#1");
            holder.xTvSpot2e.setText("#2");
        } else {
            holder.xTvSpot1e.setText("#2");
            holder.xTvSpot2e.setText("#1");
        }


        // win check
        if (follow.getWin() == 0) {
            holder.xTvWonMoney.setText("YOU LOST");

        } else if (follow.getWin() == 1) {
            holder.xTvWonMoney.setText("YOU WON \u20B9" + follow.getContest().getPrizeAmount());
        } else if (follow.getWin() == 2) {
            holder.xTvWonMoney.setText("Match Suspended & Refunded");
        } else if (follow.getWin() == 3) {
            holder.xTvWonMoney.setText("No competitor & Refunded");
        } else if (follow.getWin() == 4) {
            holder.xTvWonMoney.setText("Match Draw & Refunded");
        } else {
            holder.xTvWonMoney.setText("Awaiting Results");
        }

//        if (follow.getMatchDraw() == 0) {
//            if (follow.getWin() == 0)
//                holder.xTvWonMoney.setText("YOU LOST");
//            else
//                holder.xTvWonMoney.setText("YOU WON \u20B9" + follow.getContest().getPrizeAmount());
//        } else if (follow.getMatchDraw() == 1) {
//        } else if (follow.getMatchDraw() == 2) {
//        } else {
//            holder.xTvWonMoney.setText("Awaiting Results");
//        }


        holder.xLinLayMain1e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.get(position).getPackageId() == 1) {
                    Intent intent = new Intent(mCtx, LivePreview5MainActivity.class);
                    intent.putExtra(StaticUtils.CONTEST_USERID, String.valueOf(data.get(position).getId()));
                    intent.putExtra(StaticUtils.TOTAL_POINTS, String.valueOf(data.get(position).getTeam1Points()));
                    intent.putExtra("matchId", String.valueOf(data.get(position).getContest().getMatchId()));
                    intent.putExtra("ScreenName", "CompletedAdapter");
                    intent.putExtra("TeamName", String.valueOf(data.get(position).getTeamName()));

                    mCtx.startActivity(intent);
                } else {
                    Intent intent = new Intent(mCtx, LivePreview7MainActivity.class);
                    intent.putExtra(StaticUtils.CONTEST_USERID, String.valueOf(data.get(position).getId()));
                    intent.putExtra(StaticUtils.TOTAL_POINTS, String.valueOf(data.get(position).getTeam1Points()));
                    intent.putExtra("matchId", String.valueOf(data.get(position).getContest().getMatchId()));
                    intent.putExtra("ScreenName", "CompletedAdapter");
                    intent.putExtra("TeamName", String.valueOf(data.get(position).getTeamName()));

                    mCtx.startActivity(intent);
                }
            }
        });

        holder.xLinLayMain2e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.get(position).getPackageId() == 1) {
                    Intent intent = new Intent(mCtx, LivePreview5MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra(StaticUtils.CONTEST_USERID, String.valueOf(follow.getPair().getId()));
                    intent.putExtra("matchId", String.valueOf(data.get(position).getContest().getMatchId()));
                    intent.putExtra("ScreenName", "CompletedAdapter");
                    intent.putExtra("TeamName", follow.getPair().getTeamName());

                    intent.putExtra(StaticUtils.TOTAL_POINTS, follow.getTeam2Points().toString());

                    mCtx.startActivity(intent);
                } else {
                    Intent intent = new Intent(mCtx, LivePreview7MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    intent.putExtra(StaticUtils.CONTEST_USERID, String.valueOf(follow.getPair().getId()));

                    intent.putExtra("matchId", String.valueOf(data.get(position).getContest().getMatchId()));
                    intent.putExtra("ScreenName", "CompletedAdapter");
                    intent.putExtra("TeamName", follow.getPair().getTeamName());

                    intent.putExtra(StaticUtils.TOTAL_POINTS, follow.getTeam2Points().toString());

                    mCtx.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class FilterViewHolder extends RecyclerView.ViewHolder {

        TextView xTvWonMoney, xTvTeamPonints1e, xTvEntryFee, xTvName, xTvTeamName1e, xTvTeamName2e, xTvTeamPonints2e, xTvSpot1e, xTvSpot2e;
        LinearLayout xLinLayMain1e, xLinLayMain2e;

        public FilterViewHolder(View itemView) {
            super(itemView);
            xTvName = itemView.findViewById(R.id.xTvName);
            xTvTeamName1e = itemView.findViewById(R.id.xTvTeamName1e);
            xTvTeamName2e = itemView.findViewById(R.id.xTvTeamName2e);
            xTvSpot1e = itemView.findViewById(R.id.xTvSpot1e);
            xTvSpot2e = itemView.findViewById(R.id.xTvSpot2e);

            xTvWonMoney = itemView.findViewById(R.id.xTvWonMoney);
            xTvTeamPonints1e = itemView.findViewById(R.id.xTvTeamPonints1e);
            xTvTeamPonints2e = itemView.findViewById(R.id.xTvTeamPonints2e);

            xTvEntryFee = itemView.findViewById(R.id.xTvEntryFee);
            xLinLayMain1e = itemView.findViewById(R.id.xLinLayMain1e);
            xLinLayMain2e = itemView.findViewById(R.id.xLinLayMain2e);


        }
    }
}
