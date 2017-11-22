package com.example.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/11/21.
 */

public class test {

    public static void main(String[] args) {
        try {
            // 1、通过反射获取BeReflected所属的类
            Class<?> beReflectedClass = Class.forName("com.example.reflect.BeReflected");
            System.out.println(test.class + ", " + beReflectedClass);

            // 2、通过反射创建实例化一个类
            Object beReflected1 = beReflectedClass.newInstance();
            System.out.println(test.class + ", " + beReflected1);

            // 3、通过反射调用一个私有方法和成员变量
            Method method = beReflectedClass.getDeclaredMethod("method1");
            method.setAccessible(true); // 将此值设为true即可访问私有的方法和成员变量
            method.invoke(beReflected1); // 访问普通成员变量和方法是需要在调用invoke方法是传入该类的对象

            Field field1 = beReflectedClass.getDeclaredField("field1");
            field1.setAccessible(true);
            System.out.println("field1 改变前 " + field1.get(beReflected1));
            field1.set(beReflected1, "gaibianhou");
            System.out.println("field1 改变hou " + field1.get(beReflected1));

            // 4、通过反射调用一个静态的方法和变量
            Method staticMethod = beReflectedClass.getDeclaredMethod("staticMethod");
            staticMethod.invoke(null);

            Field staticField = beReflectedClass.getDeclaredField("staticField");
            staticField.setAccessible(true);

            // 5、通过反射访问一个带参数的方法
            Method method1 = beReflectedClass.getDeclaredMethod("method1", String.class);
            method1.setAccessible(true);
            method1.invoke(beReflected1, "params");

            // 6、遍历类中所有的方法和成员变量
            for (Method method2 : beReflectedClass.getDeclaredMethods()) {

            }

            for (Field field : beReflectedClass.getDeclaredFields()) {

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

}
