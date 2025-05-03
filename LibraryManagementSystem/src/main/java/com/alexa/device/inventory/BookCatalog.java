package org.example.inventory;

import org.example.book.*;
import org.example.booking.Booking;
import org.example.finance.FineCalculator;
import org.example.user.Member;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class BookCatalog {
    private List<Book> bookList;
    private Set<String> bookIds;
    private List<BookItem> bookItemList;
    private List<Booking> bookingList;
    private FineCalculator fineCalculator;

    private final static String BOOKING_PREFIX = "LMS_";
    private AtomicLong counter;

    public BookCatalog(List<BookItem> bookItemList) {
        this.bookItemList = bookItemList;
        this.bookIds = new HashSet<>();
        this.bookList = new ArrayList<>();
        this.bookingList = new ArrayList<>();

        this.counter = new AtomicLong();
        this.counter.set(1);

        fineCalculator = new FineCalculator();

        for(BookItem bookItem: bookItemList) {
            if(!bookIds.contains(bookItem.getBookId())) {
                bookIds.add(bookItem.getBookId());
                bookList.add(new Book(bookItem.getBookId(), bookItem.getTitle(), bookItem.getGenre(), bookItem.getAuthorList()));
            }
        }
    }

    public Book getBookById(String bookId) {
        return bookList.stream().filter(book -> book.getBookId().equals(bookId)).collect(Collectors.toList()).get(0);
    }

    public void addBook(BookItem bookItem) {
        if(!bookIds.contains(bookItem.getBookId())) {
            bookIds.add(bookItem.getBookId());
            bookList.add(new Book(bookItem.getBookId(), bookItem.getTitle(), bookItem.getGenre(), bookItem.getAuthorList()));
        }
        bookList.add(bookItem);
    }

    public void markBookAsLost(BookItem bookItem) {
        for(BookItem eachBookItem: bookItemList) {
            if(eachBookItem.equals(bookItem)) {
                eachBookItem.setBookingStatus(BookingStatus.LOST);
            }
        }
    }

    public Booking issueBook(String bookId, Member member) {

        int issuedBookCount = bookItemList.stream().filter(bookItem -> bookItem.getBookingStatus() == BookingStatus.BOOKED
                && bookItem.getBookedBy().equals(member)).collect(Collectors.toList()).size();
        if(issuedBookCount >= 5) {
            System.out.println("Member has taken 5 books already!");
            return null;
        }

        Booking booking = null;
        for(BookItem bookItem: bookItemList) {
            if(bookItem.getBookId().equals(bookId) && bookItem.getBookingStatus() == BookingStatus.AVAILABLE) {
                bookItem.setBookedBy(member);
                bookItem.setBookingStatus(BookingStatus.BOOKED);
                String bookingId = BOOKING_PREFIX + counter.incrementAndGet() + "_" + LocalDateTime.now();
                booking = new Booking(bookingId, bookItem, LocalDate.now(), LocalDate.now().plusDays(10));
                booking.setSubmitted(false);
                bookingList.add(booking);
                return booking;
            }
        }
        return null;
    }

    public boolean checkAvailability(String bookId) {
        for(BookItem bookItem: bookItemList) {
            if(bookItem.getBookId().equals(bookId) && bookItem.getBookingStatus() == BookingStatus.AVAILABLE)
                return true;
        }
        return false;
    }

    public void submitBook(String bookingId, Member member) {
        Booking booking = null;
        for(Booking eachBooking: bookingList) {
            if(eachBooking.getBookingId().equals(bookingId) && booking.getBookItem().getBookedBy().equals(member)) {
                booking = eachBooking;
                break;
            }
        }
        if(booking == null) {
            System.out.println("No Booking found for this member.");
        } else {
            booking.setSubmitted(true);
            double fine = fineCalculator.calculateFine(booking.getDueDate());
            System.out.println("Fine for this booking is : " + fine);
            BookItem bookItem = booking.getBookItem();
            bookItem.setBookingStatus(BookingStatus.AVAILABLE);
            bookItem.setBookedBy(null);
        }
    }

    public List<BookSearchResult> searchByTitle(String title) {
        List<BookItem> filteredBooks = bookItemList.stream().filter(book -> book.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
        return convertToBookSearchResultList(filteredBooks);
    }

    public List<BookSearchResult> searchByAuthor(Author author) {
        List<BookItem> filteredBookItems = bookItemList.stream()
                .filter(bookItem -> bookItem.getAuthorList().contains(author)).collect(Collectors.toList());
        return convertToBookSearchResultList(filteredBookItems);
    }

    public List<BookSearchResult> searchByGenre(BookGenre bookGenre) {
        List<BookItem> filteredBookItems = bookItemList.stream().filter(bookItem -> bookItem.getGenre() == bookGenre).collect(Collectors.toList());
        return convertToBookSearchResultList(filteredBookItems);
    }

    List<BookSearchResult> convertToBookSearchResultList(List<BookItem> filteredBooks) {
        Map<String, Integer> availabilityMap = new HashMap<>();

        for(BookItem bookItem: filteredBooks) {
            if(!availabilityMap.containsKey(bookItem.getBookId())) {
                availabilityMap.put(bookItem.getBookId(), 0);
            }
            if(bookItem.getBookingStatus() == BookingStatus.AVAILABLE)
                availabilityMap.merge(bookItem.getBookId(), 1, Integer::sum);
        }
        List<BookSearchResult> bookSearchResult = new ArrayList<>();

        for(Map.Entry<String, Integer> entry: availabilityMap.entrySet()) {
            bookSearchResult.add(new BookSearchResult(getBookById(entry.getKey()), entry.getValue()));
        }
        return bookSearchResult;
    }

    public List<BookItem> getAllBooksByMember(Member member) {
        return bookItemList.stream().filter(bookItem -> bookItem.getBookingStatus() == BookingStatus.AVAILABLE
                && bookItem.getBookedBy().equals(member)).collect(Collectors.toList());
    }

    public Member getBookItemByBarcode(String barcode) {
        Member member = null;
        for(BookItem bookItem: bookItemList) {
            if(bookItem.getBarcode().equals(barcode)) {
                member = bookItem.getBookedBy();
                break;
            }
        }
        return member;
    }

    public List<Booking> getAllBookingsWithApproachingDueDate(LocalDate now) {
        return bookingList.stream().filter(booking -> booking.isSubmitted() == false &&
                        booking.getDueDate().equals(now.minusDays(1)))
                .collect(Collectors.toList());

    }
}
