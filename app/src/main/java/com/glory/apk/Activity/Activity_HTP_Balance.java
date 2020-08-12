package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.glory.apk.R;


public class Activity_HTP_Balance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_htp_balance);

        ImageView imgcancel = (ImageView) findViewById(R.id.imgcancel);
        imgcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String strtitle = "Account Balance";
        String strdesc = "\u2022 The money can be viewed, deposited or withdrawn into your Glory-5 account anytime by going to “My Balance” link.\n" +
                "\n" +
                "\u2022 One time Account Verification process is a must before you withdraw any money from your glory -5 account.\n" +
                "\n" +
                "\u2022 This process won’t take much time won’t be repeated until you change any of your details. \n" +
                "\n" +
                "\u2022  Only your \"Winnings\" can be withdrawn. \n" +
                "\n" +
                "\u2022 No processing fee will be deducted if you wish to withdrawn any winning amount. \n" +
                "\n" +
                "\u2022 The amount given in the form of cash bonus from Gory-5 cannot be withdrawn. However it can be used to join any cash contests available and win more money. This cash bonus will be having an expiry date, so it has to be used before that. \n" +
                "\n" +
                "\u2022 The minimum amount to withdrawn will be restricted to ₹150.";

        TextView texttitle = (TextView) findViewById(R.id.texttitle);
        TextView textdesc = (TextView) findViewById(R.id.textdesc);
        texttitle.setText(strtitle);
        textdesc.setText(strdesc);


    }
}
