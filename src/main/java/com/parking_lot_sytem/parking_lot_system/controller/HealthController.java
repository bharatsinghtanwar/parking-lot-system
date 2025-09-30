package com.parking_lot_sytem.parking_lot_system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    // Health check endpoint
    // Returns "OK" if the service is running
    @GetMapping("/health")
    public String healthCheck() {
        return "OK";
    }


}
