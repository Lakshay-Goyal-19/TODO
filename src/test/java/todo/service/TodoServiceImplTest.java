package todo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import todo.dto.TodoRequest;
import todo.model.Priority;
import todo.repository.InMemoryTodoRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TodoServiceImplTest {

    private TodoServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new TodoServiceImpl(new InMemoryTodoRepository());
    }

    @Test
    void testAddTodoAndGetAllTodos() {
        TodoRequest req = new TodoRequest();
        req.title = "Test";
        req.description = "Desc";
        req.priority = Priority.LOW;
        req.dueDate = LocalDateTime.now().plusDays(1);

        service.addTodo(req);
        assertEquals(1, service.getAllTodos().size());
    }

    @Test
    void testMarkComplete() {
        TodoRequest req = new TodoRequest();
        req.title = "Complete";
        req.description = "Desc";
        req.priority = Priority.HIGH;
        req.dueDate = LocalDateTime.now().plusDays(1);

        service.addTodo(req);
        int id = service.getAllTodos().get(0).id;
        service.markComplete(id);
        assertTrue(service.getAllTodos().get(0).completed);
    }

    @Test
    void testDeleteTodo() {
        TodoRequest req = new TodoRequest();
        req.title = "Delete";
        req.description = "Desc";
        req.priority = Priority.MEDIUM;
        req.dueDate = LocalDateTime.now().plusDays(1);

        service.addTodo(req);
        int id = service.getAllTodos().get(0).id;
        service.deleteTodo(id);
        assertTrue(service.getAllTodos().isEmpty());
    }
}
