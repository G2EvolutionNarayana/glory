package com.glory.apk.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.glory.apk.R;


/**
 * Created by Android4 on 4/20/2019.
 */

public class ViewHolder_Navigation extends RecyclerView.ViewHolder implements View.OnClickListener {


    public TextView cart_name;

    public ImageView userimage;
    public RelativeLayout relativelayout1;



    public ViewHolder_Navigation(View itemView) {

        super(itemView);
        itemView.setOnClickListener(this);


        userimage = (ImageView) itemView.findViewById(R.id.cart_image);

        cart_name = (TextView)itemView.findViewById(R.id.cart_name);

        relativelayout1 = (RelativeLayout) itemView.findViewById(R.id.relativelayout1);



    }

    @Override
    public void onClick(View view) {
        //Toast.makeText(view.getContext(), "Clicked Position = " + getPosition(), Toast.LENGTH_SHORT).show();

    /*    Integer valueinteger = getPosition();
        String positionvalue = String.valueOf(valueinteger);
        positionvalue.equals("number");*/
    }

}
