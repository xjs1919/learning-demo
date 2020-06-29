package com.github.xjs.demo;

import com.github.xjs.spi.sql.Connection;
import com.github.xjs.spi.sql.DriverManager;

public class UserMain {
    public static void main(String[] args) {
        Connection connection =  DriverManager.getConnection();
        System.out.println(connection.getClass().getName());
        java.sql.DriverManager manager = null;
        Thread.currentThread().setContextClassLoader(ClassLoader.getSystemClassLoader());
    }
}
