package com.project.cinema.repos;

import com.project.cinema.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat,Long> {

    List<Seat> findAllByOrderByIdDesc();

}
