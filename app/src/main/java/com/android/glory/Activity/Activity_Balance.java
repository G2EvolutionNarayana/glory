package com.android.glory.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.Dialog;
import android.app.ProgressDialog;
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
import com.android.glory.Adapter.LiveAdapter;
import com.android.glory.Model.AboutUs.AboutExample;
import com.android.glory.Model.GenerateToken.GenerateTokenExample;
import com.android.glory.Model.LiveContestListModel.LiveContestDatum;
import com.android.glory.Model.LiveContestListModel.LiveContestExample;
import com.android.glory.Pojo.Pojo_WalletList;
import com.android.glory.R;
import com.android.glory.Retrofit.Api;
import com.android.glory.Retrofit.ApiClient;
import com.android.glory.Retrofit.CashPaymentApiClint;
import com.android.glory.Utilites.sharedPrefs;
import com.bumptech.glide.Glide;
import com.gocashfree.cashfreesdk.CFPaymentService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_APP_ID;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_CUSTOMER_EMAIL;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_CUSTOMER_NAME;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_CUSTOMER_PHONE;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_ORDER_AMOUNT;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_ORDER_CURRENCY;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_ORDER_ID;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_ORDER_NOTE;

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
    String CashTsoken;
    String orderid;
    private ProgressDialog wallatedialog;
//    private ProgressDialog dialog;

    TextView textaddmoney, textwithdrawmoney;
    private RecyclerView mFeedRecyler;
    private ArrayList<Pojo_WalletList> mListFeederInfo;
    private Adapter_WalletList adapter;
    Adapter_WalletList mAdapterFeeds;
    private GridLayoutManager lLayout;
    private Adapter_WalletList.OnItemClick mCallback;
    String image;
    ImageView xIvImage;
    private static final String TAG = "Activity_Balance";

    CompositeDisposable compositeDisposable=new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        String strrs = getResources().getString(R.string.Rs);
        textwallet = (TextView) findViewById(R.id.textwallet);
        xIvImage=(ImageView)findViewById(R.id.xIvImage);
        if ((sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Wallet_Amount)==null ||sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Wallet_Amount).isEmpty())){
            textwallet.setText("\u20B9"+"0");

        }else {
            textwallet.setText(strrs+" "+sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Wallet_Amount));
                Log.e("testing", "status = " + sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Wallet_Amount));

        }
        image=sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Profile_Image);

        if (image == null || image.length() == 0) {
            Glide.with(getApplicationContext())
                    .load((R.drawable.user)).placeholder(R.drawable.user)
                    .into(xIvImage);
            Log.e("testing", "getImageUrl = " + "null");

        } else {
            Glide.with(getApplicationContext())
                    .load(Uri.parse(image))
                    .error(R.drawable.user)
                    .into(xIvImage);
            Log.e("testing", "getImageUrl = " + "image");

        }
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

                Intent intent=new Intent(getApplicationContext(),AddMoneyActivity.class);
                startActivityForResult(intent, 1);
//                mCallPaymentToken();

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

//                    mCallPaymentToken();
                    dialog.dismiss();
//                    Toast.makeText(Activity_Balance.this, "Need to Integrate Payment Gateway for this function", Toast.LENGTH_SHORT).show();


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

        Intent intent=new Intent(getApplicationContext(),AccountVerifyActivity.class);
        startActivity(intent);

    }
//


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
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", strmail, null));
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


    @Override
    protected void onResume() {
        super.onResume();
    }

    private void Activity_Profile() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");

        wallatedialog = new ProgressDialog(Activity_Balance.this);
        wallatedialog.setMessage("Please Wait ...");
//        wallatedialog.setIndeterminate(false);
        wallatedialog.setCancelable(false);
        wallatedialog.show();

//        Api api = ApiClient.getClient().create(Api.class);
//        Call<AboutExample> login = api.PlayersList("1");


        String viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);
        Log.e("testing", "viewuseremail = " + viewuseremail);

        Api api = ApiClient.getClient().create(Api.class);
        Call<AboutExample> login = api.aboutusjson(viewuseremail);
        login.enqueue(new Callback<AboutExample>() {
            @Override
            public void onResponse(Call<AboutExample> call, Response<AboutExample> response) {
                wallatedialog.dismiss();

                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    if (response.body().getAboutResponse() == null) {

                    } else if (response.body().getAboutResponse().getType().equals("data_available")) {
                        Log.e("testing", "status = " + response.body().getStatus());
                        Log.e("testing", "response = " + response.body().getAboutResponse().getType());
                        //  Log.e("testing","response = "+response.body().getData().getPageContent());

                        Log.e("testing", "response = " + response.body());
                        if ((response.body().getAboutData().getWalletAmount()==null)){
                            textwallet.setText("\u20B9"+"0");

                        }else {
                            textwallet.setText(("\u20B9"+" "+response.body().getAboutData().getWalletAmount().toString()));
                            sharedPrefs.savepref(getApplicationContext(), sharedPrefs.Wallet_Amount, response.body().getAboutData().getWalletAmount().toString());

                        }
//                        textdob.setText(Html.fromHtml(response.body().getAboutData().get()));
//                        editname.setText(Html.fromHtml(response.body().getAboutData().getName()));

                    } else {
                        Toast.makeText(Activity_Balance.this, response.body().getAboutResponse().getType(), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Log.e("testing", "error");
                    wallatedialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.body().getAboutResponse().getType(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<AboutExample> call, Throwable t) {
                Toast.makeText(getApplicationContext(), String.valueOf(t.getLocalizedMessage()), Toast.LENGTH_SHORT).show();
                wallatedialog.dismiss();

            }
        });
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//
//    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                if (data.getStringExtra("backtoScreen").equals("true")){
                    Activity_Profile();

                }
            }
        }
    }

}
