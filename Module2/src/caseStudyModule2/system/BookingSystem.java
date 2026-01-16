
package caseStudyModule2.system;

import caseStudyModule2.admin.AdminSystem;
import caseStudyModule2.models.Movie;
import caseStudyModule2.utils.InputHandler;
import caseStudyModule2.utils.MovieSearch;
import caseStudyModule2.utils.MovieSelector;
import caseStudyModule2.models.BookingData;
import caseStudyModule2.services.MovieManager;
import caseStudyModule2.services.PaymentProcessor;
import caseStudyModule2.services.SeatSelector;

import java.util.ArrayList;
import java.util.Scanner;

public class BookingSystem {
    private final MovieManager movieManager;
    private InputHandler inputHandler;
    private MovieSelector movieSelector;
    private MovieSearch movieSearch;
    private SeatSelector seatSelector;
    private PaymentProcessor paymentProcessor;
    private AdminSystem adminSystem;
    private Scanner scanner;

    public BookingSystem(MovieManager movieManager) {
        this.movieManager = movieManager;
        this.scanner = new Scanner(System.in);
        this.inputHandler = new InputHandler(scanner);
        this.movieSelector = new MovieSelector(movieManager, inputHandler);
        this.movieSearch = new MovieSearch(movieManager);
        this.seatSelector = new SeatSelector(inputHandler);
        this.paymentProcessor = new PaymentProcessor(inputHandler);
        this.adminSystem = new AdminSystem(movieManager, inputHandler);
    }

    public void run() {
        int choice = 0;
        do {
            displayMainMenu();
            choice = getUserChoice();

            handleMenuChoice(choice);

        } while (choice != 5);

        scanner.close();
    }

    private void displayMainMenu() {
        System.out.println("\n===== MOVIE BOOKING SYSTEM =====");
        System.out.println("1. Xem danh sách phim");
        System.out.println("2. Tìm kiếm phim");
        System.out.println("3. Đặt vé xem phim");
        System.out.println("4. Quản lý (Admin)");
        System.out.println("5. Thoát");
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
                handleSearchMovie();
                break;
            case 3:
                processBooking();
                break;
            case 4:
                handleAdminPanel();
                break;
            case 5:
                System.out.println("\nCảm ơn bạn đã sử dụng hệ thống!");
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
        }
    }

    private void handleSearchMovie() {
        System.out.println("\n===== TÌM KIẾM PHIM =====");
        System.out.print("Nhập từ khóa tìm kiếm: ");
        String keyword = inputHandler.getStringInput();

        if (keyword.isEmpty()) {
            System.out.println("Từ khóa không được để trống!\n");
            return;
        }

        ArrayList<Movie> results = movieSearch.searchMovies(keyword);
        movieSearch.displaySearchResults(results, keyword);

        if (!results.isEmpty()) {
            System.out.print("Bạn có muốn đặt vé cho phim nào không? (y/n): ");
            if (inputHandler.getConfirmation()) {
                System.out.print("Chọn phim (nhập số thứ tự): ");
                int choice = inputHandler.getIntInput();

                if (choice >= 1 && choice <= results.size()) {
                    Movie selectedMovie = results.get(choice - 1);
                    processBookingForMovie(selectedMovie);
                } else {
                    System.out.println("Lựa chọn không hợp lệ!\n");
                }
            }
        }
    }

    private void handleAdminPanel() {
        adminSystem.run();
    }

    private void processBooking() {
        System.out.println("\n===== ĐẶT VÉ XEM PHIM =====");

        Movie selectedMovie = movieSelector.selectMovie();
        if (selectedMovie == null) return;

        processBookingForMovie(selectedMovie);
    }

    private void processBookingForMovie(Movie movie) {
        String selectedTime = movieSelector.selectShowTime(movie);
        if (selectedTime == null) return;

        BookingData bookingData = seatSelector.selectSeats(movie, selectedTime);
        if (bookingData == null) return;

        paymentProcessor.processPayment(movie, selectedTime, bookingData);
    }
}
