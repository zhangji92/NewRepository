package com.example.lenovo.amuse.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.amuse.R;
import com.example.lenovo.amuse.activity.LoginActivity;

/**
 * Created by lenovo on 2016/9/22.
 * 我的
 */

public class Who extends BaseFragment implements View.OnClickListener {
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
        View view = inflater.inflate(R.layout.who, container, false);
        TextView textView_login = (TextView) view.findViewById(R.id.login);
        textView_login.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
        }
    }
}
