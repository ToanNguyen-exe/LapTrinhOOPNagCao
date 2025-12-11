package caseStudyModule2.models;

import java.util.ArrayList;

public class BookingData {
    private final Room room;
    private final ArrayList<String> selectedSeats;
    private final ArrayList<Integer> seatPrices;

    public BookingData(Room room, ArrayList<String> selectedSeats, ArrayList<Integer> seatPrices) {
        this.room = room;
        this.selectedSeats = selectedSeats;
        this.seatPrices = seatPrices;
    }

    public Room getRoom() {
        return this.room;
    }

    public ArrayList<String> getSelectedSeats() {
        return this.selectedSeats;
    }

    public ArrayList<Integer> getSeatPrices() {
        return this.seatPrices;
    }
}
