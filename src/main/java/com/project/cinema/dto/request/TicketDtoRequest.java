package com.project.cinema.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TicketDtoRequest {

    private Long sessionId;
    private List<Long> selectedSeatIds;

}
