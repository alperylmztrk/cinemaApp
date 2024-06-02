package com.project.cinema.security.jwtauth.dto;

import lombok.Builder;

@Builder
public record AuthReq(

        String username,
        String password
) {

}
