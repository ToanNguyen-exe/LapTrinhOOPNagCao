package cleanCodeAndRefactoring.BTTennisGame;

public class TennisGame {

    private static final String[] SCORE_NAMES = {
            "Love", "Fifteen", "Thirty", "Forty"
    };

    public static String getScore(String player1Name, String player2Name, int p1Score, int p2Score) {

        if (isTie(p1Score, p2Score)) {
            return getTieScore(p1Score);
        }

        if (isNormalScore(p1Score, p2Score)) {
            return getNormalScore(p1Score, p2Score);
        }

        return getAdvantageOrWinScore(p1Score, p2Score);
    }

    private static boolean isTie(int p1, int p2) {
        return p1 == p2;
    }

    private static boolean isNormalScore(int p1, int p2) {
        return p1 < 4 && p2 < 4;
    }

    private static String getTieScore(int score) {
        if (score < 3) {
            return SCORE_NAMES[score] + "-All";
        }
        return "Deuce";
    }

    private static String getNormalScore(int p1, int p2) {
        return SCORE_NAMES[p1] + "-" + SCORE_NAMES[p2];
    }

    private static String getAdvantageOrWinScore(int p1, int p2) {
        int diff = p1 - p2;

        if (diff == 1) return "Advantage " + "player1";
        if (diff == -1) return "Advantage " + "player2";
        if (diff >= 2) return "Win for " + "player1";
        return "Win for " + "player2";
    }
}
