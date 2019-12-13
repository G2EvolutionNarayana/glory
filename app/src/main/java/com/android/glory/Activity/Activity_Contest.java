package com.android.glory.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.glory.Adapter.ContestAdapter;
import com.android.glory.Pojo.Contest;
import com.android.glory.R;

import java.util.ArrayList;
import java.util.List;

public class Activity_Contest extends AppCompatActivity {

    private List<Contest> contestList;
    private ContestAdapter contestAdapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest);


        recyclerView = (RecyclerView) findViewById(R.id.rv_contest);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        contestList = new ArrayList<>();
        contestList.add(new Contest("\u20B9 5 Lakhs","\u20B975","45698 spots left","5245 spots"));
        contestList.add(new Contest("\u20B9 5 Lakhs","\u20B975","45698 spots left","5245 spots"));
        contestList.add(new Contest("\u20B9 5 Lakhs","\u20B975","45698 spots left","5245 spots"));
        contestList.add(new Contest("\u20B9 5 Lakhs","\u20B975","45698 spots left","5245 spots"));
        contestList.add(new Contest("\u20B9 5 Lakhs","\u20B975","45698 spots left","5245 spots"));
        contestList.add(new Contest("\u20B9 5 Lakhs","\u20B975","45698 spots left","5245 spots"));
        contestList.add(new Contest("\u20B9 5 Lakhs","\u20B975","45698 spots left","5245 spots"));
        contestList.add(new Contest("\u20B9 5 Lakhs","\u20B975","45698 spots left","5245 spots"));
        contestList.add(new Contest("\u20B9 5 Lakhs","\u20B975","45698 spots left","5245 spots"));
        contestList.add(new Contest("\u20B9 5 Lakhs","\u20B975","45698 spots left","5245 spots"));
        contestList.add(new Contest("\u20B9 5 Lakhs","\u20B975","45698 spots left","5245 spots"));
        contestList.add(new Contest("\u20B9 5 Lakhs","\u20B975","45698 spots left","5245 spots"));
        contestList.add(new Contest("\u20B9 5 Lakhs","\u20B975","45698 spots left","5245 spots"));

        contestAdapter = new ContestAdapter(getApplicationContext(),contestList);
        recyclerView.setAdapter(contestAdapter);

    }
}
