package duke;

import java.util.Scanner;

import duke.task.Task;

public class Ui {
    Scanner sc;

    /**
     * Creates a Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads a line from the command-line input.
     *
     * @return The line read from the input.
     */
    public String readInput() {
        return this.sc.nextLine();
    }

    /**
     * Displays the separator line for responses.
     */
    public void displayLine() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }

    /**
     * Displays file load error message.
     */
    public void displayLoadingError() {
        this.displayLine();
        System.out.println("There was an error in loading data from file.");
        this.displayLine();
    }

    /**
     * Displays welcome message.
     */
    public void displayWelcome() {
        this.displayLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        this.displayLine();
    }

    /**
     * Displays error response.
     *
     * @param message The error message.
     */
    public void displayError(String message) {
        System.out.println(":( OOPS!!! " + message);
    }

    /**
     * Displays the list of tasks.
     *
     * @param taskList The task list.
     */
    public void displayTasks(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.totalTasks(); i++) {
            Task t = taskList.getTask(i);
            System.out.printf("%d.%s\n", i + 1, t.displayFormat());
        }
    }

    /**
     * Displays the response after adding a task.
     *
     * @param t The task that is just added.
     * @param taskCount The taskCount after the addition.
     */
    public void displayAddTask(Task t, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t.displayFormat());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays the response after a task is marked done.
     *
     * @param t The task that has just been marked done.
     */
    public void displayMarkedDone(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + t.displayFormat());
    }

    /**
     * Displays the response after a task has been deleted.
     *
     * @param t The task that was just deleted.
     * @param taskCount The task count after the deletion.
     */
    public void displayDeleteTask(Task t, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + t.displayFormat());
        System.out.println("Now you have " + taskCount + " tasks in the list");
    }

    /**
     * Displays the found tasks after the find command.
     *
     * @param matched The list of matched tasks.
     */
    public void displayFindTask(TaskList matched) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < matched.totalTasks(); i++) {
            Task t = matched.getTask(i);
            System.out.printf("%d.%s\n", i + 1, t.displayFormat());
        }
    }

    /**
     * Displays goodbye message.
     */
    public void displayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
