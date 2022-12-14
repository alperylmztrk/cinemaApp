package com.project.cinema.repos;

import com.project.cinema.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByUserId(Long userId);

    List<Ticket> findByAssignedMovieId(Long assignedMovieId);

    List<Ticket> findByUserIdAndAssignedMovieId(Long userId,Long assignedMovieId);

}
