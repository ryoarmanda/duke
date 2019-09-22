package duke.task;

import duke.exception.UnknownTaskTypeException;

public enum TaskType {
    TODO("T"),
    EVENT("E"),
    DEADLINE("D");

    private final String code;

    TaskType(String code) {
        this.code = code;
    }

    /**
     * Parses given code to its corresponding task type.
     *
     * @param code The task type code.
     * @return The task type, if found.
     * @throws UnknownTaskTypeException if no matching task type found.
     */
    public static TaskType parse(String code) throws UnknownTaskTypeException {
        for (TaskType t : values()) {
            if (t.code.equals(code)) {
                return t;
            }
        }

        throw new UnknownTaskTypeException("Invalid task type.");
    }
}
