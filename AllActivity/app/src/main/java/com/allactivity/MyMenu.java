package com.allactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.allactivity.L11_Graphics.MainActivity;
import com.allactivity.custom.calendar.CustomCalendar;
import com.allactivity.imkit.IMKit;
import com.allactivity.listview.ListViewActivity;
import com.allactivity.listview.refresh.ListActivityAnimator;
import com.allactivity.speech.SpeechFragment;
import com.allactivity.sqlite.SQLiteDome;
import com.allactivity.sqlite.SQLiteDome2;
import com.allactivity.viewpager.ViewPagerActivity;
import com.allactivity.volley.VolleyActivity;
import com.allactivity.weChat.WeChatActivity;
import com.allactivity.web.view.WebViewActivity;

/**
 * Created by lenovo on 2016/10/22.
 * 菜单
 */

public class MyMenu extends AppCompatActivity {
    private SpeechFragment mSpeechFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_menu_activity);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.weChat:
                startActivity(new Intent(this, WeChatActivity.class));
                break;
            case R.id.sqLite:
                startActivity(new Intent(this, SQLiteDome.class));
                break;
            case R.id.im_kit:
                startActivity(new Intent(this, IMKit.class));
                break;
            case R.id.calendar:
                startActivity(new Intent(this, CustomCalendar.class));
                break;
            case R.id.sqLite2:
                startActivity(new Intent(this, SQLiteDome2.class));
                break;
            case R.id.webView:
                startActivity(new Intent(this, WebViewActivity.class));
                break;
            case R.id.ceShi:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.list_intent:
                startActivity(new Intent(this, ListViewActivity.class));
                break;
            case R.id.refresh:
                startActivity(new Intent(this, ListActivityAnimator.class));
                break;
            case R.id.viewPager:
                startActivity(new Intent(this, ViewPagerActivity.class));
                break;
            case R.id.volley:
                startActivity(new Intent(this, VolleyActivity.class));
                break;
            case R.id.speech:
                addFragment();
                break;
        }
        return true;
    }

    private void addFragment() {
        FragmentManager fragmentManager=getSupportFragmentManager();
        //事物
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        hidFragment(fragmentTransaction);
        if (mSpeechFragment==null){
            mSpeechFragment=new SpeechFragment();
            fragmentTransaction.add(R.id.ft,mSpeechFragment,"speechFragment");
        }else {
            fragmentTransaction.show(mSpeechFragment);
        }
        fragmentTransaction.commit();

    }
    private void hidFragment(FragmentTransaction fragmentTransaction){
        if (mSpeechFragment!=null){
            fragmentTransaction.hide(mSpeechFragment);
        }

    }


}
