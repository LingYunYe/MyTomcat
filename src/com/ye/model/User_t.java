package com.ye.model;

public class User_t extends Model {
	private Integer id;
	private String user_name;
	private String password;
	private Integer age;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public User_t(Integer id, String user_name, String password, Integer age) {
		super();
		this.id = id;
		this.user_name = user_name;
		this.password = password;
		this.age = age;
	}

	public User_t() {
		super();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", user_name=" + user_name + ", password=" + password + ", age=" + age + "]";
	}
}
