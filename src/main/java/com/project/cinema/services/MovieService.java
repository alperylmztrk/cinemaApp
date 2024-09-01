package com.project.cinema.services;

import com.project.cinema.exceptions.MovieException;
import com.project.cinema.model.Movie;
import com.project.cinema.repos.MovieRepository;
import com.project.cinema.searchFilter.MovieSpecification;
import com.project.cinema.searchFilter.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
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
        try {
            if (movieId == 5L) {
                throw new MovieException("film hatası", false, HttpStatus.NOT_FOUND);
            }
            return movieRepository.findById(movieId).orElse(null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }

    }

    public Movie updateMovieById(Long movieId, Movie newMovie) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (movie.isPresent()) {
            Movie foundedMovie = movie.get();
            foundedMovie.setTitle(newMovie.getTitle());
            foundedMovie.setDuration(newMovie.getDuration());
            foundedMovie.setGenre(newMovie.getGenre());
            foundedMovie.setActors(newMovie.getActors());
            foundedMovie.setDirector(newMovie.getDirector());
            foundedMovie.setSummary(newMovie.getSummary());
            foundedMovie.setRating(newMovie.getRating());
            foundedMovie.setPosterImgPath(newMovie.getPosterImgPath());
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

    public List<Movie> getFilteredMovies(List<SearchCriteria> searchCriteriaList) {

        var specList = searchCriteriaList.stream().map(MovieSpecification::new).toList();

        Specification<Movie> result = specList.get(0);

        for (int i = 1; i < specList.size(); i++) {
            result=Specification.where(result).and(specList.get(i));
        }

        return movieRepository.findAll(result);


    }

}
