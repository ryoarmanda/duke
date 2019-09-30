package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.utility.DukeResponse;
import duke.utility.Parser;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private boolean isLoaded;

    /**
     * Creates a Duke object.
     *
     * @param filePath The file path of the local save data.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.read());
            this.isLoaded = true;
        } catch (DukeException e) {
            this.tasks = new TaskList();
            this.isLoaded = false;
        }
    }

    /**
     * Gets the welcome message.
     *
     * @return Welcome message.
     */
    public String getWelcomeResponse() {
        return this.ui.displayWelcome();
    }

    /**
     * Gets the load response, depends on whether file load is successful.
     *
     * @return Load response.
     */
    public String getLoadResponse() {
        if (this.isLoaded) {
            return this.ui.displayLoadSuccess();
        } else {
            return this.ui.displayLoadError();
        }
    }

    /**
     * Gets the execution response from a command-line input.
     *
     * @param input The raw input from the command-line.
     * @return The response after executing the input.
     */
    public DukeResponse getResponse(String input) {
        DukeResponse response;
        try {
            Command c = Parser.parseCommand(input);
            c.validate(this.tasks);
            response = c.execute(this.tasks, this.ui, this.storage);
        } catch (DukeException e) {
            response = new DukeResponse(e.getMessage(), false);
        }

        return response;
    }
}
