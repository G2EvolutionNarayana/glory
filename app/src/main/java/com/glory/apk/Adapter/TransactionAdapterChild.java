package com.glory.apk.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.glory.apk.Model.TransactionChildDataModel;
import com.glory.apk.R;

import java.util.ArrayList;

public class TransactionAdapterChild  extends RecyclerView.Adapter<TransactionAdapterChild.ContestHolder> {
    private Context context;
    ArrayList<TransactionChildDataModel> list;



    public TransactionAdapterChild(Context context, ArrayList<TransactionChildDataModel> list) {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public TransactionAdapterChild.ContestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_transaction_child, parent, false);
        return new ContestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapterChild.ContestHolder holder, int position) {
        TransactionChildDataModel transactionChildDataModel=list.get(position);


        holder.xTvDepositeAmount.setText("\u20B9" + " " + transactionChildDataModel.getTrans_amount());
        holder.xTvTypeAmount.setText(transactionChildDataModel.getTrans_code());

        holder.xTvStatus.setText(transactionChildDataModel.getTrans_status());
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ContestHolder extends RecyclerView.ViewHolder {
        TextView xTvDepositeAmount;
        TextView xTvTypeAmount,xTvStatus;

        public ContestHolder(@NonNull View itemView) {
            super(itemView);
            xTvDepositeAmount = (itemView).findViewById(R.id.xTvDepositeAmount);
            xTvTypeAmount=(itemView).findViewById(R.id.xTvTypeAmount);
            xTvStatus=(itemView).findViewById(R.id.xTvStatus);


        }
    }
}