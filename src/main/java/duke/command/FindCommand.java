package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchedTasks = new TaskList();

        for (int i = 0; i < tasks.totalTasks(); i++) {
            Task t = tasks.getTask(i);
            if (t.getDescription().contains(this.keyword)) {
                matchedTasks.addTask(t);
            }
        }

        ui.displayFindTask(matchedTasks);
    }
}
