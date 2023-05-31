package com.whiner.rebootbytime;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;

import com.blankj.utilcode.util.SPUtils;
import com.whiner.rebootbytime.databinding.ActivityMainBinding;
import com.whiner.tool.ui.base.BaseActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private static final String TAG = "MainActivity";

    @Override
    public ActivityMainBinding getViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initView() {
        viewBinding.day.setMinValue(1);
        viewBinding.day.setMaxValue(7);
        String[] values = {"日", "一", "二", "三", "四", "五", "六"};
        viewBinding.day.setDisplayedValues(values);
        viewBinding.day.setValue(1);

        viewBinding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: 清除任务");
                TaskUtils.ONE.clear();
                showTask();
            }
        });

        viewBinding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int N = viewBinding.day.getValue();
                int hour, minute;
                TimePicker timePicker = viewBinding.time;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    hour = timePicker.getHour();
                    minute = timePicker.getMinute();
                } else {
                    hour = timePicker.getCurrentHour();
                    minute = timePicker.getCurrentMinute();
                }
                TaskUtils.ONE.setTask(N, hour, minute);
                showTask();
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        showTask();
    }

    private void showTask() {
        int N = SPUtils.getInstance().getInt("N", 0);
        int HH = SPUtils.getInstance().getInt("HH", 0);
        int mm = SPUtils.getInstance().getInt("mm", 0);
        String msg = "星期" + getWeek(N) + " - " + HH + " : " + mm;
        String text = getString(R.string.msg, msg);
        viewBinding.msg.setText(text);
    }

    private String getWeek(int n) {
        switch (n) {
            case 1:
                return "日";
            case 2:
                return "一";
            case 3:
                return "二";
            case 4:
                return "三";
            case 5:
                return "四";
            case 6:
                return "五";
            case 7:
                return "六";
        }
        return "零";
    }

}