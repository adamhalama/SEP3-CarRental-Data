package com.SEP3.CarRentalAPI.DBRepository;

import com.SEP3.CarRentalAPI.Model.Employee;
import com.SEP3.CarRentalAPI.Model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>
{
}
