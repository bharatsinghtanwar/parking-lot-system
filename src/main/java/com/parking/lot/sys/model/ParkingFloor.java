package com.parking.lot.sys.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@JsonIgnoreProperties({"parkingLot"})
public class ParkingFloor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int floorNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parking_lot_id")
    private ParkingLot parkingLot;

    @OneToMany(mappedBy = "parkingFloor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParkingSpot> spots;

}
