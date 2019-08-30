import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = LocalDateTime.parse(startTime, Task.DATETIME_FORMAT);
        this.endTime = LocalDateTime.parse(endTime, Task.DATETIME_FORMAT);
    }

    public String getFormattedStartTime() {
        return this.startTime.format(Task.DATETIME_FORMAT);
    }

    public String getFormattedEndTime() {
        return this.endTime.format(Task.DATETIME_FORMAT);
    }

    public String storageFormat() {
        return String.format("E | %s | %s | %s",
                super.storageFormat(), getFormattedStartTime(), getFormattedEndTime());
    }

    public String displayFormat() {
        return "[E]" + String.format("%s (at: %s - %s)",
                super.displayFormat(), getFormattedStartTime(), getFormattedEndTime());
    }
}
