import java.util.Scanner;

public class Duke {
    private Task[] tasks;
    private int totalTasks;

    public Duke() {
        this.tasks = new Task[100];
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
        String[] formatted = new String[this.totalTasks];
        for (int i = 0; i < this.totalTasks; i++) {
            formatted[i] = String.format("%d. %s", i + 1, this.tasks[i].getDescription());
        }
        displayResponse(formatted);
    }

    private void run() {
        Scanner sc = new Scanner(System.in);

        displayResponse(new String[]{"Hello! I'm Duke", "What can I do for you?"});

        String cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            if (cmd.equals("list")) {
                displayTasks();
            } else {
                this.tasks[this.totalTasks++] = new Task(cmd);
                displayResponse("added: " + cmd);
            }

            cmd = sc.nextLine();
        }

        displayResponse("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
