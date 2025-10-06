package com.parking.lot.sys.controller;

import com.parking.lot.sys.model.ParkingFloor;
import com.parking.lot.sys.service.ParkingFloorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/floors")
public class ParkingFloorController {
    private final ParkingFloorService floorService;

    public ParkingFloorController(ParkingFloorService floorService) { this.floorService = floorService; }

    @GetMapping
    public List<ParkingFloor> all() { return floorService.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingFloor> one(@PathVariable Long id) {
        ParkingFloor floor = floorService.findById(id);
        return floor == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(floor);
    }

    @GetMapping("/by-lot/{lotId}")
    public List<ParkingFloor> byLot(@PathVariable Long lotId) { return floorService.findByLot(lotId); }

    @PostMapping("/lot/{lotId}")
    public ResponseEntity<ParkingFloor> create(@PathVariable Long lotId, @RequestBody ParkingFloor floor) {
        ParkingFloor created = floorService.create(lotId, floor);
        return created == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingFloor> update(@PathVariable Long id, @RequestBody ParkingFloor floor) {
        ParkingFloor updated = floorService.update(id, floor);
        return updated == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        floorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

