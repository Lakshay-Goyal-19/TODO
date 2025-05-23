package todo.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilTest {

    @Test
    void testParseDateTimeValid() {
        String input = "2025-12-31 23:59";
        LocalDateTime dt = DateUtils.parseDateTime(input);
        assertEquals(2025, dt.getYear());
        assertEquals(12, dt.getMonthValue());
        assertEquals(31, dt.getDayOfMonth());
        assertEquals(23, dt.getHour());
        assertEquals(59, dt.getMinute());
    }

    @Test
    void testParseDateTimeInvalid() {
        String input = "invalid-date";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            DateUtils.parseDateTime(input);
        });
        assertTrue(exception.getMessage().contains("Invalid date/time format"));
    }
}
