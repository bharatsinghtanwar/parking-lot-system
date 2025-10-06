package com.parking.lot.sys.service;

import com.parking.lot.sys.model.Vehicle;
import com.parking.lot.sys.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle save(Vehicle vehicle) { return vehicleRepository.save(vehicle); }
    public Vehicle findById(Long id) { return vehicleRepository.findById(id).orElse(null); }
    public List<Vehicle> findAll() { return vehicleRepository.findAll(); }
    public Vehicle findByLicensePlate(String plate) { return vehicleRepository.findByLicensePlate(plate).orElse(null); }
    public void delete(Long id) { vehicleRepository.deleteById(id); }
}

