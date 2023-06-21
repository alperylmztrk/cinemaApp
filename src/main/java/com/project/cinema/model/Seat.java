package com.project.cinema.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "seats")
@Data
@NoArgsConstructor
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", unique = true)
    private String number;
    @JsonBackReference
    @ManyToMany(mappedBy = "reservedSeats")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE, org.hibernate.annotations.CascadeType.PERSIST})
    private List<AssignedMovie> assignedMovies = new ArrayList<>();

    public Seat(String number, List<AssignedMovie> assignedMovies) {
        this.number = number;
        this.assignedMovies = assignedMovies;
    }

    public Seat(String number) {
        this.number = number;
    }
}
