package com.bluemotors.api.controller;

import com.bluemotors.api.model.Vehicle;
import com.bluemotors.api.repository.IVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/vehicle")
public class VehiclesController {

    @Autowired
    private IVehicleRepository vehicleRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> findVehicle(@PathVariable("id") Integer id) {
        return vehicleRepository.findById(id)
                .map(r -> ResponseEntity.ok().body(r))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Iterable<Vehicle> findVehicles() {
        return vehicleRepository.findAll();
    }

    @PostMapping
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
         return vehicleRepository.save(vehicle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Integer id, @RequestBody Vehicle vehicle) {
        Optional<Vehicle> vehicleResponse = vehicleRepository.findById(id);
        boolean vehicleIsNotPresent = !vehicleResponse.isPresent();

        if(vehicleIsNotPresent) {
            return ResponseEntity.notFound().build();
        }

        Vehicle vehicleToUpdate = vehicleResponse.get();

        vehicleToUpdate.setName(vehicle.getName());
        vehicleToUpdate.setPrice(vehicle.getPrice());
        vehicleToUpdate.setType(vehicle.getType());
        vehicleToUpdate.setQuantity(vehicle.getQuantity());

        vehicleRepository.save(vehicleToUpdate);

        return ResponseEntity.ok().body(vehicleToUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteVehicle(@PathVariable Integer id) {
        Optional<Vehicle> vehicleResponse = vehicleRepository.findById(id);
        boolean vehicleIsNotPresent = !vehicleResponse.isPresent();

        if(vehicleIsNotPresent) {
            return ResponseEntity.notFound().build();
        }

        vehicleRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
