package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    /**
     * Creates a ListCommand object.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Executes the list command.
     *
     * @param tasks The task list.
     * @param ui The Ui object.
     * @param storage The Storage object.
     * @return The response string.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.displayTasks(tasks);
    }
}
