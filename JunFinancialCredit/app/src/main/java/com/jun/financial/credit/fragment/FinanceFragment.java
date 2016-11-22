package com.jun.financial.credit.fragment;

import android.view.View;

import com.jun.financial.credit.R;
import com.jun.financial.credit.base.BaseFragment;
import com.jun.financial.credit.xlistview.XListView;


/**
 * Created by 张继 on 2016/11/17.
 * 理财
 */

public class FinanceFragment extends BaseFragment {

    private XListView mXListView;
    @Override
    protected int getLoadView() {
        return R.layout.loan_finance_products;
    }

    @Override
    protected void initView(View view) {
        mXListView= (XListView) view.findViewById(R.id.xListView);
    }

    @Override
    protected void initData() {
    }
}
