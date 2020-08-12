package com.glory.apk.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.glory.apk.Model.WinningDataModel;
import com.glory.apk.R;

import java.util.ArrayList;

public class WinningAdapter  extends RecyclerView.Adapter<WinningAdapter.ContestHolder> {
    private Context context;
    private WinningChildAdapter transactionAdapterChild;
    ArrayList<WinningDataModel> mainList;


    public WinningAdapter(Context context, ArrayList<WinningDataModel> mainList) {
        this.context=context;
        this.mainList=mainList;
    }

    @NonNull
    @Override
    public WinningAdapter.ContestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_deposite,parent, false);
        return new ContestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WinningAdapter.ContestHolder holder, int position) {
        WinningDataModel winningDataModel=mainList.get(position);
        holder.xTvDate.setText( String.valueOf(winningDataModel.getDate()));
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        holder.xRecyclerView.setLayoutManager(mLayoutManager2);
        transactionAdapterChild=new WinningChildAdapter(context,winningDataModel.getList());
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