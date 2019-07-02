package com.zxj.skin.core.logger;

import android.util.Log;

public class L {

    private static final String TAG = "Skin-Core";


    public static void i(String msg) {
        Log.i(TAG, msg);
    }

    public static void e(String msg) {
        Log.e(TAG, msg);
    }


}
