package com.gaoshen.wangfeng.maillist;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/18.
 */
public class ModeUser implements Serializable {

    /**
     * 数据存储
     * Created by LGL on 2016/1/24.
     */


    private String name;
    private String phone;
    private String id;

    public ModeUser(String name) {
        setName(name);
    }


    public ModeUser(String name, String phone) {
        setName(name);
        setPhone(phone);
    }

    public ModeUser(String name, String phone, String id) {
        this.name = name;
        this.phone = phone;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
