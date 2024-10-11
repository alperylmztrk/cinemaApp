package com.project.cinema.controllers;

import com.project.cinema.dto.request.SessionDtoRequest;
import com.project.cinema.dto.response.GetSessionDtoResponse;
import com.project.cinema.dto.response.SaveSessionDtoResponse;
import com.project.cinema.model.Session;
import com.project.cinema.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sessions")
@CrossOrigin("*")
public class SessionController {

    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    //  @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping
    public List<GetSessionDtoResponse> getAllSession(@RequestParam Optional<Long> movieId, @RequestParam Optional<Long> hallId) {
        return sessionService.getAllSessions(movieId, hallId);
    }

    @GetMapping("/{sessionId}")
    public GetSessionDtoResponse getSessionById(@PathVariable Long sessionId) {
        return sessionService.getSessionById(sessionId);
    }

    @GetMapping("sessions/{movieId}")
    public LinkedHashMap<String, List<HashMap<String, String>>> getSessionsByMovieId(@PathVariable Long movieId) {
        return sessionService.getSessionsByMovieId(movieId);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @PostMapping
    public SaveSessionDtoResponse createSession(@RequestBody SessionDtoRequest sessionDtoRequest) {
        return sessionService.addSession(sessionDtoRequest);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @PutMapping("/{sessionId}")
    public Session updateSession(@PathVariable Long sessionId, @RequestBody SessionDtoRequest sessionDtoRequest) {
        return sessionService.updateSessionById(sessionId, sessionDtoRequest);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @DeleteMapping("/{sessionId}")
    public void deleteSessionById(@PathVariable Long sessionId) {
        sessionService.deleteSessionById(sessionId);
    }
}
