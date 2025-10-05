package com.parking_lot_sytem.parking_lot_system.repository;

import com.parking_lot_sytem.parking_lot_system.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findByLicensePlate(String licensePlate);
}

