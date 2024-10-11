package com.project.cinema.dto;

import lombok.Data;

@Data
public class SaveTicketDTO {
    private Long userId;
    private Long sessionId;
    private String seatNumber;
}
