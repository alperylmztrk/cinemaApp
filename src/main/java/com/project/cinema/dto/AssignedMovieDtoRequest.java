package com.project.cinema.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.project.cinema.model.Seat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class AssignedMovieDtoRequest {

    private Long id;
    private Long movieId;
    private Long hallId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime startDateTime;


}
