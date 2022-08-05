package com.project.cinema.controllers;

import com.project.cinema.dto.TicketDTO;
import com.project.cinema.model.Ticket;
import com.project.cinema.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Ticket> getAllTickets(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> assignedMovieId) {
        return ticketService.getAllTickets(userId, assignedMovieId);
    }

    @GetMapping("/{ticketId}")
    public Ticket getTicketById(@PathVariable Long ticketId) {
        return ticketService.getTicketById(ticketId);
    }

    @PostMapping
    public Ticket createTicket(@RequestBody TicketDTO ticketDTO) {
        return ticketService.addTicket(ticketDTO);
    }

}
