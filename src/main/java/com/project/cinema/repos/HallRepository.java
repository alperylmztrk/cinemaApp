package com.project.cinema.repos;

import com.project.cinema.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallRepository extends JpaRepository<Hall,Long> {
}
