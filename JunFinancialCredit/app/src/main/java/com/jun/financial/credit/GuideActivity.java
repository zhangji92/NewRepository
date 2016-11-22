package com.jun.financial.credit;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.jun.financial.credit.shared.prefs.AppInfoPrefs;

/**
 * Created by 张继 on 2016/11/16.
 * 引导页面
 */

public class GuideActivity extends Activity {
    private View[] pagerArr;
    private ImageView dot1;
    private ImageView dot2;
    private ImageView dot3;
    private ImageView dot4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loan_guide_layout);

        this.initViews();
    }

    private void initViews() {
        this.pagerArr = new View[]{
                View.inflate(GuideActivity.this, R.layout.loan_guide_one_layout, null),
                View.inflate(GuideActivity.this, R.layout.loan_guide_second_layout, null),
                View.inflate(GuideActivity.this, R.layout.loan_guide_third_layout, null),
                View.inflate(GuideActivity.this, R.layout.loan_guide_four_layout, null),
        };
        //圆点图片
        this.dot1 = (ImageView) findViewById(R.id.dot1);
        this.dot2 = (ImageView) findViewById(R.id.dot2);
        this.dot3 = (ImageView) findViewById(R.id.dot3);
        this.dot4 = (ImageView) findViewById(R.id.dot4);


        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);//控件
        GuideAdapter guideAdapter = new GuideAdapter(pagerArr);//适配器
        viewPager.setAdapter(guideAdapter);//设置适配器

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {//viewPager监听器
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    dot1.setImageResource(R.drawable.loan_dot_pressed);
                    dot2.setImageResource(R.drawable.loan_dot_normal);
                    dot3.setImageResource(R.drawable.loan_dot_normal);
                    dot4.setImageResource(R.drawable.loan_dot_normal);
                } else if (i == 1) {
                    dot1.setImageResource(R.drawable.loan_dot_normal);
                    dot2.setImageResource(R.drawable.loan_dot_pressed);
                    dot3.setImageResource(R.drawable.loan_dot_normal);
                    dot4.setImageResource(R.drawable.loan_dot_normal);
                } else if (i == 2) {
                    dot1.setImageResource(R.drawable.loan_dot_normal);
                    dot2.setImageResource(R.drawable.loan_dot_normal);
                    dot3.setImageResource(R.drawable.loan_dot_pressed);
                    dot4.setImageResource(R.drawable.loan_dot_normal);
                } else if (i == 3) {
                    dot1.setImageResource(R.drawable.loan_dot_normal);
                    dot2.setImageResource(R.drawable.loan_dot_normal);
                    dot3.setImageResource(R.drawable.loan_dot_normal);
                    dot4.setImageResource(R.drawable.loan_dot_pressed);

                    Button bntStart = (Button) pagerArr[i].findViewById(R.id.btnStart);
                    bntStart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //记录是否是第一次安装的状态
                            AppInfoPrefs.setFirstStartApp(false);

                            Intent intent = new Intent(GuideActivity.this, MainActivity.class);//跳转到首页
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//修改标识符-->清除该栈之上的所有Activity
                            startActivity(intent);
                            finish();

                        }
                    });
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private class GuideAdapter extends PagerAdapter {
        private View[] pagerArr;

        public GuideAdapter(View[] pagerArr) {
            this.pagerArr = pagerArr;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(this.pagerArr[position]);
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(this.pagerArr[position]);
            return this.pagerArr[position];
        }

        @Override
        public int getCount() {
            return this.pagerArr.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }
    }
}
