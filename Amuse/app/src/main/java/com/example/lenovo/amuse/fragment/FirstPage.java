package com.example.lenovo.amuse.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.lenovo.amuse.R;
import com.example.lenovo.amuse.adapter.FirstAdapter;
import com.example.lenovo.amuse.adapter.ViewPageAdapter;
import com.example.lenovo.amuse.mode.FirstPageMode;
import com.example.lenovo.amuse.mode.FirstPageMode.ResultCodeBean.RecommendBean.HengBean;
import com.example.lenovo.amuse.util.BaseUri;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by lenovo on 2016/9/22.
 * 首页
 */

public class FirstPage extends BaseFragment {
    //
    private ListView listView;
    //list数据
    List<HengBean> list = new ArrayList<>();
    //适配器
    FirstAdapter firstAdapter;

    //广告相关
    private List<FirstPageMode.ResultCodeBean.AdBean> adBeanList;
    private List<View> viewList = new ArrayList<>();
    //轮播的空间
    private ViewPager viewPager;
    //适配器
    private ViewPageAdapter viewPageAdapter;

    //实例类
    FirstPageMode firstPageMode;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case BaseUri.FIRSTCODE:
                    firstPageMode = (FirstPageMode) msg.obj;

                    for (int i = 0; i < firstPageMode.getResultCode().getRecommend().getHeng().size(); i++) {
                        list.addAll(firstPageMode.getResultCode().getRecommend().getHeng());
                    }
                    if (firstPageMode!=null){
                        adBeanList=firstPageMode.getResultCode().getAd();
                        for(int i=0;i<adBeanList.size();i++){
                            ImageView imageView=new ImageView(getActivity());
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            viewList.add(imageView);
                        }
                        viewPageAdapter.setAdList(adBeanList);
                        viewPageAdapter.setViewList(viewList);
                        viewPager.setAdapter(viewPageAdapter);
                        viewPageAdapter.notifyDataSetChanged();

                        if(viewList!=null&&viewList.size()>1){
                            mHandler.sendEmptyMessageDelayed(BaseUri.FIRST_CODE_CAROUSE,2000);
                        }
                    }

                    firstAdapter.notifyDataSetChanged();
                    break;
                case BaseUri.FIRST_CODE_CAROUSE:
                    initCarousel();
                    viewPageAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

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
        View view = inflater.inflate(R.layout.first_page, container, false);
        //网络连接
        httpTools.getFirstDate(mHandler, "1", "1", null);
        //list控件
        listView = (ListView) view.findViewById(R.id.id_first_list);
        //list适配器
        firstAdapter = new FirstAdapter(list, getActivity());
        listView.setAdapter(firstAdapter);

        //轮播控件
        viewPager = new ViewPager(getActivity());
        //适配器实例化
        viewPageAdapter = new ViewPageAdapter(getActivity());

        //设定布局的宽度和高度
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, 260);
        //把布局的高度和宽度设置给viewPager
        viewPager.setLayoutParams(layoutParams);

        listView.addHeaderView(viewPager);

        return view;
    }


    /**
     * 广告位 图片轮播
     */
    public void initCarousel() {

        //当前的图片页数
        int item = viewPager.getCurrentItem();
        //当前页数等于最后一页时
        if (item == viewPageAdapter.getAdList().size() - 1) {
            //当前页数为0
            viewPager.setCurrentItem(0);
        } else {
            //当前页数+1
            viewPager.setCurrentItem(item + 1);
        }
        mHandler.sendEmptyMessageDelayed(BaseUri.FIRST_CODE_CAROUSE, 2000);
    }

}
