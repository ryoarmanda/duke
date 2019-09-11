package duke.task;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Creates an Event object.
     *
     * @param description The task description.
     * @param startTime The string of start time.
     * @param endTime The string of end time.
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = LocalDateTime.parse(startTime, Task.DATETIME_FORMAT);
        this.endTime = LocalDateTime.parse(endTime, Task.DATETIME_FORMAT);
    }

    private String getFormattedStartTime() {
        assert this.startTime != null : "No start time stored!";

        return this.startTime.format(Task.DATETIME_FORMAT);
    }

    private String getFormattedEndTime() {
        assert this.endTime != null : "No end time stored!";

        return this.endTime.format(Task.DATETIME_FORMAT);
    }

    /**
     * Creates a formatted string of the Event data for storage.
     *
     * @return A string of the formatted data.
     */
    public String storageFormat() {
        return String.format("E | %s | %s | %s",
                super.storageFormat(), getFormattedStartTime(), getFormattedEndTime());
    }

    /**
     * Creates a formatted string of the Event data for display.
     *
     * @return A string of the formatted data.
     */
    public String displayFormat() {
        return "[E]" + String.format("%s (at: %s - %s)",
                super.displayFormat(), getFormattedStartTime(), getFormattedEndTime());
    }
}
