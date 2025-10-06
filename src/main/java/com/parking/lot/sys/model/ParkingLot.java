package com.parking.lot.sys.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.Data;

@Entity
@Data
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParkingFloor> floors;

}
