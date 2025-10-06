package com.parking.lot.sys.service;

import com.parking.lot.sys.model.ParkingFloor;
import com.parking.lot.sys.model.ParkingSpot;
import com.parking.lot.sys.repository.ParkingFloorRepository;
import com.parking.lot.sys.repository.ParkingSpotRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ParkingSpotService {
    private final ParkingSpotRepository spotRepository;
    private final ParkingFloorRepository floorRepository;

    public ParkingSpotService(ParkingSpotRepository spotRepository, ParkingFloorRepository floorRepository) {
        this.spotRepository = spotRepository;
        this.floorRepository = floorRepository;
    }

    public List<ParkingSpot> findAll() { return spotRepository.findAll(); }

    public ParkingSpot findById(Long id) { return spotRepository.findById(id).orElse(null); }

    public List<ParkingSpot> findByFloor(Long floorId) { return spotRepository.findByParkingFloorId(floorId); }

    public List<ParkingSpot> findAvailable() { return spotRepository.findByOccupied(false); }

    @Transactional
    public ParkingSpot create(Long floorId, ParkingSpot spot) {
        ParkingFloor floor = floorRepository.findById(floorId).orElse(null);
        if (floor == null) return null;
        spot.setParkingFloor(floor);
        spot.setOccupied(false);
        return spotRepository.save(spot);
    }

    @Transactional
    public ParkingSpot update(Long id, ParkingSpot updated) {
        ParkingSpot existing = findById(id);
        if (existing == null) return null;
        existing.setSpotId(updated.getSpotId());
        existing.setSpotType(updated.getSpotType());
        existing.setOccupied(updated.isOccupied());
        return existing;
    }

    @Transactional
    public ParkingSpot setOccupied(Long id, boolean occupied) {
        ParkingSpot existing = findById(id);
        if (existing == null) return null;
        existing.setOccupied(occupied);
        return existing;
    }

    public void delete(Long id) { spotRepository.deleteById(id); }
}
