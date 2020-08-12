package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.glory.apk.R;


public class Activity_HTPIntroduction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_htpintroduction);

        ImageView imgcancel = (ImageView) findViewById(R.id.imgcancel);
        imgcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String strtitle = "Introduction";
        String strdesc = "\u2022 Glory-5 is one of the fantasy sports Application that is played using cricket knowledge & Skills. \n" +
                "\n" +
                "\u2022 Your own team can be created by picking real cricket players. \n" +
                "\n" +
                "\u2022 Use maximum budget of available credit and create your team. \n" +
                "\n" +
                "\u2022 The performance of your chosen players in real life match will gain them points. \n" +
                "\n" +
                "\u2022 Join !! Play !! Showcase your skill !! Win For Glory  !!";

        TextView texttitle = (TextView) findViewById(R.id.texttitle);
        TextView textdesc = (TextView) findViewById(R.id.textdesc);
        texttitle.setText(strtitle);
        textdesc.setText(strdesc);
    }
}
