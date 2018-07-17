/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import views.ColorPanel;
import views.MainFrame;

/**
 * This manages the color picker section of the info panel in the main frame.
 * @author Scott
 */
public class ColorPickerController {
    private int r = 0;
    private int g = 0;
    private int b = 0;
    private int a = 255;
    
    private final JSlider redSlider;
    private final JSlider greenSlider;
    private final JSlider blueSlider;
    private final JSlider alphaSlider;
    
    private final JSpinner redSpinner;
    private final JSpinner greenSpinner;
    private final JSpinner blueSpinner;
    private final JSpinner alphaSpinner;
    
    private final ColorPanel colorPanel;
    
    private final JTextField colorCodeTextField;
    
    private boolean includeHashTag = false;
    private boolean includeAlpha = true;
    
    
    public ColorPickerController(MainFrame frame,
            ModifiedController modifiedController) {
        redSlider = frame.getRedSlider();
        greenSlider = frame.getGreenSlider();
        blueSlider = frame.getBlueSlider();
        alphaSlider = frame.getAlphaSlider();
        
        redSpinner = frame.getRedSpinner();
        greenSpinner = frame.getGreenSpinner();
        blueSpinner = frame.getBlueSpinner();
        alphaSpinner = frame.getAlphaSpinner();
        
        colorPanel = frame.getColorPanel();
        
        colorCodeTextField = frame.getColorCodeTextField();
        
        redSlider.addChangeListener((ChangeEvent e) -> {
            r = redSlider.getValue();
            updateColorCodeTextField();
            updateColorPanel();
            redSpinner.setValue(r);
            modifiedController.setModified(true);
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
            modifiedController.setModified(true);
        });
        
        greenSlider.addChangeListener((ChangeEvent e) -> {
            g = greenSlider.getValue();
            updateColorCodeTextField();
            updateColorPanel();
            greenSpinner.setValue(g);
            modifiedController.setModified(true);
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
            modifiedController.setModified(true);
        });
        
        blueSlider.addChangeListener((ChangeEvent e) -> {
            b = blueSlider.getValue();
            updateColorCodeTextField();
            updateColorPanel();
            blueSpinner.setValue(b);
            modifiedController.setModified(true);
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
            modifiedController.setModified(true);
        });
        
        alphaSlider.addChangeListener((ChangeEvent e) -> {
            a = alphaSlider.getValue();
            updateColorCodeTextField();
            updateColorPanel();
            alphaSpinner.setValue(a);
            modifiedController.setModified(true);
        });
        
        alphaSpinner.addChangeListener((ChangeEvent e) -> {
            //"ensure manually typed values with the editor 
            //are propagated to the model"
            try {
                //add manually typed value to the model
                alphaSpinner.commitEdit();
            } catch (ParseException ex ) {
                //if the manually typed value is not formatted properly
                System.err.println(ex.toString());
                //reset the spinner to the previous r value
                alphaSpinner.setValue(a);
            }
            //get the spinner value
            int val = (Integer) alphaSpinner.getValue();
            //if val is at least 1
            if (1 <= val) {
                if (val <= 255) {
                    //set r to the value in the spinner
                    a = val;
                } else {
                    //if val is greater than 255
                    a = 255;
                    alphaSpinner.setValue(255);
                }
            } else {
                //if val is less than 1
                a = 1;
                alphaSpinner.setValue(1);
            }
            updateColorCodeTextField();
            updateColorPanel();
            alphaSlider.setValue(a);
            modifiedController.setModified(true);
        });
        
        colorCodeTextField.addActionListener((ActionEvent e) -> {
            parseUserHex();
            modifiedController.setModified(true);
        });
        
    }
    
