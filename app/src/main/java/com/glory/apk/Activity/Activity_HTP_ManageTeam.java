package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.glory.apk.R;


public class Activity_HTP_ManageTeam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_htp_manage_team);
        ImageView imgcancel = (ImageView) findViewById(R.id.imgcancel);
        imgcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

                String strtitle = "Managing your team";
                String strdesc = "\u2022 You will be having till deadline of the match to edit or make changes to your Glory-5 team as you like.\n" +
                        "\n" +
                        "\u2022 You can also change your power hitter before the deadline of the match.\n" +
                        "\n" +
                        "\u2022 Select edit team to make changes to your team \n" +
                        "\n" +
                        "\u2022 Per match you can create up to 5 different teams and with any of the teams created you can choose to join any contest. Click on “create team” for creating new team.";




        TextView texttitle = (TextView) findViewById(R.id.texttitle);
        TextView textdesc = (TextView) findViewById(R.id.textdesc);
        texttitle.setText(strtitle);
        textdesc.setText(strdesc);


    }
}
