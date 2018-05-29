/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Color;
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
    public static final String defaultType = "Misc";
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
     * 
     * @param width The width of the desired BufferedImage
     * @param height The height of the desired BufferedImage
     * @param imageFile The file containing the image that you would like
     * to draw onto the desired BufferedImage (or null if you would just like
     * to use the color input)
     * @param color The color that the BufferedImage will be if null is passed
     * in for the imageFile or if the imageFile could not be found or read.
     * @return A BufferedImage of the passed in height and width with the
     * imageFile drawn onto it.
     */
    public static BufferedImage getBufferedImageFromFile(
            int width, int height, File imageFile, Color color) {
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
                            BufferedImage.TYPE_INT_RGB);
                    //Draw the tempImage with the distance from the side and top
                    //and the new width and height calculated above
                    resultImage.getGraphics().drawImage(tempImage, 
                            distFromSide, distFromTop, 
                            newWidth, newHeight, null);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, 
                            "Could not read file:\n"
                            + imageFile.toString(), "Warning", 
                            JOptionPane.WARNING_MESSAGE);
                }
            } else {
                //if the image does not exist
                JOptionPane.showMessageDialog(null,
                        "Could not find image at\n" + imageFile + 
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
}
