package caseStudyModule2.services;

import caseStudyModule2.models.Movie;

import java.io.*;
import java.util.ArrayList;

public class DataManager {
    private static final String MOVIES_FILE = "data_movies.dat";
    private static final String SEATS_DIR = "data_seats/";

    static {
        File dir = new File(SEATS_DIR);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    public static void saveMovies(ArrayList<Movie> movies) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(MOVIES_FILE))) {
            oos.writeObject(movies);
        } catch (IOException e) {
            System.out.println("✗ Lỗi lưu dữ liệu phim: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Movie> loadMovies() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(MOVIES_FILE))) {
            return (ArrayList<Movie>) ois.readObject();
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("✗ Lỗi đọc dữ liệu phim: " + e.getMessage());
            return null;
        }
    }

    public static void deleteMoviesData() {
        File file = new File(MOVIES_FILE);
        if (file.exists()) {
            file.delete();
            System.out.println("✓ Đã xóa dữ liệu phim.");
        }
    }

    public static void saveSeats(String roomKey, boolean[][] seats) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(SEATS_DIR + roomKey + ".dat"))) {
            oos.writeObject(seats);
        } catch (IOException e) {
            System.out.println("✗ Lỗi lưu dữ liệu ghế: " + e.getMessage());
        }
    }

    public static boolean[][] loadSeats(String roomKey) {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(SEATS_DIR + roomKey + ".dat"))) {
            return (boolean[][]) ois.readObject();
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("✗ Lỗi đọc dữ liệu ghế: " + e.getMessage());
            return null;
        }
    }

    public static void deleteSeats(String roomKey) {
        File file = new File(SEATS_DIR + roomKey + ".dat");
        if (file.exists()) {
            file.delete();
        }
    }

    public static void deleteAllSeats() {
        File dir = new File(SEATS_DIR);
        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
            System.out.println("✓ Đã xóa tất cả dữ liệu ghế.");
        }
    }

    public static boolean seatFileExists(String roomKey) {
        File file = new File(SEATS_DIR + roomKey + ".dat");
        return file.exists();
    }
}
