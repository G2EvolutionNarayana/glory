package com.glory.apk.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.glory.apk.R;


/**
 * Created by Jana on 07-03-2016.
 */
public class ViewHolder_WalletList extends RecyclerView.ViewHolder {
    public ImageView feederThumbnail;
    public TextView feederName;
    public LinearLayout linearLayout;




    public ViewHolder_WalletList(View itemView) {
        super(itemView);
        feederThumbnail = (ImageView) itemView.findViewById(R.id.fedderthumbnail);
        feederName = (TextView)itemView.findViewById(R.id.feedername);
        linearLayout = (LinearLayout)itemView.findViewById(R.id.linearlayout);



    }
}
