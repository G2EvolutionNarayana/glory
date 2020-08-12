package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.glory.apk.R;


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

        String strtitle = "Create your team";
        String strdesc = "<b>1. Select a Match:</b> <br>" +
                "\u2022 Select a match from any of the listed current or upcoming matches. <br>" + "<br>";
        String strdesc1 = "<b>2. Create your Team:</b>  <br>" +
                "\u2022 Select a total of 5/7 players from the two teams. <br>" +
                "\u2022 Maximum of 3 players from each team. <br>" +
                "\u2022 After selecting all the 5/7 players, Select a powerhitter among them. <br>" +
                "\u2022 Note that players should be selected within the available credit points. <br>" +
                "<br>" +
                "Note:Points earned by Normal player and Powerhitter in different fields is explained under “Point System ” Category. <br>" +
                "<br>";
        String strdesc3 = "<b>3. Join the Contest:</b> <br>" +
                "\u2022 Join the cash contest available in the next step. <br>" +
                "\u2022 Joining any contest will redirect you to payment page. Make a payment and you are in the race of winning. <br>" +
                "<br>" +
                "Note:Contest will be among only 2 players and hence your chance of winning will be more.";

        TextView texttitle = (TextView) findViewById(R.id.texttitle);
        TextView textdesc = (TextView) findViewById(R.id.textdesc);
        TextView textdesc1 = (TextView) findViewById(R.id.textdesc1);
        TextView textdesc2 = (TextView) findViewById(R.id.textdesc2);

        texttitle.setText(strtitle);
        textdesc.setText(Html.fromHtml(strdesc));
        textdesc1.setText(Html.fromHtml(strdesc1));
        textdesc2.setText(Html.fromHtml(strdesc3));


    }
}
