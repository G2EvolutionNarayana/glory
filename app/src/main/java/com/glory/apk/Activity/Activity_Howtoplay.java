package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glory.apk.R;


public class Activity_Howtoplay extends AppCompatActivity {

    ImageView imgintroduction, imgcreate, imgmanaging, imgbalance;

    RelativeLayout relativeintroduction, relativecreate, relativemanaging, relativebalance;
    String strtitle, strdesc;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howtoplay);

        dialog = new Dialog(Activity_Howtoplay.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        relativeintroduction = (RelativeLayout) findViewById(R.id.relativeintroduction);
        relativecreate = (RelativeLayout) findViewById(R.id.relativecreate);
        relativemanaging = (RelativeLayout) findViewById(R.id.relativemanaging);
        relativebalance = (RelativeLayout) findViewById(R.id.relativebalance);

        imgintroduction = (ImageView) findViewById(R.id.imgintroduction);
        imgcreate = (ImageView) findViewById(R.id.imgcreate);
        imgmanaging = (ImageView) findViewById(R.id.imgmanaging);
        imgbalance = (ImageView) findViewById(R.id.imgbalance);

        relativeintroduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strtitle = "Introduction";
                strdesc = "\u2022 Glory-5 is one of the fantasy sports Application that is played using cricket knowledge & Skills. \n" +
                        "\n" +
                        "\u2022 Your own team can be created by picking real cricket players. \n" +
                        "\n" +
                        "\u2022 Use maximum budget of available credit and create your team. \n" +
                        "\n" +
                        "\u2022 The performance of your chosen players in real life match will gain them points. \n" +
                        "\n" +
                        "\u2022 Join !! Play !! Showcase your skill !! Win For Glory  !!";
                Intent intent = new Intent(Activity_Howtoplay.this, Activity_HTPIntroduction.class);
                startActivity(intent);
               // dialogplay();
            }
        });

        relativecreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strtitle = "Create your team";
                strdesc = "1. Select a Match:\n" +
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
                Intent intent = new Intent(Activity_Howtoplay.this, Activity_HTP_CreateTeam.class);
                startActivity(intent);
               // dialogplay();
            }
        });

        relativemanaging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strtitle = "Managing your team";
                strdesc = "\u2022 You will be having till deadline of the match to edit or make changes to your Glory-5 team as you like.\n" +
                        "\n" +
                        "\u2022 You can also change your power hitter before the deadline of the match.\n" +
                        "\n" +
                        "\u2022 Select edit team to make changes to your team \n" +
                        "\n" +
                        "\u2022 Per match you can create up to 5 different teams and with any of the teams created you can choose to join any contest. Click on “create team” for creating new team.";
                Intent intent = new Intent(Activity_Howtoplay.this, Activity_HTP_ManageTeam.class);
                startActivity(intent);
               // dialogplay();
            }
        });

        relativebalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strtitle = "Account Balance";
                strdesc = "\u2022 The money can viewed, deposited or withdrawn into your Glory-5 account anytime by going to “My Balance” link.\n" +
                        "\n" +
                        "\u2022 One time Account Verification process is a must before you withdraw any money from your glory -5 account.\n" +
                        "\n" +
                        "\u2022 This process won’t take much time won’t be repeated until you change any of your details. \n" +
                        "\n" +
                        "\u2022 No processing fee will be deducted if you wish to withdrawn any winning amount. \n" +
                        "\n" +
                        "\u2022 The amount given in the form of cash bonus from Gory-5 cannot be withdrawn. However it can be used to join any cash contests available and win more money. This cash bonus will be having an expiry date, so it has to be used before that. \n" +
                        "\n" +
                        "\u2022 The minimum amount to withdrawn will be restricted to ₹150.";
                Intent intent = new Intent(Activity_Howtoplay.this, Activity_HTP_Balance.class);
                startActivity(intent);
                //dialogplay();
            }
        });


    }
    private void dialogplay() {
        dialog.setContentView(R.layout.dialog_howtoplay);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.TOP;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        ImageView signupcancel = (ImageView) dialog.findViewById(R.id.imgcancel);
        TextView texttitle = (TextView) dialog.findViewById(R.id.texttitle);
        TextView textdesc = (TextView) dialog.findViewById(R.id.textdesc);
        texttitle.setText(strtitle);
        textdesc.setText(strdesc);
        signupcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
