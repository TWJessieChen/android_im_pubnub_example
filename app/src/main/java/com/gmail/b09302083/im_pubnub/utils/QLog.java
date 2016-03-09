package com.gmail.b09302083.im_pubnub.utils;

import com.gmail.b09302083.im_pubnub.BuildConfig;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.util.List;
import java.util.Vector;

public class QLog {
    public static final int ACTIVITY_LOG_ON_SCREEN = 0x313;

    private static List<Handler> mNotifyList = new Vector<Handler>(10);

    public static void setHandlerToNotify(Handler aHandler) {
        if (!mNotifyList.contains(aHandler)) {
            mNotifyList.add(aHandler);
        }
    }

    private static void notifyAllHandlers(String aString) {
        Message msg = Message.obtain();
        msg.what = ACTIVITY_LOG_ON_SCREEN;
        msg.obj = aString;
        for (Handler h : mNotifyList) {
            h.sendMessage(msg);
        }
    }

    public static void v(final String tag, final String log) {
        if (BuildConfig.QLOG) {
            Log.v(tag, log);
            notifyAllHandlers(log);
        }
    }

    public static void i(final String tag, final String log) {
        if (BuildConfig.QLOG) {
            Log.i(tag, log);
            notifyAllHandlers(log);
        }
    }

    public static void d(final String tag, final String log) {
        if (BuildConfig.QLOG) {
            Log.d(tag, log);
            notifyAllHandlers(log);
        }
    }

    public static void w(final String tag, final String log) {
        if (BuildConfig.QLOG) {
            Log.w(tag, log);
            notifyAllHandlers(log);
        }
    }

    public static void e(final String tag, final String log) {
        if (BuildConfig.QLOG) {
            Log.e(tag, log);
            notifyAllHandlers(log);
        }
    }

    public static void catchException(Exception aException) {
        if (BuildConfig.QLOG) {
            notifyAllHandlers("----------------------\r\n" + aException.toString()
                    + "\r\n----------------------");
        }
    }
}
