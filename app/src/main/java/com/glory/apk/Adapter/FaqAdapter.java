package com.glory.apk.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.glory.apk.Model.FaqDataModel.Datum;
import com.glory.apk.R;

import java.util.List;

public class FaqAdapter extends RecyclerView.Adapter<FaqAdapter.FaqAdapterChild> {
    private Context context;
    List<Datum> data;
    public FaqAdapter(Context context, List<Datum> data) {
        this.context=context;
        this.data=data;

    }

    @NonNull
    @Override
    public FaqAdapter.FaqAdapterChild onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_faq_child, parent, false);
        return new FaqAdapterChild(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FaqAdapter.FaqAdapterChild holder, int position) {
        holder.xTvQuestions.setText(data.get(position).getTitle());
        holder.xTvAnswers.setText(data.get(position).getDescription());
        holder.xTvQuestions.setText(data.get(position).getTitle());
        holder.xRelayMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.get(position).getSelected()){
                    holder.xTvAnswers.setVisibility(View.GONE);
                    data.get(position).setSelected(false);
//                    #d2f8d2
                    holder.xIvArrow.setImageResource(R.drawable.ic_expand_more);

                }else {
                    holder.xTvAnswers.setVisibility(View.VISIBLE);
                    data.get(position).setSelected(true);
                    holder.xIvArrow.setImageResource(R.drawable.ic_expand_less);

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class FaqAdapterChild extends RecyclerView.ViewHolder {
        private TextView xTvQuestions,xTvAnswers;
        private ImageView xIvArrow;
        RelativeLayout xRelayMain;
        public FaqAdapterChild(@NonNull View itemView) {
            super(itemView);
            xTvQuestions=itemView.findViewById(R.id.xTvQuestions);
            xTvAnswers=itemView.findViewById(R.id.xTvAnswers);
            xIvArrow=itemView.findViewById(R.id.xIvArrow);
            xRelayMain=itemView.findViewById(R.id.xRelayMain);

        }
    }
}
