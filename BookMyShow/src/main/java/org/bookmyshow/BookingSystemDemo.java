package org.bookmyshow;

import org.bookmyshow.booking.Booking;
import org.bookmyshow.seat.Seat;
import org.bookmyshow.seat.SeatStatus;
import org.bookmyshow.seat.SeatType;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.*;

public class BookingSystemDemo {
    public static void main(String[] args) {
        MovieTicketBookingSystem bookingSystem = MovieTicketBookingSystem.getInstance();

        Movie movie1 = new Movie("M1", "Movie1", "description1", 120);
        Movie movie2 = new Movie("M2", "Movie2", "description2", 135);
        bookingSystem.addMovie(movie1);
        bookingSystem.addMovie(movie2);

        Theatre theatre1 = new Theatre("T1", "Theatre1", "Location1", new ArrayList<>());
        Theatre theatre2 = new Theatre("T2", "Theatre2", "Location2", new ArrayList<>());
        bookingSystem.addTheater(theatre1);
        bookingSystem.addTheater(theatre2);

        Show show1 = new Show("S1", theatre1, movie1, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(movie1.getDurationInMinutes()), createSeats(10, 10));
        Show show2 = new Show("S2", theatre2, movie2, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(movie2.getDurationInMinutes()), createSeats(8, 9));

        bookingSystem.addShow(show1);
        bookingSystem.addShow(show2);

        User user1 = new User("u1", "user1", "email1");
        List<Seat> selectedSeats = Arrays.asList(show1.getSeatMap().get("1-5"), show1.getSeatMap().get("1-6"));
        Booking booking = bookingSystem.bookTickets(user1, show1, selectedSeats);
        if(booking != null) {
            System.out.println("Booking Id: " + booking.getId());
            bookingSystem.confirmBooking(booking.getId());
        } else {
            System.out.println("Booking failed");
        }

        User user2 = new User("u2", "user2", "email2");
        List<Seat> selectedSeats2 = Arrays.asList(show1.getSeatMap().get("1-5"));
        Booking booking1 = bookingSystem.bookTickets(user2, show1, selectedSeats2);
        if(booking1 != null) {
            System.out.println("Booking Id: " + booking1.getId());
            bookingSystem.confirmBooking(booking1.getId());
        } else {
            System.out.println("Booking failed");
        }
    }

    private static Map<String, Seat> createSeats(int row, int column) {
        Map<String, Seat> seatMap = new HashMap<>();
        for(int i = 1; i <= row; i++) {
            for(int j = 1; j <= column; j++) {
                String seatId = i + "-" + j;
                SeatType seatType = (i <= 2) ? SeatType.PREMIUM : SeatType.NORMAL;
                double seatPrice = (seatType == SeatType.PREMIUM) ? 200.0: 150.0;
                Seat seat = new Seat(seatId, i, j, seatType, seatPrice, SeatStatus.AVAILABLE);
                seatMap.put(seatId, seat);
            }
        }
        return seatMap;

    }
}