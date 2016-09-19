package com.gaoshen.wangfeng.maillist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SelectUser extends AppCompatActivity implements View.OnClickListener{

    private TextView textViewName,textViewPhoto;
    private ImageView imageViewFh,bodaImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);

        ModeUser mo= (ModeUser) getIntent().getSerializableExtra("ModeUser");
        imageViewFh= (ImageView) findViewById(R.id.fanhui);
        textViewName= (TextView) findViewById(R.id.selectName);
        textViewPhoto= (TextView) findViewById(R.id.selectPhoto);
        bodaImage= (ImageView) findViewById(R.id.bd);
        textViewName.setText(mo.getName());
        textViewPhoto.setText(mo.getPhone());
        imageViewFh.setOnClickListener(this);
        bodaImage.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fanhui:
                Intent intent=new Intent(SelectUser.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.bd:

                Uri uri = Uri.parse("tel:" + textViewPhoto.getText().toString().trim());
                Intent in = new Intent();
                in.setAction(Intent.ACTION_DIAL);
                in.setData(uri);
                startActivity(in);
                break;
        }
    }
}
