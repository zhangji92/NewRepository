package com.example.lenovo.amuse.util;

/**
 * Created by lenovo on 2016/9/26.
 * 所有网址
 */

public class BaseUri {
    /**
     * 通过一个地址 连接服务器（通过该地址获取服务器端的数据）
     */
    public final static String BASE="http://tc.ceol8.com/";
    //首页
    public final static String HOME=BASE+"service/index.php?model=home&action=home_new";
    public final static String LOVE_PLAY=BASE+"/service/index.php?model=city&action=indexcity";

    //code标识符
    public final static int FIRSTCODE=1;
    //FIRST_CODE_Carousel轮播
    public final static int FIRST_CODE_CAROUSE=2;
    //定位
    public final static int LOCATION=3;
    public final static int LOVE_PLAY_CODE=4;

}
