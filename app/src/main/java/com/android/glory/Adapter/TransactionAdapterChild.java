package com.android.glory.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.glory.Model.Contest.ContestDatum;
import com.android.glory.Model.TransactionChildDataModel;
import com.android.glory.Model.TransactionDataModel;
import com.android.glory.R;

import java.util.ArrayList;
import java.util.List;

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
        if (String.valueOf(transactionChildDataModel.getTrans_type()).isEmpty()||String.valueOf(transactionChildDataModel.getTrans_type()).length()==0){

        }else {
            if (String.valueOf(transactionChildDataModel.getTrans_type())=="debit"){
                holder.xTvDepositeAmount.setText("-"+holder.xTvDepositeAmount.getText().toString());

            }else {
                holder.xTvDepositeAmount.setText("+"+holder.xTvDepositeAmount.getText().toString());

            }

        }


    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ContestHolder extends RecyclerView.ViewHolder {
        TextView xTvDepositeAmount;
        TextView xTvTypeAmount;

        public ContestHolder(@NonNull View itemView) {
            super(itemView);
            xTvDepositeAmount = (itemView).findViewById(R.id.xTvDepositeAmount);
            xTvTypeAmount=(itemView).findViewById(R.id.xTvTypeAmount);


        }
    }
}