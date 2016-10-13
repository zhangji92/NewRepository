package com.example.lenovo.amuse;

import android.app.Application;

import com.example.lenovo.amuse.mode.SuccessMode;
import com.example.lenovo.amuse.util.MyFinalDB;

import net.tsz.afinal.FinalDb;

/**
 * Created by lenovo on 2016/9/27.
 *
 */

public class MyApplication extends Application {


    private SuccessMode successMode;

    public SuccessMode getSuccessMode() {
        return successMode;
    }


    public void setSuccessMode(SuccessMode successMode) {
        this.successMode = successMode;
    }
}
