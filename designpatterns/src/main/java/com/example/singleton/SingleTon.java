package com.example.singleton;

/**
 * 单例模式：线程安全和非安全， 懒汉和恶汉模式
 * Created by Administrator on 2017/11/13.
 */

public class SingleTon {

    public static void main(String[] args) {

    }

    public static class Singleton1 {

        private Singleton1() {

        }

        //懒
        private static Singleton1 instance;

        private static Singleton1 getInstance() {
            if (instance == null)
                instance = new Singleton1();
            return instance;
        }

        //饿
        private static Singleton1 instance2 = new Singleton1();

        private static Singleton1 getInstance2() {
            return instance2;
        }

        //线程安全单例
        private static Singleton1 getInstance3() {
            if (instance == null) {
                synchronized (SingleTon.class) {
                    if (instance == null) {
                        instance = new Singleton1();
                    }
                }
            }
            return instance;
        }

    }


}
