package com.parking_lot_sytem.parking_lot_system.model;

import jakarta.persistence.*;
import java.util.Date;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@JsonIgnoreProperties({"spot", "vehicle"})
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ticketId;
    @ManyToOne(fetch = FetchType.LAZY)
    private ParkingSpot spot;
    @ManyToOne(fetch = FetchType.LAZY)
    private Vehicle vehicle;
    @Temporal(TemporalType.TIMESTAMP)
    private Date entryTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date exitTime;
    private Double fee;

    // getters and setters
}
