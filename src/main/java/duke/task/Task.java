package duke.task;

import java.time.format.DateTimeFormatter;

public class Task {
    static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    private String description;
    private boolean isDone;

    /**
     * Creates a Task object.
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Creates a formatted string of the Task data for storage.
     *
     * @return A string of the formatted data.
     */
    public String storageFormat() {
        return String.format("%s | %s", this.isDone ? "1" : "0", this.description);
    }

    /**
     * Creates a formatted string of the Task data for display.
     *
     * @return A string of the formatted data.
     */
    public String displayFormat() {
        return String.format("[%s] %s", this.isDone ? "+" : " ", this.description);
    }
}
