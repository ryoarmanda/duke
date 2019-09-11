package duke;

import java.util.Scanner;

import duke.task.Task;

public class Ui {
    /**
     * Displays file load success message.
     *
     * @return The response string.
     */
    public String displayLoadSuccess() {
        return "Data loaded successfully!";
    }

    /**
     * Displays file load error message.
     *
     * @return The response string.
     */
    public String displayLoadError() {
        return "There was an error in loading data from file.";
    }

    /**
     * Displays welcome message.
     *
     * @return The response string.
     */
    public String displayWelcome() {
        return "Hello! I'm Duke\n"
                + "What can I do for you?";
    }

    /**
     * Displays error response.
     *
     * @param message The error message.
     * @return The response string.
     */
    public String displayError(String message) {
        return ":( OOPS!!! " + message;
    }

    /**
     * Displays the list of tasks.
     *
     * @param taskList The task list.
     * @return The response string.
     */
    public String displayTasks(TaskList taskList) {
        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.totalTasks(); i++) {
            Task t = taskList.getTask(i);
            response.append(String.format("%d.%s\n", i + 1, t.displayFormat()));
        }

        return response.toString();
    }

    /**
     * Displays the response after adding a task.
     *
     * @param t The task that is just added.
     * @param taskCount The taskCount after the addition.
     * @return The response string.
     */
    public String displayAddTask(Task t, int taskCount) {
        return "Got it. I've added this task:\n"
                + "  " + t.displayFormat() + "\n"
                + "Now you have " + taskCount + " tasks in the list.";
    }

    /**
     * Displays the response after a task is marked done.
     *
     * @param t The task that has just been marked done.
     * @return The response string.
     */
    public String displayMarkedDone(Task t) {
        return "Nice! I've marked this task as done:\n"
                + "  " + t.displayFormat() + "\n";
    }

    /**
     * Displays the response after a task has been deleted.
     *
     * @param t The task that was just deleted.
     * @param taskCount The task count after the deletion.
     * @return The response string.
     */
    public String displayDeleteTask(Task t, int taskCount) {
        return "Noted. I've removed this task:\n"
                + "  " + t.displayFormat() + "\n"
                + "Now you have " + taskCount + " tasks in the list";
    }

    /**
     * Displays the found tasks after the find command.
     *
     * @param matched The list of matched tasks.
     * @return The response string.
     */
    public String displayFindTask(TaskList matched) {
        StringBuilder response = new StringBuilder("Here are the matching tasks in your list:");
        for (int i = 0; i < matched.totalTasks(); i++) {
            Task t = matched.getTask(i);
            response.append(String.format("%d.%s\n", i + 1, t.displayFormat()));
        }

        return response.toString();
    }

    /**
     * Displays goodbye message.
     *
     * @return The response string.
     */
    public String displayBye() {
        return "Bye. Hope to see you again soon!";
    }
}
