package com.project.cinema.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "assignedMovies")
@Data
@NoArgsConstructor
public class AssignedMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movieId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "hallId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Hall hall;

    @Column(name = "startDateTime", nullable = false)
    private LocalDateTime startDateTime;


    @ManyToMany
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE, org.hibernate.annotations.CascadeType.PERSIST})
    @JoinTable(name = "reservedSeats")
    private List<Seat> reservedSeats = new ArrayList<>();

    public AssignedMovie(Movie movie, Hall hall, LocalDateTime startDateTime) {
        this.movie = movie;
        this.hall = hall;
        this.startDateTime = startDateTime;
    }
}
