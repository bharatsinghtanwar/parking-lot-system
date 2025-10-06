package com.parking.lot.sys.repository;

import com.parking.lot.sys.model.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {
    // ...existing code...
}

