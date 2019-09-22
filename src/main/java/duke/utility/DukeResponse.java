package duke.utility;

public class DukeResponse {
    private String message;
    private boolean isExit;

    public DukeResponse(String message, boolean isExit) {
        this.message = message;
        this.isExit = isExit;
    }

    public String getMessage() {
        return this.message;
    }

    public boolean isExit() {
        return this.isExit;
    }
}
