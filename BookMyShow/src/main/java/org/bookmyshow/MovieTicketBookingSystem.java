package org.bookmyshow;

import org.bookmyshow.booking.Booking;
import org.bookmyshow.booking.BookingStatus;
import org.bookmyshow.seat.Seat;
import org.bookmyshow.seat.SeatStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MovieTicketBookingSystem {
    private static MovieTicketBookingSystem instance;
    private final List<Movie> movies;
    private final List<Theatre> theatres;
    private final Map<String, Show> shows;
    private final Map<String, Booking> bookings;

    private static String BOOKING_ID_PREFIX = "BMS";
    private static AtomicLong bookingCounter = new AtomicLong(0);

    private MovieTicketBookingSystem() {
        movies = new ArrayList<>();
        theatres = new ArrayList<>();
        shows = new ConcurrentHashMap<>();
        bookings = new ConcurrentHashMap<>();
    }

    public static synchronized MovieTicketBookingSystem getInstance() {
        if(instance == null) {
            instance = new MovieTicketBookingSystem();
        }
        return instance;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void addTheater(Theatre theatre) {
        theatres.add(theatre);
    }

    public void addShow(Show show) {
        shows.put(show.getId(), show);
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Theatre> getTheaters() {
        return theatres;
    }

    public Show getShow(String showId) {
        return shows.get(showId);
    }

    public synchronized Booking bookTickets(User user, Show show, List<Seat> selectedSeats) {
        if(areSeatsAvailable(show, selectedSeats)) {
            markSeatsAsBooked(show, selectedSeats);
            double totalPrice = calculateTotalPrice(selectedSeats);
            String bookingId = generateBookingId();
            Booking booking = new Booking(bookingId, user, show, selectedSeats, totalPrice, BookingStatus.PENDING);
            bookings.put(bookingId, booking);
            return booking;
        }
        return null;
    }

    public synchronized boolean confirmBooking(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if(booking != null && booking.getStatus() == BookingStatus.PENDING) {
            // Process Payment and send Confirmation
            // ....
            booking.setStatus(BookingStatus.CONFIRMED);
            return true;
        }
        return false;
    }

    public synchronized boolean cancelBooking(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if(booking != null && booking.getStatus() != BookingStatus.CANCELLED) {
            // Refund Payment and Cancellation Notification
            // ..
            booking.setStatus(BookingStatus.CANCELLED);
            markSeatsAvailable(booking.getShow(), booking.getSeats());
            return true;
        }
        return false;
    }
    public void markSeatsAvailable(Show show, List<Seat> selectedSeats) {
        for(Seat seat: selectedSeats) {
            Seat currentShowSeat = show.getSeatMap().get(seat.getId());
            if(currentShowSeat != null && currentShowSeat.getSeatStatus() == SeatStatus.BOOKED) {
                currentShowSeat.setSeatStatus(SeatStatus.AVAILABLE);
            }
        }
    }

    private String generateBookingId() {
        long bookingNumber = bookingCounter.incrementAndGet();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        return BOOKING_ID_PREFIX + timestamp + String.format("%06d", bookingNumber);
    }

    private double calculateTotalPrice(List<Seat> selectedSeats) {
        return selectedSeats.stream().mapToDouble(Seat::getPrice).sum();
    }

    private void markSeatsAsBooked(Show show, List<Seat> selectedSeats) {
        for(Seat seat: selectedSeats) {
            Seat currentShowSeat = show.getSeatMap().get(seat.getId());
            currentShowSeat.setSeatStatus(SeatStatus.BOOKED);
        }
    }

    private boolean areSeatsAvailable(Show show, List<Seat> selectedSeats) {
        for(Seat seat: selectedSeats) {
            Seat currentSeat = show.getSeatMap().get(seat.getId());
            if(currentSeat == null || currentSeat.getSeatStatus() != SeatStatus.AVAILABLE) {
                return false;
            }
        }
        return true;
    }
}
