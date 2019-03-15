package com.developer.architecture.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.developer.architecture.app.domain.Employee;
import com.developer.architecture.app.dto.ApiResponseDto;
import com.developer.architecture.app.dto.ApiResponseDto.ApiResponseDtoBuilder;
import com.developer.architecture.app.dto.Data;
import com.developer.architecture.app.repository.EmployeeRepository;
import com.developer.architecture.app.services.EmployeeServiceImpl;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeServiceImpl employeeService;

	/**
	 * This api call will add a new employee
	 * 
	 * @param employee
	 * @return
	 */
	@RequestMapping(value = "/addEmployee", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ApiResponseDto addEmployee(@RequestBody Employee employee) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		employeeService.addEmployee(employee);
		if (employee.getGuid() != null && !employee.getGuid().isEmpty()) {
			apiResponseDtoBuilder.withData(employee).withMessage("success").withStatus(HttpStatus.OK);
		} else {
			apiResponseDtoBuilder.withMessage("failed").withStatus(HttpStatus.BAD_REQUEST);
		}
		return apiResponseDtoBuilder.build();
	}

	/**
	 * Method will return all employess
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAllEmployee", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ApiResponseDto getAllEmployee() {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		List<Employee> employees = employeeService.getAllemployee();
		if (!employees.isEmpty()) {
			apiResponseDtoBuilder.withData(employees).withMessage("success").withStatus(HttpStatus.OK);
		} else {
			apiResponseDtoBuilder.withMessage("failure").withStatus(HttpStatus.BAD_REQUEST);
		}
		return apiResponseDtoBuilder.build();
	}

	/**
	 * Method will return Employee Dto by employee name
	 * 
	 * @param employeeName
	 * @return
	 */
	@RequestMapping(value = "/getEmployeesByName/{employeeName}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ApiResponseDto getEmployeesByName(@PathVariable String employeeName) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		List<Employee> employees = employeeService.getEmployeesByName(employeeName);
		apiResponseDtoBuilder.withData(employees).withMessage("success").withStatus(HttpStatus.OK);
		return apiResponseDtoBuilder.build();
	}

	/**
	 * Method willl return complete child hierarchy by using employee Id
	 * 
	 * @param guid
	 * @return
	 */
	@RequestMapping(value = "/getHierarchyByEmployeeId/{guid}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ApiResponseDto getHierarchyByEmployeeId(@PathVariable String guid) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		List<Employee> employees = employeeService.getHierarchyByGuid(guid);
		if (!employees.isEmpty()) {
			apiResponseDtoBuilder.withData(employees).withMessage("success").withStatus(HttpStatus.OK);
		} else {
			apiResponseDtoBuilder.withMessage("failed").withStatus(HttpStatus.BAD_REQUEST);
		}

		return apiResponseDtoBuilder.build();
	}

	/**
	 * method will update user hierarchy
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/updateHierarchy", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ApiResponseDto updateHierarchy(@RequestBody Data data) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		employeeService.updateEmployeeHierarchy(data);
		apiResponseDtoBuilder.withData(data).withMessage("success").withStatus(HttpStatus.OK);
		return apiResponseDtoBuilder.build();
	}

	/**
	 * method will update user hierarchy
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/updateNode", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ApiResponseDto updateEmployee(@RequestBody Employee employee) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		employeeService.updateEmployee(employee);
		apiResponseDtoBuilder.withData(employee).withMessage("success").withStatus(HttpStatus.OK);
		return apiResponseDtoBuilder.build();
	}

}
