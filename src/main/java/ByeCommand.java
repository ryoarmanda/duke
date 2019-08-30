public class ByeCommand extends Command {
    public ByeCommand() {
        super(true);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayBye();
    }
}
