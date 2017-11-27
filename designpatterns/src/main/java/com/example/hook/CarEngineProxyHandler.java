package com.example.hook;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/11/22.
 */

public class CarEngineProxyHandler implements InvocationHandler {

    private Object object;
    public CarEngineProxyHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if ("maxSpeed".equals(method.getName())){
            return 180;
        }

        return method.invoke(object, args);
    }
}
