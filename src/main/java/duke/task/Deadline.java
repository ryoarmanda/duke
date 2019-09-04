package duke.task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime time;

    /**
     * Creates a Deadline object.
     *
     * @param description The task description.
     * @param time The string of the deadline time.
     */
    public Deadline(String description, String time) {
        super(description);
        this.time = LocalDateTime.parse(time, Task.DATETIME_FORMAT);
    }

    public String getFormattedTime() {
        return this.time.format(Task.DATETIME_FORMAT);
    }

    /**
     * Creates a formatted string of the Deadline data for storage.
     *
     * @return A string of the formatted data.
     */
    public String storageFormat() {
        return String.format("D | %s | %s", super.storageFormat(), getFormattedTime());
    }

    /**
     * Creates a formatted string of the Deadline data for display.
     *
     * @return A string of the formatted data.
     */
    public String displayFormat() {
        return "[D]" + String.format("%s (by: %s)", super.displayFormat(), getFormattedTime());
    }
}
