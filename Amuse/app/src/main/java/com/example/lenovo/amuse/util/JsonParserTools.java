package com.example.lenovo.amuse.util;

import com.example.lenovo.amuse.mode.FirstPageMode;
import com.example.lenovo.amuse.mode.LovePlayMode;
import com.example.lenovo.amuse.mode.PreferentialMode;
import com.example.lenovo.amuse.mode.RegisterSuccess;
import com.example.lenovo.amuse.mode.SnapShortMode;
import com.example.lenovo.amuse.mode.SuccessMode;
import com.example.lenovo.amuse.mode.VerificationCode;
import com.google.gson.Gson;

/**
 * Created by lenovo on 2016/9/26.
 * json转成Mode实体类
 */

public class JsonParserTools {

    public static Object parserMode(String s, int flags) {
        Object obj = null;
        Gson gson = new Gson();
        if (s != null) {
            if (flags == 1) {
                obj = gson.fromJson(s, FirstPageMode.class);
            } else if (flags == 2) {
                obj = gson.fromJson(s, LovePlayMode.class);
            }else if (flags==3){
                obj = gson.fromJson(s, PreferentialMode.class);
            }else if (flags==4){
                obj = gson.fromJson(s, VerificationCode.class);
            }else if (flags==5){
                obj = gson.fromJson(s, RegisterSuccess.class);
            }else if (flags==6){
                obj = gson.fromJson(s, SuccessMode.class);
            }else if (flags==7){
                obj = gson.fromJson(s, SnapShortMode.class);
            }
        }
        return obj;
    }


}
