package com.project.cinema.dto;

import lombok.Data;

@Data
public class SaveTicketWithUserDTO {
    private Long userId;
    private Long sessionId;
    private String seatNumber;
    private String name;
    private String surname;
    private String username;
    private String password;
}
