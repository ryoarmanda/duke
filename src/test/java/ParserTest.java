import duke.DukeException;
import duke.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void parseCommand_noDescriptionInTodo_exceptionThrown() {
        String input = "todo";

        assertThrows(DukeException.class, () -> Parser.parseCommand(input));
    }

    @Test
    public void parseCommand_noDateInDeadline_exceptionThrown() {
        String input = "deadline test";

        assertThrows(DukeException.class, () -> Parser.parseCommand(input));
    }

    @Test
    public void parseCommand_noDatesInEvent_exceptionThrown() {
        String input = "event test";

        assertThrows(DukeException.class, () -> Parser.parseCommand(input));
    }

    @Test
    public void parseCommand_noEndDateInEvent_exceptionThrown() {
        String input = "event test /at 01/01/2019 0100";

        assertThrows(DukeException.class, () -> Parser.parseCommand(input));
    }

    @Test
    public void parseCommand_noIndexInDone_exceptionThrown() {
        String input = "done";

        assertThrows(DukeException.class, () -> Parser.parseCommand(input));
    }

    @Test
    public void parseCommand_noIndexInDelete_exceptionThrown() {
        String input = "delete";

        assertThrows(DukeException.class, () -> Parser.parseCommand(input));
    }

    @Test
    public void parseCommand_unknownCommand_exceptionThrown() {
        String input = "not_a_command";

        assertThrows(DukeException.class, () -> Parser.parseCommand(input));
    }
}
