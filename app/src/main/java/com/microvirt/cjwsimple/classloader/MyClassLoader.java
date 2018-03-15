package com.microvirt.cjwsimple.classloader;

/**
 * 1,根据指定类的名称，找到或者生成其对应的字节码
 * 然后根据字节码中定义出一个java类
 * 2,加载java应用所需的资源
 */
public class MyClassLoader extends ClassLoader {

    /**
     类加载器分类：系统提供的和开发人员编写的

         引导类加载器（bootstrap class loader）
            它用来加载 Java 的核心库，是用原生代码来实现的，
            并不继承自 java.lang.ClassLoader。
         扩展类加载器（extensions class loader）
            它用来加载 Java 的扩展库。Java 虚拟机的实现会提供一个扩展库目录。
            该类加载器在此目录里面查找并加载 Java 类。
         系统类加载器（system class loader）
            它根据 Java 应用的类路径（CLASSPATH）来加载 Java 类。
            一般来说，Java 应用的类都是由它来完成加载的。
            可以通过 ClassLoader.getSystemClassLoader()来获取它。

     */


}
