package caseStudyModule2.services;

import caseStudyModule2.models.Movie;

import java.util.ArrayList;
import java.util.Arrays;
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
        addMovie("Zootopia 2", 1, "10:00", "13:30", "16:00", "18:05", "20:30", "23:00");
        addMovie("Hoàng tử quỷ", 2, "09:00", "11:45", "18:15");
        addMovie("Vua Của Các Vua", 3, "08:30", "14:00", "20:00");
        addMovie("Truy Tìm Long Diên Hương", 4, "12:00", "15:30", "19:00");
        addMovie("Năm Đêm Kinh Hoàng", 5, "17:00", "21:30", "23:59");
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

    public void updateMovieName(int id, String newName) {
        Movie movie = getMovieById(id);
        if (movie != null) {
            movie.setName(newName);
            saveMoviesToFile();
        }
    }

    public void updateShowTimes(int id, List<String> newShowTimes) {
        Movie movie = getMovieById(id);
        if (movie != null) {
            movie.setShowTimes(newShowTimes);
            saveMoviesToFile();
        }
    }

    public ArrayList<Movie> searchMovies(String keyword) {
        ArrayList<Movie> results = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();

        for (Movie movie : movies) {
            if (movie.getName().toLowerCase().contains(lowerKeyword)) {
                results.add(movie);
            }
        }
        return results;
    }

    public void displaySearchResults(String keyword) {
        ArrayList<Movie> results = searchMovies(keyword);

        if (results.isEmpty()) {
            System.out.println("\n✗ Không tìm thấy phim nào với từ khóa: \"" + keyword + "\"");
        } else {
            System.out.println("\n===== KẾT QUẢ TÌM KIẾM: \"" + keyword + "\" =====");
            System.out.println("Tìm thấy " + results.size() + " phim:");
            for (Movie movie : results) {
                System.out.println(movie);
            }
        }
        System.out.println();
    }

    public ArrayList<Movie> getAllMovies() {
        return movies;
    }

    public Movie getMovieById(int id) {
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    public void displayMovies() {
        System.out.println("\n===== DANH SÁCH PHIM =====");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
        System.out.println();
    }

}
