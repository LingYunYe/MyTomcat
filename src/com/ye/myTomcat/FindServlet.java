package com.ye.myTomcat;

import java.io.IOException;

public class FindServlet extends MyServlet{

	@Override
	public void doGet(MyRequest myRequest, MyResponse myResponse) {
		// TODO Auto-generated method stub
		try {
			myResponse.write("GET FindServlet");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doPost(MyRequest myRequest, MyResponse myResponse) {
		// TODO Auto-generated method stub
		try {
			myResponse.write("POST FindServlet");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
