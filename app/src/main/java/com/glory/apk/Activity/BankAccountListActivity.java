package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.glory.apk.Adapter.AccountListAdapter;
import com.glory.apk.Model.AccountListModel;
import com.glory.apk.R;

import java.util.ArrayList;

public class BankAccountListActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView xRecyAccount;
    private ArrayList<AccountListModel> accountList;
    TextView xTvNewAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_account_list);
        mInitWidgets();
    }

    private void mInitWidgets() {
        accountList = new ArrayList<>();
        xRecyAccount = (RecyclerView) findViewById(R.id.xRecyAccount);
        xTvNewAccount = (TextView) findViewById(R.id.xTvNewAccount);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        xRecyAccount.setHasFixedSize(true);
        xRecyAccount.setLayoutManager(linearLayoutManager);
        accountList.add(new AccountListModel(R.drawable.wallet, "6434 4423 4423 2323", "Indian Bank"));
        accountList.add(new AccountListModel(R.drawable.wallet, "6434 2134 6342 7855", "State Bank"));
        accountList.add(new AccountListModel(R.drawable.wallet, "4342 4344 2332 4545", "Indusland Bank"));
        xRecyAccount.setAdapter(new AccountListAdapter(BankAccountListActivity.this, accountList));

        xTvNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BankAccountListActivity.this, AccountVerifyActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {


    }
}
