package com.pain.t;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Optional;

/*
    file: PainT.java
    date: 8/26/2025
    credit: Austin Oh
    description: Loads paint fxml and displays it.
*/


public class PainT extends Application{
    /**
     * Loads pain-t-display.fxml and creates a warning message to be
     * displayed upon pressing the x button when ran.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PainT.class.getResource("pain-t-display.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Pain(t)");
        stage.setScene(scene);

        Alert closeAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to close? Any unsaved progress will be lost.");
        closeAlert.setTitle("Careful!");
        closeAlert.setHeaderText("Warning");

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Optional<ButtonType> choice = closeAlert.showAndWait();

                //if user does not click ok, the close attempt is consumed,
                //and the program stays open.
                //if they do, it is allowed to close.
                if(choice.get() != ButtonType.OK){
                    windowEvent.consume();
                }//end if
            }//end handler
        });//end close setter

        stage.show();
    }//end start

    public static void main(String[] args) {
        launch();
    }//end main
}//end PainT
