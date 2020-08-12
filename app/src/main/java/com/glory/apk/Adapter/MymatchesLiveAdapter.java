package com.glory.apk.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.glory.apk.Model.LiveMyMatchesModel.LiveMyMatchesDatum;
import com.bumptech.glide.Glide;
import com.glory.apk.R;

import java.util.List;

public class MymatchesLiveAdapter extends RecyclerView.Adapter<MymatchesLiveAdapter.FilterViewHolder>{

    private Context mCtx;
    //we are storing all the products in a list
    private List<LiveMyMatchesDatum> courses_offered_list;
    private MymatchesLiveAdapter.OnItemClickcourses mCallback1;
    String qty;
    String sub_category_id;



    public MymatchesLiveAdapter(Context activity, List<LiveMyMatchesDatum> data, MymatchesLiveAdapter.OnItemClickcourses mCallback1) {
        this.mCtx = activity;
        this.courses_offered_list = data;
        this.mCallback1=mCallback1;
    }

    public interface OnItemClickcourses {
        void OnItemClickcourses(int pos);
    }
    @Override
    public MymatchesLiveAdapter.FilterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.item_update_matches, parent, false);
        return new FilterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MymatchesLiveAdapter.FilterViewHolder holder, final int position) {

        final LiveMyMatchesDatum follow = courses_offered_list.get(position);
        holder.xTvTime.setVisibility(View.INVISIBLE);

        if (follow.getName()!=null){
            holder.xTvTitle.setText(follow.getName());
        }
        holder.xTvHomeTeam.setText(follow.getLiveMyMatchesHometeam().getShortName());
        holder.xTvOppositeTeamName.setText(follow.getLiveMyMatchesAwayteam().getShortName());
        if (follow.getLiveMyMatchesDatetime().getTime_left()!= null) {
            holder.xTvTime.setText(follow.getLiveMyMatchesDatetime().getTime_left()+" left");

        }

        String erfgerfg=follow.getLiveMyMatchesHometeam().getLogoUrl();
        if (follow.getLiveMyMatchesHometeam().getLogoUrl() == null || follow.getLiveMyMatchesHometeam().getLogoUrl().length() == 0){
            Glide.with(mCtx)
                    .load(Uri.parse(String.valueOf(R.drawable.default_team_logo))
                    )
                    .error(R.drawable.default_team_logo)
                    .into(holder.xIvHomeTeam);
        }else{

            Glide.with(mCtx)
                    .load(Uri.parse(follow.getLiveMyMatchesHometeam().getLogoUrl()))
                    .error(R.drawable.default_team_logo)
                    .into(holder.xIvHomeTeam);
        }

        if (follow.getLiveMyMatchesAwayteam().getLogoUrl() == null || follow.getLiveMyMatchesAwayteam().getLogoUrl().length() == 0){
            Glide.with(mCtx)
                    .load(Uri.parse(String.valueOf(R.drawable.default_team_logo)))
                    .error(R.drawable.default_team_logo)
                    .into(holder.xIvOppsiteTeam);

        }else{

            Glide.with(mCtx)
                    .load(Uri.parse(follow.getLiveMyMatchesAwayteam().getLogoUrl()))
                    .error(R.drawable.default_team_logo)
                    .into(holder.xIvOppsiteTeam);
        }

        holder.card_view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mCallback1!=null){
                    mCallback1.OnItemClickcourses(position);
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
        TextView xTvHomeTeam, xTvOppositeTeamName, xTvTime;
        ImageView xIvHomeTeam, xIvOppsiteTeam;
        CardView card_view1;

        public FilterViewHolder(View itemView) {
            super(itemView);
            xTvTitle=itemView.findViewById(R.id.xTvTitle);
            xTvHomeTeam=itemView.findViewById(R.id.xTvHomeTeam);
            xTvOppositeTeamName=itemView.findViewById(R.id.xTvOppositeTeamName);
            xTvTime=itemView.findViewById(R.id.xTvTime);
            card_view1=itemView.findViewById(R.id.card_view1);

            xIvHomeTeam=itemView.findViewById(R.id.xIvHomeTeam);
            xIvOppsiteTeam=itemView.findViewById(R.id.xIvOppsiteTeam);

        }
    }
}