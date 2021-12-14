package com.SEP3.CarRentalAPI.DBRepository;

import com.SEP3.CarRentalAPI.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>
{
    Employee findByEmail(String email);
}
