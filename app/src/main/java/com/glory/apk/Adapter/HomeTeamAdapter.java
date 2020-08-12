package com.glory.apk.Adapter;

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
import androidx.recyclerview.widget.RecyclerView;

import com.glory.apk.Model.PlayersList.PlayersListHomeTeam;
import com.glory.apk.R;
import com.glory.apk.Utilites.StaticUtils;
import com.bumptech.glide.Glide;

import java.util.List;

public class HomeTeamAdapter extends RecyclerView.Adapter<HomeTeamAdapter.PlayerViewHolder> {
    private Context context;
    private List<PlayersListHomeTeam> playerDetailsList;
    String packageName;
    private onItemClick mCallBack;

    public HomeTeamAdapter(Context context, List<PlayersListHomeTeam> playerDetailsList, String packageName,onItemClick mCallBack) {
        this.context = context;
        this.playerDetailsList = playerDetailsList;
        this.packageName=packageName;
        this.mCallBack=mCallBack;
    }

    @NonNull
    @Override
    public HomeTeamAdapter.PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_team, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeTeamAdapter.PlayerViewHolder holder, int position) {
//        PlayerSelection playerSelection = new PlayerSelection(false);
        if(packageName.equals("5")){
            mCallBack.onItemClick(String.valueOf(StaticUtils.FINAL_COUNT),String.valueOf(StaticUtils.HomeTeamcount),String.valueOf(StaticUtils.OppoTeamcount),StaticUtils.CREDITS5);

        }else {
            mCallBack.onItemClick(String.valueOf(StaticUtils.FINAL_COUNT),String.valueOf(StaticUtils.HomeTeamcount),String.valueOf(StaticUtils.OppoTeamcount),StaticUtils.CREDITS7);
        }

        PlayersListHomeTeam playerDetails = playerDetailsList.get(position);
        holder.playerName.setText(playerDetails.getPlayer().getFullName());

        if (playerDetails.getPlayer().getPlayerType()!=null){
            Log.e("teting",playerDetails.getPlayer().getPlayerType());

            holder.xTvPlayerType.setVisibility(View.VISIBLE);

            if (playerDetails.getPlayer().getPlayerType().equalsIgnoreCase("Batsman")){
                holder.xTvPlayerType.setText("(BAT)");
            }else if(playerDetails.getPlayer().getPlayerType().equalsIgnoreCase("Bowler")){
                holder.xTvPlayerType.setText("(BWL)");

            }else if(playerDetails.getPlayer().getPlayerType().equalsIgnoreCase("Allrounder")){
                holder.xTvPlayerType.setText("(ALL)");

            }else if(playerDetails.getPlayer().getPlayerType().equalsIgnoreCase("Wicketkeeper")){
                holder.xTvPlayerType.setText("(WK)");

            }else {
                holder.xTvPlayerType.setVisibility(View.GONE);
            }

        }else {
            holder.xTvPlayerType.setVisibility(View.GONE);

        }

//        holder.playerName.setText(playerDetails.getPlayer().getFullName());
        if (playerDetails.getCreadits()!= null) {
            holder.credits.setText(playerDetails.getCreadits().toString());

        }
//        if (playerDetails.getCreadits()==null){
//
//        }else {
//
//        }

        if (playerDetails.getPlayer().getImageURL() == null || playerDetails.getPlayer().getImageURL().length() == 0) {
            Glide.with(context)
                    .load((R.drawable.player))
                    .error(R.drawable.player)
                    .into(holder.playerImage);
            Log.e("testing", "getImageUrl = " + "Null Image");
        } else {
            Glide.with(context)
                    .load(Uri.parse(playerDetails.getPlayer().getImageURL().toString()))
                    .error(R.drawable.player)
                    .into(holder.playerImage);
            Log.e("testing", "getImageUrl = " + "image");
        }
//        holder.playerImage.setImageResource(playerDetails.getImage());
//        holder.playerName.setText(playerDetails.getName());
//        holder.credits.setText(playerDetails.getCredits());
//        holder.add.setImageResource(R.drawable.ic_plus);

//        holder.add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//            }
//        });
        holder.PlayerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context,String.valueOf(StaticUtils.CREDITS5),Toast.LENGTH_LONG).show();
               if (playerDetails.getCreadits()!=null){
                   if (playerDetails.getSelected()){
                       gotoCheck();

                   }else {
                       if(packageName.equals("5")){

                           if(StaticUtils.CREDITS5-playerDetails.getCreadits()<0){

                               if (StaticUtils.FINAL_COUNT==5){
                                   Toast.makeText(context, "Maximum 5 Players you can select", Toast.LENGTH_LONG).show();

                               }else {
                                   Toast.makeText(context,"Credit Points Left lessthan the Selected Player Credits",Toast.LENGTH_LONG).show();

                               }

                           }else {
                               gotoCheck();
                           }
                       }else {
                           if(StaticUtils.CREDITS7-playerDetails.getCreadits()<0){
                               if (StaticUtils.FINAL_COUNT==7){
                                   Toast.makeText(context, "Maximum 7 Players you can select", Toast.LENGTH_LONG).show();

                               }else {
                                   Toast.makeText(context,"Credit Points Left lessthan the Selected Player Credits",Toast.LENGTH_LONG).show();

                               }
                           }else {
                               gotoCheck();

                           }
                       }
                   }
               }else {
                   Toast.makeText(context, "Player credit points still not updated.", Toast.LENGTH_LONG).show();

               }



            }

            private void gotoCheck() {

                if(packageName.equals("5")){
                    if (StaticUtils.FINAL_COUNT == 5) {
                        if (playerDetails.getSelected()) {
//                        playerSelection.setSelected(false);
                            holder.add.setImageResource(R.drawable.ic_plus);
                            StaticUtils.FINAL_COUNT--;
                            StaticUtils.CREDITS5=StaticUtils.CREDITS5+playerDetails.getCreadits();

//                        StaticUtils.homePlayers.remove(position);
                            playerDetails.setSelected(false);
                            holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                            holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.colorPrimary));

                            holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                            //       StaticUtils.homePlayers.add(new HomePlayersSelected(playerDetails.getPlayer().getFullName(),playerDetails.getPlayer().getImageURL(),playerDetails.getPlayer().getId(),false));
                            StaticUtils.HomeTeamcount--;
                            mCallBack.onItemClick(String.valueOf(StaticUtils.FINAL_COUNT),String.valueOf(StaticUtils.HomeTeamcount),String.valueOf(StaticUtils.OppoTeamcount),StaticUtils.CREDITS5);

                        } else {
                            Toast.makeText(context, "Maximum 5 Players you can select", Toast.LENGTH_LONG).show();
//                        playerSelection.setSelected(false);
//                        holder.add.setImageResource(R.drawable.ic_minus);
//                        StaticUtils.HomeTeamcount--;
                        }
                    } else {
                        if (StaticUtils.OppoTeamcount == 3) {

                            if (playerDetails.getSelected()) {
                                holder.add.setImageResource(R.drawable.ic_minus);
                                StaticUtils.FINAL_COUNT--;
                                StaticUtils.CREDITS5=StaticUtils.CREDITS5+playerDetails.getCreadits();

                                playerDetails.setSelected(false);
                                holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.colorPrimary));

//                            StaticUtils.homePlayers.add(new HomePlayersSelected(playerDetails.getPlayer().getFullName(),playerDetails.getPlayer().getImageURL(),playerDetails.getPlayer().getId(),false));
                                StaticUtils.HomeTeamcount--;
                                mCallBack.onItemClick(String.valueOf(StaticUtils.FINAL_COUNT),String.valueOf(StaticUtils.HomeTeamcount),String.valueOf(StaticUtils.OppoTeamcount),StaticUtils.CREDITS5);

                            } else {
                                if (StaticUtils.HomeTeamcount < 3) {
                                    StaticUtils.HomeTeamcount++;
                                    StaticUtils.FINAL_COUNT++;
                                    StaticUtils.CREDITS5=StaticUtils.CREDITS5-playerDetails.getCreadits();

                                    playerDetails.setSelected(true);
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.credits.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.greencolor));

