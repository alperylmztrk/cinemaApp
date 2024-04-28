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
    private Long reservedSeatNum;

    public GetAssignedMovieDtoResponse(Long id, Long movieId, String movieTitle, Long hallId, String hallName, Integer hallCapacity, LocalDateTime startDateTime) {
        this.id = id;
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.hallId = hallId;
        this.hallName = hallName;
        this.hallCapacity = hallCapacity;
        this.startDateTime = startDateTime;
    }
}
