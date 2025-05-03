package org.example;

import org.example.book.*;
import org.example.booking.Booking;
import org.example.inventory.BookCatalog;
import org.example.user.Librarian;
import org.example.user.Member;

import java.util.List;

public class LibraryManagementSystem {

    BookCatalog bookCatalog;
    List<Member> memberList;
    Librarian librarian;

    public LibraryManagementSystem(Librarian librarian, List<Member> memberList, List<BookItem> bookItemList) {
        bookCatalog = new BookCatalog(bookItemList);
        this.memberList = memberList;
        this.librarian = librarian;
    }

    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }

    public void addBook(BookItem bookItem, Librarian librarian) {
        if(librarian.equals(this.librarian)) {
            bookCatalog.addBook(bookItem);
        }
    }

    public void markBookAsLost(BookItem bookItem, Librarian librarian) {
        if(librarian.equals(this.librarian)) {
            bookCatalog.markBookAsLost(bookItem);
        }
    }

    public boolean checkAvailability(String bookId) {
        return bookCatalog.checkAvailability(bookId);
    }

    public Booking issueBook(String bookId, Librarian librarian, Member member) {
        Booking booking = null;
        if(librarian.equals(this.librarian)) {
            booking = bookCatalog.issueBook(bookId, member);
        }
        return booking;
    }

    public void submitBook(String bookingId, Member member) {
        bookCatalog.submitBook(bookingId, member);
    }

    public List<BookSearchResult> searchByTitle(String title) {
        return bookCatalog.searchByTitle(title);
    }

    public List<BookSearchResult> searchByAuthor(Author author) {
        return bookCatalog.searchByAuthor(author);
    }

    public List<BookSearchResult> searchByGenre(BookGenre bookGenre) {
        return bookCatalog.searchByGenre(bookGenre);
    }

    public List<BookItem> getAllBooksByMember(Member member) {
        return bookCatalog.getAllBooksByMember(member);
    }
    public Member getMemberByBookItemBarcode(String barcode) {
        return bookCatalog.getBookItemByBarcode(barcode);
    }
}
