package com.project.cinema.repos;

import com.project.cinema.dto.response.GetSessionDtoResponse;
import com.project.cinema.dto.response.GetSessionsDtoResponse;
import com.project.cinema.model.Seat;
import com.project.cinema.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {

    List<Session> findByMovieIdOrderByStartDateTimeAsc(Long movieId);

    List<Session> findAllByOrderByStartDateTimeAsc();

    List<Session> findByHallIdOrderByStartDateTimeAsc(Long hallId);

    List<Session> findByMovieIdAndHallIdOrderByStartDateTimeAsc(Long movieId, Long hallId);

    @Query("select new com.project.cinema.dto.response.GetSessionsDtoResponse(s.id, s.startDateTime) from Session s where s.movie.id= ?1 order by s.startDateTime asc ")
    List<GetSessionsDtoResponse> findSessionsByMovieId(Long movieId);

    @Query("select new com.project.cinema.dto.response.GetSessionDtoResponse(s.id, s.movie.id, s.movie.title, s.hall.id, s.hall.name, s.hall.capacity, s.startDateTime, size(s.reservedSeats), s.movie.posterImgPath) from Session s where s.id= ?1")
    Optional<GetSessionDtoResponse> findByIdDto(Long id);



}
