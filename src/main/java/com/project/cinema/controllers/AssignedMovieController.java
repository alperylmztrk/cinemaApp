package com.project.cinema.controllers;

import com.project.cinema.dto.AssignedMovieDTO;
import com.project.cinema.model.AssignedMovie;
import com.project.cinema.services.AssignedMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public List<AssignedMovie> getAllAssignedMovies(@RequestParam Optional<Long> movieId, @RequestParam Optional<Long> hallId) {
        return assignedMovieService.getAllAssignedMovies(movieId, hallId);
    }

    @GetMapping("/{assignedMovieId}")
    public AssignedMovie getAssignedMovieById(@PathVariable Long assignedMovieId) {
        return assignedMovieService.getAssignedMovieById(assignedMovieId);
    }

    @PostMapping
    public AssignedMovie createAssignedMovie(@RequestBody AssignedMovieDTO assignedMovieDTO) {
        return assignedMovieService.addAssignedMovie(assignedMovieDTO);
    }

    @PutMapping("/{assignedMovieId}")
    public AssignedMovie updateAssignedMovie(@PathVariable Long assignedMovieId, @RequestBody AssignedMovieDTO assignedMovieDTO) {
        return assignedMovieService.updateAssignedMovieById(assignedMovieId, assignedMovieDTO);
    }

    @DeleteMapping("/{assignedMovieId}")
    public void deleteAssignedMovieById(@PathVariable Long assignedMovieId) {
        assignedMovieService.deleteAssignedMovieById(assignedMovieId);
    }
}
