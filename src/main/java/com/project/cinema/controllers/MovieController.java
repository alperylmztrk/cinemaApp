package com.project.cinema.controllers;

import com.project.cinema.model.Movie;
import com.project.cinema.searchFilter.SearchCriteria;
import com.project.cinema.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @PostMapping
    public Movie createMovie(@RequestBody Movie newMovie) {
        return movieService.addMovie(newMovie);
    }

    @GetMapping("/{movieId}")
    public Movie getMovieById(@PathVariable Long movieId) {
        return movieService.getMovieById(movieId);
    }

    @PutMapping("/{movieId}")
    public Movie updateMovieById(@PathVariable Long movieId, @RequestBody Movie newMovie) {
        return movieService.updateMovieById(movieId, newMovie);
    }

    @DeleteMapping("/{movieId}")
    public void deleteMovieById(@PathVariable Long movieId) {
        movieService.deleteMovie(movieId);
    }

    @PostMapping("/fake-veri-bas")
    public List<Movie> addFakeMovies() {
        return movieService.addFakeMovies();
    }

    @PostMapping("/filtered-movies")
    public List<Movie> getFilteredMovies(@RequestBody List<SearchCriteria> searchCriteriaList) {
        return movieService.getFilteredMovies(searchCriteriaList);
    }

}
