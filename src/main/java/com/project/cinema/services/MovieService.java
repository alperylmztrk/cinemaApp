package com.project.cinema.services;

import com.project.cinema.model.Movie;
import com.project.cinema.repos.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie addMovie(Movie newMovie) {
        return movieRepository.save(newMovie);
    }

    public Movie getMovieById(Long movieId) {
        return movieRepository.findById(movieId).orElse(null);
    }

    public Movie updateMovieById(Long movieId, Movie newMovie) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (movie.isPresent()) {
            Movie foundedMovie = movie.get();
            foundedMovie.setTitle(newMovie.getTitle());
            foundedMovie.setDuration(newMovie.getDuration());
            foundedMovie.setGenre(newMovie.getGenre());
            foundedMovie.setCast(foundedMovie.getCast());
            foundedMovie.setDirector(newMovie.getDirector());
            foundedMovie.setSummary(newMovie.getSummary());
            foundedMovie.setRating(newMovie.getRating());
            movieRepository.save(foundedMovie);
            return foundedMovie;
        }
        return null;
    }

    public void deleteMovie(Long movieId) {
        movieRepository.deleteById(movieId);
    }

}
