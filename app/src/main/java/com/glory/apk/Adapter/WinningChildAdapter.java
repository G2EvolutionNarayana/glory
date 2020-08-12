package com.glory.apk.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.glory.apk.Model.WinningChildDataModel;
import com.glory.apk.R;

import java.util.ArrayList;

public class WinningChildAdapter extends RecyclerView.Adapter<WinningChildAdapter.ContestHolder> {
    private Context context;
    ArrayList<WinningChildDataModel> list;



    public WinningChildAdapter(Context context, ArrayList<WinningChildDataModel> list) {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public WinningChildAdapter.ContestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_winning_child, parent, false);
        return new ContestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WinningChildAdapter.ContestHolder holder, int position) {
        WinningChildDataModel transactionChildDataModel=list.get(position);
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