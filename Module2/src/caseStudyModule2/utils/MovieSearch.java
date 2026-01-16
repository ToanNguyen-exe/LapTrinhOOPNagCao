package caseStudyModule2.utils;
import caseStudyModule2.models.Movie;
import caseStudyModule2.services.MovieManager;

import java.util.ArrayList;

public class MovieSearch {
    private MovieManager movieManager;

    public MovieSearch(MovieManager movieManager) {
        this.movieManager = movieManager;
    }

    public ArrayList<Movie> searchMovies(String keyword) {
        ArrayList<Movie> results = new ArrayList<>();

        if (keyword == null || keyword.trim().isEmpty()) {
            return results;
        }

        String normalizedKeyword = normalizeVietnamese(keyword.toLowerCase().trim());

        for (Movie movie : movieManager.getAllMovies()) {
            String normalizedMovieName = normalizeVietnamese(movie.getName().toLowerCase());

            if (isSimilar(normalizedMovieName, normalizedKeyword)) {
                results.add(movie);
            }
        }

        return results;
    }

    public void displaySearchResults(ArrayList<Movie> results, String keyword) {
        if (results.isEmpty()) {
            System.out.println("\n❌ Không tìm thấy phim nào với từ khóa: \"" + keyword + "\"");
            System.out.println("Gợi ý: Thử tìm với từ khóa khác hoặc xem danh sách phim đầy đủ.\n");
            return;
        }

        System.out.println("\n🔍 Tìm thấy " + results.size() + " phim với từ khóa: \"" + keyword + "\"");
        System.out.println("═════════════════════════════════════════════");

        for (int i = 0; i < results.size(); i++) {
            Movie movie = results.get(i);
            System.out.println((i + 1) + ". " + movie.getName());
            System.out.println("   Phòng: " + movie.getRoomNumber());
            System.out.println("   Suất chiếu: " + String.join(", ", movie.getShowTimes()));
            System.out.println("   ─────────────────────────");
        }
        System.out.println();
    }

    private boolean isSimilar(String text, String keyword) {
        if (text.contains(keyword)) {
            return true;
        }

        String[] keywordWords = keyword.split("\\s+");
        int matchCount = 0;

        for (String word : keywordWords) {
            if (word.length() >= 2 && text.contains(word)) {
                matchCount++;
            }
        }

        if (keywordWords.length > 0 && matchCount >= Math.ceil(keywordWords.length * 0.5)) {
            return true;
        }

        return calculateSimilarity(text, keyword) > 0.6;
    }

    private double calculateSimilarity(String s1, String s2) {
        int distance = levenshteinDistance(s1, s2);
        int maxLength = Math.max(s1.length(), s2.length());

        if (maxLength == 0) return 1.0;

        return 1.0 - ((double) distance / maxLength);
    }

    private int levenshteinDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= s2.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(
                        Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                        dp[i - 1][j - 1] + cost
                );
            }
        }

        return dp[s1.length()][s2.length()];
    }

    private String normalizeVietnamese(String str) {
        if (str == null) return "";

        str = str.replaceAll("[àáạảãâầấậẩẫăằắặẳẵ]", "a");
        str = str.replaceAll("[èéẹẻẽêềếệểễ]", "e");
        str = str.replaceAll("[ìíịỉĩ]", "i");
        str = str.replaceAll("[òóọỏõôồốộổỗơờớợởỡ]", "o");
        str = str.replaceAll("[ùúụủũưừứựửữ]", "u");
        str = str.replaceAll("[ỳýỵỷỹ]", "y");
        str = str.replaceAll("đ", "d");

        return str;
    }
}

