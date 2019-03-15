package com.developer.architecture.app.services;

import java.util.List;

import com.developer.architecture.app.domain.Employee;
import com.developer.architecture.app.dto.Data;

public interface IEmployeeService {

	void addEmployee(Employee user);
	
	List<Employee> getEmployeesByName(String employeeName);
	
	List<Employee> getAllemployee();
	
	List<Employee> getHierarchyByGuid(String guid);
	
	void updateEmployeeHierarchy(Data data);
	
	void updateEmployee(Employee employee);
}
