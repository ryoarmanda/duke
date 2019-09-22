package duke.exception;

public class UnknownTaskTypeException extends DukeParseException {
    public UnknownTaskTypeException(String message) {
        super(message);
    }
}
