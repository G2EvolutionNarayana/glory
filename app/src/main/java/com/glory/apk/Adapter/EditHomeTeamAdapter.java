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

import com.glory.apk.Model.EditPlayersListModel.EditPlayerHomeTeam;
import com.glory.apk.R;
import com.glory.apk.Utilites.StaticUtils;
import com.bumptech.glide.Glide;

import java.util.List;

public class EditHomeTeamAdapter extends RecyclerView.Adapter<EditHomeTeamAdapter.PlayerViewHolder> {
    private Context context;
    private List<EditPlayerHomeTeam> playerDetailsList;
    int packageName;
    String homeTeam;
    private EditHomeTeamAdapter.onItemClick mCallBack;
    int count = 0;

//    public EditHomeTeamAdapter(Context context, List<EditPlayerHomeTeam> playerDetailsList, String packageName, HomeTeamAdapter.onItemClick mCallBack) {
//        this.context = context;
//        this.playerDetailsList = playerDetailsList;
//        this.packageName=packageName;
//        this.mCallBack=mCallBack;
//    }

    public EditHomeTeamAdapter(Context activity, List<EditPlayerHomeTeam> playerDetailsList, int packageName, onItemClick mCallBack, String homeTeam) {
        this.context = activity;
        this.playerDetailsList = playerDetailsList;
        this.packageName = packageName;
        this.mCallBack = mCallBack;
        this.homeTeam = homeTeam;
    }

    @NonNull
    @Override
    public EditHomeTeamAdapter.PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_team, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final EditHomeTeamAdapter.PlayerViewHolder holder, int position) {
//        PlayerSelection playerSelection = new PlayerSelection(false);
//        mCallBack.onItemClick(String.valueOf(FINAL_COUNT),String.valueOf(Edit_HomeTeamcount),String.valueOf(Edit_OppoTeamcount));

        EditPlayerHomeTeam playerDetails = playerDetailsList.get(position);
        holder.playerName.setText(playerDetails.getEditPlayerPlayer().getFullName());
        holder.credits.setText(playerDetails.getCredits());
        if (playerDetails.getEditPlayerPlayer().getPlayerType() != null) {
            holder.xTvPlayerType.setVisibility(View.VISIBLE);

            if (playerDetails.getEditPlayerPlayer().getPlayerType().toString().equalsIgnoreCase("Batsman")) {
                holder.xTvPlayerType.setText("(BAT)");
            } else if (playerDetails.getEditPlayerPlayer().getPlayerType().toString().equalsIgnoreCase("Bowler")) {
                holder.xTvPlayerType.setText("(BWL)");

            } else if (playerDetails.getEditPlayerPlayer().getPlayerType().toString().equalsIgnoreCase("Allrounder")) {
                holder.xTvPlayerType.setText("(ALL)");

            } else if (playerDetails.getEditPlayerPlayer().getPlayerType().toString().equalsIgnoreCase("Wicketkeeper")) {
                holder.xTvPlayerType.setText("(WK)");

            } else {
                holder.xTvPlayerType.setVisibility(View.GONE);
            }
        } else {
            holder.xTvPlayerType.setVisibility(View.GONE);

        }

//        mCallBack.onItemClick(String.valueOf(Edit_FINAL_COUNT), String.valueOf(Edit_HomeTeamcount), String.valueOf(Edit_OppoTeamcount), StaticUtils.EditCREDITS5);

        if (Integer.valueOf(homeTeam) == count) {
            Log.e("testing", "Homecount " + count);
        } else {

            if (playerDetails.getIsselected().toString().equals("1")) {

                if (packageName == 1) {
                    count++;
                    Log.e("testing", "Homecount " + count);

//                    Edit_FINAL_COUNT = 5;
//                    Edit_HomeTeamcount = Integer.valueOf(homeTeam);
//                    holder.points.setTextColor(context.getResources().getColor(R.color.greencolor));
                    holder.playerName.setTextColor(context.getResources().getColor(R.color.greencolor));
                    holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.greencolor));
                    holder.credits.setTextColor(context.getResources().getColor(R.color.greencolor));
                    holder.add.setImageResource(R.drawable.ic_minus);
//                    StaticUtils.EditCREDITS5=StaticUtils.EditCREDITS5+Double.parseDouble(playerDetails.getCredits());
//                    mCallBack.onItemClick(String.valueOf(Edit_FINAL_COUNT), String.valueOf(Edit_HomeTeamcount), String.valueOf(Edit_OppoTeamcount), StaticUtils.EditCREDITS5);

                } else {
                    count++;
//                    Edit_FINAL_COUNT = 7;
//                    Edit_HomeTeamcount = Integer.valueOf(homeTeam);
//                    holder.points.setTextColor(context.getResources().getColor(R.color.greencolor));
                    holder.playerName.setTextColor(context.getResources().getColor(R.color.greencolor));
                    holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.greencolor));

                    holder.credits.setTextColor(context.getResources().getColor(R.color.greencolor));
                    holder.add.setImageResource(R.drawable.ic_minus);
