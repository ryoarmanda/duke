package duke.command;

import duke.exception.UnknownCommandTypeException;

public enum CommandType {
    LIST("list"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DONE("done"),
    DELETE("delete"),
    FIND("find"),
    BYE("bye");

    private final String cmd;

    CommandType(String cmd) {
        this.cmd = cmd;
    }

    public static CommandType parse(String cmd) throws UnknownCommandTypeException {
        for (CommandType c : values()) {
            if (c.cmd.equals(cmd)) {
                return c;
            }
        }

        throw new UnknownCommandTypeException("Invalid command type.");
    }
}
