package com.kaishengit.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import com.kaishengit.exception.DataAccessException;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbHelp {

    private static Logger logger = LoggerFactory.getLogger(DbHelp.class);

    private static Connection getConnection() {
        return ConnectionManager.getConnection();
    }

    public static void update(String sql,Object... params) throws DataAccessException {
        try {
            QueryRunner queryRunner = new QueryRunner(ConnectionManager.getDataSource());
            queryRunner.update(sql, params);

            logger.debug("SQL: {}",sql);
        } catch (SQLException ex) {
            logger.error("执行{}异常",sql);
            throw new DataAccessException("执行"+ sql + "异常",ex);
        }
    }

    public static Integer insert(String sql,Object... params) throws DataAccessException {
        try {
            QueryRunner queryRunner = new QueryRunner(ConnectionManager.getDataSource());
            logger.debug("SQL: {}",sql);
            return queryRunner.insert(sql,new ScalarHandler<Long>(),params).intValue();
        } catch (SQLException ex) {
            logger.error("执行{}异常",sql);
            throw new DataAccessException("执行"+ sql + "异常",ex);
        }
    }

    public static <T> T query(String sql,ResultSetHandler<T> handler,Object... params) throws DataAccessException {

        QueryRunner queryRunner = new QueryRunner(ConnectionManager.getDataSource());
        try {
            T t = queryRunner.query(sql,handler,params);

            logger.debug("SQL: {}",sql);
            return t;
        } catch (SQLException e) {
            logger.error("执行{}异常",sql);
            throw new DataAccessException("执行"+ sql + "异常",e);
        }
    }

    private static void close(Connection connection) {
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("关闭Connection异常");
                throw new DataAccessException("关闭Connection异常",e);
            }
        }

    }

}
