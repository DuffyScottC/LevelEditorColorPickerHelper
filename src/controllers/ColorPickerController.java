/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import javax.swing.JCheckBox;
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
public class ColorPickerController {
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
    
    private final JCheckBox includeHashTagCheckBox;
    private boolean includeHashTag = false;
    
    
    public ColorPickerController(MainFrame frame,
            ModifiedController modifiedController) {
        redSlider = frame.getRedSlider();
        greenSlider = frame.getGreenSlider();
        blueSlider = frame.getBlueSlider();
        
        redSpinner = frame.getRedSpinner();
        greenSpinner = frame.getGreenSpinner();
        blueSpinner = frame.getBlueSpinner();
        
        colorPanel = frame.getColorPanel();
        
        colorCodeTextField = frame.getColorCodeTextField();
        
        includeHashTagCheckBox = frame.getIncludeHashTagCheckBox();
        
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
        
        colorCodeTextField.addActionListener((ActionEvent e) -> {
            parseUserHex();
            modifiedController.setModified(true);
        });
        
        includeHashTagCheckBox.addActionListener((ActionEvent e) -> {
            includeHashTag = includeHashTagCheckBox.isSelected();
            updateColorCodeTextField();
        });
        
        frame.getSelectButton().addActionListener((ActionEvent e) -> {
            //Copy the color code to the clipboard
            String wolfString = frame.getColorCodeTextField().getText();
            StringSelection stringSelection = new StringSelection(wolfString);
            Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
            clpbrd.setContents(stringSelection, null);
        });
        
    }
    
    private void updateColorCodeTextField() {
        String hex;
        if (includeHashTag) {
            hex = String.format("#%02X%02X%02X", r, g, b);
        } else {
            hex = String.format("%02X%02X%02X", r, g, b);
        }
        colorCodeTextField.setText(hex);
    }
    
    private void updateColorPanel() {
        colorPanel.setBackground(new Color(r, g, b));
    }
    
    private void parseUserHex() {
        String hex = colorCodeTextField.getText();
        //if the user input hex is completely blank
        if (hex.length() == 0) {
            r = 0;
            g = 0;
            b = 0;
            updateAll();
            return;
        }
        //if we are including hashTags
        if (includeHashTag) {
            //if this has a #
            if (hex.charAt(0) == '#') {
                //remove the #
                hex = hex.substring(1, hex.length());
            }
        }
        //if the hex is not of length 6
        if (hex.length() != 6) {
            //correct the size by adding or removing bits
            if (hex.length() < 6) {
                //if the hex is less than 6 characters long
                //find out how many characters are missing
                int numOfZeros = 6 - hex.length();
                //add on the appropriate number of zeros
                hex += "000000".substring(0,numOfZeros);
            } else if (hex.length() > 6) {
                //if the hex is greater than 6 characters long
                //take the first size characters
                hex = hex.substring(0, 6);
            }
        }
        //At this point, the hex is of the right size
        //if the string is NOT made up of hex characters
        if (!hex.matches("[0-9A-Fa-f]{6}")) {
            //reset the textfield to the old code
            updateColorCodeTextField();
            return;
        }
        //if the string is made up of hex characters
        r = Integer.valueOf( hex.substring( 0, 2 ), 16 );
        g = Integer.valueOf( hex.substring( 2, 4 ), 16 );
        b = Integer.valueOf( hex.substring( 4, 6 ), 16 );
        updateAll();
    }
    
    private void updateAll() {
        redSlider.setValue(r);
        greenSlider.setValue(g);
        blueSlider.setValue(b);
        
        redSpinner.setValue(r);
        greenSpinner.setValue(g);
        blueSlider.setValue(b);
        
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
    public void setColor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
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
        updateAll();
    }
    
    public Color getColor() {
        return new Color(r, g, b);
    }
    
}
