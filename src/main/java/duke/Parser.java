package duke;

import duke.command.*;
import duke.task.*;

public class Parser {
    public static Command parseCommand(String input) throws DukeException {
        String[] tokens = input.split(" ", 2);
        String[] args, times;
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

    public static Task parseTask(String input) throws DukeException {
        String[] cmd = input.split(" \\| ");

        Task task;
        switch(cmd[0]) {
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
