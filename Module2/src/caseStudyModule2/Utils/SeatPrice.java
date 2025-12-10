package caseStudyModule2.Utils;

public class SeatPrice {
    public static int getPrice(String seat) {
        char row = seat.charAt(0);
        int num = Integer.parseInt(seat.substring(1));
        if (row == 'A' || row == 'B')
            return 50_000;
        if ((row == 'D' || row == 'E' || row == 'F') && num >= 3 && num <= 8)
            return 90_000;
        if (row == 'H')
            return 130_000;
        return 75_000;
    }
}

