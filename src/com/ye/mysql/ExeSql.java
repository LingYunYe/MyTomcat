package com.ye.mysql;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.ye.model.Model;
import com.ye.model.User_t;

public class ExeSql {
	private static Logger logger = Logger.getLogger(ExeSql.class);
	private ConnMysql connMysql;

	// Ĭ�����õ����ݿ�����
	public ExeSql() {
		this.connMysql = new ConnMysql();
	}

	// ������ò���,����ָ�����ݿ�
	public ExeSql(String url, String userName, String password) {
		this.connMysql = new ConnMysql(url, userName, password);
	}

	// ��ֹsqlע�룬listΪ�������˷����ʺϷ��ض�������
	public Model queryObject(String sql, List<Object> list) {
		PreparedStatement pre = null;
		ResultSet res = null;
		try {
			pre = connMysql.conn.prepareStatement(sql);
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					pre.setObject(i + 1, list.get(i));
				}
			}
			logger.info(pre.toString());
			res = pre.executeQuery();
			// ��sql����л�ȡ��������������ĸ��д�Ѷ�Ӧmodel����,5Ϊfrom��һ���ո񳤶�
			String className = sql.substring(sql.indexOf("from") + 5, sql.indexOf(" ", sql.indexOf("from") + 5));
			className = className.substring(0, 1).toUpperCase() + className.substring(1, className.length());
			System.out.println(className);
			Class<Model> clazz = (Class<Model>) Class.forName("com.ye.model." + className);
			// ��ȡ���ݿ�������
			ResultSetMetaData rsmd = res.getMetaData();
			int count = rsmd.getColumnCount();
			Model[] colNames = new Model[count];
			for (int i = 0; i < count; i++) {
				String str = rsmd.getColumnName(i + 1);
				str = str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
				colNames[i] = new Model(str, rsmd.getColumnTypeName(i + 1).toLowerCase());
				System.out.println(colNames[i].toString());
			}
			Model model = clazz.newInstance();
			while (res.next()) {
				for (int j = 0; j < count; j++) {
					clazz.getMethod("set" + colNames[j].getColName(),
							Class.forName("java.lang." + colNames[j].getColTypeName()))
							.invoke(model, res.getObject(colNames[j].getColName()));
				}
			}
			return model;
		} catch (SQLException e) {
			logger.error("ExeSql ��  sql���ִ���쳣  ");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			logger.error("�����쳣  ");
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} finally {
			if (res != null) {
				try {
					res.close();
				} catch (SQLException e) {
					logger.error("ExeSql ��   ResultSet �����쳣  ");
					e.printStackTrace();
				}
			}
			if (pre != null) {
				try {
					pre.close();
				} catch (SQLException e) {
					logger.error("ExeSql ��    PreparedStatement �����쳣  ");
					e.printStackTrace();
				}
			}
			if (connMysql.conn != null) {
				try {
					connMysql.conn.close();
				} catch (SQLException e) {
					logger.error("ExeSql ��   Conneciton �����쳣  ");
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public void update() {

	}
}
