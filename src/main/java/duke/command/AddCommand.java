package duke.command;

import duke.exception.DukeValidationException;
import duke.utility.DateTime;
import duke.exception.DukeException;
import duke.utility.DukeResponse;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.Todo;


public class AddCommand extends Command {
    private TaskType type;
    private String description;
    private DateTime time;
    private DateTime timeEnd;

    /**
     * Creates an AddCommand object.
     *
     * @param type The type of the task.
     * @param description The description of the task.
     * @param dates The dates of the task, if any.
     */
    public AddCommand(TaskType type, String description, DateTime... dates) {
        super(false);
        this.type = type;
        this.description = description;
        this.time = dates.length > 0 ? dates[0] : null;
        this.timeEnd = dates.length > 1 ? dates[1] : null;
    }

    /**
     * Validates the command parameters.
     *
     * @param taskList The task list.
     * @throws DukeValidationException if there are any invalid parameters.
     */
    public void validate(TaskList taskList) throws DukeValidationException {
        if (this.type == null) {
            throw new DukeValidationException("Task type is not set");
        }

        if (this.description.isEmpty()) {
            throw new DukeValidationException("Task description is empty.");
        }

        if (this.type == TaskType.DEADLINE) {
            if (this.time == null) {
                throw new DukeValidationException("No date is set");
            }
        }

        if (this.type == TaskType.EVENT) {
            if (this.time == null) {
                throw new DukeValidationException("No start date is set");
            } else if (this.timeEnd == null) {
                throw new DukeValidationException("No end date is set");
            } else if (this.timeEnd.compareTo(this.time) < 0) {
                throw new DukeValidationException("Time range invalid.");
            }
        }
    }

    /**
     * Executes the add command.
     *
     * @param tasks The task list.
     * @param ui The Ui object.
     * @param storage The Storage object.
     * @return The response string.
     */
    public DukeResponse execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task;

        switch (this.type) {
        case TODO:
            task = new Todo(this.description);
            break;
        case DEADLINE:
            task = new Deadline(this.description, this.time);
            break;
        case EVENT:
            task = new Event(this.description, this.time, this.timeEnd);
            break;
        default:
            throw new DukeException("Task type not yet supported.");
        }

        tasks.addTask(task);
        storage.write(tasks);
        return new DukeResponse(ui.displayAddTask(task, tasks.totalTasks()), this.isExit());
    }
}
