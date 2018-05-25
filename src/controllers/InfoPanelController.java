/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Entity;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.xml.bind.JAXBException;
import projects.Project;
import views.MainFrame;
import views.NewProjectDialog;

/**
 * Responsible for adding action listeners to, controlling access to, parsing
 * input of, and updating the elements of the info panel.
 * @author Scott
 */
public class InfoPanelController {
    
    private final MainFrame frame;
    private ProjectController projectController = null;
    private Project currentProject = null;
    
    private final NewProjectDialog newProjectDialog;
    
    public InfoPanelController(MainFrame frame) {
        
        this.frame = frame;
        
        //set up new project dialog
        newProjectDialog = new NewProjectDialog(frame, true);
        setUpNewProjectDialogActionListeners(frame);
        
        frame.getNewProjectMenuItem().addActionListener((ActionEvent e) -> {
            openNewProjectDialog();
        });
        
        //action listener for add entity Button and add entity MenuItem
        ActionListener addEntityActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if the user just opened the app and no project has been selected yet
                if (currentProject != null) {
                    try {
                        //generate a new default entity
                        Entity newEntity = Project.generateNewDefaultEntity();
                        //add the new entity to the currentProject
                        currentProject.addEntity(newEntity);
                        currentProject.setCurrentEntity(newEntity);
                        //update the UI to reflect the creation of a new entity
                        loadEntityIntoInfoPanel(newEntity);
                        
                        //tell the project that it has been modified
                        setIsModified(true);
                        setInfoElementsEnabled(true);
                        //in this case, there is nothing to revert to
                        frame.getRevertButton().setEnabled(false);
                    } catch (Exception ex) {
                        //the one with no explaination
                        JOptionPane.showMessageDialog(frame,  "Technical "
                                + "difficulties:\n"
                                + ex.toString());
                    }
                } else {
                    //open the new project dialog
                    openNewProjectDialog();
                }
            }
        };
        
        //add the action listener to both the button and the menu item
        frame.getAddEntityButton().addActionListener(addEntityActionListener);
        frame.getAddEntityMenuItem().addActionListener(addEntityActionListener);
        
    }
    
    public void setProjectController(ProjectController projectController) {
        this.projectController = projectController;
    }
    
    public void setCurrentProject(Project currentProject) {
        this.currentProject = currentProject;
    }
    
    //MARK: New Project
    private void openNewProjectDialog() {
        //reset the dialog
        resetNewProjectDialog(frame);
        newProjectDialog.setVisible(true);
    }
    
    private void resetNewProjectDialog(MainFrame frame) {
        newProjectDialog.setName("New Project");
        newProjectDialog.setLocationRelativeTo(null);
        
        JButton finishButton = newProjectDialog.getFinishButton();
        
        //make it so that the user can press enter to finish
        newProjectDialog.getRootPane().setDefaultButton(finishButton);
        
        JTextField pNameTextField 
                = newProjectDialog.getProjectNameTextField();
        JTextField pLocationTextField 
                = newProjectDialog.getProjectLocationTextField();
        JTextField pFolderTextField 
                = newProjectDialog.getProjectFolderTextField();
        pNameTextField.requestFocus();
        pNameTextField.selectAll();
        
        //initialize the project location
        projectLocation = new File(System.getProperty("user.dir"));
        
        pNameTextField.setText("New Project");
        pLocationTextField.setText(System.getProperty("user.dir"));
        Path defaultPath = Paths.get(System.getProperty("user.dir"), "New Project");
        pFolderTextField.setText(defaultPath.toString());
    }
    
    private void setUpNewProjectDialogActionListeners(MainFrame frame) {
        resetNewProjectDialog(frame);
        
        JButton cancelButton = newProjectDialog.getCancelButton();
        JButton finishButton = newProjectDialog.getFinishButton();
        
        //make it so that the user can press enter to finish
        newProjectDialog.getRootPane().setDefaultButton(finishButton);
        
        JTextField pNameTextField 
                = newProjectDialog.getProjectNameTextField();
        JTextField pLocationTextField 
                = newProjectDialog.getProjectLocationTextField();
        
        //If the user clicks the browse button
        newProjectDialog.getBrowseButton().addActionListener((ActionEvent e) -> {
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = chooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File tempProjectLocation = chooser.getSelectedFile();
                if (tempProjectLocation.exists()) {
                    if (tempProjectLocation.isDirectory()) {
                        //assign the project location.
                        projectLocation = tempProjectLocation;
                        //set the project location
                        pLocationTextField.setText(projectLocation.getAbsolutePath());
                        
                        updateNewProjectFolderTextBox();
                    }
                }
            }
            chooser.resetChoosableFileFilters();
        });
        
        pNameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                projectName = pNameTextField.getText();
                //every time the user presses a key, update the project folder
                updateNewProjectFolderTextBox();
            }
        });
        
        cancelButton.addActionListener((ActionEvent e) -> {
            newProjectDialog.setVisible(false);
        });
        
        finishButton.addActionListener((ActionEvent e) -> {
            try {
                //if we successfully create a new project
                if (createNewProject()) {
                    newProjectDialog.setVisible(false);
                }
            } catch (JAXBException ex) {
                //if the creation of the project was not successful,
                //output the exception message to the user.
                JOptionPane.showMessageDialog(frame, "Unable to serialize project.\n" 
                        + ex.toString());
                //allow the user to try again by staying still and keeping
                //the newProjectDialog window open.
            } catch (Exception ex) {
                //if the creation of the project was not successful,
                //output the exception message to the user.
                JOptionPane.showMessageDialog(frame, ex.toString());
                //allow the user to try again by staying still and keeping
                //the newProjectDialog window open.
            }
        });
    }
    
}
