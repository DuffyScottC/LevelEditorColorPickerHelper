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
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
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
    private File projectFile = null;
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
    private final JFileChooser chooser 
            = new JFileChooser(System.getProperty("user.dir"));
    /**
     * A file filter which only allows .lecp files (Project files) to be
     * chosen.
     */
    private final FileFilter projectFileFilter 
            = new FileNameExtensionFilter("Project (.lecp)","lecp");
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
        newEntityMenuItem = frame.getAddEntityMenuItem();
        deleteEntityMenuItem = frame.getDeleteEntityMenuItem();
        
        //set up chooser
        chooser.setMultiSelectionEnabled(false);
        
        //set up new project dialog
        newProjectDialog = new NewProjectDialog(frame, true);
        setUpNewProjectDialogActionListeners(frame);
        
        newProjectMenuItem.addActionListener((ActionEvent e) -> {
            //reset the dialog
            resetNewProjectDialog(frame);
            newProjectDialog.setVisible(true);
        });
        
        openProjectMenuItem.addActionListener((ActionEvent e) -> {
            openProject();
        });
        
        frame.getAddEntityButton().addActionListener((ActionEvent e) -> {
            
        });
    }
    
    //MARK: Add Entity
    /**
     * Fills all the appropriate fields in the mainframe info panel with the
     * default "new entity" info.
     */
    private void fillNewEntityInfo() {
        //set the name of the new entity
        frame.getNameTextField().setText(getUniqueDefaultName());
        
        //set theimage of the new entity to a blank white image
        BufferedImage newImage = Utils.getBlankImageIcon(32, 32);
        frame.getImagePanel().setImage(newImage);
    }
    
    private String getUniqueDefaultName() {
        Map<String, Entity> entities = currentProject.getEntities();
        String nameBase = "Entity ";
        int num = 1;
        //keep incrementing the num component until we find a unique name
        //(i.e. a name that is not already in entities)
        while (!entities.containsKey(nameBase + num)) {
            num++;
        }
        //return the unique name
        return nameBase + num;
    }
    
    //MARK: Open Project
    private void openProject() {
        //only allow the user to open project files (.lecp)
        chooser.setFileFilter(projectFileFilter);
        //allow the user to choose a file
        int result = chooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            //get the user's selected file
            File tempFile = chooser.getSelectedFile();
            //reset the chooser's file filters
            chooser.resetChoosableFileFilters();
            //if the file does NOT exist
            if (!tempFile.exists()) {
                //tell the user the file does not exist
                JOptionPane.showMessageDialog(frame, "Could not open project.\n"
                        + "The file " + tempFile.getAbsolutePath() 
                        + " does not exist.");
                //give up the ghost
                return;
            }
            //if the file exists, continue
            
            //if the file does NOT end with .lecp
            if (!tempFile.getName().matches(".*\\.lecp")) {
                //tell the user the file does not end with .lecp
                JOptionPane.showMessageDialog(frame, "Could not open project.\n"
                        + "The file " + tempFile.getAbsolutePath() 
                        + " is not a .lecp project file.");
                //give up the ghost
                return;
            }
            //if the file ends with .lecp, continue
            
            try {
                //try to deserialize the project file
                Project tempProject = deserializeProjectFromXML(tempFile);
                if (tempProject == null) {
                    //tell the user the file does not end with .lecp
                    JOptionPane.showMessageDialog(frame, "Could not deserialize"
                        + " the project file:\n" + tempFile.getAbsolutePath());
                    //give up the ghost
                    return;
                }
                //if the tempProject is not null, then it is a valid project
                //deserialized from the file, and we can
                //load the new project
                loadProject(tempProject);
            } catch (JAXBException ex) {
                //tell the user that you couldn't
                JOptionPane.showMessageDialog(frame, "Could not deserialize"
                        + " the project file:\n" + tempFile.getAbsolutePath()
                        + "\n" + ex.toString());
            }
            
        }
        
    }
    
    private void loadProject(Project newProject) {
        enterOpenProjectState();
    }
    
    private Project deserializeProjectFromXML(File file) throws JAXBException {
         JAXBContext context = JAXBContext.newInstance(Project.class);
         Unmarshaller unmarshaller = context.createUnmarshaller();
         Object obj = unmarshaller.unmarshal(file);
         if (obj instanceof Project) {
             return (Project) obj;
         }
         return null;
     }
    
    //MARK: New Project
    /**
     * Creates a new project in the current projectLocation by creating a new
     * folder and creating an XML serialized file representing the project.
     * @returns True if this process was successful, false if there was a problem
     * and you should stop creating a new project.
     */
    private boolean createNewProject() throws JAXBException, Exception {
        // Create the directory that the user designated for the project:
        //Fist, convert the projectLocation to a path object
        Path tempProjectLocationPath = projectLocation.toPath();
        //add the projectName to the path to get the project folder
        Path projectLocationPath = Paths.get(tempProjectLocationPath.toString(), projectName);
        //and store it in a file object
        File tempProjectLocation = projectLocationPath.toFile();
        //if the project location already exists
        if (tempProjectLocation.exists()) {
            //if the user wants to continue
            if (shouldContinue("The project folder already exists at\n"
                    + tempProjectLocation.getAbsolutePath()
                    + "\nAre you sure you wish to continue?")) {
                //if the projectLocation is not empty
                if (tempProjectLocation.list().length > 0) {
                    //if the user does NOT want to continue
                    if (!shouldContinue("The project folder at\n"
                            + tempProjectLocation.getAbsolutePath()
                            + "\nis not empty. Are you sure you wish to continue?")) {
                        //return without throwing an exception and without
                        //creating the new project, because the user chose
                        //not to continue.
                        return false;
                    }
                    //if the user does want to create the project inside a
                    //non-empty project folder, continue
                }
                //if the projectLocation is empty (or the user wants to create
                //the project inside a non-empty project folder, continue
            } else {
                //return without throwing an exception and without
                //creating the new project, because the user chose
                //not to continue.
                return false;
            }
        } else {
            //if the projectLocation does NOT exist:
            //make the directory
            boolean success = tempProjectLocation.mkdir();
            //if we could not create the project directory
            if (!success) {
                //output the problem to the user
                throw new Exception("Could not create the project folder " +
                        tempProjectLocation.getAbsolutePath());
            }
        }
        //if we reach this point, the user wants to create the project, regardless
        //of whether the project folder already exists or the project file
        //already exists (or the folder does not exist and we are free to make it)
        
        // Create the project file:
        //if the project file could NOT be created
        if (!createNewProjectFile(tempProjectLocation)) {
            //return without throwing an exception and without
            //creating the new project, because the user chose
            //not to continue.
            return false;
        }
        //if the project file was successfully created
        
        //At this point, the name, locaiton, and file are assigned.
        //create the new project using the name, location, and file created
        //specifically for this project
        currentProject = new Project(projectName, projectLocation, projectFile);
        //serialize the new project
        serializeNewProjectToXML();
        enterNewProjectState();
        return true;
    }
    
    /**
     * Creates a file that will be used to serialize the project from now on.<>
     * 
     * This also assigns the {@link projectLocation} and {@link projectFile} to
     * their temp values once and for all, if the creation of the new project
     * file goes well.
     * 
     * @return True if the file was created with no problems, false if the
     * file could not be created for some reason.
     */
    private boolean createNewProjectFile(File tempProjectLocation) throws Exception {
        //get the path of the project location
        Path tempLocationPath = tempProjectLocation.toPath();
        //append the project .xml file to the project location
        //using the .lecp extension (Level Editor Color Picker)
        Path tempFilePath = Paths.get(tempLocationPath.toString(), projectName + ".lecp");
        //convert the path into a file
        File tempFile = tempFilePath.toFile();
        if (tempFile.exists()) {
            //if the user does NOT want to continue
            if (!shouldContinue("The file " + tempFile.getName() + "already exists "
                    + "at \n" + tempFile.getAbsolutePath() + ".\nWould you like to "
                    + "overwrite this file?\n"
                    + "Please note: Overwriting this file will erase all data "
                    + "in " + tempFile.getName() + ". This cannot be undone.")) {
                return false;
            }
            //if the user does want to continue, continue
        }
        //if the tempFile does not exist or the user wants to overwrite this file
        
        //At this point, everything is correct and we can assign the projectFile
        //and the projectLocation to their temp values.
        projectLocation = tempProjectLocation;
        projectFile = tempFile;
        return true;
    }
    
    /**
     * Serializes the currentProject to the projectFile using JAXB
     * XML serialization. 
     * @throws JAXBException if something goes wrong in the serialization 
     * process
     */
    private void serializeNewProjectToXML() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Project.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(currentProject, projectFile);
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
        pFolderTextField.setText(System.getProperty("user.dir") + "/New Project");
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
    
    public void updateNewProjectFolderTextBox() {
        JTextField pFolderTextField = newProjectDialog.getProjectFolderTextField();
        //Convert the projectLocation to a path object
        Path projectLocationPath = projectLocation.toPath();
        //add the projectName to the path to get the project folder
        Path projectFolderPath = Paths.get(projectLocationPath.toString(), projectName);
        //convert the path to a file object
        File projectFolder = projectFolderPath.toFile();
        //set the project folder
        pFolderTextField.setText(projectFolder.getAbsolutePath());
    }
    
    private void enterOpenProjectState() {
        //get the map of all the entities in the project
        Map<String,Entity> newEntitiesMap = currentProject.getEntities();
        //convert the map to a list
        List<Entity> allNewEntities = new ArrayList(newEntitiesMap.values());
        //update the entities in the results list
        resultsListController.setEntities(allNewEntities);
        
        //search panel
        frame.getTypeComboBox().setEnabled(true);
        frame.getSearchTextField().setEnabled(true);
        
        //info panel
        frame.getSelectButton().setEnabled(true);
        frame.getNameTextField().setEnabled(true);
        frame.getEntityTypeComboBox().setEnabled(true);
        frame.getColorCodeTextField().setEnabled(true);
        frame.getIncludeHashTagCheckBox().setEnabled(true);
        frame.getRedSlider().setEnabled(true);
        frame.getRedSpinner().setEnabled(true);
        frame.getGreenSlider().setEnabled(true);
        frame.getGreenSpinner().setEnabled(true);
        frame.getBlueSlider().setEnabled(true);
        frame.getBlueSpinner().setEnabled(true);
        frame.getTagsTextField().setEnabled(true);
        frame.getRevertButton().setEnabled(true);
        frame.getApplyButton().setEnabled(true);
    }
    
    public void enterNewProjectState() {
        resultsListController.clearEntities();
        recentListController.clearEntities();
        
        //search panel
        frame.getTypeComboBox().setEnabled(false);
        frame.getSearchTextField().setEnabled(false);
        
        //info panel
        frame.getSelectButton().setEnabled(false);
        frame.getNameTextField().setEnabled(false);
        frame.getEntityTypeComboBox().setEnabled(false);
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
