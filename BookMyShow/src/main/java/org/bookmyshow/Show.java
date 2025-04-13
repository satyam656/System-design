package org.bookmyshow;

import org.bookmyshow.seat.Seat;

import java.time.LocalDateTime;
import java.util.Map;

public class Show {
    private final String id;
    private final Theatre theatre;
    private final Movie movie;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final Map<String, Seat> seatMap;

    public Show(String id, Theatre theatre, Movie movie, LocalDateTime startTime, LocalDateTime endTime, Map<String, Seat> seatMap) {
        this.id = id;
        this.theatre = theatre;
        this.movie = movie;
        this.startTime = startTime;
        this.endTime = endTime;
        this.seatMap = seatMap;
    }

    public String getId() {
        return id;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Map<String, Seat> getSeatMap() {
        return seatMap;
    }
}
