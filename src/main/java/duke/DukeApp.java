package duke;

import java.io.IOException;

import duke.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DukeApp extends Application {
    public static Stage stage;
    private Duke duke = new Duke("./data/duke.txt");

    @Override
    public void start(Stage stage) {
        try {
            DukeApp.stage = stage;
            FXMLLoader fxmlLoader = new FXMLLoader(DukeApp.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Duke, the All Knowing");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().handleDukeStartup();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
