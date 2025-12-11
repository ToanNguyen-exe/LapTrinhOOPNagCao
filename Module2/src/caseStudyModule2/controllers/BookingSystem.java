package caseStudyModule2.controllers;


import caseStudyModule2.Utils.InputHandler;
import caseStudyModule2.Utils.MovieSelector;
import caseStudyModule2.models.BookingData;
import caseStudyModule2.models.Movies;
import caseStudyModule2.services.MovieManager;
import caseStudyModule2.services.PaymentProcessor;
import caseStudyModule2.services.SeatSelector;

import java.util.Scanner;

public class BookingSystem {
    private final MovieManager movieManager;
    private final MovieSelector movieSelector;
    private final SeatSelector seatSelector;
    private final PaymentProcessor paymentProcessor;
    private final Scanner scanner;

    public BookingSystem(MovieManager movieManager) {
        this.movieManager = movieManager;
        this.scanner = new Scanner(System.in);
        InputHandler inputHandler = new InputHandler(scanner);
        this.movieSelector = new MovieSelector(movieManager, inputHandler);
        this.seatSelector = new SeatSelector(inputHandler);
        this.paymentProcessor = new PaymentProcessor(inputHandler);
    }

    public void run() {
        int choice;
        do {
            displayMainMenu();
            choice = getUserChoice();

            handleMenuChoice(choice);

        } while (choice != 4);

        scanner.close();
    }

    private void displayMainMenu() {
        System.out.println("\n===== MOVIE BOOKING SYSTEM =====");
        System.out.println("1. Xem danh sách phim");
        System.out.println("2. Đặt vé xem phim");
        System.out.println("3. Thoát");
        System.out.print("Chọn chức năng: ");
    }

    private int getUserChoice() {
        try {
            String input = scanner.nextLine().trim();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Vui lòng nhập số hợp lệ!");
            return -1;
        }
    }

    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                movieManager.displayMovies();
                break;
            case 2:
                processBooking();
                break;
            case 4:
                System.out.println("\nCảm ơn bạn đã sử dụng hệ thống!");
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
        }
    }


    private void processBooking() {
        System.out.println("\n===== ĐẶT VÉ XEM PHIM =====");

        Movies selectedMovie = movieSelector.selectMovie();
        if (selectedMovie == null) return;

        String selectedTime = movieSelector.selectShowTime(selectedMovie);
        if (selectedTime == null) return;

        BookingData bookingData = seatSelector.selectSeats(selectedMovie, selectedTime);
        if (bookingData == null) return;

        paymentProcessor.processPayment(selectedMovie, selectedTime, bookingData);
    }

}
