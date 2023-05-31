package com.whiner.rebootbytime;

import android.util.Log;

import com.blankj.utilcode.util.ShellUtils;

public class RebootUtils {

    private static final String TAG = "RebootUtils";
    
    public static void reboot(){
        Log.d(TAG, "run: 开始重启");
        ShellUtils.execCmd("setprop sys.powerctl reboot", false);
        Log.d(TAG, "run: 重启结束");
    }

}
