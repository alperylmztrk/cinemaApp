package com.project.cinema.services;

import com.project.cinema.model.Hall;
import com.project.cinema.repos.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallService {

    private final HallRepository hallRepository;

    @Autowired
    public HallService(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }
    public List<Hall> getAllHalls(){
        return hallRepository.findAll();
    }
    public Hall getHallById(Long hallId){
        return hallRepository.findById(hallId).orElse(null);
    }
}
