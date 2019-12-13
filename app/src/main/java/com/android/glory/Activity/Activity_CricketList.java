package com.android.glory.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.android.glory.R;

public class Activity_CricketList extends AppCompatActivity {

    LinearLayout layout5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cricket_list);

        layout5 = (LinearLayout) findViewById(R.id.lay_5_side);


        layout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_CricketList.this, Activity_SelectPlayer.class);
                startActivity(intent);

            }
        });

    }
}
