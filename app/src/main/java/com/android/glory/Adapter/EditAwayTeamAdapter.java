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
import androidx.recyclerview.widget.RecyclerView;

import com.android.glory.Model.EditPlayersListModel.EditPlayerAwayTeam;
import com.android.glory.Model.EditPlayersListModel.EditPlayerHomeTeam;
import com.android.glory.R;
import com.android.glory.Utilites.StaticUtils;
import com.bumptech.glide.Glide;

import java.util.List;

import static com.android.glory.Utilites.StaticUtils.Edit_FINAL_COUNT;
import static com.android.glory.Utilites.StaticUtils.Edit_HomeTeamcount;
import static com.android.glory.Utilites.StaticUtils.Edit_OppoTeamcount;
import static com.android.glory.Utilites.StaticUtils.Edit_FINAL_COUNT;
import static com.android.glory.Utilites.StaticUtils.Edit_HomeTeamcount;
import static com.android.glory.Utilites.StaticUtils.Edit_OppoTeamcount;

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
//        PlayerSelection playerSelection = new PlayerSelection(false);
//        mCallBack.onItemClick(String.valueOf(Edit_FINAL_COUNT),String.valueOf(Edit_HomeTeamcount),String.valueOf(Edit_OppoTeamcount));

        EditPlayerAwayTeam playerDetails = playerDetailsList.get(position);
        holder.playerName.setText(playerDetails.getPlayer().getFirstName());
        holder.credits.setText(playerDetails.getCredits());

        if (Integer.valueOf(awayTeamCount)==count){
            Log.e("testing","count "+count);
            Log.e("testing","awayTeamCount "+awayTeamCount);
        }else {
            mCallBack.onItemClick(String.valueOf(Edit_FINAL_COUNT), String.valueOf(Edit_HomeTeamcount), String.valueOf(awayTeamCount),StaticUtils.EditCREDITS5);
            if (playerDetails.getIsselected() == 1) {

                if (packageName==1){
                    Log.e("testing","awaycount "+count);
                    Log.e("testing","awayTeamCount "+awayTeamCount);

                    count++;
                    Edit_FINAL_COUNT=5;
                    Log.e("testing","Edit_OppoTeamcount "+Edit_OppoTeamcount);

                    Edit_OppoTeamcount=Integer.valueOf(awayTeamCount);
                    Log.e("testing","Edit_OppoTeamcount "+Edit_OppoTeamcount);

                    holder.points.setTextColor(context.getResources().getColor(R.color.greencolor));
                    holder.playerName.setTextColor(context.getResources().getColor(R.color.greencolor));
                    holder.credits.setTextColor(context.getResources().getColor(R.color.greencolor));
                    holder.add.setImageResource(R.drawable.ic_minus);
//                    StaticUtils.EditCREDITS5=StaticUtils.EditCREDITS5+Double.parseDouble(playerDetails.getCredits());

//                    mCallBack.onItemClick(String.valueOf(Edit_FINAL_COUNT), String.valueOf(Edit_HomeTeamcount), String.valueOf(awayTeamCount),StaticUtils.EditCREDITS5);


                }else {


                    count++;
                    Edit_FINAL_COUNT=7;
                    Log.e("testing","Edit_OppoTeamcount "+Edit_OppoTeamcount);

                    Edit_OppoTeamcount=Integer.valueOf(awayTeamCount);
                    Log.e("testing","Edit_OppoTeamcount "+Edit_OppoTeamcount);

                    holder.points.setTextColor(context.getResources().getColor(R.color.greencolor));
                    holder.playerName.setTextColor(context.getResources().getColor(R.color.greencolor));
                    holder.credits.setTextColor(context.getResources().getColor(R.color.greencolor));
                    holder.add.setImageResource(R.drawable.ic_minus);
//                    StaticUtils.EditCREDITS7=StaticUtils.EditCREDITS7-Double.parseDouble(playerDetails.getCredits());

//                    mCallBack.onItemClick(String.valueOf(Edit_FINAL_COUNT), String.valueOf(Edit_HomeTeamcount), String.valueOf(awayTeamCount),StaticUtils.EditCREDITS7);
                }


            }

        }


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

        holder.PlayerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (playerDetails.getIsselected()==1){
                    gotoCheck();

                }else {
                    if(packageName==1){

                        if(StaticUtils.EditCREDITS5-Double.parseDouble(playerDetails.getCredits())<0){

                            if (Edit_FINAL_COUNT == 5) {
                                Toast.makeText(context, "Maximum 5 Players you can select", Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(context, "Credit Points Left lessthan the Selected Player Credits", Toast.LENGTH_LONG).show();

                            }
                        }else {
                            gotoCheck();
                        }
                    }else {
                        if(StaticUtils.EditCREDITS7-Double.parseDouble(playerDetails.getCredits())<0){
                            if (Edit_FINAL_COUNT == 7) {
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
                    if (Edit_FINAL_COUNT == 5) {
                        if (playerDetails.getIsselected()==1) {
//                            playerSelection.setSelected(false);
                            holder.add.setImageResource(R.drawable.ic_plus);
                            Edit_FINAL_COUNT--;
                            holder.points.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                            holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                            holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                            StaticUtils.EditCREDITS5=StaticUtils.EditCREDITS5+Double.parseDouble(playerDetails.getCredits());

                            playerDetails.setIsselected(0);
                            Edit_OppoTeamcount--;

                            mCallBack.onItemClick(String.valueOf(Edit_FINAL_COUNT), String.valueOf(Edit_HomeTeamcount), String.valueOf(Edit_OppoTeamcount), StaticUtils.EditCREDITS5);

//                        StaticUtils.opposePlayers.add(new OpposPlayerSelected(playerDetails.getPlayer().getFullName(), "https://www.cricket.com.au/-/media/Players/Men/International/Bangladesh/Tamim-Iqbal-CWC19.ashx", playerDetails.getPlayer().getId(),false));

//                        opposPlayerSelected.setOppositePlayer(-1);
                        } else {
                            Toast.makeText(context, "Maximum 5 Players you can select", Toast.LENGTH_LONG).show();
//                        playerSelection.setSelected(false);
//                        holder.add.setImageResource(R.drawable.ic_minus);
//                        StaticUtils.Edit_HomeTeamcount--;
                        }
                    } else {
                        if (StaticUtils.Edit_HomeTeamcount == 3) {

                            if (playerDetails.getIsselected()==1) {

//                                playerSelection.setSelected(false);
                                holder.add.setImageResource(R.drawable.ic_plus);
                                Edit_FINAL_COUNT--;
                                holder.points.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                Edit_OppoTeamcount--;
                                StaticUtils.EditCREDITS5=StaticUtils.EditCREDITS5+Double.parseDouble(playerDetails.getCredits());

                                playerDetails.setIsselected(0);
                                mCallBack.onItemClick(String.valueOf(Edit_FINAL_COUNT), String.valueOf(Edit_HomeTeamcount), String.valueOf(Edit_OppoTeamcount), StaticUtils.EditCREDITS5);
                                Log.e("testing","awayTeamCount "+Edit_OppoTeamcount);
//                            StaticUtils.opposePlayers.add(new OpposPlayerSelected(playerDetails.getPlayer().getFullName(), "https://www.cricket.com.au/-/media/Players/Men/International/Bangladesh/Tamim-Iqbal-CWC19.ashx", playerDetails.getPlayer().getId(),false));


                            } else {
                                if (StaticUtils.Edit_OppoTeamcount < 3) {
                                    playerDetails.setIsselected(1);
                                    StaticUtils.Edit_OppoTeamcount++;
                                    Edit_FINAL_COUNT++;
                                    StaticUtils.EditCREDITS5=StaticUtils.EditCREDITS5-Double.parseDouble(playerDetails.getCredits());

                                    holder.points.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.credits.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.add.setImageResource(R.drawable.ic_minus);
                                    mCallBack.onItemClick(String.valueOf(Edit_FINAL_COUNT), String.valueOf(Edit_HomeTeamcount), String.valueOf(Edit_OppoTeamcount), StaticUtils.EditCREDITS5);

//                                StaticUtils.opposePlayers.add(new OpposPlayerSelected(playerDetails.getPlayer().getFullName(), "https://www.cricket.com.au/-/media/Players/Men/International/Bangladesh/Tamim-Iqbal-CWC19.ashx", playerDetails.getPlayer().getId(),true));

                                } else {
                                    Toast.makeText(context, "Maximum 5 Players you can select", Toast.LENGTH_LONG).show();

                                }


                            }
                        } else {
                            if (Edit_OppoTeamcount == 3) {
                                if (playerDetails.getIsselected()==1) {
                                    playerDetails.setIsselected(0);
                                    holder.add.setImageResource(R.drawable.ic_plus);
                                    Edit_FINAL_COUNT--;
                                    StaticUtils.EditCREDITS5=StaticUtils.EditCREDITS5+Double.parseDouble(playerDetails.getCredits());

                                    holder.points.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
//                                StaticUtils.opposePlayers.add(new OpposPlayerSelected(playerDetails.getPlayer().getFullName(), "https://www.cricket.com.au/-/media/Players/Men/International/Bangladesh/Tamim-Iqbal-CWC19.ashx", playerDetails.getPlayer().getId(),false));
                                    Edit_OppoTeamcount--;
                                    mCallBack.onItemClick(String.valueOf(Edit_FINAL_COUNT), String.valueOf(Edit_HomeTeamcount), String.valueOf(Edit_OppoTeamcount), StaticUtils.EditCREDITS5);

                                } else {
                                    Toast.makeText(context, "Maximum 3 Players you can select From one Team", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                if (playerDetails.getIsselected()==1) {
                                    playerDetails.setIsselected(0);
                                    holder.add.setImageResource(R.drawable.ic_plus);
                                    Edit_FINAL_COUNT--;
                                    StaticUtils.EditCREDITS5=StaticUtils.EditCREDITS5+Double.parseDouble(playerDetails.getCredits());

                                    holder.points.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
//                                StaticUtils.opposePlayers.add(new OpposPlayerSelected(playerDetails.getPlayer().getFullName(), "https://www.cricket.com.au/-/media/Players/Men/International/Bangladesh/Tamim-Iqbal-CWC19.ashx", playerDetails.getPlayer().getId(),false));
                                    Edit_OppoTeamcount--;
                                    mCallBack.onItemClick(String.valueOf(Edit_FINAL_COUNT), String.valueOf(Edit_HomeTeamcount), String.valueOf(Edit_OppoTeamcount), StaticUtils.EditCREDITS5);

                                } else {
                                    playerDetails.setIsselected(1);
                                    StaticUtils.Edit_OppoTeamcount++;
                                    Edit_FINAL_COUNT++;
                                    StaticUtils.EditCREDITS5=StaticUtils.EditCREDITS5-Double.parseDouble(playerDetails.getCredits());

                                    holder.points.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.credits.setTextColor(context.getResources().getColor(R.color.greencolor));
//                                StaticUtils.opposePlayers.add(new OpposPlayerSelected(playerDetails.getPlayer().getFullName(), "https://www.cricket.com.au/-/media/Players/Men/International/Bangladesh/Tamim-Iqbal-CWC19.ashx", playerDetails.getPlayer().getId(),true));
                                    holder.add.setImageResource(R.drawable.ic_minus);
                                    mCallBack.onItemClick(String.valueOf(Edit_FINAL_COUNT), String.valueOf(Edit_HomeTeamcount), String.valueOf(Edit_OppoTeamcount), StaticUtils.EditCREDITS5);

                                }
                            }
                        }
                    }
                } else {


                    if (Edit_FINAL_COUNT == 7) {
                        if (playerDetails.getIsselected()==1) {
//                            playerSelection.setSelected(false);
                            holder.add.setImageResource(R.drawable.ic_plus);
                            Edit_FINAL_COUNT--;
                            holder.points.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                            holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                            holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                            playerDetails.setIsselected(0);
                            StaticUtils.EditCREDITS7=StaticUtils.EditCREDITS7+Double.parseDouble(playerDetails.getCredits());

//                        StaticUtils.opposePlayers.add(new OpposPlayerSelected(playerDetails.getPlayer().getFullName(), "https://www.cricket.com.au/-/media/Players/Men/International/Bangladesh/Tamim-Iqbal-CWC19.ashx", playerDetails.getPlayer().getId(),false));

//                        opposPlayerSelected.setOppositePlayer(-1);
                            Edit_OppoTeamcount--;
                            mCallBack.onItemClick(String.valueOf(Edit_FINAL_COUNT), String.valueOf(Edit_HomeTeamcount), String.valueOf(Edit_OppoTeamcount), StaticUtils.EditCREDITS7);

                        } else {
                            Toast.makeText(context, "Maximum 7 Players you can select", Toast.LENGTH_LONG).show();
//                        playerSelection.setSelected(false);
//                        holder.add.setImageResource(R.drawable.ic_minus);
//                        StaticUtils.Edit_HomeTeamcount--;
                        }
                    } else {
                        if (StaticUtils.Edit_HomeTeamcount == 4) {

                            if (playerDetails.getIsselected()==1) {

//                                playerSelection.setSelected(false);
                                holder.add.setImageResource(R.drawable.ic_plus);
                                Edit_FINAL_COUNT--;
                                holder.points.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                Edit_OppoTeamcount--;
                                StaticUtils.EditCREDITS7=StaticUtils.EditCREDITS7+Double.parseDouble(playerDetails.getCredits());

                                playerDetails.setIsselected(0);
                                mCallBack.onItemClick(String.valueOf(Edit_FINAL_COUNT), String.valueOf(Edit_HomeTeamcount), String.valueOf(Edit_OppoTeamcount), StaticUtils.EditCREDITS7);

//                            StaticUtils.opposePlayers.add(new OpposPlayerSelected(playerDetails.getPlayer().getFullName(), "https://www.cricket.com.au/-/media/Players/Men/International/Bangladesh/Tamim-Iqbal-CWC19.ashx", playerDetails.getPlayer().getId(),false));


                            } else {
                                if (StaticUtils.Edit_OppoTeamcount < 4) {
                                    playerDetails.setIsselected(1);
                                    StaticUtils.Edit_OppoTeamcount++;
                                    Edit_FINAL_COUNT++;
                                    StaticUtils.EditCREDITS7=StaticUtils.EditCREDITS7-Double.parseDouble(playerDetails.getCredits());

                                    holder.points.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.credits.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.add.setImageResource(R.drawable.ic_minus);
                                    mCallBack.onItemClick(String.valueOf(Edit_FINAL_COUNT), String.valueOf(Edit_HomeTeamcount), String.valueOf(Edit_OppoTeamcount), StaticUtils.EditCREDITS7);

//                                StaticUtils.opposePlayers.add(new OpposPlayerSelected(playerDetails.getPlayer().getFullName(), "https://www.cricket.com.au/-/media/Players/Men/International/Bangladesh/Tamim-Iqbal-CWC19.ashx", playerDetails.getPlayer().getId(),true));

                                } else {
                                    Toast.makeText(context, "Maximum 5 Players you can select", Toast.LENGTH_LONG).show();

                                }


                            }
                        } else {
                            if (Edit_OppoTeamcount == 4) {
                                if (playerDetails.getIsselected()==1) {
                                    playerDetails.setIsselected(0);
                                    holder.add.setImageResource(R.drawable.ic_plus);
                                    Edit_FINAL_COUNT--;
                                    StaticUtils.EditCREDITS7=StaticUtils.EditCREDITS7+Double.parseDouble(playerDetails.getCredits());

                                    holder.points.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
//                                StaticUtils.opposePlayers.add(new OpposPlayerSelected(playerDetails.getPlayer().getFullName(), "https://www.cricket.com.au/-/media/Players/Men/International/Bangladesh/Tamim-Iqbal-CWC19.ashx", playerDetails.getPlayer().getId(),false));
                                    Edit_OppoTeamcount--;
                                    mCallBack.onItemClick(String.valueOf(Edit_FINAL_COUNT), String.valueOf(Edit_HomeTeamcount), String.valueOf(Edit_OppoTeamcount), StaticUtils.EditCREDITS7);

                                } else {
                                    Toast.makeText(context, "Maximum 4 Players you can select From one Team", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                if (playerDetails.getIsselected()==1) {
                                    playerDetails.setIsselected(0);
                                    holder.add.setImageResource(R.drawable.ic_plus);
                                    Edit_FINAL_COUNT--;
                                    StaticUtils.EditCREDITS7=StaticUtils.EditCREDITS7+Double.parseDouble(playerDetails.getCredits());

                                    holder.points.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                                    holder.credits.setTextColor(context.getResources().getColor(R.color.colorPrimary));
//                                StaticUtils.opposePlayers.add(new OpposPlayerSelected(playerDetails.getPlayer().getFullName(), "https://www.cricket.com.au/-/media/Players/Men/International/Bangladesh/Tamim-Iqbal-CWC19.ashx", playerDetails.getPlayer().getId(),false));
                                    Edit_OppoTeamcount--;
                                    mCallBack.onItemClick(String.valueOf(Edit_FINAL_COUNT), String.valueOf(Edit_HomeTeamcount), String.valueOf(Edit_OppoTeamcount), StaticUtils.EditCREDITS7);

                                } else {
                                    playerDetails.setIsselected(1);

                                    StaticUtils.Edit_OppoTeamcount++;
                                    Edit_FINAL_COUNT++;
                                    StaticUtils.EditCREDITS7=StaticUtils.EditCREDITS7-Double.parseDouble(playerDetails.getCredits());

                                    holder.points.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.playerName.setTextColor(context.getResources().getColor(R.color.greencolor));
                                    holder.credits.setTextColor(context.getResources().getColor(R.color.greencolor));
//                                StaticUtils.opposePlayers.add(new OpposPlayerSelected(playerDetails.getPlayer().getFullName(), "https://www.cricket.com.au/-/media/Players/Men/International/Bangladesh/Tamim-Iqbal-CWC19.ashx", playerDetails.getPlayer().getId(),true));
                                    holder.add.setImageResource(R.drawable.ic_minus);
                                    mCallBack.onItemClick(String.valueOf(Edit_FINAL_COUNT), String.valueOf(Edit_HomeTeamcount), String.valueOf(Edit_OppoTeamcount), StaticUtils.EditCREDITS7);

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

        }
    }
}