package caseStudyModule2.services;

import caseStudyModule2.utils.InputHandler;
import caseStudyModule2.utils.PriceCalculator;
import caseStudyModule2.models.BookingData;
import caseStudyModule2.models.Movie;

public class PaymentProcessor {
    private InputHandler inputHandler;

    public PaymentProcessor(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    public void processPayment(Movie movie, String showTime, BookingData bookingData) {
        int totalPrice = PriceCalculator.calculateTotal(bookingData.getSeatPrices());

        displayPaymentConfirmation(movie, showTime, bookingData, totalPrice);

        System.out.print("Xác nhận thanh toán? (y/n): ");
        boolean confirmed = inputHandler.getConfirmation();

        if (confirmed) {
            completeBooking(movie, showTime, bookingData, totalPrice);
        } else {
            System.out.println("\n✗ Đã hủy đặt vé.\n");
        }
    }

    private void displayPaymentConfirmation(Movie movie, String showTime,
                                            BookingData bookingData, int totalPrice) {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║     XÁC NHẬN THANH TOÁN            ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.println("Phim: " + movie.getName());
        System.out.println("Suất chiếu: " + showTime);
        System.out.println("Phòng: " + movie.getRoomNumber());
        System.out.println("Ghế: " + String.join(", ", bookingData.getSelectedSeats()));
        System.out.println("Số lượng vé: " + bookingData.getSelectedSeats().size());
        System.out.println("Tổng tiền: " + PriceCalculator.formatPrice(totalPrice));
        System.out.println("────────────────────────────────────");
    }

    private void completeBooking(Movie movie, String showTime,
                                 BookingData bookingData, int totalPrice) {
        bookingData.getRoom().saveSeats();

        printTicket(movie, showTime, bookingData, totalPrice);
    }

    private void printTicket(Movie movie, String showTime,
                             BookingData bookingData, int totalPrice) {
        System.out.println("\n✓ ĐẶT VÉ THÀNH CÔNG!\n");
        System.out.println("╔════════════════════════════════════╗");
        System.out.println("║         VÉ XEM PHIM                ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.println("Phim: " + movie.getName());
        System.out.println("Suất chiếu: " + showTime);
        System.out.println("Phòng: " + movie.getRoomNumber());

        System.out.println("\nChi tiết vé:");
        for (int i = 0; i < bookingData.getSelectedSeats().size(); i++) {
            String seat = bookingData.getSelectedSeats().get(i);
            int price = bookingData.getSeatPrices().get(i);
            System.out.println("  " + (i + 1) + ". Ghế " + seat +
                    " - " + PriceCalculator.formatPrice(price));
        }

        System.out.println("\nTổng thanh toán: " + PriceCalculator.formatPrice(totalPrice));
        System.out.println("════════════════════════════════════");
        System.out.println("Cảm ơn quý khách! Chúc bạn xem phim vui vẻ!\n");
    }
}
