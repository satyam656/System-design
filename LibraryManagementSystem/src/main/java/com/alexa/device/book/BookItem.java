package org.example.book;

import org.example.user.Member;

import java.util.Date;
import java.util.List;

public class BookItem extends Book{
    private final String barcode;
    private final Date publicationDate;
    private Rack rack;
    private BookingStatus bookingStatus;
    private Member bookedBy;

    public BookItem(String bookId, String title, BookGenre genre, List<Author> authorList,
                    String barcode, Date publicationDate) {
        super(bookId, title, genre, authorList);
        this.barcode = barcode;
        this.publicationDate = publicationDate;
    }

    public String getBarcode() {
        return barcode;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public Rack getRack() {
        return rack;
    }

    public void setRack(Rack rack) {
        this.rack = rack;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public void setBookedBy(Member bookedBy) {
        this.bookedBy = bookedBy;
    }

    public Member getBookedBy() {
        return bookedBy;
    }
}
