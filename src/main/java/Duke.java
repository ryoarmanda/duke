import java.util.Scanner;

public class Duke {
    private static final int MAX_TASKS = 100;
    private Task[] tasks;
    private int totalTasks;

    public Duke() {
        this.tasks = new Task[MAX_TASKS];
        this.totalTasks = 0;
    }

    // For displaying multi-line response
    private void displayResponse(String[] lines) {
        String bar = "____________________________________________________________";

        System.out.println(bar);
        for (String line : lines) {
            System.out.println(" " + line);
        }
        System.out.println(bar);
        System.out.println();
    }

    // For displaying single-line response
    private void displayResponse(String line) {
        displayResponse(new String[]{line});
    }

    private void displayTasks() {
        String[] responses = new String[this.totalTasks + 1];

        responses[0] = "Here are the tasks in your list:";
        for (int i = 0; i < this.totalTasks; i++) {
            responses[i + 1] = String.format("%d.%s", i + 1, this.tasks[i]);
        }

        displayResponse(responses);
    }

    private void markTaskDone(int taskIndex) {
        Task task = this.tasks[taskIndex];
        task.markAsDone();
        displayResponse(new String[]{
            "Nice! I've marked this task as done:",
            "  " + task
        });
    }

    private void addTask(Task task) {
        this.tasks[this.totalTasks++] = task;
        displayResponse(new String[]{
            "Got it. I've added this task:",
            "  " + task,
            "Now you have " + this.totalTasks + " tasks in the list."
        });
    }

    // Handles input to its appropriate functions, returns boolean
    // that signifies whether the infinite loop should be stopped.
    private boolean handleInput(String input) throws DukeException {
        // Command-only input
        if (input.equals("bye")) {
            return true;
        } else if (input.equals("list")) {
            displayTasks();
            return false;
        }

        String[] tokens = input.split(" ", 2);
        String cmd = tokens[0];
        switch (cmd) {
            case "done":
                markTaskDone(Integer.parseInt(tokens[1]) - 1);
                return false;
            case "todo":
                try {
                    addTask(new Todo(tokens[1]));
                    return false;
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty.");
                }
            case "deadline":
                String[] deadline_args = tokens[1].split(" /by ");
                addTask(new Deadline(deadline_args[0], deadline_args[1]));
                return false;
            case "event":
                String[] event_args = tokens[1].split(" /at ");
                addTask(new Event(event_args[0], event_args[1]));
                return false;
            default:
                throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private void run() {
        Scanner sc = new Scanner(System.in);

        displayResponse(new String[]{
            "Hello! I'm Duke",
            "What can I do for you?"
        });

        boolean stop = false;
        while (!stop) {
            try {
                stop = handleInput(sc.nextLine());
            } catch (DukeException e) {
                displayResponse(e.getMessage());
            }
        }

        displayResponse("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
