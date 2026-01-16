package caseStudyModule2.services;

import caseStudyModule2.models.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MovieManager {
    private ArrayList<Movie> movies;

    public MovieManager() {
        this.movies = new ArrayList<>();
        loadMoviesFromFile();
    }

    private void loadMoviesFromFile() {
        ArrayList<Movie> loadedMovies = DataManager.loadMovies();

        if (loadedMovies != null && !loadedMovies.isEmpty()) {
            this.movies = loadedMovies;
            System.out.println("✓ Đã tải " + movies.size() + " phim từ file.");
        } else {
            initializeDefaultMovies();
            saveMoviesToFile();
        }
    }

    private void initializeDefaultMovies() {

        System.out.println("✓ Đã khởi tạo dữ liệu phim mặc định.");
    }

    public void saveMoviesToFile() {
        DataManager.saveMovies(movies);
    }

    public void addMovie(String name, int roomNumber, String... showTimes) {
        List<String> timesList = Arrays.asList(showTimes);
        movies.add(new Movie(movies.size() + 1, name, roomNumber, timesList));
        saveMoviesToFile();
    }

    public void removeMovie(int id) {
        movies.removeIf(movie -> movie.getId() == id);
        saveMoviesToFile();
    }

    public ArrayList<Movie> getAllMovies() {
        return movies;
    }

    public ArrayList<Movie> getMoviesSortedByRoom() {
        ArrayList<Movie> sorted = new ArrayList<>(movies);
        sorted.sort(Comparator.comparingInt(Movie::getRoomNumber));
        return sorted;
    }

    public Movie getMovieById(int id) {
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    public Movie getMovieByDisplayIndex(int index) {
        ArrayList<Movie> sorted = getMoviesSortedByRoom();
        if (index >= 1 && index <= sorted.size()) {
            return sorted.get(index - 1);
        }
        return null;
    }

    public void displayMovies() {
        System.out.println("\n===== DANH SÁCH PHIM =====");

        ArrayList<Movie> sortedMovies = new ArrayList<>(movies);
        sortedMovies.sort(Comparator.comparingInt(Movie::getRoomNumber));

        for (Movie movie : sortedMovies) {
            System.out.println("Phòng " + movie.getRoomNumber() + " - " + movie.getName());
            System.out.println("  Suất chiếu: " + String.join(", ", movie.getShowTimes()));
        }
        System.out.println();
    }

    public void displayMoviesForAdmin() {
        System.out.println("\n===== DANH SÁCH PHIM (ADMIN) =====");

        for (Movie movie : movies) {
            System.out.println("\n[ID: " + movie.getId() + "] " + movie.getName());
            System.out.println("  Phòng: " + movie.getRoomNumber());
            System.out.println("  Suất chiếu: " + String.join(", ", movie.getShowTimes()));
            System.out.println("  ─────────────────────");
        }
        System.out.println();
    }
}


