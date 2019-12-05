package com.android.glory.Retrofit;

/**
 * Created by Android4 on 3/27/2019.
 */

import com.android.glory.Retrofit.Aboutus.AboutusJson;
import com.android.glory.Retrofit.ChangePassword.ChangePasswordJson;
import com.android.glory.Retrofit.Login.LoginJson;
import com.android.glory.Retrofit.Logout.LogoutJson;
import com.android.glory.Retrofit.Profile.ProfileJson;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Kamere on 9/1/2018.
 */

public interface Api {

    @POST("signin/login")
    @FormUrlEncoded
    Call<LoginJson> loginjson(@Field("mobile_no") String mobile_no, @Field("password") String password);

    @POST("user/logout")
    Call<LogoutJson> logoutjson(@Header("Authorization") String auth);

    @GET("aboutus")
    Call<AboutusJson> aboutusjson(@Header("Authorization") String auth);

    @POST("user/profile")
    Call<ProfileJson> profilejson(@Header("Authorization") String auth);

    @POST("user/password_update")
    @FormUrlEncoded
    Call<ChangePasswordJson> changepasswordjson(@Field("old_password") String old_password, @Field("password") String password, @Header("Authorization") String auth);

}