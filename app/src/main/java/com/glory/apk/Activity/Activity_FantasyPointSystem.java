package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.glory.apk.R;


public class Activity_FantasyPointSystem extends AppCompatActivity {

    TextView textdesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fantasy_point_system);

        textdesc = (TextView) findViewById(R.id.textdesc);


        String strdesc = "\u2022 The powerhitter you choose from your selected playing 5/7 will be receiving the points as shown in above second table. \n" +
                "\n" +
                "\u2022 5/7 from each side play the game. \n" +
                "\n" +
                "\u2022 After announcement of his/her inclusion in team, starting points are assigned to the player. \n" +
                "\n" +
                "He/she will not score any points if in case the player is unable to start the match. If any selected player is replaced in real cricket, starting points of previous player will be assigned to the replaced player. \n" +
                "\n" +
                "\u2022 The points will be assigned/applied to a selected player during the events occurring in superover. \n" +
                "\n" +
                "\u2022 If a player scores century, bonus points will be awarded to his century only and not for his half century. Any score above century will again be considered for next half century bonus points. \n" +
                "\n" +
                "\u2022 If in between, a player is transferred to a different team between two scheduled updates, for any reason whatsoever, such player will not be listed in the upcoming match w.r.t transferred team. Anyhow, the player will be available for selection in the next scheduled update. \n" +
                "\n" +
                "\u2022 Reliable sources will provide the data and as soon as the points have been marked completed i.e. winners are have been announced, there is no place for adjustments.. \n" +
                "\n" +
                "\u2022 However, if the match status is ‘In progress’ or ‘Waiting for review’ the points awarded are subject to change.";

        textdesc.setText(strdesc);
    }
}
