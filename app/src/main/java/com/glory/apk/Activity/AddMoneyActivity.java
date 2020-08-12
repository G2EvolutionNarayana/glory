package com.glory.apk.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.glory.apk.Model.GenerateToken.GenerateTokenExample;
import com.glory.apk.Model.WallateAmount.Example;
import com.glory.apk.R;
import com.glory.apk.Retrofit.Api;
import com.glory.apk.Retrofit.ApiClient;
import com.glory.apk.Retrofit.CashPaymentApiClint;
import com.glory.apk.Retrofit.UpdateProfile.UpdateprofileJson;
import com.glory.apk.Utilites.sharedPrefs;
import com.gocashfree.cashfreesdk.CFPaymentService;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    String strwallet, order_id, trans_amount, transaction_id, tracking_id, bank_ref_no, payment_mode, trans_message, trans_datetime, trans_status, status;
    private static final String TAG = "AddMoneyActivity";
    Dialog Wallatedialog;
    String phoneNumber;
    Dialog mobilekDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);
        Wallatedialog = new Dialog(AddMoneyActivity.this);
        Wallatedialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mInitWidgets();
    }

    private void mInitWidgets() {

        CFPaymentService cfPaymentService = CFPaymentService.getCFPaymentServiceInstance();
        cfPaymentService.setOrientation(0);

        trans_amount = "";
        strwallet = "";
        order_id = "";
        bank_ref_no = "";
        trans_amount = "";
        payment_mode = "";
        trans_message = "";
        trans_datetime = "";
        trans_status = "";

        mobilekDialog = new Dialog(AddMoneyActivity.this);
        mobilekDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
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
                }
//                else if(Integer.parseInt(strwallet)<10) {
//                    editamount.setError("Minimum Add amount is 10 ");
//
//
//                }
                else {
                    if (sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.PhoneNumber).length() == 0 || sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.PhoneNumber).isEmpty()) {

                        Wallatedialog.setContentView(R.layout.mobile_number_verification_dialog);
                        Wallatedialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        TextView phoneContinue = (TextView) Wallatedialog.findViewById(R.id.phoneContinue);

                        ImageView imgcancel = (ImageView) Wallatedialog.findViewById(R.id.xIvCancel);
                        EditText editmobileno = (EditText) Wallatedialog.findViewById(R.id.editmobileno);

                        phoneContinue.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                if (editmobileno.getText().toString().isEmpty()) {

                                    Toast.makeText(getApplicationContext(), "Please enter your mobile number", Toast.LENGTH_LONG);


                                } else if (editmobileno.getText().toString().length() != 10) {
                                    Toast.makeText(getApplicationContext(), "Please enter your Correct mobile number", Toast.LENGTH_LONG);
                                } else {
                                    mCallVerifyPhone(editmobileno.getText().toString());
                                }

                            }


                        });

                        imgcancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Wallatedialog.cancel();
                            }
                        });
                        Window window = Wallatedialog.getWindow();
                        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                        Wallatedialog.setCancelable(false);
                        Wallatedialog.show();


                    } else {
                        phoneNumber = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.PhoneNumber);
                        mCallPaymentToken();
                    }
                }
            }

            private void mCallVerifyPhone(String phone) {

                Log.e("testing", "strregisteredtoken = " + "matchesList");
                final ProgressDialog dialog;
                dialog = new ProgressDialog(AddMoneyActivity.this);
                dialog.setMessage("Please Wait ...");
                dialog.setCancelable(false);
                dialog.show();

                String viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);
                Log.e("testing", "viewuseremail = " + viewuseremail);

                Api api = ApiClient.getClient().create(Api.class);

                Call<UpdateprofileJson> login = api.updatePhoneProfile(viewuseremail, phone);
                login.enqueue(new Callback<UpdateprofileJson>() {
                    @Override
                    public void onResponse(Call<UpdateprofileJson> call, Response<UpdateprofileJson> response) {
                        dialog.dismiss();
                        Log.e("testing", "status = " + response.body().getStatus());
                        Log.e("testing", "response = " + response.body().getResponse().getType());
                        //  Log.e("testing","response = "+response.body().getData().getPageContent());

                        Log.e("testing", "response = " + response.body());

                        if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                        } else if (response.body().getStatus().equals("success")) {
                            if (response.body().getResponse() == null) {

                            } else if (response.body().getResponse().getType().equals("update_success")) {
                                mCallPaymentToken();
                                Wallatedialog.cancel();
                                sharedPrefs.savepref(getApplicationContext(), sharedPrefs.PhoneNumber, phone);
                                dialog.dismiss();

                            } else {
                                Toast.makeText(getApplicationContext(), response.body().getResponse().getType(), Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                Wallatedialog.cancel();

                            }
                        } else {
                            Log.e("testing", "error");
                            dialog.dismiss();
                            Wallatedialog.cancel();

                            Toast.makeText(getApplicationContext(), response.body().getResponse().getType(), Toast.LENGTH_SHORT).show();
                        }
                    }


                    @Override
                    public void onFailure(Call<UpdateprofileJson> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                    }
                });
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

        final ProgressDialog pDialog;
        pDialog = new ProgressDialog(AddMoneyActivity.this);
        pDialog.setMessage("Please Wait ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        String viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);

        Api api = CashPaymentApiClint.getClient().create(Api.class);

        Call<GenerateTokenExample> login = api.GetCashToken(strwallet, viewuseremail);

        login.enqueue(new Callback<GenerateTokenExample>() {
            @Override
            public void onResponse(Call<GenerateTokenExample> call, Response<GenerateTokenExample> response) {
                pDialog.dismiss();
                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {

                    Log.e("testing", "response = " + sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.PhoneNumber));

                    String orderid = response.body().getOrderId().toString();
                    String phoneNumber = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.PhoneNumber);
                    String appid = "45081e3e8fb85fd80da8ec82818054";
                    String orderAmount = strwallet;
                    String currency = "INR";
                    String notifyUrl = "https://glory5.in/glory5/cashfreephp/transactionStatus.php";

                    Map<String, String> dataSend = new HashMap<>();
                    dataSend.put(PARAM_APP_ID, appid);
                    dataSend.put(PARAM_ORDER_ID, orderid);
                    dataSend.put(PARAM_ORDER_AMOUNT, orderAmount);
                    dataSend.put(PARAM_ORDER_CURRENCY, currency);
                    dataSend.put(PARAM_CUSTOMER_PHONE, phoneNumber);
                    dataSend.put(PARAM_CUSTOMER_EMAIL, "lekkalanarayana131@gmail.com");
                    dataSend.put(PARAM_ORDER_NOTE, "Test Order");
                    dataSend.put(PARAM_CUSTOMER_NAME, sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.USER_NAME));
                    dataSend.put(PARAM_NOTIFY_URL, notifyUrl);

                    CFPaymentService.getCFPaymentServiceInstance().doPayment(AddMoneyActivity.this, dataSend, response.body().getToken().toString(), "PROD", "#784BD2", "#FFFFFF", false);
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
                        if (key.equals("orderId")) {
                            order_id = bundle.getString(key);
                        }
                        if (key.equals("paymentMode")) {
                            payment_mode = bundle.getString(key);
                        }
                        if (key.equals("txTime")) {
                            trans_datetime = bundle.getString(key);
                        }
                        if (key.equals("referenceId")) {
                            bank_ref_no = bundle.getString(key);
                        }
                        if (key.equals("txMsg")) {
                            trans_message = bundle.getString(key);
                        }
                        if (key.equals("orderAmount")) {
                            trans_amount = bundle.getString(key);

                        }
                        if (key.equals("txStatus")) {
                            trans_status = bundle.getString(key);
                        }
                    }
                }

