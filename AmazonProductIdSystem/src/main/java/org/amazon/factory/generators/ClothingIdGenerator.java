package org.amazon.factory;

// Clothing Id generator (format : CLT-XXX-XXXXX)
public class ClothingIdGenerator extends IdGenerator{
    public ClothingIdGenerator(String prefix, int length) {
        super("CLT", 8);
    }

    @Override
    public boolean validateId(String id) {
        return id != null && id.matches("^CLT-[A-Z0-9]{3}-[A-Z0-9]{5}$");
    }

    @Override
    public String generateId() {
        return prefix + "-" + generateRandomString(3) + "-" + generateRandomString(5);
    }
}
