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

	// 默认配置的数据库连接
	public ExeSql() {
		this.connMysql = new ConnMysql();
	}

	// 如果设置参数,连接指定数据库
	public ExeSql(String url, String userName, String password) {
		this.connMysql = new ConnMysql(url, userName, password);
	}

	// 防止sql注入，list为参数，此方法适合返回对象类型
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
			// 从sql语句中获取表名，并将首字母大写已对应model类名,5为from加一个空格长度
			String className = sql.substring(sql.indexOf("from") + 5, sql.indexOf(" ", sql.indexOf("from") + 5));
			className = className.substring(0, 1).toUpperCase() + className.substring(1, className.length());
			System.out.println(className);
			Class<Model> clazz = (Class<Model>) Class.forName("com.ye.model." + className);
			// 获取数据库表的列名
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
			logger.error("ExeSql 类  sql语句执行异常  ");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			logger.error("反射异常  ");
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
					logger.error("ExeSql 类   ResultSet 创建异常  ");
					e.printStackTrace();
				}
			}
			if (pre != null) {
				try {
					pre.close();
				} catch (SQLException e) {
					logger.error("ExeSql 类    PreparedStatement 创建异常  ");
					e.printStackTrace();
				}
			}
			if (connMysql.conn != null) {
				try {
					connMysql.conn.close();
				} catch (SQLException e) {
					logger.error("ExeSql 类   Conneciton 创建异常  ");
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public void update() {

	}
}
