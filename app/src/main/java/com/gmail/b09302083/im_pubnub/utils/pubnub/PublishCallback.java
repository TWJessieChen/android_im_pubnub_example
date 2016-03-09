package com.gmail.b09302083.im_pubnub.utils.pubnub;

import com.gmail.b09302083.im_pubnub.utils.QLog;
import com.pubnub.api.Callback;
import com.pubnub.api.PubnubError;


public class PublishCallback extends Callback {
    private static final String TAG = PublishCallback.class.getSimpleName();

    public PubNubAdapter pubNubAdapter;

    public PublishCallback(PubNubAdapter aPubNubAdapter) throws NullPointerException {
        if (aPubNubAdapter == null) {
            throw new NullPointerException();
        }
        pubNubAdapter = aPubNubAdapter;
    }

    @Override
    public void successCallback(String channel, Object message) {
        super.successCallback(channel, message);
        QLog.v(TAG, "successCallback");
    }

    @Override
    public void successCallback(String channel, Object message, String timetoken) {
        super.successCallback(channel, message, timetoken);
    }

    @Override
    public void errorCallback(String channel, PubnubError error) {
        super.errorCallback(channel, error);
        QLog.w(TAG, "errorCallback , error = " + error.getErrorString());
    }

    @Override
    public void connectCallback(String channel, Object message) {
        super.connectCallback(channel, message);
        QLog.v(TAG, "connectCallback");
    }

    @Override
    public void reconnectCallback(String channel, Object message) {
        super.reconnectCallback(channel, message);
        QLog.d(TAG, "reconnectCallback");
    }

    @Override
    public void disconnectCallback(String channel, Object message) {
        super.disconnectCallback(channel, message);
        QLog.v(TAG, "disconnectCallback");
    }
}
