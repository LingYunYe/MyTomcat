package com.ye.model;

import java.sql.Date;

public class Student extends Model{
	private Integer id;
	private String name;
	private String phone;
	private String address;
	private Date date;
	public Student() {
		super();
	}
	public Student(Integer id, String name, String phone, String address, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.date = date;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
