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
		logger.info("开始加载jdbc.properties文件内容.......");
		props = new Properties();
		InputStream in = null;
		try {
			// 第一种，通过类加载器进行获取properties文件流
			in = PropertiesUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
			// 第二种，通过类进行获取properties文件流
			// in = PropertyUtil.class.getResourceAsStream("/jdbc.properties");
			props.load(in);
			driver = props.getProperty("driver");
			url = props.getProperty("url");
			userName = props.getProperty("userName");
			password = props.getProperty("password");
		} catch (FileNotFoundException e) {
			logger.error("jdbc.properties文件未找到");
		} catch (IOException e) {
			logger.error("出现IOException");
		} finally {
			try {
				if (null != in) {
					in.close();
				}
			} catch (IOException e) {
				logger.error("jdbc.properties文件流关闭出现异常");
			}
		}
		logger.info("加载properties文件内容完成...........");
		logger.info("properties文件内容：" + props);
	}
}
