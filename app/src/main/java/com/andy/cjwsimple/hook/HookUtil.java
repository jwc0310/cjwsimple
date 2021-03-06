package com.andy.cjwsimple.hook;

import android.content.Context;
import android.os.Build;
import android.os.Handler;

import com.andy.cjwsimple.classloader.ClassLoaderActivity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/12/26.
 */

public class HookUtil {


    private Class<?> proxyActivity;
    private Context context;

    public HookUtil(Class<?> proxyActivity, Context context) {
        this.proxyActivity = proxyActivity;
        this.context = context;
    }

    public void hookAms() {
        //一路反射知道拿到IActivityManager
        try {
            Class<?> ActivityManagerNativeClss = Class.forName("android.app.ActivityManagerNative");
            Field defaultField = ActivityManagerNativeClss.getDeclaredField("gDefault");
            defaultField.setAccessible(true);
            Object defaultValue = defaultField.get(null);

            //反射SingleTon
            Class<?> SingletonClass = Class.forName("android.util.Singleton");
            Field mInstance = SingletonClass.getDeclaredField("mInstance");
            mInstance.setAccessible(true);
            //拿到ActivityManager
            Object iActivityManagerObject = mInstance.get(defaultValue);


            //开始动态代理
            Class<?> IActivityManagerIntercept = Class.forName("android.app.IActivityManager");
            AmsInvocationHandler handler = new AmsInvocationHandler(iActivityManagerObject, context);

            Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                    new Class<?>[]{IActivityManagerIntercept}, handler);

            mInstance.set(defaultValue, proxy);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleOpenGLInfo() {
        try {
            Class<?> systemPropertiesClass = Class.forName("android.os.Build");
            Field board = systemPropertiesClass.getDeclaredField("ID");
            board.setAccessible(true);
            board.set(systemPropertiesClass, "Andy888");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void handleBuildinfo() {
        try {
            Class<?> systemPropertiesClass = Class.forName("android.os.Build");
            Field board = systemPropertiesClass.getDeclaredField("BOARD");
            board.setAccessible(true);
            board.set(systemPropertiesClass, "Andy666");
            /*
            Method method = systemPropertiesClass.getMethod(systemPropertiesClass.getName(), String.class, String.class);
            MyBuildInfoHandler handler = new MyBuildInfoHandler(systemPropertiesClass);


            Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class<?>[]{systemPropertiesClass}, handler);
            method.invoke(systemPropertiesClass, proxy);
            */
            Field product = systemPropertiesClass.getDeclaredField("PRODUCT");
            product.setAccessible(true);
            product.set(systemPropertiesClass, "Andy777");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleSystemHandler() {
        try {
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
            currentActivityThreadMethod.setAccessible(true);
            //获取主线程对象
            Object activityThread = currentActivityThreadMethod.invoke(null);

            //获取mH字段
            Field mH = activityThreadClass.getDeclaredField("mH");
            mH.setAccessible(true);

            //获取handler
            Handler handler = (Handler) mH.get(activityThread);

            //获取原始的mCallback字段
            Field mCallback = Handler.class.getDeclaredField("mCallback");
            mCallback.setAccessible(true);
            //这里设置我们自己实现的callback
            mCallback.set(handler, new ActivityThreadHandlerCallback(handler));
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
