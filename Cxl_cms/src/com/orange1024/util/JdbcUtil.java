package com.orange1024.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 
 * @author Mr_Cxl
 * @Title JdbcUtil
 * @data 2020年7月17日 下午2:24:36
 * @Describe::写一个连接数据库的工具类，方便以后的直接调用
 */
public class JdbcUtil {
	/*全局变量*/
	static String URL = "jdbc:mysql://localhost:3306/study";
	static String user = "root";
	static String password = "orange1024";
	
	//连接数据库
	
	public static Connection getConnection() {	
		Connection conn = null;
		try {
			//加载数据驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获得数据库连接
			conn = DriverManager.getConnection(URL, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	//释放连接的资源
	public static void close(Connection conn) {
		try {
			if(conn==null) {
				conn.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//释放，连接，指令对象
	public static void close(Connection conn,PreparedStatement pstm) {
		try {
			if(conn==null) {
				pstm.close();
			}
			if(pstm == null) {
				pstm.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//释放，连接，指令对象，结果集
	public static void close(Connection conn,PreparedStatement pstm,ResultSet rs) {
		close(conn,pstm);
		try {
			if(rs==null) {
				rs.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}

