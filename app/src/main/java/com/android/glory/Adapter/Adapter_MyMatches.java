package com.android.glory.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.glory.Model.MyMatches.Datum;
import com.android.glory.Pojo.Entity_MyMatches;
import com.android.glory.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter_MyMatches extends RecyclerView.Adapter<Adapter_MyMatches.FilterViewHolder>{

    private Context mCtx;
    //we are storing all the products in a list
    private List<Datum> courses_offered_list;
    private Adapter_MyMatches.OnItemClickcourses mCallback1;
    String qty;
    String sub_category_id;



    public Adapter_MyMatches(Context activity, List<Datum> data,Adapter_MyMatches.OnItemClickcourses mCallback1) {
        this.mCtx = activity;
        this.courses_offered_list = data;
        this.mCallback1=mCallback1;
    }

    public interface OnItemClickcourses {
        void OnItemClickcourses(int pos);
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

        holder.xTvTitle.setText(follow.getSeries().getName()+follow.getName());
        holder.xTvHomeTeam.setText(follow.getHometeam().getShortName());
        holder.xTvOppositeTeamName.setText(follow.getAwayteam().getShortName());
        holder.xTvTime.setText(follow.getDatetime().getStartDateTime());

          String erfgerfg=follow.getHometeam().getLogoUrl();
        if (follow.getHometeam().getLogoUrl() == null || follow.getHometeam().getLogoUrl().length() == 0){
            Glide.with(mCtx)
                    .load(Uri.parse(String.valueOf(R.drawable.icon1))
                    )
                    .error(R.drawable.icon1)
                    .into(holder.xIvHomeTeam);
        }else{

            Glide.with(mCtx)
                    .load(Uri.parse(follow.getHometeam().getLogoUrl()))
                    .error(R.drawable.icon1)
                    .into(holder.xIvHomeTeam);
        }

        if (follow.getAwayteam().getLogoUrl() == null || follow.getAwayteam().getLogoUrl().length() == 0){
            Glide.with(mCtx)
                    .load(Uri.parse(String.valueOf(R.drawable.icon1)))
                    .error(R.drawable.icon1)
                    .into(holder.xIvOppsiteTeam);

        }else{

            Glide.with(mCtx)
                    .load(Uri.parse(follow.getAwayteam().getLogoUrl()))
                    .error(R.drawable.icon1)
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
