package duke.utility;

import duke.exception.DukeParseException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTime {
    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    private LocalDateTime dateTime;

    private DateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    private LocalDateTime getDateTime() {
        return this.dateTime;
    }

    /**
     * Parses the given date and time to a DateTime object.
     *
     * @param dateTime The date to be parsed, as a string.
     * @return A DateTime object containing the parsed date and time.
     * @throws DukeParseException if parsing failed.
     */
    public static DateTime parse(String dateTime) throws DukeParseException {
        try {
            return new DateTime(LocalDateTime.parse(dateTime, DATETIME_FORMAT));
        } catch (DateTimeParseException e) {
            throw new DukeParseException("Invalid date.");
        }
    }

    public String format() {
        return this.dateTime.format(DATETIME_FORMAT);
    }

    public int compareTo(DateTime dt) {
        return this.dateTime.compareTo(dt.getDateTime());
    }
}
