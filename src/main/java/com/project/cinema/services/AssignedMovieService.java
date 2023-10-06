package com.project.cinema.services;

import com.project.cinema.dto.AssignedMovieDtoRequest;
import com.project.cinema.dto.AssignedMovieDtoResponse;
import com.project.cinema.model.AssignedMovie;
import com.project.cinema.model.Hall;
import com.project.cinema.model.Movie;
import com.project.cinema.repos.AssignedMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public AssignedMovieDtoResponse addAssignedMovie(AssignedMovieDtoRequest assignedMovieDtoRequest) {

        Movie movie = movieService.getMovieById(assignedMovieDtoRequest.getMovieId());
        Hall hall = hallService.getHallById(assignedMovieDtoRequest.getHallId());

        if (movie == null || hall == null) {
            return null;
        } else {
            AssignedMovie assignedMovie = new AssignedMovie();
            assignedMovie.setHall(hall);
            assignedMovie.setMovie(movie);
            assignedMovie.setStartDateTime(assignedMovieDtoRequest.getStartDateTime());

            List<AssignedMovie> assignedMovieList = assignedMovieRepository.findAll();

            for (AssignedMovie assignedMovieDate : assignedMovieList) {
                LocalDateTime dateTime = assignedMovieDate.getStartDateTime();
                LocalDateTime newDateTime = assignedMovieDtoRequest.getStartDateTime();

                if (newDateTime.toLocalDate().equals(dateTime.toLocalDate()) && assignedMovieDtoRequest.getHallId().equals(assignedMovieDate.getHall().getId())) {
                    if (Math.abs(newDateTime.getHour() - dateTime.getHour()) < 2) {
                        System.out.println("Ayni salonda filmler arasi en az 2 saat olmali.");
                        throw new RuntimeException("Ayni salonda filmler arasi en az 2 saat olmali.");
                    }
                }
            }


            AssignedMovie save = assignedMovieRepository.save(assignedMovie);
            return AssignedMovieDtoResponse.builder().
                    id(save.getId()).
                    movieId(save.getMovie().getId()).
                    movieTitle(save.getMovie().getTitle()).
                    startDateTime(save.getStartDateTime()).
                    hallName(save.getHall().getName()).
                    build();
        }
    }

    public AssignedMovie getAssignedMovieById(Long assignedMovieId) {
        return assignedMovieRepository.findById(assignedMovieId).orElse(null);
    }

    public AssignedMovie updateAssignedMovieById(Long assignedMovieId, AssignedMovieDtoRequest assignedMovieDtoRequest) {
        Movie movie = movieService.getMovieById(assignedMovieDtoRequest.getMovieId());
        Hall hall = hallService.getHallById(assignedMovieDtoRequest.getHallId());

        Optional<AssignedMovie> assignedMovie = assignedMovieRepository.findById(assignedMovieId);
        if (assignedMovie.isPresent() && movie != null && hall != null) {
            AssignedMovie foundedAssignedMovie = assignedMovie.get();
            foundedAssignedMovie.setMovie(movie);
            foundedAssignedMovie.setHall(hall);
            foundedAssignedMovie.setStartDateTime(assignedMovieDtoRequest.getStartDateTime());

            return assignedMovieRepository.save(foundedAssignedMovie);
        }
        return null;
    }

    public void deleteAssignedMovieById(Long assignedMovieId) {
        assignedMovieRepository.deleteById(assignedMovieId);
    }
}
