package com.parking_lot_sytem.parking_lot_system.controller;

import com.parking_lot_sytem.parking_lot_system.model.ParkingSpot;
import com.parking_lot_sytem.parking_lot_system.service.ParkingSpotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spots")
public class ParkingSpotController {
    private final ParkingSpotService spotService;

    public ParkingSpotController(ParkingSpotService spotService) { this.spotService = spotService; }

    @GetMapping
    public List<ParkingSpot> all() { return spotService.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSpot> one(@PathVariable Long id) {
        ParkingSpot spot = spotService.findById(id);
        return spot == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(spot);
    }

    @GetMapping("/floor/{floorId}")
    public List<ParkingSpot> byFloor(@PathVariable Long floorId) { return spotService.findByFloor(floorId); }

    @GetMapping("/available")
    public List<ParkingSpot> available() { return spotService.findAvailable(); }

    @PostMapping("/floor/{floorId}")
    public ResponseEntity<ParkingSpot> create(@PathVariable Long floorId, @RequestBody ParkingSpot spot) {
        ParkingSpot created = spotService.create(floorId, spot);
        return created == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingSpot> update(@PathVariable Long id, @RequestBody ParkingSpot spot) {
        ParkingSpot updated = spotService.update(id, spot);
        return updated == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}/occupied")
    public ResponseEntity<ParkingSpot> setOccupied(@PathVariable Long id, @RequestParam boolean value) {
        ParkingSpot updated = spotService.setOccupied(id, value);
        return updated == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        spotService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

