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
     * lat N string 维度
     * lng N string 经度
     * searcename N string 检索
     */
    public void getFirstDate(final Handler handler, String lat, String lng, String searcename) {
        String url = BaseUri.HOME + "&lat=" + lat + "&lng=" + lng;//"&searcename="+searcename
        finalHttp.get(url, new AjaxCallBack<String>() {
            @Override
            public void onStart() {
                super.onStart();
                Log.i("getFirstDate", "onStart");
            }

            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                Log.i("getFirstDate", "onSuccess:" + s);
                Message message = new Message();
                message.what=BaseUri.FIRSTCODE;
                message.obj = JsonParserTools.getHengBean(s);
                handler.sendMessage(message);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                Log.i("getFirstDate", "onFailure:" + strMsg);
            }
        });
    }
}
