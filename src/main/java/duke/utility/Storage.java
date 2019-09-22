package duke.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Task;

public class Storage {
    private String filePath;

    /**
     * Creates a Storage object.
     *
     * @param filePath The relative file path.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads the task data from the local save file.
     * If the file is not found, an exception is thrown.
     *
     * @return tasks The list of tasks parsed from the file.
     * @throws DukeException if the file is not found.
     */
    public ArrayList<Task> read() throws DukeException {
        File f = new File(this.filePath);
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                tasks.add(Parser.parseTask(sc.nextLine()));
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("duke.Storage file not found.");
        }

        return tasks;
    }

    /**
     * Writes the tasks and their data to the local save file.
     * If there are any errors in writing the file, an exception is thrown.
     *
     * @param taskList A TaskList object which contains tasks to be written.
     * @throws DukeException if there is an error in writing the file.
     */
    public void write(TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (int i = 0; i < taskList.totalTasks(); i++) {
                Task t = taskList.getTask(i);
                fw.write(t.storageFormat() + '\n');
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Cannot save tasks to file.");
        }
    }
}
