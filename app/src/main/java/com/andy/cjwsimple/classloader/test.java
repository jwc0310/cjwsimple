package com.andy.cjwsimple.classloader;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

/**
 * Created by Administrator on 2017/12/27.
 */

public class test {

    public static void main(String[] args) {

//        DexClassLoader dexClassLoader = new DexClassLoader();
//        PathClassLoader pathClassLoader = new PathClassLoader()

        ClassLoader loader = test.class.getClassLoader();
        while (loader != null){
            System.out.println(loader.toString());
            loader = loader.getParent();
            System.out.println(loader.toString());
        }

    }

}
