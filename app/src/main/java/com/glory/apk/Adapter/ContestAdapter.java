package com.glory.apk.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.glory.apk.Model.Contest.ContestDatum;
import com.glory.apk.R;

import java.util.List;

public class ContestAdapter extends RecyclerView.Adapter<ContestAdapter.ContestHolder> {
    private Context context;
    private List<ContestDatum> contestList;
    private OnItemClick mCallBack;

    public ContestAdapter(Context context, List<ContestDatum> contestList,OnItemClick mCallBack) {
        this.context = context;
        this.contestList = contestList;
        this.mCallBack=mCallBack;
    }

    @NonNull
    @Override
    public ContestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_contest,parent, false);
        return new ContestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContestHolder holder, int position) {
        ContestDatum contest = contestList.get(position);
     //   holder.prize.setText(contest.getPrize());
        holder.entryFee.setText("\u20B9 "+contest.getEntryFee().toString());
        holder.prize.setText("\u20B9 "+contest.getPrizeAmount().toString());

      holder.spotsLeft.setText(contest.getSpots().toString()+"/2 JOINED");
    //    holder.spots.setText(contest.getSpots());
        holder.xLinLayEntryFee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onClicKListner(position);
            }
        });

    }

    public interface OnItemClick{
        void onClicKListner(int position);
    }
    @Override
    public int getItemCount() {
        return contestList.size();
    }

    public class ContestHolder extends RecyclerView.ViewHolder {
        private TextView prize,entryFee,spotsLeft,spots;
        LinearLayout xLinLayEntryFee;
        public ContestHolder(@NonNull View itemView) {
            super(itemView);
            prize = (TextView) itemView.findViewById(R.id.prize_amount);
            entryFee = (TextView) itemView.findViewById(R.id.entry_fee);
            spotsLeft = (TextView) itemView.findViewById(R.id.spot_left);
            spots = (TextView) itemView.findViewById(R.id.spots);
            xLinLayEntryFee=(LinearLayout)itemView.findViewById(R.id.xLinLayEntryFee);

        }
    }
}
