package com.parking.lot.sys.controller;

import com.parking.lot.sys.model.Vehicle;
import com.parking.lot.sys.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) { this.vehicleService = vehicleService; }

    @GetMapping
    public List<Vehicle> all(@RequestParam(value = "licensePlate", required = false) String plate) {
        if (plate != null) {
            Vehicle v = vehicleService.findByLicensePlate(plate);
            return v == null ? List.of() : List.of(v);
        }
        return vehicleService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> one(@PathVariable Long id) {
        Vehicle v = vehicleService.findById(id);
        return v == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(v);
    }

    @PostMapping
    public Vehicle create(@RequestBody Vehicle vehicle) { return vehicleService.save(vehicle); }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> update(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        Vehicle existing = vehicleService.findById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        existing.setLicensePlate(vehicle.getLicensePlate());
        existing.setVehicleType(vehicle.getVehicleType());
        return ResponseEntity.ok(vehicleService.save(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vehicleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

