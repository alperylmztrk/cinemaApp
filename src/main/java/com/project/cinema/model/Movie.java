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
    private String baslik;

    @Column(name = "genre", nullable = false)
    private String tur;

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

    public Movie(String title, String tur, int duration, String director, String actors, double rating, String summary, String posterImgPath) {
        this.baslik = title;
        this.tur = tur;
        this.duration = duration;
        this.director = director;
        this.actors = actors;
        this.rating = rating;
        this.summary = summary;
        this.posterImgPath = posterImgPath;
    }

}
