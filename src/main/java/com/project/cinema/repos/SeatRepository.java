package com.project.cinema.repos;

import com.project.cinema.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat,Long> {

    @Query(value = "select s.* from seats s limit ?1", nativeQuery = true)
    List<Seat> findSeatsByLimit(Integer limit);

    @Query(value = "select s from Seat s where s.id in ?1")
    List<Seat> findSeatsByIds(List<Long> ids);

    @Query("select s from Seat s join s.sessions s1 where s1.id= ?1 ")
    List<Seat> findReservedSeatsBySessionId(Long sessionId);

}
