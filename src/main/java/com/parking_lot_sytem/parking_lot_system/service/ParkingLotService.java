package com.parking_lot_sytem.parking_lot_system.service;

import com.parking_lot_sytem.parking_lot_system.model.ParkingLot;
import com.parking_lot_sytem.parking_lot_system.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ParkingLotService {
    @Autowired
    private ParkingLotRepository parkingLotRepository;

    public ParkingLot save(ParkingLot parkingLot) {
        return parkingLotRepository.save(parkingLot);
    }

    public List<ParkingLot> findAll() {
        return parkingLotRepository.findAll();
    }

    public ParkingLot findById(Long id) {
        return parkingLotRepository.findById(id).orElse(null);
    }

    @Transactional
    public ParkingLot update(Long id, ParkingLot updated) {
        ParkingLot existing = findById(id);
        if (existing == null) return null;
        existing.setName(updated.getName());
        existing.setAddress(updated.getAddress());
        return existing; // managed entity auto-flushed
    }

    public void deleteById(Long id) {
        parkingLotRepository.deleteById(id);
    }
}
