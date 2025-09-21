package com.pain.t;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.awt.*;
import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;

import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;

/* TODO: keyboard shortcuts (control S for save, etc),
*        tabs
*        */
/*
    file: PainTControls.java
    date: 8/26/2025
    credit: Austin Oh
    description: Controls for paint fxml.
*/

/**
 * Primary controller that handles user input.
 */
public class PainTControls {
    //pain-t-display variables
    @FXML private Image imagePixelate;
    @FXML private WritableImage image;
    @FXML private ImageView imageView;
    @FXML private Spinner<Integer> shapeWidth;
    @FXML private Spinner<Integer> shapeHeight;
    @FXML private Spinner<Integer> penSize;
    @FXML private Label saveOutcome;//does more than handle save outcomes by now, but seems risky to change its name at this point
    @FXML private ColorPicker colorPicker;
    @FXML private MenuItem Save;
    @FXML private MenuItem BasicPencil;
    @FXML private CheckMenuItem outlined;

    //for remembering where the image came from for the save button.
    private File imgFile;

    //currently selected tool for mouse clicks. default on regular pencil tool.
    private int curTool = 0;
    //numbers stored in a variable named after what tool they represent. only use
    //these guys in matters of tools.
    final int pencil = 0;
    final int rectangle = 1;
    final int circle = 2;
    final int triangle = 3;
    final int eraser = 4;
    final int colorGrabber = 5;
    final int joe = 100000;

