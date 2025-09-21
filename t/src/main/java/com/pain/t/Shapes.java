package com.pain.t;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;


/**
 * Handles shape tool's drawing, width, and style.
 */
public class Shapes {

    /**
     * Draws a rectangle of specified width and height to the specified
     * <code>PixelWriter</code> of a <code>WritableImage</code>, in the chosen color.
     * @param image The <code>Writable Image</code> to be drawn on
     * @param width The width of the rectangle to be drawn
     * @param height The height of the rectangle to be drawn
     * @param x The horizontal location to build the rectangle from
     * @param y The vertical location to build the rectangle from
     * @param color The color to draw the shape in
     * @param outlined Whether to draw the shape as an outline or not
     * @param lineWidth the width of the outline if present
     */
    protected static void rectangle(WritableImage image, double width, double height, int x, int y, Color color, boolean outlined, int lineWidth){
        x = (int) (x - (width/2));
        y = (int) (y - (height/2));

        Shape rectangle = new Rectangle(width, height);

        painter(image, rectangle, width, height, x, y, color, outlined, lineWidth);
    }//end rectangle

    /**
     * Draws a circle of specified width and height to the specified
     * <code>PixelWriter</code> of a <code>WritableImage</code>, in the chosen color.
     * @param image The <code>Writable Image</code> to be drawn on
     * @param width The width of the circle to be drawn
     * @param height The height of the circle to be drawn
     * @param x The horizontal location to build the circle from
     * @param y The vertical location to build the circle from
     * @param color The color to draw the shape in
     * @param outlined Whether to draw the shape as an outline or not
     * @param lineWidth the width of the outline if present
     */
    protected static void circle(WritableImage image, double width, double height, int x, int y, Color color, boolean outlined, int lineWidth){
        x = (int) (x - (width/2));
        y = (int) (y - (height/2));

        Shape ellipse = new Ellipse(width/2, height/2);// divide by two for scaling

        painter(image, ellipse, width, height, x, y, color, outlined, lineWidth);
    }//end circle/ellipse

    /**
     * Draws a triangle of specified width and height to the specified
     * <code>PixelWriter</code> of a <code>WritableImage</code>, in the chosen color.
     * @param image The <code>Writable Image</code> to be drawn on
     * @param width The width of the triangle to be drawn
     * @param height The height of the triangle to be drawn
     * @param x The horizontal location to build the triangle from
     * @param y The vertical location to build the triangle from
     * @param color The color to draw the shape in
     * @param outlined Whether to draw the shape as an outline or not
     * @param lineWidth the width of the outline if present
     */
    protected static void triangle(WritableImage image, double width, double height, int x, int y, Color color, boolean outlined, int lineWidth){
        x = (int) (x - (width/2));
        y = (int) (y - (height/2));

        Shape triangle = new Polygon(x, y, x+width/2, y+height, x-width/2, y+height);// math to move points where needed

        painter(image, triangle, width, height, x, y, color, outlined, lineWidth);
    }//end triangle

    /**
     * Draws a chosen shape of specified width and height to the specified
     * <code>PixelWriter</code> of a <code>WritableImage</code>, in the chosen color.
     * @param image The <code>Writable Image</code> to be drawn on
     * @param width The width of the shape to be drawn
     * @param height The height of the shape to be drawn
     * @param x The horizontal location to build the shape from
     * @param y The vertical location to build the shape from
     * @param color The color to draw the shape in
     * @param outlined Whether to draw the shape as an outline or not
     * @param lineWidth the width of the outline if present
     */
    private static void painter(WritableImage image, Shape shape, double width, double height, int x, int y, Color color, boolean outlined, int lineWidth){
        int i;
        int j;

        WritableImage shapePaste = new WritableImage((int) width, (int) height);

        shape.setFill(color);
        if (outlined){
            shape.getStrokeDashArray().addAll(20.0, 20.0);
            shape.setStrokeLineCap(StrokeLineCap.ROUND);
            shape.setStroke(color);
            shape.setStrokeWidth(lineWidth);
            shape.setFill(Color.TRANSPARENT);
            shape.setStrokeType(StrokeType.INSIDE);
        }//end outline check

        SnapshotParameters snaParam = new SnapshotParameters();
        snaParam.setFill(Color.TRANSPARENT);
        shape.snapshot(snaParam, shapePaste);

        PixelReader pr = shapePaste.getPixelReader();
        PixelWriter pw = image.getPixelWriter();

        for (j = 0; j < height; j++) {
            for (i = 0; i < width; i++) {
                //checks if in bounds
                if ((j+y < image.getHeight() && i+x < image.getWidth() && (j+y >= 0 && i+x >= 0))) {
                    //filters out background pixels
                    if (pr.getColor(i, j).equals(color)) {
                        pw.setColor(i + x, j + y, pr.getColor(i, j));//adds x and y because we need the correct position.
                    }//end if check
                }//end in bounds check
            }//end width layer
        }//end of last layer
    }//end painter

    /**
     * Draws a special shape of specified width and height to the specified
     * <code>PixelWriter</code> of a <code>WritableImage</code>, in a specially picked color.
     * @param image The <code>Writable Image</code> to be drawn on
     * @param width The width of the shape to be drawn
     * @param height The height of the shape to be drawn
     * @param x The horizontal location to build the shape from
     * @param y The vertical location to build the shape from
     */
    protected static void secret(WritableImage image, double width, double height, int x, int y){
        x = (int) (x - (width/2));
        y = (int) (y - (height/2));

        Shape secret = new Polygon(x, y, x-10, y, x-20, y-10, x-15, y+40, x+15, y+40, x+20, y-10, x+10, y);// math to move points where needed

        painter(image, secret, width, height, x, y, Color.SADDLEBROWN, false, 0);
    }//end of secret
}//end Shapes