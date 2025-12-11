package caseStudyModule2.admin;

import caseStudyModule2.services.DataManager;
import caseStudyModule2.utils.InputHandler;
import caseStudyModule2.models.Movie;
import caseStudyModule2.models.Room;
import caseStudyModule2.services.MovieManager;

import java.util.ArrayList;
import java.util.List;

public class AdminSystem {
    private MovieManager movieManager;
    private InputHandler inputHandler;
    private AdminAuth adminAuth;

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

        } while (choice != 8);
    }

    private void displayAdminMenu() {
        System.out.println("\n===== ADMIN PANEL =====");
        System.out.println("1. Xem danh sách phim");
        System.out.println("2. Thêm phim mới");
        System.out.println("3. Sửa thông tin phim");
        System.out.println("4. Xóa phim");
        System.out.println("5. Xem thống kê đặt vé");
        System.out.println("6. Reset ghế theo phòng");
        System.out.println("7. Tìm kiếm phim");
        System.out.println("8. Đăng xuất");
        System.out.print("Chọn chức năng: ");
    }

    private void handleAdminChoice(int choice) {
        switch (choice) {
            case 1 -> viewAllMovies();
            case 2 -> addNewMovie();
            case 3 -> editMovie();
            case 4 -> removeMovie();
            case 5 -> viewBookingStats();
            case 6 -> resetRoomSeats();
            case 7 -> searchMovie();
            case 8 -> System.out.println("Đã đăng xuất khỏi admin panel.");
            default -> System.out.println("Lựa chọn không hợp lệ!");
        }
    }

    private void viewAllMovies() {
        System.out.println("\n===== DANH SÁCH PHIM CHI TIẾT =====");
        for (Movie movie : movieManager.getAllMovies()) {
            System.out.println("\nID: " + movie.getId());
            System.out.println("Tên: " + movie.getName());
            System.out.println("Phòng: " + movie.getRoomNumber());
            System.out.println("Suất chiếu: " + String.join(", ", movie.getShowTimes()));
            System.out.println("────────────────────");
        }
    }

    private void addNewMovie() {
        System.out.println("\n===== THÊM PHIM MỚI =====");

        System.out.print("Tên phim: ");
        String name = inputHandler.getStringInput();

        System.out.print("Số phòng: ");
        int roomNumber = inputHandler.getIntInput();

        System.out.print("Các suất chiếu (cách nhau bởi dấu phẩy, vd: 10:00,14:00,18:00): ");
        String timesInput = inputHandler.getStringInput();
        String[] showTimes = timesInput.split(",");

        for (int i = 0; i < showTimes.length; i++) {
            showTimes[i] = showTimes[i].trim();
        }

        movieManager.addMovie(name, roomNumber, showTimes);
        System.out.println("\n✓ Đã thêm phim thành công!\n");
    }

    private void editMovie() {
        System.out.println("\n===== SỬA THÔNG TIN PHIM =====");
        movieManager.displayMovies();

        System.out.print("Nhập ID phim cần sửa: ");
        int movieId = inputHandler.getIntInput();

        Movie movie = movieManager.getMovieById(movieId);
        if (movie == null) {
            System.out.println("Không tìm thấy phim!\n");
            return;
        }

        System.out.println("\nPhim hiện tại:");
        System.out.println("ID: " + movie.getId());
        System.out.println("Tên: " + movie.getName());
        System.out.println("Phòng: " + movie.getRoomNumber());
        System.out.println("Suất chiếu: " + String.join(", ", movie.getShowTimes()));

        System.out.println("\n--- Chọn thông tin cần sửa ---");
        System.out.println("1. Sửa tên phim");
        System.out.println("2. Sửa số phòng");
        System.out.println("3. Sửa giờ chiếu");
        System.out.println("4. Sửa tất cả");
        System.out.println("0. Hủy");
        System.out.print("Chọn: ");

        int editChoice = inputHandler.getIntInput();

        switch (editChoice) {
            case 1 -> editMovieName(movie);
            case 2 -> editRoomNumber(movie);
            case 3 -> editShowTimes(movie);
            case 4 -> editAllMovieInfo(movie);
            case 0 -> System.out.println("Đã hủy sửa phim.\n");
            default -> System.out.println("Lựa chọn không hợp lệ!\n");
        }
    }

    private void editMovieName(Movie movie) {
        System.out.print("\nNhập tên phim mới: ");
        String newName = inputHandler.getStringInput();

        if (newName.isEmpty()) {
            System.out.println("Tên phim không được để trống!\n");
            return;
        }

        movieManager.updateMovieName(movie.getId(), newName);
        System.out.println("\n✓ Đã cập nhật tên phim thành công!\n");
    }

    private void editRoomNumber(Movie movie) {
        System.out.print("\nNhập số phòng mới: ");
        int newRoom = inputHandler.getIntInput();

        if (newRoom <= 0) {
            System.out.println("Số phòng không hợp lệ!\n");
            return;
        }

        movie.setRoomNumber(newRoom);
        movieManager.saveMoviesToFile();
        System.out.println("\n✓ Đã cập nhật số phòng thành công!\n");
    }

    private void editShowTimes(Movie movie) {
        System.out.print("\nNhập giờ chiếu mới (cách nhau bởi dấu phẩy, vd: 10:00,14:00,18:00): ");
        String timesInput = inputHandler.getStringInput();

        if (timesInput.isEmpty()) {
            System.out.println("Giờ chiếu không được để trống!\n");
            return;
        }

        String[] showTimesArray = timesInput.split(",");
        List<String> newShowTimes = new ArrayList<>();

        for (String time : showTimesArray) {
            newShowTimes.add(time.trim());
        }

        movieManager.updateShowTimes(movie.getId(), newShowTimes);
        System.out.println("\n✓ Đã cập nhật giờ chiếu thành công!\n");
    }

    private void editAllMovieInfo(Movie movie) {
        editMovieName(movie);
        editRoomNumber(movie);
        editShowTimes(movie);
    }

    private void searchMovie() {
        System.out.println("\n===== TÌM KIẾM PHIM =====");
        System.out.print("Nhập từ khóa tìm kiếm: ");
        String keyword = inputHandler.getStringInput();

        if (keyword.isEmpty()) {
            System.out.println("Vui lòng nhập từ khóa!\n");
            return;
        }

        movieManager.displaySearchResults(keyword);
    }

    private void removeMovie() {
        System.out.println("\n===== XÓA PHIM =====");
        movieManager.displayMovies();

        System.out.print("Nhập ID phim cần xóa: ");
        int movieId = inputHandler.getIntInput();

        Movie movie = movieManager.getMovieById(movieId);
        if (movie == null) {
            System.out.println("Không tìm thấy phim!\n");
            return;
        }

        System.out.println("Bạn có chắc muốn xóa phim: " + movie.getName() + "?");
        System.out.print("Xác nhận (y/n): ");

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
        movieManager.displayMovies();

        System.out.print("Nhập ID phim: ");
        int movieId = inputHandler.getIntInput();

        Movie movie = movieManager.getMovieById(movieId);
        if (movie == null) {
            System.out.println("Không tìm thấy phim!\n");
            return;
        }

        System.out.println("\nCác suất chiếu:");
        for (int i = 0; i < movie.getShowTimes().size(); i++) {
            System.out.println((i + 1) + ". " + movie.getShowTimes().get(i));
        }

        System.out.print("Chọn suất chiếu cần reset: ");
        int choice = inputHandler.getIntInput();

        if (choice < 1 || choice > movie.getShowTimes().size()) {
            System.out.println("Suất chiếu không hợp lệ!\n");
            return;
        }

        String showTime = movie.getShowTimes().get(choice - 1);
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
