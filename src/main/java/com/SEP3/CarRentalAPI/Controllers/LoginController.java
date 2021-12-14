package com.SEP3.CarRentalAPI.Controllers;

import com.SEP3.CarRentalAPI.DBRepository.CustomerRepository;
import com.SEP3.CarRentalAPI.DBRepository.EmployeeRepository;
import com.SEP3.CarRentalAPI.Model.Customer;
import com.SEP3.CarRentalAPI.Model.Employee;
import com.SEP3.CarRentalAPI.Model.UserLogin;
import com.SEP3.CarRentalAPI.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class LoginController
{
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/login")
    public ResponseEntity<UserLogin> login(@Valid @RequestBody UserLogin userLogin)
    {
        //Checking if the email belongs to an employee
        Employee employee = employeeRepository.findByEmail(userLogin.getEmail());
        if (employee != null && !employee.isDeleted())
        {
            if (employee.getPassword().equals(userLogin.getPassword()))
            {
                return ResponseEntity.ok().body(new UserLogin(true, employee.getId(), true));
            }
        }

        //Checking if the email belongs to a customer
        Customer customer = customerRepository.findByEmail(userLogin.getEmail());
        if (customer != null && !customer.isDeleted())
        {
            if (customer.getPassword().equals(userLogin.getPassword()))
            {
                return ResponseEntity.ok().body(new UserLogin(true, customer.getId(), false));
            }
            return ResponseEntity.noContent().build();
//            throw new ResourceNotFoundException("Wrong password");
        }
        return ResponseEntity.notFound().build();
//        throw new ResourceNotFoundException("User with this email not found:" + userLogin.getEmail());
    }
}
