package com.parking.lot.sys.repository;

import com.parking.lot.sys.model.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {
    List<ParkingSpot> findByParkingFloorId(Long parkingFloorId);
    List<ParkingSpot> findByOccupied(boolean occupied);
}
