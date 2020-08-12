package com.glory.apk.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.glory.apk.BuildConfig;
import com.glory.apk.MainActivity;
import com.glory.apk.Model.VersionModel;
import com.glory.apk.R;
import com.glory.apk.Utilites.sharedPrefs;
import com.glory.apk.app.Config;
import com.glory.apk.utils.NotificationUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

public class Activity_Splash extends AppCompatActivity {

    // ----FCM server key on glory5fantasysports@gmail.com/----
    //------------------------FCM Notification--------------------------
    private static final String TAG = Activity_Splash.class.getSimpleName();
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    //private TextView txtRegId, txtMessage;
    //------------------------FCM Notification--------------------------
    String regId;
    private DatabaseReference mDatabase;
    Query myTopPostsQuery;
    String version, force;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);// hide statusbar of Android
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        myTopPostsQuery = mDatabase.child("versions").child("android");


        //------------------------FCM Notification--------------------------
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                    displayFirebaseRegId();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");

                    Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();

                    // txtMessage.setText(message);
                }
            }
        };


        displayFirebaseRegId();
//------------------------FCM Notification--------------------------
        myTopPostsQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> list = new ArrayList<>();
                HashMap<String, String> hashmap = new HashMap<>();
                Log.e("testings", "messageSnapshot = " + dataSnapshot);

                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                    String key = messageSnapshot.getKey();
                    String value = messageSnapshot.getValue(String.class);
                    hashmap.put(key, value);

                }
                version = hashmap.get("android_versions");
                force = hashmap.get("forceble");

                String versionName = BuildConfig.VERSION_NAME;
                if (Float.parseFloat(version) == Float.parseFloat(versionName)) {
                    threadcalling();

                } else {
                    if (Float.parseFloat(force) == 0) {
                        Log.e("testings", "no force = " + "no force");
                        if ((Float.parseFloat(version) > Float.parseFloat(versionName))) {
                            AlertDialog dialog = new AlertDialog.Builder(Activity_Splash.this)
                                    .setTitle("New version available")
                                    .setCancelable(false)
                                    .setMessage("Please, update app to new version to continue playing.")
                                    .setPositiveButton("Update",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW);

                                                    //Copy App URL from Google Play Store.
                                                    intent.setData(Uri.parse("https://glory5.in/"));

                                                    startActivity(intent);
//                                                    redirectStore(updateUrl);
                                                }
                                            }).setNegativeButton("No, thanks",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    threadcalling();
                                                }
                                            }).create();
                            dialog.show();
                        } else {
                            threadcalling();
                        }

                    } else {
                        Log.e("testings", "force = " + " force");

                        AlertDialog dialog = new AlertDialog.Builder(Activity_Splash.this)
                                .setTitle("New version available")
                                .setCancelable(false)
                                .setMessage("Please, update app to new version to continue playing.")
                                .setPositiveButton("Update",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                Intent intent = new Intent(android.content.Intent.ACTION_VIEW);

                                                //Copy App URL from Google Play Store.
                                                intent.setData(Uri.parse("https://glory5.in/"));

                                                startActivity(intent);
                                            }
                                        }).create();
                        dialog.show();
                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Activity_Splash.this, "DataSnapShot :" + databaseError, Toast.LENGTH_LONG);

            }
        });


//        threadcalling();
    }

    //------------------------FCM Notification--------------------------------------------------------
    // Fetches reg id from shared preferences
    // and displays on the screen
    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        regId = pref.getString("regId", null);

        Log.e("testing", "Firebase reg id: " + regId);

        if (!TextUtils.isEmpty(regId))
            Log.e("testing", "Firebase Reg Id: " + regId);
            // txtRegId.setText("Firebase Reg Id: " + regId);
        else
            Log.e("testing", "Firebase Reg Id is not received yet!");
        //  txtRegId.setText("Firebase Reg Id is not received yet!");
    }


    @Override
    protected void onResume() {
        super.onResume();

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }


    //------------------------FCM Notification------------------------------------------------------


    private void threadcalling() {

        // StartSmartAnimation.startAnimation(logotxt.findViewById(R.id.logotxt), AnimationType.ZoomIn, 1000, 0, true, 100);
        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {


                    String viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);

                    Log.e("userId", "userid====splash" + viewuseremail);

                    if (viewuseremail.equals("") || viewuseremail.equals("null") || viewuseremail.equals(null) || viewuseremail.equals("0")) {

                        Intent intent = new Intent(Activity_Splash.this, Activity_Login.class);
                        startActivity(intent);
                        finish();

                    } else {

                        Intent intent2 = new Intent(Activity_Splash.this, MainActivity.class);
                        startActivity(intent2);
                        finish();

                    }


                }
            }
        };
        timerThread.start();

    }

}
