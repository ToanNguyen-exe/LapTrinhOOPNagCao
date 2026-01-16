package caseStudyModule2.models;

import java.util.List;

public class Movie {
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

    @Override
    public String toString() {
        return id + ". " + name + " (Phòng " + roomNumber + ")";
    }
}
