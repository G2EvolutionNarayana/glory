package com.android.glory.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.android.glory.Pojo.Contest;
import com.android.glory.R;

import java.util.List;

public class ContestAdapter extends RecyclerView.Adapter<ContestAdapter.ContestHolder> {
    private Context context;
    private List<Contest> contestList;

    public ContestAdapter(Context context, List<Contest> contestList) {
        this.context = context;
        this.contestList = contestList;
    }

    @NonNull
    @Override
    public ContestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_contest,parent, false);
        return new ContestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContestHolder holder, int position) {
        Contest contest = contestList.get(position);
        holder.prize.setText(contest.getPrize());
        holder.entryFee.setText(contest.getEntryFee());
        holder.spotsLeft.setText(contest.getSpotsLeft());
        holder.spots.setText(contest.getSpots());

    }

    @Override
    public int getItemCount() {
        return contestList.size();
    }

    public class ContestHolder extends RecyclerView.ViewHolder {
        private TextView prize,entryFee,spotsLeft,spots;
        public ContestHolder(@NonNull View itemView) {
            super(itemView);
            prize = (TextView) itemView.findViewById(R.id.prize_amount);
            entryFee = (TextView) itemView.findViewById(R.id.entry_fee);
            spotsLeft = (TextView) itemView.findViewById(R.id.spot_left);
            spots = (TextView) itemView.findViewById(R.id.spots);
        }
    }
}
