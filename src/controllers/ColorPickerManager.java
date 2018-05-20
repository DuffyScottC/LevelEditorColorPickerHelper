/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import views.MainFrame;

/**
 * This manages the color picker section of the info panel in the main frame.
 * @author Scott
 */
public class ColorPickerManager {
    
    private final JSpinner redSpinner;
    private final JSpinner greenSpinner;
    private final JSpinner blueSpinner;
    
    private final JSlider redSlider;
    private final JSlider greenSlider;
    private final JSlider blueSlider;
    
    private final JPanel colorPanel;
    
    private final JTextField colorCodeTextField;
    
    public ColorPickerManager(MainFrame frame) {
        redSpinner = frame.getRedSpinner();
        greenSpinner = frame.getRedSpinner();
        blueSpinner = frame.getRedSpinner();
        
        redSlider = frame.getRedSlider();
        greenSlider = frame.getGreenSlider();
        blueSlider = frame.getBlueSlider();
        
        colorPanel = frame.getColorPanel();
        
        colorCodeTextField = frame.getColorCodeTextField();
    }
    
    //String hex = String.format("#%02x%02x%02x", r, g, b);
    
}
