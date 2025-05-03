package org.example.book;

import java.util.List;

public class Book {
    protected final String bookId;
    protected final String title;
    protected final BookGenre genre;
    protected final List<Author> authorList;

    public Book(String bookId, String title, BookGenre genre, List<Author> authorList) {
        this.bookId = bookId;
        this.title = title;
        this.genre = genre;
        this.authorList = authorList;
    }

    public String getTitle() {
        return title;
    }

    public BookGenre getGenre() {
        return genre;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public String getBookId() {
        return bookId;
    }
}
