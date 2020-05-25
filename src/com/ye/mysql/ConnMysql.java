package com.ye.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.ye.util.PropertiesUtil;

// 简单实用 jdbc 连接 MySQL
public class ConnMysql {
	private static Logger logger = Logger.getLogger(ConnMysql.class);
	public Connection conn = null;

	@SuppressWarnings("finally")
	public Connection getConnection(String url, String userName, String password) {
		try {
			System.out.println(url + userName + password);
			Class.forName(PropertiesUtil.driver);
			conn = DriverManager.getConnection(url, userName, password);
			System.out.println(conn);
			logger.info("MySQL 连接成功");
		} catch (ClassNotFoundException e) {
			logger.error("ConnMysql 加载异常");
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error("ConnMysql 类   中  mysql 连接异常");
			e.printStackTrace();
		} finally {
			return conn;
		}
	}
	//默认参数连接
	public ConnMysql() {
		this.conn = getConnection(PropertiesUtil.url, PropertiesUtil.userName, PropertiesUtil.password);
	}
	//指定参数连接
	public ConnMysql(String url, String userName, String password) {
		this.conn = getConnection(url, userName, password);
	}
}
