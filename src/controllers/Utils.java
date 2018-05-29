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
                    BufferedImage tempImage = ImageIO.read(imageFile);
                    resultImage = new BufferedImage(width, height,
                            BufferedImage.TYPE_INT_ARGB);
                    resultImage.getGraphics().drawRect(0, 0, width, height);
                    resultImage.getGraphics().drawImage(tempImage, 
                            0, 0, width, height, null);
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
