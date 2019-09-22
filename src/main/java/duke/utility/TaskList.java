package duke.utility;

import java.util.ArrayList;

import duke.task.Task;

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Creates a TaskList object filled with task data.
     *
     * @param tasks The tasks list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param t The Task object to be added.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * Gets a task from the list.
     *
     * @param idx The index of the task.
     * @return The Task object at the specified index.
     */
    public Task getTask(int idx) {
        return this.tasks.get(idx);
    }

    /**
     * Gets the total count of tasks in the list.
     *
     * @return Task count.
     */
    public int totalTasks() {
        return this.tasks.size();
    }

    /**
     * Deletes a task from the list.
     *
     * @param idx The index of the task.
     */
    public void deleteTask(int idx) {
        this.tasks.remove(idx);
    }
}
