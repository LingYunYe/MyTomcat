package com.ye.myTomcat;

import java.io.IOException;

public class FaviconServlet extends MyServlet {

	@Override
	public void doGet(MyRequest myRequest, MyResponse myResponse) {
		// TODO Auto-generated method stub
		try {
			myResponse.write("GET FaviconServlet");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doPost(MyRequest myRequest, MyResponse myResponse) {
		// TODO Auto-generated method stub
		try {
			myResponse.write("POST FaviconServlet");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
