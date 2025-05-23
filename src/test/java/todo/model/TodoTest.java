package todo.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void testTodoGettersAndSetters() {
        LocalDateTime due = LocalDateTime.now().plusDays(1);
        Todo todo = new Todo("Title", "Desc", Priority.HIGH, due);

        assertEquals("Title", todo.getTitle());
        assertEquals("Desc", todo.getDescription());
        assertEquals(Priority.HIGH, todo.getPriority());
        assertEquals(due, todo.getDueDate());
        assertFalse(todo.isCompleted());

        todo.setTitle("New Title");
        todo.setDescription("New Desc");
        todo.setPriority(Priority.LOW);
        todo.setDueDate(due.plusDays(1));
        todo.setCompleted(true);

        assertEquals("New Title", todo.getTitle());
        assertEquals("New Desc", todo.getDescription());
        assertEquals(Priority.LOW, todo.getPriority());
        assertEquals(due.plusDays(1), todo.getDueDate());
        assertTrue(todo.isCompleted());
    }
}
