/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.Utils;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Scott
 */
public class ImagePanel extends JPanel {
    
    private BufferedImage image = null;
    /**
     * Tells the ImagePanel whether to highlight or not, such as when the
     * user rolls over the panel
     */
    private boolean rollover = false;
    
    /**
     * Give the image panel a new image path to display the image
     * associated with the selected entity in the side bar.
     * @param imageFile The file containing the image
     * @param color
     */
    public void setImagePath(File imageFile, Color color) {
        BufferedImage newImage = null;
        //Get the size of the panel that we are drawing to
        int w = getSize().width;
        int h = getSize().height;
        
        boolean noImage = false;
        //if the imageFile is not null
        if (imageFile != null) {
            //if the image exists
            if (imageFile.exists()) {
                try {
                    newImage = ImageIO.read(imageFile);
                    image = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
                    image.getGraphics().drawImage(newImage, 0, 0, w, h, null);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Could not read file:\n"
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
            newImage = Utils.getBlankBufferedImage(w, h, color);
            image = newImage;
        }
        this.repaint();
    }
    
    /**
     * Tells the paint component whether to highlight or not.
     * @param rollover 
     */
    public void setRollover(boolean rollover) {
        this.rollover = rollover;
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
            //if we should highlight
            if (rollover) {
                //highlight the panel
                highlight(g2d);
            }
        } else if (rollover) {
            //if we should highlight
            //get the g2d context
            Graphics2D g2d = (Graphics2D) g.create();
            //highlight the panel
            highlight(g2d);
        }
    }
    
    /**
     * Highlights the panel
     * @param g2d 
     */
    private void highlight(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke((float) 5.0));
        g2d.setColor(Color.CYAN);
        g2d.drawRect(0, 0, getSize().width-1, getSize().height-1);
    }
}
