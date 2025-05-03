package org.example.booking;

import org.example.book.BookItem;

import java.time.LocalDate;
import java.util.Date;

public class Booking {
    private final String bookingId;
    private final BookItem bookItem;
    private final LocalDate issueDate;
    private final LocalDate dueDate;
    private boolean isSubmitted;

    public Booking(String bookingId, BookItem bookItem, LocalDate issueDate, LocalDate dueDate) {
        this.bookingId = bookingId;
        this.bookItem = bookItem;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
    }

    public String getBookingId() {
        return bookingId;
    }

    public BookItem getBookItem() {
        return bookItem;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isSubmitted() {
        return isSubmitted;
    }

    public void setSubmitted(boolean submitted) {
        isSubmitted = submitted;
    }
}
