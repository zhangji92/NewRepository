package com.example.lenovo.amuse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lenovo.amuse.MyApplication;
import com.example.lenovo.amuse.R;
import com.example.lenovo.amuse.adapter.TableListAdapter;
import com.example.lenovo.amuse.mode.TableListMode;
import com.example.lenovo.amuse.util.BaseUri;
import com.example.lenovo.amuse.util.ServiceMessage;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 张继 on 2016/10/26.
 * 拼桌接口
 */

public class TableListActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener2,View.OnClickListener {
    //listVIew控件
    private PullToRefreshListView pullToRefreshListView;
    //标识符
    private boolean isFlag = true;
    //页数
    private int pageNumber = 0;
    //
    private String token;

    private List<TableListMode.ResultCodeBean> mList = new ArrayList<>();
    private TableListAdapter tableListAdapter;
    private TableListMode tableListMode;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case BaseUri.TABLE_LIST_CODE:
                    tableListMode = parseMode(msg.obj);
                    if (tableListMode != null) {
                        if (pageNumber == 0) {
                        for (int i = 0; i < tableListMode.getResultCode().size(); i++) {
                            mList.add(tableListMode.getResultCode().get(i));
                        }
                        } else {
                            if (tableListMode.getResultCode().get(7).getNickname().equals(mList.get(0).getNickname())) {
                                for (int i = 0; i < tableListMode.getResultCode().size(); i++) {
                                    mList.add(tableListMode.getResultCode().get(i));
                                }
                            } else {
                                Toast.makeText(TableListActivity.this, "数据已全部加载", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    pullToRefreshListView.setAdapter(tableListAdapter);
                    //停止刷新
                    pullToRefreshListView.onRefreshComplete();
                    break;
            }
        }
    };

    /**
     * 格式化数据
     * @param obj 数据类
     * @return 返回数据
     */
    private TableListMode parseMode(Object obj) {
        TableListMode tableListMode = null;
        if (obj != null && obj instanceof TableListMode) {
            tableListMode = (TableListMode) obj;
        }
        return tableListMode;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_list);
        //初始化控件
        initView();
        //初始化数据
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        /**
         * token 是 string token 用户信息
         * pageNumber 是 string 页码
         * pageSize 是 string 条数
         * lat 是 string 维度
         * lng 否 string 经度
         */
        token = ((MyApplication) getApplication()).getSuccessMode().getResultCode().getToken();
        String uri = BaseUri.TABLE_LIST + "&token=" + token + "&pageNumber=" + String.valueOf(pageNumber) + "&pageSize=" + "8" + "&lat=" + "1" + "&lng=" + "1";
        ServiceMessage<TableListMode> serviceMessage = new ServiceMessage<TableListMode>(uri, BaseUri.TABLE_LIST_CODE, new TableListMode());
        httpTools.getServiceMessage(mHandler, serviceMessage);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.table_list_listView);
        ImageView imageView= (ImageView) findViewById(R.id.snap_details_release);
        imageView.setOnClickListener(this);

        ImageView imageView_return= (ImageView) findViewById(R.id.table_list_return);
        imageView_return.setOnClickListener(this);

        //刷新监听器
        pullToRefreshListView.setOnRefreshListener(this);
        //上拉下拉模式
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        //适配器
        tableListAdapter = new TableListAdapter(mList, TableListActivity.this);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        Toast.makeText(TableListActivity.this, "下拉刷新", Toast.LENGTH_LONG).show();
        pageNumber=0;
        String uri = BaseUri.TABLE_LIST + "&token=" + token + "&pageNumber=" + String.valueOf(pageNumber) + "&pageSize=" + "8" + "&lat=" + "1" + "&lng=" + "1";
        ServiceMessage<TableListMode> serviceMessage = new ServiceMessage<TableListMode>(uri, BaseUri.TABLE_LIST_CODE, new TableListMode());
        httpTools.getServiceMessage(mHandler, serviceMessage);
        mList.clear();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        Toast.makeText(TableListActivity.this, "上啦加载", Toast.LENGTH_LONG).show();
        ++pageNumber;
        String uri = BaseUri.TABLE_LIST + "&token=" + token + "&pageNumber=" + String.valueOf(pageNumber) + "&pageSize=" + "8" + "&lat=" + "1" + "&lng=" + "1";
        ServiceMessage<TableListMode> serviceMessage = new ServiceMessage<TableListMode>(uri, BaseUri.TABLE_LIST_CODE, new TableListMode());
        httpTools.getServiceMessage(mHandler, serviceMessage);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            pullToRefreshListView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    if (isFlag) {
                        isFlag = false;
                        pullToRefreshListView.setRefreshing();
                        //添加适配器
                        pullToRefreshListView.setAdapter(tableListAdapter);
                        pullToRefreshListView.onRefreshComplete(); //停止刷新
                    }
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.table_list_return:
                finish();
                break;
            case R.id.snap_details_release:
                startActivity(new Intent(TableListActivity.this,TableAddActivity.class));
                finish();
                break;
        }

    }
}
