package com.jun.financial.credit;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.jun.financial.credit.util.ScreenUtil;

public class SplashActivity extends Activity {

    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();

    private FrameLayout logoLayout;
    private ImageView textImg;
    private ImageView searchImg;
    private int screenHeight;
    private int searchHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loan_splash_logolayout);
        mLocationClient = new LocationClient(getApplicationContext()); // 声明LocationClient类
        mLocationClient.registerLocationListener(myListener); // 注册监听函数
        //初始化定位数据
        initLocation();
        mLocationClient.start();//定位开始

        logoLayout = (FrameLayout) findViewById(R.id.loan_splash_logoLayout);
        searchImg = (ImageView) findViewById(R.id.loan_splash_searchImg);
        textImg = (ImageView) findViewById(R.id.loan_splash_textImg);

        searchImg.post(new Runnable() {
            @Override
            public void run() {
                screenHeight = ScreenUtil.getScreenHeight(SplashActivity.this);//获取屏幕的高度
                searchHeight=searchImg.getHeight();//获取图片的高度
                startAnimator();//启动动画
            }
        });
    }

    /**
     * 启动动画
     */
    private void startAnimator() {
        AnimatorSet animatorSet = new AnimatorSet();//动画集合
        ObjectAnimator search = ObjectAnimator.ofFloat(searchImg, "alpha", 0f, 1f);//透明度的变换
        search.setInterpolator(new AccelerateDecelerateInterpolator());//设置插值器
        search.setDuration(2500);//时间毫秒数

        ObjectAnimator logo = ObjectAnimator.ofFloat(logoLayout, "translationY", 0f, -((screenHeight - searchHeight) / 3f));
        logo.setInterpolator(new AccelerateDecelerateInterpolator());
        logo.setDuration(1500);

        logo.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                searchImg.setVisibility(View.VISIBLE);//搜索图标可见的
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                textImg.setVisibility(View.VISIBLE);//文本图标
            }
        });

        ObjectAnimator text=ObjectAnimator.ofFloat(textImg,"alpha",0f,1f);
        text.setDuration(1000);
        text.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                StartActivity();
            }
        });
        animatorSet.playTogether(logo,search);
        animatorSet.playSequentially(logo,text);
        animatorSet.setStartDelay(500);
        animatorSet.start();
    }

    /**
     * 启动页
     */
    private void StartActivity() {
        Intent intent=new Intent(SplashActivity.this,GuideActivity.class);//跳转到欢迎页
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//清楚该栈之上的所有activity
        startActivity(intent);
        finish();
    }


    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");// 可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);// 可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);// 可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);// 可选，默认false,设置是否使用gps
        option.setLocationNotify(true);// 可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);// 可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);// 可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);// 可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
        option.SetIgnoreCacheException(false);// 可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);// 可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    private class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //获取经纬度
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            String province = null;//省份
            String city = null;//城市


        }
    }
}
