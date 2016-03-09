package com.gmail.b09302083.im_pubnub.application;

import com.gmail.b09302083.im_pubnub.utils.QLog;
import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import java.util.Calendar;

public class MainApplication extends Application {
    private static final String TAG = MainApplication.class.getSimpleName();

    private static String sAndroidId = "";

    private static WifiManager.MulticastLock sMulticastLock;

    private static ContentResolver sContentResolver;

    public static Context sContext;


    @Override
    public void onCreate() {
        super.onCreate();
        QLog.v(TAG, "onCreate");
        sAndroidId = Settings.Secure.getString(
                getContentResolver(),
                Settings.Secure.ANDROID_ID);
        QLog.v(TAG, "Android UUID : " + sAndroidId);
        sMulticastLock = ((WifiManager) getSystemService(Context.WIFI_SERVICE))
                .createMulticastLock("BenQ NAS");
        sContentResolver = getContentResolver();
        sContext = getApplicationContext();
    }

    public static String getAndroidId() {
        return sAndroidId;
    }

    public static WifiManager.MulticastLock getWifiManager() {
        return sMulticastLock;
    }

    public static ContentResolver getAppContentResolver() {
        return sContentResolver;
    }

    public static Context getAppContext() {
        return sContext;
    }

    public static String getAppHour() {
        if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) >= 0
                && Calendar.getInstance().get(Calendar.HOUR_OF_DAY) < 10) {
            return "0" + Integer.toString(
                    Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
        } else {
            return Integer.toString(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
        }
    }

    public static String getAppMinute() {
        if (Calendar.getInstance().get(Calendar.MINUTE) >= 0
                && Calendar.getInstance().get(Calendar.MINUTE) < 10) {
            return "0" + Integer.toString(
                    Calendar.getInstance().get(Calendar.MINUTE));
        } else {
            return Integer.toString(Calendar.getInstance().get(Calendar.MINUTE));
        }
    }

    public static String getAppSecond() {
        if (Calendar.getInstance().get(Calendar.SECOND) >= 0
                && Calendar.getInstance().get(Calendar.SECOND) < 10) {
            return "0" + Integer.toString(
                    Calendar.getInstance().get(Calendar.SECOND));
        } else {
            return Integer.toString(Calendar.getInstance().get(Calendar.SECOND));
        }
    }
}
