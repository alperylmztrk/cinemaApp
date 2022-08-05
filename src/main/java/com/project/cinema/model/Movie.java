package com.project.cinema.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "genre", nullable = false)
    private String genre;

    @Column(name = "duration", nullable = false)
    private int duration;

    @Column(name = "director", nullable = false)
    private String director;

    @Column(name = "cast", nullable = false)
    private String cast;

    @Column(name = "rating", nullable = false)
    private double rating;

    @Column(name = "summary", nullable = false, length = 1000, columnDefinition = "TEXT")
    private String summary;

    @Column(name = "posterImgPath", nullable = false)
    private String posterImgPath;

    public Movie(String title, String genre, int duration, String director, String cast, double rating, String summary, String posterImgPath) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.director = director;
        this.cast = cast;
        this.rating = rating;
        this.summary = summary;
        this.posterImgPath = posterImgPath;
    }
}
