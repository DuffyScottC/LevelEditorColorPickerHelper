/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Entity;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import projects.Project;
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
     * Gets updated to reflect the types of the current project every time
     * showTypesDialog() is called.
     */
    private List<String> types;
    /**
     * Gets updated to reflect the entities of the current project every time
     * showTypesDialog() is called.
     */
    private List<Entity> entities;
    /**
     * The index to remove when removing a type. Should never be 0
     */
    private int removeIndex = 0;
    
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
        replaceTypeDialog.getOkButton().addActionListener((ActionEvent e) -> {
            //remove the type
            types.remove(removeIndex);
            
            //get the selected type from the combo box
            int replaceIndex = replaceTypeDialog
                    .getReplaceTypeComboBox().getSelectedIndex();
            
            //loop through all the entities
            for (Entity entity : entities) {
                //if this has the index we're removing
                if (entity.getTypeIndex() == removeIndex) {
                    //replace the index
                    entity.setTypeIndex(replaceIndex);
                }
            }
            
            //update the visual list
            updateListModel();
            //clear the selection
            typesDialog.getTypesList().clearSelection();
            
            //disable the remove button
            typesDialog.getRemoveButton().setEnabled(false);
            
            //change the types comboBox in the MainFrame's infoPanel:
            //get the old selected index
            int oldIndex = frame.getTypeComboBox().getSelectedIndex();
            //if the old index is the one that was removed
            if (oldIndex == removeIndex) {
                //set it to the new index
                frame.getTypeComboBox().setSelectedIndex(replaceIndex);
            }
            
            replaceTypeDialog.setVisible(false);
        });
        
        replaceTypeDialog.getCancelButton().addActionListener((ActionEvent e) -> {
            replaceTypeDialog.setVisible(false);
        });
    }
    
    private void showReplaceTypeDialog() {
        //tell the user what they are replacing
        String replaceText = "Replace " + types.get(removeIndex) + " with:";
        replaceTypeDialog.getReplaceLabel().setText(replaceText);
        
        JComboBox rtComboBox = replaceTypeDialog.getReplaceTypeComboBox();
        //clear the items
        rtComboBox.removeAllItems();
        //loop through all the types
        for (int i = 0; i < types.size(); i++) {
            //if this is not the type we're removing
            if (i != removeIndex) {
                //add the type to the comboBox
                rtComboBox.addItem(types.get(i));
            }
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
                types.add(newType);
                //update the visual list
                updateListModel();
            }
        });
        
        typesDialog.getRemoveButton().addActionListener((ActionEvent e) -> {
            int index = typesDialog.getTypesList().getSelectedIndex();
            //if this is not "Misc" and there is actually a selection
            if (index > 0) {
                removeIndex = index;
                showReplaceTypeDialog();
            } else {
                JOptionPane.showMessageDialog(typesDialog, 
                        Utils.DEFAULT_TYPE + " can't be removed.", 
                        "Cannot Remove " + Utils.DEFAULT_TYPE, 
                        JOptionPane.WARNING_MESSAGE);
            }
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
        
        typesDialog.getCloseButton().addActionListener((ActionEvent e) -> {
            typesDialog.setVisible(false);
        });
    }
    
    /**
     * Shows the dialog with the current types in the project
     * @param types The list of all types in the project
     */
    public void showTypesDialog(Project currentProject) {
        this.types = currentProject.getTypes();
        this.entities = currentProject.getEntities();
        
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
