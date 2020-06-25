package com.android.glory.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.android.glory.Model.HomePlayersSelected;
import com.android.glory.Model.PowerHitterModel;
import com.android.glory.Pojo.PlayerDetails;
import com.android.glory.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class PowerHitterAdpter extends RecyclerView.Adapter<PowerHitterAdpter.HitterViewHolder> {
    private Context context;
    private ArrayList<PowerHitterModel> playerDetailsList;
    public static int count = -1;
    public  OnItemClick mCallBack;

    public PowerHitterAdpter(Context context, ArrayList<PowerHitterModel> playerDetailsList,OnItemClick mCallBack) {
        this.context = context;
        this.playerDetailsList = playerDetailsList;
        this.mCallBack=mCallBack;
    }

    @NonNull
    @Override
    public HitterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_power_hitter, parent, false);
        return new HitterViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull HitterViewHolder holder, int position) {

        PowerHitterModel playerDetails = playerDetailsList.get(position);

        if (count != position) {
            Log.e("testing", "count =" + count);
            playerDetails.setPowerHitter(false);
            holder.playerName.setText(playerDetails.getName());
            holder.points.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            playerDetails.setPowerHitter(false);
            holder.PowerHitter.setImageResource(R.drawable.ph_icon_black);

//        holder.PowerHitter.setImageResource(R.drawable.ph_icon);
            if (playerDetails.getImage() == null || playerDetails.getImage().toString().length() == 0) {
                Glide.with(context)
                        .load(Uri.parse(String.valueOf(R.drawable.user)))
                        .error(R.drawable.user)
                        .into(holder.playerImage);
                Log.e("testing", "getImageUrl = " + "Null Image");
            } else {
                Glide.with(context)
                        .load(Uri.parse(playerDetails.getImage()))
                        .error(R.drawable.user)
                        .into(holder.playerImage);
                Log.e("testing", "getImageUrl = " + "image");
            }

        } else {
            Log.e("testing", "count =" + count);
            holder.PowerHitter.setImageResource(R.drawable.ph_icon);
            holder.playerName.setText(playerDetails.getName());
            holder.PowerHitter.setVisibility(View.VISIBLE);
            playerDetails.setPowerHitter(true);
            holder.points.setTextColor(context.getResources().getColor(R.color.greencolor));
            holder.playerName.setTextColor(context.getResources().getColor(R.color.greencolor));
            if (playerDetails.getImage() == null || playerDetails.getImage().toString().length() == 0) {
                Glide.with(context)
                        .load(Uri.parse(String.valueOf(R.drawable.user)))
                        .error(R.drawable.user)
                        .into(holder.playerImage);
                Log.e("testing", "getImageUrl = " + "Null Image");
            } else {
                Glide.with(context)
                        .load(Uri.parse(playerDetails.getImage()))
                        .error(R.drawable.user)
                        .into(holder.playerImage);
                Log.e("testing", "getImageUrl = " + "image");
            }
        }
        holder.PlayerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = position;
                mCallBack.onClicKListner(position);
                notifyDataSetChanged();
            }
        });

    }

    public interface OnItemClick{
        void onClicKListner(int position);
    }
    @Override
    public int getItemCount() {
        return playerDetailsList.size();
    }

    public class HitterViewHolder extends RecyclerView.ViewHolder {
        private ImageView playerImage, PowerHitter;
        private TextView playerName, points;
        private CardView PlayerView;

        public HitterViewHolder(@NonNull View itemView) {
            super(itemView);
            playerImage = (ImageView) itemView.findViewById(R.id.player_img);
            playerName = (TextView) itemView.findViewById(R.id.player_name);
            points = (TextView) itemView.findViewById(R.id.points);
            PlayerView = (CardView) itemView.findViewById(R.id.player_view);
            PowerHitter = (ImageView) itemView.findViewById(R.id.power_hitter);
        }
    }
}
