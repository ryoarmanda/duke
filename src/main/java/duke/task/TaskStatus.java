package duke.task;

import duke.exception.UnknownTaskStatusException;
import duke.exception.UnknownTaskTypeException;

public enum TaskStatus {
    DONE("1", "+"),
    ONGOING("0", "x");

    private final String code;
    private final String indicator;

    TaskStatus(String code, String indicator) {
        this.code = code;
        this.indicator = indicator;
    }

    public String displayFormat() {
        return this.indicator;
    }

    public String storageFormat() {
        return this.code;
    }

    public static TaskStatus parse(String code) throws UnknownTaskStatusException {
        for (TaskStatus s : values()) {
            if (s.code.equals(code)) {
                return s;
            }
        }

        throw new UnknownTaskStatusException("Invalid task status.");
    }
}
