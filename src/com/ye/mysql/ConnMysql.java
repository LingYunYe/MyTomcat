package com.ye.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.ye.util.PropertiesUtil;

// ��ʵ�� jdbc ���� MySQL
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
			logger.info("MySQL ���ӳɹ�");
		} catch (ClassNotFoundException e) {
			logger.error("ConnMysql �����쳣");
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error("ConnMysql ��   ��  mysql �����쳣");
			e.printStackTrace();
		} finally {
			return conn;
		}
	}
	//Ĭ�ϲ�������
	public ConnMysql() {
		this.conn = getConnection(PropertiesUtil.url, PropertiesUtil.userName, PropertiesUtil.password);
	}
	//ָ����������
	public ConnMysql(String url, String userName, String password) {
		this.conn = getConnection(url, userName, password);
	}
}
