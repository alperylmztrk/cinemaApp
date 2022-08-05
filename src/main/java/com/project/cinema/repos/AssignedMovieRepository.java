package com.project.cinema.repos;

import com.project.cinema.model.AssignedMovie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignedMovieRepository extends JpaRepository<AssignedMovie, Long> {

    List<AssignedMovie> findByMovieIdOrderByStartDateTimeAsc(Long movieId);
    List<AssignedMovie> findAllByOrderByStartDateTimeAsc();
    List<AssignedMovie> findByHallIdOrderByStartDateTimeAsc(Long hallId);

    List<AssignedMovie> findByMovieIdAndHallIdOrderByStartDateTimeAsc(Long movieId, Long hallId);

}
