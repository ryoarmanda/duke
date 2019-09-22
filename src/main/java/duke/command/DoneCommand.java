package duke.command;

import duke.exception.DukeException;
import duke.exception.DukeValidationException;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;
import duke.task.Task;

public class DoneCommand extends Command {
    private int taskIndex;

    /**
     * Creates a DoneCommand object.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public DoneCommand(int taskIndex) {
        super(false);
        this.taskIndex = taskIndex;
    }

    public void validate(TaskList taskList) throws DukeValidationException {
        if (this.taskIndex >= taskList.totalTasks()) {
            throw new DukeValidationException("There is no task number " + this.taskIndex);
        }
    }

    /**
     * Executes the done command.
     *
     * @param tasks The task list.
     * @param ui The Ui object.
     * @param storage The Storage object.
     * @return The response string.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(this.taskIndex);
        task.markAsDone();
        storage.write(tasks);
        return ui.displayMarkedDone(task);
    }
}
