package com.microvirt.cjwsimple.hook;

import android.app.Activity;
import android.app.Instrumentation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/11/22.
 */

public class ActivityThreadHookHelper {

    /**
     * hook Instrumentation
     * activity看起来中的Instrumentation看起来像是context中的Instrumentation传递过去的
     */

    public static void doContextStartHook() {
        try {
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
            Object activityThread = currentActivityThreadMethod.invoke(null);

            Field mInstrumentationField = activityThreadClass.getDeclaredField("mInstrumentation");
            mInstrumentationField.setAccessible(true);
            Instrumentation instrumentation = (Instrumentation) mInstrumentationField.get(activityThread);
            mInstrumentationField.set(activityThread, new EvilInstrumentation(instrumentation));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void doActivityStartHook(Activity activity) {
        try {
            Field mInstrumentationField = Activity.class.getDeclaredField("mInstrumentation");
            mInstrumentationField.setAccessible(true);
            Instrumentation instrumentation = (Instrumentation) mInstrumentationField.get(activity);
            mInstrumentationField.set(activity, new EvilInstrumentation(instrumentation));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
