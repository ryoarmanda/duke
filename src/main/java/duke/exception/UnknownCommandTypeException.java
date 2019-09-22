package duke.exception;

public class UnknownCommandTypeException extends DukeParseException {
    public UnknownCommandTypeException(String message) {
        super(message);
    }
}
