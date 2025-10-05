package com.parking_lot_sytem.parking_lot_system.repository;

import com.parking_lot_sytem.parking_lot_system.model.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {
    // ...existing code...
}

