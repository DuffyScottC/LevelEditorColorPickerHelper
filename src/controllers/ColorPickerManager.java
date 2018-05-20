/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Color;
import java.text.ParseException;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import views.MainFrame;

/**
 * This manages the color picker section of the info panel in the main frame.
 * @author Scott
 */
public class ColorPickerManager {
    private int r = 0;
    private int g = 0;
    private int b = 0;
    
    private final JSlider redSlider;
    private final JSlider greenSlider;
    private final JSlider blueSlider;
    
    private final JSpinner redSpinner;
    private final JSpinner greenSpinner;
    private final JSpinner blueSpinner;
    
    private final JPanel colorPanel;
    
    private final JTextField colorCodeTextField;
    
    public ColorPickerManager(MainFrame frame) {
        redSlider = frame.getRedSlider();
        greenSlider = frame.getGreenSlider();
        blueSlider = frame.getBlueSlider();
        
        redSpinner = frame.getRedSpinner();
        greenSpinner = frame.getRedSpinner();
        blueSpinner = frame.getRedSpinner();
        
        colorPanel = frame.getColorPanel();
        
        colorCodeTextField = frame.getColorCodeTextField();
        
        redSlider.addChangeListener((ChangeEvent e) -> {
            r = redSlider.getValue();
            updateColorCodeTextField();
            updateColorPanel();
            redSpinner.setValue(r);
        });
        
        redSpinner.addChangeListener((ChangeEvent e) -> {
            //"ensure manually typed values with the editor 
            //are propagated to the model"
            try {
                //add manually typed value to the model
                redSpinner.commitEdit();
            } catch (ParseException ex ) {
                //if the manually typed value is not formatted properly
                System.err.println(ex.toString());
                //reset the spinner to the previous r value
                redSpinner.setValue(r);
            }
            //get the spinner value
            int val = (Integer) redSpinner.getValue();
            //if val is at least 0
            if (0 <= val) {
                if (val <= 255) {
                    //set r to the value in the spinner
                    r = val;
                } else {
                    //if val is greater than 255
                    r = 255;
                    redSpinner.setValue(255);
                }
            } else {
                //if val is less than 0
                r = 0;
                redSpinner.setValue(0);
            }
            updateColorCodeTextField();
            updateColorPanel();
            redSlider.setValue(r);
        });
        
        greenSlider.addChangeListener((ChangeEvent e) -> {
            g = greenSlider.getValue();
            updateColorCodeTextField();
            updateColorPanel();
            greenSpinner.setValue(r);
        });
        
        blueSlider.addChangeListener((ChangeEvent e) -> {
            b = blueSlider.getValue();
            updateColorCodeTextField();
            updateColorPanel();
            blueSpinner.setValue(r);
        });
        
        
    }
    
    private void updateColorCodeTextField() {
        String hex = String.format("%02X%02X%02X", r, g, b);
        colorCodeTextField.setText(hex);
    }
    
    private void updateColorPanel() {
        colorPanel.setBackground(new Color(r, g, b));
    }
    
}
