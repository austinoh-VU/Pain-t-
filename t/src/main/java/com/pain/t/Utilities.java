package com.pain.t;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Utilities {
    /**
     * Instantiates a <code>FileChooser</code> object and applies a filter for png, jpg,
     * and bmp images
     * @return A <code>FileChooser</code> filtered for png, jpg, and bmp files
     */
    protected static FileChooser imgChooser(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pick an image file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images (*.png), (*.jpg), (*.bmp)", "*.png", "*.jpg", "*.bmp"));
        return fileChooser;
    }//end imgChooser

    /**
     * opens a file and takes the text of the given file
     * and turns it into a <code>String</code>.
     * @param filePath File path of the file to be read
     * @return <code>String</code> containing the text contained by the read file. Returns a failure message if something goes wrong
     */
    protected static String textBuilder(String filePath){
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))){
            StringBuilder stringBuilder = new StringBuilder();
            String textLine = bufferedReader.readLine();

            while (textLine != null){
                stringBuilder.append(textLine);
                stringBuilder.append(System.lineSeparator());
                textLine = bufferedReader.readLine();
            }//end while builder

            return stringBuilder.toString();
        } catch (IOException exception){
            return "Text file building failed.";
        }//end try-catch
    }//end text builder
}