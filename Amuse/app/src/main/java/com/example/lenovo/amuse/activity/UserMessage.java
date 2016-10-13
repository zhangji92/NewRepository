package com.example.lenovo.amuse.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.lenovo.amuse.MyApplication;
import com.example.lenovo.amuse.R;
import com.example.lenovo.amuse.mode.ResultCodeBean;
import com.example.lenovo.amuse.util.BaseUri;
import com.example.lenovo.amuse.util.MyFinalDB;
import com.makeramen.roundedimageview.RoundedImageView;

import net.tsz.afinal.FinalBitmap;

public class UserMessage extends BaseActivity {
    MyApplication myApplication;
    RoundedImageView roundedImageView;
    RadioButton radioButton_boy;
    RadioButton radioButton_girl;
    TextView textView_phone;
    EditText editText_name;
    EditText editText_age;
    EditText editText_address;
    FinalBitmap finalBitmap;
    MyFinalDB myFinalDB;
    ResultCodeBean resultCodeBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_message);
        myApplication = (MyApplication) getApplication();
        //头像
        roundedImageView = (RoundedImageView) findViewById(R.id.userImg);
        //用户
        textView_phone = (TextView) findViewById(R.id.userPhone);
        //昵称
        editText_name = (EditText) findViewById(R.id.userName);
        radioButton_boy = (RadioButton) findViewById(R.id.boy);
        radioButton_girl = (RadioButton) findViewById(R.id.girl);

        //年龄
        editText_age = (EditText) findViewById(R.id.userAge);
        //地址
        editText_address = (EditText) findViewById(R.id.userAddress);
        //图片
        finalBitmap = FinalBitmap.create(this);
        //初始化数据
        init();
    }

    /**
     * 初始化数据
     */
    private void init() {
        if (myApplication.getSuccessMode() != null) {
//            resultCodeBean = myApplication.getSuccessMode().getResultCode();
            myFinalDB = MyFinalDB.getInstance(UserMessage.this);
            resultCodeBean = myFinalDB.selectFinalDB();
            String img=resultCodeBean.getImgUrl();
            finalBitmap.display(roundedImageView, BaseUri.BASE + resultCodeBean.getImgUrl());
            textView_phone.setText(resultCodeBean.getMobile());
            editText_name.setText(resultCodeBean.getNickname());
            editText_age.setText(resultCodeBean.getAge());
            editText_address.setText(resultCodeBean.getAddress());
            if (resultCodeBean.getGender().contains("1")) {
                radioButton_boy.setChecked(true);
            } else {
                radioButton_girl.setChecked(true);
            }
        }

    }

    private void getData() {
        String name = editText_name.getText().toString();
        String token = resultCodeBean.getToken();
        String img=resultCodeBean.getImgUrl();
        String age = editText_age.getText().toString();
        String address = editText_address.getText().toString();
        String sex = "1";
        if (radioButton_boy.isChecked() == true) {
            sex = "1";
        } else if (radioButton_girl.isChecked() == true) {
            sex = "2";
        }

    }


}
