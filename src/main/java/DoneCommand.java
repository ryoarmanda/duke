public class DoneCommand extends Command {
    private int taskIndex;

    public DoneCommand(int taskIndex) {
        super(false);
        this.taskIndex = taskIndex;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(this.taskIndex);
        task.markAsDone();
        storage.write(tasks);
        ui.displayMarkedDone(task);
    }
}
