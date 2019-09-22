package duke.exception;

public class UnknownTaskStatusException extends DukeParseException {
    public UnknownTaskStatusException(String message) {
        super(message);
    }
}
