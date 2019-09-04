package duke;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.Todo;

public class Parser {
    /**
     * Parses the input string coming from command-line.
     * If there are any incomplete or invalid inputs, an exception is thrown.
     *
     * @param input The input string from the command-line.
     * @return Command The command describing the action.
     * @throws DukeException if the input syntax is invalid.
     */
    public static Command parseCommand(String input) throws DukeException {
        String[] tokens = input.split(" ", 2);
        String[] args;
        String[] times;
        switch (tokens[0]) {
        case "list":
            return new ListCommand();
        case "todo":
            checkExistence(tokens, 2, "Please supply data for the task.");
            return new AddCommand(TaskType.TODO, tokens[1]);
        case "deadline":
            checkExistence(tokens, 2, "Please supply data for the task.");
            args = tokens[1].split(" /by ");
            checkExistence(args, 1, "Please supply description for the task.");
            checkExistence(args, 2, "Please supply the deadline for the task.");
            return new AddCommand(TaskType.DEADLINE, args[0], args[1]);
        case "event":
            checkExistence(tokens, 2, "Please supply data for the task.");
            args = tokens[1].split(" /at ");
            checkExistence(args, 1, "Please supply description for the task.");
            checkExistence(args, 2, "Please supply the date range for the task.");
            times = args[1].split(" - ");
            checkExistence(times, 1, "Please supply the start date for the task.");
            checkExistence(times, 2, "Please supply the end date for the task.");
            return new AddCommand(TaskType.EVENT, args[0], times[0], times[1]);
        case "done":
            checkExistence(tokens, 2, "Please supply data for the task.");
            return new DoneCommand(Integer.parseInt(tokens[1]) - 1);
        case "delete":
            checkExistence(tokens, 2, "Please supply data for the task.");
            return new DeleteCommand(Integer.parseInt(tokens[1]) - 1);
        case "find":
            checkExistence(tokens, 2, "Please supply data for the task.");
            return new FindCommand(tokens[1]);
        case "bye":
            return new ByeCommand();
        default:
            throw new DukeException("I'm sorry, but I don't know what it means.");
        }
    }

    /**
     * Parses the data from a line of a local file.
     * If there are any incomplete or invalid inputs, an exception is thrown.
     *
     * @param input A line from the local save file.
     * @return Task The task parsed from input.
     * @throws DukeException if the input is not valid.
     */
    public static Task parseTask(String input) throws DukeException {
        String[] cmd = input.split(" \\| ");

        Task task;
        switch (cmd[0]) {
        case "T":
            task = new Todo(cmd[2]);
            break;
        case "D":
            task = new Deadline(cmd[2], cmd[3]);
            break;
        case "E":
            task = new Event(cmd[2], cmd[3], cmd[4]);
            break;
        default:
            // TODO: Create error message
            throw new DukeException("There is a faulty data in file.");
        }

        if (cmd[1].equals("1")) {
            task.markAsDone();
        }

        return task;
    }

    private static void checkExistence(String[] args, int length, String errMessage) throws DukeException {
        if (args.length < length) {
            throw new DukeException(errMessage);
        }
    }
}
