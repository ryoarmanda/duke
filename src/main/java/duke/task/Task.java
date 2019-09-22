package duke.task;

import java.util.Objects;

public class Task {
    private String description;
    private TaskStatus status;
    private TaskPriority priority;

    /**
     * Creates a Task object.
     *
     * @param description The description of the task.
     * @param priority The priority of the task.
     */
    public Task(String description, TaskPriority priority) {
        this.description = description;
        this.status = TaskStatus.ONGOING;
        this.priority = Objects.requireNonNull(priority);
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.status = TaskStatus.DONE;
    }

    /**
     * Creates a formatted string of the Task data for storage.
     *
     * @return A string of the formatted data.
     */
    public String storageFormat() {
        return String.format("%s | %s | %s",
                this.priority.storageFormat(),
                this.status.storageFormat(),
                this.description
        );
    }

    /**
     * Creates a formatted string of the Task data for display.
     *
     * @return A string of the formatted data.
     */
    public String displayFormat() {
        return String.format("[%s][%s] %s",
                this.priority.displayFormat(),
                this.status.displayFormat(),
                this.description
        );
    }
}
