package com.gmail.b09302083.im_pubnub.controller;

import com.gmail.b09302083.im_pubnub.utils.pubnub.PubNubAdapter;

import android.os.Handler;
import android.os.Message;
import java.util.concurrent.CopyOnWriteArrayList;

public class MainController extends Handler {
    private static final String TAG = MainController.class.getSimpleName();

    private static MainController sMainController = null;

    public static final int MSG_RECEIVER_CONNECTION_INTERNET_IS_FAIL = 0;

    public static final int MSG_RECEIVER_CONNECTION_INTERNET_IS_SUCCESS = 1;

    public static final int MSG_RECEIVER_MESSAGE_IS_SUCCESS = 2;

    private CopyOnWriteArrayList<Handler.Callback> mListenerList = new CopyOnWriteArrayList<>();

    public static synchronized MainController getInstance() {
        if (null == sMainController) {
            synchronized (MainController.class) {
                if (sMainController == null) {
                    sMainController = new MainController();
                }
            }
        }
        return sMainController;
    }

    @Override
    public void handleMessage(final Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case MSG_RECEIVER_CONNECTION_INTERNET_IS_FAIL:
                PubNubAdapter.getInstance().unSubscribePubnub();
                break;
            case MSG_RECEIVER_CONNECTION_INTERNET_IS_SUCCESS:
                PubNubAdapter.getInstance().subscribePubnub();
                break;
            default:
                notifyAllListeners(msg);
                break;
        }
    }

    public void registerUiListener(Callback aCallback) {
        if (!mListenerList.contains(aCallback)) {
            mListenerList.add(aCallback);
        }
    }

    public boolean deregisterUiListener(Handler.Callback aCallback) {
        return mListenerList.remove(aCallback);
    }

    private void notifyAllListeners(Message aMessage) {
        for (Callback callback : mListenerList) {
            callback.handleMessage(aMessage);
        }
    }


}
