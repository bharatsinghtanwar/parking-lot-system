package com.parking_lot_sytem.parking_lot_system.controller;

import com.parking_lot_sytem.parking_lot_system.model.Ticket;
import com.parking_lot_sytem.parking_lot_system.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) { this.ticketService = ticketService; }

    @GetMapping
    public List<Ticket> all(@RequestParam(value = "active", required = false) Boolean active) {
        if (active != null && active) {
            return ticketService.findActive();
        }
        return ticketService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> one(@PathVariable Long id) {
        Ticket t = ticketService.findById(id);
        return t == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(t);
    }

    @PostMapping("/open")
    public ResponseEntity<Ticket> open(@RequestParam Long spotId, @RequestParam Long vehicleId) {
        Ticket t = ticketService.openTicket(spotId, vehicleId);
        return t == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(t);
    }

    @PostMapping("/{id}/close")
    public ResponseEntity<Ticket> close(@PathVariable Long id) {
        Ticket t = ticketService.closeTicket(id);
        return t == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(t);
    }
}

