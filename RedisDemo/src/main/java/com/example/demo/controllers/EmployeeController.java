package com.example.demo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Employee;
import com.example.demo.services.EmployeeService;

@RestController
@RequestMapping("redisApi")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	@GetMapping("/employees/{employeeId}")
	public Employee fetchEmployeeDetails(@PathVariable("employeeId") final Integer employeeId) {
		LOGGER.trace("Enter into fetchEmployeeDetails method with parameters : {}", employeeId);
		final Employee employee = employeeService.fetchEmployee(employeeId);
		LOGGER.trace("Exit from fetchEmployeeDetails method with output : {}", employee);
		return employee;
	}

	@PostMapping("/employees")
	public Employee persistEmployee(@RequestBody final Employee employee) {
		LOGGER.trace("Enter into persistEmployee method with parameters : {}", employee);
		final Employee employeeResponse = employeeService.saveEmployee(employee);
		LOGGER.trace("Exit from persistEmployee method with output : {}", employeeResponse);
		return employeeResponse;
	}

	@PutMapping("/employees/{employeeId}")
	public Employee modifyEmployee(@PathVariable("employeeId") final Integer employeeId,
			@RequestBody final Employee employee) {
		LOGGER.trace("Enter into modifyEmployee method with parameters : {}", employee);
		final Employee employeeResponse = employeeService.updateEmployee(employee);
		LOGGER.trace("Exit from modifyEmployee method with output : {}", employeeResponse);
		return employeeResponse;
	}

	@DeleteMapping("/employees/{employeeId}")
	public Employee removeEmployee(@PathVariable("employeeId") final Integer employeeId) {
		LOGGER.trace("Enter into removeEmployee method with parameters : {}", employeeId);
		final Employee employeeResponse = employeeService.deleteEmployee(employeeId);
		LOGGER.trace("Exit from removeEmployee method with output : {}", employeeResponse);
		return employeeResponse;
	}

}
