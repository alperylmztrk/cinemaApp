package com.project.cinema.services;

import com.project.cinema.dto.request.AssignedMovieDtoRequest;
import com.project.cinema.dto.response.GetAssignedMovieDtoResponse;
import com.project.cinema.dto.response.GetSessionsDtoResponse;
import com.project.cinema.dto.response.SaveAssignedMovieDtoResponse;
import com.project.cinema.model.AssignedMovie;
import com.project.cinema.model.Hall;
import com.project.cinema.model.Movie;
import com.project.cinema.repos.AssignedMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    public List<GetAssignedMovieDtoResponse> getAllAssignedMovies(Optional<Long> movieId, Optional<Long> hallId) {
        List<GetAssignedMovieDtoResponse> getAssignedMovieDtoResponseList = new ArrayList<>();
        List<AssignedMovie> assignedMovieList;
        if (movieId.isPresent() && hallId.isEmpty()) {
            assignedMovieList = assignedMovieRepository.findByMovieIdOrderByStartDateTimeAsc(movieId.get());
        } else if (movieId.isEmpty() && hallId.isPresent()) {
            assignedMovieList = assignedMovieRepository.findByHallIdOrderByStartDateTimeAsc(hallId.get());
        } else if (movieId.isPresent() && hallId.isPresent()) {
            assignedMovieList = assignedMovieRepository.findByMovieIdAndHallIdOrderByStartDateTimeAsc(movieId.get(), hallId.get());
        } else {
            assignedMovieList = assignedMovieRepository.findAllByOrderByStartDateTimeAsc();
        }

        for (AssignedMovie assignedMovie : assignedMovieList) {
            getAssignedMovieDtoResponseList.add(GetAssignedMovieDtoResponse.builder().
                    id(assignedMovie.getId()).
                    movieId(assignedMovie.getMovie().getId()).
                    movieTitle(assignedMovie.getMovie().getTitle()).
                    startDateTime(assignedMovie.getStartDateTime()).
                    hallId(assignedMovie.getHall().getId()).
                    hallName(assignedMovie.getHall().getName()).
                    hallCapacity(assignedMovie.getHall().getCapacity()).
                    reservedSeatNum(assignedMovie.getReservedSeats().size()).
                    build());
        }

        return getAssignedMovieDtoResponseList;
    }

    public SaveAssignedMovieDtoResponse addAssignedMovie(AssignedMovieDtoRequest assignedMovieDtoRequest) {

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
            return SaveAssignedMovieDtoResponse.builder().
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

    public LinkedHashMap<String,List<HashMap<String,String>>> getSessionsByMovieId(Long movieId) {
        var seanslar = assignedMovieRepository.findSessionsByMovieId(movieId);
        LinkedHashMap<String,List<HashMap<String,String>>> hashMap=new LinkedHashMap<>();
        seanslar.forEach(s -> {
            var a = s.getStartDateTime().format(DateTimeFormatter.ofPattern("dd LLLL yyyy HH:mm", new Locale("tr")));
            var date = (a.split(" ")[0].startsWith("0") ? a.split(" ")[0].charAt(1) : a.split(" ")[0]) + " " + a.split(" ")[1];
            var time = a.split(" ")[3];
            HashMap<String,String> saatler=new HashMap<>();
            saatler.put("id",s.getId().toString());
            saatler.put("time",time);

            hashMap.putIfAbsent(date,new ArrayList<>());
            hashMap.get(date).add(saatler);

        });

        System.out.println(hashMap);

        return hashMap;
    }
}