    /**
     * Initializes buttons with key binds
     */
    @FXML
    private void initialize(){
        Save.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN, KeyCombination.SHORTCUT_DOWN));
        BasicPencil.setAccelerator(new KeyCodeCombination(KeyCode.P, KeyCombination.CONTROL_DOWN, KeyCombination.SHORTCUT_DOWN));
    }// end initialization


    //handlers

    /**
     * handler that determines what the program will do on a mouse click,
     * depending on the currently selected tool
     * @param event
     */
    @FXML
    private void onImageViewClick(MouseEvent event){
        //might switch to case tree?
        if (curTool == pencil){//start of pencil line
            Shapes.circle(image, penSize.getValue(), penSize.getValue(), (int) event.getX(), (int) event.getY(), colorPicker.getValue(), false, penSize.getValue());
        }//end pencil if
        else if (curTool == eraser) {//start of eraser line
            Shapes.circle(image, penSize.getValue(), penSize.getValue(), (int) event.getX(), (int) event.getY(), Color.TRANSPARENT, false, penSize.getValue());
        }//end eraser if
        else if (curTool == rectangle){
            Shapes.rectangle(image, shapeWidth.getValue(), shapeHeight.getValue(), (int) event.getX(), (int) event.getY(), colorPicker.getValue(), outlined.isSelected(), penSize.getValue());
        }//end rect if
        else if (curTool == circle) {
            Shapes.circle(image, shapeWidth.getValue(), shapeHeight.getValue(), (int) event.getX(), (int) event.getY(), colorPicker.getValue(), outlined.isSelected(), penSize.getValue());
        }//end circle if
        else if (curTool == triangle) {
            Shapes.triangle(image, shapeWidth.getValue(), shapeHeight.getValue(), (int) event.getX(), (int) event.getY(), colorPicker.getValue(), outlined.isSelected(), penSize.getValue());
        }//end triangle if
        //gets the image's pixel reader and then gets the color at the clicked spot, setting the color picker to that color
        else if (curTool == colorGrabber) {
            colorPicker.setValue(image.getPixelReader().getColor((int) event.getX(), (int) event.getY()));
        }//end color grabber if
        else if (curTool == joe) {
            Shapes.secret(image, shapeWidth.getValue(), shapeHeight.getValue(), (int) event.getX(), (int) event.getY());
        }//end joe?
    }//end image view click

    /**
     * handler that determines what the program will do on a mouse and drag,
     * depending on the currently selected tool
     * @param event
     */
    @FXML
    private void onImageViewMouseDrag(MouseEvent event){
        if (curTool == pencil){//continued pencil line
            Shapes.circle(image, penSize.getValue(), penSize.getValue(), (int) event.getX(), (int) event.getY(), colorPicker.getValue(), false, penSize.getValue());
        }//end pencil if
        else if (curTool == eraser) {//continued eraser line
            Shapes.circle(image, penSize.getValue(), penSize.getValue(), (int) event.getX(), (int) event.getY(), Color.TRANSPARENT, false, penSize.getValue());
        }//end eraser if
        //gets the image's pixel reader and then gets the color at the dragged over spot, setting the color picker to that color
        else if (curTool == colorGrabber) {
            colorPicker.setValue(image.getPixelReader().getColor((int) event.getX(), (int) event.getY()));
        }//end color grabber if
    }//end image view mouse drag

    //pencil items
    /**
     * Changes the selected tool to 0, which is a pencil tool
     */
    @FXML
    protected void onBasicClick(){
        curTool = pencil;
        saveOutcome.setText("tool set to pencil");
    }//end rectangle click
    /**
     * Changes the selected tool to 0, which is a pencil tool
     */
    @FXML
    protected void onEraserClick(){
        curTool = eraser;
        saveOutcome.setText("tool set to eraser");
    }//end rectangle click


    //shape menu items. Contains various shape creator tools.

    /**
     * Changes the selected tool to 1, which is a rectangle
     */
    @FXML
    protected void onRectangleClick(){
        curTool = rectangle;
        saveOutcome.setText("tool set to rectangle");
    }//end rectangle click

    /**
     * Changes the selected tool to 2, which is a circle
     */
    @FXML
    protected void onCircleClick(){
        curTool = circle;
        saveOutcome.setText("tool set to circle");
    }//end circle click

    /**
     * Changes the selected tool to 3, which is a triangle
     */
    @FXML
    protected void onTriangleClick(){
        curTool = triangle;
        saveOutcome.setText("tool set to triangle");
    }//end triangle click

    /**
     * Changes the selected tool to 5, which is the color grabber
     */
    @FXML
    protected void onColorGrabberClick(){
        curTool = colorGrabber;
        saveOutcome.setText("tool set to color grabber");
    }//end triangle click

    @FXML
    protected void onPenSpinnerClick(MouseEvent event){
        saveOutcome.setText("Pixel size set to " + penSize.getValue());
    }//end spinner click

    /**
     * Changes the selected tool to 100000, which is a cup 'o joe
     */
    @FXML
    protected void onSecretClick(){
        curTool = joe;
        saveOutcome.setText("Set tool to @$#%*");
    }//end file click

    //File menu items. Contains things for saving and loading files.

    /**
     * passes the currently loaded image and its file to the <code>save</code> method.
     * Updates <code>saveOutcome</code>'s text to display the outcome of the save,
     * catching any exceptions in the process.
     */
    @FXML
    protected void onSaveClick(){
        try {
            save(imgFile, image);
            saveOutcome.setText("Saved!");
        } catch (IOException exception){
            saveOutcome.setText(exception.getMessage());
        }//end try catch
    }//end save click

    /**
     * opens a file picker filtered for png, jpg, and bmp images
     * and passes the chosen file and <code>image</code>
     * to the <code>save</code> function. Updates <code>saveOutcome</code>'s text
     * to display the outcome of the save, catching any exceptions in the process.
     */
    @FXML
    protected void onSaveAsClick(){
        FileChooser fileChooser = Utilities.imgChooser();

        try {
            save(fileChooser.showSaveDialog(null), image);
            saveOutcome.setText("Saved!");
        } catch (IOException exception){
            saveOutcome.setText(exception.getMessage());
        }//end try catch
    }//end save as click

    /**
     * displays a file picker prompt filtered for images and loads the chosen
     * image to the imageview. does nothing if canceled.
     */
    @FXML
    protected void onLoadClick(){
        FileChooser fileChooser = Utilities.imgChooser();

        imgFile = fileChooser.showOpenDialog(null);

        if (imgFile != null){
            imagePixelate = new Image(imgFile.getAbsoluteFile().toURI().toString());
            image = new WritableImage(imagePixelate.getPixelReader(), (int) imagePixelate.getWidth(), (int) imagePixelate.getHeight());
            imageView.setImage(image);
        }//end if block
    }//end file click

    /**
     * Takes a <code>WritableImage</code> and saves it in the spot of the given <code>File</code>
     * @param file  Spot to save the image
     * @param image Image to be saved
     * @throws IOException Throws if the save was canceled or interrupted
     */
    private static void save(File file, WritableImage image) throws IOException{
        if (file != null) {
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);

            ImageIO.write(bufferedImage, "png", file);
        }//end null file check
        else{throw new IOException("No save location chosen");}//Save canceled or no previous file, unless something weird happens.
    }//end save

    //Help menu items. Contains information like tool tips and about.

    /**
     * Driver for <code>HelpMenu</code>'s <code>about</code> method
     */
    @FXML
    protected void onAboutClick(){
        HelpMenu.about();
    }//end about click

    /**
     * Driver for <code>HelpMenu</code>'s <code>tips</code> method
     */
    @FXML
    protected void onTipsClick() {
        HelpMenu.tips();
    }//end tips click

    //Zoom menu items. controls image zoom

    /**
     * Driver for <code>Zoom</code>'s <code>in</code> method that increases the size
     * of <code>image</code>
     */
    @FXML
    private void onZoomIn(){
        Zoom.in(imageView);
    }//end on zoom

    /**
     * Driver for <code>Zoom</code>'s <code>out</code> method that decreases the size
     * of <code>image</code>
     */
    @FXML
    private void onZoomOut(){
        Zoom.out(imageView);
    }//end on zoom
}//end PainTControls
