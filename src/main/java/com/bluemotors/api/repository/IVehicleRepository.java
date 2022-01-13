package com.bluemotors.api.repository;

import com.bluemotors.api.model.Vehicle;
import org.springframework.data.repository.CrudRepository;

public interface IVehicleRepository extends CrudRepository<Vehicle, Integer> {
}
