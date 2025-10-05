package com.parking_lot_sytem.parking_lot_system.repository;

import com.parking_lot_sytem.parking_lot_system.model.ParkingFloor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ParkingFloorRepository extends JpaRepository<ParkingFloor, Long> {
    List<ParkingFloor> findByParkingLotId(Long parkingLotId);
}

