package duke.command;

import duke.utility.DukeResponse;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

public class ByeCommand extends Command {
    /**
     * Creates a ByeCommand object.
     */
    public ByeCommand() {
        super(true);
    }

    /**
     * Executes the bye command.
     *
     * @param tasks The task list.
     * @param ui The Ui object.
     * @param storage The Storage object.
     * @return The response string.
     */
    public DukeResponse execute(TaskList tasks, Ui ui, Storage storage) {
        return new DukeResponse(ui.displayBye(), this.isExit());
    }
}
