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
    
    private BufferedImage ret = null;
    
    public void setImagePath(String imagePath) {
        File imageFile = new File(imagePath);
        BufferedImage image = null;
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Get the size of the panel that we are drawing to
        int w = getSize().width;
        int h = getSize().height;
        ret = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        ret.getGraphics().drawImage(image,0,0,w,h,null);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int x = (getWidth() - ret.getWidth()) / 2;
        int y = (getHeight() - ret.getHeight()) / 2;
        g2d.drawImage(ret, x, y, this);
        g2d.dispose();
    }
}
