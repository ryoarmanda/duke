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

    private void run() {
        this.ui.displayWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                String input = this.ui.readInput();
                this.ui.displayLine();
                Command c = Parser.parseCommand(input);
                c.execute(this.tasks, this.ui, this.storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                this.ui.displayError(e.getMessage());
            } finally {
                this.ui.displayLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
