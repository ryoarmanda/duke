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
    PRIORITY("priority"),
    BYE("bye");

    private final String cmd;

    CommandType(String cmd) {
        this.cmd = cmd;
    }

    /**
     * Parses given command code to its corresponding command type.
     *
     * @param cmd The command code.
     * @return The command type, if found.
     * @throws UnknownCommandTypeException if no matching command type found.
     */
    public static CommandType parse(String cmd) throws UnknownCommandTypeException {
        for (CommandType c : values()) {
            if (c.cmd.equals(cmd)) {
                return c;
            }
        }

        throw new UnknownCommandTypeException("Invalid command type.");
    }
}
