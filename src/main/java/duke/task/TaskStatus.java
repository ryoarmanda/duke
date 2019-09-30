package duke.task;

import duke.exception.UnknownTaskStatusException;

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

    /**
     * Parses given code to its corresponding task status.
     *
     * @param code The task status code.
     * @return The task status, if found.
     * @throws UnknownTaskStatusException if no matching task status found.
     */
    public static TaskStatus parse(String code) throws UnknownTaskStatusException {
        for (TaskStatus s : values()) {
            if (s.code.equals(code)) {
                return s;
            }
        }

        throw new UnknownTaskStatusException("Invalid task status.");
    }
}
