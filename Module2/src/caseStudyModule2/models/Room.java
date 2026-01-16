package caseStudyModule2.models;

import caseStudyModule2.services.DataManager;

public class Room {
    private String roomKey;
    private SeatMap seatMap;

    public Room(String roomKey) {
        this.roomKey = roomKey;
        this.seatMap = new SeatMap(roomKey);
    }

    public SeatMap getSeatMap() {
        return seatMap;
    }

    public void saveSeats() {
        DataManager.saveSeats(roomKey, seatMap.getSeats());
    }
}
