package com.android.glory.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.glory.Model.CompletedContestModel.CompletedContestDatum;
import com.android.glory.Model.MyMatches.Datum;
import com.android.glory.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class CompletedAdapter extends RecyclerView.Adapter<CompletedAdapter.FilterViewHolder> {

    private Context mCtx;
    //we are storing all the products in a list
    private List<Datum> courses_offered_list;
    private Adapter_MyMatches.OnItemClickcourses mCallback1;
    String qty;
    String sub_category_id;
    List<CompletedContestDatum> data;


//    public CompletedAdapter(Context activity, List<Datum> data, Adapter_MyMatches.OnItemClickcourses mCallback1) {
//        this.mCtx = activity;
//        this.courses_offered_list = data;
//        this.mCallback1 = mCallback1;
//    }

    public CompletedAdapter(Context mCtx, List<CompletedContestDatum> data) {
        this.mCtx = mCtx;
        this.data = data;
    }


    @Override
    public CompletedAdapter.FilterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.item_compete_matches, parent, false);
        return new FilterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CompletedAdapter.FilterViewHolder holder, final int position) {
        CompletedContestDatum follow = data.get(position);

        holder.xTvWonMoney.setText("YOU WON \u20B9" + "60");
        holder.xTvEntryFee.setText("\u20B9" + "60");
        holder.xTvName.setText(follow.getTeamName());
        holder.xTvPoints.setText(follow.getTeam1Points().toString()+" pts");
        holder.xTvEntryFee.setText("\u20B9" + follow.getCompletedContestContest().getEntryFee());

        if (follow.getWin() == 1) {
            holder.xTvWonMoney.setText("YOU Lost");
        } else {
            holder.xTvWonMoney.setText("YOU WON \u20B9" + follow.getCompletedContestContest().getPrizeAmount());


        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class FilterViewHolder extends RecyclerView.ViewHolder {

        TextView xTvWonMoney, xTvPoints, xTvEntryFee, xTvName;
        CardView card_view1;

        public FilterViewHolder(View itemView) {
            super(itemView);
            xTvName = itemView.findViewById(R.id.xTvName);
            xTvWonMoney = itemView.findViewById(R.id.xTvWonMoney);
            xTvPoints = itemView.findViewById(R.id.xTvPoints);
            xTvEntryFee = itemView.findViewById(R.id.xTvEntryFee);
            card_view1 = itemView.findViewById(R.id.card_view1);


        }
    }
}
