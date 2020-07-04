package cn.edu.zucc.personplan.util;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtil2 {
    private static DBUtil2 dbPool;
    private static ComboPooledDataSource dataSource;

    static {
        dbPool = new DBUtil2();
    }

    public DBUtil2() {
        try {
            dataSource = new ComboPooledDataSource();
            dataSource.setUser("root");
            dataSource.setPassword("86948122xuyang");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/dxq?useSSL=false");
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setInitialPoolSize(2);
            dataSource.setMinPoolSize(1);
            dataSource.setMaxPoolSize(10);
            dataSource.setMaxStatements(50);
            dataSource.setMaxIdleTime(60);
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }

    public final static DBUtil2 getInstance() {
        return dbPool;
    }

    public static final Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("¡¨Ω” ß∞‹ ", e);
        }
    }
}

