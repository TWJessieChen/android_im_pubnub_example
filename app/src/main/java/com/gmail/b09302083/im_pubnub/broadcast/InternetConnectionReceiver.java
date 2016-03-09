package com.gmail.b09302083.im_pubnub.broadcast;


import com.gmail.b09302083.im_pubnub.controller.MainController;
import com.gmail.b09302083.im_pubnub.utils.QLog;
import com.gmail.b09302083.im_pubnub.utils.wifi.WifiUtil;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class InternetConnectionReceiver extends BroadcastReceiver {
    private static final String TAG = InternetConnectionReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean status = WifiUtil.getInstance().getIsConnectInternet();

        if (!status) {
            QLog.v(TAG,
                    "InternetConnectionReceiver is false!!!");
            MainController.getInstance()
                    .sendEmptyMessage(MainController.MSG_RECEIVER_CONNECTION_INTERNET_IS_FAIL);
        } else {
            QLog.v(TAG,
                    "InternetConnectionReceiver is true!!!");
            MainController.getInstance()
                    .sendEmptyMessage(MainController.MSG_RECEIVER_CONNECTION_INTERNET_IS_SUCCESS);
        }
    }
}