//                                StaticUtils.homePlayers.add(new HomePlayersSelected(playerDetails.getPlayer().getFullName(),playerDetails.getPlayer().getImageURL(),playerDetails.getPlayer().getId(),true));
                                    holder.add.setImageResource(R.drawable.ic_minus);
                                    mCallBack.onItemClick(String.valueOf(StaticUtils.FINAL_COUNT),String.valueOf(StaticUtils.HomeTeamcount),String.valueOf(StaticUtils.OppoTeamcount),StaticUtils.CREDITS5);

                                } else {
                                    Toast.makeText(context, "Maximum 5 Players you can select", Toast.LENGTH_LONG).show();
                                }
                            }
                        } else {
                            if (StaticUtils.HomeTeamcount == 3) {
                                if (playerDetails.getSelected()) {
                                    holder.add.setImageResource(R.drawable.ic_plus);
                                    StaticUtils.FINAL_COUNT--;
                                    //         StaticUtils.homePlayers.remove(position);
                                    StaticUtils.HomeTeamcount--;
                                    StaticUtils.CREDITS5=StaticUtils.CREDITS5+playerDetails.getCreadits();

                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.colorPrimary));

                                    playerDetails.setSelected(false);
                                    mCallBack.onItemClick(String.valueOf(StaticUtils.FINAL_COUNT),String.valueOf(StaticUtils.HomeTeamcount),String.valueOf(StaticUtils.OppoTeamcount),StaticUtils.CREDITS5);


//                                StaticUtils.homePlayers.add(new HomePlayersSelected(playerDetails.getPlayer().getFullName(),playerDetails.getPlayer().getImageURL(),playerDetails.getPlayer().getId(),false));

                                }else {
                                    Toast.makeText(context, "Maximum 3 Players you can select From one Team", Toast.LENGTH_LONG).show();

                                }
                            } else {
                                if (playerDetails.getSelected()) {
                                    holder.add.setImageResource(R.drawable.ic_plus);
                                    StaticUtils.FINAL_COUNT--;
                                    StaticUtils.CREDITS5=StaticUtils.CREDITS5+playerDetails.getCreadits();

                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.colorPrimary));

                                    //         StaticUtils.homePlayers.remove(position);
                                    StaticUtils.HomeTeamcount--;
                                    playerDetails.setSelected(false);
                                    mCallBack.onItemClick(String.valueOf(StaticUtils.FINAL_COUNT),String.valueOf(StaticUtils.HomeTeamcount),String.valueOf(StaticUtils.OppoTeamcount),StaticUtils.CREDITS5);


