import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s - %s)", super.toString(),
                this.startTime.format(super.DATETIME_FORMAT),
                this.endTime.format(super.DATETIME_FORMAT));
    }
}
