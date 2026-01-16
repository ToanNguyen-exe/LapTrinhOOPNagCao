package caseStudyModule2.services;

import caseStudyModule2.utils.InputHandler;
import caseStudyModule2.utils.PriceCalculator;
import caseStudyModule2.utils.SeatPrice;
import caseStudyModule2.models.BookingData;
import caseStudyModule2.models.Movie;
import caseStudyModule2.models.Room;

import java.util.ArrayList;

public class SeatSelector {
    private InputHandler inputHandler;

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

            System.out.print("\nChọn ghế (vd: A5), 'ok' để thanh toán, hoặc '0' để hủy: ");
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
            handleCoupleSeats(room, row, col, seatInput, selectedSeats, seatPrices);
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

    private void handleCoupleSeats(Room room, int row, int col, String seatInput,
                                   ArrayList<String> selectedSeats,
                                   ArrayList<Integer> seatPrices) {

        if (room.getSeatMap().isTaken(row, col)) {
            System.out.println("Ghế đã được đặt. Vui lòng chọn ghế khác!\n");
            return;
        }

        int coupleCol;
        String coupleSeatName;

        if (col % 2 == 0) {
            coupleCol = col + 1;
            coupleSeatName = "H" + (coupleCol + 1);
        }

        else {
            coupleCol = col - 1;
            coupleSeatName = "H" + (coupleCol + 1);
        }

        if (room.getSeatMap().isTaken(row, coupleCol)) {
            System.out.println("⚠️  Ghế Couple hàng H phải đặt 2 ghế cạnh nhau!");
            System.out.println("Ghế " + coupleSeatName + " đã được đặt. Vui lòng chọn cặp ghế khác!\n");
            return;
        }

        room.getSeatMap().book(row, col);
        room.getSeatMap().book(row, coupleCol);

        String seat1, seat2;
        if (col < coupleCol) {
            seat1 = seatInput;
            seat2 = coupleSeatName;
        } else {
            seat1 = coupleSeatName;
            seat2 = seatInput;
        }

        selectedSeats.add(seat1);
        selectedSeats.add(seat2);

        seatPrices.add(65000);
        seatPrices.add(65000);

        System.out.println("✓ Đã thêm ghế Couple: " + seat1 + " & " + seat2 +
                " (130,000 VNĐ/cặp)");
    }

    private String generateRoomKey(int movieId, String showTime) {
        return movieId + "_" + showTime.replace(":", "");
    }
}