//                                StaticUtils.homePlayers.add(new HomePlayersSelected(playerDetails.getPlayer().getFullName(),playerDetails.getPlayer().getImageURL(),playerDetails.getPlayer().getId(),false));

                                } else {
                                    playerDetails.setSelected(true);
                                    StaticUtils.HomeTeamcount++;
                                    StaticUtils.FINAL_COUNT++;
                                    StaticUtils.CREDITS5=StaticUtils.CREDITS5-playerDetails.getCreadits();

//                                StaticUtils.homePlayers.add(new HomePlayersSelected(playerDetails.getPlayer().getFullName(),playerDetails.getPlayer().getImageURL(),playerDetails.getPlayer().getId(),true));
                                    holder.add.setImageResource(R.drawable.ic_minus);
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.greencolor));

                                    holder.credits.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    mCallBack.onItemClick(String.valueOf(StaticUtils.FINAL_COUNT),String.valueOf(StaticUtils.HomeTeamcount),String.valueOf(StaticUtils.OppoTeamcount),StaticUtils.CREDITS5);


                                }
                            }


                        }

                    }
                }else {

                    // if it is 7A
                    if (StaticUtils.FINAL_COUNT == 7) {
                        if (playerDetails.getSelected()) {
//                        playerSelection.setSelected(false);
                            holder.add.setImageResource(R.drawable.ic_plus);
                            StaticUtils.FINAL_COUNT--;
                            StaticUtils.CREDITS7=StaticUtils.CREDITS7+playerDetails.getCreadits();

//                        StaticUtils.homePlayers.remove(position);
                            playerDetails.setSelected(false);
                            holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                            holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                            holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.colorPrimary));

                            //       StaticUtils.homePlayers.add(new HomePlayersSelected(playerDetails.getPlayer().getFullName(),playerDetails.getPlayer().getImageURL(),playerDetails.getPlayer().getId(),false));
                            StaticUtils.HomeTeamcount--;
                            mCallBack.onItemClick(String.valueOf(StaticUtils.FINAL_COUNT),String.valueOf(StaticUtils.HomeTeamcount),String.valueOf(StaticUtils.OppoTeamcount),StaticUtils.CREDITS7);

                        } else {
                            Toast.makeText(context, "Maximum 7 Players you can select", Toast.LENGTH_LONG).show();
//                        playerSelection.setSelected(false);
//                        holder.add.setImageResource(R.drawable.ic_minus);
//                        StaticUtils.HomeTeamcount--;
                        }
                    } else {
                        if (StaticUtils.OppoTeamcount == 4) {

                            if (playerDetails.getSelected()) {
                                holder.add.setImageResource(R.drawable.ic_plus);
                                StaticUtils.FINAL_COUNT--;
                                playerDetails.setSelected(false);
                                holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.colorPrimary));

                                StaticUtils.CREDITS7=StaticUtils.CREDITS7+playerDetails.getCreadits();

//                            StaticUtils.homePlayers.add(new HomePlayersSelected(playerDetails.getPlayer().getFullName(),playerDetails.getPlayer().getImageURL(),playerDetails.getPlayer().getId(),false));
                                StaticUtils.HomeTeamcount--;
                                mCallBack.onItemClick(String.valueOf(StaticUtils.FINAL_COUNT),String.valueOf(StaticUtils.HomeTeamcount),String.valueOf(StaticUtils.OppoTeamcount),StaticUtils.CREDITS7);


                            } else {
                                if (StaticUtils.HomeTeamcount < 4) {
                                    StaticUtils.HomeTeamcount++;
                                    StaticUtils.FINAL_COUNT++;
                                    playerDetails.setSelected(true);
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.credits.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.greencolor));

                                    StaticUtils.CREDITS7=StaticUtils.CREDITS7-playerDetails.getCreadits();

