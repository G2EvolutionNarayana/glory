package com.glory.apk.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CashPaymentApiClint {

    //base url
    //  public static final String BASE_URL = "http://padhnalikhna.com/api/";
 //   public static final String BASE_URL = EndUrls.APIURL;
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://glory5.in/glory5/cashfreephp/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
