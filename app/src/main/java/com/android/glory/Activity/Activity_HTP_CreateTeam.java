package com.android.glory.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.glory.R;

public class Activity_HTP_CreateTeam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_htp_create_team);

        ImageView imgcancel = (ImageView) findViewById(R.id.imgcancel);
        imgcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

               String strtitle = "Create you team";
               String strdesc = "1. Select a Match:\n" +
                        "\u2022 Select a match from any of the listed current or upcoming matches. \n" +
                        "\n" +
                        "2. Create your Team: \n" +
                        "\u2022 Select a total of 5/7 players from the two teams. \n" +
                        "\u2022 Maximum of 3 players from each team. \n" +
                        "\u2022 After selecting all the 5/7 players, Select a powerhitter among them. \n" +
                        "\u2022 Note that players should be selected within the available credit points. \n" +
                        "\n" +
                        "Note:Points earned by Normal player and Powerhitter in different fields is explained under “Point System ” Category. \n" +
                        "\n" +
                        "3. Join the Contest: \n" +
                        "\u2022 Join the cash contest available in the next step. \n" +
                        "\u2022 Joining any contest will redirect you to payment page. Make a payment and you are in the race of winning. \n" +
                        "\n" +
                        "Note:Contest will be among only 2 players and hence your chance of winning will be more.";

        TextView texttitle = (TextView) findViewById(R.id.texttitle);
        TextView textdesc = (TextView) findViewById(R.id.textdesc);
        texttitle.setText(strtitle);
        textdesc.setText(strdesc);


    }
}
