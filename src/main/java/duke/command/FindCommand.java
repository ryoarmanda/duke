package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a FindCommand object.
     *
     * @param keyword The keyword to find through all the tasks.
     */
    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    /**
     * Executes the find command.
     *
     * @param tasks The task list.
     * @param ui The Ui object.
     * @param storage The Storage object.
     * @return The response string.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchedTasks = new TaskList();

        for (int i = 0; i < tasks.totalTasks(); i++) {
            Task t = tasks.getTask(i);
            if (t.getDescription().contains(this.keyword)) {
                matchedTasks.addTask(t);
            }
        }

        return ui.displayFindTask(matchedTasks);
    }
}
