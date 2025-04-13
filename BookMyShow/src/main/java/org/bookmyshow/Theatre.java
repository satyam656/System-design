package org.bookmyshow;

import java.util.List;

public class Theatre {
    private final String id;
    private final String name;
    private final String location;
    private final List<Show> shows;

    public Theatre(String id, String name, String location, List<Show> shows) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.shows = shows;
    }
}
