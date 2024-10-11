package com.project.cinema.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetSessionDtoResponse {

    private Long id;
    private Long movieId;
    private String movieTitle;
    private Long hallId;
    private String hallName;
    private Integer hallCapacity;
    private LocalDateTime startDateTime;
    private Integer reservedSeatNum;
    private String posterImgPath;
    private List<Long> reservedSeatIds;

    public GetSessionDtoResponse(Long id, Long movieId, String movieTitle, Long hallId, String hallName, Integer hallCapacity, LocalDateTime startDateTime, Integer reservedSeatNum, String posterImgPath) {
        this.id = id;
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.hallId = hallId;
        this.hallName = hallName;
        this.hallCapacity = hallCapacity;
        this.startDateTime = startDateTime;
        this.reservedSeatNum = reservedSeatNum;
        this.posterImgPath = posterImgPath;

    }
}
