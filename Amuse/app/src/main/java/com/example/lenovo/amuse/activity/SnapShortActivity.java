package com.example.lenovo.amuse.activity;

import android.animation.ObjectAnimator;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.example.lenovo.amuse.R;
import com.example.lenovo.amuse.adapter.SnapShortAdapter;
import com.example.lenovo.amuse.mode.SnapShortMode;
import com.example.lenovo.amuse.util.BaseUri;

import java.util.ArrayList;
import java.util.List;

/**
 * 快拍
 */
public class SnapShortActivity extends BaseActivity {

    private SnapShortAdapter snapShortAdapter;
    private List<SnapShortMode.ResultCodeBean.QuickphotoBean> beanList = new ArrayList<>();
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case BaseUri.SNAP:
                    SnapShortMode snapShortMode = parseMode(msg.obj);
                    for (int i = 0; i < snapShortMode.getResultCode().getQuickphoto().size(); i++) {
                        beanList.add(snapShortMode.getResultCode().getQuickphoto().get(i));
                    }
                    snapShortAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    private SnapShortMode parseMode(Object obj) {
        SnapShortMode snapShortMode = null;
        if (obj != null && obj instanceof SnapShortMode) {
            snapShortMode = (SnapShortMode) obj;
        }
        return snapShortMode;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snap_short);
        httpTools.getDate(mHandler, null, null, null, null, "1", "10", null, 4);
        GridView gridView = (GridView) findViewById(R.id.snap_gridView);
        snapShortAdapter = new SnapShortAdapter(this, beanList);
        gridView.setAdapter(snapShortAdapter);
    }
}
