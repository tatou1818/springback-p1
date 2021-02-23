package net.javaguides.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/v1/")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeerepository;
	
	//get all employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeerepository.findAll();
		
	}
	
	//create employee
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeerepository.save(employee);
	}
	
	//get employee by Id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById (@PathVariable long id){
		Employee employee = employeerepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Employee not found with Id: "+id));
		return ResponseEntity.ok(employee);
		
	}
	
	//update employee
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employeedetails){
		Employee employee = employeerepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Employee not found with Id: "+id));
		employee.setFirstName(employeedetails.getFirstName());
		employee.setLastName(employeedetails.getLastName());
		employee.setEmailId(employeedetails.getEmailId());
		
		Employee updatedemployee = employeerepository.save(employee);
		return ResponseEntity.ok(updatedemployee);

	}
	
	//delete employee
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Employee employee = employeerepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Employee not found with Id: "+id));
		employeerepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
