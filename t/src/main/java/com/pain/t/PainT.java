package com.pain.t;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/*
    file: PainT.java
    date: 8/26/2025
    credit: Austin Oh
    description: Loads paint fxml and displays it.
*/
public class PainT extends Application{
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PainT.class.getResource("pain-t-display.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Pain(t)");
        stage.setScene(scene);
        stage.show();
    }//end start

    public static void main(String[] args) {
        launch();
    }//end main
}//end PainT
