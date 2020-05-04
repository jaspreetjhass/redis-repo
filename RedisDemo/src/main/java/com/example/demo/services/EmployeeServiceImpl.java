package com.example.demo.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Override
	public Employee fetchEmployee(final Integer employeeId) {
		LOGGER.trace("Enter into fetchEmployee method with parameters : {}", employeeId);
		Employee employeeResponse = null;
		final Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
		if (optionalEmployee.isPresent())
			employeeResponse = optionalEmployee.get();
		LOGGER.trace("Exit from fetchEmployee method with output : {}", employeeResponse);
		return employeeResponse;
	}

	@Override
	public Employee saveEmployee(final Employee employee) {
		LOGGER.trace("Enter into saveEmployee method with parameters : {}", employee);
		final Employee employeeResponse = employeeRepository.save(employee);
		LOGGER.trace("Exit from saveEmployee method with output : {}", employeeResponse);
		return employeeResponse;
	}

	@Override
	public Employee updateEmployee(final Employee employee) {
		LOGGER.trace("Enter into updateEmployee method with parameters : {}", employee);
		final Employee employeeResponse = employeeRepository.save(employee);
		LOGGER.trace("Exit from updateEmployee method with output : {}", employeeResponse);
		return employeeResponse;
	}

	@Override
	public Employee deleteEmployee(final Integer employeeId) {
		LOGGER.trace("Enter into deleteEmployee method with parameters : {}", employeeId);
		Employee employeeResponse = null;
		final Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
		if (optionalEmployee.isPresent()) {
			employeeResponse = optionalEmployee.get();
			employeeRepository.delete(employeeResponse);
		}
		LOGGER.trace("Exit from deleteEmployee method with output : {}", employeeResponse);
		return employeeResponse;
	}

}
