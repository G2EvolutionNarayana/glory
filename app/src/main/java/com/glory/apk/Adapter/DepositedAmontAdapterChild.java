package com.glory.apk.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.glory.apk.Model.Contest.ContestDatum;
import com.glory.apk.R;

import java.util.List;

public class DepositedAmontAdapterChild  extends RecyclerView.Adapter<DepositedAmontAdapterChild.ContestHolder> {
    private Context context;
    private List<ContestDatum> contestList;
    private ContestAdapter.OnItemClick mCallBack;

    public DepositedAmontAdapterChild(Context context, List<ContestDatum> contestList, ContestAdapter.OnItemClick mCallBack) {
        this.context = context;
        this.contestList = contestList;
        this.mCallBack=mCallBack;
    }

    @NonNull
    @Override
    public DepositedAmontAdapterChild.ContestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_deposite_child,parent, false);
        return new DepositedAmontAdapterChild.ContestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DepositedAmontAdapterChild.ContestHolder holder, int position) {
        holder.xTvDepositeAmount.setText("\u20B9"+" "+"15");


    }

    public interface OnItemClick{
        void onClicKListner(int position);
    }
    @Override
    public int getItemCount() {
        return contestList.size();
    }

    public class ContestHolder extends RecyclerView.ViewHolder {
        TextView xTvDepositeAmount;

        public ContestHolder(@NonNull View itemView) {
            super(itemView);
            xTvDepositeAmount=(itemView).findViewById(R.id.xTvDepositeAmount);


        }
    }
}