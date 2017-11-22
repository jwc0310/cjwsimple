package com.example.reflect;

/**
 * Created by Administrator on 2017/11/21.
 */

/**
 * 被反射测试的类
 * Created by liuwei on 17/4/2.
 */
public class BeReflected {
    private String field1 = "I am field1";
    private String field2 = "I am field2";
    private static String staticField = "I am staticField";

    private void method1(){
        System.out.println(BeReflected.class +" I am method1");
    }
    private void method1(String param) {
        System.out.println(BeReflected.class +" I am method1--param = " + param);
    }
    private void method2(){
        System.out.println(BeReflected.class +" I am method2");
    }
    public static void staticMethod(){
        System.out.println(BeReflected.class +" I am staticMethod");
    }
}