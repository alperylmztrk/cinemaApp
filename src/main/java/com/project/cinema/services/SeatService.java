package com.project.cinema.services;

import com.project.cinema.model.Seat;
import com.project.cinema.repos.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {
    private final SeatRepository seatRepository;

    @Autowired
    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<Seat> getSeatsBySeatNum(Integer koltukSayisi) {
        return seatRepository.findSeatsByLimit(koltukSayisi);
    }

    public Seat getSeatById(Long seatId) {
        return seatRepository.findById(seatId).orElse(null);
    }

    public Seat addSeat(Seat newSeat) {
        return seatRepository.save(newSeat);
    }
}
