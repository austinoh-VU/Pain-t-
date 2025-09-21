package com.pain.t;

import javafx.scene.image.ImageView;

/**
 * Handles image zoom for the Zoom menu
 */
public class Zoom {
    /**
     * Increases the size of an <code>ImageView</code>
     * @param imageView The image display to be increased in size
     */
    protected static void in(ImageView imageView){
        imageView.setFitWidth(imageView.getFitWidth() + 100);
    }//end zoom in

    /**
     * Decreases the size of an <code>ImageView</code>
     * @param imageView The image display to be decreased in size
     */
    protected static void out(ImageView imageView){
        imageView.setFitWidth(imageView.getFitWidth() - 100);
    }//end zoom out
}//end zoom
