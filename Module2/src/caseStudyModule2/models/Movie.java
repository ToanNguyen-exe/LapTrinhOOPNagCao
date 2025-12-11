package caseStudyModule2.models;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private int roomNumber;
    private List<String> showTimes;

    public Movie(int id, String name, int roomNumber, List<String> showTimes) {
        this.id = id;
        this.name = name;
        this.roomNumber = roomNumber;
        this.showTimes = showTimes;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public List<String> getShowTimes() {
        return showTimes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShowTimes(List<String> showTimes) {
        this.showTimes = showTimes;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return id + ". " + name + " (Phòng " + roomNumber + ")";
    }
}
