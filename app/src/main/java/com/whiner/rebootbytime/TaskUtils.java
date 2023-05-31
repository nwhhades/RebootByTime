package com.whiner.rebootbytime;

import android.util.Log;

import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public enum TaskUtils {
    ONE;

    private static final String TAG = "TaskUtils";

    TaskUtils() {
    }

    public void setTask() {
        int N = SPUtils.getInstance().getInt("N", 0);
        int HH = SPUtils.getInstance().getInt("HH", 0);
        int mm = SPUtils.getInstance().getInt("mm", 0);
        if (N != 0) {
            setTask(N, HH, mm);
        }
    }

    public void setTask(final int N, final int HH, final int mm) {
        clear();
        Log.d(TAG, "setTask: 添加任务" + N + " - " + HH + " - " + mm);
        ToastUtils.showShort("添加任务" + N + " - " + HH + " - " + mm);
        SPUtils.getInstance().put("N", N);
        SPUtils.getInstance().put("HH", HH);
        SPUtils.getInstance().put("mm", mm);


        Calendar now = Calendar.getInstance();
        Log.d(TAG, "setTask: " + now);
        Calendar nextRunTime = Calendar.getInstance();
        nextRunTime.set(Calendar.DAY_OF_WEEK, N);
        nextRunTime.set(Calendar.HOUR_OF_DAY, HH);
        nextRunTime.set(Calendar.MINUTE, mm);
        nextRunTime.set(Calendar.SECOND, 0);
        nextRunTime.set(Calendar.MILLISECOND, 0);
        if (nextRunTime.before(now) || nextRunTime.equals(now)) {
            nextRunTime.add(Calendar.WEEK_OF_YEAR, 1);
        }

        long initialDelay = nextRunTime.getTimeInMillis() - now.getTimeInMillis();
        Log.d(TAG, "setTask: 首次执行任务的时间" + initialDelay);
        long repeatInterval = TimeUnit.DAYS.toMillis(7);
        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(MyWorker.class, repeatInterval, TimeUnit.MILLISECONDS)
                .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS)
                .build();
        WorkManager.getInstance(Utils.getApp()).enqueue(workRequest);
    }

    public void clear() {
        Log.d(TAG, "clear: 清除任务");
        SPUtils.getInstance().clear();
        WorkManager.getInstance(Utils.getApp()).cancelAllWork();
    }

}
