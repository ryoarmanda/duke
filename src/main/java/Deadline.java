import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime time;

    public Deadline(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.time.format(super.DATETIME_FORMAT));
    }
}
