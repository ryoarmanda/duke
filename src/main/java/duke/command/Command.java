package duke.command;

import duke.exception.DukeValidationException;
import duke.utility.DukeResponse;
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

    /**
     * Validates the command parameters.
     *
     * @param taskList The task list.
     * @throws DukeValidationException if there are any invalid parameters.
     */
    public void validate(TaskList taskList) throws DukeValidationException {
        // No validation by default.
    }

    public abstract DukeResponse execute(TaskList tasks, Ui ui, Storage storage);
}
