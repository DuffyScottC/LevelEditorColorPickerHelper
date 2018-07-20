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
import javax.swing.JPanel;

/**
 *
 * @author Scott
 */
public class ColorPanel extends JPanel {
    
    private Color color = Color.black;
    private final int dim = 14;
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (color == null) {
            return;
        }
        
        Graphics2D g2 = (Graphics2D) g;
        
        Utils.drawCheckeredPattern(
                g2, color, getSize().width, getSize().height, dim);
        
        //draw the color
        g2.setColor(color);
        g2.fillRect(0, 0, getSize().width, getSize().height);
    }

    public void setColor(Color color) {
        this.color = color;
        repaint();
    }
    
}
