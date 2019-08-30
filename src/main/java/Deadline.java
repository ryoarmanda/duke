import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime time;

    public Deadline(String description, String time) {
        super(description);
        this.time = LocalDateTime.parse(time, Task.DATETIME_FORMAT);
    }

    public String getFormattedTime() {
        return this.time.format(Task.DATETIME_FORMAT);
    }

    public String storageFormat() {
        return String.format("D | %s | %s", super.storageFormat(), getFormattedTime());
    }

    public String displayFormat() {
        return "[D]" + String.format("%s (by: %s)", super.displayFormat(), getFormattedTime());
    }
}
