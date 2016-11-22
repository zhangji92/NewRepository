package com.jun.financial.credit.util;

import android.util.Log;

/**
 * Created by 张继 on 2016/11/16.
 * log输出, 开发和测试阶段isDebug = true, 产品上线后改成false
 */

public class LogUtil {
    //是否需要打印bug
    private final static boolean isDebug=true;

    private LogUtil(){
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public final static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

    public final static void d(String tag, String msg) {
        if (isDebug) {
            Log.d(tag, msg);
        }
    }

    public final static void e(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, msg);
        }
    }

    public final static void v(String tag, String msg) {
        if (isDebug) {
            Log.v(tag, msg);
        }
    }

    public final static void w(String tag, String msg) {
        if (isDebug) {
            Log.w(tag, msg);
        }
    }


}