//            if (trans_status.equals("SUCCESS")) {
//                mStoreWallateAmount();
//            } else {

            Intent intent = new Intent();
            intent.putExtra("backtoScreen", "true");
            setResult(RESULT_OK, intent);
            finish();
//            }
        }
    }

    private void mStoreWallateAmount() {
        ProgressDialog pDialog;
        pDialog = new ProgressDialog(AddMoneyActivity.this);
        pDialog.setMessage("Please Wait ...");
        pDialog.setCancelable(false);
        pDialog.show();
        String viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);
        Log.e("testing", "viewuseremail " + viewuseremail + "order_id " + order_id + "trans_amount " + trans_amount + "bank_ref_no " + bank_ref_no + "payment_mode " + payment_mode + "trans_message " + trans_message + "trans_datetime " + trans_datetime + "trans_status " + trans_status + "trans_message " + trans_message);

        Api api = ApiClient.getClient().create(Api.class);
        Call<Example> login = api.WallateAmountStore(viewuseremail, order_id, trans_amount, " ", "", bank_ref_no, payment_mode, trans_message, trans_datetime, trans_status, trans_message);
//        Log.e("testing", "status = " + MatchId);

        login.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                pDialog.dismiss();

                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {
                    Log.e("testing", "status = " + response.body().getStatus());
                    Log.e("testing", "response = " + response.body().getResponse());
                    //  Log.e("testing","response = "+response.body().getData().getPageContent());
                    Log.e("testing", "response = " + response.body());
                } else if (response.body().getStatus().equals("success")) {
                    if (response.body().getResponse().getType().equals("save_success")) {
                        Wallatedialog.setContentView(R.layout.payment_dialogue);
                        Wallatedialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//paymentMode,orderId,txTime,referenceId,type,txMsg,signature,orderAmount,txStatus,
                        Button Ok = (Button) Wallatedialog.findViewById(R.id.Ok);
                        TextView paymentText = (TextView) Wallatedialog.findViewById(R.id.paymentText);
                        paymentText.setText("PAYMENT " + trans_status);
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

                    } else {
//                        Toast.makeText(getApplicationContext(), response.body().getContestResponse().getType(), Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();

                    }

                } else {
                    Log.e("testing", "error");
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.body().getResponse().getMessage(), Toast.LENGTH_SHORT).show();
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
