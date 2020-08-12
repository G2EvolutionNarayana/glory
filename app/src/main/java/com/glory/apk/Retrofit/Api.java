package com.glory.apk.Retrofit;

import com.glory.apk.Model.AboutUs.AboutExample;
import com.glory.apk.Model.ChangePassword.ChangeExample;
import com.glory.apk.Model.Contest.ContestExample;
import com.glory.apk.Model.EditPlayersListModel.EditPlayerExample;
import com.glory.apk.Model.EditTeamListModel.EditTeamExample;
import com.glory.apk.Model.Forgot.PasswordMain;
import com.glory.apk.Model.GenerateToken.GenerateTokenExample;
import com.glory.apk.Model.LiveContestListModel.LiveContestExample;
import com.glory.apk.Model.LiveMyMatchesModel.LiveMyMatchesExample;
import com.glory.apk.Model.LivePointsDataModel.LivePointsExample;
import com.glory.apk.Model.LoginEmail.LoginExample;
import com.glory.apk.Model.Logout.LogoutExample;
import com.glory.apk.Model.MyMatches.Example;
import com.glory.apk.Model.MyMatchesCompleted.MyMatchesCompletedExample;
import com.glory.apk.Model.MyMatchesUpComing.MyMatchesUpComingExample;
import com.glory.apk.Model.NotificationModel.NotificationExample;
import com.glory.apk.Model.PackagesList.PackageList;
import com.glory.apk.Model.PancardUpload.PancardExample;
import com.glory.apk.Model.PaymentModel.PaymentExample;
import com.glory.apk.Model.Pending.PendingExample;
import com.glory.apk.Model.PlayersList.PlayersMain;
import com.glory.apk.Model.PlayersListModel.Play_listExample;
import com.glory.apk.Model.UpdatePassword.UpdatePasswordExample;
import com.glory.apk.Model.UpdatePlayersModel.UpdatePlayersExample;
import com.glory.apk.Model.VerifyBankDetailsActivity.VerifyBankDetailsExample;
import com.glory.apk.Model.VerifyEmailModel.VerifyEmailExample;
import com.glory.apk.Model.VerifyOtpPhoneActivity.VerifyOtpPhoneExample;
import com.glory.apk.Model.VerifyPhoneAccountModel.VeifyPhoneAccountExample;
import com.glory.apk.Model.WithDrawAmount.WithDrawExample;
import com.glory.apk.Retrofit.ChangePassword.ChangePasswordJson;
import com.glory.apk.Retrofit.Contactus.contactusjson;
import com.glory.apk.Retrofit.CricketList.cricketlistjson;
import com.glory.apk.Retrofit.FAQs.FaqsJson;
import com.glory.apk.Retrofit.Login.LoginJson;
import com.glory.apk.Retrofit.Profile.ProfileJson;
import com.glory.apk.Retrofit.TermsandConditions.TermsandconditionsJson;
import com.glory.apk.Retrofit.UpdateProfile.UpdateprofileJson;

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

    @POST("admin/queries/store")
    @FormUrlEncoded
    Call<contactusjson> contactusjson(@Field("name") String name, @Field("email") String email, @Field("phone") String telephone, @Field("subject") String message, @Field("user_id") String user_id);

    @GET("cricketlist")
    Call<cricketlistjson> cricketlistjson(@Header("Authorization") String auth);

    @POST("user/authentication/password/change")
    @FormUrlEncoded
    Call<ChangeExample> ChangePassword(@Field("old_password") String name, @Field("user_id") String email, @Field("password") String telephone);

    @GET("rest/contest/list")
    Call<ContestExample> ContestList(@Query("match_id") String match_id, @Query("package_id") String package_id);

    @POST("rest/contest/payment")
    @FormUrlEncoded
    Call<PaymentExample> PaymentList(@Field("match_id") String match_id, @Field("contest_id") String contest_id, @Field("user_id") String user_id, @Field("package_id") String package_id, @Field("entry_fee") String entry_fee, @Field("power_hitter_id") String power_hitter_id, @Field("selected_players[]") ArrayList<String> selected_players);


    @GET("rest/packages/list")
    Call<PackageList> PackagesList();

    @GET("user/authentication/signin/profile")
    Call<AboutExample> aboutusjson(@Query("user_id") String userid);

    @POST("user/authentication/signin/profile")
    @FormUrlEncoded
    Call<UpdateprofileJson> updateProfile(@Field("user_id") String name, @Field("email") String email, @Field("fullname") String fullname, @Field("name") String telephone, @Field("dob") String dob, @Field("gender") String gender, @Field("phone") String phone);

    @POST("user/authentication/signin/profile")
    @FormUrlEncoded
    Call<UpdateprofileJson> UpdateImage(@Field("user_id") String name, @Field("image") String image);


    @GET("rest/matches/list")
    Call<Example> MatchesList(@Query("match_status") String matchStatus);

    @GET("rest/matches/list")
    Call<Example> MyMatchesList(@Query("match_status") String matchStatus, @Query("user_id") String userid);

    @GET("rest/matches/list")
    Call<MyMatchesUpComingExample> MyMatchesListUpcoming(@Query("match_status") String matchStatus, @Query("user_id") String userid);

    @GET("rest/matches/list")
    Call<LiveMyMatchesExample> MyMatchesListLive(@Query("match_status") String matchStatus, @Query("user_id") String userid);

    @GET("rest/matches/list")
    Call<MyMatchesCompletedExample> MyMatchesListCompleted(@Query("match_status") String matchStatus, @Query("user_id") String userid, @Query("page") int page, @Query("limit") int limit, @Query("pagination") String pagination);

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
    Call<LivePointsExample> LivePlayerPoints(@Query("contest_user_id") String contest_user_id, @Query("match_id") String match_id);


    @GET("rest/contest/players_list")
    Call<EditPlayerExample> EditPlayerList(@Query("contest_user_id") String contest_user_id);

    @GET("rest/matches/teams_list")
    Call<EditTeamExample> EditMatchesList(@Query("match_id") String match_id);

    @POST("rest/contest/update_players_list")
    @FormUrlEncoded
    Call<UpdatePlayersExample> UpdatePlayers(@Field("contest_user_id") String contest_user_id, @Field("power_hitter_id") String power_hitter_id, @Field("selected_players[]") ArrayList<String> selected_players);

    //    Call<UpdatePlayersExample> UpdatePlayers(@Field("contest_user_id") String contest_user_id, @Field("power_hitter_id") String power_hitter_id);
    @GET("rest/contest/completed_list")
    Call<com.glory.apk.Model.CompletedDataModel.Example> CompletedContestList(@Query("match_id") String match_id, @Query("user_id") String userid);
