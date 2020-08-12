package com.glory.apk.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.glory.apk.R;
import com.glory.apk.Retrofit.CricketList.Match;
import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AdapterMainMatches extends RecyclerView.Adapter<AdapterMainMatches.FilterViewHolder>{

    private Context mCtx;
    //we are storing all the products in a list
    private List<Match> courses_offered_list;
    private AdapterMainMatches.OnItemClickcourses mCallback1;
    String qty;
    String sub_category_id;



    //getting the context and product list with constructor
    public AdapterMainMatches(Context mCtx, List<Match> courses_offered_list, AdapterMainMatches.OnItemClickcourses mCallback1) {
        this.mCtx = mCtx;
        this.courses_offered_list = courses_offered_list;
        this.mCallback1 = mCallback1;
    }

    public interface OnItemClickcourses {
        void OnItemClickcourses(int pos, String qty, String sub_category_id, int status);
    }
    @Override
    public AdapterMainMatches.FilterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.item_cricket, parent, false);
        return new FilterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterMainMatches.FilterViewHolder holder, final int position) {

        final Match follow = courses_offered_list.get(position);

        holder.texttitle.setText(follow.getSeries().getShortName());
        holder.textname1.setText(follow.getHomeTeam().getShortName());
        holder.textname2.setText(follow.getAwayTeam().getShortName());

        holder.linearlayoutcolor1.setBackgroundColor(Color.parseColor(follow.getHomeTeam().getTeamColour()));
        holder.linearlayoutcolor2.setBackgroundColor(Color.parseColor(follow.getAwayTeam().getTeamColour()));

        Date date = null;
        Date date1 = null;
        Date date2 = null;

        String strstartdate = follow.getStartDateTime();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
        SimpleDateFormat mdformat1 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Log.e("testing","start on create = "+strstartdate);

        Calendar calendar = Calendar.getInstance();
        String strcurrenctdate = mdformat1.format(calendar.getTime());
        try {
            date = mdformat.parse(strstartdate);

            String str = mdformat1.format(date);
            date1 = mdformat1.parse(strcurrenctdate);
            date2 = mdformat1.parse(str);

            Log.e("testing","year on create = "+str);

//in milliseconds
            long diff = date2.getTime() - date1.getTime();

            long diffSeconds = diff / 1000 % 60;

            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            Log.e("testing","difference days = "+diffDays+"-"+diffHours+"-"+diffMinutes+"-"+diffSeconds);

            System.out.print(diffDays + " days, ");
            System.out.print(diffHours + " hours, ");
            System.out.print(diffMinutes + " minutes, ");
            System.out.print(diffSeconds + " seconds.");

            if (diffDays < 0){
                holder.texttime.setText("");
            }else if (diffDays == 0){
                if (diffHours < 0){
                    holder.texttime.setText("");
                }else if (diffHours == 0){
                    holder.texttime.setText(diffMinutes+"m left");
                }else{
                    holder.texttime.setText(diffHours+"h "+diffMinutes+"m left");
                }


            }else{
                holder.texttime.setText(diffDays+"days "+diffHours+"h "+diffMinutes+"m left");
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


        if (follow.getHomeTeam().getLogoUrl() == null || follow.getHomeTeam().getLogoUrl().length() == 0){
            Glide.with(mCtx)
                    .load(Uri.parse(String.valueOf(R.drawable.glorylogo)))
                    .error(R.drawable.glorylogo)
                    .into(holder.img1);

        }else{

            Glide.with(mCtx)
                    .load(Uri.parse(follow.getHomeTeam().getLogoUrl()))
                    .error(R.drawable.glorylogo)
                    .into(holder.img1);
        }

        if (follow.getAwayTeam().getLogoUrl() == null || follow.getAwayTeam().getLogoUrl().length() == 0){
            Glide.with(mCtx)
                    .load(Uri.parse(String.valueOf(R.drawable.glorylogo)))
                    .error(R.drawable.glorylogo)
                    .into(holder.img2);

        }else{

            Glide.with(mCtx)
                    .load(Uri.parse(follow.getAwayTeam().getLogoUrl()))
                    .error(R.drawable.glorylogo)
                    .into(holder.img2);
        }

        holder.card_view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mCallback1!=null){
                    mCallback1.OnItemClickcourses(position,qty, "", 1);
                }


            }
        });


    }

    @Override
    public int getItemCount() {
        return courses_offered_list.size();
    }

    public class FilterViewHolder extends RecyclerView.ViewHolder {

        TextView texttitle;
        TextView textname1, textname2, texttime;
        ImageView img1, img2;
        CardView card_view1;
        LinearLayout linearlayoutcolor1, linearlayoutcolor2;

        public FilterViewHolder(View itemView) {
            super(itemView);
            texttitle=itemView.findViewById(R.id.texttitle);
            textname1=itemView.findViewById(R.id.textname1);
            textname2=itemView.findViewById(R.id.textname2);
            texttime=itemView.findViewById(R.id.texttime);
            card_view1=itemView.findViewById(R.id.card_view1);
            linearlayoutcolor1=itemView.findViewById(R.id.linearlayoutcolor1);
            linearlayoutcolor2=itemView.findViewById(R.id.linearlayoutcolor2);

            img1=itemView.findViewById(R.id.img1);
            img2=itemView.findViewById(R.id.img2);

        }
    }



}