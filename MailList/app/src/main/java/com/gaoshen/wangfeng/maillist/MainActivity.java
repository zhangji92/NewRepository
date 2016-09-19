package com.gaoshen.wangfeng.maillist;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {

    private ListView listView;
    private List<ModeUser> listName = new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        listView = (ListView) findViewById(R.id.mailList);
        /**
         * 1.系统给了我们一个接口访问
         * 2.3.4.5也是查询条件，这我们并不需要
         * 并且返回一个Cursor类型的参数
         */
        Cursor query = this.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        //创建一个对象进行储存
        String name; //联系人
        String phone; //电话号码
        String id;
        //我们获取到这些信息之后遍历出来
        while (query.moveToNext()) {
            //获取名字就需要Phone.DISPLAY_NAME
            name = query.getString(query.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            //获取num字段需要Phone.NUMBER
            phone = query.getString(query.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            id = query.getString(query.getColumnIndex(ContactsContract.RawContacts.Data.RAW_CONTACT_ID));
            Log.i("字段", name + ":" + phone);

            ModeUser mu = new ModeUser(name, phone, id);
            listName.add(mu);
        }
        myAdapter = new MyAdapter(listName, this);
        addUser();
        listView.setAdapter(myAdapter);
        listView.setOnItemLongClickListener(this);
        listView.setOnItemClickListener(this);
    }

    /**
     * 点击跳转到添加用户信息页
     *
     * @param view
     */
    public void buttonAdd(View view) {
        switch (view.getId()) {
            case R.id.addUser:
                Intent intent = new Intent(this, AddUser.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 获取添加的用户数据
     */
    public void addUser() {

        String name = "";
        String photo = "";

        Intent intent = getIntent();
        ModeUser mo = (ModeUser) intent.getSerializableExtra("user");

        if (mo != null) {
            listName.add(mo);
            Log.e("addUser_**** ", mo.getName() + "----" + mo.getPhone());
            name = mo.getName().toString().trim();
            photo = mo.getPhone().toString().trim();
        }


        if (!name.equals("") && !photo.equals("")) {
            ContentResolver contentResolver = getContentResolver();
            ContentValues contentValues = new ContentValues();
            Uri rawUri = contentResolver.insert(ContactsContract.RawContacts.CONTENT_URI, contentValues);
            long rawContactId = ContentUris.parseId(rawUri);

            //往data表入姓名数据
            contentValues.clear();
            contentValues.put(ContactsContract.RawContacts.Data.RAW_CONTACT_ID, rawContactId);
            contentValues.put(ContactsContract.RawContacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
            contentValues.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, mo.getName());
            getContentResolver().insert(android.provider.ContactsContract.Data.CONTENT_URI, contentValues);

            //往data表入电话数据
            contentValues.clear();
            contentValues.put(android.provider.ContactsContract.Contacts.Data.RAW_CONTACT_ID, rawContactId);
            contentValues.put(ContactsContract.RawContacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
            contentValues.put(ContactsContract.CommonDataKinds.Phone.NUMBER, mo.getPhone());
            contentValues.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
            getContentResolver().insert(android.provider.ContactsContract.Data.CONTENT_URI, contentValues);
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        }


        myAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {


        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setItems(new String[]{"查看", "编辑", "删除"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0://查看
                                Intent intent = new Intent(MainActivity.this, SelectUser.class);
                                intent.putExtra("ModeUser", listName.get(i));
                                startActivity(intent);
                                break;
                    case 1://编辑
                        Intent intent2 = new Intent(MainActivity.this,AddUser.class);
                        intent2.putExtra("edit",listName.get(i));
                        startActivity(intent2);
                        break;
                            case 2://删除
                                ContentResolver resolver = getContentResolver();
                                Uri uri = Uri.parse("content://com.android.contacts/data");
                                int res = resolver.delete(uri, "raw_contact_id=?", new String[]{listName.get(i).getId()});

                                if (res > 0) {
                                    Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                                    listName.remove(listName.get(i));
                                    myAdapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(MainActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                                }
                                break;

                        }
                    }
                }

        );
        AlertDialog alert = builder.create();
        Window window = alert.getWindow();
        alert.show();
        window.setGravity(Gravity.BOTTOM);


        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Intent intent = new Intent(MainActivity.this, SelectUser.class);
        intent.putExtra("ModeUser", listName.get(i));
        startActivity(intent);
    }
}