//    Call<CompletedContestExample> CompletedContestList(@Query("match_id") String match_id);

    @POST("user/withdraw/verification")
    @FormUrlEncoded
    Call<VerifyEmailExample> VerifyEmailAcount(@Field("email") String email, @Field("user_id") String user_id);


    @POST("user/withdraw/verification")
    @FormUrlEncoded
    Call<VeifyPhoneAccountExample> VerifyPhoneAcount(@Field("phone") String email, @Field("user_id") String user_id);

    @POST("user/withdraw/verify")
    @FormUrlEncoded
    Call<VerifyOtpPhoneExample> VerifyPhoneOtpAcount(@Field("phone") String email, @Field("user_id") String user_id, @Field("verify_otp") String verify_otp);


    @POST("admin/bank_details/store")
    @FormUrlEncoded
    Call<VerifyBankDetailsExample> VerifyBankAccount(@Field("account_name") String account_name, @Field("account_number") String account_number, @Field("ifsc_code") String ifsc_code, @Field("user_id") String user_id, @Field("id") String id);

    @GET("user/withdraw/verification_list")
    Call<com.glory.apk.Model.WithdrawVerificationModel.Example> WithDrawVerifyList(@Query("user_id") String userid);

    @POST("getToken.php")
    @FormUrlEncoded
    Call<GenerateTokenExample> GetCashToken(@Field("orderAmount") String orderAmount, @Field("user_id") String user_id);

    @GET("user/notifications_list")
    Call<NotificationExample> GetNotification(@Query("user_id") String user_id);

    @POST("user/withdraw/verification")
    @FormUrlEncoded
    Call<PancardExample> PancardImageUpload(@Field("card_image") String file, @Field("pan_number") String pan_number, @Field("name") String username, @Field("dob") String dob, @Field("user_id") String user_id, @Field("state") String state,@Field("id") String id);


    @POST("user/authentication/signin/index")
    @FormUrlEncoded
    Call<LoginExample> LoginWithEmail(@Field("image") String Image, @Field("fcm_token") String Fcm_token, @Field("fullname") String fullname, @Field("email") String email, @Field("device_id") String device_id, @Field("login_type") String login_type, @Field("device_type") String device_type);

    @POST("user/withdraw/request")
    @FormUrlEncoded
    Call<WithDrawExample> WithDrawAmount(@Field("user_id") String user_id, @Field("amount") String amount);


    @POST("user/wallet/store_wallet_recharge")
    @FormUrlEncoded
    Call<com.glory.apk.Model.WallateAmount.Example> WallateAmountStore(@Field("user_id") String user_id, @Field("order_id") String order_id, @Field("trans_amount") String trans_amount, @Field("transaction_id") String transaction_id, @Field("tracking_id") String tracking_id, @Field("bank_ref_no") String bank_ref_no, @Field("payment_mode") String payment_mode, @Field("trans_message") String trans_message, @Field("trans_datetime") String trans_datetime, @Field("trans_status") String trans_status, @Field("status") String status);

    @GET("rest/faq/list")
    Call<com.glory.apk.Model.FaqDataModel.Example> faqList();

    @POST("user/withdraw/request")
    @FormUrlEncoded
    Call<com.glory.apk.Model.FinalWithDrawAmount.Example> FinalWithDraw(@Field("user_id") String user_id, @Field("amount") String amount);

    @POST("user/authentication/signin/profile")
    @FormUrlEncoded
    Call<UpdateprofileJson> updatePhoneProfile(@Field("user_id") String name, @Field("phone") String phone);


    @GET("rest/matches/score")
    Call<com.glory.apk.Model.LiveScore.Example> LiveScore(@Query("match_id") String match_id);


    @GET("user/withdraw/list")
    Call<com.glory.apk.Model.WithdrawalList.Example> WithdrawList(@Query("user_id") String user_id);

    @GET("rest/content_manager/list")
    Call<com.glory.apk.Model.TermsModel.Example> TermsCall(@Query("content_key") String content_key);

}
