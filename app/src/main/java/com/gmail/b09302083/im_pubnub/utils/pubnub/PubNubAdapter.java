package com.gmail.b09302083.im_pubnub.utils.pubnub;

import com.gmail.b09302083.im_pubnub.application.MainApplication;
import com.gmail.b09302083.im_pubnub.utils.QLog;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubException;

public class PubNubAdapter {
    private static final String TAG = PubNubAdapter.class.getSimpleName();

    private static PubNubAdapter sPubNubAdapter = null;

    private Pubnub mPubnub;

    private SubscribeCallback mSubscribeCallback;

    private PublishCallback mPublishCallback;

    public static synchronized PubNubAdapter getInstance() {
        if (null == sPubNubAdapter) {
            synchronized (PubNubAdapter.class) {
                if (sPubNubAdapter == null) {
                    sPubNubAdapter = new PubNubAdapter();
                }
            }
        }
        return sPubNubAdapter;
    }

    public PubNubAdapter() {
        QLog.v(TAG, "Subscribe Callback : " + MainApplication.getAndroidId());
        mPubnub = new Pubnub(PubNubAccount.PUBLISH_KEY, PubNubAccount.SUBSCRIBE_KEY);
        mSubscribeCallback = new SubscribeCallback(this);
        mPublishCallback = new PublishCallback(this);
        subscribePubnub();
    }

    public void subscribePubnub() {
        try {
            QLog.v(TAG, "subscribePubnub callback : " + MainApplication.getAndroidId());
            mPubnub.subscribe(MainApplication.getAndroidId(), mSubscribeCallback);
        } catch (PubnubException e) {
            e.printStackTrace();
        }
    }

    public void unSubscribePubnub() {
        QLog.v(TAG, "unSubscribePubnub callback : " + MainApplication.getAndroidId());
        mPubnub.unsubscribe(MainApplication.getAndroidId());
    }
}
