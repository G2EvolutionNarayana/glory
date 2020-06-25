package com.android.glory.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.glory.Adapter.HomeTeamAdapter;
import com.android.glory.Model.PlayersList.PlayersListHomeTeam;
import com.android.glory.Model.PlayersList.PlayersMain;
import com.android.glory.R;
import com.android.glory.Retrofit.Api;
import com.android.glory.Retrofit.ApiClient;
import com.android.glory.Utilites.EndUrls;
import com.android.glory.Utilites.JSONParser;
import com.android.glory.Utilites.sharedPrefs;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_AboutUs extends AppCompatActivity {

    private ProgressDialog dialog;

    JSONParser jsonParser = new JSONParser();
    String strregisteredtoken;

    TextView textdesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__about_us);
        SharedPreferences prefuserdata3 = getSharedPreferences(sharedPrefs.Pref, 0);
        strregisteredtoken = prefuserdata3.getString(sharedPrefs.Pref_token, "");

        textdesc = (TextView) findViewById(R.id.textdesc);

        // new LoadAboutus().execute();

     //   AboutusUrl();

    }




    private void AboutusUrl() {

//        Log.e("testing", "strregisteredtoken = " + "matchesList");
//
//        dialog = new ProgressDialog(Activity_AboutUs.this);
//        dialog.setMessage("Please Wait ...");
//        dialog.setIndeterminate(false);
//        dialog.setCancelable(false);
//        dialog.show();
//
////        Api api = ApiClient.getClient().create(Api.class);
////        Call<AboutExample> login = api.PlayersList("1");
//
//
//        String viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);
//        Log.e("testing", "viewuseremail = " +viewuseremail);
//
//        Api api = ApiClient.getClient().create(Api.class);
//        Call<AboutExample> login = api.aboutusjson(viewuseremail);
//        login.enqueue(new Callback<AboutExample>() {
//            @Override
//            public void onResponse(Call<AboutExample> call, Response<AboutExample> response) {
//                dialog.dismiss();
//                Log.e("testing", "status = " + response.body().getStatus());
//                Log.e("testing", "response = " + response.body().getAboutResponse().getType());
//                //  Log.e("testing","response = "+response.body().getData().getPageContent());
//
//                Log.e("testing", "response = " + response.body());
//
//                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {
//
//                } else if (response.body().getStatus().equals("success")) {
//                    if (response.body().getAboutResponse() == null) {
//
//                    } else if (response.body().getAboutResponse().getType().equals("data_available")) {
//
//                        textdesc.setText(Html.fromHtml(response.body().getAboutData().getName()));
//
//                    } else {
//                        Toast.makeText(Activity_AboutUs.this, response.body().getAboutResponse().getType(), Toast.LENGTH_SHORT).show();
//                    }
//
//                } else {
//                    Log.e("testing", "error");
//                    dialog.dismiss();
//                    Toast.makeText(getApplicationContext(), response.body().getAboutResponse().getType(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//
//            @Override
//            public void onFailure(Call<AboutExample> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                dialog.dismiss();
//
//            }
//        });
//    }
    }
}
