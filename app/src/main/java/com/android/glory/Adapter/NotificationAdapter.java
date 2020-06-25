package com.android.glory.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.glory.Activity.Preview5MainActivity;
import com.android.glory.Activity.Preview7MainActivity;
import com.android.glory.Model.NotificationModel.NotificationModelDatum;
import com.android.glory.Model.Pending.PendingDatum;
import com.android.glory.R;
import com.android.glory.Utilites.StaticUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotificationAdapter  extends RecyclerView.Adapter<NotificationAdapter.NotificationAdapterChild> {
    private Context mcontext;
    List<NotificationModelDatum> data;

    public NotificationAdapter(Context applicationContext, List<NotificationModelDatum> data) {
        this.data = data;
        this.mcontext = applicationContext;
    }

    @NonNull
    @Override
    public NotificationAdapter.NotificationAdapterChild onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.item_adapter_notification, parent, false);
        return new NotificationAdapterChild(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.NotificationAdapterChild holder, int position) {
        NotificationModelDatum dataList = data.get(position);
        holder.xTvNotificationText.setText( dataList.getNotificationText());
        holder.xTvDaysAgo.setText("6 days ago");


    }

    private void setText(String s) {
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class NotificationAdapterChild extends RecyclerView.ViewHolder {
        TextView xTvDaysAgo, xTvNotificationText;
        public NotificationAdapterChild(@NonNull View itemView) {
            super(itemView);
            xTvDaysAgo = (TextView) itemView.findViewById(R.id.xTvDaysAgo);
            xTvNotificationText = (TextView) itemView.findViewById(R.id.xTvNotificationText);

        }
    }
}