package caseStudyModule2;

import caseStudyModule2.controllers.BookingSystem;
import caseStudyModule2.services.MovieManager;

public class Main {
    public static void main(String[] args) {
        MovieManager movieManager = new MovieManager();
        BookingSystem bookingSystem = new BookingSystem(movieManager);

        bookingSystem.run();
    }
}
