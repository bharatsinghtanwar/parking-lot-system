package com.parking.lot.sys.service;

import com.parking.lot.sys.model.ParkingSpot;
import com.parking.lot.sys.model.Ticket;
import com.parking.lot.sys.model.Vehicle;
import com.parking.lot.sys.repository.ParkingSpotRepository;
import com.parking.lot.sys.repository.TicketRepository;
import com.parking.lot.sys.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

