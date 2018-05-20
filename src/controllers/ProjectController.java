/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Path;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
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
    private File projectLocation = null;
    private String projectName = "newproject";
    
    
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
        
        JTextField pNameTextField 
                = newProjectDialog.getProjectNameTextField();
        JTextField pLocationTextField 
                = newProjectDialog.getProjectLocationTextField();
        JTextField pFolderTextField 
                = newProjectDialog.getProjectFolderTextField();
        
        pNameTextField.setText("New Project");
        pLocationTextField.setText(System.getProperty("user.dir"));
        pFolderTextField.setText(System.getProperty("user.dir") + "/New Project");
        
        //If the user clicks the browse button
        newProjectDialog.getBrowseButton().addActionListener((ActionEvent e) -> {
            int result = chooser.showDialog(frame, "Create");
            if (result == JFileChooser.APPROVE_OPTION) {
                projectLocation = chooser.getSelectedFile();
                if (projectLocation.exists()) {
                    if (projectLocation.isDirectory()) {
                        //set the project location
                        pLocationTextField.setText(projectLocation.getAbsolutePath());
                        
                        //Convert the projectLocation to a path object
                        Path projectLocationPath = projectLocation.toPath();
                        //add the projectName to the path to get the project folder
                        //and store it in a file object
                        File projectFolder = projectLocationPath.resolve(projectName).toFile();
                        //set the project folder
                        pFolderTextField.setText(projectFolder.getAbsolutePath());
                    }
                }
            }
        });
        
        newProjectDialog.getProjectNameTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                
            }
        });
    }
}
