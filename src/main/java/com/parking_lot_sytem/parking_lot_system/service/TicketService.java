package com.parking_lot_sytem.parking_lot_system.service;

import com.parking_lot_sytem.parking_lot_system.model.ParkingSpot;
import com.parking_lot_sytem.parking_lot_system.model.Ticket;
import com.parking_lot_sytem.parking_lot_system.model.Vehicle;
import com.parking_lot_sytem.parking_lot_system.repository.ParkingSpotRepository;
import com.parking_lot_sytem.parking_lot_system.repository.TicketRepository;
import com.parking_lot_sytem.parking_lot_system.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final ParkingSpotRepository spotRepository;
    private final VehicleRepository vehicleRepository;

    private static final double HOURLY_RATE = 10.0; // simple flat rate

    public TicketService(TicketRepository ticketRepository, ParkingSpotRepository spotRepository, VehicleRepository vehicleRepository) {
        this.ticketRepository = ticketRepository;
        this.spotRepository = spotRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public List<Ticket> findAll() { return ticketRepository.findAll(); }
    public Ticket findById(Long id) { return ticketRepository.findById(id).orElse(null); }
    public List<Ticket> findActive() { return ticketRepository.findAll().stream().filter(t -> t.getExitTime() == null).toList(); }

    @Transactional
    public Ticket openTicket(Long spotId, Long vehicleId) {
        ParkingSpot spot = spotRepository.findById(spotId).orElse(null);
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
        if (spot == null || vehicle == null || spot.isOccupied()) return null;
        spot.setOccupied(true);
        Ticket ticket = new Ticket();
        ticket.setTicketId("T-" + Instant.now().toEpochMilli());
        ticket.setSpot(spot);
        ticket.setVehicle(vehicle);
        ticket.setEntryTime(new Date());
        return ticketRepository.save(ticket);
    }

    @Transactional
    public Ticket closeTicket(Long ticketId) {
        Ticket ticket = findById(ticketId);
        if (ticket == null || ticket.getExitTime() != null) return null;
        ticket.setExitTime(new Date());
        // fee calculation
        long millis = ticket.getExitTime().getTime() - ticket.getEntryTime().getTime();
        long hours = Math.max(1, (long) Math.ceil(millis / 3600000.0));
        ticket.setFee(hours * HOURLY_RATE);
        // free the spot
        ParkingSpot spot = ticket.getSpot();
        spot.setOccupied(false);
        return ticket;
    }
}

