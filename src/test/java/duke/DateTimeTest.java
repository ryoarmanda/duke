package duke;

import duke.exception.DukeParseException;
import duke.utility.DateTime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateTimeTest {
    @Test
    public void parse_notADate_exceptionThrown() {
        assertThrows(DukeParseException.class, () -> DateTime.parse("not_a_date"));
    }

    @Test
    public void parse_notInFormat_exceptionThrown() {
        assertThrows(DukeParseException.class, () -> DateTime.parse("10-09-2017 01:23:45"));
    }
}
