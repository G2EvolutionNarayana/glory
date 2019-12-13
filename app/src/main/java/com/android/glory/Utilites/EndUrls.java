package com.android.glory.Utilites;

public class EndUrls {

    public static final String APIURL = "https://dev.netwebexperts.com/glory5/v1/";

    //---------------------------------Signup--------------------------
    public static final String Signup_URL = APIURL + "register";
    public static final String Signup_name  = "username";
    public static final String Signup_phone  = "mobile_no";
    public static final String Signup_emailid  = "email";
    public static final String Signup_password  = "password";
    public static final String Signup_referalcode  = "referal_code";

    //---------------------------------Signup OTP--------------------------
    public static final String SignupOTP_URL = APIURL + "signup/verify";
    public static final String SignupOTP_URL_OTP  = "user_otp";

    //---------------------------------Signup OTP Resend--------------------------
    public static final String SignupOTPResend_URL = APIURL + "signup/resend_otp";

    //---------------------------------Login--------------------------rrr
    public static final String Login_URL = APIURL + "signin/login";
    public static final String Login_mobileno  = "mobile_no";
    public static final String Login_Password  = "password";


    //---------------------------------Logout--------------------------rrr
    public static final String Logout_URL = APIURL + "user/logout";


    //---------------------------------ProfileDetails--------------------------rrr
    public static final String ProfileDetails_URL = APIURL + "user/profile";


    //---------------------------------Aboutus--------------------------rrr
    public static final String Aboutus_URL = APIURL + "aboutus";


    //---------------------------------Change Password--------------------------rrr
    public static final String ChangePassword_URL = APIURL + "user/password_update";
    public static final String ChangePassword_OldPassword  = "old_password";
    public static final String ChangePassword_NewPassword  = "password";


/*
    //---------------------------------Profile Update--------------------------rrr
    public static final String ProfileUpdate_URL = APIURL + "user/password_update";
    public static final String ProfileUpdate_username  = "username";
    public static final String ProfileUpdate_  = "password";
    public static final String ProfileUpdate_  = "password";
    public static final String ProfileUpdate_  = "password";*/

}
