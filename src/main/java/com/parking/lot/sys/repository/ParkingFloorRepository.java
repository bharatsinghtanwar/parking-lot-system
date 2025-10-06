package com.parking.lot.sys.repository;

import com.parking.lot.sys.model.ParkingFloor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ParkingFloorRepository extends JpaRepository<ParkingFloor, Long> {
    List<ParkingFloor> findByParkingLotId(Long parkingLotId);
}

