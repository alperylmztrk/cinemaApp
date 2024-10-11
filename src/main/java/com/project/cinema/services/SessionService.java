package com.project.cinema.services;

import com.project.cinema.dto.request.SessionDtoRequest;
import com.project.cinema.dto.response.GetSessionDtoResponse;
import com.project.cinema.dto.response.SaveSessionDtoResponse;
import com.project.cinema.model.Seat;
import com.project.cinema.model.Session;
import com.project.cinema.model.Hall;
import com.project.cinema.model.Movie;
import com.project.cinema.repos.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final MovieService movieService;
    private final HallService hallService;

    @Autowired
    public SessionService(SessionRepository sessionRepository, MovieService movieService, HallService hallService) {
        this.sessionRepository = sessionRepository;
        this.movieService = movieService;
        this.hallService = hallService;
    }

    public Session getSessionByIdEntity(Long sessionId) {
        return sessionRepository.findById(sessionId).orElseThrow(() -> new RuntimeException("Seans Bulunamadı"));
    }

    public List<GetSessionDtoResponse> getAllSessions(Optional<Long> movieId, Optional<Long> hallId) {
        List<GetSessionDtoResponse> getSessionDtoResponseList = new ArrayList<>();
        List<Session> sessionList;
        if (movieId.isPresent() && hallId.isEmpty()) {
            sessionList = sessionRepository.findByMovieIdOrderByStartDateTimeAsc(movieId.get());
        } else if (movieId.isEmpty() && hallId.isPresent()) {
            sessionList = sessionRepository.findByHallIdOrderByStartDateTimeAsc(hallId.get());
        } else if (movieId.isPresent() && hallId.isPresent()) {
            sessionList = sessionRepository.findByMovieIdAndHallIdOrderByStartDateTimeAsc(movieId.get(), hallId.get());
        } else {
            sessionList = sessionRepository.findAllByOrderByStartDateTimeAsc();
        }

        for (Session session : sessionList) {
            getSessionDtoResponseList.add(GetSessionDtoResponse.builder().
                    id(session.getId()).
                    movieId(session.getMovie().getId()).
                    movieTitle(session.getMovie().getTitle()).
                    startDateTime(session.getStartDateTime()).
                    hallId(session.getHall().getId()).
                    hallName(session.getHall().getName()).
                    hallCapacity(session.getHall().getCapacity()).
                    reservedSeatNum(session.getReservedSeats().size()).
                    reservedSeatIds(session.getReservedSeats().stream().map(Seat::getId).toList()).
                    build());
        }

        return getSessionDtoResponseList;
    }

    public Session saveSession(Session session) {
        return sessionRepository.save(session);
    }

    public SaveSessionDtoResponse addSession(SessionDtoRequest sessionDtoRequest) {

        Movie movie = movieService.getMovieById(sessionDtoRequest.getMovieId());
        Hall hall = hallService.getHallById(sessionDtoRequest.getHallId());

        if (movie == null || hall == null) {
            return null;
        } else {
            Session session = new Session();
            session.setHall(hall);
            session.setMovie(movie);
            session.setStartDateTime(sessionDtoRequest.getStartDateTime());

            List<Session> sessionList = sessionRepository.findAll();

            for (Session sessionDate : sessionList) {
                LocalDateTime dateTime = sessionDate.getStartDateTime();
                LocalDateTime newDateTime = sessionDtoRequest.getStartDateTime();

                if (newDateTime.toLocalDate().equals(dateTime.toLocalDate()) && sessionDtoRequest.getHallId().equals(sessionDate.getHall().getId())) {
                    if (Math.abs(newDateTime.getHour() - dateTime.getHour()) < 2) {
                        System.out.println("Ayni salonda filmler arasi en az 2 saat olmali.");
                        throw new RuntimeException("Ayni salonda filmler arasi en az 2 saat olmali.");
                    }
                }
            }


            Session save = sessionRepository.save(session);
            return SaveSessionDtoResponse.builder().
                    id(save.getId()).
                    movieId(save.getMovie().getId()).
                    movieTitle(save.getMovie().getTitle()).
                    startDateTime(save.getStartDateTime()).
                    hallName(save.getHall().getName()).
                    build();
        }
    }

    public GetSessionDtoResponse getSessionById(Long sessionId) {
        var session = getSessionByIdEntity(sessionId);

        return GetSessionDtoResponse.builder()
                .id(session.getId())
                .movieId(session.getMovie().getId())
                .hallId(session.getHall().getId())
                .startDateTime(session.getStartDateTime())
                .hallName(session.getHall().getName())
                .movieTitle(session.getMovie().getTitle())
                .hallCapacity(session.getHall().getCapacity())
                .reservedSeatNum(session.getReservedSeats().size())
                .posterImgPath(session.getMovie().getPosterImgPath())
                .reservedSeatIds(session.getReservedSeats().stream().map(Seat::getId).toList())
                .build();
    }

    public Session updateSessionById(Long sessionId, SessionDtoRequest sessionDtoRequest) {
        Movie movie = movieService.getMovieById(sessionDtoRequest.getMovieId());
        Hall hall = hallService.getHallById(sessionDtoRequest.getHallId());

        Optional<Session> session = sessionRepository.findById(sessionId);
        if (session.isPresent() && movie != null && hall != null) {
            Session foundedSession = session.get();
            foundedSession.setMovie(movie);
            foundedSession.setHall(hall);
            foundedSession.setStartDateTime(sessionDtoRequest.getStartDateTime());

            return sessionRepository.save(foundedSession);
        }
        return null;
    }

    public void deleteSessionById(Long sessionId) {
        sessionRepository.deleteById(sessionId);
    }

    public LinkedHashMap<String, List<HashMap<String, String>>> getSessionsByMovieId(Long movieId) {
        var seanslar = sessionRepository.findSessionsByMovieId(movieId);
        LinkedHashMap<String, List<HashMap<String, String>>> hashMap = new LinkedHashMap<>();
        seanslar.forEach(s -> {
            var a = s.getStartDateTime().format(DateTimeFormatter.ofPattern("dd LLLL yyyy HH:mm", new Locale("tr")));
            var date = (a.split(" ")[0].startsWith("0") ? a.split(" ")[0].charAt(1) : a.split(" ")[0]) + " " + a.split(" ")[1];
            var time = a.split(" ")[3];
            HashMap<String, String> saatler = new HashMap<>();
            saatler.put("id", s.getId().toString());
            saatler.put("time", time);

            hashMap.putIfAbsent(date, new ArrayList<>());
            hashMap.get(date).add(saatler);

        });

        System.out.println(hashMap);

        return hashMap;
    }
}
