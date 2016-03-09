package com.gmail.b09302083.im_pubnub.activity;

import com.gmail.b09302083.im_pubnub.R;
import com.gmail.b09302083.im_pubnub.controller.MainController;
import com.gmail.b09302083.im_pubnub.utils.QLog;
import com.gmail.b09302083.im_pubnub.utils.pubnub.Constants;
import com.gmail.b09302083.im_pubnub.utils.pubnub.PubNubAdapter;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Handler.Callback {
    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) this.findViewById(R.id.subscribetextview);
    }

    @Override
    protected void onResume() {
        super.onResume();
        QLog.v(TAG, "onResume");
        PubNubAdapter.getInstance();
        MainController.getInstance().registerUiListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        QLog.v(TAG, "onDestroy");
        MainController.getInstance().deregisterUiListener(this);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MainController.MSG_RECEIVER_MESSAGE_IS_SUCCESS:
                mTextView.setText(mTextView.getText().toString() + "\n" + Constants.getInstance().getMsg());
                break;
        }
        return false;
    }
}
