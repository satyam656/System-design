package org.amazon.factory;

// Electronics ID generator (format : ELC-XXXXXXXX)
public class ElectronicsIdGenerator extends IdGenerator{

    public ElectronicsIdGenerator() {
        super("ELC", 8);
    }

    @Override
    public boolean validateId(String id) {
        return id != null && id.matches("^ELC-[A-Z0-9]{8}$");
    }

    @Override
    public String generateId() {
        return prefix + "-" + generateRandomString(length);
    }
}
