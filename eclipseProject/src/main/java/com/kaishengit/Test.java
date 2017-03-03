package com.kaishengit;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Types;

public class Test {
	
	@org.junit.Test
	public void testQuery() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql:///kaishengit_db","root","rootroot");
			
			String sql = "{call p_1()}";
			CallableStatement stat = conn.prepareCall(sql);
			
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				String prodName = rs.getString("prod_name");
				System.out.println(prodName);
			}
			
			rs.close();
			stat.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@org.junit.Test
	public void testInParam() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql:///kaishengit_db","root","rootroot");
			
			String sql = "{call p_4(?)}";
			CallableStatement stat = conn.prepareCall(sql);
			stat.setString(1, "Ruby2°à");
			
			stat.executeUpdate();
			
			
			stat.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@org.junit.Test
	public void testOutParam() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql:///kaishengit_db","root","rootroot");
			
			String sql = "{call p_2(?,?)}";
			CallableStatement stat = conn.prepareCall(sql);
			stat.registerOutParameter(1, Types.DECIMAL);
			stat.registerOutParameter(2, Types.DECIMAL);
			
			stat.executeUpdate();
			
			float max = stat.getFloat(1);
			float min = stat.getFloat(2);
			
			System.out.println(min + " : " + max);
			
			
			stat.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