//                                StaticUtils.homePlayers.add(new HomePlayersSelected(playerDetails.getPlayer().getFullName(),playerDetails.getPlayer().getImageURL(),playerDetails.getPlayer().getId(),true));
                                    holder.add.setImageResource(R.drawable.ic_minus);
                                    mCallBack.onItemClick(String.valueOf(StaticUtils.FINAL_COUNT),String.valueOf(StaticUtils.HomeTeamcount),String.valueOf(StaticUtils.OppoTeamcount),StaticUtils.CREDITS7);

                                } else {
                                    Toast.makeText(context, "Maximum 7 Players you can select", Toast.LENGTH_LONG).show();
                                }
                            }
                        } else {
                            if (StaticUtils.HomeTeamcount == 4) {
                                if (playerDetails.getSelected()) {
                                    holder.add.setImageResource(R.drawable.ic_plus);
                                    StaticUtils.FINAL_COUNT--;
                                    StaticUtils.CREDITS7=StaticUtils.CREDITS7+playerDetails.getCreadits();

                                    //         StaticUtils.homePlayers.remove(position);
                                    StaticUtils.HomeTeamcount--;
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.colorPrimary));

                                    playerDetails.setSelected(false);
                                    mCallBack.onItemClick(String.valueOf(StaticUtils.FINAL_COUNT),String.valueOf(StaticUtils.HomeTeamcount),String.valueOf(StaticUtils.OppoTeamcount),StaticUtils.CREDITS7);


//                                StaticUtils.homePlayers.add(new HomePlayersSelected(playerDetails.getPlayer().getFullName(),playerDetails.getPlayer().getImageURL(),playerDetails.getPlayer().getId(),false));

                                }else {
                                    Toast.makeText(context, "Maximum 4 Players you can select From one Team", Toast.LENGTH_LONG).show();

                                }
                            } else {
                                if (playerDetails.getSelected()) {
                                    holder.add.setImageResource(R.drawable.ic_plus);
                                    StaticUtils.FINAL_COUNT--;
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.colorPrimary));

                                    //         StaticUtils.homePlayers.remove(position);
                                    StaticUtils.HomeTeamcount--;
                                    StaticUtils.CREDITS7=StaticUtils.CREDITS7+playerDetails.getCreadits();

                                    playerDetails.setSelected(false);
                                    mCallBack.onItemClick(String.valueOf(StaticUtils.FINAL_COUNT),String.valueOf(StaticUtils.HomeTeamcount),String.valueOf(StaticUtils.OppoTeamcount),StaticUtils.CREDITS7);


//                                StaticUtils.homePlayers.add(new HomePlayersSelected(playerDetails.getPlayer().getFullName(),playerDetails.getPlayer().getImageURL(),playerDetails.getPlayer().getId(),false));

                                } else {
                                    playerDetails.setSelected(true);
                                    StaticUtils.HomeTeamcount++;
                                    StaticUtils.FINAL_COUNT++;
                                    StaticUtils.CREDITS7=StaticUtils.CREDITS7-playerDetails.getCreadits();

//                                StaticUtils.homePlayers.add(new HomePlayersSelected(playerDetails.getPlayer().getFullName(),playerDetails.getPlayer().getImageURL(),playerDetails.getPlayer().getId(),true));
                                    holder.add.setImageResource(R.drawable.ic_minus);
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.credits.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    mCallBack.onItemClick(String.valueOf(StaticUtils.FINAL_COUNT),String.valueOf(StaticUtils.HomeTeamcount),String.valueOf(StaticUtils.OppoTeamcount),StaticUtils.CREDITS7);



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
    public long getItemId(int position) {
        return position;
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
        private ImageView playerImage, add;
        private TextView playerName, credits,xTvPlayerType;
        private CardView PlayerView;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            playerImage = (ImageView) itemView.findViewById(R.id.player_img);
            playerName = (TextView) itemView.findViewById(R.id.player_name);
            credits = (TextView) itemView.findViewById(R.id.credits);
            add = (ImageView) itemView.findViewById(R.id.add);
            PlayerView = (CardView) itemView.findViewById(R.id.player_view);
            xTvPlayerType=(TextView)itemView.findViewById(R.id.xTvPlayerType);


        }
    }
}