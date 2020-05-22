package com.ye.myTomcat;

import java.util.ArrayList;
import java.util.List;

public class ServletMappingConfig {
	public static List<ServletMapping> servletMappingList = new ArrayList<ServletMapping>();
	//相当web.xml中通过<servlet>和<servlet-mapping>来进行指定哪个URL交给哪个servlet进行处理。
	static {
		servletMappingList.add(new ServletMapping("findServlet", "/find", "com.ye.myTomcat.FindServlet"));
		servletMappingList.add(new ServletMapping("helloWorldServlet", "/hello", "com.ye.myTomcat.HelloWorldServlet"));
		servletMappingList.add(new ServletMapping("faviconServlet", "/favicon.ico", "com.ye.myTomcat.FaviconServlet"));
	}
}
