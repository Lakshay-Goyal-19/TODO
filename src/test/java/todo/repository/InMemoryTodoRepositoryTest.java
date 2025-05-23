package todo.repository;

import org.junit.jupiter.api.Test;
import todo.model.Priority;
import todo.model.Todo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTodoRepositoryTest {

    @Test
    void testSaveAndFindById() {
        InMemoryTodoRepository repo = new InMemoryTodoRepository();
        Todo todo = new Todo("Test", "Desc", Priority.LOW, LocalDateTime.now().plusDays(1));
        repo.save(todo);

        Optional<Todo> found = repo.findById(todo.getId());
        assertTrue(found.isPresent());
        assertEquals("Test", found.get().getTitle());
    }

    @Test
    void testFindAll() {
        InMemoryTodoRepository repo = new InMemoryTodoRepository();
        repo.save(new Todo("A", "A", Priority.LOW, LocalDateTime.now().plusDays(1)));
        repo.save(new Todo("B", "B", Priority.HIGH, LocalDateTime.now().plusDays(2)));

        List<Todo> all = repo.findAll();
        assertEquals(2, all.size());
    }

    @Test
    void testDelete() {
        InMemoryTodoRepository repo = new InMemoryTodoRepository();
        Todo todo = new Todo("Del", "Del", Priority.MEDIUM, LocalDateTime.now().plusDays(1));
        repo.save(todo);
        repo.delete(todo.getId());

        assertFalse(repo.findById(todo.getId()).isPresent());
    }
}
