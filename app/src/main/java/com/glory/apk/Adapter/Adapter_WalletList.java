package com.glory.apk.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.glory.apk.Activity.ActivityTransactionList;
import com.glory.apk.Activity.BonusActivity;
import com.glory.apk.Activity.DepositedActivity;
import com.glory.apk.Activity.WinningListActivity;
import com.glory.apk.Activity.WithdrawalListActivity;
import com.glory.apk.Pojo.Pojo_WalletList;
import com.glory.apk.R;
import com.glory.apk.ViewHolder.ViewHolder_WalletList;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * Created by Jana on 07-03-2016.
 */
public class Adapter_WalletList extends RecyclerView.Adapter<ViewHolder_WalletList> {
    //contains the list of buyers
    private ArrayList<Pojo_WalletList> mListFeeds;
 //   private LayoutInflater mInflater;
    // private VolleySingleton mVolleySingleton;
    // private ImageLoader mImageLoader;
    private int mPreviousPosition = 0;
    private Context mContext;
    private FeederItemListener feedItemListner;
    private final SparseBooleanArray mCollapsedStatus;

    String rowid, cusid;
    String clicking;
    String qty;
    private OnItemClick mCallback;

    public Adapter_WalletList(Context context, ArrayList<Pojo_WalletList> feedList, OnItemClick listener){
        mContext = context;
       // mInflater = LayoutInflater.from(context);
        mListFeeds=feedList;

        mCollapsedStatus = new SparseBooleanArray();
        this.mCallback = listener;



    }
    public interface OnItemClick {
        void onClickedItem (int pos, String qty, int status, String name);
    }
    public void SetListener(FeederItemListener listener){
        feedItemListner = listener;
    }

    public void setData( ArrayList<Pojo_WalletList> feedList){
        mListFeeds=feedList;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder_WalletList onCreateViewHolder(ViewGroup parent, int viewType) {


        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_walletlist, null);
        ViewHolder_WalletList rcv = new ViewHolder_WalletList(layoutView);


        return rcv;
    }

    public  long getDifferenceDays( Date d1) {
        Calendar c = Calendar.getInstance();
        long diff = c.getTimeInMillis() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public void onBindViewHolder(final ViewHolder_WalletList holder, final int position) {
        final Pojo_WalletList feederInfo = mListFeeds.get(position);



        holder.feederName.setText(feederInfo.getFeederName());
       // holder.feederName.setText(feederInfo.getFeederName());


        //  holder.feederThumbnail.setImageResource(feederInfo.getPhoto());

        if (feederInfo.getFeederThumbnail() == null){

        }else{
            holder.feederThumbnail.setImageResource(feederInfo.getFeederThumbnail());
           /* Glide.with(mContext)
                    .load(feederInfo.getPhoto())
                    // .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .error(R.drawable.glorylogo)
                    .centerCrop()
                    // .placeholder(R.drawable.button)
                    //  .skipMemoryCache(true)
                    .into(holder.feederThumbnail);*/

        }

        // holder.linearLayout.setBackgroundColor(Color.parseColor((feederInfo.getBackcolor())));

        mPreviousPosition = position;

//        holder.feederThumbnail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                qty = feederInfo.getRowid();
//               String name = feederInfo.getFeederName();
//
//                if (mCallback!=null){
//                    mCallback.onClickedItem(position,qty, 1, name);
//                }
//            }
//        });
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position==1){
                    Intent intent=new Intent(mContext, DepositedActivity.class);
                    mContext.startActivity(intent);
                }else if(position==0){
                    Intent intent=new Intent(mContext, WinningListActivity.class);
                    mContext.startActivity(intent);
                }else if (position==3){
                    Intent intent=new Intent(mContext, ActivityTransactionList.class);
                    mContext.startActivity(intent);
                }
                else if (position==2){
                    Intent intent=new Intent(mContext, BonusActivity.class);
                    mContext.startActivity(intent);
                } else if (position==4){
                    Intent intent=new Intent(mContext, WithdrawalListActivity.class);
                    mContext.startActivity(intent);
                }

            }
        });



    }

    @Override
    public int getItemCount() {
        return mListFeeds.size();
    }


    public interface FeederItemListener {
        public void onFeedClicked(int position, int resid);
    }
}

