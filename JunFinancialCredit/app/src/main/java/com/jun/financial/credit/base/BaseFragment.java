package com.jun.financial.credit.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 张继 on 2016/11/17.
 * 基础Fragment
 */

public abstract class BaseFragment extends Fragment {
    protected View view;
    private Dialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(this.getLoadView(), container, false);
        initView(view);
        return view;

    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        this.initData();//初始化数据
        super.onActivityCreated(savedInstanceState);
    }

    protected abstract int getLoadView();

    protected abstract void initView(View view);

    protected abstract void initData();


}
