/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

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
    private final int dim = 16;
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (color == null) {
            return;
        }
        
        Graphics2D g2 = (Graphics2D) g;
        
        //draw a checkered pattern
        boolean alt = true;
//        int maxX = getSize().width * dim;
//        int maxY = getSize().height * dim;
        for (int x = 0; x <= getSize().width; x += dim) {
            for (int y = 0; y <= getSize().height; y += dim) {
                if (alt) { //white
                    g2.setColor(Color.white);
                } else { //grey
                    g2.setColor(Color.lightGray);
                }
                g2.fillRect(x, y, dim, dim);
                alt = !alt;
            }
            alt = !alt;
        }
        
        //draw color
        
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
}
