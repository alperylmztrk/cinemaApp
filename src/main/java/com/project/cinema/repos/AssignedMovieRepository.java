package com.project.cinema.repos;

import com.project.cinema.dto.response.GetSessionsDtoResponse;
import com.project.cinema.model.AssignedMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AssignedMovieRepository extends JpaRepository<AssignedMovie, Long> {

    List<AssignedMovie> findByMovieIdOrderByStartDateTimeAsc(Long movieId);

    List<AssignedMovie> findAllByOrderByStartDateTimeAsc();

    List<AssignedMovie> findByHallIdOrderByStartDateTimeAsc(Long hallId);

    List<AssignedMovie> findByMovieIdAndHallIdOrderByStartDateTimeAsc(Long movieId, Long hallId);

    @Query("select new com.project.cinema.dto.response.GetSessionsDtoResponse( a.id, a.startDateTime) from AssignedMovie a where a.movie.id= ?1 order by a.startDateTime asc ")
    List<GetSessionsDtoResponse> findSessionsByMovieId(Long movieId);

}
