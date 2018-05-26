/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import views.MainFrame;

/**
 * Handles detecting modification of entities and enabling/disabling the Revert
 * and Apply buttons in the info panel. 
 * @author Scott
 */
public class ModifiedController {
    
    private final MainFrame frame;
    private boolean modified = false;
    
    public ModifiedController(MainFrame frame) {
        this.frame = frame;
    }

    public boolean isModified() {
        return modified;
    }
    
    /**
     * Set the isModified boolean, and calls setEnabled() with the passed in 
     * value on the frame's revert and apply buttons.
     */
    public void setModified(boolean modified) {
        this.modified = modified;
        frame.getRevertButton().setEnabled(modified);
        frame.getApplyButton().setEnabled(modified);
    }
    
}
