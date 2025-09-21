package org.amazon.factory;

import java.util.Random;

// Abstract base class for ID generator
abstract class IdGenerator {
    protected String prefix;
    protected int length;

    public IdGenerator(String prefix, int length) {
        this.prefix = prefix;
        this.length = length;
    }

    public abstract boolean validateId(String id);
    public abstract String generateId();

    protected String generateRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();

        for(int i = 0; i < length; i++) {
            stringBuilder.append(chars.charAt(random.nextInt(chars.length())));
        }
        return stringBuilder.toString();
    }
}
