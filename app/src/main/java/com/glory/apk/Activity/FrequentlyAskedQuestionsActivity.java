package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.glory.apk.Adapter.FaqAdapter;
import com.glory.apk.R;
import com.glory.apk.Retrofit.Api;
import com.glory.apk.Retrofit.ApiClient;
import com.glory.apk.Model.FaqDataModel.Example;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FrequentlyAskedQuestionsActivity extends AppCompatActivity {
    private RecyclerView xRecyFaq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frequently_asked_questions);
        mInitWidgets();
        mFrequentlyAskedQuestions();
    }

    private void mInitWidgets() {
        xRecyFaq=(RecyclerView)findViewById(R.id.xRecyFaq);
        xRecyFaq.setLayoutManager(new LinearLayoutManager(this));
        xRecyFaq.setHasFixedSize(true);
        ImageView back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void mFrequentlyAskedQuestions() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");
        ProgressDialog pDialog;
        pDialog = new ProgressDialog(FrequentlyAskedQuestionsActivity.this);
        pDialog.setMessage("Please Wait ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        Api api = ApiClient.getClient().create(Api.class);
        Call<Example> login = api.faqList();

        login.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                pDialog.dismiss();


                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    Log.e("testing", "status = " + response.body().getStatus());
                    Log.e("testing", "response = " + response.body().getResponse().getType());
                    //  Log.e("testing","response = "+response.body().getData().getPageContent());

                    Log.e("testing", "response = " + response.body());
                    if (response.body().getResponse() == null) {

                    } else if (response.body().getResponse().getType().equals("data_found")) {

                        for (int i=0;i<response.body().getData().size();i++){
                            response.body().getData().get(i).setSelected(false);
                        }

                        xRecyFaq.setAdapter(new FaqAdapter(FrequentlyAskedQuestionsActivity.this,response.body().getData()));




                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getResponse().getMessage(), Toast.LENGTH_SHORT).show();
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
