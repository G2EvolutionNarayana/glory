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

import com.glory.apk.Adapter.WinningAdapter;
import com.glory.apk.Model.WinningChildDataModel;
import com.glory.apk.Model.WinningDataModel;
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

public class WinningListActivity extends AppCompatActivity {
    RecyclerView xRecyclerViewWinning;
    JSONParserNew jsonParser = new JSONParserNew();
    WinningDataModel winningDataModel;
    String trans_code, trans_amount, trans_type;
    ArrayList<WinningChildDataModel> CHILDlIST;
    ArrayList<WinningDataModel> MainList;
    WinningAdapter transactionAdapter;
    TextView xTvNoWinnings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winning_list);
        mInitWidgets();
        new LoadRegister().execute();
    }

    private void mInitWidgets() {
        MainList = new ArrayList<>();
        xRecyclerViewWinning = findViewById(R.id.xRecyclerViewWinning);
        xTvNoWinnings = findViewById(R.id.xTvNoWinnings);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        xRecyclerViewWinning.setLayoutManager(mLayoutManager2);
    }

    public class LoadRegister extends AsyncTask<String, String, String> {


        String status;
        String response;
        String strresponse;
        String strcode, strtype, strmessage;
        private ProgressDialog WinningDialog;

        Integer intcartcount = 0;
        String strregisteredtoken;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            WinningDialog = new ProgressDialog(WinningListActivity.this);
            WinningDialog.setMessage("Please Wait ...");
//            WinningDialog.setIndeterminate(false);
            WinningDialog.setCancelable(false);
            WinningDialog.show();


        }

        public String doInBackground(String... args) {

            //  product_details_lists = new ArrayList<Product_list>();

            // Retrieve JSON Objects from the given URL address
            List<NameValuePair> userpramas = new ArrayList<NameValuePair>();
            String viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);

            userpramas.add(new BasicNameValuePair("user_id", viewuseremail));
            String strurl = "https://glory5.in/glory5/api/user/wallet/winnings_list";
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
                                    trans_code = valueArrayjsonObject.getString("trans_code");
                                    trans_amount = valueArrayjsonObject.getString("trans_amount");
                                    trans_type = valueArrayjsonObject.getString("trans_type");
                                    WinningChildDataModel winningChildDataModel = new WinningChildDataModel(trans_code, trans_amount, trans_type);
                                    CHILDlIST.add(winningChildDataModel);
                                }
                                winningDataModel = new WinningDataModel(key, CHILDlIST);

                                MainList.add(winningDataModel);

                            }

                        }

                    } else {

                        xRecyclerViewWinning.setVisibility(View.INVISIBLE);
                        xTvNoWinnings.setVisibility(View.VISIBLE);

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


            WinningDialog.dismiss();

            if (status == null || status.trim().length() == 0 || status.equals("null")) {


            } else if (status.equals("success")) {
                xRecyclerViewWinning.setVisibility(View.VISIBLE);
                xTvNoWinnings.setVisibility(View.INVISIBLE);
                if (MainList.size() == 0) {
                    xRecyclerViewWinning.setVisibility(View.INVISIBLE);
                    xTvNoWinnings.setVisibility(View.VISIBLE);
                } else {
                    xRecyclerViewWinning.setVisibility(View.VISIBLE);
                    xTvNoWinnings.setVisibility(View.INVISIBLE);

                    transactionAdapter = new WinningAdapter(getApplicationContext(), MainList);
                    xRecyclerViewWinning.setAdapter(transactionAdapter);
                }
            } else {
                xRecyclerViewWinning.setVisibility(View.INVISIBLE);
                xTvNoWinnings.setVisibility(View.VISIBLE);

            }


        }


    }
}
