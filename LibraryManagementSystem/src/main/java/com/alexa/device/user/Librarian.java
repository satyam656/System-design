package org.example.user;

public class Librarian extends Person{
    private final String librarianId;

    public Librarian(String name, String email, String librarianId) {
        super(name, email);
        this.librarianId = librarianId;
    }

    public String getLibrarianId() {
        return librarianId;
    }
}
