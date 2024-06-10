package com.project.cinema.controllers;

import com.project.cinema.dto.request.HallDtoRequest;
import com.project.cinema.model.Hall;
import com.project.cinema.services.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/halls")
public class HallController {

    private final HallService hallService;

    @Autowired
    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @GetMapping
    public List<Hall> getAllHalls() {
        return hallService.getAllHalls();
    }

    @GetMapping("/{hallId}")
    public Hall getHallById(@PathVariable Long hallId) {
        return hallService.getHallById(hallId);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @PostMapping
    public Hall saveHall(@RequestBody HallDtoRequest hallDtoRequest) {
        return hallService.saveHall(hallDtoRequest);
    }
}
