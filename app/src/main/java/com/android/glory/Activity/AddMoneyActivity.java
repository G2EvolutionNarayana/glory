package com.android.glory.Activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.glory.Model.GenerateToken.GenerateTokenExample;
import com.android.glory.R;
import com.android.glory.Retrofit.Api;
import com.android.glory.Retrofit.CashPaymentApiClint;
import com.android.glory.Utilites.sharedPrefs;
import com.gocashfree.cashfreesdk.CFPaymentService;

import java.util.HashMap;
import java.util.Map;

import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_APP_ID;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_CUSTOMER_EMAIL;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_CUSTOMER_NAME;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_CUSTOMER_PHONE;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_NOTIFY_URL;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_ORDER_AMOUNT;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_ORDER_CURRENCY;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_ORDER_ID;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_ORDER_NOTE;

public class AddMoneyActivity extends AppCompatActivity {
    EditText editamount;
    TextView textwalletsubmit;
    TextView text100, text200, text500;
    String strwallet;
    private static final String TAG = "AddMoneyActivity";
    Dialog Wallatedialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);
        Wallatedialog = new Dialog(AddMoneyActivity.this);
        Wallatedialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mInitWidgets();
    }

    private void mInitWidgets() {

        editamount = (EditText) findViewById(R.id.editamount);
        textwalletsubmit = (TextView) findViewById(R.id.textwalletsubmit);
        text100 = (TextView) findViewById(R.id.text100);
        text200 = (TextView) findViewById(R.id.text200);
        text500 = (TextView) findViewById(R.id.text500);

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
    }

    private void mCallPaymentToken() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");
        final ProgressDialog pDialog;

        pDialog = new ProgressDialog(AddMoneyActivity.this);
        pDialog.setMessage("Please Wait ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        String viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);
        Api api = CashPaymentApiClint.getClient().create(Api.class);
        Call<GenerateTokenExample> login = api.GetCashToken(strwallet, viewuseremail);
//        Call<LiveContestExample> login = api.LivePlayersDetails(matchId, viewuseremail);
        login.enqueue(new Callback<GenerateTokenExample>() {
            @Override
            public void onResponse(Call<GenerateTokenExample> call, Response<GenerateTokenExample> response) {
                pDialog.dismiss();
                Log.e("testing", "status = " + response.body().getStatus());
                Log.e("testing", "response = " + response.body().getMessage());
                //  Log.e("testing","response = "+response.body().getData().getPageContent());
                Log.e("testing", "response = " + response.body());
                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    Log.e("testing", "response = " + response.body().getToken());
//                    orderid= UUID.randomUUID().toString();
//                    orderid=response.body().getOrderId().toString();
                    Map<String, String> dataSend = new HashMap<>();
                    dataSend.put(PARAM_APP_ID, "1446344dd53f698c06f9ce52136441");
                    dataSend.put(PARAM_ORDER_ID, response.body().getOrderId().toString());
                    dataSend.put(PARAM_ORDER_AMOUNT, strwallet);
                    dataSend.put(PARAM_ORDER_CURRENCY, "INR");
                    dataSend.put(PARAM_CUSTOMER_PHONE, "7780602740");
                    dataSend.put(PARAM_CUSTOMER_EMAIL, "lekkalanarayana131@gmail.com");
                    dataSend.put(PARAM_ORDER_NOTE, "Test Order");
                    dataSend.put(PARAM_CUSTOMER_NAME, "John Doe");
                    dataSend.put(PARAM_NOTIFY_URL, "http://glory5.in/glory5/cashfreephp/transactionStatus.php");

//                    CashTsoken=response.body().getToken().toString();
//                    Log.e("testing", "response = " + CashTsoken);

                    CFPaymentService.getCFPaymentServiceInstance().doPayment(AddMoneyActivity.this, dataSend, response.body().getToken().toString(), "TEST", "#784BD2", "#FFFFFF", false);
                    pDialog.dismiss();

                } else {
                    Log.e("testing", "error");
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GenerateTokenExample> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                pDialog.dismiss();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Same request code for all payment APIs.
        Log.d(TAG, "ReqCode : " + CFPaymentService.REQ_CODE);
        Log.d("testing", "ReqCode : " + CFPaymentService.REQ_CODE);
        Log.d("testing", "API Response : ");        //Prints all extras. Replace with app logic.
        if (data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null)
                for (String key : bundle.keySet()) {
                    if (bundle.getString(key) != null) {
                        Log.e("testing", key + " : " + bundle.getString(key));
                        Log.e("testing", "ReqCode : " + CFPaymentService.REQ_CODE);
                        Log.d("testing", "ReqCode : " + CFPaymentService.PARAM_PAYMENT_MODES);
                        Log.e("testing", "API Response : ");
                        Wallatedialog.setContentView(R.layout.payment_dialogue);
                        Wallatedialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                        Button Ok = (Button) Wallatedialog.findViewById(R.id.Ok);
                        TextView paymentText=(TextView)Wallatedialog.findViewById(R.id.paymentText);
                        paymentText.setText("PAYMENT "+bundle.getString(key));
                        Ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Wallatedialog.cancel();
                                Intent intent = new Intent();
                                intent.putExtra("backtoScreen", "true");
                                setResult(RESULT_OK, intent);
                                finish();
//                                finish();

                            }
                        });
                        Wallatedialog.show();
                    }
                }
        }
    }
}
