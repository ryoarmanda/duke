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

    private void addTask(String description) {
        this.tasks[this.totalTasks++] = new Task(description);
        displayResponse("added: " + description);
    }

    private boolean handleInput(String input) {
        String[] tokens = input.split(" ");
        String cmd = tokens[0];

        switch (cmd) {
            case "bye":
                return true;
            case "list":
                displayTasks();
                return false;
            case "done":
                markTaskDone(Integer.parseInt(tokens[1]) - 1);
                return false;
            default:
                addTask(input);
                return false;
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
            stop = handleInput(sc.nextLine());
        }

        displayResponse("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
