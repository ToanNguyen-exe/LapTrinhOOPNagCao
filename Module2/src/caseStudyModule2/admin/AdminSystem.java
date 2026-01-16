package caseStudyModule2.admin;

import caseStudyModule2.services.DataManager;
import caseStudyModule2.utils.InputHandler;
import caseStudyModule2.models.Movie;
import caseStudyModule2.models.Room;
import caseStudyModule2.services.MovieManager;
import caseStudyModule2.utils.TimeValidator;

public class AdminSystem {
    private final MovieManager movieManager;
    private final InputHandler inputHandler;
    private final AdminAuth adminAuth;

    public AdminSystem(MovieManager movieManager, InputHandler inputHandler) {
        this.movieManager = movieManager;
        this.inputHandler = inputHandler;
        this.adminAuth = new AdminAuth(inputHandler);
    }

    public void run() {
        if (!adminAuth.login()) {
            return;
        }

        int choice = 0;
        do {
            displayAdminMenu();
            choice = inputHandler.getIntInput();

            handleAdminChoice(choice);

        } while (choice != 6);
    }


    private void displayAdminMenu() {
        System.out.println("\n===== ADMIN PANEL =====");
        System.out.println("1. Xem danh sách phim");
        System.out.println("2. Thêm phim mới");
        System.out.println("3. Xóa phim");
        System.out.println("4. Xem thống kê đặt vé");
        System.out.println("5. Reset ghế theo phòng");
        System.out.println("6. Đăng xuất");
        System.out.print("Chọn chức năng: ");
    }

    private void handleAdminChoice(int choice) {
        switch (choice) {
            case 1 -> viewAllMovies();
            case 2 -> addNewMovie();
            case 3 -> removeMovie();
            case 4 -> viewBookingStats();
            case 5 -> resetRoomSeats();
            case 6 -> System.out.println("Đã đăng xuất khỏi admin panel.");
            default -> System.out.println("Lựa chọn không hợp lệ!");
        }
    }

    private void viewAllMovies() {
        movieManager.displayMoviesForAdmin();
    }

    private void addNewMovie() {
        System.out.println("\n===== THÊM PHIM MỚI =====");

        System.out.print("Tên phim: ");
        String name = inputHandler.getStringInput();

        if (name.isEmpty()) {
            System.out.println("Tên phim không được để trống!\n");
            return;
        }

        System.out.print("Số phòng: ");
        int roomNumber = inputHandler.getIntInput();

        if (roomNumber <= 0) {
            System.out.println("Số phòng không hợp lệ!\n");
            return;
        }

        System.out.println("Nhập các suất chiếu (định dạng HH:mm, cách nhau bởi dấu phẩy)");
        System.out.print("Ví dụ: 09:00, 14:30, 18:00: ");
        String timesInput = inputHandler.getStringInput();

        String[] showTimes = timesInput.split(",");

        for (int i = 0; i < showTimes.length; i++) {
            String formatted = TimeValidator.formatTime(showTimes[i]);
            if (formatted == null) {
                System.out.println("✗ Suất chiếu không hợp lệ: '" + showTimes[i].trim() + "'");
                System.out.println("Định dạng đúng: HH:mm (ví dụ: 09:00, 14:30)\n");
                return;
            }
            showTimes[i] = formatted;
        }

        movieManager.addMovie(name, roomNumber, showTimes);
        System.out.println("\n✓ Đã thêm phim thành công!");
        System.out.println("Phim: " + name);
        System.out.println("Phòng: " + roomNumber);
        System.out.println("Suất chiếu: " + String.join(", ", showTimes) + "\n");
    }


    private void removeMovie() {
        System.out.println("\n===== XÓA PHIM =====");
        movieManager.displayMoviesForAdmin();

        System.out.print("Nhập ID phim cần xóa: ");
        int movieId = inputHandler.getIntInput();

        Movie movie = movieManager.getMovieById(movieId);
        if (movie == null) {
            System.out.println("Không tìm thấy phim!\n");
            return;
        }

        System.out.println("\nThông tin phim sẽ xóa:");
        System.out.println("ID: " + movie.getId());
        System.out.println("Tên: " + movie.getName());
        System.out.println("Phòng: " + movie.getRoomNumber());
        System.out.print("\nBạn có chắc muốn xóa? (y/n): ");

        if (inputHandler.getConfirmation()) {
            movieManager.removeMovie(movieId);
            System.out.println("\n✓ Đã xóa phim thành công!\n");
        } else {
            System.out.println("Đã hủy xóa phim.\n");
        }
    }

    private void viewBookingStats() {
        System.out.println("\n===== THỐNG KÊ ĐẶT VÉ =====");

        for (Movie movie : movieManager.getAllMovies()) {
            System.out.println("\nPhim: " + movie.getName());

            for (String showTime : movie.getShowTimes()) {
                String roomKey = movie.getId() + "_" + showTime.replace(":", "");
                Room room = new Room(roomKey);

                int bookedSeats = countBookedSeats(room);
                int totalSeats = 80;
                double percentage = (bookedSeats * 100.0) / totalSeats;

                System.out.printf("  Suất %s: %d/%d ghế (%.1f%%)\n",
                        showTime, bookedSeats, totalSeats, percentage);
            }
        }
        System.out.println();
    }

    private int countBookedSeats(Room room) {
        int count = 0;
        boolean[][] seats = room.getSeatMap().getSeats();

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }


    private void resetRoomSeats() {
        System.out.println("\n===== RESET GHẾ PHÒNG =====");
        movieManager.displayMoviesForAdmin();

        System.out.print("Nhập ID phim: ");
        int movieId = inputHandler.getIntInput();

        Movie movie = movieManager.getMovieById(movieId);
        if (movie == null) {
            System.out.println("Không tìm thấy phim!\n");
            return;
        }

        System.out.println("\nCác suất chiếu:");
        String[] showTimes = movie.getShowTimes().toArray(new String[0]);
        for (int i = 0; i < showTimes.length; i++) {
            System.out.println((i + 1) + ". " + showTimes[i]);
        }

        System.out.print("Chọn suất chiếu cần reset: ");
        int choice = inputHandler.getIntInput();

        if (choice < 1 || choice > showTimes.length) {
            System.out.println("Suất chiếu không hợp lệ!\n");
            return;
        }

        String showTime = showTimes[choice - 1];
        String roomKey = movie.getId() + "_" + showTime.replace(":", "");

        System.out.println("CẢNH BÁO: Thao tác này sẽ xóa tất cả ghế đã đặt!");
        System.out.print("Xác nhận reset (y/n): ");

        if (inputHandler.getConfirmation()) {
            DataManager.deleteSeats(roomKey);
            System.out.println("\n✓ Đã reset ghế thành công!\n");
        } else {
            System.out.println("Đã hủy reset.\n");
        }
    }


}
