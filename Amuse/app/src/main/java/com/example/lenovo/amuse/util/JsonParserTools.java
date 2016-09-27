package com.example.lenovo.amuse.util;

import com.example.lenovo.amuse.mode.FirstPageMode;
import com.example.lenovo.amuse.mode.LovePlayMode;
import com.google.gson.Gson;

import java.util.Objects;

import static com.example.lenovo.amuse.mode.FirstPageMode.ResultCodeBean.RecommendBean.HengBean;

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
            }
        }
        return obj;
    }


}
