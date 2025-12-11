package caseStudyModule2.services;

import caseStudyModule2.models.Movies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieManager {
    private final ArrayList<Movies> movies;

    public MovieManager() {
        this.movies = new ArrayList<>();
        initializeMovies();
    }

    private void initializeMovies() {
        addMovie("Zootopia 2", 1, "10:00", "13:30", "16:00", "18:05", "20:30", "23:00");
        addMovie("Hoàng tử quỷ", 2, "09:00", "11:45", "18:15");
        addMovie("Vua Của Các Vua", 3, "08:30", "14:00", "20:00");
        addMovie("Truy Tìm Long Diên Hương", 4, "12:00", "15:30", "19:00");
        addMovie("Năm Đêm Kinh Hoàng", 5, "17:00", "21:30", "23:59");
    }

    public void addMovie(String name, int roomNumber, String... showTimes) {
        List<String> timesList = Arrays.asList(showTimes);
        movies.add(new Movies(movies.size() + 1, name, roomNumber, timesList));
    }

    public void removeMovie(int id) {
        movies.removeIf(movie -> movie.getId() == id);
    }

    public ArrayList<Movies> getAllMovies() {
        return movies;
    }

    public Movies getMovieById(int id) {
        for (Movies movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    public void displayMovies() {
        System.out.println("\n===== DANH SÁCH PHIM =====");
        for (Movies movie : movies) {
            System.out.println(movie);
        }
        System.out.println();
    }
}
