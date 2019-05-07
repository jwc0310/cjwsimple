package com.andy.cjwsimple.utils;

import android.util.Log;

import java.lang.reflect.Method;

public class Property {

    public static String getprop(String key, String defaultValue) {
        String value = defaultValue;
        String TAG = "property";
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class, String.class);
            value = (String) (get.invoke(c, key, defaultValue));
        } catch (Exception e) {
            Log.d(TAG, "get property error, " + e.getMessage());
        }
        return value;
    }

}
