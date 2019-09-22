package duke.gui;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import duke.Duke;
import duke.DukeApp;
import duke.utility.DukeResponse;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Handles the display of startup responses from Duke.
     */
    public void handleDukeStartup() {
        String welcomeResponse = duke.getWelcomeResponse();
        String loadResponse = duke.getLoadResponse();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcomeResponse, dukeImage),
                DialogBox.getDukeDialog(loadResponse, dukeImage)
        );
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        DukeResponse response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response.getMessage(), dukeImage)
        );
        userInput.clear();

        if (response.isExit()) {
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> DukeApp.stage.hide());
            pause.play();
        }
    }
}
