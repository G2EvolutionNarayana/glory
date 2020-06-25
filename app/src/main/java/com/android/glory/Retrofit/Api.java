package com.android.glory.Retrofit;

import com.android.glory.Model.AboutUs.AboutExample;
import com.android.glory.Model.ChangePassword.ChangeExample;
import com.android.glory.Model.CompletedContestModel.CompletedContestExample;
import com.android.glory.Model.Contest.ContestExample;
import com.android.glory.Model.EditPlayersListModel.EditPlayerExample;
import com.android.glory.Model.EditTeamListModel.EditTeamExample;
import com.android.glory.Model.Forgot.PasswordMain;
import com.android.glory.Model.GenerateToken.GenerateTokenExample;
import com.android.glory.Model.LiveContestListModel.LiveContestExample;
import com.android.glory.Model.LiveMyMatchesModel.LiveMyMatchesExample;
import com.android.glory.Model.LivePointsDataModel.LivePointsExample;
import com.android.glory.Model.LoginEmail.LoginExample;
import com.android.glory.Model.Logout.LogoutExample;
import com.android.glory.Model.MyMatches.Example;
import com.android.glory.Model.MyMatchesCompleted.MyMatchesCompletedExample;
import com.android.glory.Model.MyMatchesUpComing.MyMatchesUpComingExample;
import com.android.glory.Model.NotificationModel.NotificationExample;
import com.android.glory.Model.PackagesList.PackageList;
import com.android.glory.Model.PancardUpload.PancardExample;
import com.android.glory.Model.PaymentModel.PaymentExample;
import com.android.glory.Model.Pending.PendingExample;
import com.android.glory.Model.PlayersList.PlayersMain;
import com.android.glory.Model.PlayersListModel.Play_listExample;
import com.android.glory.Model.UpdatePassword.UpdatePasswordExample;
import com.android.glory.Model.UpdatePlayersModel.UpdatePlayersExample;
import com.android.glory.Model.VerifyBankDetailsActivity.VerifyBankDetailsExample;
import com.android.glory.Model.VerifyEmailModel.VerifyEmailExample;
import com.android.glory.Model.VerifyOtpPhoneActivity.VerifyOtpPhoneExample;
import com.android.glory.Model.VerifyPhoneAccountModel.VeifyPhoneAccountExample;
import com.android.glory.Model.WithDrawAmount.WithDrawExample;
import com.android.glory.Model.WithdrawStatusModel.WithdrawStatusExample;
import com.android.glory.Retrofit.ChangePassword.ChangePasswordJson;
import com.android.glory.Retrofit.Contactus.contactusjson;
import com.android.glory.Retrofit.CricketList.cricketlistjson;
import com.android.glory.Retrofit.FAQs.FaqsJson;
import com.android.glory.Retrofit.Login.LoginJson;
import com.android.glory.Retrofit.Logout.LogoutJson;
import com.android.glory.Retrofit.Profile.ProfileJson;
import com.android.glory.Retrofit.TermsandConditions.TermsandconditionsJson;
import com.android.glory.Retrofit.UpdateProfile.UpdateprofileJson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {
    @POST("signin/login")
    @FormUrlEncoded
    Call<LoginJson> loginjson(@Field("mobile_no") String mobile_no, @Field("password") String password);

    @POST("user/authentication/signin/logout")
    @FormUrlEncoded
    Call<LogoutExample> logoutjson(@Field("social_login") String social_login, @Field("id") String id);

//    @GET("aboutus")
//    Call<AboutusJson> aboutusjson(@Header("Authorization") String auth);


    @GET("aboutus")
    Call<TermsandconditionsJson> termsandconditionsjson(@Header("Authorization") String auth);

    @POST("user/profile")
    Call<ProfileJson> profilejson(@Header("Authorization") String auth);

    @POST("user/password_update")
    @FormUrlEncoded
    Call<ChangePasswordJson> changepasswordjson(@Field("old_password") String old_password, @Field("password") String password, @Header("Authorization") String auth);

    @POST("user/profile_update")
    @FormUrlEncoded
    Call<UpdateprofileJson> profilupdatejson(@Field("username") String username, @Field("email") String email, @Field("dob") String dob, @Field("gender") String gender, @Field("address") String address, @Field("country") String country, @Field("state") String state, @Field("city") String city, @Header("Authorization") String auth);

    @POST("faq")
    @FormUrlEncoded
    Call<FaqsJson> faquploadjson(@Field("faq_question") String faqquestion, @Header("Authorization") String auth);

    @POST("contactus")
    @FormUrlEncoded
    Call<contactusjson> contactusjson(@Field("name") String name, @Field("email") String email, @Field("telephone") String telephone, @Field("message") String message, @Header("Authorization") String auth);

    @GET("cricketlist")
    Call<cricketlistjson> cricketlistjson(@Header("Authorization") String auth);

    @POST("user/authentication/password/change")
    @FormUrlEncoded
    Call<ChangeExample> ChangePassword(@Field("old_password") String name, @Field("user_id") String email, @Field("password") String telephone);

    @GET("rest/contest/list")
    Call<ContestExample> ContestList(@Query("match_id") String match_id, @Query("user_id") String user_id);

    @POST("rest/contest/payment")
    @FormUrlEncoded
    Call<PaymentExample> PaymentList(@Field("match_id") String match_id, @Field("contest_id") String contest_id, @Field("user_id") String user_id, @Field("package_id") String package_id, @Field("entry_fee") String entry_fee, @Field("power_hitter_id") String power_hitter_id, @Field("selected_players[]") ArrayList<String> selected_players);


    @GET("rest/packages/list")
    Call<PackageList> PackagesList();

    @GET("user/authentication/signin/profile")
    Call<AboutExample> aboutusjson(@Query("user_id") String userid);
    @POST("user/authentication/signin/profile")
    @FormUrlEncoded
    Call<UpdateprofileJson> updatePassword(@Field("user_id") String name, @Field("email") String email, @Field("name") String telephone, @Field("dob") String dob, @Field("gender") String gender);

    @POST("user/authentication/signin/profile")
    @FormUrlEncoded
    Call<UpdateprofileJson> UpdateImage(@Field("user_id") String name,@Field("image") String image);


    @GET("rest/matches/list")
    Call<Example> MatchesList(@Query("match_status") String matchStatus);

    @GET("rest/matches/list")
    Call<Example> MyMatchesList(@Query("match_status") String matchStatus, @Query("user_id") String userid);

    @GET("rest/matches/list")
    Call<MyMatchesUpComingExample> MyMatchesListUpcoming(@Query("match_status") String matchStatus, @Query("user_id") String userid);

    @GET("rest/matches/list")
    Call<LiveMyMatchesExample> MyMatchesListLive(@Query("match_status") String matchStatus, @Query("user_id") String userid);

    @GET("rest/matches/list")
    Call<MyMatchesCompletedExample> MyMatchesListCompleted(@Query("match_status") String matchStatus, @Query("user_id") String userid);
    @POST("user/authentication/forgot-password/send")
    @FormUrlEncoded
    Call<PasswordMain> ForgotPassword(@Field("emailphone") String name);

    //    @POST("user/authentication/forgot-password/verify")
//    @FormUrlEncoded
//    Call<UpdatePasswordExample> PasswordUpadte(@Field("user_id") String user_id, @Field("forgot_exp") String forgot_exp);
    @POST("user/authentication/password/update")
    @FormUrlEncoded
    Call<UpdatePasswordExample> PasswordUpadte(@Field("user_id") String user_id, @Field("password") String password);

    @GET("rest/players/list")
    Call<PlayersMain> PlayersList(@Query("match_id") String match_id);

    @GET("rest/contest/upcoming_list")
    Call<PendingExample> UpComingPlayersDetails(@Query("match_id") String matchid, @Query("user_id") String userid);


    @GET("rest/contest/players_list")
    Call<Play_listExample> UpComingPlayersDetails(@Query("contest_user_id") String matchid);

    //
    @GET("rest/contest/live_list")
    Call<LiveContestExample> LivePlayersDetails(@Query("match_id") String matchid, @Query("user_id") String userid);

    @GET("rest/players/points")
    Call<LivePointsExample> LivePlayerPoints(@Query("contest_user_id") String matchid, @Query("match_id") String match_id);



    @GET("rest/contest/players_list")
    Call<EditPlayerExample> EditPlayerList(@Query("contest_user_id") String contest_user_id);

    @GET("rest/matches/teams_list")
    Call<EditTeamExample> EditMatchesList(@Query("match_id") String match_id);

    @POST("rest/contest/update_players_list")
    @FormUrlEncoded
    Call<UpdatePlayersExample> UpdatePlayers(@Field("contest_user_id") String contest_user_id, @Field("power_hitter_id") String power_hitter_id, @Field("selected_players[]") ArrayList<String> selected_players);
    //    Call<UpdatePlayersExample> UpdatePlayers(@Field("contest_user_id") String contest_user_id, @Field("power_hitter_id") String power_hitter_id);
    @GET("rest/contest/completed_list")
    Call<CompletedContestExample> CompletedContestList(@Query("match_id") String match_id, @Query("user_id") String userid);
//    Call<CompletedContestExample> CompletedContestList(@Query("match_id") String match_id);

    @POST("user/withdraw/verification")
    @FormUrlEncoded
    Call<VerifyEmailExample> VerifyEmailAcount(@Field("email") String email, @Field("user_id") String user_id);


    @POST("user/withdraw/verification")
    @FormUrlEncoded
    Call<VeifyPhoneAccountExample> VerifyPhoneAcount(@Field("phone") String email, @Field("user_id") String user_id);

    @POST("user/withdraw/verification")
    @FormUrlEncoded
    Call<VerifyOtpPhoneExample> VerifyPhoneOtpAcount(@Field("phone") String email, @Field("user_id") String user_id, @Field("verify_otp") String verify_otp);


    @POST("admin/bank_details/store")
    @FormUrlEncoded
    Call<VerifyBankDetailsExample> VerifyBankAccount(@Field("account_name") String account_name, @Field("account_number") String account_number, @Field("ifsc_code") String ifsc_code, @Field("user_id") String user_id);

    @GET("user/withdraw/verification_list")
    Call<WithdrawStatusExample> WithDrawVerifyList(@Query("user_id") String userid);

    @POST("getToken.php")
    @FormUrlEncoded
    Call<GenerateTokenExample> GetCashToken(@Field("orderAmount") String orderAmount, @Field("user_id") String user_id);

    @GET("user/notifications_list")
    Call<NotificationExample> GetNotification(@Query("user_id") String user_id);
    @POST("user/withdraw/verification")
    @FormUrlEncoded
    Call<PancardExample> PancardImageUpload(@Field("card_image")  String file, @Field("pan_number") String pan_number, @Field("name") String username, @Field("dob") String dob, @Field("user_id") String user_id, @Field("state") String state);


    @POST("user/authentication/signin/index")
    @FormUrlEncoded
    Call<LoginExample> LoginWithEmail(@Field("image") String Image,@Field("fcm_token") String Fcm_token,@Field("name") String name, @Field("email") String email, @Field("device_id") String device_id, @Field("login_type") String login_type, @Field("device_type") String device_type);

    @POST("user/withdraw/request")
    @FormUrlEncoded
    Call<WithDrawExample> WithDrawAmount(@Field("user_id") String user_id, @Field("amount") String amount);

}
