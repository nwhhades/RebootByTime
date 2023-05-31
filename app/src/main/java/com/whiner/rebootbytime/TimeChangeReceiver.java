package com.whiner.rebootbytime;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TimeChangeReceiver extends BroadcastReceiver {

    private static final String TAG = "TimeChangeReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: 收到广播");
        if (intent != null) {
            Log.d(TAG, "onReceive: " + intent.getAction());
            TaskUtils.ONE.setTask();
        }
    }

}
