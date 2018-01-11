package com.microvirt.cjwsimple.utils;

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

    /**
     * 获取所有接口的baseurl
     *
     * @return
     */
    public static String getBaseHostName() {
        String key = "murl";
        String keyNew = "microvirt.murl";
        String defaultValue = "www";
        return "http://" + getprop(key, getprop(keyNew, defaultValue)) + ".microvirt.com/";
    }

    /**
     * 获取数据打点接口的baseurl，默认为http://stat.microvirt.com/
     *
     * @return
     */
    public static String getEventHostName() {
        String key = "murl";
        String keyNew = "microvirt.murl";
        String defaultValue = "www";
        String defaultEventHost = "stat";
        if (!getprop(key, getprop(keyNew, defaultValue)).equals(defaultValue)) {
            return "http://" + defaultEventHost + "-" + getprop(key, getprop(keyNew, defaultValue)) + ".microvirt.com/";
        }
        return "http://" + defaultEventHost + ".microvirt.com/";
    }

    public static String getHostMacProp() {
        String defaultvalue = "01:23:45:67:89:ab";
        String key = "ro.microvirt.hmac";
        return getprop(key, defaultvalue);
    }

    public static boolean goBrower() {
        String key = "adurl";
        String keyNew = "microvirt.adurl";
        String defaultValue = "0";
        return getprop(key, getprop(keyNew, defaultValue)).equals("1");
    }

    public static boolean showLoading() {
        String keyNew = "microvirt.adurl";
        String key = "adurl";
        String defaultValue = "0";
        return getprop(key, getprop(keyNew, defaultValue)).equals("2");
    }

    public static String getChannel() {
        String key = "microvirt.channel";
        String defaultValue = "7c8a454a";
        return getprop(key, defaultValue);
    }

    public static String getMemuVersion() {
        String key = "microvirt.memu_version";
        String defaultValue = "0";
        return getprop(key, defaultValue);
    }

    public static boolean isCtrlEnabled(int index) {
        String key = "microvirt.ctrl";
        String defaultValue = "0";
        String getprop = getprop(key, defaultValue);
        String value = "0x" + getprop;
        int ctrl = Integer.parseInt(value.replaceAll("^0[x|X]", ""), 16);
        return (ctrl & (1 << index)) != 0;
    }
}
