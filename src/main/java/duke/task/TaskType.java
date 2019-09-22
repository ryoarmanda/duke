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

    public static TaskType parse(String code) throws UnknownTaskTypeException {
        for (TaskType t : values()) {
            if (t.code.equals(code)) {
                return t;
            }
        }

        throw new UnknownTaskTypeException("Invalid task type.");
    }
}
