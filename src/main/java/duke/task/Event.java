package duke.task;

import java.util.Objects;

import duke.utility.DateTime;

public class Event extends Task {
    private DateTime startTime;
    private DateTime endTime;

    /**
     * Creates an Event object.
     *
     * @param description The description of the task.
     * @param startTime The starting date and time.
     * @param endTime The ending date and time.
     */
    public Event(String description, DateTime startTime, DateTime endTime) {
        super(description);
        this.startTime = Objects.requireNonNull(startTime);
        this.endTime = Objects.requireNonNull(endTime);
    }

    /**
     * Creates a formatted string of the Event data for storage.
     *
     * @return A string of the formatted data.
     */
    public String storageFormat() {
        return String.format("E | %s | %s | %s",
                super.storageFormat(),
                this.startTime.format(),
                this.endTime.format()
        );
    }

    /**
     * Creates a formatted string of the Event data for display.
     *
     * @return A string of the formatted data.
     */
    public String displayFormat() {
        return "[E]" + String.format("%s (at: %s - %s)",
                super.displayFormat(),
                this.startTime.format(),
                this.endTime.format()
        );
    }
}
