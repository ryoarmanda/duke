package duke.command;

import duke.exception.DukeValidationException;
import duke.task.Task;
import duke.task.TaskPriority;
import duke.utility.DukeResponse;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

public class PriorityCommand extends Command {
    private int taskIndex;
    private TaskPriority priority;

    /**
     * Creates a PriorityCommand object.
     *
     * @param taskIndex The index of the task to be deleted.
     * @param priority The task priority.
     */
    public PriorityCommand(int taskIndex, TaskPriority priority) {
        super(false);
        this.taskIndex = taskIndex;
        this.priority = priority;
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
     * Executes the priority command.
     *
     * @param tasks The task list.
     * @param ui The Ui object.
     * @param storage The Storage object.
     * @return The response string.
     */
    @Override
    public DukeResponse execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(this.taskIndex);
        task.setPriority(this.priority);
        storage.write(tasks);
        return new DukeResponse(ui.displayPrioritySet(task), this.isExit());
    }
}
