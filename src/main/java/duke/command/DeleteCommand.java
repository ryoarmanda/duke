package duke.command;

import duke.exception.DukeValidationException;
import duke.utility.DukeResponse;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Creates a DeleteCommand object.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        super(false);
        this.taskIndex = taskIndex;
    }

    /**
     * Validates the command parameters.
     *
     * @param taskList The task list.
     * @throws DukeValidationException if there are any invalid parameters.
     */
    public void validate(TaskList taskList) throws DukeValidationException {
        if (this.taskIndex >= taskList.totalTasks()) {
            throw new DukeValidationException("There is no task number " + (this.taskIndex + 1));
        }
    }

    /**
     * Executes the delete command.
     *
     * @param tasks The task list.
     * @param ui The Ui object.
     * @param storage The Storage object.
     * @return The response string.
     */
    public DukeResponse execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(this.taskIndex);
        tasks.deleteTask(this.taskIndex);
        storage.write(tasks);
        return new DukeResponse(ui.displayDeleteTask(task, tasks.totalTasks()), this.isExit());
    }
}
