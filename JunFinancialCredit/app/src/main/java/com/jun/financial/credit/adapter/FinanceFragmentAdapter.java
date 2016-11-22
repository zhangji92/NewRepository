package com.jun.financial.credit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jun.financial.credit.R;
import com.jun.financial.credit.mode.FinanceItem;

import java.util.List;

/**
 * Created by 张继 on 2016/11/17.
 * 理财适配器
 */

public class FinanceFragmentAdapter extends BaseAdapter {

    private List<FinanceItem> mList;
    private Context context;

    public FinanceFragmentAdapter(List<FinanceItem> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mList.size() == 0 ? 0 : mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.finance_adapter, null);
            viewHolder = new ViewHolder();
            viewHolder.investmentProject = (TextView) view.findViewById(R.id.loan_finance_direct_title);
            viewHolder.investmentIncentive = (TextView) view.findViewById(R.id.loan_finance_direct_projectName);
            viewHolder.yield = (TextView) view.findViewById(R.id.loan_finance_direct_four);
            viewHolder.deadline = (TextView) view.findViewById(R.id.loan_finance_direct_day);
            viewHolder.investmentBalance = (TextView) view.findViewById(R.id.loan_finance_direct_thousand);
            viewHolder.Investors = (TextView) view.findViewById(R.id.loan_expected_no_person);
            viewHolder.comments = (TextView) view.findViewById(R.id.loan_expected_no_strip);
            viewHolder.faBiaoQi = (TextView) view.findViewById(R.id.loan_expected_no_stage);
            viewHolder.payment = (TextView) view.findViewById(R.id.loan_expected_no_element);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        FinanceItem financeItem = mList.get(i);
        if (financeItem != null) {
            viewHolder.investmentProject.setText(financeItem.getInvestmentProject());
            viewHolder.investmentIncentive.setText(financeItem.getInvestmentIncentive());
            viewHolder.yield.setText(financeItem.getYield());
            viewHolder.deadline.setText(financeItem.getDeadline());
            viewHolder.investmentBalance.setText(financeItem.getInvestmentBalance());
            viewHolder.Investors.setText(financeItem.getInvestors());
            viewHolder.comments.setText(financeItem.getComments());
            viewHolder.faBiaoQi.setText(financeItem.getFaBiaoQi());
            viewHolder.payment.setText(financeItem.getPayment());
        }
        return view;
    }

    class ViewHolder {
        //投资项目
        private TextView investmentProject;
        //投资优惠
        private TextView investmentIncentive;
        //收益率
        private TextView yield;
        //期限
        private TextView deadline;
        //投资余额
        private TextView investmentBalance;
        //投资人数
        private TextView Investors;
        //评论数
        private TextView comments;
        //发标期
        private TextView faBiaoQi;
        //还款数
        private TextView payment;
    }
}
