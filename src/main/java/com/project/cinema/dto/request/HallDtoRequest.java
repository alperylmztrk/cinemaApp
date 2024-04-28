package com.project.cinema.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class HallDtoRequest {

    private Long id;

    private String name;

    private Integer capacity;


}
