/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.Utils;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.JPanel;

/**
 *
 * @author Scott
 */
public class ImagePanel extends JPanel {
    
    private BufferedImage image = null;
    
    /**
     * Give the image panel a new image path to display the image
     * associated with the selected entity in the side bar.
     * @param imageFile The file containing the image
     * @param color The backup color if there is no image or if the
     * image could not be found.
     */
    public void setImagePath(File imageFile, Color color) {
        //Get the dimension of the square panel that we are drawing to
        //(The panel must be square because that's the only way the
        //algorithm Utils.getBufferedImageFromFile() works right now.)
        int dim = getSize().width;
        
        image = Utils.getBufferedImageFromFile(dim, imageFile, color);
        this.repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            int x = (getWidth() - image.getWidth()) / 2;
            int y = (getHeight() - image.getHeight()) / 2;
            g2d.drawImage(image, x, y, this);
            g2d.dispose();
        }
    }
}
