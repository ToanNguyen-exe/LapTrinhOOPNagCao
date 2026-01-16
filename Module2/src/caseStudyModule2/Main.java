package caseStudyModule2;

import caseStudyModule2.system.BookingSystem;
import caseStudyModule2.services.MovieManager;

public class Main {
    static void main() {
        MovieManager movieManager = new MovieManager();
        BookingSystem bookingSystem = new BookingSystem(movieManager);

        bookingSystem.run();
    }
}
