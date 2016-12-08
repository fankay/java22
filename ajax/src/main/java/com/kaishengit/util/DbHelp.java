package com.kaishengit.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import com.kaishengit.exception.DataAccessException;

public class DbHelp {

    private static Connection getConnection() {
        return ConnectionManager.getConnection();
    }

    public static void update(String sql,Object... params) throws DataAccessException {

        try {
            QueryRunner queryRunner = new QueryRunner(ConnectionManager.getDataSource());
            queryRunner.update(sql, params);

            System.out.println("SQL: " + sql);
        } catch (SQLException ex) {
            throw new DataAccessException("执行"+ sql + "异常",ex);
        }
    }

    public static <T> T query(String sql,ResultSetHandler<T> handler,Object... params) throws DataAccessException {

        QueryRunner queryRunner = new QueryRunner(ConnectionManager.getDataSource());
        try {
            T t = queryRunner.query(sql,handler,params);

            System.out.println("SQL: " + sql);
            return t;
        } catch (SQLException e) {
            throw new DataAccessException("执行"+ sql + "异常",e);
        }
    }

    private static void close(Connection connection) {
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DataAccessException("关闭Connection异常",e);
            }
        }

    }

}
