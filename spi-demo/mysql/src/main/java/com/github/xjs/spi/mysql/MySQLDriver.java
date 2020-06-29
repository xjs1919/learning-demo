package com.github.xjs.spi.mysql;

import com.github.xjs.spi.sql.Connection;
import com.github.xjs.spi.sql.JdbcDriver;

public class MySQLDriver implements JdbcDriver {
    @Override
    public Connection getConnection() {
        return new Connection(){
        };
    }
}
