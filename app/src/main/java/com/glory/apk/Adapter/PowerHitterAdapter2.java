package com.glory.apk.Adapter;

import android.content.Context;
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

import com.glory.apk.Model.OpposPlayerSelected;
import com.bumptech.glide.Glide;
import com.glory.apk.R;

import java.util.ArrayList;

public class PowerHitterAdapter2 extends RecyclerView.Adapter<PowerHitterAdapter2.HitterViewHolder> {
    private Context context;
    private ArrayList<OpposPlayerSelected> playerDetailsList;

    public PowerHitterAdapter2(Context context, ArrayList<OpposPlayerSelected> playerDetailsList) {
        this.context = context;
        this.playerDetailsList = playerDetailsList;
    }

    @NonNull
    @Override
    public PowerHitterAdapter2.HitterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_power_hitter,parent, false);
        return new HitterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PowerHitterAdapter2.HitterViewHolder holder, int position) {

        OpposPlayerSelected playerDetails = playerDetailsList.get(position);
        holder.playerName.setText(playerDetails.getName());
        holder.PowerHitter.setImageResource(R.drawable.ph_icon);
        if (playerDetails.getImage() == null || playerDetails.getImage().toString().length()== 0) {
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

    @Override
    public int getItemCount() {
        return playerDetailsList.size();
    }

    public class HitterViewHolder extends RecyclerView.ViewHolder {
        private ImageView playerImage,PowerHitter;
        private TextView playerName,points;
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