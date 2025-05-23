package todo.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TodoNotFoundExceptionTest {

    @Test
    void testExceptionMessage() {
        TodoNotFoundException ex = new TodoNotFoundException("Not found");
        assertEquals("Not found", ex.getMessage());
    }
}
