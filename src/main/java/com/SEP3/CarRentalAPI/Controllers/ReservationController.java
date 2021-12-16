package com.SEP3.CarRentalAPI.Controllers;

import com.SEP3.CarRentalAPI.DBRepository.CustomerRepository;
import com.SEP3.CarRentalAPI.DBRepository.EmployeeRepository;
import com.SEP3.CarRentalAPI.DBRepository.ReservationRepository;
import com.SEP3.CarRentalAPI.DBRepository.VehicleRepository;
import com.SEP3.CarRentalAPI.Model.Customer;
import com.SEP3.CarRentalAPI.Model.Employee;
import com.SEP3.CarRentalAPI.Model.Reservation;
import com.SEP3.CarRentalAPI.Model.Vehicle;
import com.SEP3.CarRentalAPI.exception.ResourceNotFoundException;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ReservationController
{
    @Autowired
    private ReservationRepository repository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/reservations/vehicle/{id}")
    public ResponseEntity<List<Reservation>> getReservationsByVehicle(@PathVariable(value = "id") Long vehicleId) throws ResourceNotFoundException
    {

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id :: " + vehicleId));
        return ResponseEntity.ok().body(repository.getAllByVehicle(vehicle));
    }

    @GetMapping("/reservations/customer/{id}")
    public ResponseEntity<List<Reservation>> getReservationsByCustomer(@PathVariable(value = "id") Long customerId) throws ResourceNotFoundException
    {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
        return ResponseEntity.ok().body(repository.getAllByCustomer(customer));
    }

    @GetMapping("/reservations/employee/{id}")
    public ResponseEntity<List<Reservation>> getReservationsByEmployee(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException
    {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(repository.getAllByEmployee(employee));
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getAllReservations()
    {
        return ResponseEntity.ok().body(repository.findAll());
    }


    @GetMapping("/reservations/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable(value = "id") Long reservationId)
            throws ResourceNotFoundException
    {
        Reservation reservation = repository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found for this id :: " + reservationId));

        return ResponseEntity.ok().body(reservation);
    }

    @PostMapping("/reservations")
    public ResponseEntity<Reservation> createReservation(@Valid @RequestBody Reservation reservation)
    {
        reservation.setVehicle(vehicleRepository.getById(reservation.getVehicle().getId()));
        reservation.setCustomer(customerRepository.getById(reservation.getCustomer().getId()));
        if (reservation.getEmployee().getId() != -1)
        {
            reservation.setEmployee(employeeRepository.getById(reservation.getEmployee().getId()));
        }
        else
        {
            reservation.setEmployee(null);
        }

        Reservation savedReservation = repository.save(reservation);

        Object customer = Hibernate.unproxy(savedReservation.getCustomer());
        Object employee = Hibernate.unproxy(savedReservation.getEmployee());
        Object vehicle = Hibernate.unproxy(savedReservation.getVehicle());

        savedReservation.setCustomer((Customer) customer);
        savedReservation.setEmployee((Employee) employee);
        savedReservation.setVehicle((Vehicle) vehicle);

        return ResponseEntity.ok().body(savedReservation);
    }

    @PutMapping("/reservations/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable(value = "id") Long reservationId,
                                                         @Valid @RequestBody Reservation reservationDetails) throws ResourceNotFoundException
    {
        Reservation reservation = repository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found for this id :: " + reservationId));

        reservation.setVehicle(reservationDetails.getVehicle());
        reservation.setCustomer(reservationDetails.getCustomer());
        reservation.setEmployee(reservationDetails.getEmployee());

        reservation.setSecurityDeposit(reservationDetails.getSecurityDeposit());
        reservation.setDateCreated(reservationDetails.getDateCreated());
        reservation.setDateStart(reservationDetails.getDateStart());
        reservation.setDateEnd(reservationDetails.getDateEnd());
        reservation.setAllowedKm(reservationDetails.getAllowedKm());
        reservation.setPaymentAmount(reservationDetails.getPaymentAmount());
        reservation.setBillDate(reservationDetails.getBillDate());
        reservation.setPaid(reservationDetails.isPaid());
        final Reservation updatedReservation = repository.save(reservation);
        return ResponseEntity.ok(updatedReservation);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Reservation> deleteReservation(@PathVariable(value = "id") Long reservationId)
            throws ResourceNotFoundException
    {
        Reservation reservation = repository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found for this id :: " + reservationId));

        Reservation deletedReservation = repository.getById(reservationId);
        repository.deleteById(reservationId);
        return ResponseEntity.ok().body(deletedReservation);
    }
}
