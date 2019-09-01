package duke.task;

import java.time.format.DateTimeFormatter;

public class Task {
    public static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    protected String description;
    protected boolean isDone;

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

    public String storageFormat() {
        return String.format("%s | %s", this.isDone ? "1" : "0", this.description);
    }

    public String displayFormat() {
        return String.format("[%s] %s", this.isDone ? "\u2714" : "\u2718", this.description);
    }
}
