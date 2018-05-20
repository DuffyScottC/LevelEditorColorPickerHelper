/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import projects.Project;
import views.MainFrame;
import views.NewProjectDialog;

/**
 * Handles the creation, storage, opening, closing, and other management of all
 * projects.
 * @author Scott
 */
public class ProjectController {
    /**
     * The current opened project
     */
    private Project currentProject = null;
    
    
    private final JMenuItem newProjectMenuItem;
    private final JMenuItem openProjectMenuItem;
    private final JMenuItem newEntityMenuItem;
    private final JMenuItem deleteEntityMenuItem;
    
    /**
     * initialized with user.dir just in case something goes wrong with loading
     * preferences
     */
    private final JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
    private final NewProjectDialog newProjectDialog;
    
    public ProjectController(MainFrame frame) {
        newProjectMenuItem = frame.getNewProjectMenuItem();
        openProjectMenuItem = frame.getOpenProjectMenuItem();
        newEntityMenuItem = frame.getNewEntityMenuItem();
        deleteEntityMenuItem = frame.getDeleteEntityMenuItem();
        
        //set up chooser
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        newProjectDialog = new NewProjectDialog(frame, true);
        setUpNewProjectDialog(frame);
        
        newProjectMenuItem.addActionListener((ActionEvent e) -> {
            newProjectDialog.setVisible(true);
            
        });
    }
    
    private void setUpNewProjectDialog(MainFrame frame) {
        newProjectDialog.setName("New Project");
        
        //If the user clicks the browse button
        newProjectDialog.getBrowseButton().addActionListener((ActionEvent e) -> {
            int result = chooser.showDialog(frame, "Create");
            if (result == JFileChooser.APPROVE_OPTION) {
                File directory = chooser.getSelectedFile();
                if (directory.exists()) {
                    if (directory.isDirectory()) {
                        //set the project location
                        newProjectDialog.getProjectLocationTextField()
                                .setText(directory.getAbsolutePath());
                        //set the project folder
                        newProjectDialog.getProjectFolderTextField()
                                .setText(directory.getAbsolutePath() 
                                    + newProjectDialog.getProjectNameTextField()
                                            .getText());
                    }
                }
            }
        });
    }
}
