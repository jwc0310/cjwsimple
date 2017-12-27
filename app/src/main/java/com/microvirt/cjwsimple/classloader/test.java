package com.microvirt.cjwsimple.classloader;

/**
 * Created by Administrator on 2017/12/27.
 */

public class test {

    public static void main(String[] args) {

        ClassLoader loader = test.class.getClassLoader();
        while (loader != null){
            System.out.println(loader.toString());
            loader = loader.getParent();
        }

    }

}
