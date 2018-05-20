/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.Controller;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
     */
    public void setImagePath(File imageFile) {
        BufferedImage newImage = null;
        try {
            newImage = ImageIO.read(imageFile);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Get the size of the panel that we are drawing to
        int w = getSize().width;
        int h = getSize().height;
        image = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        image.getGraphics().drawImage(newImage, 0, 0, w, h, null);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int x = (getWidth() - image.getWidth()) / 2;
        int y = (getHeight() - image.getHeight()) / 2;
        g2d.drawImage(image, x, y, this);
        g2d.dispose();
    }
}
