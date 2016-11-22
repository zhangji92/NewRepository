package com.jun.financial.credit.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by 张继 on 2016/11/16.
 * 获取手机屏幕的高度
 */

public final class ScreenUtil {
    private ScreenUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 获取屏幕中控件底部的位置的高度，即控件底部的Y点
     * @param view 控件
     * @return 返回控件底部的Y点
     */
    public static int getScreenViewBottomHeight(View view) {
        return view.getBottom();
    }

    /**
     * 获取屏幕中控件顶部的位置的高度，即控件底部的Y点
     * @param view 控件
     * @return 返回控件底部的Y点
     */
    public static int getScreenViewTopHeight(View view) {
        return view.getTop();
    }

    /**
     * 获取屏幕的宽度
     * @param context 上下文
     * @return 返回屏幕宽度
     */
    public final static int getScreenWidth(Context context){
        WindowManager wm= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);//窗口服务
        DisplayMetrics outMetrics=new DisplayMetrics();//获取屏幕信息实例对象
        wm.getDefaultDisplay().getMetrics(outMetrics);//获取屏幕信息
        return outMetrics.widthPixels;
    }
    /**
     * 获取屏幕的高度
     * @param context 上下文
     * @return 返回屏幕高度
     */
    public final static int getScreenHeight(Context context){
        WindowManager wm= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);//窗口服务
        DisplayMetrics outMetrics=new DisplayMetrics();//获取屏幕信息实例对象
        wm.getDefaultDisplay().getMetrics(outMetrics);//获取屏幕信息
        return outMetrics.heightPixels;
    }
    /**
     * 调节屏幕亮度
     */
    public final static void setScreenBrightness(Activity activity, int paramInt){
        Window localWindow=activity.getWindow();//activity的窗口
        WindowManager.LayoutParams localLayoutParams=localWindow.getAttributes();//获取activity布局属性
        float f=paramInt/255.0f;
        localLayoutParams.screenBrightness=f;//屏幕亮度
        localWindow.setAttributes(localLayoutParams);//设置屏幕亮度


    }

}
