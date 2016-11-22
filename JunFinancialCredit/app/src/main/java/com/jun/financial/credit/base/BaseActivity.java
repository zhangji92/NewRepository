package com.jun.financial.credit.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.jun.financial.credit.util.ActivityCollector;
import com.jun.financial.credit.util.LogUtil;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by 张继 on 2016/11/16.
 * 基础的Activity
 */

public abstract class BaseActivity extends Activity {

    private final static String TAG = "BaseActivity";//打印log日志标识符
    private final static int LAST_TIME = 30000;//时间毫秒数
    private HomeWatcherReceiver homeKeyReceiver;//广播
    private static boolean isPresseHomeKey = false;//是否按home键的标识符
    private static boolean gestureLockShowing = false;//密码锁的标识符
    private static long lastTime = System.currentTimeMillis();//系统当前的毫秒数
    private Dialog dialog;
    private Dialog dialog2;
    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //添加视图
        this.rootView = View.inflate(this, this.loadWindowLayout(), null);
        setContentView(this.rootView);
        //activity管理器
        ActivityCollector.addActivity(this);
        this.initTitleBar();
        this.initViews();
    }

    protected abstract void initViews();//初始化视图

    protected abstract void initTitleBar();//初始化标题栏

    protected abstract int loadWindowLayout();//加载布局

    @Override
    protected void onResume() {
        super.onResume();
        registerHomeKeyReceiver();//注册广播
        MobclickAgent.onResume(this);
        //如果isPresseHomeKey=true;说明用户按了home键退出
        if (isPresseHomeKey){
            isPresseHomeKey=false;
            //
        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    @Override
    protected void onStop() {
        super.onStop();
        //注销广播
        unregisterHomeKeyReceiver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除activity
        ActivityCollector.removeActivity(this);
    }

    public static void setIsPresseHomeKey(boolean isPresseHomeKey) {
        BaseActivity.isPresseHomeKey = isPresseHomeKey;
    }

    public static void setLastTime(long lastTime) {
        BaseActivity.lastTime = lastTime;
    }

    public static void setGestureLockShowing(boolean gestureLockShowing) {
        BaseActivity.gestureLockShowing = gestureLockShowing;
    }

    /**
     * 注册广播
     */
    private final void registerHomeKeyReceiver(){
        LogUtil.i(TAG, "--------- registerHomeKeyReceiver");
        //自定义广播
        homeKeyReceiver = new HomeWatcherReceiver();
        //过滤器
        final IntentFilter homeFilter = new IntentFilter();
        homeFilter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);//添加要接受的广播
        //注册广播
        registerReceiver(homeKeyReceiver, homeFilter);
    }

    /**
     * 注销广播
     */
    private final void unregisterHomeKeyReceiver() {
        LogUtil.i(TAG, "--------- unregisterHomeKeyReceiver");
        if (null != homeKeyReceiver) {
            unregisterReceiver(homeKeyReceiver);
            homeKeyReceiver = null;
        }
    }

    /**
     * 自定义广播
     */
    private class HomeWatcherReceiver extends BroadcastReceiver {
        private static final String LOG_TAG = "HomeReceiver";//log日志标志
        private static final String SYSTEM_DIALOG_REASON_KEY = "reason";//系统对话框的原因
        private static final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";//系统对话原因最近的应用程序
        private static final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";//系统对话框原因家庭键
        private static final String SYSTEM_DIALOG_REASON_LOCK = "lock";//系统对话原因锁
        private static final String SYSTEM_DIALOG_REASON_ASSIST = "assist";//系统对话原因辅助

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();//接受字符串
            if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {//监听home键消息
                String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
                LogUtil.i(LOG_TAG, "------reason：" + reason);
                if (SYSTEM_DIALOG_REASON_HOME_KEY.equals(reason) // 短按Home键
                        || SYSTEM_DIALOG_REASON_RECENT_APPS.equals(reason) // 长按Home键或者activity切换键
                        || SYSTEM_DIALOG_REASON_LOCK.equals(reason) // 锁屏
                        || SYSTEM_DIALOG_REASON_ASSIST.equals(reason)) {// samsung长按Home键用户按了home键
                    isPresseHomeKey = true;
                    lastTime = System.currentTimeMillis();//系统当前的毫秒数
                }
            }
        }
    }
}
