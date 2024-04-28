package com.project.cinema.services;

import com.project.cinema.dto.SaveTicketWithUserDTO;
import com.project.cinema.dto.TicketDTO;
import com.project.cinema.model.AssignedMovie;
import com.project.cinema.model.Ticket;
import com.project.cinema.model.User;
import com.project.cinema.repos.TicketRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    private final UserService userService;
    private final AssignedMovieService assignedMovieService;

    @Autowired
    public TicketService(TicketRepository ticketRepository, UserService userService, AssignedMovieService assignedMovieService) {
        this.ticketRepository = ticketRepository;
        this.userService = userService;
        this.assignedMovieService = assignedMovieService;
    }

    public List<Ticket> getAllTickets(Optional<Long> userId, Optional<Long> assignedMovieId) {
        if (userId.isPresent() && assignedMovieId.isEmpty()) {
            return ticketRepository.findByUserId(userId.get());
        } else if (userId.isEmpty() && assignedMovieId.isPresent()) {
            return ticketRepository.findByAssignedMovieId(assignedMovieId.get());
        } else if (userId.isPresent() && assignedMovieId.isPresent()) {
            return ticketRepository.findByUserIdAndAssignedMovieId(userId.get(), assignedMovieId.get());
        } else {
            return ticketRepository.findAll();
        }
    }

    public Ticket getTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId).orElse(null);
    }

    public Ticket addTicket(TicketDTO ticketDTO) {

        User user = userService.getUserById(ticketDTO.getUserId());
        AssignedMovie assignedMovie = assignedMovieService.getAssignedMovieByIdEntity(ticketDTO.getAssignedMovieId());

        if (user != null && assignedMovie != null) {
            Ticket ticket = new Ticket();
            //ticket.setId(ticketDTO.getId());
            ticket.setUser(user);
            ticket.setAssignedMovie(assignedMovie);
            ticket.setSeatNumber(ticketDTO.getSeatNumber());
            return ticketRepository.save(ticket);
        } else {
            return null;
        }
    }

    @Transactional
    public Ticket addTicketWithUser2(SaveTicketWithUserDTO saveTicketWithUserDTO) {

        User user = new User();
        BeanUtils.copyProperties(saveTicketWithUserDTO, user);
        var newUser = userService.saveUser(user);

        System.out.println(newUser);

        Ticket ticket = new Ticket();
        BeanUtils.copyProperties(saveTicketWithUserDTO, ticket);
        ticket.setUser(newUser);

        System.out.println(ticket);

        return ticketRepository.save(ticket);

    }
}
