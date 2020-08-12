package com.glory.apk.Adapter;

import android.content.Context;
import android.net.Uri;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.glory.apk.Model.MyMatches.Datum;
import com.bumptech.glide.Glide;
import com.glory.apk.R;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Adapter_MyMatches extends RecyclerView.Adapter<Adapter_MyMatches.FilterViewHolder> {

    private Context mCtx;
    //we are storing all the products in a list
    private List<Datum> courses_offered_list;
    private Adapter_MyMatches.OnItemClickcourses mCallback1;
    String qty;
    String sub_category_id;
    Boolean startSec = true;


    public Adapter_MyMatches(Context activity, List<Datum> data, Adapter_MyMatches.OnItemClickcourses mCallback1) {
        this.mCtx = activity;
        this.courses_offered_list = data;
        this.mCallback1 = mCallback1;
    }

    public interface OnItemClickcourses {
        void OnItemClickcourses(int pos);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public Adapter_MyMatches.FilterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.item_update_matches, parent, false);
        return new Adapter_MyMatches.FilterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Adapter_MyMatches.FilterViewHolder holder, final int position) {


        final Datum follow = courses_offered_list.get(position);


        if (follow.getRecord_entry_status().equals("active")) {
            holder.xRelayGray.setVisibility(View.GONE);

        } else {
            holder.xRelayGray.setVisibility(View.VISIBLE);

        }


        if (!follow.getSeason_name().equals(null)) {
//            holder.xTvTitle.setText(follow.getSeries().getName()+follow.getName());
            holder.xTvTitle.setText(follow.getSeason_name());

        } else {

        }

        if (!follow.getCmsMatchAssociatedType().equals(null)) {
//            holder.xTvTitle.setText(follow.getSeries().getName()+follow.getName());

            if (follow.getCmsMatchAssociatedType().equalsIgnoreCase("t10")){
                holder.xTvMatchType.setText("T10");

            }else if (follow.getCmsMatchAssociatedType().equalsIgnoreCase("t20")){
                holder.xTvMatchType.setText("T20");

            }else if (follow.getCmsMatchAssociatedType().equalsIgnoreCase("test")){
                holder.xTvMatchType.setText("TEST");

            }else if (follow.getCmsMatchAssociatedType().equalsIgnoreCase("one-day")){
                holder.xTvMatchType.setText("ODI");

            }else {
                holder.xTvMatchType.setVisibility(View.INVISIBLE);
            }

        } else {
            holder.xTvMatchType.setVisibility(View.INVISIBLE);
        }

        if (follow.getHometeam().getShortName() == (null)) {
            holder.xTvHomeTeam.setText("");
            holder.xTvOppositeTeamName.setText("");

        } else {
            holder.xTvHomeTeam.setText(follow.getHometeam().getShortName());
            holder.xTvOppositeTeamName.setText(follow.getAwayteam().getShortName());

        }

//        if (follow.getCurrentMatchState().equals("play_suspended_unknown")) {
//            holder.card_view1.getBackground().setAlpha(100);
//
//        } else {
//            holder.card_view1.getBackground().setAlpha(225);
//
//        }

        if (follow.getDatetime().getTime_left() != null) {

            if (position == 0) {
                if (startSec == true) {
                    startSec = false;
                    CountDownTimer countDownTimer;

                    countDownTimer = new CountDownTimer(Integer.parseInt(follow.getDatetime().getSeconds()) * 1000, 1000) {
                        public void onTick(long millisUntilFinished) {
                            long millis = millisUntilFinished;
                            //Convert milliseconds into hour,minute and seconds
                            String hms = String.format("%02dh %02dm %02ds left", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                            holder.xTvTime.setText(hms);//set text
                        }

                        public void onFinish() {

//                        countdownTimerText.setText("TIME'S UP!!"); //On finish change timer text
//                        countDownTimer = null;//set CountDownTimer to null
//                        startTimer.setText(getString(R.string.start_timer));//Change button text
                        }
                    }.start();
                } else {

                }


            } else
                holder.xTvTime.setText(follow.getDatetime().getTime_left() + " left");

        }


//        String erfgerfg = follow.getHometeam().getLogoUrl();
        if (follow.getHometeam().getLogoUrl() == null || follow.getHometeam().getLogoUrl().length() == 0) {
            Glide.with(mCtx)
                    .load(Uri.parse(String.valueOf(R.drawable.default_team_logo))
                    )
                    .error(R.drawable.default_team_logo)
                    .into(holder.xIvHomeTeam);
        } else {

            Glide.with(mCtx)
                    .load(Uri.parse(follow.getHometeam().getLogoUrl()))
                    .error(R.drawable.default_team_logo)
                    .into(holder.xIvHomeTeam);
        }
        if (follow.getAwayteam().getLogoUrl() == null || follow.getAwayteam().getLogoUrl().length() == 0) {
            Glide.with(mCtx)
                    .load(Uri.parse(String.valueOf(R.drawable.default_team_logo)))
                    .error(R.drawable.default_team_logo)
                    .into(holder.xIvOppsiteTeam);

        } else {

            Glide.with(mCtx)
                    .load(Uri.parse(follow.getAwayteam().getLogoUrl()))
                    .error(R.drawable.default_team_logo)
                    .into(holder.xIvOppsiteTeam);
        }

        holder.card_view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (follow.getRecord_entry_status().equals("active")) {
                    if (mCallback1 != null) {
                        mCallback1.OnItemClickcourses(position);

                    }
                }


            }
        });


    }

    @Override
    public int getItemCount() {
        return courses_offered_list.size();
    }

    public class FilterViewHolder extends RecyclerView.ViewHolder {

        TextView xTvTitle;
        TextView xTvHomeTeam, xTvOppositeTeamName, xTvTime,xTvMatchType;
        ImageView xIvHomeTeam, xIvOppsiteTeam;
        RelativeLayout xRelayGray;
        CardView card_view1;

        public FilterViewHolder(View itemView) {
            super(itemView);
            xTvTitle = itemView.findViewById(R.id.xTvTitle);
            xTvHomeTeam = itemView.findViewById(R.id.xTvHomeTeam);
            xTvOppositeTeamName = itemView.findViewById(R.id.xTvOppositeTeamName);
            xTvTime = itemView.findViewById(R.id.xTvTime);
            card_view1 = itemView.findViewById(R.id.card_view1);

            xIvHomeTeam = itemView.findViewById(R.id.xIvHomeTeam);
            xIvOppsiteTeam = itemView.findViewById(R.id.xIvOppsiteTeam);
            xRelayGray = itemView.findViewById(R.id.xRelayGray);
            xTvMatchType=itemView.findViewById(R.id.xTvMatchType);

        }
    }
}