    private void updateColorCodeTextField() {
        String hex;
        if (includeHashTag) {
            if (includeAlpha) {
                hex = String.format("#%02X%02X%02X%02X", r, g, b, a);
            } else {
                hex = String.format("#%02X%02X%02X", r, g, b);
            }
        } else {
            if (includeAlpha) {
                hex = String.format("%02X%02X%02X%02X", r, g, b, a);
            } else {
                hex = String.format("%02X%02X%02X", r, g, b);
            }
        }
        colorCodeTextField.setText(hex);
    }
    
    private void updateColorPanel() {
        colorPanel.setColor(new Color(r, g, b, a));
    }
    
    private void parseUserHex() {
        String hex = colorCodeTextField.getText();
        //if the user input hex is completely blank
        if (hex.length() == 0) {
            r = 0;
            g = 0;
            b = 0;
            a = 0;
            updateAll();
            return;
        }
        //if we are including hashTags
        if (includeHashTag) {
            //if this has a #
            if (hex.charAt(0) == '#') {
                //remove the #
                hex = hex.substring(1, hex.length());
                //if the only thing entered was the #
                if (hex.length() == 0) {
                    r = 0;
                    g = 0;
                    b = 0;
                    a = 0;
                    updateAll();
                    return;
                }
            }
        }
        
        int properLength = 6;
        String zeros = "000000";
        if (includeAlpha) {
            properLength = 8;
            zeros = "00000000";
        }
        //if the hex is not of length properLength
        if (hex.length() != properLength) {
            //correct the size by adding or removing bits
            if (hex.length() < properLength) {
                //if the hex is less than properLength characters long
                //find out how many characters are missing
                int numOfZeros = properLength - hex.length();
                //add on the appropriate number of zeros
                hex += zeros.substring(0,numOfZeros);
            } else if (hex.length() > properLength) {
                //if the hex is greater than properLength characters long
                //take the first size characters
                hex = hex.substring(0, properLength);
            }
        }
        //At this point, the hex is of the right size
        //if the string is NOT made up of hex characters
        if (!hex.matches("[0-9A-Fa-f]{" + properLength + "}")) {
            //reset the textfield to the old code
            updateColorCodeTextField();
            return;
        }
        //if the string is made up of hex characters
        r = Integer.valueOf( hex.substring( 0, 2 ), 16 );
        g = Integer.valueOf( hex.substring( 2, 4 ), 16 );
        b = Integer.valueOf( hex.substring( 4, 6 ), 16 );
        if (includeAlpha) {
            a = Integer.valueOf( hex.substring( 7, 8 ), 16);
        }
        updateAll();
    }
    
    private void updateAll() {
        redSlider.setValue(r);
        greenSlider.setValue(g);
        blueSlider.setValue(b);
        alphaSlider.setValue(a);
        
        redSpinner.setValue(r);
        greenSpinner.setValue(g);
        blueSpinner.setValue(b);
        alphaSpinner.setValue(a);
        
        updateColorPanel();
        updateColorCodeTextField();
    }
    
    /**
     * Sets the r, g, b values of the color and updates the UI to reflect the
     * new color.
     * @param r
     * @param g
     * @param b 
     */
    public void setColor(int r, int g, int b, int a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
        updateAll();
    }
    
    /**
     * Sets the r, g, b values of the color using the r, g, b of the passed color 
     * and updates the UI to reflect the new color.
     * @param color 
     */
    public void setColor(Color color) {
        r = color.getRed();
        g = color.getGreen();
        b = color.getBlue();
        a = color.getAlpha();
        updateAll();
    }
    
    public Color getColor() {
        return new Color(r, g, b, a);
    }
    
    public boolean includeHashTag() {
        return includeHashTag;
    }
    
    public void setIncludeHashTag(boolean includeHashTag) {
        this.includeHashTag = includeHashTag;
        updateColorCodeTextField();
    }

    public boolean includeAlpha() {
        return includeAlpha;
    }
    
    public void setIncludeAlpha(boolean includeAlpha) {
        this.includeAlpha = includeAlpha;
        updateColorCodeTextField();
    }
    
}
