package todo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import todo.dto.TodoRequest;
import todo.dto.TodoResponse;
import todo.model.Priority;
import todo.repository.InMemoryTodoRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TodoServiceTest {

    private TodoService service;

    @BeforeEach
    void setUp() {
        service = new TodoServiceImpl(new InMemoryTodoRepository());
    }

    @Test
    void testAddAndGetAllTodos() {
        TodoRequest req = new TodoRequest();
        req.title = "Task";
        req.description = "Desc";
        req.priority = Priority.HIGH;
        req.dueDate = LocalDateTime.now().plusDays(1);

        service.addTodo(req);
        List<TodoResponse> todos = service.getAllTodos();
        assertEquals(1, todos.size());
        assertEquals("Task", todos.get(0).title);
    }

    @Test
    void testMarkComplete() {
        TodoRequest req = new TodoRequest();
        req.title = "Complete";
        req.description = "Desc";
        req.priority = Priority.LOW;
        req.dueDate = LocalDateTime.now().plusDays(1);

        service.addTodo(req);
        int id = service.getAllTodos().get(0).id;
        service.markComplete(id);

        TodoResponse todo = service.getAllTodos().get(0);
        assertTrue(todo.completed);
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
