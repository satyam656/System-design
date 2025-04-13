package org.bookmyshow;

public class Movie {
    private final String id;
    private final String name;
    private final String description;
    private final int durationInMinutes;

    public Movie(String id, String name, String description, int durationInMinutes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.durationInMinutes = durationInMinutes;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }
}
