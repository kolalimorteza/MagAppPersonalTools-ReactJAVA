package com.developer.architecture.app.dto;

import java.util.List;

import com.developer.architecture.app.domain.Employee;

public class Data {

	List<Employee> data;

	public List<Employee> getData() {
		return data;
	}

	public void setData(List<Employee> data) {
		this.data = data;
	}

}
