package com.github.xjs.spi.sql;

import java.util.Iterator;
import java.util.ServiceLoader;

public class DriverManager {

    public static Connection getConnection(){
        ServiceLoader<JdbcDriver> drivers = ServiceLoader.load(JdbcDriver.class);
        Iterator<JdbcDriver> iterator = drivers.iterator();
        if(iterator.hasNext()){
            JdbcDriver driver = iterator.next();
            return driver.getConnection();
        }
        java.sql.DriverManager driverManager = null;
        return null;
    }
}
