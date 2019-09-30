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

    /**
     * Parses given code to its corresponding task priority.
     *
     * @param code The task priority code.
     * @return The task priority, if found.
     * @throws UnknownTaskPriorityException if no matching task priority found.
     */
    public static TaskPriority parseByCode(String code) throws UnknownTaskPriorityException {
        for (TaskPriority p : values()) {
            if (p.code.equals(code)) {
                return p;
            }
        }

        throw new UnknownTaskPriorityException("Invalid task priority code.");
    }

    /**
     * Parses given name to its corresponding task priority.
     *
     * @param name The task priority name.
     * @return The task priority, if found.
     * @throws UnknownTaskPriorityException if no matching task priority found.
     */
    public static TaskPriority parseByName(String name) throws UnknownTaskPriorityException {
        for (TaskPriority p : values()) {
            if (p.name.equalsIgnoreCase(name)) {
                return p;
            }
        }

        throw new UnknownTaskPriorityException("Invalid task priority name.");
    }
}
