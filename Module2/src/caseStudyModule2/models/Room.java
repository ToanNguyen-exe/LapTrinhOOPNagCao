package caseStudyModule2.models;

import caseStudyModule2.services.FileManager;

public class Room {
    private final String roomKey;
    private final SeatMap seatMap;

    public Room(String roomKey) {
        this.roomKey = roomKey;
        this.seatMap = new SeatMap(roomKey);
    }

    public SeatMap getSeatMap() {
        return seatMap;
    }

    public void saveSeats() {
        FileManager.saveRoom(roomKey, seatMap.getSeats());
    }
}
