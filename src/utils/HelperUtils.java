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
}

