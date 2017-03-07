/** 
* @ClassName: Test 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author jinjianghao 
* @date 2017年2月14日 下午10:01:25 
* @version 1.0
* 
*/  

package com.kaishengit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/** 
* history:
* 1. jinjianghao 2017年2月14日 下午10:01:25 创建Test 
* 
* 
* 
*/

public class Test {
	public static void main(String[] args) {

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.
                    getConnection("jdbc:oracle:thin:@127.0.0.1:1521:ORCL","scott","tiger");
            String sql = "select * from emp";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                String name = rs.getString("ename");
                System.out.println(name);
            }
            rs.close();
            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
