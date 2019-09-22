package duke.utility;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.CommandType;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;

import duke.exception.DukeParseException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskPriority;
import duke.task.TaskStatus;
import duke.task.TaskType;
import duke.task.Todo;

public class Parser {
    /**
     * Parses the input string coming from command-line.
     * If there are any incomplete or invalid inputs, an exception is thrown.
     *
     * @param input The input string from the command-line.
     * @return Command The command describing the action.
     * @throws DukeParseException if the input syntax is invalid.
     */
    public static Command parseCommand(String input) throws DukeParseException {
        String[] tokens = input.split(" ", 2);

        CommandType cmd = CommandType.parse(tokens[0]);

        // Handling commands with no parameters first
        switch (cmd) {
        case LIST:
            return new ListCommand();
        case BYE:
            return new ByeCommand();
        default:
            break;
        }

        // Handling commands with parameters
        checkExistence(tokens, 1, "Please supply data for this command.");
        switch (cmd) {
        case TODO:
            return parseTodo(tokens[1]);
        case DEADLINE:
            return parseDeadline(tokens[1]);
        case EVENT:
            return parseEvent(tokens[1]);
        case DONE:
            return parseDone(tokens[1]);
        case DELETE:
            return parseDelete(tokens[1]);
        case FIND:
            return parseFind(tokens[1]);
        default:
            throw new DukeParseException("Command type not yet supported.");
        }
    }

    /**
     * Parses the task data from the storage format.
     * If there are any incomplete or invalid inputs, an exception is thrown.
     *
     * @param input A line from the local save file.
     * @return Task The task parsed from input.
     * @throws DukeParseException if the input is not valid.
     */
    public static Task parseTask(String input) throws DukeParseException {
        String[] args = input.split(" \\| ");

        Task task;
        TaskType type = TaskType.parse(args[0]);
        TaskPriority priority = TaskPriority.parse(args[1]);
        TaskStatus status = TaskStatus.parse(args[2]);

        switch (type) {
        case TODO:
            task = new Todo(args[3], priority);
            break;
        case DEADLINE:
            task = new Deadline(args[3], priority, DateTime.parse(args[4]));
            break;
        case EVENT:
            task = new Event(args[3], priority, DateTime.parse(args[4]), DateTime.parse(args[5]));
            break;
        default:
            throw new DukeParseException("Task type not yet supported.");
        }

        if (status == TaskStatus.DONE) {
            task.markAsDone();
        }

        return task;
    }

    private static Command parseTodo(String input) {
        String[] descAndPriority = input.split(" /p ", 2);

        checkExistence(descAndPriority, 1, "Please supply the task priority.");
        TaskPriority priority = TaskPriority.parse(descAndPriority[1]);

        return new AddCommand(TaskType.TODO, priority, descAndPriority[0]);
    }

    private static Command parseDeadline(String input) {
        String[] descAndRest = input.split(" /p ", 2);

        checkExistence(descAndRest, 1, "Please supply the task priority.");
        String[] priorityAndDate = descAndRest[1].split(" /by ", 2);
        TaskPriority priority = TaskPriority.parse(priorityAndDate[0]);

        checkExistence(priorityAndDate, 1, "Please supply the task date.");
        DateTime date = DateTime.parse(priorityAndDate[1]);

        return new AddCommand(TaskType.DEADLINE, priority, descAndRest[0], date);
    }

    private static Command parseEvent(String input) {
        String[] descAndRest = input.split(" /p ", 2);

        checkExistence(descAndRest, 1, "Please supply the task priority.");
        String[] priorityAndDates = descAndRest[1].split(" /at ", 2);
        TaskPriority priority = TaskPriority.parse(priorityAndDates[0]);

        checkExistence(priorityAndDates, 1, "Please supply the task dates.");
        String[] startAndEndDate = priorityAndDates[1].split(" - ", 2);
        DateTime startDate = DateTime.parse(startAndEndDate[0]);

        checkExistence(startAndEndDate, 1, "Please supply the task end date.");
        DateTime endDate = DateTime.parse(startAndEndDate[1]);

        return new AddCommand(TaskType.EVENT, priority, descAndRest[0], startDate, endDate);
    }

    private static Command parseDone(String input) {
        int idx = Integer.parseInt(input) - 1;
        return new DoneCommand(idx);
    }

    private static Command parseDelete(String input) {
        int idx = Integer.parseInt(input) - 1;
        return new DeleteCommand(idx);
    }

    private static Command parseFind(String input) {
        return new FindCommand(input);
    }

    private static void checkExistence(String[] args, int index, String message) throws DukeParseException {
        assert args != null;

        if (args.length <= index) {
            throw new DukeParseException(message);
        }
    }
}
