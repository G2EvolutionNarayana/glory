package com.android.glory.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.glory.Adapter.PowerHitterAdpter;
import com.android.glory.Pojo.PlayerDetails;
import com.android.glory.R;

import java.util.ArrayList;
import java.util.List;

public class Activity_PowerHitter extends AppCompatActivity {

    private List<PlayerDetails> playerDetailsList;
    private PowerHitterAdpter powerHitterAdpter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    Button contin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_hitter);
        recyclerView = (RecyclerView) findViewById(R.id.rv_hitter);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        playerDetailsList = new ArrayList<>();
        playerDetailsList.add(new PlayerDetails(R.drawable.user,"M.S. Dhoni","158","10"));
        playerDetailsList.add(new PlayerDetails(R.drawable.user,"M.S. Dhoni","158","10"));
        playerDetailsList.add(new PlayerDetails(R.drawable.user,"M.S. Dhoni","158","10"));
        playerDetailsList.add(new PlayerDetails(R.drawable.user,"M.S. Dhoni","158","10"));
        playerDetailsList.add(new PlayerDetails(R.drawable.user,"M.S. Dhoni","158","10"));
        playerDetailsList.add(new PlayerDetails(R.drawable.user,"M.S. Dhoni","158","10"));
        playerDetailsList.add(new PlayerDetails(R.drawable.user,"M.S. Dhoni","158","10"));
        playerDetailsList.add(new PlayerDetails(R.drawable.user,"M.S. Dhoni","158","10"));
        playerDetailsList.add(new PlayerDetails(R.drawable.user,"M.S. Dhoni","158","10"));
        powerHitterAdpter = new PowerHitterAdpter(getApplicationContext(),playerDetailsList);
        recyclerView.setAdapter(powerHitterAdpter);
        contin = (Button) findViewById(R.id.contin);
        contin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_PowerHitter.this, Activity_Contest.class);
                startActivity(intent);
            }
        });
    }
}
