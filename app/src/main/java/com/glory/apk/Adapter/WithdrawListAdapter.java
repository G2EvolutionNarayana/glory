package com.glory.apk.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.glory.apk.Model.WithdrawalList.Datum;
import com.glory.apk.R;

import java.util.List;

public class WithdrawListAdapter extends RecyclerView.Adapter<WithdrawListAdapter.WithdrawListAdapterChild> {
    private Context mcontext;
    List<Datum> data;

    public WithdrawListAdapter(Context applicationContext, List<Datum> data) {
        this.data = data;
        this.mcontext = applicationContext;
    }


    @NonNull
    @Override
    public WithdrawListAdapter.WithdrawListAdapterChild onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.item_withdrawlist, parent, false);
        return new WithdrawListAdapterChild(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WithdrawListAdapter.WithdrawListAdapterChild holder, int position) {
        Datum dataList = data.get(position);

        if (dataList.getReferenceNumber() != null) {
            holder.xTvReferenceNumber.setText(dataList.getReferenceNumber());

        }
        if (dataList.getAmount() != null) {
            holder.xTvAmount.setText("\u20B9 " + dataList.getAmount());

        }
        if (dataList.getRequestedOn() != null) {
            holder.xTvDateTime.setText(dataList.getRequestedOn());

        }
        if (dataList.getStatus() != null) {

            if (dataList.getStatus() == 1) {
                holder.xTvStatus.setText("Pending");

            } else if (dataList.getStatus() == 2) {
                holder.xTvStatus.setText("Initiated");

            } else if (dataList.getStatus() == 3) {
                holder.xTvStatus.setText("Rejected");

            } else if (dataList.getStatus() == 4) {
                holder.xTvStatus.setText("Success");

            }

        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class WithdrawListAdapterChild extends RecyclerView.ViewHolder {
        TextView xTvReferenceNumber, xTvAmount, xTvDateTime, xTvStatus;

        public WithdrawListAdapterChild(@NonNull View itemView) {
            super(itemView);
            xTvReferenceNumber = (TextView) itemView.findViewById(R.id.xTvReferenceNumber);
            xTvAmount = (TextView) itemView.findViewById(R.id.xTvAmount);
            xTvDateTime = (TextView) itemView.findViewById(R.id.xTvDateTime);
            xTvStatus = (TextView) itemView.findViewById(R.id.xTvStatus);
        }
    }
}