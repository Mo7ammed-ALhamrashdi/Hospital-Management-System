package utils;

public class HelperUtils {
    public static boolean isEmpty(String text) {
        return text == null || text.isEmpty();
    }

    public static boolean isEmpty(Object[] collection) {
        return collection == null || collection.length == 0;
    }

    public static boolean isValidText(String text) {
        return text != null && !text.isEmpty();
    }

    public static boolean isValidText(String text, int minLength) {
        return text != null && text.length() >= minLength;
    }

    public static boolean isValidText(String text, int minLength, int maxLength) {
        return text != null
                && text.length() >= minLength
                && text.length() <= maxLength;
    }
    private static int counter = 1;

    public static String generateId() {
        return String.valueOf(counter++);
    }

    public static String generateId(String prefix) {
        return prefix + counter++;
    }

    public static boolean isPositive(int number) {
        return number > 0;
    }

    public static boolean isPositive(double number) {
        return number > 0;
    }

    public static boolean isInRange(int number, int min, int max) {
        return number >= min && number <= max;
    }

    public static boolean isInRange(double number, double min, double max) {
        return number >= min && number <= max;
    }
}

