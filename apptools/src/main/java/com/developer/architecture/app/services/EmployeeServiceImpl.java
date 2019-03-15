package com.developer.architecture.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.architecture.app.domain.Employee;
import com.developer.architecture.app.dto.Data;
import com.developer.architecture.app.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public void addEmployee(Employee user) {
		employeeRepository.save(user);
	}

	public List<Employee> getAllemployee() {
		return employeeRepository.findAll();
	}

	public List<Employee> getEmployeesByName(String employeeName) {
		return employeeRepository.findByDisplayName(employeeName);
	}

	public List<Employee> getHierarchyByGuid(String guid) {
		List<String> listOfIds = new ArrayList<String>();
		List<Employee> employeeList = new ArrayList<Employee>();
		if (employeeRepository.findByGuid(guid) != null) {
			listOfIds.add(guid);
			getAllChildrenIds(listOfIds, guid);
		}
		for (String employeeId : listOfIds) {
			Employee employee = employeeRepository.findByGuid(employeeId);
			employeeList.add(employee);
		}
		return employeeList;
	}

	private void getAllChildrenIds(List<String> listOfIds, String guid) {
		if (!listOfIds.contains(guid)) {
			listOfIds.add(guid);
		}
		Employee employee = employeeRepository.findByGuid(guid);
		if (employee.getChildren() != null && !employee.getChildren().isEmpty()) {
			List<String> childs = employee.getChildren();
			for (String cId : childs) {
				findAndAddInnerChildInIdsList(listOfIds, cId);
			}
		}
	}

	private void findAndAddInnerChildInIdsList(List<String> listOfIds, String guid) {
		Employee employee = employeeRepository.findByGuid(guid);
		if (employee.getChildren() != null && !employee.getChildren().isEmpty()) {
			List<String> childs = employee.getChildren();
			for (String cId : childs) {
				getAllChildrenIds(listOfIds, cId);
			}
		}
		getAllChildrenIds(listOfIds, guid);
	}

	public void updateEmployeeHierarchy(Data data) {
		for (Employee employee : data.getData()) {
			employeeRepository.save(employee);
		}
	}

	@Override
	public void updateEmployee(Employee employee) {
		employeeRepository.save(employee);
		
	}

}
