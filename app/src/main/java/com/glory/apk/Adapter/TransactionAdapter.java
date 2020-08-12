package com.glory.apk.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.glory.apk.Model.TransactionDataModel;
import com.glory.apk.R;

import java.util.ArrayList;

public class TransactionAdapter  extends RecyclerView.Adapter<TransactionAdapter.ContestHolder> {
    private Context context;
    private TransactionAdapterChild transactionAdapterChild;
    ArrayList<TransactionDataModel> mainList;


    public TransactionAdapter(Context context, ArrayList<TransactionDataModel> mainList) {
        this.context=context;
        this.mainList=mainList;
    }

    @NonNull
    @Override
    public TransactionAdapter.ContestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_deposite,parent, false);
        return new ContestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.ContestHolder holder, int position) {
        TransactionDataModel transactionDataModel=mainList.get(position);
        holder.xTvDate.setText( String.valueOf(transactionDataModel.getDate()));
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        holder.xRecyclerView.setLayoutManager(mLayoutManager2);
        transactionAdapterChild=new TransactionAdapterChild(context,transactionDataModel.getList());
        holder.xRecyclerView.setAdapter(transactionAdapterChild);
    }

    @Override
    public int getItemCount() {
        return mainList.size();
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