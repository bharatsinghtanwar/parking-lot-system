package com.parking_lot_sytem.parking_lot_system.repository;

import com.parking_lot_sytem.parking_lot_system.model.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {
    List<ParkingSpot> findByParkingFloorId(Long parkingFloorId);
    List<ParkingSpot> findByOccupied(boolean occupied);
}
