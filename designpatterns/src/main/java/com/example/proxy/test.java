package com.example.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/11/13.
 */

public class test {

    public static void main(String[] args){
        Customer customer = new Customer();
        customer.setCash(120000);
        BuyCarProxy buyCarProxy = new BuyCarProxy(customer);
        buyCarProxy.buyCar();


        Customer customer1 = new Customer();
        customer1.setCash(123);
        InvocationHandler handler = new DynamicProxy(customer1);
        IBuyCar buyCar = (IBuyCar) Proxy.newProxyInstance(handler.getClass().getClassLoader(),
                customer1.getClass().getInterfaces(), handler);
        buyCar.buyCar();
    }

}
