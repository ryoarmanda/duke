package duke.task;

public class Task {
    private String description;
    private TaskStatus status;
    private TaskPriority priority;

    /**
     * Creates a Task object.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.status = TaskStatus.ONGOING;
        this.priority = TaskPriority.MEDIUM;
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.status = TaskStatus.DONE;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
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
