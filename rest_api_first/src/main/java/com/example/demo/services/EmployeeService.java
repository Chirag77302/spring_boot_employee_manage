package com.example.demo.services;

import java.util.List;

import com.example.demo.model.Employee;

public interface EmployeeService {
	
	Employee createEmployee(Employee employee);
	List<Employee> getallEmployees();
	boolean deleteEmployee(Long id);
	Employee getEmployeeById(Long id);
    Employee updateEmployee(Long id, Employee employee);
}
