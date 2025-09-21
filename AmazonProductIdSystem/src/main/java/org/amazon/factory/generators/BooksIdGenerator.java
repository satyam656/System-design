package org.amazon.factory;

// Books Id generator (format : BK-XXXXXXXXXX)
public class BooksIdGenerator extends IdGenerator{

    public BooksIdGenerator(String prefix, int length) {
        super("BK", 10);
    }

    @Override
    public boolean validateId(String id) {
        return id != null && id.matches("^BK-[A-Z0-9]{10}$");
    }

    @Override
    public String generateId() {
        return prefix + "-" + generateRandomString(length);
    }
}
