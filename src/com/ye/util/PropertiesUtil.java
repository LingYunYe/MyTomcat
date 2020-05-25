package com.ye.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesUtil {
	private static Logger logger = Logger.getLogger(PropertiesUtil.class);
	private static Properties props;
	public static String userName;
	public static String password;
	public static String url;
	public static String driver;
	static {
		loadProps();
	}

	synchronized static private void loadProps() {
		logger.info("��ʼ����jdbc.properties�ļ�����.......");
		props = new Properties();
		InputStream in = null;
		try {
			// ��һ�֣�ͨ������������л�ȡproperties�ļ���
			in = PropertiesUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
			// �ڶ��֣�ͨ������л�ȡproperties�ļ���
			// in = PropertyUtil.class.getResourceAsStream("/jdbc.properties");
			props.load(in);
			driver = props.getProperty("driver");
			url = props.getProperty("url");
			userName = props.getProperty("userName");
			password = props.getProperty("password");
		} catch (FileNotFoundException e) {
			logger.error("jdbc.properties�ļ�δ�ҵ�");
		} catch (IOException e) {
			logger.error("����IOException");
		} finally {
			try {
				if (null != in) {
					in.close();
				}
			} catch (IOException e) {
				logger.error("jdbc.properties�ļ����رճ����쳣");
			}
		}
		logger.info("����properties�ļ��������...........");
		logger.info("properties�ļ����ݣ�" + props);
	}
}
