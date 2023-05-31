package com.whiner.rebootbytime;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.hjq.toast.Toaster;

public class MyWorker extends Worker {

    private static final String TAG = "MyWorker";

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        // 在这里执行你的定时任务逻辑
        // 这个方法将在定时任务触发时被调用
        Toaster.show("执行任务");
        Log.d(TAG, "doWork: 任务！！！！！！");

        RebootUtils.reboot();
        // 返回 Result.success() 表示任务执行成功
        // 返回 Result.failure() 表示任务执行失败
        return Result.success();
    }

}
