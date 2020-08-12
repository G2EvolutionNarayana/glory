package com.glory.apk.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.glory.apk.Pojo.Entity_Cricket;
import com.glory.apk.R;

import java.util.List;

public class Adapter_MyMatchesUpcoming extends RecyclerView.Adapter<Adapter_MyMatchesUpcoming.FilterViewHolder>{

    private Context mCtx;
    //we are storing all the products in a list
    private List<Entity_Cricket> courses_offered_list;
    private Adapter_MyMatchesUpcoming.OnItemClickcourses mCallback1;
    String qty;
    String sub_category_id;



    //getting the context and product list with constructor
    public Adapter_MyMatchesUpcoming(Context mCtx, List<Entity_Cricket> courses_offered_list, Adapter_MyMatchesUpcoming.OnItemClickcourses mCallback1) {
        this.mCtx = mCtx;
        this.courses_offered_list = courses_offered_list;
        this.mCallback1 = mCallback1;
    }

    public interface OnItemClickcourses {
        void OnItemClickcourses(int pos, String qty, String sub_category_id, int status);
    }
    @Override
    public Adapter_MyMatchesUpcoming.FilterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.item_cricket, parent, false);
        return new Adapter_MyMatchesUpcoming.FilterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Adapter_MyMatchesUpcoming.FilterViewHolder holder, final int position) {

        final Entity_Cricket follow = courses_offered_list.get(position);

        holder.texttitle.setText(follow.getProductName());

       /* if (follow.getProductImage() == null || follow.getProductImage().length() == 0){
            Glide.with(mCtx)
                    .load(Uri.parse(String.valueOf(R.drawable.logo)))
                    .error(R.drawable.logo)
                    .into(holder.product_image);

        }else{

            Glide.with(mCtx)
                    .load(Uri.parse(follow.getProductImage()))
                    .error(R.drawable.logo)
                    .into(holder.product_image);
        }*/

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

        public FilterViewHolder(View itemView) {
            super(itemView);
            texttitle=itemView.findViewById(R.id.texttitle);
            textname1=itemView.findViewById(R.id.textname1);
            textname2=itemView.findViewById(R.id.textname2);
            texttime=itemView.findViewById(R.id.texttime);
            card_view1=itemView.findViewById(R.id.card_view1);

            img1=itemView.findViewById(R.id.img1);
            img2=itemView.findViewById(R.id.img2);

        }
    }
}
