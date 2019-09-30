package duke.command;

import duke.exception.DukeValidationException;
import duke.utility.DukeResponse;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;
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
     * Validates the command parameters.
     *
     * @param taskList The task list.
     * @throws DukeValidationException if there are any invalid parameters.
     */
    public void validate(TaskList taskList) throws DukeValidationException {
        if (this.keyword.isEmpty()) {
            throw new DukeValidationException("Keyword is empty");
        }
    }

    /**
     * Executes the find command.
     *
     * @param tasks The task list.
     * @param ui The Ui object.
     * @param storage The Storage object.
     * @return The response string.
     */
    public DukeResponse execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchedTasks = new TaskList();

        for (int i = 0; i < tasks.totalTasks(); i++) {
            Task t = tasks.getTask(i);
            String taskDesc = t.getDescription().toLowerCase();
            if (taskDesc.contains(this.keyword.toLowerCase())) {
                matchedTasks.addTask(t);
            }
        }

        return new DukeResponse(ui.displayFindTask(matchedTasks), this.isExit());
    }
}
