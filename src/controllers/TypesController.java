/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import views.MainFrame;
import views.ReplaceTypeDialog;
import views.TypesDialog;

/**
 *
 * @author Scott
 */
public class TypesController {
    
    private final MainFrame frame;
    private final TypesDialog typesDialog;
    private final ReplaceTypeDialog replaceTypeDialog;
    
    private final DefaultListModel editTypesListModel = new DefaultListModel();
    /**
     * Gets updated to reflect the types of the current project every type
     * showTypesDialog() is called.
     */
    private List<String> types;
    /**
     * Used to hold types as the user adds/removes them in the dialog. If the
     * user presses "cancel", this is not used. If the user presses "OK",
     * then this is used to replace the currentProject's "types" ArrayList.
     */
    private final List<String> tempTypes = new ArrayList();
    /**
     * When we are done, we must replace every Entity's typeIndex that was
     * was removed with the designated replace typeIndex. You can use the
     * Entity's current typeIndex as the key and the value for that key
     * as the replacement.
     */
    private final Map<Integer, Integer> replacePairs = new HashMap();
    
    public TypesController (MainFrame frame) {
        this.frame = frame;
        typesDialog = new TypesDialog(frame, true);
        setUpTypesDialogActionListeners();
        replaceTypeDialog = new ReplaceTypeDialog(frame, true);
        setUpReplaceTypeDialogActionListeners();
        
        JList typesList = typesDialog.getTypesList();
        typesList.setModel(editTypesListModel);
        typesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    /**
     * Updates the typeComboBox to reflect the current open project's types
     * @param types The list of all types in the project
     */
    public void updateTypeComboBox(List<String> types) {
        JComboBox typeComboBox = frame.getTypeComboBox();
        //remove all items in the type combo box
        typeComboBox.removeAllItems();
        for (String type : types) {
            typeComboBox.addItem(type);
        }
        //the last item should be "Edit..."
        typeComboBox.addItem(Utils.EDIT_TYPES_ITEM);
    }
    
    private void setUpReplaceTypeDialogActionListeners() {
        
    }
    
    private void showReplaceTypeDialog() {
        JComboBox rtComboBox = replaceTypeDialog.getReplaceTypeComboBox();
        //clear the items
        rtComboBox.removeAllItems();
        //loop through all the temp types
        for (String type : tempTypes) {
            //add each tempType
            rtComboBox.addItem(type);
        }
        replaceTypeDialog.setVisible(true);
    }
    
    private void setUpTypesDialogActionListeners() {
        typesDialog.getTypesList().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = typesDialog.getTypesList().getSelectedIndex();
                //if no type was selected or if Misc was selected
                if (index == -1 || index == 0) {
                    //the user can't remove Misc, and they can't remove nothing
                    typesDialog.getRemoveButton().setEnabled(false);
                } else {
                    //allow the user to remove this
                    typesDialog.getRemoveButton().setEnabled(true);
                }
            }
        });
        
        typesDialog.getAddButton().addActionListener((ActionEvent e) -> {
            String newType = typesDialog.getNewTypeTextField().getText();
            //if the new type is not empty
            if (!newType.isEmpty()) {
                //add the new type to the end of the list
                tempTypes.add(newType);
                //update the visual list
                updateListModel();
            }
        });
        
        typesDialog.getRemoveButton().addActionListener((ActionEvent e) -> {
            showReplaceTypeDialog();
        });
        
        typesDialog.getNewTypeTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                //if there is no type written
                if (typesDialog.getNewTypeTextField().getText().isEmpty()) {
                    //the user can't add an empty type
                    typesDialog.getAddButton().setEnabled(false);
                }
            }
        });
        
        typesDialog.getOkButton().addActionListener((ActionEvent e) -> {
            typesDialog.setVisible(false);
        });
        
        typesDialog.getCancelButton().addActionListener((ActionEvent e) -> {
            typesDialog.setVisible(false);
        });
    }
    
    /**
     * Shows the dialog with the current types in the project
     * @param types The list of all types in the project
     */
    public void showTypesDialog(List<String> types) {
        this.types = types;
        
        //fill out the tempTypes ArrayList with a copy of "types"
        tempTypes.clear();
        for (String type : types) {
            tempTypes.add(type);
        }
        
        //update the visual list
        updateListModel();
        //clear the selection (if there was one)
        typesDialog.getTypesList().clearSelection();
        
        //clear the textbox
        typesDialog.getNewTypeTextField().setText("");
        
        //disable the buttons
        typesDialog.getAddButton().setEnabled(false);
        typesDialog.getRemoveButton().setEnabled(false);
        
        //show the dialog
        typesDialog.setVisible(true);
    }
    
    private void updateListModel() {
        editTypesListModel.clear();
        for (String e : types) {
            editTypesListModel.addElement(e);
        }
        typesDialog.getTypesList().repaint();
    }
    
}
