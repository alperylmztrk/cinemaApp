package com.project.cinema.controllers;

import com.project.cinema.dto.request.AssignedMovieDtoRequest;
import com.project.cinema.dto.response.GetAssignedMovieDtoResponse;
import com.project.cinema.dto.response.SaveAssignedMovieDtoResponse;
import com.project.cinema.model.AssignedMovie;
import com.project.cinema.services.AssignedMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/assignedMovies")
public class AssignedMovieController {

    private final AssignedMovieService assignedMovieService;

    @Autowired
    public AssignedMovieController(AssignedMovieService assignedMovieService) {
        this.assignedMovieService = assignedMovieService;
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping
    public List<GetAssignedMovieDtoResponse> getAllAssignedMovies(@RequestParam Optional<Long> movieId, @RequestParam Optional<Long> hallId) {
        return assignedMovieService.getAllAssignedMovies(movieId, hallId);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping("/{assignedMovieId}")
    public GetAssignedMovieDtoResponse getAssignedMovieById(@PathVariable Long assignedMovieId) {
        return assignedMovieService.getAssignedMovieById(assignedMovieId);
    }

    @GetMapping("sessions/{movieId}")
    public LinkedHashMap<String, List<HashMap<String, String>>> getSessionsByMovieId(@PathVariable Long movieId) {
        return assignedMovieService.getSessionsByMovieId(movieId);
    }


    @PostMapping
    public SaveAssignedMovieDtoResponse createAssignedMovie(@RequestBody AssignedMovieDtoRequest assignedMovieDtoRequest) {
        return assignedMovieService.addAssignedMovie(assignedMovieDtoRequest);
    }

    @PutMapping("/{assignedMovieId}")
    public AssignedMovie updateAssignedMovie(@PathVariable Long assignedMovieId, @RequestBody AssignedMovieDtoRequest assignedMovieDtoRequest) {
        return assignedMovieService.updateAssignedMovieById(assignedMovieId, assignedMovieDtoRequest);
    }

    @DeleteMapping("/{assignedMovieId}")
    public void deleteAssignedMovieById(@PathVariable Long assignedMovieId) {
        assignedMovieService.deleteAssignedMovieById(assignedMovieId);
    }
}
