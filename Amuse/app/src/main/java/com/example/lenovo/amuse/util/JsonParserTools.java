package com.example.lenovo.amuse.util;

import com.example.lenovo.amuse.mode.FirstPageMode;
import com.google.gson.Gson;

import static com.example.lenovo.amuse.mode.FirstPageMode.ResultCodeBean.RecommendBean.HengBean;

/**
 * Created by lenovo on 2016/9/26.
 * json转成Mode实体类
 */

public class JsonParserTools {

    public static FirstPageMode getHengBean(String s) {
        FirstPageMode firstPageMode = null;
        if (s != null) {
            Gson gson = new Gson();
            firstPageMode = gson.fromJson(s, FirstPageMode.class);
        }
        return firstPageMode;
    }
}
