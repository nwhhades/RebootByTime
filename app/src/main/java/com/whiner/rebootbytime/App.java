package com.whiner.rebootbytime;

import android.widget.Toast;

import com.whiner.tool.BaseApp;

public class App extends BaseApp {

    @Override
    public void init() {
        //读取时间，启动任务
        TaskUtils.ONE.setTask();
        //Toast.makeText(this, "应用已启动", Toast.LENGTH_SHORT).show();
    }

}
