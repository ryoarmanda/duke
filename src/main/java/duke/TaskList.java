package duke;

import java.util.ArrayList;

import duke.task.Task;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public Task getTask(int idx) {
        return this.tasks.get(idx);
    }

    public int totalTasks() {
        return this.tasks.size();
    }

    public void deleteTask(int idx) {
        this.tasks.remove(idx);
    }
}
