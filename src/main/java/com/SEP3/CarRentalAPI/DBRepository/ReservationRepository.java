package com.SEP3.CarRentalAPI.DBRepository;

import com.SEP3.CarRentalAPI.Model.Customer;
import com.SEP3.CarRentalAPI.Model.Employee;
import com.SEP3.CarRentalAPI.Model.Reservation;
import com.SEP3.CarRentalAPI.Model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long>
{
    List<Reservation> getAllByCustomer(Customer customer);
    List<Reservation> getAllByVehicle(Vehicle vehicle);
    List<Reservation> getAllByEmployee(Employee employee);
}
