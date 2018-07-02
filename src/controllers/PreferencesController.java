/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import views.MainFrame;
import views.PreferencesDialog;

/**
 *
 * @author Scott
 */
public class PreferencesController {
    
    private static boolean includeHashTag = false;
    private static boolean includeAlpha = false;
    private static boolean includeOffset = false;
    
    /**
     * Creates a new PreferencesDialog window and displays it to the user.
     * @param frame The frame that the PreferencesDialog should appear relative
     * to.
     */
    public static void showPreferencesWindow(MainFrame frame) {
        PreferencesDialog preferencesDialog = new PreferencesDialog(frame, true);
        
        //set up the checkboxes to reflect the current state
        preferencesDialog.getHashCheckBox().setSelected(includeAlpha);
        preferencesDialog.getAlphaCheckBox().setSelected(includeHashTag);
        preferencesDialog.getOffsetCheckBox().setSelected(includeOffset);
        
        //add actionlisteners to edit the 
        preferencesDialog.getHashCheckBox().addActionListener((ActionEvent e) -> {
            includeHashTag = preferencesDialog.getHashCheckBox().isSelected();
        });
        
        preferencesDialog.getAlphaCheckBox().addActionListener((ActionEvent e) -> {
            includeAlpha = preferencesDialog.getAlphaCheckBox().isSelected();
        });
        
        preferencesDialog.getOffsetCheckBox().addActionListener((ActionEvent e) -> {
            includeOffset = preferencesDialog.getOffsetCheckBox().isSelected();
        });
        
        preferencesDialog.getCancelButton().addActionListener((ActionEvent e) -> {
            preferencesDialog.setVisible(false);
        });
        
        preferencesDialog.setVisible(true);
    }
    
}
