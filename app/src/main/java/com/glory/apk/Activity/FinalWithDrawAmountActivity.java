package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.glory.apk.R;
import com.glory.apk.Retrofit.Api;
import com.glory.apk.Retrofit.ApiClient;
import com.glory.apk.Utilites.StaticUtils;
import com.glory.apk.Utilites.sharedPrefs;
import com.glory.apk.Model.FinalWithDrawAmount.Example;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinalWithDrawAmountActivity extends AppCompatActivity {
    private TextView xTvFaq;
    private TextView xTvBankName, xTvAccountNumber, xTvWithDraw;
    private EditText xEtWithDrawAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_with_draw_amount);
        mInitWidgets();
    }

    private void mInitWidgets() {
        xTvFaq = (TextView) findViewById(R.id.xTvFaq);
        xTvBankName = findViewById(R.id.xTvBankName);
        xTvAccountNumber = findViewById(R.id.xTvAccountNumber);
        xTvWithDraw = findViewById(R.id.xTvWithDraw);

        xEtWithDrawAmount = findViewById(R.id.xEtWithDrawAmount);

        xTvBankName.setText(getIntent().getStringExtra(StaticUtils.BRANCH_NAME));
        String str = getIntent().getStringExtra(StaticUtils.ACCOUNT_NUMBER);
//        str = "1234 3474 43434 w34324";
        String main = "";
        char[] ch = str.toCharArray();
        for (int i = 0; i <= str.length(); i++) {
            if (i > str.length() - 4) {
                main = main + String.valueOf(ch[i - 1]);

            } else if (i < 4) {
                main = main + String.valueOf(ch[i]);

            } else {
                main = main + "*";
            }
        }
        xTvAccountNumber.setText(main);
        if ((sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Wallet_Amount) == null || sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.AVAILABLE_WITHDRAW).isEmpty())) {
            xTvFaq.setText("You have \u20B9 + 0+.00 winning amount available..");
        } else {
            xTvFaq.setText("You have \u20B9 " + sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.AVAILABLE_WITHDRAW) + " winning amount available..");
        }
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        xTvWithDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float availableAmount = Float.parseFloat(sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.AVAILABLE_WITHDRAW).toString());

                if (xEtWithDrawAmount.getText().toString().isEmpty() || xEtWithDrawAmount.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter withdraw amount", Toast.LENGTH_LONG).show();
                } else if (Integer.parseInt(xEtWithDrawAmount.getText().toString().trim()) > availableAmount) {
                    Toast.makeText(getApplicationContext(), "Withdrawal amount requested is higher than the available winnings for withdrawal", Toast.LENGTH_LONG).show();
                } else if (Integer.parseInt(xEtWithDrawAmount.getText().toString().trim()) < 150) {
                    Toast.makeText(getApplicationContext(), "Minimum withdraw amount is \u20B9 150", Toast.LENGTH_LONG).show();

                } else {
                    mFinalWithDrawCall(availableAmount,Integer.parseInt(xEtWithDrawAmount.getText().toString().trim()));
                }
            }
        });

    }

    private void mFinalWithDrawCall(float availableAmount, int withdrawamount) {

        Log.e("testing", "strregisteredtoken = " + "matchesList");
        ProgressDialog pDialog;
        pDialog = new ProgressDialog(FinalWithDrawAmountActivity.this);
        pDialog.setMessage("Please Wait ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        Api api = ApiClient.getClient().create(Api.class);
        Call<Example> login = api.FinalWithDraw(sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId).toString(), xEtWithDrawAmount.getText().toString().trim());
//        Call<Example> login = api.FinalWithDraw("44", xEtWithDrawAmount.getText().toString().trim());

        login.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                pDialog.dismiss();

                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    finish();
                    Log.e("testing", "status = " + response.body().getStatus());
                    Log.e("testing", "response = " + response.body().getResponse().getType());
                    //  Log.e("testing","response = "+response.body().getData().getPageContent());

                    Log.e("testing", "response = " + response.body());
                    if (response.body().getResponse() == null) {

                    } else if (response.body().getResponse().getType().equals("save_success")) {

                        if ((response.body().getData().getRemWinningAmount() == null)) {
                            sharedPrefs.savepref(getApplicationContext(), sharedPrefs.AVAILABLE_WITHDRAW,"0");

                        } else {
                            sharedPrefs.savepref(getApplicationContext(), sharedPrefs.AVAILABLE_WITHDRAW,response.body().getData().getRemWinningAmount());
                            pDialog.dismiss();
                        }

                        if ((response.body().getData().getTotal_wallet_balance() == null)) {
                            sharedPrefs.savepref(getApplicationContext(), sharedPrefs.Wallet_Amount, "0");

                        } else {
                            sharedPrefs.savepref(getApplicationContext(), sharedPrefs.Wallet_Amount,response.body().getData().getTotal_wallet_balance());

                        }

                        Toast.makeText(getApplicationContext(), "Your withdraw request is received. Kindly wait till the request is processed.", Toast.LENGTH_SHORT).show();


                    } else {
//                        Toast.makeText(getApplicationContext(), "Withdrawal amount requested is higher than the available winnings for withdrawal", Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), response.body().getResponse().getMessage(), Toast.LENGTH_LONG).show();

                        pDialog.dismiss();
                    }
                } else {
                    Log.e("testing", "error");
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.body().getResponse().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                pDialog.dismiss();
            }
        });
    }
}
