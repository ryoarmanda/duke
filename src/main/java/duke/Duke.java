package duke;

import duke.command.Command;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

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
        } catch (DukeException e) {
            this.ui.displayLoadingError();
            this.tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);
            return c.execute(this.tasks, this.ui, this.storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
