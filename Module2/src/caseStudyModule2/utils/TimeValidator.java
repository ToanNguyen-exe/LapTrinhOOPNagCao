package caseStudyModule2.utils;

import java.util.regex.Pattern;

public class TimeValidator {
    private static final Pattern TIME_PATTERN = Pattern.compile("^([01]?[0-9]|2[0-3]):[0-5][0-9]$");

    public static boolean isValidTime(String time) {
        if (time == null || time.trim().isEmpty()) {
            return false;
        }
        return TIME_PATTERN.matcher(time.trim()).matches();
    }


    public static String formatTime(String time) {
        time = time.trim();

        if (!time.contains(":")) {
            return null;
        }

        String[] parts = time.split(":");
        if (parts.length != 2) {
            return null;
        }

        try {
            int hour = Integer.parseInt(parts[0]);
            int minute = Integer.parseInt(parts[1]);

            if (hour < 0 || hour > 23 || minute < 0 || minute > 59) {
                return null;
            }

            return String.format("%02d:%02d", hour, minute);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static boolean validateTimes(String[] times) {
        if (times == null || times.length == 0) {
            return false;
        }

        for (String time : times) {
            if (!isValidTime(time)) {
                return false;
            }
        }
        return true;
    }
}
