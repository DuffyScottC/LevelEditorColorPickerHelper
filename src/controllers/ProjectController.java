/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Entity;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
    private final ColorPickerController colorPickerController;
    
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
    
    private boolean isModified = false;
    
    /**
     * Set up the ProjectController
     * @param frame
     * @param resultsListController
     * @param recentListController 
     * @param colorPickerController 
     * @param infoPanelController 
     */
    public ProjectController(MainFrame frame,
            ResultsListController resultsListController,
            RecentListController recentListController,
            ColorPickerController colorPickerController) {
        this.frame = frame;
        
        this.resultsListController = resultsListController;
        this.recentListController = recentListController;
        this.colorPickerController = colorPickerController;
        
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
            openNewProjectDialog();
        });
        
        openProjectMenuItem.addActionListener((ActionEvent e) -> {
            openProject();
        });
        
        //action listener for add entity Button and add entity MenuItem
        ActionListener addEntityActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if the user just opened the app and no project has been selected yet
                if (currentProject != null) {
                    try {
                        //generate a new default entity
                        Entity newEntity = generateNewDefaultEntity();
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
        
        frame.getApplyButton().addActionListener((ActionEvent e) -> {
            loadEntityFromInfoPanelIntoProject();
            saveProject();
            setIsModified(false);
        });
        
        frame.getNewTypeButton().addActionListener((ActionEvent e) -> {
            //Get the new message type from the user
            String newType = JOptionPane.showInputDialog(frame, "New type:", 
                    "New Type", JOptionPane.PLAIN_MESSAGE);
            //if the user did not press cancel
            if (newType != null) {
                //if the user actually entered a string
                if (newType.length() > 0) {
                    //if this type is NOT already in the types list
                    if (!currentProject.getTypes().contains(newType)) {
                        //add the new type to the project
                        currentProject.addType(newType);
                        //update the combo box
                        updateTypeComboBox();
                    } else {
                        //tell the user that the type already exists
                        JOptionPane.showMessageDialog(frame, "This type already "
                                + "exists in this project.", "Type Exists", 
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
    
    //MARK: Add Entity
    /**
     * Generates a new entity with default attributes.
     * @throws Exception if the user can't addEntity a new entity (the reason is
 in the exception's description).
     */
    private Entity generateNewDefaultEntity() throws Exception {
        // Set the color of the entity
        Color newColor = getUniqueDefaultColor();
        //if newColor is null
        if (newColor == null) {
            //tell the user they have run out of colors
            throw new Exception(
                    "Oops! No more colors are available in the RGB spectrum!\n"
                    + "Congratulations, you used all 16,581,375 possible color\n"
                    + "combinations! Unfortunately, this means you can't add\n"
                    + "more entities (at least no to this project).");
        }

        String newType = currentProject.getTypes().get(0);

        String newName = getUniqueDefaultName();

        String newUnityPrefab = getUniqueDefaultUnityPrefab();

        //create and return a new entity with the attributes created above
        return new Entity(null, newName, newType, newColor, newUnityPrefab);
    }
    
    private Color getUniqueDefaultColor() {
        Map<Integer, Entity> entities = currentProject.getEntitiesByColorValue();
        for (int r = 0; r < 255; r++) {
            for (int g = 0; g < 255; g++) {
                for (int b = 0; b < 255; b++) {
                    //create a new color from these values
                    Color color = new Color(r, g, b);
                    //if this is a unique color (no other entity has this color)
                    if (!entities.containsKey(color.getRGB())) {
                        return color;
                    }
                }
            }
        }
        //in the highly unlikely instance that we reach this point, 
        //then all the colors have been used and we cannot create new
        //entities for this project. 
        return null;
    }
    
    private String getUniqueDefaultName() {
        Map<String, Entity> entities = currentProject.getEntitiesByName();
        String nameBase = "Entity ";
        int num = 1;
        //keep incrementing the num component until we find a unique name
        //(i.e. a name that is not already in entities)
        while (entities.containsKey(nameBase + num)) {
            num++;
        }
        //return the unique name
        return nameBase + num;
    }
    
    private String getUniqueDefaultUnityPrefab() {
        Map<String, Entity> entities = currentProject.getEntitiesByUnityPrefab();
        String prefabBase = "Prefabs/Prefab";
        int num = 1;
        //keep incrementing the num component until we find a unique string
        //(i.e. a prefab that is not already in entities)
        while (entities.containsKey(prefabBase + num)) {
            num++;
        }
        //return the unique prefab string
        return prefabBase + num;
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
    
    /**
     * This function is called once the project is successfully unmarshalled, 
     * or deserialized, from xml .lecp file to a project object. This sets up
     * the project for use, loads data into the JLists, sets up the GUI, etc.
     * @param newProject A project recently deserialized from .lecp xml format.
     */
    private void loadProject(Project newProject) {
        newProject.setUpTransientLists();
        currentProject = newProject;
        projectLocation = newProject.getProjectLocation();
        projectFile = newProject.getProjectFile();
        projectName = newProject.getName();
        updateTypeComboBox();
        enterOpenProjectState();
        List<Entity> allNewEntities = newProject.getEntities();
        resultsListController.setEntities(allNewEntities);
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
    private void openNewProjectDialog() {
        //reset the dialog
        resetNewProjectDialog(frame);
        newProjectDialog.setVisible(true);
    }
    
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
        currentProject.addType(Utils.defaultType);
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
        frame.setTitle(projectName);
        //get the list of all the entities in the project
        List<Entity> allNewEntities = currentProject.getEntities();
        //update the entities in the results list
        resultsListController.setEntities(allNewEntities);
        resultsListController.setSelectedIndex(0);
        setInfoElementsEnabled(true);
    }
    
    public void enterNewProjectState() {
        resultsListController.clearEntities();
        recentListController.clearEntities();
        setInfoElementsEnabled(false);
        setIsModified(false);
    }
    
    //MARK: Misc
    private void loadEntityIntoInfoPanel(Entity entity) {
        frame.getNameTextField().setText(entity.getName());
        //get the index of this entity's type
        int index = currentProject.getTypes().indexOf(entity.getType());
        //set the selected type
        frame.getTypeComboBox().setSelectedIndex(index);
        boolean noImage = false;
        //if the entity has an image path
        if (entity.getImage() != null) {
            //get the image path and turn it into a file
            File imageFile = new File(entity.getImage());
            //if the image exists
            if (imageFile.exists()) {
                //set the image of the ImagePanel
                frame.getImagePanel().setImagePath(imageFile);
            } else {
                //tell the user the image does not exist
                JOptionPane.showMessageDialog(frame,
                        "Could not find image for " + entity.getName()
                        + " at\n" + entity.getImage() + "\nFile does not "
                        + "exist.");
                noImage = true;
            }
            
        } else {
            noImage = true;
        }
        
        //if there was no image file associated with this entity
        if (noImage) {
            //create a blank image of the color of the entity
            BufferedImage image 
                    = Utils.getBlankBufferedImage(32, 32, entity.getColor());
            //set the image of the ImagePanel
            frame.getImagePanel().setImage(image);
        }
        
        colorPickerController.setColor(entity.getR(), entity.getG(), entity.getB());
    }
    
    private void setInfoElementsEnabled(boolean value) {
        //search panel
        frame.getTypeComboBox().setEnabled(value);
        frame.getSearchTextField().setEnabled(value);
        
        //info panel
        frame.getSelectButton().setEnabled(value);
        frame.getNameTextField().setEnabled(value);
        frame.getTypeComboBox().setEnabled(value);
        frame.getNewTypeButton().setEnabled(value);
        frame.getChangeImageButton().setEnabled(value);
        frame.getColorCodeTextField().setEnabled(value);
        frame.getIncludeHashTagCheckBox().setEnabled(value);
        frame.getRedSlider().setEnabled(value);
        frame.getRedSpinner().setEnabled(value);
        frame.getGreenSlider().setEnabled(value);
        frame.getGreenSpinner().setEnabled(value);
        frame.getBlueSlider().setEnabled(value);
        frame.getBlueSpinner().setEnabled(value);
    }
    
    /**
     * Updates the typeComboBox to reflect the current open project's types
     */
    public void updateTypeComboBox() {
        JComboBox typeComboBox = frame.getTypeComboBox();
        //remove all items in the type combo box
        typeComboBox.removeAllItems();
        for (String type : currentProject.getTypes()) {
            typeComboBox.addItem(type);
        }
    }
    
    /**
     * Convenience method that asks a user if they want to continue
     * 
     * @return true if the process should continue, false if you should stop
     * the process
     */
    private boolean shouldContinue(String message) {
        //ask the user if they want to continue
        int selection = JOptionPane.showConfirmDialog(frame, message);
        //if the user did not choose "yes", then we should cancel the operation
        //if the user did choose yes, then we should continue the operation
        //if the file has been saved, then we can just return true
        return selection == JOptionPane.YES_OPTION;
    }
    
    /**
     * Loads the user-edited information from the info panel into the project,
     * replacing the project's currentEntity info with that info.
     */
    private void loadEntityFromInfoPanelIntoProject() {
        String newImage = null;
        String newName = frame.getNameTextField().getText();
        int newTypeIndex = frame.getTypeComboBox().getSelectedIndex();
        String newType = currentProject.getTypes().get(newTypeIndex);
        Color newColor = colorPickerController.getColor();
        String newUnityPrefab = frame.getUnityPrefabTextField().getText();
        currentProject.getCurrentEntity().replaceValues(newImage, newName, 
                newType, newColor, newUnityPrefab);
    }
    
    /**
     * This is run when the user presses the apply button.
     */
    private void saveProject() {
        try {
            //serialize the new project
            serializeNewProjectToXML();
            
        } catch (JAXBException ex) {
            //if the serialization of the project was not successful,
            //output the exception message to the user.
            JOptionPane.showMessageDialog(frame, "Unable to serialize project.\n" 
                    + ex.toString());
        }
    }
    
    /**
     * Set the isModified boolean, and calls setEnabled() with the passed in 
     * value on the frame's revert and apply buttons.
     */
    private void setIsModified(boolean isModified) {
        this.isModified = isModified;
        frame.getRevertButton().setEnabled(isModified);
        frame.getApplyButton().setEnabled(isModified);
    }
}
