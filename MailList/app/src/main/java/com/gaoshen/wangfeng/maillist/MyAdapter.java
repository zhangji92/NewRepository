package com.gaoshen.wangfeng.maillist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/9/18.
 */
public class MyAdapter extends BaseAdapter {

    private List<ModeUser> list;
    private LayoutInflater layoutInflater;
    private Context context;

    public MyAdapter( List<ModeUser> list, Context context) {
        this.list=list;
        layoutInflater=LayoutInflater.from(context);
        this.context=context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        //==null代表并没有记载view
        if (view == null) {
            view =layoutInflater.inflate(R.layout.list, null);
            holder = new ViewHolder();
            holder.tv_name = (TextView) view.findViewById(R.id.list);
            //加载内容
            holder.tv_name.setText(list.get(i).getName());
            //第一次加载完毕后标签储存
            view.setTag(holder);
        } else {
            //代表加载过了
            holder = (ViewHolder) view.getTag();
            //加载内容
            holder.tv_name.setText(list.get(i).getName());
        }

        return view;
    }

    private static class ViewHolder {
        TextView tv_name;
    }
}
