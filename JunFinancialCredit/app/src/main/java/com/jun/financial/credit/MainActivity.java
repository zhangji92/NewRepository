package com.jun.financial.credit;

import android.app.Dialog;
import android.app.FragmentManager;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.jun.financial.credit.base.BaseActivity;
import com.jun.financial.credit.shared.prefs.AppInfoPrefs;
import com.umeng.message.PushAgent;

/**
 * Created by 张继 on 2016/11/16.
 * 首页
 */

public class MainActivity extends BaseActivity {

    private final static int BOTTOM_MENU_HOME_PAGE = 0;//主页底部的菜单
    private final static int BOTTOM_MENU_FINANCE = 1;//底部菜单金融
    public final static int BOTTOM_MENU_MY_ACCOUNT = 2;//底部菜单我的账户
    private int currMenu = BOTTOM_MENU_HOME_PAGE;//当前菜单

    private FragmentManager fragmentManager;//fragment管理

//    private BoutiqueCommendFragment homeFragment;
//    private FinanceFragment financeFragment;
//    private MyAccountFragment myAccountFragment;

    private ImageButton btnHome; // 首页
    private ImageButton btnFinance; // 理财
    private ImageButton btnMyAccount; // 我的账户

    private LinearLayout bottomBarLayout;

    private int financeMenuIndex = 0;
    private long singTime = 0L;

    private Dialog dialog;
    private Dialog dialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //推送
        PushAgent mPushAgent=PushAgent.getInstance(this);
        mPushAgent.enable();
        PushAgent.getInstance(this).onAppStart();

        AppInfoPrefs.setFirstStartApp(false);//记录是否时第一次安装的标识符

    }

    /**
     * 版本个更新
     */
    private void checkAppNewVersion(){
    }

    @Override
    protected void initViews() {
        //获取布局
        this.bottomBarLayout= (LinearLayout) findViewById(R.id.wallet_main_buttom_rg);
        //获取fragment管理器
        this.fragmentManager=getFragmentManager();
    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected int loadWindowLayout() {
        return R.layout.loan_main ;
    }
}
