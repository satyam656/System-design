package org.amazon.factory;

// Digital Products ID generator (format: DIG-XXXX-XXXX-XXXX-XXXX)
public class DigitalIdGenerator extends IdGenerator{

    public DigitalIdGenerator(String prefix, int length) {
        super("DIG", 12);
    }

    @Override
    public boolean validateId(String id) {
        return id != null && id.matches("^DIG-[A-Z0-9]{4}-[A-Z0-9]{4}-[A-Z0-9]{4}$");
    }

    @Override
    public String generateId() {
        String part1 = generateRandomString(4);
        String part2 = generateRandomString(4);
        String part3 = generateRandomString(4);
        return prefix + "-" + part1 + "-" + part2 + "-" + part3;
    }
}
