package com.kaishengit.util;


import java.sql.Connection;
import java.sql.SQLException;

import com.kaishengit.exception.DataAccessException;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class ConnectionManager {

    private static String DRIVER;//= "com.mysql.jdbc.Driver";
    private static String URL;//= "jdbc:mysql:///lib_22";
    private static String USERNAME;//= "root";
    private static String PASSWORD;//= "rootroot";
    private static BasicDataSource dataSource = new BasicDataSource();

    static {


        DRIVER = Config.get("jdbc.driver");
        URL = Config.get("jdbc.url");
        USERNAME = Config.get("jdbc.username");
        PASSWORD = Config.get("jdbc.password");


        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        dataSource.setInitialSize(5);
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(20);
        dataSource.setMaxWaitMillis(5000);

    }

    /**
     * 获取数据库连接池
     *
     * @return
     */
    public static DataSource getDataSource() {
        return dataSource;
    }


    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new DataAccessException("获取数据库连接异常", e);
        }
        return connection;
    }

}
