package com.android.glory.Adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.glory.Model.HomePlayersSelected;
import com.android.glory.Model.OpposPlayerSelected;
import com.android.glory.Model.PlayerSelection;
import com.android.glory.Model.PlayersList.PlayersAwayTeam;
import com.android.glory.Model.PlayersList.PlayersListHomeTeam;
import com.android.glory.R;
import com.android.glory.Utilites.StaticUtils;
import com.bumptech.glide.Glide;

import java.util.List;

import static com.android.glory.Utilites.StaticUtils.FINAL_COUNT;
import static com.android.glory.Utilites.StaticUtils.HomeTeamcount;
import static com.android.glory.Utilites.StaticUtils.OppoTeamcount;

public class OppositeTeamAdapter extends RecyclerView.Adapter<OppositeTeamAdapter.PlayerViewHolder> {
    private Context context;
    private List<PlayersAwayTeam> playerDetailsList;
    String packageName;
    private onItemClick mCallBack;


    public OppositeTeamAdapter(Context context, List<PlayersAwayTeam> playerDetailsList, String packageName,onItemClick mCallBack) {
        this.context = context;
        this.playerDetailsList = playerDetailsList;
        this.packageName=packageName;
        this.mCallBack=mCallBack;
    }

    @NonNull
    @Override
    public OppositeTeamAdapter.PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_opposite_team, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final OppositeTeamAdapter.PlayerViewHolder holder, int position) {
        PlayerSelection playerSelection = new PlayerSelection(false);
        //     OpposPlayerSelected opposPlayerSelected = new OpposPlayerSelected(-1);
//        mCallBack.onItemClick(String.valueOf(FINAL_COUNT),String.valueOf(HomeTeamcount),String.valueOf(OppoTeamcount));
        if(packageName.equals("5")){
            mCallBack.onItemClick(String.valueOf(FINAL_COUNT),String.valueOf(HomeTeamcount),String.valueOf(OppoTeamcount),StaticUtils.CREDITS5);

        }else {

            mCallBack.onItemClick(String.valueOf(FINAL_COUNT),String.valueOf(HomeTeamcount),String.valueOf(OppoTeamcount),StaticUtils.CREDITS7);

        }
//        holder.add.setImageResource(R.drawable.ic_minus);
        PlayersAwayTeam playerDetails = playerDetailsList.get(position);
        holder.playerName.setText(playerDetails.getPlayer().getFullName());
        holder.credits.setText(playerDetails.getCredits().toString());

        if (playerDetails.getPlayer().getImageURL() == null || playerDetails.getPlayer().getImageURL().toString().length() == 0) {
            Glide.with(context)
                    .load(Uri.parse(String.valueOf(R.drawable.user)))
                    .error(R.drawable.user)
                    .into(holder.playerImage);
            Log.e("testing", "getImageUrl = " + "Null Image");
        } else {
            Glide.with(context)
                    .load(Uri.parse(playerDetails.getPlayer().getImageURL().toString()))
                    .error(R.drawable.user)
                    .into(holder.playerImage);
            Log.e("testing", "getImageUrl = " + "image");
        }
//        holder.playerImage.setImageResource(playerDetails.getImage());
//        holder.playerName.setText(playerDetails.getName());
//        holder.points.setText(playerDetails.getPoints());
//        holder.credits.setText(playerDetails.getCredits());
//        holder.add.setImageResource(R.drawable.ic_plus);
//        holder.remove.setImageResource(R.drawable.ic_minus);
//        holder.remove.setVisibility(View.INVISIBLE);
        holder.PlayerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playerDetails.getSelected()){
                    gotoCheck();

                }else {
                    if(packageName.equals("5")){

                        if(StaticUtils.CREDITS5-playerDetails.getCredits()<0){
                            if (FINAL_COUNT==5){
                                Toast.makeText(context, "Maximum 5 Players you can select", Toast.LENGTH_LONG).show();

                            }else {
                                Toast.makeText(context,"Credit Points Left lessthan the Selected Player Credits",Toast.LENGTH_LONG).show();

                            }


                        }else {
                            gotoCheck();
                        }
                    }else {
                        if(StaticUtils.CREDITS7-playerDetails.getCredits()<0){

                            if (FINAL_COUNT==7){
                                Toast.makeText(context, "Maximum 7 Players you can select", Toast.LENGTH_LONG).show();

                            }else {
                                Toast.makeText(context,"Credit Points Left lessthan the Selected Player Credits",Toast.LENGTH_LONG).show();

                            }

                        }else {
                            gotoCheck();

                        }
                    }
                }
            }

            private void gotoCheck() {
                if(packageName.equals("5")){
                    if (FINAL_COUNT == 5) {
                        if (playerDetails.getSelected()) {
                            playerSelection.setSelected(false);
                            holder.add.setImageResource(R.drawable.ic_plus);
                            playerDetails.getCredits();
                            StaticUtils.CREDITS5=StaticUtils.CREDITS5+playerDetails.getCredits();
                            FINAL_COUNT--;
                            holder.points.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                            holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                            holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                            playerDetails.setSelected(false);
                            OppoTeamcount--;

                            mCallBack.onItemClick(String.valueOf(FINAL_COUNT),String.valueOf(HomeTeamcount),String.valueOf(OppoTeamcount),StaticUtils.CREDITS5);

//                        StaticUtils.opposePlayers.add(new OpposPlayerSelected(playerDetails.getPlayer().getFullName(), "https://www.cricket.com.au/-/media/Players/Men/International/Bangladesh/Tamim-Iqbal-CWC19.ashx", playerDetails.getPlayer().getId(),false));

//                        opposPlayerSelected.setOppositePlayer(-1);
                        } else {
                            Toast.makeText(context, "Maximum 5 Players you can select", Toast.LENGTH_LONG).show();
//                        playerSelection.setSelected(false);
//                        holder.add.setImageResource(R.drawable.ic_minus);
//                        StaticUtils.HomeTeamcount--;
                        }
                    } else {
                        if (StaticUtils.HomeTeamcount == 3) {

                            if (playerDetails.getSelected()) {

                                playerSelection.setSelected(false);
                                holder.add.setImageResource(R.drawable.ic_plus);
                                FINAL_COUNT--;
                                holder.points.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                StaticUtils.CREDITS5=StaticUtils.CREDITS5+playerDetails.getCredits();

                                OppoTeamcount--;
                                playerDetails.setSelected(false);
                                mCallBack.onItemClick(String.valueOf(FINAL_COUNT),String.valueOf(HomeTeamcount),String.valueOf(OppoTeamcount),StaticUtils.CREDITS5);

//                            StaticUtils.opposePlayers.add(new OpposPlayerSelected(playerDetails.getPlayer().getFullName(), "https://www.cricket.com.au/-/media/Players/Men/International/Bangladesh/Tamim-Iqbal-CWC19.ashx", playerDetails.getPlayer().getId(),false));


                            } else {
                                if (StaticUtils.OppoTeamcount < 3) {
                                    playerDetails.setSelected(true);
                                    StaticUtils.OppoTeamcount++;
                                    FINAL_COUNT++;
                                    StaticUtils.CREDITS5=StaticUtils.CREDITS5-playerDetails.getCredits();

                                    holder.points.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.credits.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.add.setImageResource(R.drawable.ic_minus);
                                    mCallBack.onItemClick(String.valueOf(FINAL_COUNT),String.valueOf(HomeTeamcount),String.valueOf(OppoTeamcount),StaticUtils.CREDITS5);

//                                StaticUtils.opposePlayers.add(new OpposPlayerSelected(playerDetails.getPlayer().getFullName(), "https://www.cricket.com.au/-/media/Players/Men/International/Bangladesh/Tamim-Iqbal-CWC19.ashx", playerDetails.getPlayer().getId(),true));

                                } else {
                                    Toast.makeText(context, "Maximum 5 Players you can select", Toast.LENGTH_LONG).show();

                                }


                            }
                        } else {
                            if (OppoTeamcount == 3) {
                                if (playerDetails.getSelected()) {
                                    playerDetails.setSelected(false);
                                    holder.add.setImageResource(R.drawable.ic_plus);
                                    FINAL_COUNT--;
                                    StaticUtils.CREDITS5=StaticUtils.CREDITS5+playerDetails.getCredits();

                                    holder.points.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
//                                StaticUtils.opposePlayers.add(new OpposPlayerSelected(playerDetails.getPlayer().getFullName(), "https://www.cricket.com.au/-/media/Players/Men/International/Bangladesh/Tamim-Iqbal-CWC19.ashx", playerDetails.getPlayer().getId(),false));
                                    OppoTeamcount--;
                                    mCallBack.onItemClick(String.valueOf(FINAL_COUNT),String.valueOf(HomeTeamcount),String.valueOf(OppoTeamcount),StaticUtils.CREDITS5);

                                } else {
                                    Toast.makeText(context, "Maximum 3 Players you can select From one Team", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                if (playerDetails.getSelected()) {
                                    playerDetails.setSelected(false);
                                    holder.add.setImageResource(R.drawable.ic_plus);
                                    FINAL_COUNT--;
                                    StaticUtils.CREDITS5=StaticUtils.CREDITS5+playerDetails.getCredits();

                                    holder.points.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
//                                StaticUtils.opposePlayers.add(new OpposPlayerSelected(playerDetails.getPlayer().getFullName(), "https://www.cricket.com.au/-/media/Players/Men/International/Bangladesh/Tamim-Iqbal-CWC19.ashx", playerDetails.getPlayer().getId(),false));
                                    OppoTeamcount--;
                                    mCallBack.onItemClick(String.valueOf(FINAL_COUNT),String.valueOf(HomeTeamcount),String.valueOf(OppoTeamcount),StaticUtils.CREDITS5);

                                } else {
                                    playerDetails.setSelected(true);
                                    StaticUtils.OppoTeamcount++;
                                    FINAL_COUNT++;
                                    StaticUtils.CREDITS5=StaticUtils.CREDITS5-playerDetails.getCredits();

                                    holder.points.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.credits.setTextColor(context.getResources().getColor(R.color.greencolor));
//                                StaticUtils.opposePlayers.add(new OpposPlayerSelected(playerDetails.getPlayer().getFullName(), "https://www.cricket.com.au/-/media/Players/Men/International/Bangladesh/Tamim-Iqbal-CWC19.ashx", playerDetails.getPlayer().getId(),true));
                                    holder.add.setImageResource(R.drawable.ic_minus);
                                    mCallBack.onItemClick(String.valueOf(FINAL_COUNT),String.valueOf(HomeTeamcount),String.valueOf(OppoTeamcount),StaticUtils.CREDITS5);

                                }
                            }
                        }
                    }
                }else {


                    if (FINAL_COUNT == 7) {
                        if (playerDetails.getSelected()) {
                            playerSelection.setSelected(false);
                            holder.add.setImageResource(R.drawable.ic_plus);
                            FINAL_COUNT--;
                            StaticUtils.CREDITS7=StaticUtils.CREDITS7+playerDetails.getCredits();
                            holder.points.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                            holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                            holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                            playerDetails.setSelected(false);

//                        StaticUtils.opposePlayers.add(new OpposPlayerSelected(playerDetails.getPlayer().getFullName(), "https://www.cricket.com.au/-/media/Players/Men/International/Bangladesh/Tamim-Iqbal-CWC19.ashx", playerDetails.getPlayer().getId(),false));

//                        opposPlayerSelected.setOppositePlayer(-1);
                            OppoTeamcount--;
                            mCallBack.onItemClick(String.valueOf(FINAL_COUNT),String.valueOf(HomeTeamcount),String.valueOf(OppoTeamcount),StaticUtils.CREDITS7);

                        } else {
                            Toast.makeText(context, "Maximum 7 Players you can select", Toast.LENGTH_LONG).show();
//                        playerSelection.setSelected(false);
//                        holder.add.setImageResource(R.drawable.ic_minus);
//                        StaticUtils.HomeTeamcount--;
                        }
                    } else {
                        if (StaticUtils.HomeTeamcount == 4) {

                            if (playerDetails.getSelected()) {

                                playerSelection.setSelected(false);
                                holder.add.setImageResource(R.drawable.ic_plus);
                                FINAL_COUNT--;
                                StaticUtils.CREDITS7=StaticUtils.CREDITS7+playerDetails.getCredits();

                                holder.points.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                OppoTeamcount--;
                                playerDetails.setSelected(false);
                                mCallBack.onItemClick(String.valueOf(FINAL_COUNT),String.valueOf(HomeTeamcount),String.valueOf(OppoTeamcount),StaticUtils.CREDITS7);

//                            StaticUtils.opposePlayers.add(new OpposPlayerSelected(playerDetails.getPlayer().getFullName(), "https://www.cricket.com.au/-/media/Players/Men/International/Bangladesh/Tamim-Iqbal-CWC19.ashx", playerDetails.getPlayer().getId(),false));


                            } else {
                                if (StaticUtils.OppoTeamcount < 4) {
                                    playerDetails.setSelected(true);
                                    StaticUtils.OppoTeamcount++;
                                    FINAL_COUNT++;
                                    StaticUtils.CREDITS7=StaticUtils.CREDITS7-playerDetails.getCredits();

                                    holder.points.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.credits.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.add.setImageResource(R.drawable.ic_minus);
                                    mCallBack.onItemClick(String.valueOf(FINAL_COUNT),String.valueOf(HomeTeamcount),String.valueOf(OppoTeamcount),StaticUtils.CREDITS7);

//                                StaticUtils.opposePlayers.add(new OpposPlayerSelected(playerDetails.getPlayer().getFullName(), "https://www.cricket.com.au/-/media/Players/Men/International/Bangladesh/Tamim-Iqbal-CWC19.ashx", playerDetails.getPlayer().getId(),true));

                                } else {
                                    Toast.makeText(context, "Maximum 5 Players you can select", Toast.LENGTH_LONG).show();

                                }


                            }
                        } else {
                            if (OppoTeamcount == 4) {
                                if (playerDetails.getSelected()) {
                                    playerDetails.setSelected(false);
                                    holder.add.setImageResource(R.drawable.ic_plus);
                                    FINAL_COUNT--;
                                    StaticUtils.CREDITS7=StaticUtils.CREDITS7+playerDetails.getCredits();

                                    holder.points.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
//                                StaticUtils.opposePlayers.add(new OpposPlayerSelected(playerDetails.getPlayer().getFullName(), "https://www.cricket.com.au/-/media/Players/Men/International/Bangladesh/Tamim-Iqbal-CWC19.ashx", playerDetails.getPlayer().getId(),false));
                                    OppoTeamcount--;
                                    mCallBack.onItemClick(String.valueOf(FINAL_COUNT),String.valueOf(HomeTeamcount),String.valueOf(OppoTeamcount),StaticUtils.CREDITS7);

                                } else {
                                    Toast.makeText(context, "Maximum 4 Players you can select From one Team", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                if (playerDetails.getSelected()) {
                                    playerDetails.setSelected(false);
                                    holder.add.setImageResource(R.drawable.ic_plus);
                                    FINAL_COUNT--;
                                    StaticUtils.CREDITS7=StaticUtils.CREDITS7+playerDetails.getCredits();

                                    holder.points.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
//                                StaticUtils.opposePlayers.add(new OpposPlayerSelected(playerDetails.getPlayer().getFullName(), "https://www.cricket.com.au/-/media/Players/Men/International/Bangladesh/Tamim-Iqbal-CWC19.ashx", playerDetails.getPlayer().getId(),false));
                                    OppoTeamcount--;
                                    mCallBack.onItemClick(String.valueOf(FINAL_COUNT),String.valueOf(HomeTeamcount),String.valueOf(OppoTeamcount),StaticUtils.CREDITS7);

                                } else {
                                    playerDetails.setSelected(true);
                                    StaticUtils.OppoTeamcount++;
                                    FINAL_COUNT++;
                                    StaticUtils.CREDITS7=StaticUtils.CREDITS7-playerDetails.getCredits();
                                    holder.points.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.credits.setTextColor(context.getResources().getColor(R.color.greencolor));
//                                StaticUtils.opposePlayers.add(new OpposPlayerSelected(playerDetails.getPlayer().getFullName(), "https://www.cricket.com.au/-/media/Players/Men/International/Bangladesh/Tamim-Iqbal-CWC19.ashx", playerDetails.getPlayer().getId(),true));
                                    holder.add.setImageResource(R.drawable.ic_minus);
                                    mCallBack.onItemClick(String.valueOf(FINAL_COUNT),String.valueOf(HomeTeamcount),String.valueOf(OppoTeamcount),StaticUtils.CREDITS7);

                                }
                            }
                        }
                    }

                }
            }
        });

    }
    public interface onItemClick{
        void onItemClick(String finalValue,String homeValue,String OppositeValue,Double credits);

    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return playerDetailsList.size();
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder {
        private ImageView playerImage, add, remove;
        private TextView playerName, points, credits;
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

        public CardView getPlayerView() {
            return PlayerView;
        }

        public ImageView getAdd() {
            return add;
        }

        public ImageView getPlayerImage() {
            return playerImage;
        }

        public ImageView getRemove() {
            return remove;
        }

        public TextView getCredits() {
            return credits;
        }

        public TextView getPlayerName() {
            return playerName;
        }

        public TextView getPoints() {
            return points;
        }

    }
}