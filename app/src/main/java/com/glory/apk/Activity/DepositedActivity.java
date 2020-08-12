package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.glory.apk.Adapter.DepositedAmountAdapter;
import com.glory.apk.Model.DepositedChildModel;
import com.glory.apk.Model.DepositedModel;
import com.glory.apk.R;
import com.glory.apk.Utilites.JSONParserNew;
import com.glory.apk.Utilites.sharedPrefs;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DepositedActivity extends AppCompatActivity {
    RecyclerView xRecyclerView;

    JSONParserNew jsonParser = new JSONParserNew();
    DepositedModel DepositedModel;
    String order_id, trans_amount, bank_ref_no, payment_mode, Pamentstatus;
    ArrayList<DepositedChildModel> CHILDlIST;
    ArrayList<DepositedModel> MainList;
    DepositedAmountAdapter transactionAdapter;
    TextView xTvNoDeposites;
//    private ProgressDialog DepositeDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposited);
        mInitWidgets();
        new LoadRegister().execute();

    }

    private void mInitWidgets() {

        MainList = new ArrayList<>();
        xRecyclerView = findViewById(R.id.xRecyclerView);
        xTvNoDeposites = findViewById(R.id.xTvNoDeposites);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        xRecyclerView.setLayoutManager(mLayoutManager2);
    }

    public class LoadRegister extends AsyncTask<String, String, String> {


        String status;
        String response;
        String strresponse;
        String strcode, strtype, strmessage;

        Integer intcartcount = 0;
        String strregisteredtoken;
        private ProgressDialog DepositeDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            DepositeDialog = new ProgressDialog(DepositedActivity.this);
            DepositeDialog.setMessage("Please Wait ...");
            DepositeDialog.setIndeterminate(true);
            DepositeDialog.setCancelable(false);
            DepositeDialog.show();

        }

        public String doInBackground(String... args) {

            //  product_details_lists = new ArrayList<Product_list>();

            // Retrieve JSON Objects from the given URL address
            String viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);
            List<NameValuePair> userpramas = new ArrayList<NameValuePair>();
            //   userpramas.add(new BasicNameValuePair(EndUrls.Signup_registering_by, "user"));
            userpramas.add(new BasicNameValuePair("user_id", viewuseremail));


            Log.e("testing", "userpramas = " + userpramas);

            String strurl = "https://glory5.in/glory5/api/user/wallet/deposited_list";
            Log.e("testing", "strurl = " + strurl);
            JSONObject json = jsonParser.makeHttpRequest(strurl, "GET", userpramas);


            if (json == null) {
                Log.e("testing", "json result = " + json);

            } else {
                Log.e("testing", "jon2222222222222");
                try {


                    status = json.getString("status");
                    strresponse = json.getString("response");
                    JSONObject jsonobject = new JSONObject(strresponse);
                    strcode = jsonobject.getString("code");
                    strtype = jsonobject.getString("type");
                    strmessage = jsonobject.getString("message");
                    String data = json.getString("data");
                    JSONObject dataobject = new JSONObject(data);
                    Log.e("testing", "json status = " + status);

                    if (status.equals("success")) {

                        status = json.getString("status");
                        strresponse = json.getString("response");
                        if (status.equals("success")) {
                            status = json.getString("status");
                            strresponse = json.getString("response");
                            String strdata = json.getString("data");
                            Log.e("testing", "json status = " + status);
                            Log.e("testing", "strdata = " + strdata);

//                            JSONArray jsonArray = new JSONArray(strdata);
                            JSONObject jsonObject = new JSONObject(strdata);
                            Iterator iter = jsonObject.keys();
//                            Log.e("testing", "jsonArray = " + jsonArray);

                            while (iter.hasNext()) {
                                CHILDlIST = new ArrayList<>();

                                String key = (String) iter.next();
                                String value = jsonObject.getString(key);
                                Log.e("testing", "" + key);
                                Log.e("testing", "" + value);
                                JSONArray valueArray = new JSONArray(value);
                                for (int i = 0; i < valueArray.length(); i++) {
                                    JSONObject valueArrayjsonObject = valueArray.getJSONObject(i);
                                    order_id = String.valueOf(valueArrayjsonObject.get("order_id"));
                                    trans_amount = valueArrayjsonObject.getString("trans_amount");
                                    bank_ref_no = valueArrayjsonObject.getString("bank_ref_no");
                                    payment_mode = valueArrayjsonObject.getString("payment_mode");
                                    Pamentstatus = valueArrayjsonObject.getString("trans_status");
                                    DepositedChildModel winningChildDataModel = new DepositedChildModel(trans_amount, order_id, bank_ref_no, payment_mode, Pamentstatus);
                                    CHILDlIST.add(winningChildDataModel);
                                }
                                DepositedModel = new DepositedModel(key, CHILDlIST);
                                MainList.add(DepositedModel);

                            }

                        }

                    } else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


            return response;


        }

        @Override
        protected void onPostExecute(String responce) {
            super.onPostExecute(responce);


            DepositeDialog.dismiss();

            if (status == null || status.trim().length() == 0 || status.equals("null")) {


            } else if (status.equals("success")) {
                if (MainList.size() == 0) {
                    xTvNoDeposites.setVisibility(View.VISIBLE);
                    xRecyclerView.setVisibility(View.INVISIBLE);
                } else {
                    xTvNoDeposites.setVisibility(View.INVISIBLE);
                    xRecyclerView.setVisibility(View.VISIBLE);
                    transactionAdapter = new DepositedAmountAdapter(getApplicationContext(), MainList);
                    xRecyclerView.setAdapter(transactionAdapter);
                }


            } else {
                xTvNoDeposites.setVisibility(View.VISIBLE);
                xRecyclerView.setVisibility(View.INVISIBLE);

            }


        }


    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}
