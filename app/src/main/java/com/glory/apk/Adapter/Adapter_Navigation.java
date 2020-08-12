package com.glory.apk.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.glory.apk.Pojo.Pojo_navigation;
import com.glory.apk.R;
import com.glory.apk.ViewHolder.ViewHolder_Navigation;

import java.util.ArrayList;

/**
 * Created by Android4 on 4/20/2019.
 */

public class Adapter_Navigation extends RecyclerView.Adapter<ViewHolder_Navigation> {

    private ArrayList<Pojo_navigation> mListFeeds;
    private LayoutInflater mInflater;
    private int mPreviousPosition = 0;
    private Context mContext;
    private Adapter_Navigation.OnItemClick mCallback;
    int currentNos;
    String qty;
    int intgridlist;
    String subcatgoryid;
    private static final int LIST_ITEM = 0;
    private static final int GRID_ITEM = 1;
    boolean isSwitchView = true;

    String locatelanguage;

    public Adapter_Navigation(Context context, ArrayList<Pojo_navigation> feedList, Adapter_Navigation.OnItemClick listener){
        mContext = context;
        // mInflater = LayoutInflater.from(context);
        mListFeeds=feedList;
        this.mCallback = listener;

    }

    public interface OnItemClick {
        void onClickedItem(int pos, String qty, int status);
    }
    public void setData( ArrayList<Pojo_navigation> feedList){
        mListFeeds=feedList;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder_Navigation onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        intgridlist = viewType;
        Log.e("testing","intgridlist = "+intgridlist);

        itemView = LayoutInflater.from(parent.getContext()).inflate( R.layout.item_navigation, null);


        // itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_servicelist, null);
        ViewHolder_Navigation rcv = new ViewHolder_Navigation(itemView);



        return rcv;
    }


    @Override
    public void onBindViewHolder(final ViewHolder_Navigation holder, final int position) {
        final Pojo_navigation feederInfo = mListFeeds.get(position);

        String feedDesc = null;



        holder.cart_name.setText(feederInfo.getName());

        holder.userimage.setImageResource(feederInfo.getImage());
     /*   Glide.with(mContext)
                .load(Uri.parse(String.valueOf(feederInfo.getImage())))
                //  .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.androidicon)
                // .placeholder(R.drawable.spa)
                //  .skipMemoryCache(true)
                .into(holder.userimage);*/



        holder.relativelayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty = feederInfo.getId();



                //holder.rate.setRating(Float.parseFloat(feederInfo.get_rating()));

                if (mCallback!=null){
                    mCallback.onClickedItem(position,qty, 1);
                }
            }
        });


        //holder.rate.setRating(Float.parseFloat(feederInfo.get_rating()));

 /*       if (position > mPreviousPosition) {
            AnimationUtils.animateSunblind(holder, true);

        } else {
            AnimationUtils.animateSunblind(holder, false);

        }*/

        mPreviousPosition = position;

    }
    @Override
    public int getItemViewType (int position) {
        if (isSwitchView){
            return LIST_ITEM;
        }else{
            return GRID_ITEM;
        }
    }

    public boolean toggleItemViewType () {
        isSwitchView = !isSwitchView;
        return isSwitchView;

    }

    @Override
    public int getItemCount() {
        return mListFeeds.size();
    }


}