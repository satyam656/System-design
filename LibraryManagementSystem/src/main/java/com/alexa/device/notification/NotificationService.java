package org.example.notification;

import org.example.booking.Booking;
import org.example.inventory.BookCatalog;

import java.time.LocalDate;
import java.util.List;

public class NotificationService {

    BookCatalog bookCatalog;

    public NotificationService(BookCatalog bookCatalog) {
        this.bookCatalog = bookCatalog;
    }

    // This method will be more like cron job triggered once everyday.
    public void dueDateNotify() {
        List<Booking> allBookings = bookCatalog.getAllBookingsWithApproachingDueDate(LocalDate.now());
        for(Booking booking: allBookings) {
            if(booking.getDueDate().equals(LocalDate.now().minusDays(1))) {
                System.out.println("Your due date is approaching, Please submit your book.");
            }
            else if(booking.getDueDate().equals(LocalDate.now())) {
                System.out.println("Your due date is today. Please submit your book by EOD to avoid fine.");
            }
            else {
                System.out.println("Your due date has been passed. Please submit your book asap.");
            }
        }
    }

    public void subscribe(String bookId) {

    }

    public void notifyForAvailability() {

    }
}
