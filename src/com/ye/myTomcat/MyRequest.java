package com.ye.myTomcat;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MyRequest {
	private String url;
	private String method;
	private Map<String, String> allParameter = new HashMap<String, String>();

	public String getParameter(String paramName) {
		return allParameter.get(paramName);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	@Override
	public String toString() {
		return "MyRequest [url=" + url + ", method=" + method + "]";
	}

	public MyRequest(InputStream inputStream) throws IOException {
		String httpRequest = "";
		byte[] httpRequestBytes = new byte[1024];
		int length = 0;
		if ((length = inputStream.read(httpRequestBytes)) > 0) {
			httpRequest = new String(httpRequestBytes, 0, length);
		}
		System.out.println(httpRequest);
		String httpHead = httpRequest.split("\n")[0];
		//get请求带参数
		if (httpHead.indexOf("?") > 0) {
			String url1 = httpHead.split("\\s")[1];
			url = url1.substring(0, url1.indexOf("?"));
			String[] param = url1.substring(url1.indexOf("?") + 1).split("&");
			for (int i = 0; i < param.length; i++) {
				String key = param[i].substring(0, param[i].indexOf("="));
				String value = param[i].substring(param[i].indexOf("=") + 1);
				//将参数以键值对形式放入map集合
				allParameter.put(key, value);
			}
		//get请求不带参数
		} else {
			url = httpHead.split("\\s")[1];
		}

		method = httpHead.split("\\s")[0];
		System.out.println("MyRequest---------------------->" + this);
	}
}
