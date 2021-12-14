package com.SEP3.CarRentalAPI.DBRepository;

import com.SEP3.CarRentalAPI.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>
{
    Customer findByEmail(String email);
}
