/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author Scott
 */
public class Utils {
    public static final String DEFAULT_TYPE = "Misc";
    public static final int LIST_ICON_DIM = 32;
    
    public static BufferedImage getBlankBufferedImage(int width, int height, Color color) {
        //Create a blank white image icon
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        g2d.setColor(color);
        g2d.fillRect(0, 0, width, height);
        return bi;
    }
    
    /**
     * Fits the given image to the given height and width so that the image is
     * the maximum size while still showing all of the image. Note: Right now,
     * this algorithm only works for square displays, but the image can be any 
     * dimension (square, or rectangle of north-south or east-west orientation).
     * 
     * @param dim
     * @param imageFile The file containing the image that you would like
     * to draw onto the desired BufferedImage (or null if you would just like
     * to use the color input)
     * @param color The color that the BufferedImage will be if null is passed
     * in for the imageFile or if the imageFile could not be found or read.
     * @return A BufferedImage of the passed in height and width with the
     * imageFile drawn onto it.
     */
    public static BufferedImage getBufferedImageFromFile(
            int dim, File imageFile, Color color) {
        int width = dim;
        int height = dim;
        BufferedImage resultImage = null;
        boolean noImage = false;
        //if the imageFile is not null
        if (imageFile != null) {
            //if the image exists
            if (imageFile.exists()) {
                try {
                    /*
                    For commenting purposes, when I say "display" I mean the
                    thing we're drawing on that has the passed in width and
                    height above. This is likely an ImagePanel or an ImageIcon.
                    */
                    BufferedImage tempImage = ImageIO.read(imageFile);
                    
                    // Fit the image to the display:
                    //get the width and height of the actual image
                    int tempWidth = tempImage.getWidth();
                    int tempHeight = tempImage.getHeight();
                    
                    //initialize the variables to the ideal (when the actual
                    //image is a square and there is no distance from the side
                    //or top.
                    int newWidth = width;
                    int newHeight = height;
                    int distFromSide = 0;
                    int distFromTop = 0;
                    
                    //decide what configuration the original image is in
                    if (tempHeight > tempWidth) {
                        //if the image is taller than it is wide
                        newWidth = height * tempWidth / tempHeight;
                        distFromSide = (width - newWidth) / 2;
                    } else if (tempHeight < tempWidth) {
                        //if the image is shorter than it is wide
                        newHeight = tempHeight * width / tempWidth;
                        distFromTop = (height - newHeight) / 2;
                    }
                    //if the image is a square like the display, then we don't
                    //have to do any calculations and we can keep the variables
                    //as they were initialized above.
                    
                    //create a BufferedImage the size of the display (ImagePanel
                    //or ImageIcon)
                    resultImage = new BufferedImage(width, height,
                            BufferedImage.TYPE_INT_ARGB);
                    //Draw the tempImage with the distance from the side and top
                    //and the new width and height calculated above
                    resultImage.getGraphics().drawImage(tempImage, 
                            distFromSide, distFromTop, 
                            newWidth, newHeight, null);
                } catch (IOException ex) {
                    System.err.println("Could not read file:\n"
                            + imageFile.toString());
                    noImage = true;
                }
            } else {
                //if the image does not exist
                System.err.println("Could not find image at\n" 
                        + imageFile.toString() + 
                        "\nFile does not exist.");
                noImage = true;
            }
        } else {
            noImage = true;
        }
        
        if (noImage) {
            //use the backup color passed in
            resultImage = getBlankBufferedImage(width, height, color);
        }
        return resultImage;
    }
    
    /**
     * Convenience method that asks a user if they want to continue
     * 
     * @param message
     * @param parent
     * @return true if the process should continue, false if you should stop
     * the process
     */
    public static boolean shouldContinue(String message, Component parent) {
        //ask the user if they want to continue
        int selection = JOptionPane.showConfirmDialog(parent, message);
        //if the user did not choose "yes", then we should cancel the operation
        //if the user did choose yes, then we should continue the operation
        //if the file has been saved, then we can just return true
        return selection == JOptionPane.YES_OPTION;
    }
    
}
