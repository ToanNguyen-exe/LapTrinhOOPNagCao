package caseStudyModule2.utils;


import caseStudyModule2.models.Movie;
import caseStudyModule2.services.MovieManager;

import java.util.ArrayList;
import java.util.List;

public class MovieSelector {
    private final MovieManager movieManager;
    private final InputHandler inputHandler;

    public MovieSelector(MovieManager movieManager, InputHandler inputHandler) {
        this.movieManager = movieManager;
        this.inputHandler = inputHandler;
    }


    public Movie selectMovie() {
        displayMoviesForCustomer();
        System.out.print("Chọn phim (nhập số thứ tự): ");

        int choice = inputHandler.getIntInput();
        Movie selectedMovie = movieManager.getMovieByDisplayIndex(choice);

        if (selectedMovie == null) {
            System.out.println("Không tìm thấy phim. Vui lòng chọn lại.\n");
            return null;
        }

        System.out.println("Phim đã chọn: " + selectedMovie.getName());
        return selectedMovie;
    }

    private void displayMoviesForCustomer() {
        System.out.println("\n===== DANH SÁCH PHIM =====");
        ArrayList<Movie> sortedMovies = movieManager.getMoviesSortedByRoom();

        for (int i = 0; i < sortedMovies.size(); i++) {
            Movie movie = sortedMovies.get(i);
            System.out.println((i + 1) + ". Phòng " + movie.getRoomNumber() + " - " + movie.getName());
            System.out.println("   Suất chiếu: " + String.join(", ", movie.getShowTimes()));
        }
        System.out.println();
    }

    public String selectShowTime(Movie movie) {
        List<String> showTimes = movie.getShowTimes();

        displayShowTimes(showTimes);
        System.out.print("Chọn suất chiếu (nhập số): ");

        int choice = inputHandler.getIntInput();

        if (choice < 1 || choice > showTimes.size()) {
            System.out.println("Suất chiếu không tồn tại.\n");
            return null;
        }

        String selectedTime = showTimes.get(choice - 1);
        System.out.println("\nBạn đã chọn suất chiếu: " + selectedTime);
        return selectedTime;
    }

    private void displayShowTimes(List<String> showTimes) {
        System.out.println("\n===== CÁC SUẤT CHIẾU =====");
        for (int i = 0; i < showTimes.size(); i++) {
            System.out.println((i + 1) + ". " + showTimes.get(i));
        }
    }
}
