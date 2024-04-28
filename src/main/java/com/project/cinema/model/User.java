package com.project.cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Ticket> tickets;

    public User(String name, String surname, String username) {
        this.name = name;
        this.surname = surname;
        this.username = username;
    }
}
