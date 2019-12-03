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

}
