import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private static final String FILE_PATH = "./data/duke.txt";
    private ArrayList<Task> tasks;

    public Duke() {
        this.tasks = new ArrayList<>();
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
        ArrayList<String> responses = new ArrayList<>();

        responses.add("Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            String formatted = String.format("%d.%s", i + 1, this.tasks.get(i));
            responses.add(formatted);
        }

        displayResponse(responses.toArray(new String[]{}));
    }

    private void markTaskDone(int taskIndex) {
        Task task = this.tasks.get(taskIndex);
        task.markAsDone();
        displayResponse(new String[]{
            "Nice! I've marked this task as done:",
            "  " + task
        });
    }

    private void addTask(Task task) {
        this.tasks.add(task);
        displayResponse(new String[]{
            "Got it. I've added this task:",
            "  " + task,
            "Now you have " + this.tasks.size() + " tasks in the list."
        });
    }

    private void deleteTask(int taskIndex) {
        Task task = this.tasks.get(taskIndex);
        this.tasks.remove(taskIndex);
        displayResponse(new String[]{
                "Noted. I've removed this task:",
                "  " + task,
                "Now you have " + this.tasks.size() + " tasks in the list."
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
            case "delete":
                deleteTask(Integer.parseInt(tokens[1]) - 1);
                return false;
            default:
                throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private void readFromFile() throws FileNotFoundException {
        File f = new File(FILE_PATH);
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            String[] cmd = sc.nextLine().split(" \\| ");

            String type = cmd[0];
            boolean isDone = cmd[1].equals("1");
            String desc = cmd[2];

            Task task = new Task(desc);
            switch(type) {
            case "T":
                task = new Todo(desc);
                break;
            case "D":
                String by = cmd[3];
                task = new Deadline(desc, by);
                break;
            case "E":
                String at = cmd[3];
                task = new Event(desc, at);
                break;
            }

            if (isDone) {
                task.markAsDone();
            }

            tasks.add(task);
        }
    }

    private void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Task task : tasks) {
            String desc = task.getDescription();
            String done = task.isDone() ? "1" : "0";

            String s = "";
            if (task instanceof Todo) {
                s = String.join(" | ", "T", done, desc);
            } else if (task instanceof Deadline) {
                s = String.join(" | ", "D", done, desc, ((Deadline) task).getTime());
            } else if (task instanceof Event) {
                s = String.join(" | ", "E", done, desc, ((Event) task).getTime());
            }
            s += "\n";
            fw.write(s);
        }
        fw.close();
    }

    private void run() {
        try {
            readFromFile();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

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

        try {
            writeToFile();
        } catch (IOException e) {
            System.out.println("Cannot write to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
