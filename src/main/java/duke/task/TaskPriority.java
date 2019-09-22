package duke.task;

import duke.exception.UnknownTaskPriorityException;

public enum TaskPriority {
    HIGH("H", "High"),
    MEDIUM("M", "Medium"),
    LOW("L", "Low");

    private final String code;
    private final String name;

    TaskPriority(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String displayFormat() {
        return this.name;
    }

    public String storageFormat() {
        return this.code;
    }

    public static TaskPriority parse(String code) throws UnknownTaskPriorityException {
        for (TaskPriority p : values()) {
            if (p.code.equals(code)) {
                return p;
            }
        }

        throw new UnknownTaskPriorityException("Invalid task priority.");
    }
}
