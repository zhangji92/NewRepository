package com.example.lenovo.amuse.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.lenovo.amuse.R;
import com.example.lenovo.amuse.adapter.LovePlayAdapter;
import com.example.lenovo.amuse.mode.LovePlayMode;
import com.example.lenovo.amuse.util.BaseUri;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/9/22.
 * 同城爱玩
 */

public class LovePlay extends BaseFragment {
    //添加数据集合
    private List<LovePlayMode.ResultCodeBean> modeList = new ArrayList<>();
    //实体类
    private LovePlayMode mLovePlayMode;
    //list控件
    ListView listView;
    //适配器
    private LovePlayAdapter lovePlayAdapter;
    //精度
    int lat = 1;
    //维度
    int lng = 1;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case BaseUri.LOVE_PLAY_CODE:
                    mLovePlayMode = (LovePlayMode) msg.obj;
                    for (int i = 0; i < mLovePlayMode.getResultCode().size(); i++) {
                        modeList.addAll(mLovePlayMode.getResultCode());
                    }
                    //计算list高度
                    getViewHright(listView);
                    lovePlayAdapter.notifyDataSetChanged();
                    break;
                case BaseUri.LOCATION:
                    //定位数据获取地址
                    lat = msg.arg1;
                    lng = msg.arg2;
                    break;
            }
        }
    };

//    private LovePlayMode parseMode(Object obj){
//        LovePlayMode lovePlayMode=null;
//        if (obj!=null&&obj instanceof lovePlayMode){
//
//        }
//        return lovePlayMode;
//    }

    /**
     * 每次创建（Fragment） 都会绘制Fragemnt 的View 组件时回调该方法
     *
     * @param inflater           加载布局文件
     * @param container          加载Layout 布局的 父（ViewGroup）
     * @param savedInstanceState 是否返回父 ViewGroup  false 为不返回
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.love_play, container, false);
        //网络传输数据
        httpTools.getDate(mHandler, String.valueOf(lat), String.valueOf(lng), null, null, null, null, 2);
        //适配器
        lovePlayAdapter = new LovePlayAdapter(getActivity(), modeList);
        //控件
        listView = (ListView) view.findViewById(R.id.play_list);
        listView.setAdapter(lovePlayAdapter);

        //scroll+listView设置一起滚动
        RelativeLayout zhiding = (RelativeLayout) view.findViewById(R.id.scroll_relative);
        zhiding.setFocusable(true);
        zhiding.setFocusableInTouchMode(true);
        zhiding.requestFocus();
        return view;
    }

    /**
     * 测量ListView的高度
     *
     * @param listView
     */
    public void getViewHright(ListView listView) {

        Adapter adapter = listView.getAdapter();
        int gd = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View view = adapter.getView(i, null, listView);
            view.measure(0, 0);
            int item = view.getMeasuredHeight();
            gd += item;
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = gd + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
