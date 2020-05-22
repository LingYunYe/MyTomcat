package com.ye.myTomcat;

import java.io.IOException;
import java.io.OutputStream;

public class MyResponse {
	private OutputStream outputStream;
	public MyResponse(OutputStream outputStream) {
		this.outputStream = outputStream;
	}
	public void write(String content) throws IOException {
		//http œÏ”¶–≠“È
		StringBuffer httpResponse = new StringBuffer();
		httpResponse.append("HTTP/1.1 200 OK\n")
		.append("Content-Type: text/html;charset=UTF-8\n")
		.append("\r\n")
		.append("<html><body>")
		.append(content)
		.append("</body></html>");
		outputStream.write(httpResponse.toString().getBytes());
		outputStream.close();
	}
}
