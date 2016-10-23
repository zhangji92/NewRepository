package com.allactivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewConfiguration;
import android.view.Window;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by lenovo on 2016/10/22.
 * 微信
 */

public class WeChatActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(R.style.MyTheme);
        super.onCreate(savedInstanceState);
        setOverflowButtonAlways();
        //把ActionBar图标隐藏
        getActionBar().setDisplayShowHomeEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.we_chat,menu);
        return true;
    }

    /**
     * 使用反射强制让系统把菜单图标显示-->一直显示
     * 且菜单显示的位置
     */
    private void setOverflowButtonAlways(){
        //
        ViewConfiguration configuration=ViewConfiguration.get(this);
        try {
            //显示OverflowButton的Field
            Field menuKey=ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            menuKey.setAccessible(true);
            menuKey.set(configuration,false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //设置显示子菜单中的图标
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId== Window.FEATURE_ACTION_BAR&&menu!=null){
            if (menu.getClass().getSimpleName().equals("MenuBuilder")){
                try {
                    Method m=menu.getClass().getDeclaredMethod("setOptionalIconsVisible",Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu,true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }
}
