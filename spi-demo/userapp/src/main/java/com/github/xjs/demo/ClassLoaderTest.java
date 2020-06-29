package com.github.xjs.demo;

public class ClassLoaderTest {
    public static void main(String[] args) {
        ClassLoader cl= ClassLoaderTest.class.getClassLoader();
        System.out.println(cl);
        //sun.misc.Launcher$AppClassLoader@18b4aac2
        ClassLoader clp=cl.getParent();
        System.out.println(clp);
        //sun.misc.Launcher$ExtClassLoader@4554617c
        ClassLoader clpp=clp.getParent();
        System.out.println(clpp);
        //null
        //输出的是环境变量的值,AppClassLoader的默认加载类的路径
        String s =System.getProperty("java.class.path");
        System.out.println(s);
        // 这是ExtClassLoader的默认加载类的路径
        String s2 =System.getProperty("java.ext.dirs");
        System.out.println(s2);
        // 这是BootStrapLoader的默认加载类的路径
        String s3 =System.getProperty("sun.boot.class.path");
        System.out.println(s3);
    }
}
