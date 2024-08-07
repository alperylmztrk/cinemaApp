package com.project.cinema.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

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

    @Column(name = "actors", nullable = false)
    private String actors;

    @Column(name = "rating", nullable = false)
    private double rating;

    @Column(name = "summary", nullable = false)
    private String summary;

    @Column(name = "posterImgPath", nullable = false)
    private String posterImgPath;

    public Movie(String title, String genre, int duration, String director, String actors, double rating, String summary, String posterImgPath) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.director = director;
        this.actors = actors;
        this.rating = rating;
        this.summary = summary;
        this.posterImgPath = posterImgPath;
    }

}
