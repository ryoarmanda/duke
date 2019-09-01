package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        super(false);
        this.taskIndex = taskIndex;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(this.taskIndex);
        tasks.deleteTask(this.taskIndex);
        storage.write(tasks);
        ui.displayDeleteTask(task, tasks.totalTasks());
    }
}
