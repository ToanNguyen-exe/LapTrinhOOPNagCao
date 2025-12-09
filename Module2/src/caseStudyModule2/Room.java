package caseStudyModule2;

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

    public String getRoomKey() {
        return roomKey;
    }

    // Lưu trạng thái ghế vào file
    public void saveSeats() {
        FileManager.saveRoom(roomKey, seatMap.getSeats());
    }
}
