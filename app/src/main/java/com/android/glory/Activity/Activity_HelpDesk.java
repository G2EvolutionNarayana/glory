package com.android.glory.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.glory.R;

public class Activity_HelpDesk extends AppCompatActivity {


    Dialog dialog;

    String strfaqquestion;
    EditText editquestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__help_desk);



        dialog = new Dialog(Activity_HelpDesk.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);


        LinearLayout linearemailid = (LinearLayout) findViewById(R.id.linearemailid);
        LinearLayout linearfaq = (LinearLayout) findViewById(R.id.linearfaq);


        linearemailid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strmail = "Glory5@gmail.com";
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", strmail, null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Glory5");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });

        linearfaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogfaq();
            }
        });

    }

    private void dialogfaq() {
        dialog.setContentView(R.layout.dialog_faq);

        ImageView signupcancel = (ImageView) dialog.findViewById(R.id.imgcancel);
        editquestion = (EditText) dialog.findViewById(R.id.editquestion);
        TextView textsubmit = (TextView) dialog.findViewById(R.id.textsubmit);


        textsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strfaqquestion = editquestion.getText().toString();

                if (strfaqquestion == null || strfaqquestion.length() == 0){
                    editquestion.setError("Enter Question");
                    // Toast.makeText(Activity_Balance.this, "Enter Amount", Toast.LENGTH_SHORT).show();
                }else{

                    Toast.makeText(Activity_HelpDesk.this, "Need to Integrate API", Toast.LENGTH_SHORT).show();


                   /* ConnectionDetector cd = new ConnectionDetector(getActivity());
                    if (cd.isConnectingToInternet()) {
                        new WalletUser().execute();
                    } else {
                        Toast.makeText(getActivity(), "Internet Connection Not Available Enable Internet And Try Again", Toast.LENGTH_LONG).show();
                    }*/


                }
            }
        });


        signupcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