//                    StaticUtils.EditCREDITS7=StaticUtils.EditCREDITS7-Double.parseDouble(playerDetails.getCredits());

//                    mCallBack.onItemClick(String.valueOf(Edit_FINAL_COUNT), String.valueOf(Edit_HomeTeamcount), String.valueOf(Edit_OppoTeamcount),StaticUtils.EditCREDITS7);

                }
            }
        }


        if (playerDetails.getEditPlayerPlayer().getImageURL() == null || playerDetails.getEditPlayerPlayer().getImageURL().toString().length() == 0) {
            Glide.with(context)
                    .load(Uri.parse(String.valueOf(R.drawable.player)))
                    .error(R.drawable.player)
                    .into(holder.playerImage);
            Log.e("testing", "getImageUrl = " + "Null Image");
        } else {
            Glide.with(context)
                    .load(Uri.parse(playerDetails.getEditPlayerPlayer().getImageURL().toString()))
                    .error(R.drawable.player)
                    .into(holder.playerImage);
            Log.e("testing", "getImageUrl = " + "image");
        }

        holder.PlayerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (playerDetails.getIsselected() == 1) {
                    gotoCheck();

                } else {
                    if (packageName == 1) {

                        if (StaticUtils.EditCREDITS5 - Double.parseDouble(playerDetails.getCredits()) < 0) {

                            if (StaticUtils.Edit_FINAL_COUNT == 5) {
                                Toast.makeText(context, "Maximum 5 Players you can select", Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(context, "Credit Points Left lessthan the Selected Player Credits", Toast.LENGTH_LONG).show();

                            }

                        } else {
                            gotoCheck();
                        }

                    } else {


                        if (StaticUtils.EditCREDITS7 - Double.parseDouble(playerDetails.getCredits()) < 0) {
                            if (StaticUtils.Edit_FINAL_COUNT == 7) {
                                Toast.makeText(context, "Maximum 7 Players you can select", Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(context, "Credit Points Left lessthan the Selected Player Credits", Toast.LENGTH_LONG).show();

                            }
                        } else {
                            gotoCheck();

                        }


                    }
                }
            }

            private void gotoCheck() {

                if (packageName == 1) {
                    if (StaticUtils.Edit_FINAL_COUNT == 5) {
                        if (playerDetails.getIsselected() == 1) {
//                        playerSelection.setSelected(false);
                            holder.add.setImageResource(R.drawable.ic_plus);
                            StaticUtils.Edit_FINAL_COUNT--;
//                        StaticUtils.homePlayers.remove(position);
                            playerDetails.setIsselected(0);
//                            holder.points.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                            holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                            holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.colorPrimary));

                            holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                            StaticUtils.EditCREDITS5 = StaticUtils.EditCREDITS5 + Double.parseDouble(playerDetails.getCredits());
                            StaticUtils.Edit_HomeTeamcount--;
                            mCallBack.onItemClick(String.valueOf(StaticUtils.Edit_FINAL_COUNT), String.valueOf(StaticUtils.Edit_HomeTeamcount), String.valueOf(StaticUtils.Edit_OppoTeamcount), StaticUtils.EditCREDITS5);

                        } else {
                            Toast.makeText(context, "Maximum 5 Players you can select", Toast.LENGTH_LONG).show();
//                        playerSelection.setSelected(false);
//                        holder.add.setImageResource(R.drawable.ic_minus);
//                        StaticUtils.Edit_HomeTeamcount--;

                        }
                    } else {
                        if (StaticUtils.Edit_OppoTeamcount == 3) {

                            if (playerDetails.getIsselected() == 1) {
                                holder.add.setImageResource(R.drawable.ic_minus);
                                StaticUtils.Edit_FINAL_COUNT--;
                                playerDetails.setIsselected(0);

//                                holder.points.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.colorPrimary));

                                holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                StaticUtils.EditCREDITS5 = StaticUtils.EditCREDITS5 + Double.parseDouble(playerDetails.getCredits());

//                            StaticUtils.homePlayers.add(new HomePlayersSelected(playerDetails.getPlayer().getFullName(),playerDetails.getPlayer().getImageURL(),playerDetails.getPlayer().getId(),false));
                                StaticUtils.Edit_HomeTeamcount--;
                                mCallBack.onItemClick(String.valueOf(StaticUtils.Edit_FINAL_COUNT), String.valueOf(StaticUtils.Edit_HomeTeamcount), String.valueOf(StaticUtils.Edit_OppoTeamcount), StaticUtils.EditCREDITS5);

                            } else {
                                if (StaticUtils.Edit_HomeTeamcount < 3) {
                                    StaticUtils.Edit_HomeTeamcount++;
                                    StaticUtils.Edit_FINAL_COUNT++;
                                    playerDetails.setIsselected(1);
                                    StaticUtils.EditCREDITS5 = StaticUtils.EditCREDITS5 - Double.parseDouble(playerDetails.getCredits());

//                                    holder.points.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.greencolor));

                                    holder.credits.setTextColor(context.getResources().getColor(R.color.greencolor));

//                                StaticUtils.homePlayers.add(new HomePlayersSelected(playerDetails.getPlayer().getFullName(),playerDetails.getPlayer().getImageURL(),playerDetails.getPlayer().getId(),true));
                                    holder.add.setImageResource(R.drawable.ic_minus);
                                    mCallBack.onItemClick(String.valueOf(StaticUtils.Edit_FINAL_COUNT), String.valueOf(StaticUtils.Edit_HomeTeamcount), String.valueOf(StaticUtils.Edit_OppoTeamcount), StaticUtils.EditCREDITS5);

                                } else {
                                    Toast.makeText(context, "Maximum 5 Players you can select", Toast.LENGTH_LONG).show();
                                }
                            }
                        } else {
                            if (StaticUtils.Edit_HomeTeamcount == 3) {
                                if (playerDetails.getIsselected() == 1) {
                                    holder.add.setImageResource(R.drawable.ic_plus);
                                    StaticUtils.Edit_FINAL_COUNT--;
                                    //         StaticUtils.homePlayers.remove(position);
                                    StaticUtils.Edit_HomeTeamcount--;
                                    StaticUtils.EditCREDITS5 = StaticUtils.EditCREDITS5 + Double.parseDouble(playerDetails.getCredits());

//                                    holder.points.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.colorPrimary));

                                    holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    playerDetails.setIsselected(0);

                                    mCallBack.onItemClick(String.valueOf(StaticUtils.Edit_FINAL_COUNT), String.valueOf(StaticUtils.Edit_HomeTeamcount), String.valueOf(StaticUtils.Edit_OppoTeamcount), StaticUtils.EditCREDITS5);


//                                StaticUtils.homePlayers.add(new HomePlayersSelected(playerDetails.getPlayer().getFullName(),playerDetails.getPlayer().getImageURL(),playerDetails.getPlayer().getId(),false));

                                } else {
                                    Toast.makeText(context, "Maximum 3 Players you can select From one Team", Toast.LENGTH_LONG).show();

                                }
                            } else {
                                if (playerDetails.getIsselected() == 1) {
                                    holder.add.setImageResource(R.drawable.ic_plus);
                                    StaticUtils.Edit_FINAL_COUNT--;
                                    StaticUtils.EditCREDITS5 = StaticUtils.EditCREDITS5 + Double.parseDouble(playerDetails.getCredits());

//                                    holder.points.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.colorPrimary));

                                    holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    //         StaticUtils.homePlayers.remove(position);
                                    StaticUtils.Edit_HomeTeamcount--;
                                    playerDetails.setIsselected(0);
                                    mCallBack.onItemClick(String.valueOf(StaticUtils.Edit_FINAL_COUNT), String.valueOf(StaticUtils.Edit_HomeTeamcount), String.valueOf(StaticUtils.Edit_OppoTeamcount), StaticUtils.EditCREDITS5);


//                                StaticUtils.homePlayers.add(new HomePlayersSelected(playerDetails.getPlayer().getFullName(),playerDetails.getPlayer().getImageURL(),playerDetails.getPlayer().getId(),false));

                                } else {
                                    playerDetails.setIsselected(1);
                                    StaticUtils.Edit_HomeTeamcount++;
                                    StaticUtils.Edit_FINAL_COUNT++;
                                    StaticUtils.EditCREDITS5 = StaticUtils.EditCREDITS5 - Double.parseDouble(playerDetails.getCredits());

//                                StaticUtils.homePlayers.add(new HomePlayersSelected(playerDetails.getPlayer().getFullName(),playerDetails.getPlayer().getImageURL(),playerDetails.getPlayer().getId(),true));
                                    holder.add.setImageResource(R.drawable.ic_minus);
//                                    holder.points.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.greencolor));

                                    holder.credits.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    mCallBack.onItemClick(String.valueOf(StaticUtils.Edit_FINAL_COUNT), String.valueOf(StaticUtils.Edit_HomeTeamcount), String.valueOf(StaticUtils.Edit_OppoTeamcount), StaticUtils.EditCREDITS5);


                                }
                            }


                        }

                    }
                } else {

                    // if it is 7A
                    if (StaticUtils.Edit_FINAL_COUNT == 7) {
                        if (playerDetails.getIsselected() == 1) {
//                        playerSelection.setSelected(false);
                            holder.add.setImageResource(R.drawable.ic_plus);
                            StaticUtils.Edit_FINAL_COUNT--;
//                        StaticUtils.homePlayers.remove(position);
                            playerDetails.setIsselected(1);
                            StaticUtils.EditCREDITS7 = StaticUtils.EditCREDITS7 + Double.parseDouble(playerDetails.getCredits());

//                            holder.points.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                            holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                            holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.colorPrimary));

                            holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));

                            //       StaticUtils.homePlayers.add(new HomePlayersSelected(playerDetails.getPlayer().getFullName(),playerDetails.getPlayer().getImageURL(),playerDetails.getPlayer().getId(),false));
                            playerDetails.setIsselected(0);
                            mCallBack.onItemClick(String.valueOf(StaticUtils.Edit_FINAL_COUNT), String.valueOf(StaticUtils.Edit_HomeTeamcount), String.valueOf(StaticUtils.Edit_OppoTeamcount), StaticUtils.EditCREDITS7);

                        } else {
                            Toast.makeText(context, "Maximum 7 Players you can select", Toast.LENGTH_LONG).show();
//                        playerSelection.setSelected(false);
//                        holder.add.setImageResource(R.drawable.ic_minus);
//                        StaticUtils.Edit_HomeTeamcount--;
                        }
                    } else {
                        if (StaticUtils.Edit_OppoTeamcount == 4) {

                            if (playerDetails.getIsselected() == 1) {
                                holder.add.setImageResource(R.drawable.ic_plus);
                                StaticUtils.Edit_FINAL_COUNT--;
                                playerDetails.setIsselected(0);
//                                holder.points.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.colorPrimary));

                                holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                StaticUtils.EditCREDITS7 = StaticUtils.EditCREDITS7 + Double.parseDouble(playerDetails.getCredits());

//                            StaticUtils.homePlayers.add(new HomePlayersSelected(playerDetails.getPlayer().getFullName(),playerDetails.getPlayer().getImageURL(),playerDetails.getPlayer().getId(),false));
                                StaticUtils.Edit_HomeTeamcount--;
                                mCallBack.onItemClick(String.valueOf(StaticUtils.Edit_FINAL_COUNT), String.valueOf(StaticUtils.Edit_HomeTeamcount), String.valueOf(StaticUtils.Edit_OppoTeamcount), StaticUtils.EditCREDITS7);


                            } else {
                                if (StaticUtils.Edit_HomeTeamcount < 4) {
                                    StaticUtils.Edit_HomeTeamcount++;
                                    StaticUtils.Edit_FINAL_COUNT++;
                                    playerDetails.setIsselected(1);
//                                    holder.points.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.greencolor));

                                    holder.credits.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    StaticUtils.EditCREDITS7 = StaticUtils.EditCREDITS7 - Double.parseDouble(playerDetails.getCredits());

//                                StaticUtils.homePlayers.add(new HomePlayersSelected(playerDetails.getPlayer().getFullName(),playerDetails.getPlayer().getImageURL(),playerDetails.getPlayer().getId(),true));
                                    holder.add.setImageResource(R.drawable.ic_minus);
                                    mCallBack.onItemClick(String.valueOf(StaticUtils.Edit_FINAL_COUNT), String.valueOf(StaticUtils.Edit_HomeTeamcount), String.valueOf(StaticUtils.Edit_OppoTeamcount), StaticUtils.EditCREDITS7);

                                } else {
                                    Toast.makeText(context, "Maximum 7 Players you can select", Toast.LENGTH_LONG).show();
                                }
                            }
                        } else {
                            if (StaticUtils.Edit_HomeTeamcount == 4) {
                                if (playerDetails.getIsselected() == 1) {
                                    holder.add.setImageResource(R.drawable.ic_plus);
                                    StaticUtils.Edit_FINAL_COUNT--;
                                    //         StaticUtils.homePlayers.remove(position);
                                    StaticUtils.Edit_HomeTeamcount--;
                                    StaticUtils.EditCREDITS7 = StaticUtils.EditCREDITS7 + Double.parseDouble(playerDetails.getCredits());

//                                    holder.points.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.colorPrimary));

                                    holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    playerDetails.setIsselected(0);
                                    mCallBack.onItemClick(String.valueOf(StaticUtils.Edit_FINAL_COUNT), String.valueOf(StaticUtils.Edit_HomeTeamcount), String.valueOf(StaticUtils.Edit_OppoTeamcount), StaticUtils.EditCREDITS7);


//                                StaticUtils.homePlayers.add(new HomePlayersSelected(playerDetails.getPlayer().getFullName(),playerDetails.getPlayer().getImageURL(),playerDetails.getPlayer().getId(),false));

                                } else {
                                    Toast.makeText(context, "Maximum 4 Players you can select From one Team", Toast.LENGTH_LONG).show();

                                }
                            } else {
                                if (playerDetails.getIsselected() == 1) {
                                    holder.add.setImageResource(R.drawable.ic_plus);
                                    StaticUtils.Edit_FINAL_COUNT--;
                                    StaticUtils.EditCREDITS7 = StaticUtils.EditCREDITS7 + Double.parseDouble(playerDetails.getCredits());

//                                    holder.points.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.colorPrimary));

                                    holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    //         StaticUtils.homePlayers.remove(position);
                                    StaticUtils.Edit_HomeTeamcount--;
                                    playerDetails.setIsselected(0);
                                    mCallBack.onItemClick(String.valueOf(StaticUtils.Edit_FINAL_COUNT), String.valueOf(StaticUtils.Edit_HomeTeamcount), String.valueOf(StaticUtils.Edit_OppoTeamcount), StaticUtils.EditCREDITS7);


//                                StaticUtils.homePlayers.add(new HomePlayersSelected(playerDetails.getPlayer().getFullName(),playerDetails.getPlayer().getImageURL(),playerDetails.getPlayer().getId(),false));

                                } else {
                                    playerDetails.setIsselected(1);
                                    StaticUtils.Edit_HomeTeamcount++;
                                    StaticUtils.Edit_FINAL_COUNT++;
                                    StaticUtils.EditCREDITS7 = StaticUtils.EditCREDITS7 - Double.parseDouble(playerDetails.getCredits());

//                                StaticUtils.homePlayers.add(new HomePlayersSelected(playerDetails.getPlayer().getFullName(),playerDetails.getPlayer().getImageURL(),playerDetails.getPlayer().getId(),true));
                                    holder.add.setImageResource(R.drawable.ic_minus);
//                                    holder.points.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.xTvPlayerType.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.credits.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    mCallBack.onItemClick(String.valueOf(StaticUtils.Edit_FINAL_COUNT), String.valueOf(StaticUtils.Edit_HomeTeamcount), String.valueOf(StaticUtils.Edit_OppoTeamcount), StaticUtils.EditCREDITS7);


                                }
                            }


                        }

                    }
                }

            }
        });

    }

    public interface onItemClick {
        void onItemClick(String finalValue, String homeValue, String OppositeValue, Double credits);

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
        private TextView playerName, points, credits, xTvPlayerType;
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