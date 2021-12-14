package com.SEP3.CarRentalAPI.Controllers;

import com.SEP3.CarRentalAPI.DBRepository.CustomerRepository;
import com.SEP3.CarRentalAPI.DBRepository.EmployeeRepository;
import com.SEP3.CarRentalAPI.Model.Employee;
import com.SEP3.CarRentalAPI.exception.EmailAlreadyUsedException;
import com.SEP3.CarRentalAPI.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController
{
	@Autowired
	private EmployeeRepository repository;
	@Autowired
	private CustomerRepository customerRepository;

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		return ResponseEntity.ok().body(repository.findAll());
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException
	{
		Employee employee = repository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/employees")
	public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) throws EmailAlreadyUsedException
	{
		if(customerRepository.findByEmail(employee.getEmail()) != null)
		{
			throw new EmailAlreadyUsedException("Email already used");
		}
		if (employee.getClearanceLevel() > 1)
			employee.setClearanceLevel(1);
		return ResponseEntity.ok().body(repository.save(employee));
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		Employee employee = repository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employee.setName(employeeDetails.getName());
		employee.setEmail(employeeDetails.getEmail());
		if(employeeDetails.getPassword() != null && !employeeDetails.getPassword().isEmpty())
			employee.setPassword(employeeDetails.getPassword());
		if (employeeDetails.getClearanceLevel() < 1)
			employeeDetails.setClearanceLevel(1);
		employee.setClearanceLevel(employeeDetails.getClearanceLevel());
		final Employee updatedEmployee = repository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = repository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		Employee deletedEmployee = repository.getById(employeeId);
		repository.deleteById(employeeId);
		return ResponseEntity.ok().body(deletedEmployee);
	}
}
