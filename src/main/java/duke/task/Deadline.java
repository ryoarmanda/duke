package duke.task;

import java.util.Objects;

import duke.utility.DateTime;

public class Deadline extends Task {
    private DateTime time;

    /**
     * Creates a Deadline object.
     *
     * @param description The description of the task.
     * @param time The date and time of the task.
     */
    public Deadline(String description, DateTime time) {
        super(description);
        this.time = Objects.requireNonNull(time);
    }

    /**
     * Creates a formatted string of the Deadline data for storage.
     *
     * @return A string of the formatted data.
     */
    public String storageFormat() {
        return String.format("D | %s | %s",
                super.storageFormat(),
                this.time.format()
        );
    }

    /**
     * Creates a formatted string of the Deadline data for display.
     *
     * @return A string of the formatted data.
     */
    public String displayFormat() {
        return "[D]" + String.format("%s (by: %s)",
                super.displayFormat(),
                this.time.format()
        );
    }
}
