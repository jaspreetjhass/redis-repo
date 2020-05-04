package com.example.demo.services;

import com.example.demo.models.Employee;

public interface EmployeeService {

	Employee fetchEmployee(Integer employeeId);

	Employee saveEmployee(Employee employee);

	Employee updateEmployee(Employee employee);

	Employee deleteEmployee(Integer employeeId);

}