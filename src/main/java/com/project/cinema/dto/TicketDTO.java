package com.project.cinema.dto;

import lombok.Data;

@Data
public class TicketDTO {
    private Long Id;
    private Long userId;
    private Long sessionId;
    private String seatNumber;
}
