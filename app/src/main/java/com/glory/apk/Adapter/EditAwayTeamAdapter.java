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

import com.glory.apk.Model.EditPlayersListModel.EditPlayerAwayTeam;
import com.glory.apk.R;
import com.glory.apk.Utilites.StaticUtils;
import com.bumptech.glide.Glide;

import java.util.List;

public class EditAwayTeamAdapter extends RecyclerView.Adapter<EditAwayTeamAdapter.PlayerViewHolder> {
    private Context context;
    private List<EditPlayerAwayTeam> playerDetailsList;
    int packageName;
    private EditAwayTeamAdapter.onItemClick mCallBack;
    String awayTeamCount;
    int count;



    public EditAwayTeamAdapter(Context activity, List<EditPlayerAwayTeam> playerDetailsList, int packageName, onItemClick mCallBack, String awayTeamCount,int count) {
        this.context = activity;
        this.playerDetailsList = playerDetailsList;
        this.packageName=packageName;
        this.mCallBack=mCallBack;
        this.awayTeamCount=awayTeamCount;
        this.count=count;
    }

    @NonNull
    @Override
    public EditAwayTeamAdapter.PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_team, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final EditAwayTeamAdapter.PlayerViewHolder holder, int position) {

        EditPlayerAwayTeam playerDetails = playerDetailsList.get(position);
        holder.playerName.setText(playerDetails.getPlayer().getFullName());
        holder.credits.setText(playerDetails.getCredits());

        if (playerDetails.getPlayer().getPlayerType() != null) {
            holder.xTvPlayerType.setVisibility(View.VISIBLE);

            if (playerDetails.getPlayer().getPlayerType().toString().equalsIgnoreCase("Batsman")) {
                holder.xTvPlayerType.setText("(BAT)");
            } else if (playerDetails.getPlayer().getPlayerType().toString().equalsIgnoreCase("Bowler")) {
                holder.xTvPlayerType.setText("(BWL)");

            } else if (playerDetails.getPlayer().getPlayerType().toString().equalsIgnoreCase("Allrounder")) {
                holder.xTvPlayerType.setText("(ALL)");

            } else if (playerDetails.getPlayer().getPlayerType().toString().equalsIgnoreCase("Wicketkeeper")) {
                holder.xTvPlayerType.setText("(WK)");
            } else {
                holder.xTvPlayerType.setVisibility(View.GONE);
            }
        } else {
            holder.xTvPlayerType.setVisibility(View.GONE);
        }

        if (Integer.valueOf(awayTeamCount)==count){
            Log.e("testing","count "+count);
            Log.e("testing","awayTeamCount "+awayTeamCount);
        }else {
            if (playerDetails.getIsselected() == 1) {

                if (packageName==1){
                    Log.e("testing","awaycount "+count);
                    Log.e("testing","awayTeamCount "+awayTeamCount);

                    count++;
                    Log.e("testing","Edit_OppoTeamcount "+ StaticUtils.Edit_OppoTeamcount);

                    Log.e("testing","Edit_OppoTeamcount "+ StaticUtils.Edit_OppoTeamcount);

//                    holder.points.setTextColor(context.getResources().getColor(R.color.greencolor));
                    holder.playerName.setTextColor(context.getResources().getColor(R.color.greencolor));
                    holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.greencolor));

                    holder.credits.setTextColor(context.getResources().getColor(R.color.greencolor));
                    holder.add.setImageResource(R.drawable.ic_minus);



                }else {


                    count++;
                    StaticUtils.Edit_FINAL_COUNT=7;
                    Log.e("testing","Edit_OppoTeamcount "+ StaticUtils.Edit_OppoTeamcount);

                    StaticUtils.Edit_OppoTeamcount=Integer.valueOf(awayTeamCount);
                    Log.e("testing","Edit_OppoTeamcount "+ StaticUtils.Edit_OppoTeamcount);

//                    holder.points.setTextColor(context.getResources().getColor(R.color.greencolor));
                    holder.playerName.setTextColor(context.getResources().getColor(R.color.greencolor));
                    holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.greencolor));

                    holder.credits.setTextColor(context.getResources().getColor(R.color.greencolor));
                    holder.add.setImageResource(R.drawable.ic_minus);

                }


            }

        }


        if (playerDetails.getPlayer().getImageURL() == null || playerDetails.getPlayer().getImageURL().toString().length() == 0) {
            Glide.with(context)
                    .load(Uri.parse(String.valueOf(R.drawable.user)))
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

        holder.PlayerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (playerDetails.getIsselected()==1){
                    gotoCheck();

                }else {
                    if(packageName==1){

                        if(StaticUtils.EditCREDITS5-Double.parseDouble(playerDetails.getCredits())<0){

                            if (StaticUtils.Edit_FINAL_COUNT == 5) {
                                Toast.makeText(context, "Maximum 5 Players you can select", Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(context, "Credit Points Left lessthan the Selected Player Credits", Toast.LENGTH_LONG).show();

                            }
                        }else {
                            gotoCheck();
                        }
                    }else {
                        if(StaticUtils.EditCREDITS7-Double.parseDouble(playerDetails.getCredits())<0){
                            if (StaticUtils.Edit_FINAL_COUNT == 7) {
                                Toast.makeText(context, "Maximum 7 Players you can select", Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(context, "Credit Points Left lessthan the Selected Player Credits", Toast.LENGTH_LONG).show();

                            }
                        }else {
                            gotoCheck();

                        }
                    }
                }





            }

            private void gotoCheck() {

                if (packageName==1) {
                    if (StaticUtils.Edit_FINAL_COUNT == 5) {
                        if (playerDetails.getIsselected()==1) {
                            holder.add.setImageResource(R.drawable.ic_plus);
                            StaticUtils.Edit_FINAL_COUNT--;
//                            holder.points.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                            holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                            holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.colorPrimary));

                            holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                            StaticUtils.EditCREDITS5=StaticUtils.EditCREDITS5+Double.parseDouble(playerDetails.getCredits());

                            playerDetails.setIsselected(0);
                            StaticUtils.Edit_OppoTeamcount--;

                            mCallBack.onItemClick(String.valueOf(StaticUtils.Edit_FINAL_COUNT), String.valueOf(StaticUtils.Edit_HomeTeamcount), String.valueOf(StaticUtils.Edit_OppoTeamcount), StaticUtils.EditCREDITS5);


                        } else {
                            Toast.makeText(context, "Maximum 5 Players you can select", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        if (StaticUtils.Edit_HomeTeamcount == 3) {

                            if (playerDetails.getIsselected()==1) {

                                holder.add.setImageResource(R.drawable.ic_plus);
                                StaticUtils.Edit_FINAL_COUNT--;
//                                holder.points.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                StaticUtils.Edit_OppoTeamcount--;
                                StaticUtils.EditCREDITS5=StaticUtils.EditCREDITS5+Double.parseDouble(playerDetails.getCredits());

                                playerDetails.setIsselected(0);
                                mCallBack.onItemClick(String.valueOf(StaticUtils.Edit_FINAL_COUNT), String.valueOf(StaticUtils.Edit_HomeTeamcount), String.valueOf(StaticUtils.Edit_OppoTeamcount), StaticUtils.EditCREDITS5);
                                Log.e("testing","awayTeamCount "+ StaticUtils.Edit_OppoTeamcount);


                            } else {
                                if (StaticUtils.Edit_OppoTeamcount < 3) {
                                    playerDetails.setIsselected(1);
                                    StaticUtils.Edit_OppoTeamcount++;
                                    StaticUtils.Edit_FINAL_COUNT++;
                                    StaticUtils.EditCREDITS5=StaticUtils.EditCREDITS5-Double.parseDouble(playerDetails.getCredits());

//                                    holder.points.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.greencolor));

                                    holder.credits.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.add.setImageResource(R.drawable.ic_minus);
                                    mCallBack.onItemClick(String.valueOf(StaticUtils.Edit_FINAL_COUNT), String.valueOf(StaticUtils.Edit_HomeTeamcount), String.valueOf(StaticUtils.Edit_OppoTeamcount), StaticUtils.EditCREDITS5);


                                } else {
                                    Toast.makeText(context, "Maximum 5 Players you can select", Toast.LENGTH_LONG).show();

                                }


                            }
                        } else {
                            if (StaticUtils.Edit_OppoTeamcount == 3) {
                                if (playerDetails.getIsselected()==1) {
                                    playerDetails.setIsselected(0);
                                    holder.add.setImageResource(R.drawable.ic_plus);
                                    StaticUtils.Edit_FINAL_COUNT--;
                                    StaticUtils.EditCREDITS5=StaticUtils.EditCREDITS5+Double.parseDouble(playerDetails.getCredits());

//                                    holder.points.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.colorPrimary));

                                    holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    StaticUtils.Edit_OppoTeamcount--;
                                    mCallBack.onItemClick(String.valueOf(StaticUtils.Edit_FINAL_COUNT), String.valueOf(StaticUtils.Edit_HomeTeamcount), String.valueOf(StaticUtils.Edit_OppoTeamcount), StaticUtils.EditCREDITS5);

                                } else {
                                    Toast.makeText(context, "Maximum 3 Players you can select From one Team", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                if (playerDetails.getIsselected()==1) {
                                    playerDetails.setIsselected(0);
                                    holder.add.setImageResource(R.drawable.ic_plus);
                                    StaticUtils.Edit_FINAL_COUNT--;
                                    StaticUtils.EditCREDITS5=StaticUtils.EditCREDITS5+Double.parseDouble(playerDetails.getCredits());

//                                    holder.points.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.colorPrimary));

                                    holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
//                                StaticUtils.opposePlayers.add(new OpposPlayerSelected(playerDetails.getPlayer().getFullName(), "https://www.cricket.com.au/-/media/Players/Men/International/Bangladesh/Tamim-Iqbal-CWC19.ashx", playerDetails.getPlayer().getId(),false));
                                    StaticUtils.Edit_OppoTeamcount--;
                                    mCallBack.onItemClick(String.valueOf(StaticUtils.Edit_FINAL_COUNT), String.valueOf(StaticUtils.Edit_HomeTeamcount), String.valueOf(StaticUtils.Edit_OppoTeamcount), StaticUtils.EditCREDITS5);

                                } else {
                                    playerDetails.setIsselected(1);
                                    StaticUtils.Edit_OppoTeamcount++;
                                    StaticUtils.Edit_FINAL_COUNT++;
                                    StaticUtils.EditCREDITS5=StaticUtils.EditCREDITS5-Double.parseDouble(playerDetails.getCredits());

//                                    holder.points.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.greencolor));

                                    holder.credits.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.add.setImageResource(R.drawable.ic_minus);
                                    mCallBack.onItemClick(String.valueOf(StaticUtils.Edit_FINAL_COUNT), String.valueOf(StaticUtils.Edit_HomeTeamcount), String.valueOf(StaticUtils.Edit_OppoTeamcount), StaticUtils.EditCREDITS5);

                                }
                            }
                        }
                    }
                } else {


                    if (StaticUtils.Edit_FINAL_COUNT == 7) {
                        if (playerDetails.getIsselected()==1) {
//                            playerSelection.setSelected(false);
                            holder.add.setImageResource(R.drawable.ic_plus);
                            StaticUtils.Edit_FINAL_COUNT--;
//                            holder.points.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                            holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                            holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.colorPrimary));

                            holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                            playerDetails.setIsselected(0);
                            StaticUtils.EditCREDITS7=StaticUtils.EditCREDITS7+Double.parseDouble(playerDetails.getCredits());
                            StaticUtils.Edit_OppoTeamcount--;
                            mCallBack.onItemClick(String.valueOf(StaticUtils.Edit_FINAL_COUNT), String.valueOf(StaticUtils.Edit_HomeTeamcount), String.valueOf(StaticUtils.Edit_OppoTeamcount), StaticUtils.EditCREDITS7);

                        } else {
                            Toast.makeText(context, "Maximum 7 Players you can select", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        if (StaticUtils.Edit_HomeTeamcount == 4) {

                            if (playerDetails.getIsselected()==1) {

//                                playerSelection.setSelected(false);
                                holder.add.setImageResource(R.drawable.ic_plus);
                                StaticUtils.Edit_FINAL_COUNT--;
//                                holder.points.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.colorPrimary));

                                holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                StaticUtils.Edit_OppoTeamcount--;
                                StaticUtils.EditCREDITS7=StaticUtils.EditCREDITS7+Double.parseDouble(playerDetails.getCredits());

                                playerDetails.setIsselected(0);
                                mCallBack.onItemClick(String.valueOf(StaticUtils.Edit_FINAL_COUNT), String.valueOf(StaticUtils.Edit_HomeTeamcount), String.valueOf(StaticUtils.Edit_OppoTeamcount), StaticUtils.EditCREDITS7);

                            } else {
                                if (StaticUtils.Edit_OppoTeamcount < 4) {
                                    playerDetails.setIsselected(1);
                                    StaticUtils.Edit_OppoTeamcount++;
                                    StaticUtils.Edit_FINAL_COUNT++;
                                    StaticUtils.EditCREDITS7=StaticUtils.EditCREDITS7-Double.parseDouble(playerDetails.getCredits());

//                                    holder.points.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.greencolor));

                                    holder.credits.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.add.setImageResource(R.drawable.ic_minus);
                                    mCallBack.onItemClick(String.valueOf(StaticUtils.Edit_FINAL_COUNT), String.valueOf(StaticUtils.Edit_HomeTeamcount), String.valueOf(StaticUtils.Edit_OppoTeamcount), StaticUtils.EditCREDITS7);


                                } else {
                                    Toast.makeText(context, "Maximum 5 Players you can select", Toast.LENGTH_LONG).show();

                                }


                            }
                        } else {
                            if (StaticUtils.Edit_OppoTeamcount == 4) {
                                if (playerDetails.getIsselected()==1) {
                                    playerDetails.setIsselected(0);
                                    holder.add.setImageResource(R.drawable.ic_plus);
                                    StaticUtils.Edit_FINAL_COUNT--;
                                    StaticUtils.EditCREDITS7=StaticUtils.EditCREDITS7+Double.parseDouble(playerDetails.getCredits());

//                                    holder.points.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.colorPrimary));

                                    holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    StaticUtils.Edit_OppoTeamcount--;
                                    mCallBack.onItemClick(String.valueOf(StaticUtils.Edit_FINAL_COUNT), String.valueOf(StaticUtils.Edit_HomeTeamcount), String.valueOf(StaticUtils.Edit_OppoTeamcount), StaticUtils.EditCREDITS7);

                                } else {
                                    Toast.makeText(context, "Maximum 4 Players you can select From one Team", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                if (playerDetails.getIsselected()==1) {
                                    playerDetails.setIsselected(0);
                                    holder.add.setImageResource(R.drawable.ic_plus);
                                    StaticUtils.Edit_FINAL_COUNT--;
                                    StaticUtils.EditCREDITS7=StaticUtils.EditCREDITS7+Double.parseDouble(playerDetails.getCredits());

//                                    holder.points.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.colorPrimary));

                                    holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    StaticUtils.Edit_OppoTeamcount--;
                                    mCallBack.onItemClick(String.valueOf(StaticUtils.Edit_FINAL_COUNT), String.valueOf(StaticUtils.Edit_HomeTeamcount), String.valueOf(StaticUtils.Edit_OppoTeamcount), StaticUtils.EditCREDITS7);

                                } else {
                                    playerDetails.setIsselected(1);

                                    StaticUtils.Edit_OppoTeamcount++;
                                    StaticUtils.Edit_FINAL_COUNT++;
                                    StaticUtils.EditCREDITS7=StaticUtils.EditCREDITS7-Double.parseDouble(playerDetails.getCredits());

//                                    holder.points.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.greencolor));

                                    holder.credits.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.add.setImageResource(R.drawable.ic_minus);
                                    mCallBack.onItemClick(String.valueOf(StaticUtils.Edit_FINAL_COUNT), String.valueOf(StaticUtils.Edit_HomeTeamcount), String.valueOf(StaticUtils.Edit_OppoTeamcount), StaticUtils.EditCREDITS7);

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
        private TextView playerName, points, credits,xTvPlayerType;
        private CardView PlayerView;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            playerImage = (ImageView) itemView.findViewById(R.id.player_img);
            playerName = (TextView) itemView.findViewById(R.id.player_name);
//            points = (TextView) itemView.findViewById(R.id.points);
            credits = (TextView) itemView.findViewById(R.id.credits);
            add = (ImageView) itemView.findViewById(R.id.add);
            PlayerView = (CardView) itemView.findViewById(R.id.player_view);
            xTvPlayerType = (TextView) itemView.findViewById(R.id.xTvPlayerType);

        }
    }
}