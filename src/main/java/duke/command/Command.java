package duke.command;

import duke.exception.DukeValidationException;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

public abstract class Command {
    private boolean isExit;

    /**
     * Creates a Command object.
     *
     * @param isExit The boolean that indicates whether the command should trigger
     *               the exit procedure.
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public void validate(TaskList tasks) throws DukeValidationException {
        // No validation by default.
    }

    public abstract String execute(TaskList tasks, Ui ui, Storage storage);
}
