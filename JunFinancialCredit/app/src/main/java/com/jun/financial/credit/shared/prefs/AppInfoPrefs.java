package com.jun.financial.credit.shared.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.jun.financial.credit.base.BaseApplication;

/**
 * Created by 张继 on 2016/11/16.
 * 程序信息
 */

public class AppInfoPrefs {
    private final static String FILE_NAME = "app_info";//文件名称
    public final static String APP_INFO_KEY_CURR_VERSION = "curr_version";//应用程序的版本信息
    public final static String APP_INFO_KEY_LAST_VERSION = "last_version";//应用程序的最后版本信息
    public final static String WITHDRAW_IS_SUCCESS = "withdraw";//成功撤退
    public final static String RECHARGE_IS_SUCCESS = "recharge";//充值成功
    public final static String REGIST_TO_COUPON = "coupon";//注册到优惠券
    public final static String RECHARGE_IS_FRIST = "recharge_frist";//补给是第一的

    public static void setFirstStartApp(boolean isFirstStartApp) {
        SharedPreferences prefs = BaseApplication.getContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        prefs.edit().putBoolean("is_first_start_app",isFirstStartApp).apply();//记录是否是第一次安装的状态
    }


}
