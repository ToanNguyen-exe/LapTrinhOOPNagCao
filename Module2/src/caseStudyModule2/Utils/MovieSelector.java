package caseStudyModule2.Utils;


import caseStudyModule2.models.Movies;
import caseStudyModule2.services.MovieManager;

import java.util.List;

public class MovieSelector {
    private MovieManager movieManager;
    private InputHandler inputHandler;

    public MovieSelector(MovieManager movieManager, InputHandler inputHandler) {
        this.movieManager = movieManager;
        this.inputHandler = inputHandler;
    }

    public Movies selectMovie() {
        movieManager.displayMovies();
        System.out.print("Chọn phim: ");

        int movieId = inputHandler.getIntInput();
        Movies selectedMovie = movieManager.getMovieById(movieId);

        if (selectedMovie == null) {
            System.out.println("Không tìm thấy phim. Vui lòng chọn lại.\n");
            return null;
        }

        System.out.println("Phim đã chọn: " + selectedMovie.getName());
        return selectedMovie;
    }

    public String selectShowTime(Movies movie) {
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
