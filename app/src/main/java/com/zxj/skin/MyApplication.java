package com.zxj.skin;

import android.app.Application;

import com.zxj.skin.core.SkinManager;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.init(this);
    }
}
