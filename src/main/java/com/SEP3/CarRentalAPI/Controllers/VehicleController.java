package com.SEP3.CarRentalAPI.Controllers;

import com.SEP3.CarRentalAPI.DBRepository.ReservationRepository;
import com.SEP3.CarRentalAPI.DBRepository.VehicleRepository;
import com.SEP3.CarRentalAPI.Model.Reservation;
import com.SEP3.CarRentalAPI.Model.Vehicle;
import com.SEP3.CarRentalAPI.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class VehicleController
{

    @Autowired
    private VehicleRepository repository;

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping("/vehicles")
    public ResponseEntity<List<Vehicle>> getAllVehicles()
    {
        return ResponseEntity.ok().body(repository.findAll());
    }

    @GetMapping("/vehicles/available/startDate={startDate}&endDate={endDate}")
    public ResponseEntity<List<Vehicle>> getAvailableVehicles(@PathVariable long endDate, @PathVariable long startDate)
    {
        List<Reservation> reservationList = reservationRepository.findAll();
        List<Vehicle> vehicleList = repository.findAll();

        for (Reservation reservation : reservationList)
        {
            if ( (reservation.getDateStart() < startDate && startDate < reservation.getDateEnd() )
                    || ( reservation.getDateStart() < endDate && endDate < reservation.getDateEnd() ))
            {
                vehicleList.removeIf(vehicle -> vehicle.getId() == reservation.getVehicle().getId());
//                vehicleList.remove(reservation.getVehicle());
            }
        }

        return ResponseEntity.ok().body(vehicleList);
    }

    @GetMapping("/vehicles/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable(value = "id") Long vehicleId)
            throws ResourceNotFoundException
    {
        Vehicle vehicle = repository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id :: " + vehicleId));
        return ResponseEntity.ok().body(vehicle);
    }

    @PostMapping("/vehicles")
    public ResponseEntity<Vehicle> createVehicle(@Valid @RequestBody Vehicle vehicle)
    {
        vehicle.setId(-1);
        return ResponseEntity.ok().body(repository.save(vehicle));
    }

    @PutMapping("/vehicles/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable(value = "id") Long vehicleId,
                                                 @Valid @RequestBody Vehicle vehicleDetails) throws ResourceNotFoundException
    {
        Vehicle vehicle = repository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id :: " + vehicleId));

        vehicle.setName(vehicleDetails.getName());
        vehicle.setType(vehicleDetails.getType());
        vehicle.setPricePerDay(vehicleDetails.getPricePerDay());
        vehicle.setSeatsCount(vehicleDetails.getSeatsCount());
        vehicle.setAutomatic(vehicleDetails.isAutomatic());
        vehicle.setPowerKw(vehicleDetails.getPowerKw());
        vehicle.setFuelType(vehicleDetails.getFuelType());
        vehicle.setDeposit(vehicleDetails.getDeposit());
        
        final Vehicle updatedVehicle = repository.save(vehicle);
        return ResponseEntity.ok(updatedVehicle);
    }

    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<Vehicle> deleteVehicle(@PathVariable(value = "id") Long vehicleId)
            throws ResourceNotFoundException {
        Vehicle vehicle = repository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id :: " + vehicleId));

        Vehicle vehicleToDelete = repository.getById(vehicleId);
        repository.deleteById(vehicleId);
        return ResponseEntity.ok().body(vehicleToDelete);
    }

}
