package caseStudyModule2.services;

import caseStudyModule2.models.Movie;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class DataManager {
    private static final String MOVIES_FILE = "data_movies.csv";
    private static final String SEATS_DIR = "data_seats/";

    static {
        File dir = new File(SEATS_DIR);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    public static void saveMovies(ArrayList<Movie> movies) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MOVIES_FILE))) {
            writer.write("id,name,roomNumber,showTimes");
            writer.newLine();

            for (Movie movie : movies) {
                String showTimes = String.join("|", movie.getShowTimes());
                String line = String.format("%d,%s,%d,%s",
                        movie.getId(),
                        escapeCsv(movie.getName()),
                        movie.getRoomNumber(),
                        showTimes
                );
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("✗ Lỗi lưu dữ liệu phim: " + e.getMessage());
        }
    }

    public static ArrayList<Movie> loadMovies() {
        ArrayList<Movie> movies = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(MOVIES_FILE))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] parts = line.split(",", 4);
                if (parts.length >= 4) {
                    int id = Integer.parseInt(parts[0]);
                    String name = unescapeCsv(parts[1]);
                    int roomNumber = Integer.parseInt(parts[2]);
                    String[] showTimes = parts[3].split("\\|");

                    movies.add(new Movie(id, name, roomNumber, Arrays.asList(showTimes)));
                }
            }

            return movies.isEmpty() ? null : movies;

        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            System.out.println("✗ Lỗi đọc dữ liệu phim: " + e.getMessage());
            return null;
        }
    }

    public static void saveSeats(String roomKey, boolean[][] seats) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(SEATS_DIR + roomKey + ".csv"))) {

            writer.write("row,col");
            writer.newLine();

            for (int i = 0; i < seats.length; i++) {
                for (int j = 0; j < seats[i].length; j++) {
                    if (seats[i][j]) {
                        writer.write(i + "," + j);
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("✗ Lỗi lưu dữ liệu ghế: " + e.getMessage());
        }
    }

    public static boolean[][] loadSeats(String roomKey) {
        boolean[][] seats = new boolean[8][10];

        try (BufferedReader reader = new BufferedReader(
                new FileReader(SEATS_DIR + roomKey + ".csv"))) {

            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {

                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length == 2) {
                    int row = Integer.parseInt(parts[0]);
                    int col = Integer.parseInt(parts[1]);
                    seats[row][col] = true;
                }
            }

            return seats;

        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            System.out.println("✗ Lỗi đọc dữ liệu ghế: " + e.getMessage());
            return null;
        }
    }

    public static void deleteSeats(String roomKey) {
        File file = new File(SEATS_DIR + roomKey + ".csv");
        if (file.exists()) {
            file.delete();
        }
    }

    private static String escapeCsv(String value) {
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }

    private static String unescapeCsv(String value) {
        if (value.startsWith("\"") && value.endsWith("\"")) {
            return value.substring(1, value.length() - 1).replace("\"\"", "\"");
        }
        return value;
    }

}
