package Cinema2.Admin;

import caseStudyModule2.Utils.InputHandler;
import caseStudyModule2.models.Movies;
import caseStudyModule2.models.Room;
import caseStudyModule2.services.FileManager;
import caseStudyModule2.services.MovieManager;

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



    private void viewAllMovies() {
        System.out.println("\n===== DANH SÁCH PHIM CHI TIẾT =====");
        for (Movies movie : movieManager.getAllMovies()) {
            System.out.println("\nID: " + movie.getId());
            System.out.println("Tên: " + movie.getName());
            System.out.println("Phòng: " + movie.getRoomNumber());
            System.out.println("Suất chiếu: " + String.join(", ", movie.getShowTimes()));
            System.out.println("─────────────────────");
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

    private void removeMovie() {
        System.out.println("\n===== XÓA PHIM =====");
        movieManager.displayMovies();

        System.out.print("Nhập ID phim cần xóa: ");
        int movieId = inputHandler.getIntInput();

        Movies movie = movieManager.getMovieById(movieId);
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

        for (Movies movie : movieManager.getAllMovies()) {
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


}
