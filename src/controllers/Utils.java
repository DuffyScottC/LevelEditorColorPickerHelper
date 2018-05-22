/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Scott
 */
public class Utils {
    public static BufferedImage getBlankImageIcon(int width, int height) {
        //Create a blank white image icon
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        g2d.setColor(Color.white);
        g2d.drawRect(0, 0, width, height);
        return bi;
    }
}
