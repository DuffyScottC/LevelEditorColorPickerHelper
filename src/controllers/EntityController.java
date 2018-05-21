/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import views.MainFrame;

/**
 * Handles the creation of new entities, the modification and deletion of
 * existing entities, and other entity management processes.
 * @author Scott
 */
public class EntityController {
    public EntityController (MainFrame frame) {
        frame.getSelectButton().addActionListener((ActionEvent e) -> {
            //Copy the color code to the clipboard
            String wolfString = frame.getColorCodeTextField().getText();
            StringSelection stringSelection = new StringSelection(wolfString);
            Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
            clpbrd.setContents(stringSelection, null);
        });
    }
}
