package com.ye.model;

public class Model {
	//�������
	private String colName;
	//����������
	private String colTypeName;

	public Model() {
		super();
	}

	public Model(String colName, String colTypeName) {
		super();
		if(colTypeName.equalsIgnoreCase("VARCHAR")) {
			colTypeName = "String";
		}
		if(colTypeName.equalsIgnoreCase("INT")) {
			colTypeName = "Integer";
		}
		this.colName = colName;
		this.colTypeName = colTypeName;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getColTypeName() {
		return colTypeName;
	}

	public void setColTypeName(String colTypeName) {
		if(colTypeName.equalsIgnoreCase("VARCHAR")) {
			colTypeName = "String";
		}
		this.colTypeName = colTypeName;
	}

	@Override
	public String toString() {
		return "Model [colName=" + colName + ", colTypeName=" + colTypeName + "]";
	}
}
