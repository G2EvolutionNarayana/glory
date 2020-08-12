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

import com.glory.apk.Adapter.TransactionAdapter;
import com.glory.apk.Model.TransactionChildDataModel;
import com.glory.apk.Model.TransactionDataModel;
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


public class ActivityTransactionList extends AppCompatActivity {
    RecyclerView xRecyclerView;
    JSONParserNew jsonParser = new JSONParserNew();
    TransactionDataModel transactionDataModel;
    String trans_code, trans_amount, trans_type,trans_status;
    ArrayList<TransactionChildDataModel> CHILDlIST;
    ArrayList<TransactionDataModel> MainList;
    TransactionAdapter transactionAdapter;
    TextView xTvNoTransactions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_list);
        mInitWidgets();
        new LoadRegister().execute();
    }

    private void mInitWidgets() {
        MainList = new ArrayList<>();
        xTvNoTransactions = findViewById(R.id.xTvNoTransactions);
        xRecyclerView = findViewById(R.id.xRecyclerView);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        xRecyclerView.setLayoutManager(mLayoutManager2);
    }

    public class LoadRegister extends AsyncTask<String, String, String>
            //implements RemoveClickListner
    {


        String status;
        String response;
        String strresponse;
        String strcode, strtype, strmessage;
        private ProgressDialog Dialog;

        Integer intcartcount = 0;
        String strregisteredtoken;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Dialog = new ProgressDialog(ActivityTransactionList.this);
            Dialog.setMessage("Please Wait ...");
            Dialog.setCancelable(false);
            Dialog.show();


        }

        public String doInBackground(String... args) {

            //  product_details_lists = new ArrayList<Product_list>();

            // Retrieve JSON Objects from the given URL address
            List<NameValuePair> userpramas = new ArrayList<NameValuePair>();

            String paramsheader = "Bearer " + "sddf";
            String viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);


            //   userpramas.add(new BasicNameValuePair(EndUrls.Signup_registering_by, "user"));
            userpramas.add(new BasicNameValuePair("user_id", viewuseremail));


            Log.e("testing", "userpramas = " + userpramas);

            String strurl = "https://glory5.in/glory5/api/user/wallet/transactions_list";
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
                                CHILDlIST = new ArrayList<>();
                                for (int i = 0; i < valueArray.length(); i++) {
                                    JSONObject valueArrayjsonObject = valueArray.getJSONObject(i);
                                    trans_code = valueArrayjsonObject.getString("trans_code");
                                    trans_amount = valueArrayjsonObject.getString("trans_amount");
                                    trans_type = valueArrayjsonObject.getString("trans_type");
                                    trans_status = valueArrayjsonObject.getString("trans_status");

                                    TransactionChildDataModel transactionChildDataModel = new TransactionChildDataModel(trans_code, trans_amount, trans_type,trans_status);
                                    CHILDlIST.add(transactionChildDataModel);
                                }
                                transactionDataModel = new TransactionDataModel(key, CHILDlIST);

                                MainList.add(transactionDataModel);

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


            Dialog.dismiss();

            if (status == null || status.trim().length() == 0 || status.equals("null")) {


            } else if (status.equals("success")) {

                if (MainList.size() == 0) {
                    xTvNoTransactions.setVisibility(View.VISIBLE);
                    xRecyclerView.setVisibility(View.INVISIBLE);
                } else {
                    xTvNoTransactions.setVisibility(View.INVISIBLE);
                    xRecyclerView.setVisibility(View.VISIBLE);
                    Log.e("testing",String.valueOf(MainList.size()));
                    transactionAdapter = new TransactionAdapter(getApplicationContext(), MainList);
                    xRecyclerView.setAdapter(transactionAdapter);
                }


            } else {

                xTvNoTransactions.setVisibility(View.VISIBLE);
                xRecyclerView.setVisibility(View.INVISIBLE);
            }


        }


    }
}
