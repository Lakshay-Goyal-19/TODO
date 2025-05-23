package todo.util;

import org.junit.jupiter.api.Test;

class ConsoleUtilsTest {

    @Test
    void testPrintLineAndError() {
        // These just ensure no exceptions are thrown
        ConsoleUtils.printLine("Test message");
        ConsoleUtils.printError("Test error");
    }
}
