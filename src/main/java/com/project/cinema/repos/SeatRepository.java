package com.project.cinema.repos;

import com.project.cinema.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat,Long> {

    @Query(value = "select s.* from seats s limit ?1", nativeQuery = true)
    List<Seat> findSeatsByLimit(Integer limit);

}
