package caseStudyModule2.models;

import caseStudyModule2.services.FileManager;

public class SeatMap {
    private final boolean[][] seats;

    public SeatMap(String roomKey) {
        boolean[][] loadedSeats = FileManager.loadRoom(roomKey);

        if (loadedSeats != null) {
            seats = loadedSeats;
        } else {
            int rows = 8;
            int cols = 10;
            seats = new boolean[rows][cols];
        }
    }

    public boolean isTaken(int r, int c) {
        return seats[r][c];
    }

    public void book(int r, int c) {
        seats[r][c] = true;
    }

    public boolean[][] getSeats() {
        return seats;
    }

    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String WHITE = "\u001B[37m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String RED = "\u001B[31m";

    private String getColor(int row, int col) {
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

    public void show() {
        System.out.println("===== SƠ ĐỒ GHẾ =====");
        System.out.print("   ");
        for (int i = 1; i <= 10; i++) System.out.print(i + " ");
        System.out.println();

        for (int i = 0; i < 8; i++) {
            char rowName = (char) ('A' + i);
            System.out.print(rowName + "  ");
            for (int j = 0; j < 10; j++) {
                String color = getColor(i, j);

                if (seats[i][j]) {
                    System.out.print(RED + "X " + RESET);
                } else {
                    System.out.print(color + "O " + RESET);
                }
            }
            System.out.println();
        }

        System.out.println("\nChú thích:");
        System.out.println(GREEN + "● Ghế thường (50,000 VNĐ)" + RESET);
        System.out.println(WHITE + "● Ghế tiêu chuẩn (75,000 VNĐ)" + RESET);
        System.out.println(YELLOW + "● Ghế VIP (90,000 VNĐ)" + RESET);
        System.out.println(MAGENTA + "● Ghế Couple (130,000 VNĐ)" + RESET);
        System.out.println(RED + "X Ghế đã đặt" + RESET);
    }
}
