package com.gaoshen.wangfeng.maillist;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/9/18.
 */
public class AddUser extends AppCompatActivity implements View.OnClickListener{

    private EditText editName;
    private EditText editPhoto;
    private Button butSeva;
    private String name;
    private String photo;
    private ModeUser mo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adduser);

        editName= (EditText) findViewById(R.id.name);
        editPhoto= (EditText) findViewById(R.id.photo);
        butSeva= (Button) findViewById(R.id.seva);
        butSeva.setOnClickListener(this);

        mo= (ModeUser) getIntent().getSerializableExtra("edit");
        if(mo!=null){//修改時才賦值
            editName.setText(mo.getName());
            editPhoto.setText(mo.getPhone());
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.seva:
                if(mo==null){//保存數據
                    name=editName.getText().toString();
                    photo=editPhoto.getText().toString();
                    if(!name.equals("") && !photo.equals("")){
                        Log.e("onClick: ",name+"---"+photo );
                        ModeUser mo=new ModeUser(name,photo);
                        Log.e("ModeUser: ",mo.getName()+"---"+mo.getPhone());
                        Intent intent=new Intent(this,MainActivity.class);
                        intent.putExtra("user",mo);
                        setResult(RESULT_OK);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(this,"请输入姓名或密码",Toast.LENGTH_SHORT).show();
                    }
                }else{//修改數據
                    name=editName.getText().toString();
                    photo=editPhoto.getText().toString();
                    mo.setName(name);
                    mo.setPhone(photo);

                    Log.e( "updateUser","--------------"+ mo.getPhone() );
                    ContentValues values = new ContentValues();
                    values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, mo.getPhone());
                    values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);


                    String where = ContactsContract.Data.RAW_CONTACT_ID + "=? AND " + ContactsContract.Data.MIMETYPE + "=?";
                    String[] selectionArgs = new String[] { mo.getId(), ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE };

                    int res = getContentResolver().update(ContactsContract.Data.CONTENT_URI, values, where, selectionArgs);

                    values.clear();
                    String[] selectionArgs1 = new String[]{mo.getId(), ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE  };
                    values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, mo.getName());
                    values.put(ContactsContract.RawContacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
                    int aa=getContentResolver().update(ContactsContract.Data.CONTENT_URI, values, where, selectionArgs1);
                    Log.e("updateUser: ", "----------------------"+res);
                    if(res>0 || aa>0){
                        Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
//                        Intent intent=new Intent(this,MainActivity.class);
//                        startActivity(intent);
//                        finish();
                    }

                }

                break;
        }
    }
}
