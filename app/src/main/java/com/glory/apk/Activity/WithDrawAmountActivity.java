package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.glory.apk.MainActivity;
import com.glory.apk.Model.WithDrawAmount.WithDrawExample;
import com.glory.apk.R;
import com.glory.apk.Retrofit.Api;
import com.glory.apk.Retrofit.CashPaymentApiClint;
import com.glory.apk.Utilites.sharedPrefs;

public class WithDrawAmountActivity extends AppCompatActivity {
    EditText editamount;
    TextView textwalletsubmit;
    TextView text250, text500, text1000;
    String strwallet;
    private static final String TAG = "AddMoneyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_draw_amount);
        mInitWidgets();
    }

    private void mInitWidgets() {
        editamount = (EditText) findViewById(R.id.editamount);
        textwalletsubmit = (TextView) findViewById(R.id.textwalletsubmit);
        text250 = (TextView) findViewById(R.id.text250);
        text500 = (TextView) findViewById(R.id.text500);
        text1000 = (TextView) findViewById(R.id.text1000);

        textwalletsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strwallet = editamount.getText().toString();

                if (strwallet == null || strwallet.length() == 0) {
                    editamount.setError("Enter Amount");
                    // Toast.makeText(Activity_Balance.this, "Enter Amount", Toast.LENGTH_SHORT).show();
                } else {

                    mCallPaymentToken();


                }
            }
        });
        text250.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editamount.setText("250");
            }
        });

        text500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editamount.setText("500");
            }
        });
        text1000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editamount.setText("1000");
            }
        });
    }

    private void mCallPaymentToken() {
        final ProgressDialog pDialog;
        Log.e("testing", "strregisteredtoken = " + "matchesList");
        pDialog = new ProgressDialog(getApplicationContext());
        pDialog.setMessage("Please Wait ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        String viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);
        Api api = CashPaymentApiClint.getClient().create(Api.class);
        Call<WithDrawExample> login = api.WithDrawAmount(viewuseremail,strwallet);
        login.enqueue(new Callback<WithDrawExample>() {
            @Override
            public void onResponse(Call<WithDrawExample> call, Response<WithDrawExample> response) {
                pDialog.dismiss();
                Log.e("testing", "status = " + response.body().getStatus());
                Log.e("testing", "response = " + response.body().getWithDrawResponse().getMessage());
                //  Log.e("testing","response = "+response.body().getData().getPageContent());
                Log.e("testing", "response = " + response.body());
                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    Log.e("testing", "response = " + response.body().getWithDrawResponse().getMessage());
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Amount Will add in to Your Account Within 12 Hours", Toast.LENGTH_SHORT).show();
                    pDialog.dismiss();

                } else {
                    Log.e("testing", "error");
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.body().getWithDrawResponse().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WithDrawExample> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                pDialog.dismiss();

            }
        });

    }
}
