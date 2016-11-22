package com.jun.financial.credit.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张继 on 2016/11/16.
 * activity管理器
 */

public class ActivityCollector {
    public final static List<Activity> activityList=new ArrayList<>();

    /**
     * 添加activity
     * @param activity 活动
     */
    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    /**
     * 移除activity
     * @param activity 活动
     */
    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    /**
     * 关闭所有activity
     */
    public static void finishAll() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
