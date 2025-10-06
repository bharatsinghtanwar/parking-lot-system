package com.parking.lot.sys.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String licensePlate;
    private String vehicleType;

    // getters and setters
}
