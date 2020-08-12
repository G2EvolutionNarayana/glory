package com.glory.apk.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.glory.apk.Model.DepositedChildModel;
import com.glory.apk.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DepositedAmountChildAdapter  extends RecyclerView.Adapter<DepositedAmountChildAdapter.ContestHolder> {
    private Context context;
    private ArrayList<DepositedChildModel> contestList;


    public DepositedAmountChildAdapter(Context context, ArrayList<DepositedChildModel> list) {
        this.context=context;
        this.contestList=list;
    }

    @NonNull
    @Override
    public DepositedAmountChildAdapter.ContestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_child_deposite, parent, false);
        return new DepositedAmountChildAdapter.ContestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DepositedAmountChildAdapter.ContestHolder holder, int position) {

        DepositedChildModel transactionChildDataModel=contestList.get(position);
        holder.xTvAmount.setText("\u20B9" + " " + transactionChildDataModel.getTrans_amount());
        holder.xTvId.setText(String.valueOf(transactionChildDataModel.getOrder_id()));
        holder.xTvBankRefNo.setText(transactionChildDataModel.getBank_ref_no());
        holder.xTvMode.setText(transactionChildDataModel.getPayment_mode());
        holder.xTvStatus.setText(transactionChildDataModel.getPamentstatus());

    }

    @Override
    public int getItemCount() {
        return contestList.size();
    }

    public class ContestHolder extends RecyclerView.ViewHolder {
        TextView xTvId,xTvBankRefNo,xTvMode,xTvAmount,xTvStatus;


        public ContestHolder(@NonNull View itemView) {
            super(itemView);
            xTvId = (itemView).findViewById(R.id.xTvId);
            xTvBankRefNo = (itemView).findViewById(R.id.xTvBankRefNo);
            xTvMode = (itemView).findViewById(R.id.xTvMode);
            xTvAmount = (itemView).findViewById(R.id.xTvAmount);
            xTvStatus = (itemView).findViewById(R.id.xTvStatus);


        }
    }
}
