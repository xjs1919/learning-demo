package com.github.xjs.spi.sql;

public interface JdbcDriver {

    public Connection getConnection();

}
