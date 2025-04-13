package org.bookmyshow.seat;

public class Seat {
    private final String id;
    private final int row;
    private final int column;
    private final SeatType seatType;
    private final double price;
    private SeatStatus seatStatus;

    public Seat(String id, int row, int column, SeatType seatType, double price, SeatStatus seatStatus) {
        this.id = id;
        this.row = row;
        this.column = column;
        this.seatType = seatType;
        this.price = price;
        this.seatStatus = seatStatus;
    }

    public void setSeatStatus(SeatStatus seatStatus) {
        this.seatStatus = seatStatus;
    }

    public String getId() {
        return id;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public double getPrice() {
        return price;
    }

    public SeatStatus getSeatStatus() {
        return seatStatus;
    }
}
