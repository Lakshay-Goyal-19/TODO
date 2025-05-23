package todo.cli;

import org.junit.jupiter.api.Test;
import todo.model.Priority;

import static org.junit.jupiter.api.Assertions.*;

class InputHandlerTest {

    @Test
    void testIsValidPriority() {
        assertTrue(InputHandler.isValidPriority("LOW"));
        assertTrue(InputHandler.isValidPriority("medium"));
        assertFalse(InputHandler.isValidPriority("urgent"));
    }

    @Test
    void testParsePriority() {
        assertEquals(Priority.HIGH, InputHandler.parsePriority("HIGH"));
        assertEquals(Priority.LOW, InputHandler.parsePriority("low"));
    }

    @Test
    void testIsValidDateTime() {
        assertTrue(InputHandler.isValidDateTime("2025-01-01 12:00"));
        assertFalse(InputHandler.isValidDateTime("bad-date"));
    }

    @Test
    void testIsValidText() {
        assertTrue(InputHandler.isValidText("Hello", 10));
        assertFalse(InputHandler.isValidText("", 10));
        assertFalse(InputHandler.isValidText(null, 10));
        assertFalse(InputHandler.isValidText("A".repeat(101), 100));
    }
}
