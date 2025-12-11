package caseStudyModule2.utils;


import caseStudyModule2.models.Movie;
import caseStudyModule2.services.MovieManager;

import java.util.List;

public class MovieSelector {
    private MovieManager movieManager;
    private InputHandler inputHandler;

    public MovieSelector(MovieManager movieManager, InputHandler inputHandler) {
        this.movieManager = movieManager;
        this.inputHandler = inputHandler;
    }

    public Movie selectMovie() {
        movieManager.displayMovies();
        System.out.print("Chọn phim: ");

        int movieId = inputHandler.getIntInput();
        Movie selectedMovie = movieManager.getMovieById(movieId);

        if (selectedMovie == null) {
            System.out.println("Không tìm thấy phim. Vui lòng chọn lại.\n");
            return null;
        }

        System.out.println("Phim đã chọn: " + selectedMovie.getName());
        return selectedMovie;
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
