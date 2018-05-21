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
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
    private String projectName = "New Project";
    
    private final MainFrame frame;
    
    private final JMenuItem newProjectMenuItem;
    private final JMenuItem openProjectMenuItem;
    private final JMenuItem newEntityMenuItem;
    private final JMenuItem deleteEntityMenuItem;
    
    private final ResultsListController resultsListController;
    private final RecentListController recentListController;
    
    /**
     * initialized with user.dir just in case something goes wrong with loading
     * preferences
     */
    private final JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
    private final NewProjectDialog newProjectDialog;
    
    /**
     * Set up the ProjectController
     * @param frame
     * @param resultsListController
     * @param recentListController 
     */
    public ProjectController(MainFrame frame,
            ResultsListController resultsListController,
            RecentListController recentListController) {
        this.frame = frame;
        
        this.resultsListController = resultsListController;
        this.recentListController = recentListController;
        
        newProjectMenuItem = frame.getNewProjectMenuItem();
        openProjectMenuItem = frame.getOpenProjectMenuItem();
        newEntityMenuItem = frame.getNewEntityMenuItem();
        deleteEntityMenuItem = frame.getDeleteEntityMenuItem();
        
        //set up chooser
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        //set up new project dialog
        newProjectDialog = new NewProjectDialog(frame, true);
        setUpNewProjectDialog(frame);
        
        newProjectMenuItem.addActionListener((ActionEvent e) -> {
            newProjectDialog.setVisible(true);
        });
    }
    
    
    //MARK: Open Project
    
    
    //MARK: New Project
    /**
     * Creates a new project in the current projectLocation by creating a new
     * folder and creating an XML serialized file representing the project.
     * @returns True if this process was successful, false if there was a problem
     * and you should stop creating a new project.
     */
    private boolean createNewProject() {
        // Create the directory that the user designated for the project:
        //Fist, convert the projectLocation to a path object
        Path projectLocationPath = projectLocation.toPath();
        //add the projectName to the path to get the project folder
        //and store it in a file object
        projectLocation = projectLocationPath.resolve(projectName).toFile();
        //if the project location already exists
        if (projectLocation.exists()) {
            //if the user wants to continue
            if (shouldContinue("The project folder already exists. "
                    + "Are you sure you wish to continue?")) {
                //if the projectLocation is not empty
                if (projectLocation.list().length > 0) {
                    //if the user wants to continue
                    if (shouldContinue("The project folder is not empty."
                            + "Are you sure you wish to continue?")) {
                        //create the project file
                        createNewProjectFile();
                    }
                }
            }
        }
        //make the directory
        boolean success = projectLocation.mkdir();
        //if we could not create the project directory
        if (!success) {
            return false;
        }
        //create the new project
        currentProject = new Project(projectName, projectLocation);
        enterNewProjectState();
        return true;
    }
    
    /**
     * Creates an XML file representing the new project
     */
    private void createNewProjectFile() {
        
    }
    
    private void setUpNewProjectDialog(MainFrame frame) {
        newProjectDialog.setName("New Project");
        newProjectDialog.setLocationRelativeTo(null);
        
        JButton cancelButton = newProjectDialog.getCancelButton();
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
                        
                        updateNewProjectFolderTextBox();
                    }
                }
            }
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
            //if the creation of a new project was successful
            if (createNewProject()) {
                newProjectDialog.setVisible(false);
            } else {
                //if the creation of a new project was NOT successful
                JOptionPane.showMessageDialog(frame, "Unable to create the project.");
            }
        });
    }
    
    public void updateNewProjectFolderTextBox() {
        JTextField pFolderTextField = newProjectDialog.getProjectFolderTextField();
        //Convert the projectLocation to a path object
        Path projectLocationPath = projectLocation.toPath();
        //add the projectName to the path to get the project folder
        //and store it in a file object
        File projectFolder = projectLocationPath.resolve(projectName).toFile();
        //set the project folder
        pFolderTextField.setText(projectFolder.getAbsolutePath());
    }
    
    public void enterNewProjectState() {
        resultsListController.clearEntities();
        recentListController.clearEntities();
        frame.getTypeComboBox().setEnabled(false);
        frame.getSearchTextField().setEnabled(false);
        frame.getSelectButton().setEnabled(false);
        frame.getColorCodeTextField().setEnabled(false);
        frame.getIncludeHashTagCheckBox().setEnabled(false);
        frame.getRedSlider().setEnabled(false);
        frame.getRedSpinner().setEnabled(false);
        frame.getGreenSlider().setEnabled(false);
        frame.getGreenSpinner().setEnabled(false);
        frame.getBlueSlider().setEnabled(false);
        frame.getBlueSpinner().setEnabled(false);
        frame.getTagsTextField().setEnabled(false);
        frame.getRevertButton().setEnabled(false);
        frame.getApplyButton().setEnabled(false);
    }
    
    //MARK: Misc
    /**
     * Convenience method that asks a user if they want to continue
     * 
     * @return true if the process should continue, false if you should stop
     * the process
     */
    private boolean shouldContinue(String message) {
        int selection = JOptionPane.showConfirmDialog(frame, message); //ask the user if they want to continue
        //if the user did not choose "yes", then we should cancel the operation
        //if the user did choose yes, then we should continue the operation
        //if the file has been saved, then we can just return true
        return selection == JOptionPane.YES_OPTION;
    }
}
