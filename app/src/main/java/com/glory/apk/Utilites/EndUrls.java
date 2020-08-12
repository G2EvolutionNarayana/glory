package com.glory.apk.Utilites;

public class EndUrls {

    // public static final String APIURL = "https://dev.netwebexperts.com/glory5/v1/";
    public static final String APIURL = "https://glory5.in/glory5/api/";

    //---------------------------------Signup--------------------------
    public static final String Signup_URL = APIURL + "user/authentication/signup/index";
    public static final String Signup_fullname = "fullname";
    public static final String Signup_name = "name";
    public static final String Signup_phone = "phone";
    public static final String Signup_emailid = "email";
    public static final String Signup_password = "password";
    public static final String Signup_image = "image";

    public static final String Signup_referalcode = "referal_code";
    public static final String Signup_fcm_token = "fcm_token";
    public static final String Signup_Device_type = "device_type";

    //---------------------------------Signup OTP--------------------------
    public static final String SignupOTP_URL = APIURL + "authentication/signup/verify";
    public static final String SignupOTP_URL_OTP = "register_exp";
    public static final String SignupOTP_temp_user = "temp_user_id";

    //---------------------------------Signup OTP Resend--------------------------
    public static final String SignupOTPResend_URL = APIURL + "user/authentication/signup/resend";
    public static final String SignupOTP_URL_name = "name";
    public static final String SignupOTP_temp_resend_user = "temp_user_id";
    //---------------------------------Login--------------------------rrr
    public static final String Login_URL = APIURL + "user/authentication/signin/index";
    public static final String Login_mobileno = "emailphone";
    public static final String Login_Password = "password";
    public static final String Login_Firebase_token = "firebase_token";


    //---------------------------------Logout--------------------------rrr
    public static final String Logout_URL = APIURL + "user/logout";


    //---------------------------------ProfileDetails--------------------------rrr
    public static final String ProfileDetails_URL = APIURL + "user/profile";


    //---------------------------------Aboutus--------------------------rrr
    public static final String Aboutus_URL = APIURL + "aboutus";


    //---------------------------------Change Password--------------------------rrr
    public static final String ChangePassword_URL = APIURL + "user/password_update";
    public static final String ChangePassword_OldPassword = "old_password";
    public static final String ChangePassword_NewPassword = "password";
/*
    //---------------------------------Profile Update--------------------------rrr
    public static final String ProfileUpdate_URL = APIURL + "user/password_update";
    public static final String ProfileUpdate_username  = "username";
    public static final String ProfileUpdate_  = "password";
    public static final String ProfileUpdate_  = "password";
    public static final String ProfileUpdate_  = "password";*/


    //---------------------------------CricketList--------------------------rrr
    public static final String cricketlist_URL = APIURL + "cricketlist";


    //---------------------------------CricketList--------------------------rrr
    public static final String Forgot_password_url = APIURL + "user/authentication/forgot-password/verify";
    public static final String Forgot_password_Forgot ="forgot_exp";
    public static final String Forgot_password_user_id ="user_id";



}
