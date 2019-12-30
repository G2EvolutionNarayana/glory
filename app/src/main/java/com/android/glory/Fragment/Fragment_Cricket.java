package com.android.glory.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.glory.Activity.Activity_AboutUs;
import com.android.glory.Activity.Activity_CricketList;
import com.android.glory.Adapter.Adapter_Cricket;
import com.android.glory.Pojo.Entity_Cricket;
import com.android.glory.R;
import com.android.glory.Retrofit.Aboutus.AboutusJson;
import com.android.glory.Retrofit.Api;
import com.android.glory.Retrofit.ApiClient;
import com.android.glory.Retrofit.CricketList.Match;
import com.android.glory.Retrofit.CricketList.cricketlistjson;
import com.android.glory.Utilites.ConnectionDetector;
import com.android.glory.Utilites.EndUrls;
import com.android.glory.Utilites.JSONParser;
import com.android.glory.Utilites.sharedPrefs;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Cricket extends Fragment implements Adapter_Cricket.OnItemClickcourses{

    RecyclerView recyclercricket;
    String [] strtitle = new String[]{"MARSH CUP MATCH 15", "MARSH CUP MATCH 15","MARSH CUP MATCH 15","MARSH CUP MATCH 15","MARSH CUP MATCH 15"};
    String [] strtitle1 = new String[]{"NSW", "SAU","IND","KAR","MRW"};
    String [] strtitle2 = new String[]{"TAS", "QUN","PAK","CHA","PSW"};
    Integer []courseImage1 = new Integer[]{R.drawable.icon1,R.drawable.icon1, R.drawable.icon1, R.drawable.icon1,R.drawable.icon1};
    Integer []courseImage2 = new Integer[]{R.drawable.icon1,R.drawable.icon1, R.drawable.icon1, R.drawable.icon1,R.drawable.icon1};
    Adapter_Cricket cricket_Adapter;
    Adapter_Cricket.OnItemClickcourses mCallback2;

    private ArrayList<Entity_Cricket> allItemscricket = new ArrayList<Entity_Cricket>();
    private List<Match> allItemscricket2 = new ArrayList<Match>();
    JSONParser jsonParser = new JSONParser();
    String strregisteredtoken;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_cricket, container, false);
        recyclercricket = (RecyclerView) rootView.findViewById(R.id.recyclerview);

        SharedPreferences prefuserdata3 = getActivity().getSharedPreferences(sharedPrefs.Pref, 0);
        strregisteredtoken = prefuserdata3.getString(sharedPrefs.Pref_token, "");

        recyclercricket.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclercricket.setLayoutManager(mLayoutManager2);
        mCallback2 = this;

      //  setUpRecyler2();

        ConnectionDetector cd = new ConnectionDetector(getActivity());
        if (cd.isConnectingToInternet()) {
          //  new LoadCricketList().execute();
            RetrofitCricketlist();
        } else {
            Toast.makeText(getActivity(), "Internet Connection Not Available Enable Internet And Try Again", Toast.LENGTH_LONG).show();
        }



        return rootView;
    }
    private void setUpRecyler2() {
        allItemscricket =new ArrayList<Entity_Cricket>();

        for(int i=0;i<strtitle.length;i++){
            Entity_Cricket feedInfo = new Entity_Cricket();
            feedInfo.setProductName(strtitle[i]);
            allItemscricket.add(feedInfo);
        }
        /*cricket_Adapter = new Adapter_Cricket(getActivity(),allItemscricket, mCallback2);
        recyclercricket.setAdapter(cricket_Adapter);*/
    }

    @Override
    public void OnItemClickcourses(int pos, String qty, String sub_category_id, int status) {

        Intent intent = new Intent(getActivity(), Activity_CricketList.class);

        startActivity(intent);

    }


    public class LoadCricketList extends AsyncTask<String, String, String> {
        String responce;
        String message;
        String headers;
        String childs;
        String Result;

        String status;
        String strresponse;
        String strdata;
        String strcode, strtype, strmessage;

        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Loading");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();

        }

        protected String doInBackground(String... args) {
            Integer result = 0;
            List<NameValuePair> userpramas = new ArrayList<NameValuePair>();

            allItemscricket =new ArrayList<Entity_Cricket>();

            Log.e("testing", "jsonParser startedkljhk");
            //userpramas.add(new BasicNameValuePair("feader_reg_id", id));
            //  Log.e("testing", "feader_reg_id" + id);
            String paramsheader = "Bearer "+strregisteredtoken;
            JSONObject json = jsonParser.makeHttpRequest(EndUrls.cricketlist_URL, "GET", userpramas, paramsheader);

            Log.e("testing", "jsonParser" + json);


            if (json == null) {

                Log.e("testing", "jon11111111111111111");
                // Toast.makeText(getActivity(),"Data is not Found",Toast.LENGTH_LONG);

                return responce;
            } else {
                Log.e("testing", "jon2222222222222");
                // DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {

                    status = json.getString("status");
                    strresponse = json.getString("response");
                    JSONObject  jsonobject = new JSONObject(strresponse);
                    strcode = jsonobject.getString("code");
                    strtype = jsonobject.getString("type");
                    strmessage = jsonobject.getString("message");
                    if (status.equals("success")) {
                        status = json.getString("status");
                        strresponse = json.getString("response");
                        strdata = json.getString("data");
                        JSONObject  jsonobjectdata = new JSONObject(strdata);
                        String strmatchlist = jsonobjectdata.getString("matchList");
                        JSONObject  jsonobjectmatchlist = new JSONObject(strmatchlist);
                        String strmatches = jsonobjectmatchlist.getString("matches");

                        JSONArray responcearray = new JSONArray(strmatches);
                        Log.e("testing", "responcearray value=" + responcearray);

                        for (int i = 0; i < responcearray.length(); i++) {

                            JSONObject post = responcearray.getJSONObject(i);

                            String strstatus = post.getString("status");
                            if (strstatus == null || strstatus.trim().length() == 0 ){

                            }else if (strstatus.trim().equals("UPCOMING")){

                                String strseries = post.getString("series");
                                JSONObject  jsonobjectseries = new JSONObject(strseries);

                                String strhometeam = post.getString("homeTeam");
                                JSONObject  jsonobjecthometeam = new JSONObject(strhometeam);

                                String strawayTeam = post.getString("awayTeam");
                                JSONObject  jsonobjectawayTeam = new JSONObject(strawayTeam);

                                Entity_Cricket feedInfo = new Entity_Cricket();
                                feedInfo.setSeriesid(jsonobjectseries.getString("id"));
                                feedInfo.setSeriesname(jsonobjectseries.getString("name"));
                                feedInfo.setSeriesshortname(jsonobjectseries.getString("shortName"));


                                feedInfo.setHometeamid(jsonobjecthometeam.getString("id"));
                                feedInfo.setHometeamshortname(jsonobjecthometeam.getString("shortName"));
                                feedInfo.setHometeamlogo(jsonobjecthometeam.getString("logoUrl"));
                                feedInfo.setHometeamcolor(jsonobjecthometeam.getString("teamColour"));

                                feedInfo.setAwayteamid(jsonobjectawayTeam.getString("id"));
                                feedInfo.setAwayteamshortname(jsonobjectawayTeam.getString("shortName"));
                                feedInfo.setAwayteamlogo(jsonobjectawayTeam.getString("logoUrl"));
                                feedInfo.setAwayteamcolor(jsonobjectawayTeam.getString("teamColour"));

                                feedInfo.setStarttime(post.getString("startDateTime"));

                                allItemscricket.add(feedInfo);
                            }


                        }

                    } else {
                    }










                } catch (JSONException e) {
                    e.printStackTrace();
                }


                return responce;
            }


        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            pDialog.dismiss();
            if (status == null || status.length() == 0){

            }else if (status.equals("success")) {

                if (strresponse == null || strresponse.length() == 0){

                }else if (strtype.equals("cricket_list_available")){
                   /* cricket_Adapter = new Adapter_Cricket(getActivity(),allItemscricket, mCallback2);
                    recyclercricket.setAdapter(cricket_Adapter);*/
                }else{

                }



            }else{

            }



        }


    }
    private void RetrofitCricketlist() {

        Log.e("testing","strregisteredtoken = "+strregisteredtoken);

        final ProgressDialog pProgressDialog;
        pProgressDialog = new ProgressDialog(getActivity());
        pProgressDialog.setMessage("Please Wait ...");
        pProgressDialog.setIndeterminate(false);
        pProgressDialog.setCancelable(false);
        pProgressDialog.show();

        //call retrofit
        //making api call
        Api api = ApiClient.getClient().create(Api.class);
        Call<cricketlistjson> login = api.cricketlistjson("Bearer "+strregisteredtoken);

        login.enqueue(new Callback<cricketlistjson>() {
            @Override
            public void onResponse(Call<cricketlistjson> call, Response<cricketlistjson> response) {
                pProgressDialog.dismiss();
                Log.e("testing","status = "+response.body().getStatus());
                Log.e("testing","response = "+response.body().getResponse().getType());
              //  Log.e("testing","response = "+response.body().getData().getPageContent());

                if (response.body().getStatus() == null || response.body().getStatus().length() == 0){

                }else if (response.body().getStatus().equals("success")) {
                    if (response.body().getResponse() == null ){

                    }else if (response.body().getResponse().getType().equals("cricket_list_available")){
                        allItemscricket2 = response.body().getData().getMatchList().getMatches();
                        cricket_Adapter = new Adapter_Cricket(getActivity(),allItemscricket2, mCallback2);
                        recyclercricket.setAdapter(cricket_Adapter);

                    }else{
                        Toast.makeText(getActivity(), response.body().getResponse().getType(), Toast.LENGTH_SHORT).show();
                    }





                   /*

                    Intent intent=new Intent(Activity_Event_Details.this,Activity_Event_Details.class);
                    startActivity(intent);
                    finish();

*/




                    //  Toast.makeText(Activity_Event_Details.this, message, Toast.LENGTH_SHORT).show();


                } else  {
                    Log.e("testing","error");
                    Toast.makeText(getActivity(), response.body().getResponse().getType(), Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFailure(Call<cricketlistjson> call, Throwable t) {
                Toast.makeText(getActivity(),t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                pProgressDialog.dismiss();

            }
        });





    }
}
