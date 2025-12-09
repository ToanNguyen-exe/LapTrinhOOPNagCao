package caseStudyModule2;

import java.util.*;

public class Main {
    static ArrayList<Movies> movies = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        addMovies("Zootopia 2", 1, "10:00", "13:30", "16:00", "18:05", "20:30", "23:00");
        addMovies("Hoàng tử quỷ", 2, "09:00", "11:45", "18:15");
        addMovies("Vua Của Các Vua", 3, "08:30", "14:00", "20:00");
        addMovies("Truy Tìm Long Diên Hương", 4, "12:00", "15:30", "19:00");
        addMovies("Năm Đêm Kinh Hoàng", 5, "17:00", "21:30", "23:59");

        int choice = 0;
        do {
            System.out.println("===== MOVIE BOOKING SYSTEM =====");
            System.out.println("1. Xem danh sách phim");
            System.out.println("2. Đặt vé xem phim");
            System.out.println("3. Thoát");
            System.out.print("Chọn chức năng: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Vui lòng nhập số hợp lệ!\n");
                continue;
            }

            switch (choice) {
                case 1 -> showMovies();
                case 2 -> bookTickets();
                case 3 -> System.out.println("Cảm ơn bạn đã sử dụng hệ thống!");
                default -> System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
            }

        } while (choice != 3);
    }

    private static void addMovies(String name, int roomNumber, String... showTimes) {
        List<String> timesList = Arrays.asList(showTimes);
        movies.add(new Movies(movies.size() + 1, name, roomNumber, timesList));
    }

    private static void showMovies() {
        System.out.println("\n===== DANH SÁCH PHIM =====");
        for (Movies m : movies) {
            System.out.println(m.getId() + ". " + m.getName() + " (Phòng " + m.getRoomNumber() + ")");
        }
        System.out.println();
    }

    private static void bookTickets() {
        System.out.println("\n===== ĐẶT VÉ XEM PHIM =====");
        showMovies();

        System.out.print("Chọn phim: ");
        int movieIndex;
        try {
            movieIndex = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Vui lòng nhập ID phim hợp lệ.\n");
            return;
        }

        Movies selectedMovie = null;
        for (Movies m : movies) {
            if (m.getId() == movieIndex) {
                selectedMovie = m;
                break;
            }
        }

        if (selectedMovie == null) {
            System.out.println("Không tìm thấy phim. Vui lòng chọn lại.\n");
            return;
        }

        System.out.println("Phim đã chọn: " + selectedMovie.getName());

        List<String> times = selectedMovie.getShowTimes();

        System.out.println("\n===== CÁC SUẤT CHIẾU =====");
        for (int i = 0; i < times.size(); i++) {
            System.out.println((i + 1) + ". " + times.get(i));
        }

        System.out.print("Chọn suất chiếu (nhập số): ");
        int stchoice;

        try {
            stchoice = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Vui lòng nhập số suất chiếu hợp lệ.\n");
            return;
        }

        if (stchoice < 1 || stchoice > times.size()) {
            System.out.println("Suất chiếu không tồn tại.\n");
            return;
        }

        String selectedTime = times.get(stchoice - 1);
        System.out.println("\nBạn đã chọn suất chiếu: " + selectedTime);

        String roomKey = selectedMovie.getId() + "_" + selectedTime.replace(":", "");
        Room room = new Room(roomKey);

        ArrayList<String> selectedSeats = new ArrayList<>();
        ArrayList<Integer> seatPrices = new ArrayList<>();

        while (true) {
            System.out.println("\n===== CHỌN GHẾ =====");
            room.getSeatMap().show();

            if (!selectedSeats.isEmpty()) {
                System.out.println("\n📌 Ghế đã chọn: " + String.join(", ", selectedSeats));
                System.out.println("Tổng tạm tính: " + String.format("%,d", getTotalPrice(seatPrices)) + " VNĐ");
            }

            System.out.print("\nChọn ghế (vd: A5), 'xong' để thanh toán, hoặc '0' để quay lại: ");
            String input = sc.nextLine().toUpperCase().trim();

            if (input.equals("0")) {
                System.out.println("Đã hủy đặt vé.\n");
                return;
            }

            if (input.equals("XONG")) {
                if (selectedSeats.isEmpty()) {
                    System.out.println("Bạn chưa chọn ghế nào!\n");
                    continue;
                }
                break;
            }

            if (input.length() < 2 || input.charAt(0) < 'A' || input.charAt(0) > 'H') {
                System.out.println("Ghế không hợp lệ!\n");
                continue;
            }

            try {
                int row = input.charAt(0) - 'A';
                int col = Integer.parseInt(input.substring(1)) - 1;

                if (col < 0 || col >= 10) {
                    System.out.println("Ghế không hợp lệ!\n");
                    continue;
                }

                if (room.getSeatMap().isTaken(row, col)) {
                    System.out.println("Ghế đã được đặt. Vui lòng chọn ghế khác!\n");
                    continue;
                }

                room.getSeatMap().book(row, col);
                selectedSeats.add(input);

                int price = SeatPrice.getPrice(input);
                seatPrices.add(price);

                System.out.println("✓ Đã thêm ghế " + input + " (" + String.format("%,d", price) + " VNĐ)");

            } catch (NumberFormatException e) {
                System.out.println("Ghế không hợp lệ!\n");
            }
        }

        int totalPrice = getTotalPrice(seatPrices);

        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║     XÁC NHẬN THANH TOÁN            ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.println("Phim: " + selectedMovie.getName());
        System.out.println("Suất chiếu: " + selectedTime);
        System.out.println("Phòng: " + selectedMovie.getRoomNumber());
        System.out.println("Ghế: " + String.join(", ", selectedSeats));
        System.out.println("Số lượng vé: " + selectedSeats.size());
        System.out.println("Tổng tiền: " + String.format("%,d", totalPrice) + " VNĐ");
        System.out.println("────────────────────────────────────");

        System.out.print("Xác nhận thanh toán? (y/n): ");
        String confirm = sc.nextLine().toLowerCase();

        if (confirm.equals("y") || confirm.equals("yes")) {
            room.saveSeats();

            System.out.println("\n✓ Đặt vé thành công!\n");
            System.out.println("─────────────────────────────");
            System.out.println("Phim: " + selectedMovie.getName());
            System.out.println("Suất chiếu: " + selectedTime);
            System.out.println("Phòng: " + selectedMovie.getRoomNumber());
            System.out.println("Ghế: " + String.join(", ", selectedSeats));
            System.out.println("Giá vé: " + String.format("%,d", totalPrice) + " VNĐ");
            System.out.println("─────────────────────────────\n");
        } else {
            System.out.println("\n✗ Đã hủy đặt vé.\n");
        }
    }

    private static int getTotalPrice(ArrayList<Integer> prices) {
        int total = 0;
        for (int price : prices) {
            total += price;
        }
        return total;
    }
}
