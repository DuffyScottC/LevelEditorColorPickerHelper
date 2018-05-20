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
        greenSpinner = frame.getGreenSpinner();
        blueSpinner = frame.getBlueSpinner();
        
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
            greenSpinner.setValue(g);
        });
        
        greenSpinner.addChangeListener((ChangeEvent e) -> {
            //"ensure manually typed values with the editor 
            //are propagated to the model"
            try {
                //add manually typed value to the model
                greenSpinner.commitEdit();
            } catch (ParseException ex ) {
                //if the manually typed value is not formatted properly
                System.err.println(ex.toString());
                //reset the spinner to the previous r value
                greenSpinner.setValue(g);
            }
            //get the spinner value
            int val = (Integer) greenSpinner.getValue();
            //if val is at least 0
            if (0 <= val) {
                if (val <= 255) {
                    //set r to the value in the spinner
                    g = val;
                } else {
                    //if val is greater than 255
                    g = 255;
                    greenSpinner.setValue(255);
                }
            } else {
                //if val is less than 0
                g = 0;
                greenSpinner.setValue(0);
            }
            updateColorCodeTextField();
            updateColorPanel();
            greenSlider.setValue(g);
        });
        
        blueSlider.addChangeListener((ChangeEvent e) -> {
            b = blueSlider.getValue();
            updateColorCodeTextField();
            updateColorPanel();
            blueSpinner.setValue(b);
        });
        
        blueSpinner.addChangeListener((ChangeEvent e) -> {
            //"ensure manually typed values with the editor 
            //are propagated to the model"
            try {
                //add manually typed value to the model
                blueSpinner.commitEdit();
            } catch (ParseException ex ) {
                //if the manually typed value is not formatted properly
                System.err.println(ex.toString());
                //reset the spinner to the previous r value
                blueSpinner.setValue(b);
            }
            //get the spinner value
            int val = (Integer) blueSpinner.getValue();
            //if val is at least 0
            if (0 <= val) {
                if (val <= 255) {
                    //set r to the value in the spinner
                    b = val;
                } else {
                    //if val is greater than 255
                    b = 255;
                    blueSpinner.setValue(255);
                }
            } else {
                //if val is less than 0
                b = 0;
                blueSpinner.setValue(0);
            }
            updateColorCodeTextField();
            updateColorPanel();
            blueSlider.setValue(b);
        });
        
    }
    
    private void updateColorCodeTextField() {
        String hex = String.format("%02X%02X%02X", r, g, b);
        colorCodeTextField.setText(hex);
    }
    
    private void updateColorPanel() {
        colorPanel.setBackground(new Color(r, g, b));
    }
    
    private void parseUserHex() {
        String hex = colorCodeTextField.getText();
        //if this is not of the hex format
        if (hex.matches("[0-9A-Fa-f]{0,6}")) {
            //convert the input hex value to 16 bits
            int value = Integer.valueOf(hex, 16);
            //if it's between 0 and 0xFFFFFF
            if (0 <= value) {
                if (value <= 16777215) {
                    r = Integer.valueOf( hex.substring( 1, 3 ), 16 );
                    g = Integer.valueOf( hex.substring( 3, 5 ), 16 );
                    b = Integer.valueOf( hex.substring( 5, 7 ), 16 );
                } else {
                    r = 255;
                    g = 255;
                    b = 255;
                }
            } else {
                r = 0;
                g = 0;
                b = 0;
            }
        } else {
            //reset the color code to the old value
            updateColorCodeTextField();
            return;
        }
        updateEverything();
    }
    
}
