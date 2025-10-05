package com.parking_lot_sytem.parking_lot_system.repository;

import com.parking_lot_sytem.parking_lot_system.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findByTicketId(String ticketId);
}

