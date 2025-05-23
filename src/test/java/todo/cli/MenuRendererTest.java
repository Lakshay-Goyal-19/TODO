package todo.cli;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import todo.util.ConsoleUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuRendererTest {

    @Test
    void testPrintMessage() {
        MenuRenderer renderer = new MenuRenderer();
        assertDoesNotThrow(() -> renderer.printMessage("Hello"));
    }

    @Test
    void testGetStringInput() {
        try (MockedStatic<ConsoleUtils> mocked = mockStatic(ConsoleUtils.class)) {
            mocked.when(() -> ConsoleUtils.readLine("Prompt")).thenReturn("TestInput");
            MenuRenderer renderer = new MenuRenderer();
            String result = renderer.getStringInput("Prompt");
            assertEquals("TestInput", result);
        }
    }

    @Test
    void testDisplayMainMenuValidInput() {
        try (MockedStatic<ConsoleUtils> mocked = mockStatic(ConsoleUtils.class)) {
            mocked.when(() -> ConsoleUtils.readLine(anyString())).thenReturn("2");
            MenuRenderer renderer = new MenuRenderer();
            int choice = renderer.displayMainMenu();
            assertEquals(2, choice);
        }
    }

    @Test
    void testDisplayMainMenuInvalidInput() {
        try (MockedStatic<ConsoleUtils> mocked = mockStatic(ConsoleUtils.class)) {
            mocked.when(() -> ConsoleUtils.readLine(anyString())).thenReturn("bad");
            MenuRenderer renderer = new MenuRenderer();
            int choice = renderer.displayMainMenu();
            assertEquals(-1, choice);
        }
    }
}
