package com.project.cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
@Data
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    User user;

    @ManyToOne
    @JoinColumn(name = "assignedMovieId", nullable = false)

    AssignedMovie assignedMovie;

    @Column(name = "seatNumber", nullable = false, unique = false)
    String seatNumber;

}
