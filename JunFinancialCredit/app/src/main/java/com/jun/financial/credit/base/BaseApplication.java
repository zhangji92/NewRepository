package com.jun.financial.credit.base;

import android.app.Application;
import android.content.Context;

import com.umeng.message.PushAgent;

/**
 * Created by 张继 on 2016/11/16.
 * 基础应用
 */

public class BaseApplication extends Application {
    private static Context context;
    private PushAgent mPushAgent;

    @Override
    public void onCreate() {
        super.onCreate();

        context=this;
    }
    public static Context getContext(){
        return context;
    }

}
