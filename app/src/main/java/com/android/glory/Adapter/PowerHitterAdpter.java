package com.android.glory.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.android.glory.Pojo.PlayerDetails;
import com.android.glory.R;

import java.util.List;

public class PowerHitterAdpter extends RecyclerView.Adapter<PowerHitterAdpter.HitterViewHolder> {
    private Context context;
    private List<PlayerDetails> playerDetailsList;

    public PowerHitterAdpter(Context context, List<PlayerDetails> playerDetailsList) {
        this.context = context;
        this.playerDetailsList = playerDetailsList;
    }

    @NonNull
    @Override
    public HitterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_power_hitter,parent, false);
        return new HitterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HitterViewHolder holder, int position) {

        PlayerDetails playerDetails = playerDetailsList.get(position);
        holder.playerImage.setImageResource(playerDetails.getImage());
        holder.playerName.setText(playerDetails.getName());
        holder.points.setText(playerDetails.getPoints());
        holder.PowerHitter.setImageResource(R.drawable.ph_icon);

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
