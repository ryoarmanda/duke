public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String storageFormat() {
        return String.format("T | %s", super.storageFormat());
    }

    public String displayFormat() {
        return "[T]" + super.displayFormat();
    }
}
