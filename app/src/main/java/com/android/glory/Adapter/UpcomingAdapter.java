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

import com.android.glory.Activity.Preview5MainActivity;
import com.android.glory.Activity.Preview7MainActivity;
import com.android.glory.Model.Pending.PendingDatum;
import com.android.glory.R;
import com.android.glory.Utilites.StaticUtils;

import java.util.ArrayList;
import java.util.List;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.UpcomingChildAdapterChild> {
    private Context mcontext;
    ArrayList<String> entryFreeList;
    ArrayList<String> teamNameList;
    List<PendingDatum> data;

    public UpcomingAdapter(Context applicationContext, List<PendingDatum> data) {
        this.data = data;
        this.mcontext = applicationContext;
    }

    @NonNull
    @Override
    public UpcomingAdapter.UpcomingChildAdapterChild onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.item_adapter_upcoming, parent, false);
        return new UpcomingChildAdapterChild(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingAdapter.UpcomingChildAdapterChild holder, int position) {
        PendingDatum dataList = data.get(position);
        holder.xTvEntryFee.setText("\u20B9" + dataList.getPendingContest().getEntryFee().toString());
        holder.xTvprize.setText("\u20B9" + dataList.getPendingContest().getPrizeAmount().toString());
        holder.xTvTeamName1.setText(dataList.getTeamName());
        holder.xTvTeamPonints1.setText("0");
        holder.xTvSpot1.setText("#1");
        holder.xTvSpots.setText("1/2");


        if (dataList.getPendingPair().getId() == "" || dataList.getPendingPair().getId().length() == 0) {
            holder.xLinLayMain2.setVisibility(View.GONE);
        } else {
            holder.xLinLayMain2.setVisibility(View.VISIBLE);
            holder.xTvTeamName2.setText(dataList.getPendingPair().getTeamName());
            holder.xTvTeamPonints2.setText("0");
            holder.xTvSpot2.setText("#2");
            holder.xTvSpots.setText("2/2");


        }
        holder.xLinLayMain1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataList.getPackage().getLimit().toString().equals("5")) {
                    String con = dataList.getPendingPair().getId();
                    Log.e("testing", "dataList.getPackage().getLimit() = " + dataList.getPackage().getLimit());

                    Intent intent = new Intent(mcontext, Preview5MainActivity.class);
                    intent.putExtra(StaticUtils.CONTEST_ID, dataList.getId().toString());
                    Log.e("testing", "ContestUserId = " + dataList.getId());
                    mcontext.startActivity(intent);
                } else {
                    String con = dataList.getPendingPair().getId();
                    Intent intent = new Intent(mcontext, Preview7MainActivity.class);
                    intent.putExtra("ContestUserId", dataList.getId().toString());
                    Log.e("testing", "ContestUserId = " + dataList.getPendingPair().getId());
                    mcontext.startActivity(intent);
                }
            }
        });

//        holder.xLinLayMain2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (dataList.getPackage().getLimit().toString().equals("5")){
//                    String con=dataList.getPendingPair().getId();
//                    Intent intent=new Intent(mcontext, Preview5MainActivity.class);
//                    intent.putExtra(StaticUtils.CONTEST_ID,dataList.getPendingPair().getId());
//                    Log.e("testing","ContestUserId = "+dataList.getPendingPair().getId());
//
//                    mcontext.startActivity(intent);
//                }else {
//                    String con=dataList.getPendingPair().getId();
//
//                    Intent intent=new Intent(mcontext, Preview7MainActivity.class);
//                    intent.putExtra("ContestUserId",dataList.getPendingPair().getId().toString());
//                    mcontext.startActivity(intent);
//                }
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class UpcomingChildAdapterChild extends RecyclerView.ViewHolder {
        TextView xTvEntryFee, xTvprize;
        TextView xTvTeamName1, xTvTeamName2, xTvSpot2, xTvSpot1, xTvTeamPonints2, xTvTeamPonints1,xTvSpots;
        LinearLayout xLinLayMain1, xLinLayMain2;

        public UpcomingChildAdapterChild(@NonNull View itemView) {
            super(itemView);
            xTvEntryFee = (TextView) itemView.findViewById(R.id.xTvEntryFee);
            xTvprize = (TextView) itemView.findViewById(R.id.xTvprize);
            xTvTeamName1 = (TextView) itemView.findViewById(R.id.xTvTeamName1);
            xTvTeamName2 = (TextView) itemView.findViewById(R.id.xTvTeamName2);
            xTvSpot1 = (TextView) itemView.findViewById(R.id.xTvSpot1);
            xTvSpot2 = (TextView) itemView.findViewById(R.id.xTvSpot2);
            xTvSpots=(TextView)itemView.findViewById(R.id.xTvSpots);
            xTvTeamPonints2 = (TextView) itemView.findViewById(R.id.xTvTeamPonints2);
            xTvTeamPonints1 = (TextView) itemView.findViewById(R.id.xTvTeamPonints1);
            xLinLayMain1 = (LinearLayout) itemView.findViewById(R.id.xLinLayMain1);
            xLinLayMain2 = (LinearLayout) itemView.findViewById(R.id.xLinLayMain2);
        }
    }
}