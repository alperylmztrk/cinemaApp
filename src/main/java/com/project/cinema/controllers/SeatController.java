package com.project.cinema.controllers;

import com.project.cinema.model.Seat;
import com.project.cinema.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatController {
    private final SeatService seatService;

    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping
    public List<Seat> getAllSeats() {
        return seatService.getAllSeats();
    }

    @GetMapping("/{seatId}")
    public Seat getSeatById(@PathVariable Long seatId) {
        return seatService.getSeatById(seatId);
    }

    @PostMapping
    public Seat createSeat(@RequestBody Seat newSeat) {
        return seatService.addSeat(newSeat);
    }
}
