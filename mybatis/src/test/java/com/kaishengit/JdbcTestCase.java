package com.kaishengit;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcTestCase {

    @Test
    public void test() throws Exception {

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///mydb","root","rootroot");
        PreparedStatement statement = null;
        //开启事务
        connection.setAutoCommit(false);

        try {
            String sql = "INSERT into t_user(username,password) values('z1','111')";
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();

            String sql2 = "INSERT intos t_user(username,password) values('z2','211')";
            statement = connection.prepareStatement(sql2);
            statement.executeUpdate();

            connection.commit(); //提交事务
        } catch (SQLException ex) {
            ex.printStackTrace();
            connection.rollback(); //回滚事务
        } finally {
            statement.close();
            connection.close();
        }




    }

}
