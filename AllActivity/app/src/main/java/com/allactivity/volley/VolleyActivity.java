package com.allactivity.volley;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.allactivity.R;
import com.allactivity.util.MyApplication;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

/**
 * Created by 张继 on 2016/11/21.
 * volley框架
 * 1、Volley的get和post请求方式的使用
 * 2、Volley的网络请求队列建立和取消队列请求
 * 3、Volley与Activity生命周期的联动
 * 4、volley简单的二次回调封装
 */

public class VolleyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volley_activity);
        volley_Get();
        volleyJsonObjectGet();
    }

    private void volley_Get() {
        String url="https://www.baidu.com/";
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //数据请求成功
                Log.e("abc","sss:"+s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //数据请求失败
                Log.e("abc","sss:"+volleyError.toString());
            }
        });

        request.setTag("abcGet");
        MyApplication.getQueue().add(request);
    }

    private void volleyJsonObjectGet(){
        String url="http://tc.ceol8.com/service/index.php?model=home&action=home_new&lat=" + "1" + "&lng=" + "1";
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                //数据请求成功
                Log.e("abc","jsonObject:"+jsonObject);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //数据请求失败
                Log.e("abc","sss:"+volleyError.toString());
            }
        });
        jsonObjectRequest.setTag("abcGet");
        MyApplication.getQueue().add(jsonObjectRequest);
    }
}
