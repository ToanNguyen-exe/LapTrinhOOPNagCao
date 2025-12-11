package caseStudyModule2.services;

import caseStudyModule2.utils.InputHandler;
import caseStudyModule2.utils.PriceCalculator;
import caseStudyModule2.utils.SeatPrice;
import caseStudyModule2.models.BookingData;
import caseStudyModule2.models.Movie;
import caseStudyModule2.models.Room;

import java.util.ArrayList;

public class SeatSelector {
    private final InputHandler inputHandler;

    public SeatSelector(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    public BookingData selectSeats(Movie movie, String showTime) {
        String roomKey = generateRoomKey(movie.getId(), showTime);
        Room room = new Room(roomKey);

        ArrayList<String> selectedSeats = new ArrayList<>();
        ArrayList<Integer> seatPrices = new ArrayList<>();

        while (true) {
            displaySeatSelection(room, selectedSeats, seatPrices);

            System.out.print("\nChọn ghế (vd: A5), 'Ok' để thanh toán, hoặc '0' để hủy: ");
            String input = inputHandler.getUpperCaseInput();

            if (input.equals("0")) {
                System.out.println("Đã hủy đặt vé.\n");
                return null;
            }

            if (input.equals("OK")) {
                if (selectedSeats.isEmpty()) {
                    System.out.println("Bạn chưa chọn ghế nào!\n");
                    continue;
                }
                return new BookingData(room, selectedSeats, seatPrices);
            }

            processSeatSelection(room, input, selectedSeats, seatPrices);
        }
    }

    private void displaySeatSelection(Room room, ArrayList<String> selectedSeats,
                                      ArrayList<Integer> seatPrices) {
        System.out.println("\n===== CHỌN GHẾ =====");
        room.getSeatMap().show();

        if (!selectedSeats.isEmpty()) {
            System.out.println("\n📌 Ghế đã chọn: " + String.join(", ", selectedSeats));
            int total = PriceCalculator.calculateTotal(seatPrices);
            System.out.println("Tổng tạm tính: " + PriceCalculator.formatPrice(total));
        }
    }

    private void processSeatSelection(Room room, String seatInput,
                                      ArrayList<String> selectedSeats,
                                      ArrayList<Integer> seatPrices) {
        if (!inputHandler.isValidSeatFormat(seatInput)) {
            System.out.println("Ghế không hợp lệ!\n");
            return;
        }

        int[] coords = inputHandler.parseSeatCoordinates(seatInput);
        int row = coords[0];
        int col = coords[1];

        if (row == 7) {
            handleCoupleSeats(room, seatInput, row, col, selectedSeats, seatPrices);
            return;
        }

        if (room.getSeatMap().isTaken(row, col)) {
            System.out.println("Ghế đã được đặt. Vui lòng chọn ghế khác!\n");
            return;
        }

        room.getSeatMap().book(row, col);
        selectedSeats.add(seatInput);

        int price = SeatPrice.getPrice(seatInput);
        seatPrices.add(price);

        System.out.println("✓ Đã thêm ghế " + seatInput + " (" +
                PriceCalculator.formatPrice(price) + ")");
    }

    private void handleCoupleSeats(Room room, String seatInput, int row, int col,
                                   ArrayList<String> selectedSeats,
                                   ArrayList<Integer> seatPrices) {

        int pairCol = (col % 2 == 0) ? col + 1 : col - 1;

        if (pairCol < 0 || pairCol >= 10) {
            System.out.println("Ghế đôi phải chọn theo cặp! Vui lòng chọn ghế hợp lệ.\n");
            return;
        }

        if (room.getSeatMap().isTaken(row, col)) {
            System.out.println("Ghế " + seatInput + " đã được đặt. Vui lòng chọn cặp ghế khác!\n");
            return;
        }

        String pairSeatName = "H" + (pairCol + 1);
        if (room.getSeatMap().isTaken(row, pairCol)) {
            System.out.println("Ghế cặp " + pairSeatName + " đã được đặt. Vui lòng chọn cặp ghế khác!\n");
            return;
        }

        room.getSeatMap().book(row, col);
        room.getSeatMap().book(row, pairCol);

        String seat1 = (col < pairCol) ? seatInput : pairSeatName;
        String seat2 = (col < pairCol) ? pairSeatName : seatInput;

        selectedSeats.add(seat1);
        selectedSeats.add(seat2);

        seatPrices.add(65_000);
        seatPrices.add(65_000);

        System.out.println("✓ Đã thêm ghế đôi " + seat1 + " & " + seat2 +
                " (130,000 VNĐ cho cả cặp)");
    }

    private String generateRoomKey(int movieId, String showTime) {
        return movieId + "_" + showTime.replace(":", "");
    }
}
