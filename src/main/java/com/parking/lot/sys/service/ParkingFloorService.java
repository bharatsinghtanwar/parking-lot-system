package com.parking.lot.sys.service;

import com.parking.lot.sys.model.ParkingFloor;
import com.parking.lot.sys.model.ParkingLot;
import com.parking.lot.sys.repository.ParkingFloorRepository;
import com.parking.lot.sys.repository.ParkingLotRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ParkingFloorService {
    private final ParkingFloorRepository floorRepository;
    private final ParkingLotRepository lotRepository;

    public ParkingFloorService(ParkingFloorRepository floorRepository, ParkingLotRepository lotRepository) {
        this.floorRepository = floorRepository;
        this.lotRepository = lotRepository;
    }

    public List<ParkingFloor> findAll() { return floorRepository.findAll(); }

    public ParkingFloor findById(Long id) { return floorRepository.findById(id).orElse(null); }

    public List<ParkingFloor> findByLot(Long lotId) { return floorRepository.findByParkingLotId(lotId); }

    @Transactional
    public ParkingFloor create(Long lotId, ParkingFloor floor) {
        ParkingLot lot = lotRepository.findById(lotId).orElse(null);
        if (lot == null) return null;
        floor.setParkingLot(lot);
        return floorRepository.save(floor);
    }

    @Transactional
    public ParkingFloor update(Long id, ParkingFloor updated) {
        ParkingFloor existing = findById(id);
        if (existing == null) return null;
        existing.setFloorNumber(updated.getFloorNumber());
        return existing;
    }

    public void delete(Long id) { floorRepository.deleteById(id); }
}

