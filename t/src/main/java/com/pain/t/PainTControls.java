package com.pain.t;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

/*
    file: PainTControls.java
    date: 8/26/2025
    credit: Austin Oh
    description: Controls for paint fxml.
*/
public class PainTControls {
    //variables
    @FXML private Image imagePixelate;
    @FXML private WritableImage image;
    @FXML private ImageView imageView;
    @FXML private Label saveOutcome;

    //for remembering where the image came from for the save button.
    private File imgFile;


    //methods
    @FXML
    protected void onMenuClick(){
//        testing that was used to make sure the image data types I used would work for painting later on.
        PixelWriter pixelWriter = image.getPixelWriter();

        pixelWriter.setColor(10, 10, Color.AQUA);
        pixelWriter.setColor(11, 10, Color.AQUA);
        pixelWriter.setColor(12, 10, Color.AQUA);
        pixelWriter.setColor(13, 10, Color.AQUA);
        pixelWriter.setColor(14, 10, Color.AQUA);
        pixelWriter.setColor(10, 11, Color.AQUA);
        pixelWriter.setColor(11, 11, Color.AQUA);
        pixelWriter.setColor(12, 11, Color.AQUA);
        pixelWriter.setColor(13, 11, Color.AQUA);
        pixelWriter.setColor(14, 11, Color.AQUA);

        saveOutcome.setText("Saved!");
    }//end file click

    @FXML
    protected void onSaveClick(){
        save(imgFile);
    }//end file click

    @FXML
    protected void onSaveAsClick(){
        FileChooser fileChooser = imgChooser();

        save(fileChooser.showSaveDialog(null));
    }//end file click

    @FXML
    protected void onLoadClick(){
        FileChooser fileChooser = imgChooser();
        PixelWriter pixelWriter = image.getPixelWriter();

        imgFile = fileChooser.showOpenDialog(null);

        if (imgFile != null){
            imagePixelate = new Image(imgFile.getAbsoluteFile().toURI().toString());
            image = new WritableImage(imagePixelate.getPixelReader(), (int) imagePixelate.getWidth(), (int) imagePixelate.getHeight());
            imageView.setImage(image);
        }//end if block

    }//end file click

    @FXML
    protected void onToolClick(){
        //stufflater
    }//end file click

    private void save(File file){
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);

        try {
            ImageIO.write(bufferedImage, "png", file);
            //Save fail/success text wasn't working. don't feel like fixing atm.
            // saveOutcome.setText("Saved!");
        } catch (IOException e) {
            //wasn't working. don't feel like fixing atm.
            // saveOutcome.setText("Save Failed");
        }//end try/catch
    }//end save

    private FileChooser imgChooser(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pick an image file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images (*.png), (*.jpg)", "*.png", "*.jpg"));
        return fileChooser;
    }//end imgChooser
}//end PainTControls
