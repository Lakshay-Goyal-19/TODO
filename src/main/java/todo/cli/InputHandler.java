package todo.cli;

import todo.model.Priority;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import todo.util.DateUtils;

public class InputHandler {

    public static boolean isValidPriority(String input) {
        for (Priority p : Priority.values()) {
            if (p.name().equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }

    public static Priority parsePriority(String input) {
        return Priority.valueOf(input.toUpperCase());
    }

    public static boolean isValidDateTime(String dateTimeStr) {
        try {
            DateUtils.parseDateTime(dateTimeStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValidText(String text, int maxLength) {
        return text != null && !text.trim().isEmpty() && text.length() <= maxLength;
    }
}
