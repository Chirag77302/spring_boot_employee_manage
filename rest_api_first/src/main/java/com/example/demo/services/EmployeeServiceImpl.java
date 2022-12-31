package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.demo.entity.EmployeeEntity;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	public EmployeeRepository employeerepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeerepository) {
		this.employeerepository = employeerepository;
	}

	@Override
	public Employee createEmployee(Employee employee) {
		EmployeeEntity employeeentity = new EmployeeEntity();
		BeanUtils.copyProperties(employee, employeeentity);
		employeerepository.save(employeeentity);
		return employee;
	}

	@Override
	public List<Employee> getallEmployees() {
		List<EmployeeEntity>employeeentity = employeerepository.findAll();

		List<Employee> employees = employeeentity
                .stream()
                .map(emp -> new Employee( 
                			emp.getId(),
                			emp.getFirstName(),
                			emp.getLastName(),
                			emp.getEmail()))
                .collect(Collectors.toList());
        return employees;
	}

	@Override
	public boolean deleteEmployee(Long id) {
		EmployeeEntity emp = employeerepository.findById(id).get();
		if(emp == null)return false;
		employeerepository.delete(emp);
		return true;
	}

	@Override
	public Employee getEmployeeById(Long id) {
		EmployeeEntity emp = employeerepository.findById(id).get();
		Employee toget = new Employee();
		BeanUtils.copyProperties(emp,toget);
		return toget;
	}

	@Override
	public Employee updateEmployee(Long id, Employee employee) {
		EmployeeEntity emp = employeerepository.findById(id).get();
		emp.setEmail(employee.getEmail());
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
		emp.setId(employee.getId());
		employeerepository.save(emp);
		return employee;
	}
	
	
}
