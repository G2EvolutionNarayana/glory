package com.glory.apk.Utilites;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class sharedPrefs {
    public static final String Pref = "registerUser";
    public static final String Pref_token = "token";
    public static final String Pref_userId = "userId";
    public static final String Social_id = "Social_id";

    public static final String Wallet_Amount = "Wallet_Amount";
    public static final String PhoneNumber = "PhoneNumber";

    public static final String USER_NAME = "USER_NAME";
    public static final String Profile_Image = "Profile_Image";

    public static final String TOTAL_WITHDRAWN = "TotalWithdrawn";
    public static final String WINNING_AMOUNT = "WinningAmount";
    public static final String AVAILABLE_WITHDRAW = "Availablewithdraw";
    public static final String AVAILABLE_DEPOSIT = "AVAILABLE_DEPOSIT";

    //----------------------CricketList id---------------

    public static void savepref (Context context, String key, String value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();

    }

    public static String getPreferences(Context context, String prefKey) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(prefKey, "");
    }

}
