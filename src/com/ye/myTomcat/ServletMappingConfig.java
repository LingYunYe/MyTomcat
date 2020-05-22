package com.ye.myTomcat;

import java.util.ArrayList;
import java.util.List;

public class ServletMappingConfig {
	public static List<ServletMapping> servletMappingList = new ArrayList<ServletMapping>();
	//�൱web.xml��ͨ��<servlet>��<servlet-mapping>������ָ���ĸ�URL�����ĸ�servlet���д���
	static {
		servletMappingList.add(new ServletMapping("findServlet", "/find", "com.ye.myTomcat.FindServlet"));
		servletMappingList.add(new ServletMapping("helloWorldServlet", "/hello", "com.ye.myTomcat.HelloWorldServlet"));
		servletMappingList.add(new ServletMapping("faviconServlet", "/favicon.ico", "com.ye.myTomcat.FaviconServlet"));
	}
}
