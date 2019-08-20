import java.util.Scanner;

public class Duke {
    private void displayResponse(String[] lines) {
        String bar = "____________________________________________________________";

        System.out.println(bar);
        for (String line : lines) {
            System.out.println(" " + line);
        }
        System.out.println(bar);
        System.out.println();
    }

    private void displayResponse(String line) {
        displayResponse(new String[]{line});
    }

    private void run() {
        Scanner sc = new Scanner(System.in);

        displayResponse(new String[]{"Hello! I'm Duke", "What can I do for you?"});

        String cmd = sc.next();
        while (!cmd.equals("bye")) {
            displayResponse(cmd);
            cmd = sc.next();
        }

        displayResponse("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
