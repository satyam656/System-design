package org.amazon.factory;

// Home & Kitchen ID Generator (Format: HK-NNNNNNNN) - Sequential numbers
class HomeKitchenIdGenerator extends IdGenerator {
    public HomeKitchenIdGenerator() {
        super("HK", 8);
    }

    @Override
    public String generateId() {
        return prefix + "-" + generateRandomString(length);
    }

    @Override
    public boolean validateId(String id) {
        return id != null &&
                id.matches("^HK-[0-9]{8}$");
    }
}
