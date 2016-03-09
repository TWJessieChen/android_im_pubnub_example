package com.gmail.b09302083.im_pubnub.utils.wifi;

import com.gmail.b09302083.im_pubnub.application.MainApplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class WifiUtil {
    private static final String TAG = WifiUtil.class.getSimpleName();

    private WifiInfo mWifiInfo = null;

    private WifiManager mWifiManager = null;

    private static WifiUtil sWifiManager = null;

    private ConnectivityManager mConnectivityManager = null;

    public static synchronized WifiUtil getInstance() {
        if (null == sWifiManager) {
            synchronized (WifiUtil.class) {
                if (sWifiManager == null) {
                    sWifiManager = new WifiUtil(MainApplication.getAppContext());
                }
            }
        }
        return sWifiManager;
    }

    public WifiUtil(Context mContext) {
        mWifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        mWifiInfo = mWifiManager.getConnectionInfo();
        mConnectivityManager = (ConnectivityManager) mContext.getSystemService(
                Context.CONNECTIVITY_SERVICE);
    }

    public WifiManager getWifiManager() {
        return mWifiManager;
    }

    public String getActionPickWIFINerwork() {
        return WifiManager.ACTION_PICK_WIFI_NETWORK;
    }

    public boolean getIsConnectWIFIStatus() {
        if (null == mConnectivityManager) {
            return false;
        } else {
            return mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                    .isConnectedOrConnecting();
        }
    }

    public boolean getIsConnect3rdGenerationStatus() {
        if (null == mConnectivityManager) {
            return false;
        } else {
            return mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                    .isConnectedOrConnecting();
        }
    }

    public boolean getIsConnectInternet() {
        if (null == mConnectivityManager) {
            return false;
        }
        return mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .isConnectedOrConnecting() || mConnectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .isConnectedOrConnecting();
    }

}
