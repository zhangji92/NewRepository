package com.jun.financial.credit.mode;

/**
 * Created by Administrator on 2016/11/17.
 * 理财
 */

public class FinanceItem {

    //投资项目
    private String investmentProject;
    //投资优惠
    private String investmentIncentive;
    //收益率
    private String yield;
    //期限
    private String deadline;
    //投资余额
    private String investmentBalance;
    //投资人数
    private String Investors;
    //评论数
    private String comments;
    //发标期
    private String faBiaoQi;
    //还款数
    private String payment;


    public FinanceItem(String investmentProject,
                       String investmentIncentive,
                       String yield,  String deadline,
                       String investmentBalance, String investors,
                       String comments, String faBiaoQi, String payment) {
        this.investmentProject = investmentProject;
        this.investmentIncentive = investmentIncentive;
        this.yield = yield;
        this.deadline = deadline;
        this.investmentBalance = investmentBalance;
        Investors = investors;
        this.comments = comments;
        this.faBiaoQi = faBiaoQi;
        this.payment = payment;
    }


    public String getInvestmentProject() {
        return investmentProject;
    }

    public void setInvestmentProject(String investmentProject) {
        this.investmentProject = investmentProject;
    }

    public String getInvestmentIncentive() {
        return investmentIncentive;
    }

    public void setInvestmentIncentive(String investmentIncentive) {
        this.investmentIncentive = investmentIncentive;
    }

    public String getYield() {
        return yield;
    }

    public void setYield(String yield) {
        this.yield = yield;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getInvestmentBalance() {
        return investmentBalance;
    }

    public void setInvestmentBalance(String investmentBalance) {
        this.investmentBalance = investmentBalance;
    }

    public String getInvestors() {
        return Investors;
    }

    public void setInvestors(String investors) {
        Investors = investors;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getFaBiaoQi() {
        return faBiaoQi;
    }

    public void setFaBiaoQi(String faBiaoQi) {
        this.faBiaoQi = faBiaoQi;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
