package com.project.cinema.security.jwtauth.dto;

import com.project.cinema.security.enums.Role;
import lombok.Builder;

import java.util.Set;
@Builder
public record SaveUserReq(
        String name,
        String surname,
        String username,
        String password,
        Set<Role> authorities
) {

}
