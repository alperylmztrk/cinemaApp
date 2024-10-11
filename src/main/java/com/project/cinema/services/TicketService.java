package com.project.cinema.services;

import com.project.cinema.dto.SaveTicketWithUserDTO;

import com.project.cinema.dto.request.TicketDtoRequest;
import com.project.cinema.model.Session;
import com.project.cinema.model.Ticket;

import com.project.cinema.repos.TicketRepository;
import com.project.cinema.security.jwtauth.dto.SaveUserReq;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    private final UserService userService;
    private final SessionService sessionService;
    private final SeatService seatService;

    @Autowired
    public TicketService(TicketRepository ticketRepository, UserService userService, SessionService sessionService, SeatService seatService) {
        this.ticketRepository = ticketRepository;
        this.userService = userService;
        this.sessionService = sessionService;
        this.seatService = seatService;
    }

    public List<Ticket> getAllTickets(Optional<Long> userId, Optional<Long> sessionId) {
        if (userId.isPresent() && sessionId.isEmpty()) {
            return ticketRepository.findByUserId(userId.get());
        } else if (userId.isEmpty() && sessionId.isPresent()) {
            return ticketRepository.findBySessionId(sessionId.get());
        } else if (userId.isPresent() && sessionId.isPresent()) {
            return ticketRepository.findByUserIdAndSessionId(userId.get(), sessionId.get());
        } else {
            return ticketRepository.findAll();
        }
    }

    public Ticket getTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId).orElse(null);
    }

    @Transactional
    public Ticket addTicket(TicketDtoRequest ticketDtoRequest) {

        Session session = sessionService.getSessionByIdEntity(ticketDtoRequest.getSessionId());
        var reservedSeats= seatService.getReservedSeatsBySessionId(ticketDtoRequest.getSessionId());
        var selectedSeatList = seatService.getSeatsByIds(ticketDtoRequest.getSelectedSeatIds());
        if (selectedSeatList.isEmpty()) {
            throw new RuntimeException("Koltuk bulunamadi. Rezerve Edliemedi.");
        }
        reservedSeats.addAll(selectedSeatList);
        session.setReservedSeats(reservedSeats);
        sessionService.saveSession(session);

        var auth = SecurityContextHolder.getContext().getAuthentication();
        var username = auth.getName();
        var user = userService.getUserByUsername(username);

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setSession(session);

        return ticketRepository.save(ticket);

    }


    @Transactional
    public Ticket addTicketWithUser2(SaveTicketWithUserDTO saveTicketWithUserDTO) {

        SaveUserReq saveUserReq = new SaveUserReq(saveTicketWithUserDTO.getName(), saveTicketWithUserDTO.getSurname(), saveTicketWithUserDTO.getUsername(), saveTicketWithUserDTO.getPassword(), null);

        var newUser = userService.saveUser(saveUserReq);

        System.out.println(newUser);

        Ticket ticket = new Ticket();
        BeanUtils.copyProperties(saveTicketWithUserDTO, ticket);
        ticket.setUser(newUser);

        System.out.println(ticket);

        return ticketRepository.save(ticket);

    }
}
