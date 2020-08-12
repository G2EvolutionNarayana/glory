package com.glory.apk.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.glory.apk.Activity.FinalWithDrawAmountActivity;
import com.glory.apk.Model.AccountListModel;
import com.glory.apk.R;

import java.util.ArrayList;

public class AccountListAdapter extends RecyclerView.Adapter<AccountListAdapter.AccountListAdapterChild> {
    Context context;
    ArrayList<AccountListModel> accountList;

    public AccountListAdapter(Context context, ArrayList<AccountListModel> accountList) {
        this.context = context;
        this.accountList = accountList;
    }

    @NonNull
    @Override
    public AccountListAdapterChild onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.account_list_child, parent, false);
        return new AccountListAdapterChild(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountListAdapterChild holder, int position) {
        holder.xTvBankName.setText(accountList.get(position).getBankName());
        String str = accountList.get(position).getAccountNumber();
        String main = "";
        char[] ch = str.toCharArray();
        for (int i = 0; i <= str.length(); i++) {
            if (i > str.length() - 4) {
                main = main + String.valueOf(ch[i-1]);

            }else if (i<4) {
                main = main + String.valueOf(ch[i]);

            }else
             {
                main = main + "*";
            }
        }
        holder.xTvAccountNumber.setText(main);

        holder.xIvBankImage.setImageResource(accountList.get(position).getImage());
        holder.card_view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FinalWithDrawAmountActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }

    public class AccountListAdapterChild extends RecyclerView.ViewHolder {
        private TextView xTvBankName, xTvAccountNumber, xTvAccountStatus;
        private ImageView xIvBankImage;
        private CardView card_view1;

        public AccountListAdapterChild(@NonNull View itemView) {
            super(itemView);
            xTvBankName = (TextView) itemView.findViewById(R.id.xTvBankName);
            xTvAccountNumber = (TextView) itemView.findViewById(R.id.xTvAccountNumber);
            xTvAccountStatus = (TextView) itemView.findViewById(R.id.xTvAccountStatus);
            xIvBankImage = (ImageView) itemView.findViewById(R.id.xIvBankImage);
            card_view1 = (CardView) itemView.findViewById(R.id.card_view1);

        }
    }
}
