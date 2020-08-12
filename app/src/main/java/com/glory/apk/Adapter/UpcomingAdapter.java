package com.glory.apk.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.glory.apk.Activity.Preview5MainActivity;
import com.glory.apk.Activity.Preview7MainActivity;
import com.glory.apk.Model.Pending.PendingDatum;
import com.glory.apk.R;
import com.glory.apk.Utilites.StaticUtils;

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
                    Log.e("testing", "ContestUserId = " + dataList.getMatchId());

                    intent.putExtra(StaticUtils.MATCH_ID, String.valueOf(dataList.getMatchId().toString()));
                    intent.putExtra("TeamName", String.valueOf(dataList.getTeamName().toString()));

                    mcontext.startActivity(intent);
                } else {
                    String con = dataList.getPendingPair().getId();
                    Intent intent = new Intent(mcontext, Preview7MainActivity.class);
                    intent.putExtra("ContestUserId", dataList.getId().toString());
                    Log.e("testing", "ContestUserId = " + dataList.getPendingPair().getId());
                    intent.putExtra(StaticUtils.MATCH_ID, String.valueOf(dataList.getMatchId().toString()));
                    intent.putExtra("TeamName", String.valueOf(dataList.getTeamName().toString()));

                    mcontext.startActivity(intent);
                }
            }
        });

        holder.xLinLayMain2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog deadLineDialog;

                deadLineDialog = new Dialog(mcontext);
                deadLineDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                deadLineDialog.setContentView(R.layout.dead_line_dialog);
//        dialog!!.setCancelable(false)
                deadLineDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                deadLineDialog.setCancelable(false);
                deadLineDialog.show();

                final Handler handler  = new Handler();
                final Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        if (deadLineDialog.isShowing()) {
                            deadLineDialog.dismiss();
                        }
                    }
                };
                Window window = deadLineDialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();
                wlp.gravity = Gravity.BOTTOM;
                wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                window.setAttributes(wlp);
                handler.postDelayed(runnable, 4000);
            }
        });
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