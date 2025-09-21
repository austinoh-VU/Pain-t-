package com.pain.t;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;

public class HelpMenu {

    /**
     * Builds a scrollable scene that displays the text from the program's release notes
     * and displays it in a new window.
     */
    @FXML
    protected static void about(){
        ScrollPane root = new ScrollPane();
        Scene scene = new Scene(root, 600, 400);
        Stage stage = new Stage();

        File file = new File("Release_Notes.txt");

        Text about = new Text(10, 10, "default");
        about.setText(Utilities.textBuilder(file.getAbsolutePath()));

        root.setContent(about);

        stage.setTitle("About");
        stage.setScene(scene);
        stage.show();
    }//end about

    /**
     * Builds a scrollable scene that displays the text from the program's tips.txt file
     * and displays it in a new window.
     */
    @FXML
    protected static void tips() {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600);
        Stage stage = new Stage();

        File file = new File("Tips.txt");

        Text tips = new Text(10, 10, "default");
        tips.setText(Utilities.textBuilder(file.getAbsolutePath()));
        root.getChildren().add(tips);

        stage.setTitle("Tips");
        stage.setScene(scene);
        stage.show();

    }//end tips
}//end help menu
