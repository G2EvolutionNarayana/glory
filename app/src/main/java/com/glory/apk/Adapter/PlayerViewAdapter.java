package com.glory.apk.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.glory.apk.Pojo.PlayerDetails;
import com.glory.apk.R;

import java.util.List;

public class PlayerViewAdapter extends RecyclerView.Adapter<PlayerViewAdapter.PlayerViewHolder> {
    private Context context;
    private List<PlayerDetails> playerDetailsList;

    public PlayerViewAdapter(Context context, List<PlayerDetails> playerDetailsList) {
        this.context = context;
        this.playerDetailsList = playerDetailsList;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_player_view,parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PlayerViewHolder holder, int position) {

        PlayerDetails playerDetails = playerDetailsList.get(position);
        holder.playerImage.setImageResource(playerDetails.getImage());
        holder.playerName.setText(playerDetails.getName());
        holder.points.setText(playerDetails.getPoints());
        holder.credits.setText(playerDetails.getCredits());
        holder.add.setImageResource(R.drawable.ic_plus);
        holder.remove.setImageResource(R.drawable.ic_minus);
        holder.remove.setVisibility(View.INVISIBLE);
        holder.PlayerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.remove.setVisibility(View.VISIBLE);
                holder.add.setVisibility(View.INVISIBLE);

            }
        });

    }

    @Override
    public int getItemCount() {
        return playerDetailsList.size();
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder {
        private ImageView playerImage,add,remove;
        private TextView playerName,points,credits;
        private CardView PlayerView;
        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            playerImage = (ImageView) itemView.findViewById(R.id.player_img);
            playerName = (TextView) itemView.findViewById(R.id.player_name);
            points = (TextView) itemView.findViewById(R.id.points);
            credits = (TextView) itemView.findViewById(R.id.credits);
            add = (ImageView) itemView.findViewById(R.id.add);
            PlayerView = (CardView) itemView.findViewById(R.id.player_view);
            remove = (ImageView) itemView.findViewById(R.id.remove);

        }
    }
}
