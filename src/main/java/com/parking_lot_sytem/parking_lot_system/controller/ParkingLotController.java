package com.parking_lot_sytem.parking_lot_system.controller;

import com.parking_lot_sytem.parking_lot_system.model.ParkingLot;
import com.parking_lot_sytem.parking_lot_system.service.ParkingLotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking-lots")
public class ParkingLotController {
    private final ParkingLotService parkingLotService;

    public ParkingLotController(ParkingLotService parkingLotService) { this.parkingLotService = parkingLotService; }

    @GetMapping
    public List<ParkingLot> all() { return parkingLotService.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingLot> one(@PathVariable Long id) {
        ParkingLot lot = parkingLotService.findById(id);
        return lot == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(lot);
    }

    @PostMapping
    public ResponseEntity<ParkingLot> create(@RequestBody ParkingLot lot) {
        return ResponseEntity.ok(parkingLotService.save(lot));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingLot> update(@PathVariable Long id, @RequestBody ParkingLot lot) {
        ParkingLot updated = parkingLotService.update(id, lot);
        return updated == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        parkingLotService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

