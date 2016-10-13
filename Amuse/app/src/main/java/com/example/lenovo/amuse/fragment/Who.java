package com.example.lenovo.amuse.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.amuse.MyApplication;
import com.example.lenovo.amuse.R;
import com.example.lenovo.amuse.activity.LoginActivity;
import com.example.lenovo.amuse.activity.UserMessage;
import com.example.lenovo.amuse.mode.SuccessMode;
import com.example.lenovo.amuse.util.BaseUri;
import com.makeramen.roundedimageview.RoundedImageView;

import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalDb;

/**
 * Created by lenovo on 2016/9/22.
 * 我的
 */

public class Who extends BaseFragment implements View.OnClickListener {

    //关注
    TextView textView_follow;
    //粉丝
    TextView textView_fans;
    TextView textView_login;
    MyApplication myApplication;
    RoundedImageView roundedImageView;

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
        //头像
        roundedImageView= (RoundedImageView) view.findViewById(R.id.who_head);

        textView_login = (TextView) view.findViewById(R.id.login);
        textView_login.setOnClickListener(this);
        //获取实例
        myApplication = (MyApplication) getActivity().getApplication();
        //关注
        textView_follow = (TextView) view.findViewById(R.id.who_follow);
        //粉丝
        textView_fans = (TextView) view.findViewById(R.id.who_fans);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        //设置handler
        if (myApplication.getSuccessMode() != null) {
            textView_login.setText(myApplication.getSuccessMode().getResultCode().getNickname());
            textView_follow.setText("关注：" + myApplication.getSuccessMode().getResultCode().getAttention_count() + " | ");
            textView_fans.setText("粉丝：" + myApplication.getSuccessMode().getResultCode().getFans_count());
            FinalBitmap finalBitmap=FinalBitmap.create(getActivity());
            finalBitmap.display(roundedImageView,BaseUri.BASE+myApplication.getSuccessMode().getResultCode().getImgUrl());
        }
    }


    @Override
    public void onClick(View v) {
        String login = textView_login.getText().toString();
        switch (v.getId()) {
            case R.id.login:
                if (login.contains("登录/注册")) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {

                    startActivity(new Intent(getActivity(), UserMessage.class));
                }
                break;
        }
    }

}
