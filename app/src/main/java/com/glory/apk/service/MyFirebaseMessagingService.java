package com.glory.apk.service;

/**
 * Created by G2evolution on 4/3/2017.
 */


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.glory.apk.MainActivity;
import com.glory.apk.R;
import com.glory.apk.app.Config;
import com.glory.apk.utils.NotificationUtils;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;


public class MyFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    Bitmap bitmap;
    String type;
    Intent resultIntent;
    private static final String TAG = "testing";

    private NotificationUtils notificationUtils;

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        Log.e("Refreshed token:", token);

        sendRegistrationToServer(token);
        storeRegIdInPref(token);
    }

    private void sendRegistrationToServer(final String token) {
        // sending gcm token to server
        Log.e(TAG, "sendRegistrationToServer: " + token);
    }

    private void storeRegIdInPref(String token) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("regId", token);
        editor.commit();

        Log.e(TAG, "sendRegistrationToServer: " + token);
    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage == null)
            return;

        // Check if message contains a notification payload.
        if (remoteMessage.getData() != null) {


            Map<String, String> data = remoteMessage.getData();
            JSONObject jsonData = new JSONObject(data);
            try {
                String message = jsonData.getString("message");
                JSONObject Jsonmessage = new JSONObject(message);
                String notification_title = Jsonmessage.getString("notification_title");
                String notification_message = Jsonmessage.getString("notification_message");
                handleNotification(notification_title);
                handleDataMessage(notification_message, notification_title);


            } catch (JSONException e) {
                e.printStackTrace();
            }


//            Log.e(TAG, "Notification Body: " + remoteMessage.getNotification().getBody());

//            handleNotification(remoteMessage.getNotification().getBody());


        }

        // Check if message contains a data payload.
//        if (remoteMessage.getData().size() > 0) {
//            Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());
//
//            try {
//                JSONObject json = new JSONObject(remoteMessage.getData().toString());
//                handleDataMessage(json);
//            } catch (Exception e) {
//                Log.e(TAG, "Exception: " + e.getMessage());
//            }
//        }
    }

    private void handleDataMessage(String notification_message, String notification_title) {
        String imageUrl = null;
        type = "12";

        if (imageUrl != null)
            bitmap = getBitmapfromUrl(imageUrl);
        sendNotification(notification_message, bitmap, notification_title, type);
    }

    private static final String[] EMPTY_ARRAY = new String[0];

    private void handlerNotification() {


    }

    private void handleNotification(String message) {

        if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
            // app is in foreground, broadcast the push message
            Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
            pushNotification.putExtra("message", message);
            LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);

            // play notification sound
            NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
            notificationUtils.playNotificationSound();
        } else {
            // If the app is in background, firebase itself handles the notification
        }
    }

    private void handleDataMessage(JSONObject json) {
        Log.e(TAG, "push json: " + json.toString());

        try {
            JSONObject data = json.getJSONObject("data");
            Log.e("testing", "data: " + data);
            String title = data.getString("title");
            String message = data.getString("message");
            type = data.getString("type");
            boolean isBackground = data.getBoolean("is_background");
            String imageUrl = data.getString("image");
            String timestamp = data.getString("timestamp");
            JSONObject payload = data.getJSONObject("payload");

            Log.e(TAG, "title: " + title);
            Log.e(TAG, "message: " + message);
            Log.e("testing", "type: " + type);
            Log.e(TAG, "isBackground: " + isBackground);
            Log.e(TAG, "payload: " + payload.toString());
            Log.e(TAG, "imageUrl: " + imageUrl);
            Log.e(TAG, "timestamp: " + timestamp);


            if (type.equals("transc_order_rate_us")) {

                String strtransaction_id = payload.getString("transc_order_rate_us_order_id");
                Log.e("testing", "strtransaction_id in service = " + strtransaction_id);


                //   Toast.makeText(this, "please check", Toast.LENGTH_SHORT).show();
                Intent resultIntent = new Intent(this, MainActivity.class);
                resultIntent.putExtra("transc_order_rate_us_order_id", strtransaction_id);
                resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(resultIntent);


            }


            Log.e("testing", "firebase push notification");
/*
            if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
                // app is in foreground, broadcast the push message
                Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
                pushNotification.putExtra("message", message);
                LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);

                // play notification sound
                NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
                notificationUtils.playNotificationSound();
            } else {
                // app is in background, show the notification in notification tray
                Intent resultIntent = new Intent(getApplicationContext(), Activity_Notification.class);
                resultIntent.putExtra("message", message);

                // check for image attachment
                if (TextUtils.isEmpty(imageUrl)) {
                    showNotificationMessage(getApplicationContext(), title, message, timestamp, resultIntent);
                } else {
                    // image is present, show notification with image
                    showNotificationMessageWithBigImage(getApplicationContext(), title, message, timestamp, resultIntent, imageUrl);
                }
            }*/
            if (imageUrl != null)
                bitmap = getBitmapfromUrl(imageUrl);
            sendNotification(message, bitmap, title, type);

        } catch (JSONException e) {
            Log.e(TAG, "Json Exception: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }


    }

    /**
     * Showing notification with text only
     */
    private void showNotificationMessage(Context context, String title, String message, String timeStamp, Intent intent) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent);
    }

    /**
     * Showing notification with text and image
     */
    private void showNotificationMessageWithBigImage(Context context, String title, String message, String timeStamp, Intent intent, String imageUrl) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent, imageUrl);
    }

    private void sendNotification(String message, Bitmap image, String title, String msg_id) {
        int notifyID = 0;
        try {
            notifyID = Integer.parseInt(msg_id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        String CHANNEL_ID = "my_channel_01";            // The id of the channel.
        PendingIntent pendingIntent;
        if (msg_id == null || msg_id.trim().length() == 0 || msg_id.equals("null")) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("message", message);
            intent.putExtra("message", message);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


            pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                    PendingIntent.FLAG_ONE_SHOT);
        }/*else if (msg_id.equals("news")){


        }*/ else {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("message", message);
            intent.putExtra("message", message);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


            pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                    PendingIntent.FLAG_ONE_SHOT);
        }

        final int icon = R.drawable.ic_notify_bat;
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "01")
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_notify_bat)
                .setLargeIcon(BitmapFactory.decodeResource(MyFirebaseMessagingService.this.getResources(), icon))
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(message))
                .setContentText(message)
                .setAutoCancel(true)
                //.setSound(defaultSoundUri)
                .setChannelId(CHANNEL_ID)
                .setContentIntent(pendingIntent);
        playNotificationSound();
        if (image != null) {
            notificationBuilder.setStyle(new NotificationCompat.BigPictureStyle()   //Set the Image in Big picture Style with text.
                    .bigPicture(image)
                    .setSummaryText(message)
                    .bigLargeIcon(null));
        }

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {       // For Oreo and greater than it, we required Notification Channel.
            CharSequence name = "My New Channel";                   // The user-visible name of the channel.
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance); //Create Notification Channel
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(notifyID /* ID of notification */, notificationBuilder.build());
    }

    public Bitmap getBitmapfromUrl(String imageUrl) {     //This method returns the Bitmap from Url;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    // Playing notification sound
    public void playNotificationSound() {
        try {
            Uri alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                    + "://" + getPackageName() + "/" + R.raw.sound);
            Ringtone r = RingtoneManager.getRingtone(MyFirebaseMessagingService.this, alarmSound);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}