package caseStudyModule2.models;

import caseStudyModule2.services.DataManager;

public class SeatMap {
    private boolean[][] seats;
    private final int rows = 8;
    private final int cols = 10;

    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String WHITE = "\u001B[37m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String RED = "\u001B[31m";

    public SeatMap(String roomKey) {
        seats = loadSeatsFromFile(roomKey);
    }

    private boolean[][] loadSeatsFromFile(String roomKey) {
        boolean[][] loadedSeats = DataManager.loadSeats(roomKey);
        return (loadedSeats != null) ? loadedSeats : new boolean[rows][cols];
    }

    public boolean isTaken(int row, int col) {
        return seats[row][col];
    }

    public void book(int row, int col) {
        seats[row][col] = true;
    }

    public boolean[][] getSeats() {
        return seats;
    }

    public void show() {
        displayHeader();
        displaySeats();
        displayLegend();
    }

    private void displayHeader() {
        System.out.println("===== SƠ ĐỒ GHẾ =====");
        System.out.print("   ");
        for (int i = 1; i <= cols; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private void displaySeats() {
        for (int i = 0; i < rows; i++) {
            char rowName = (char) ('A' + i);
            System.out.print(rowName + "  ");

            for (int j = 0; j < cols; j++) {
                if (seats[i][j]) {
                    System.out.print(RED + "X " + RESET);
                } else {
                    String color = getSeatColor(i, j);
                    System.out.print(color + "O " + RESET);
                }
            }
            System.out.println();
        }
    }

    private void displayLegend() {
        System.out.println("\nChú thích:");
        System.out.println(GREEN + "● Ghế thường (50,000 VNĐ)" + RESET);
        System.out.println(WHITE + "● Ghế tiêu chuẩn (75,000 VNĐ)" + RESET);
        System.out.println(YELLOW + "● Ghế VIP (90,000 VNĐ)" + RESET);
        System.out.println(MAGENTA + "● Ghế Couple - Hàng H (130,000 VNĐ/cặp)" + RESET);
        System.out.println(RED + "X Ghế đã đặt" + RESET);
    }

    private String getSeatColor(int row, int col) {
        char rowChar = (char) ('A' + row);

        if (rowChar == 'A' || rowChar == 'B') {
            return GREEN;
        }

        if ((rowChar == 'D' || rowChar == 'E' || rowChar == 'F')
                && col >= 2 && col <= 7) {
            return YELLOW;
        }

        if (rowChar == 'H') {
            return MAGENTA;
        }
        return WHITE;
    }
}
