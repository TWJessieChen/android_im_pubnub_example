package com.gmail.b09302083.im_pubnub.utils.pubnub;


import com.gmail.b09302083.im_pubnub.controller.MainController;
import com.gmail.b09302083.im_pubnub.utils.QLog;
import com.pubnub.api.Callback;
import com.pubnub.api.PubnubError;

import org.json.JSONObject;

public class SubscribeCallback extends Callback {

    private static final String TAG = SubscribeCallback.class.getSimpleName();

    private PubNubAdapter mPubNubAdapter;

    public int errorCode = 0;

    public SubscribeCallback(PubNubAdapter aPubNubAdapter) throws NullPointerException {
        if (aPubNubAdapter == null) {
            throw new NullPointerException();
        }
        mPubNubAdapter = aPubNubAdapter;
    }

    @Override
    public void successCallback(String channel, Object message) {
        super.successCallback(channel, message);

        QLog.v(TAG, "successCallback");
        if (message == null || !(message instanceof JSONObject)) {
            errorCode = -1;
            QLog.v(TAG, "error");
        }
        sendBackToAdapterIfNeed((JSONObject) message);
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

        QLog.v(TAG, "reconnectCallback");
    }

    @Override
    public void disconnectCallback(String channel, Object message) {
        super.disconnectCallback(channel, message);
        QLog.v(TAG, "disconnectCallback");
    }

    private synchronized void sendBackToAdapterIfNeed(JSONObject aJSONObject) {
        QLog.v(TAG, "sendBackToAdapterIfNeed aJSONObject : " + aJSONObject);
        Constants.getInstance().setMsg(aJSONObject.toString());
        MainController.getInstance().sendEmptyMessage(MainController.MSG_RECEIVER_MESSAGE_IS_SUCCESS);
    }
}
