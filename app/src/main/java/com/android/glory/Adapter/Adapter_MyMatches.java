package com.android.glory.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.glory.Pojo.Entity_MyMatches;
import com.android.glory.R;

import java.util.List;

public class Adapter_MyMatches extends RecyclerView.Adapter<Adapter_MyMatches.FilterViewHolder>{

    private Context mCtx;
    //we are storing all the products in a list
    private List<Entity_MyMatches> courses_offered_list;
    private Adapter_MyMatches.OnItemClickcourses mCallback1;
    String qty;
    String sub_category_id;



    //getting the context and product list with constructor
    public Adapter_MyMatches(Context mCtx, List<Entity_MyMatches> courses_offered_list, Adapter_MyMatches.OnItemClickcourses mCallback1) {
        this.mCtx = mCtx;
        this.courses_offered_list = courses_offered_list;
        this.mCallback1 = mCallback1;
    }

    public interface OnItemClickcourses {
        void OnItemClickcourses(int pos, String qty, String sub_category_id, int status);
    }
    @Override
    public Adapter_MyMatches.FilterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.item_mymatches, parent, false);
        return new Adapter_MyMatches.FilterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Adapter_MyMatches.FilterViewHolder holder, final int position) {

        final Entity_MyMatches follow = courses_offered_list.get(position);

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
