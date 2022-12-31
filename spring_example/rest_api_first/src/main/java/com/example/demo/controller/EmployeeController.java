package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Employee;
import com.example.demo.services.EmployeeService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	
	private final EmployeeService employeeservice;

	public EmployeeController(EmployeeService employeeservice) {
		this.employeeservice = employeeservice;
	}
	
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		System.out.println("hello , hi");
		System.out.println(employee);
		employeeservice.createEmployee(employee);
		return employee;
	}
	
	@GetMapping("/employees")
	public List<Employee> getallEmployees(){
		return employeeservice.getallEmployees();
	}

	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
		boolean deleted = false;
		deleted = employeeservice.deleteEmployee(id);
		Map<String,Boolean>response = new HashMap<>();
		if(deleted == false) {
			response.put("not found", deleted);
		}else {
			response.put("deleted", deleted);
		}
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
		Employee emp = employeeservice.getEmployeeById(id);
		return ResponseEntity.ok(emp);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employee){
		Employee updated_employee = employeeservice.updateEmployee(id, employee);
		return ResponseEntity.ok(updated_employee);
	}
	
}
