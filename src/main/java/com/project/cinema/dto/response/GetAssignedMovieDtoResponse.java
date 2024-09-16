package com.project.cinema.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetAssignedMovieDtoResponse {

    private Long id;
    private Long movieId;
    private String movieTitle;
    private Long hallId;
    private String hallName;
    private Integer hallCapacity;
    private LocalDateTime startDateTime;
    private Integer reservedSeatNum;
    private String posterImgPath;

}
