import java.util.Scanner;

public class Ui {
    Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readInput() {
        return this.sc.nextLine();
    }

    public void displayLine() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }

    public void displayLoadingError() {
        this.displayLine();
        System.out.println("There was an error in loading data from file.");
        this.displayLine();
    }

    public void displayWelcome() {
        this.displayLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        this.displayLine();
    }

    public void displayError(String message) {
        System.out.println("\u2639 OOPS!!! " + message);
    }

    public void displayTasks(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.totalTasks(); i++) {
            Task t = taskList.getTask(i);
            System.out.printf("%d.%s\n", i + 1, t.displayFormat());
        }
    }

    public void displayAddTask(Task t, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t.displayFormat());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public void displayMarkedDone(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + t.displayFormat());
    }

    public void displayDeleteTask(Task t, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + t.displayFormat());
        System.out.println("Now you have " + taskCount + " tasks in the list");
    }

    public void displayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
