package duke.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
    }

    /**
     * Creates a user dialog box.
     *
     * @param text The user text.
     * @param img The user image.
     * @return A DialogBox object representing the dialog box.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.setStyle(
            "-fx-border-width: 0 1 0 0;"
            + "-fx-border-color: black;"
            + "-fx-label-padding: 5 10 5 10"
        );
        db.dialog.setAlignment(Pos.CENTER_RIGHT);
        return db;
    }

    /**
     * Creates a dialog from Duke.
     *
     * @param text The dialog text.
     * @param img The image for Duke.
     * @return The DialogBox object for Duke.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.dialog.setStyle(
            "-fx-border-width: 0 0 0 1;"
            + "-fx-border-color: black;"
            + "-fx-label-padding: 5 10 5 10"
        );
        db.dialog.setAlignment(Pos.CENTER_LEFT);
        return db;
    }
}