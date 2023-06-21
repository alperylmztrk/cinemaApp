package com.project.cinema.services;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.project.cinema.dto.AssignedMovieDTO;
import com.project.cinema.model.AssignedMovie;
import com.project.cinema.model.Hall;
import com.project.cinema.model.Movie;
import com.project.cinema.model.Seat;
import com.project.cinema.repos.AssignedMovieRepository;
import com.project.cinema.repos.HallRepository;
import com.project.cinema.repos.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AssignedMovieService {

    private final AssignedMovieRepository assignedMovieRepository;
    private final MovieService movieService;
    private final HallService hallService;

    @Autowired
    public AssignedMovieService(AssignedMovieRepository assignedMovieRepository, MovieService movieService, HallService hallService) {
        this.assignedMovieRepository = assignedMovieRepository;
        this.movieService = movieService;
        this.hallService = hallService;
    }

    public List<AssignedMovie> getAllAssignedMovies(Optional<Long> movieId, Optional<Long> hallId) {

        if (movieId.isPresent() && hallId.isEmpty()) {
            return assignedMovieRepository.findByMovieIdOrderByStartDateTimeAsc(movieId.get());
        } else if (movieId.isEmpty() && hallId.isPresent()) {
            return assignedMovieRepository.findByHallIdOrderByStartDateTimeAsc(hallId.get());
        } else if (movieId.isPresent() && hallId.isPresent()) {
            return assignedMovieRepository.findByMovieIdAndHallIdOrderByStartDateTimeAsc(movieId.get(), hallId.get());
        } else {

            return assignedMovieRepository.findAllByOrderByStartDateTimeAsc();
        }

    }

    public AssignedMovie addAssignedMovie(AssignedMovieDTO assignedMovieDTO) {

        Movie movie = movieService.getMovieById(assignedMovieDTO.getMovieId());
        Hall hall = hallService.getHallById(assignedMovieDTO.getHallId());

        if (movie == null || hall == null) {
            return null;
        } else {
            AssignedMovie assignedMovie = new AssignedMovie();
            assignedMovie.setHall(hall);
            assignedMovie.setMovie(movie);
            //  assignedMovie.setId(assignedMovieDTO.getId());
            assignedMovie.setStartDateTime(assignedMovieDTO.getStartDateTime());
            //assignedMovie.setReservedSeats(assignedMovieDTO.getReservedSeats());

            List<AssignedMovie> assignedMovieList = assignedMovieRepository.findAll();

            for (AssignedMovie assignedMovieDate : assignedMovieList) {
                LocalDateTime dateTime = assignedMovieDate.getStartDateTime();
                LocalDateTime newDateTime = assignedMovieDTO.getStartDateTime();

                if (newDateTime.toLocalDate().equals(dateTime.toLocalDate()) && assignedMovieDTO.getHallId().equals(assignedMovieDate.getHall().getId())) {
                    if (Math.abs(newDateTime.getHour() - dateTime.getHour()) < 2) {
                        System.out.println("Ayni salonda filmler arasi en az 2 saat olmali.");
                        return null;
                    }
                }
            }

            return assignedMovieRepository.save(assignedMovie);
        }
    }

    public AssignedMovie getAssignedMovieById(Long assignedMovieId) {
        return assignedMovieRepository.findById(assignedMovieId).orElse(null);
    }

    public AssignedMovie updateAssignedMovieById(Long assignedMovieId, AssignedMovieDTO assignedMovieDTO) {
        Movie movie = movieService.getMovieById(assignedMovieDTO.getMovieId());
        Hall hall = hallService.getHallById(assignedMovieDTO.getHallId());

        Optional<AssignedMovie> assignedMovie = assignedMovieRepository.findById(assignedMovieId);
        if (assignedMovie.isPresent() && movie != null && hall != null) {
            AssignedMovie foundedAssignedMovie = assignedMovie.get();
            foundedAssignedMovie.setMovie(movie);
            foundedAssignedMovie.setHall(hall);
            foundedAssignedMovie.setStartDateTime(assignedMovieDTO.getStartDateTime());
            foundedAssignedMovie.setReservedSeats(assignedMovieDTO.getReservedSeats());

            return assignedMovieRepository.save(foundedAssignedMovie);
        }
        return null;
    }

    public void deleteAssignedMovieById(Long assignedMovieId) {
        assignedMovieRepository.deleteById(assignedMovieId);
    }
}
