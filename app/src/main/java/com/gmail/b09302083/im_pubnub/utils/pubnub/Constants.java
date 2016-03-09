package com.gmail.b09302083.im_pubnub.utils.pubnub;

public class Constants {
    private static final String TAG = Constants.class.getSimpleName();

    private static Constants sConstants = null;

    private static String msg = "";

    public static synchronized Constants getInstance() {
        if (null == sConstants) {
            synchronized (Constants.class) {
                if (sConstants == null) {
                    sConstants = new Constants();
                }
            }
        }
        return sConstants;
    }

    public Constants() {
    }

    public static String getMsg() {
        return msg;
    }

    public static void setMsg(String msg) {
        Constants.msg = msg;
    }
}
