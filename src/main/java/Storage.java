import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> read() throws DukeException {
        File f = new File(this.filePath);
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                tasks.add(Parser.parseTask(sc.nextLine()));
            }
        } catch (FileNotFoundException e) {
            // TODO: Create error message
            throw new DukeException("Storage file not found.");
        }

        return tasks;
    }

    public void write(TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (int i = 0; i < taskList.totalTasks(); i++) {
                Task t = taskList.getTask(i);
                fw.write(t.storageFormat() + '\n');
            }
            fw.close();
        } catch (IOException e) {
            // TODO: Create error message
            throw new DukeException("Cannot save tasks to file.");
        }
    }
}
