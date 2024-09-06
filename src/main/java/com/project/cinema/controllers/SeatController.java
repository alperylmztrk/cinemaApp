package com.project.cinema.controllers;

import com.project.cinema.model.Seat;
import com.project.cinema.repos.SeatRepository;
import com.project.cinema.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/seats")
@CrossOrigin("*")
public class SeatController {
    private final SeatService seatService;
    private final SeatRepository seatRepository;

    @Autowired
    public SeatController(SeatService seatService,
                          SeatRepository seatRepository) {
        this.seatService = seatService;
        this.seatRepository = seatRepository;
    }

    @GetMapping("/limit/{seat-num}")
    public List<Seat> getSeatsBySeatNum(@PathVariable("seat-num") Integer seatNum) {
        return seatService.getSeatsBySeatNum(seatNum);
    }

    @GetMapping("/{seatId}")
    public Seat getSeatById(@PathVariable Long seatId) {
        return seatService.getSeatById(seatId);
    }

    @PostMapping
    public Seat createSeat(@RequestBody Seat newSeat) {
        return seatService.addSeat(newSeat);
    }

    @PostMapping("test/olustur")
    public String createSeatTest() {

        char[] rows = "ABCDEFGHIJ".toCharArray(); // Sıralar A'dan J'ye kadar
        int totalSeats = 100; // Toplam koltuk sayısı
        int seatsPerRow = 10; // Her bir sıradaki koltuk sayısı

        String[] seatLabels = new String[totalSeats];
        int index = 0;

        for (char row : rows) {
            for (int seatNumber = 1; seatNumber <= seatsPerRow; seatNumber++) {
                seatLabels[index++] = row + String.valueOf(seatNumber);
            }
        }

        List<Seat> seatList=new ArrayList<>();
        for (String seatLabel : seatLabels) {
         Seat seat =new Seat(seatLabel);
         seatList.add(seat);
        }

        seatRepository.saveAll(seatList);


        return "Koltuklar oluşturuldu";
    }

}
