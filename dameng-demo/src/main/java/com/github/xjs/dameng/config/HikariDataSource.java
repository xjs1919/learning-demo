package com.github.xjs.dameng.config;

import com.zaxxer.hikari.pool.HikariProxyConnection;
import dm.jdbc.driver.DmdbConnection;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;

public class HikariDataSource extends com.zaxxer.hikari.HikariDataSource {
    @Override
    public Connection getConnection() throws SQLException{
        Connection proxyConnection = super.getConnection();
        HikariProxyConnection conn = (HikariProxyConnection) proxyConnection;
        DmdbConnection dmdbConnection = conn.unwrap(DmdbConnection.class);
        dmdbConnection.setServerEncoding(StandardCharsets.UTF_8);
        return proxyConnection;
    }
}
