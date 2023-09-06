package com.project.cinema.services;

import com.project.cinema.model.Movie;
import com.project.cinema.repos.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Movie> addFakeMovies() {
        Movie movie1 = new Movie("Test 1", "Komedi", 70, "Yok", "Yok", 4.1, "Özet", "https://cinemaone.net/images/movie_placeholder.png");
        Movie movie2 = new Movie("Test 2", "Aksiyon", 75, "Yok", "Yok", 3.5, "Özet", "https://cinemaone.net/images/movie_placeholder.png");
        Movie movie3 = new Movie("Test 3", "Macera", 80, "Yok", "Yok", 2.6, "Özet", "https://cinemaone.net/images/movie_placeholder.png");
        Movie movie4 = new Movie("Test 4", "Polisiye", 90, "Yok", "Yok", 1.4, "Özet", "https://cinemaone.net/images/movie_placeholder.png");
        Movie movie5 = new Movie("Test 5", "Dram", 100, "Yok", "Yok", 4.4, "Özet", "https://cinemaone.net/images/movie_placeholder.png");
        Movie movie6 = new Movie("Test 6", "Korku", 110, "Yok", "Yok", 4.5, "Özet", "https://cinemaone.net/images/movie_placeholder.png");

        List<Movie> fakeMovieList = new ArrayList<>(List.of(movie1, movie2, movie3, movie4, movie5, movie6));

        return movieRepository.saveAll(fakeMovieList);
    }

}
