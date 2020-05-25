package com.ye.myTomcat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ye.model.Model;
import com.ye.model.User_t;
import com.ye.mysql.ExeSql;

public class HelloWorldServlet extends MyServlet {

	@Override
	public void doGet(MyRequest myRequest, MyResponse myResponse) {
		String name = myRequest.getParameter("name");
		String password = myRequest.getParameter("password");
		ExeSql exeSql = new ExeSql();
		List<Object> list = new ArrayList<Object>();
		list.add(name);
		list.add(password);
		User_t user = (User_t) exeSql.queryObject("select * from user_t where user_name = ? and password = ?", list);
		if (user.getId()==null) {
			try {
				myResponse.write("<script>alert('Username or password is incorrect');</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		StringBuffer content = new StringBuffer();
		content.append("method      &nbsp;&nbsp;&nbsp;GET  </br> ")
				.append("className    &nbsp; &nbsp; &nbsp;HelloWorldServlet </br>")
				.append("name    &nbsp; &nbsp; &nbsp;" + user.getUser_name() + " </br>")
				.append("password    &nbsp; &nbsp; &nbsp;" + user.getPassword() + " </br>")
				.append("<a href='/find'><buttton>Go to FindServlet </button><a>  &nbsp; &nbsp; &nbsp </br>");
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
