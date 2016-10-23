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
    //同城爱玩
    public final static String LOVE_PLAY=BASE+"/service/index.php?model=city&action=indexcity";
    //优惠专区
    public final static String PREFERENTIAL=BASE+"/service/index.php?model=favorable&action=favorableshoplist";
    //验证码
    public final static String REGISTER_CODE=BASE+"/service/index.php?model=user&action=verifycode";
    //注册接口
    public final static String REGISTER=BASE+"/service/index.php?model=user&action=register";
    //登陆接口
    public final static String LOGIN=BASE+"/service/index.php?model=user&action=login";
    //快拍列表接口
    public final static String SNAPSHORT=BASE+"/service/index.php?model=city&action=quickphotolist";
    //快拍详情列表接口
    public final static String SNAPSHORT_DETAILS=BASE+"/service/index.php?model=city&action=quickphotoinfobyid1";
    //场所
    public final static String PLACE=BASE+"/service/index.php?model=city&action=placelist";
    //编辑个人信息
    public final static String USER=BASE+"/service/index.php?model=user&action=edituser";
    //酒水
    public final static String WINE=BASE+"/service/index.php?model=city&action=agent";
    //图片上传
    public final static String PIC=BASE+"/service/index.php?model=user&action=uploaduserpic";
    //酒水商家
    public final static String WINE_AGENT=BASE+"/service/index.php?model=city&action=agentinfobyid";
    //酒水详情
    public final static String WINE_DETAILS=BASE+"/service/index.php?model=city&action=agentshopinfobyid";
    //获取商家详细信息接口
    public final static String SHOP_DETAILS=BASE+"/service/index.php?model=home&action=shopinfobyid";

    //code标识符
    public final static int FIRSTCODE=1;
    //FIRST_CODE_Carousel轮播
    public final static int FIRST_CODE_CAROUSE=2;
    //定位
    public final static int LOCATION=3;
    //同城爱玩
    public final static int LOVE_PLAY_CODE=4;
    //优惠专区
    public final static int PRE=5;
    //手机验证码
    public final static int PHONE=6;
    //注册成功
    public final static int REGISTER_SUCCESS=7;
    //登陆成功
    public final static int LOGIN_SUCCESS=8;
    //登陆成功后
    public final static int LOGIN_ID=9;
    //快拍列表
    public final static int SNAP=10;
    //酒水
    public final static int WINE_CODE=11;
    //酒水传输码
    public final static int PLACE_CODE=12;
    //保存信息后的码
    public final static int MESSAGE_CODE=13;
    //上传照片成功-->相机
    public final static int PIC_CODE=14;
    //酒水商家
    public final static int WINE_AGENT_CODE=15;
    //酒水详情
    public final static int WINE_AGENT_DETAILS=16;
    //商家详情
    public final static int SHOP_DETAILS_CODE=17;




}
