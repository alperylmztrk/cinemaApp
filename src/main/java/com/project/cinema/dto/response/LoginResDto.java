package com.project.cinema.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.cinema.security.enums.Role;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginResDto {
    @JsonProperty("token")
    private String token;
    @JsonProperty("roles")
    private Set<String> authorities;

}
