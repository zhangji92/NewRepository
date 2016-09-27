package com.example.lenovo.amuse.util;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

/**
 * Created by lenovo on 2016/9/26.
 * 网络数据
 */

public class HttpTools {

    static FinalHttp finalHttp;
    static HttpTools httpTools = null;

    //初始化网路连接
    private HttpTools() {
        finalHttp = new FinalHttp();
    }

    /**
     * 单例模式
     *
     * @return
     */
    public static HttpTools getInstance() {
        if (httpTools == null) {
            httpTools = new HttpTools();
        }
        return httpTools;
    }

    /**
     * 首页数据
     * lat N string 维度
     * lng N string 经度
     * searcename N string 检索
     * <p>
     * 同城爱玩
     * pageNumber 是 string 页数
     * pageSize   是 string 每页条数
     * city       是 string 城市
     * lat        是 string 维度
     * lng        是 string 精度
     * flags 标识符
     */
    public void getDate(final Handler handler, String lat, String lng, String searcename, String pageNumber, String pageSize, String city, final int flags) {
        String url = "";
        if (flags == 1) {
            url = BaseUri.HOME + "&lat=" + lat + "&lng=" + lng;//"&searcename="+searcename
        } else if (flags == 2) {
            url = BaseUri.LOVE_PLAY + "&lat=" + lat + "&lng=" + lng;//+"&pageNumber="+pageNumber+"&pageSize="+pageSize+"&city="+city
        }
        finalHttp.get(url, new AjaxCallBack<String>() {
            @Override
            public void onStart() {
                super.onStart();
                Log.i("getFirstDate", "onStart");
            }

            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);

                Message message = new Message();
                if (flags == 1) {
                    message.what = BaseUri.FIRSTCODE;
                    message.obj = JsonParserTools.parserMode(s, 1);
                } else if (flags == 2) {
                    message.what = BaseUri.LOVE_PLAY_CODE;
                    message.obj = JsonParserTools.parserMode(s, 2);
                    Log.i("getFirstDate", "onSuccess:" + flags + s);
                }

                handler.sendMessage(message);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                Log.i("getFirstDate", "onFailure:" + strMsg);
            }
        });
    }

    /**
     * 优惠专区
     * pageNumber 是 string 页号
     * pageSize 是 string 条数
     * lat 是 string 维度
     * lng 是 string 精度
     */
    public void postData(final Handler handler, String lat, String lng, String pageNumber, String pageSize) {

        String url = BaseUri.PREFERENTIAL + "&lat=" + lat + "&lng=" + lng;//+"&pageNumber="+pageNumber+"&pageSize="+pageSize

        finalHttp.post(url, new AjaxCallBack<String>() {
            @Override
            public void onStart() {
                super.onStart();
                Log.i("getFirstDate", "onStart:");
            }

            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                Message message = new Message();
                message.obj = JsonParserTools.parserMode(s, 3);
                message.what = BaseUri.PRE;
                handler.sendMessage(message);
                Log.i("getFirstDate", "onSuccess:" + s);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                Log.i("getFirstDate", "onFailure:" + strMsg);
            }
        });
    }
}
