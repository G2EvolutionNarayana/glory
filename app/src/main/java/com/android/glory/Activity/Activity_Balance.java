package com.android.glory.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.glory.Adapter.Adapter_WalletList;
import com.android.glory.Pojo.Pojo_WalletList;
import com.android.glory.R;

import java.util.ArrayList;

public class Activity_Balance extends AppCompatActivity implements Adapter_WalletList.OnItemClick{

    TextView textwallet;

    String []rowid =new String[]{"1","2","3","4","5"};
    String []title =new String[]{"Winnings","Deposited","Bonus","Transaction History","Manage Payments"};
    Integer []images = new Integer[]{R.drawable.winningsicon, R.drawable.depositedicon, R.drawable.bonusicon, R.drawable.trnasactionhistoryicon, R.drawable.managepaymentsicon};

    Dialog dialog;
    EditText editamount;
    TextView textwalletsubmit;
    TextView text100, text200, text500;
    String strwallet;

    EditText editquestion;
    String strfaqquestion;

    TextView textaddmoney, textwithdrawmoney;

    private RecyclerView mFeedRecyler;
    private ArrayList<Pojo_WalletList> mListFeederInfo;
    private Adapter_WalletList adapter;
    Adapter_WalletList mAdapterFeeds;
    private GridLayoutManager lLayout;
    private Adapter_WalletList.OnItemClick mCallback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        String strrs = getResources().getString(R.string.Rs);
        textwallet = (TextView) findViewById(R.id.textwallet);
        textwallet.setText(strrs+50);


        dialog = new Dialog(Activity_Balance.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        mFeedRecyler = (RecyclerView)findViewById(R.id.feedrecycler);
        lLayout = new GridLayoutManager(Activity_Balance.this, 2);
        mFeedRecyler.setLayoutManager(lLayout);
        mCallback=this;

        textaddmoney = (TextView) findViewById(R.id.textaddmoney);
        textwithdrawmoney = (TextView) findViewById(R.id.textwithdrawmoney);


        setUpReccyler();

        textaddmoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addmoney();
            }
        });

        textwithdrawmoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                withdrawmoney();
            }
        });

    }

    private void addmoney() {
        dialog.setContentView(R.layout.dialog_wallet);
        editamount = (EditText)  dialog.findViewById(R.id.editamount);
        textwalletsubmit = (TextView) dialog.findViewById(R.id.textwalletsubmit);
        text100 = (TextView) dialog.findViewById(R.id.text100);
        text200 = (TextView) dialog.findViewById(R.id.text200);
        text500 = (TextView) dialog.findViewById(R.id.text500);
        ImageView signupcancel = (ImageView) dialog.findViewById(R.id.imgcancel);
        textwalletsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strwallet = editamount.getText().toString();

                if (strwallet == null || strwallet.length() == 0){
                    editamount.setError("Enter Amount");
                   // Toast.makeText(Activity_Balance.this, "Enter Amount", Toast.LENGTH_SHORT).show();
                }else{

                    Toast.makeText(Activity_Balance.this, "Need to Integrate Payment Gateway for this function", Toast.LENGTH_SHORT).show();


                   /* ConnectionDetector cd = new ConnectionDetector(getActivity());
                    if (cd.isConnectingToInternet()) {
                        new WalletUser().execute();
                    } else {
                        Toast.makeText(getActivity(), "Internet Connection Not Available Enable Internet And Try Again", Toast.LENGTH_LONG).show();
                    }*/


                }
            }
        });

        text100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editamount.setText("100");
            }
        });

        text200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editamount.setText("200");
            }
        });
        text500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editamount.setText("500");
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
    private void withdrawmoney() {
        dialog.setContentView(R.layout.dialog_withdrawmoney);

        ImageView signupcancel = (ImageView) dialog.findViewById(R.id.imgcancel);



        signupcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onClickedItem(int pos, String qty, int status, String name) {
        Log.e("DMen", "Pos:"+ pos + "Qty:"+qty);
        Log.e("testing","status  = "+status);
        Log.e("testing","title inm  = "+mListFeederInfo.get(pos).getFeederName());

        if (qty == null || qty.trim().length() == 0 || qty.trim().equals("null")){

        }else if (qty.trim().equals("6")){
            helpdesk();
        }
    }
    private void helpdesk() {
        dialog.setContentView(R.layout.dialog_helpdesk);

        ImageView signupcancel = (ImageView) dialog.findViewById(R.id.imgcancel);
        LinearLayout linearemailid = (LinearLayout) dialog.findViewById(R.id.linearemailid);
        LinearLayout linearfaq = (LinearLayout) dialog.findViewById(R.id.linearfaq);


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
                dialog.dismiss();
                dialogfaq();
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

                    Toast.makeText(Activity_Balance.this, "Need to Integrate API", Toast.LENGTH_SHORT).show();


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

    private void setUpReccyler() {
        mListFeederInfo =new ArrayList<Pojo_WalletList>();

        for(int i=0;i<rowid.length;i++){
            Pojo_WalletList feedInfo = new Pojo_WalletList();
            feedInfo.setRowid(rowid[i]);
            feedInfo.setFeederName(title[i]);
            feedInfo.setFeederThumbnail(images[i]);
            // feedInfo.setImage(image[i]);
            mListFeederInfo.add(feedInfo);
        }
        adapter = new Adapter_WalletList(Activity_Balance.this,mListFeederInfo, mCallback);
        mFeedRecyler.setAdapter(adapter);




    }



}
