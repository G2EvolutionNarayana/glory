package com.android.glory.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.glory.Model.Contest.ContestDatum;
import com.android.glory.Model.DepositedModel;
import com.android.glory.Model.WinningDataModel;
import com.android.glory.R;

import java.util.ArrayList;
import java.util.List;

public class DepositedAmountAdapter  extends RecyclerView.Adapter<DepositedAmountAdapter.ContestHolder> {
    private Context context;
    ArrayList<DepositedModel> contestList;
    private DepositedAmountChildAdapter depositedAmountChildAdapter;

    public DepositedAmountAdapter(Context applicationContext, ArrayList<DepositedModel> mainList) {
        this.context = applicationContext;
        this.contestList = mainList;
    }

    @NonNull
    @Override
    public DepositedAmountAdapter.ContestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_deposite,parent, false);
        return new ContestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DepositedAmountAdapter.ContestHolder holder, int position) {
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        holder.xRecyclerView.setLayoutManager(mLayoutManager2);

        DepositedModel depositedModel=contestList.get(position);
        holder.xTvDate.setText( String.valueOf(depositedModel.getDate()));
        depositedAmountChildAdapter=new DepositedAmountChildAdapter(context,depositedModel.getList());
        holder.xRecyclerView.setAdapter(depositedAmountChildAdapter);
    }

    @Override
    public int getItemCount() {
        return contestList.size();
    }

    public class ContestHolder extends RecyclerView.ViewHolder {
        RecyclerView xRecyclerView;
        TextView xTvDate;

        public ContestHolder(@NonNull View itemView) {
            super(itemView);
            xRecyclerView=(itemView).findViewById(R.id.xRecyclerView);
            xTvDate=(itemView).findViewById(R.id.xTvDate);

        }
    }
}