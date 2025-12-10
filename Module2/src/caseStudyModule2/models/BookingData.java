package caseStudyModule2.models;

import java.util.ArrayList;

public class BookingData {
    private Room room;
    private ArrayList<String> selectedSeats;
    private ArrayList<Integer> seatPrices;

    public BookingData(Room room, ArrayList<String> selectedSeats, ArrayList<Integer> seatPrices) {
        this.room = room;
        this.selectedSeats = selectedSeats;
        this.seatPrices = seatPrices;
    }

    public Room getRoom() {
        return room;
    }

    public ArrayList<String> getSelectedSeats() {
        return selectedSeats;
    }

    public ArrayList<Integer> getSeatPrices() {
        return seatPrices;
    }
}
