package com.microvirt.cjwsimple.jni;

/**
 * Created by Administrator on 2017/12/27.
 */

public class MyJni {
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    public native String stringFromJNI();
}
