package com.glory.apk.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.glory.apk.Model.NotificationModel.NotificationModelDatum;
import com.glory.apk.R;

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
        holder.xTvDaysAgo.setText( dataList.getTime());

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