package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.Todo;


public class AddCommand extends Command {
    private TaskType type;
    private String description;
    private String time;
    private String timeEnd;

    public AddCommand(TaskType type, String... args) {
        super(false);
        this.type = type;
        this.description = args[0];
        this.time = args.length > 1 ? args[1] : "";
        this.timeEnd = args.length > 2 ? args[2] : "";
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
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
            // TODO: Create error message
            throw new DukeException("Wrong duke.task type.");
        }

        tasks.addTask(task);
        storage.write(tasks);
        ui.displayAddTask(task, tasks.totalTasks());
    }
}
