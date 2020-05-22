package com.ye.myTomcat;

import java.io.IOException;

public class HelloWorldServlet extends MyServlet {

	@Override
	public void doGet(MyRequest myRequest, MyResponse myResponse) {
		String name = myRequest.getParameter("name");
		String password  = myRequest.getParameter("password");
		StringBuffer content = new StringBuffer();
		content.append("method      &nbsp;&nbsp;&nbsp;GET  </br> ")
				.append("className    &nbsp; &nbsp; &nbsp;HelloWorldServlet </br>")
				.append("name    &nbsp; &nbsp; &nbsp;"+ name +" </br>")
				.append("password    &nbsp; &nbsp; &nbsp;"+ password);
		// TODO Auto-generated method stub
		try {
			myResponse.write(content.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doPost(MyRequest myRequest, MyResponse myResponse) {
		// TODO Auto-generated method stub
		try {
			myResponse.write("POST HelloWorldServlet");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
