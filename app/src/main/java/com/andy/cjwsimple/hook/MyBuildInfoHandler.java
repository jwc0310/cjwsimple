package com.andy.cjwsimple.hook;

import android.os.Build;
import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/12/27.
 */

public class MyBuildInfoHandler implements InvocationHandler {

    private Object systemProperitiesClass;

    public MyBuildInfoHandler(Object systemProperitiesClass) {
        this.systemProperitiesClass = systemProperitiesClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.i("MyBuildInfoHandler", method.getName()+", "+args.toString());
        if ("get".equals(method.getName()) && ((String)args[0]).equals(Build.BOARD)) {
                Log.e("MyBuildInfoHandler", "getgetget");
            return "board is Andy";
        }
        return method.invoke(systemProperitiesClass, args);
    }
}
