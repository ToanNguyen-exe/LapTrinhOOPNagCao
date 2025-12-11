package caseStudyModule2.utils;

import java.util.Scanner;

public class InputHandler {
    private Scanner scanner;

    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getIntInput() {
        try {
            String input = scanner.nextLine().trim();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Vui lòng nhập số hợp lệ!");
            return -1;
        }
    }

    public String getStringInput() {
        String input = scanner.nextLine().trim();
        return input;
    }

    public String getUpperCaseInput() {
        return scanner.nextLine().toUpperCase().trim();
    }

    public boolean getConfirmation() {
        String input = scanner.nextLine().toLowerCase().trim();
        return input.equals("y") || input.equals("yes");
    }

    public boolean isValidSeatFormat(String seat) {
        if (seat.length() < 2) return false;

        char row = seat.charAt(0);
        if (row < 'A' || row > 'H') return false;

        try {
            int col = Integer.parseInt(seat.substring(1));
            return col >= 1 && col <= 10;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public int[] parseSeatCoordinates(String seat) {
        int row = seat.charAt(0) - 'A';
        int col = Integer.parseInt(seat.substring(1)) - 1;
        return new int[]{row, col};
    }
}
