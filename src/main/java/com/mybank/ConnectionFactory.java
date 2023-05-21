package com.mybank;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    String user = System.getenv("userMySql");
    String password = System.getenv("passwordMySql");

    public Connection getConnection() {
        try {
            return createDataSource().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private HikariDataSource createDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/mybank");
        config.setUsername(user);
        config.setPassword(password);
        config.setMaximumPoolSize(25);

        return new HikariDataSource(config);
    }
}
