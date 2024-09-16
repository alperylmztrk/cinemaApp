package com.project.cinema.repos;

import com.project.cinema.dto.response.GetAssignedMovieDtoResponse;
import com.project.cinema.dto.response.GetSessionsDtoResponse;
import com.project.cinema.model.AssignedMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AssignedMovieRepository extends JpaRepository<AssignedMovie, Long> {

    List<AssignedMovie> findByMovieIdOrderByStartDateTimeAsc(Long movieId);

    List<AssignedMovie> findAllByOrderByStartDateTimeAsc();

    List<AssignedMovie> findByHallIdOrderByStartDateTimeAsc(Long hallId);

    List<AssignedMovie> findByMovieIdAndHallIdOrderByStartDateTimeAsc(Long movieId, Long hallId);

    @Query("select new com.project.cinema.dto.response.GetSessionsDtoResponse(a.id, a.startDateTime) from AssignedMovie a where a.movie.id= ?1 order by a.startDateTime asc ")
    List<GetSessionsDtoResponse> findSessionsByMovieId(Long movieId);

    @Query("select new com.project.cinema.dto.response.GetAssignedMovieDtoResponse(a.id, a.movie.id, a.movie.title, a.hall.id, a.hall.name, a.hall.capacity, a.startDateTime, size(a.reservedSeats), a.movie.posterImgPath ) from AssignedMovie a where a.id= ?1")
    Optional<GetAssignedMovieDtoResponse> findByIdDto(Long id);
}
