/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Entity;
import entities.Offset;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import javax.swing.filechooser.FileFilter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.Preferences;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import projects.Project;
import scriptgeneration.ScriptGenerator;
import views.MainFrame;
import views.NewProjectDialog;
import views.PreferencesDialog;
import views.SetCommandDialog;

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
    
    /**
     * Stores the image that is currently being displayed in the ImagePanel
     * (not to be confused with the currentProject's currentEntity's image)
     */
    private File currentImageFile = null;
    /**
     * Tells the program whether it should copy the currentImageFile to the
     * resources folder (true) or if the currentImageFile is already in the
     * resources folder (false)
     */
    private boolean shouldCopyCurrentImageFile = false;
    /**
     * If the user chooses to delete the image, then we need to store the image
     * to delete so that we can delete it when the user presses the apply button
     */
    private File imageFileToDelete = null;
    
    private final MainFrame frame;
    
    private final JMenuItem newProjectMenuItem;
    private final JMenuItem openProjectMenuItem;
    
    /**
     * True if the resultsList is selected, false if the recentList is selected
     */
    private boolean resultsListSelected = true;
    
    private final ResultsListController resultsListController;
    private final RecentListController recentListController;
    private final ColorPickerController colorPickerController;
    private final SearchController searchController;
    private final ModifiedController modifiedController;
    
    //MARK: Preferences
    private PreferencesDialog preferencesDialog;
    private boolean includeHashTag = false;
    private boolean includeAlpha = true;
    private boolean includeOffset = true;
    /**
     * Holds all user preferences for this application
     */
    private Preferences prefs;
    private final JFileChooser imageChooser 
            = new JFileChooser(System.getProperty("user.dir"));
    private final JFileChooser newProjectChooser 
            = new JFileChooser(System.getProperty("user.dir"));
    private final JFileChooser openProjectChooser 
            = new JFileChooser(System.getProperty("user.dir"));
    
    /**
     * A file filter which only allows .lecp files (Project files) to be
     * chosen.
     */
    private final FileFilter projectFileFilter 
            = new FileNameExtensionFilter("Project (.lecp)","lecp");
    private final NewProjectDialog newProjectDialog;
    
    private SetCommandDialog setCommandDialog;
    /**
     * True if we should use the double-click command assigned to this project,
     * false if not.
     */
    private boolean useCommand = false;
    
    
    /**
     * Set up the ProjectController
     * @param frame
     * @param resultsListController
     * @param recentListController 
     * @param colorPickerController 
     * @param searchController 
     * @param modifiedController 
     */
    public ProjectController(MainFrame frame,
            ResultsListController resultsListController,
            RecentListController recentListController,
            ColorPickerController colorPickerController,
            SearchController searchController,
            ModifiedController modifiedController) {
        this.frame = frame;
        
        this.resultsListController = resultsListController;
        this.recentListController = recentListController;
        this.colorPickerController = colorPickerController;
        this.searchController = searchController;
        this.modifiedController = modifiedController;
        
        setUpOffsetSpinners();
        
        setCommandDialog = new SetCommandDialog(frame, true);
        setUpSetCommandDialogActionListeners();
        
        preferencesDialog = new PreferencesDialog(frame, true);
        setUpPreferencesDialogActionListeners();
        
        newProjectMenuItem = frame.getNewProjectMenuItem();
        openProjectMenuItem = frame.getOpenProjectMenuItem();
        
        //set up choosers
        imageChooser.setMultiSelectionEnabled(false);
        newProjectChooser.setMultiSelectionEnabled(false);
        openProjectChooser.setMultiSelectionEnabled(false);
        imageChooser.setDragEnabled(true);
        newProjectChooser.setDragEnabled(true);
        openProjectChooser.setDragEnabled(true);
        imageChooser.setDialogTitle("Choose an Image");
        newProjectChooser.setDialogTitle("Choose a Location");
        openProjectChooser.setDialogTitle("Choose a Project");
        
        
        imageChooser.addChoosableFileFilter(
                new FileNameExtensionFilter("PNG", "png"));
        imageChooser.addChoosableFileFilter(
                new FileNameExtensionFilter("JPEG", "jpeg"));
        imageChooser.addChoosableFileFilter(
                new FileNameExtensionFilter("JPG", "jpg"));
        imageChooser.addChoosableFileFilter(
                new FileNameExtensionFilter("GIF", "gif"));
        imageChooser.addChoosableFileFilter(
                new FileNameExtensionFilter("TIFF", "tiff"));
        imageChooser.addChoosableFileFilter(
                new FileNameExtensionFilter("TIF", "tif"));
        
        loadPreferences();
        
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
        ActionListener addEntityActionListener = (ActionEvent e) -> {
            //if the entity has been modified
            if (modifiedController.isModified()) {
                //if the user does not want to continue
                if (!Utils.shouldContinue("Discard changes?", frame)) {
                    return;
                }
            }
            
            //reset the image file to delete
            imageFileToDelete = null;
            
            //if the user just opened the app and no project has been selected yet
            if (currentProject != null) {
                //generate a new default entity
                Entity newEntity = generateNewDefaultEntity();
                if (newEntity == null) { 
                    JOptionPane.showMessageDialog(null,
                            "Oops! No more colors are available in the RGB spectrum!\n"
                                    + "Congratulations, you used all 16,581,375 possible color\n"
                                    + "combinations! Unfortunately, this means you can't add\n"
                                    + "more entities (at least no to this project).",
                            "No More Colors",
                            JOptionPane.ERROR_MESSAGE);
                    //stop without saving the entity
                    return;
                }
                //add the new entity to the currentProject
                currentProject.addEntity(newEntity);
                currentProject.setCurrentEntity(newEntity);
                
                /*
                If the newly added entity matches the search string, then
                we need to update the search results to include this entity
                */
                updateSearchResultsToReflectEntityChangeOrAddition(newEntity);
                
                //update the UI to reflect the creation of a new entity
                loadEntityIntoInfoPanel(newEntity);
                
                //tell the project that it has not been modified
                modifiedController.setModified(false);
                //save the project
                saveProject();
                setSearchElementsEnabled(true);
                setInfoElementsEnabled(true);
            } else {
                //open the new project dialog
                openNewProjectDialog();
            }
        };
        
        //add the action listener to both the button and the menu item
        frame.getAddEntityButton().addActionListener(addEntityActionListener);
        frame.getAddEntityMenuItem().addActionListener(addEntityActionListener);
        
        frame.getDeleteEntityMenuItem().addActionListener((ActionEvent e) -> {
            //if the user does not want to delete the entity
            if (!Utils.shouldContinue("Are you sure you wish to delete \n"
                    + currentProject.getCurrentEntity().getName() 
                    + "\nand all its Resources (including its\n"
                    + "image?) This action cannot be undone.", frame)) {
                //do nothing
                return;
            }
            //remove the entity from the project
            currentProject.removeCurrentEntity();
            
            //assign the imageFileToDelete to the current image
            imageFileToDelete = currentImageFile;
            deleteImageFileToDelete();
            
            resultsListController.removeSelectedEntity();
            
            saveProject();
        });
        
        frame.getFileMenu().addActionListener((ActionEvent e) -> {
            //hide or show the deleteEntityMenuItem when neccessary
            if (currentProject == null) {
                frame.getDeleteEntityMenuItem().setEnabled(false);
            } else {
                if (currentProject.getEntities().isEmpty()) {
                    frame.getDeleteEntityMenuItem().setEnabled(false);
                } else {
                    frame.getDeleteEntityMenuItem().setEnabled(true);
                }
            }
        });
        
        frame.getApplyButton().addActionListener((ActionEvent e) -> {
            loadEntityFromInfoPanelIntoProject();
            deleteImageFileToDelete();
            saveProject();
            /*
            If the newly changed entity matches the search string, then
            we need to update the search results to include this entity
            */
            updateSearchResultsToReflectEntityChangeOrAddition(
                    currentProject.getCurrentEntity());
            
            modifiedController.setModified(false);
        });
        
        frame.getRevertButton().addActionListener((ActionEvent e) -> {
            //we no longer want to delete any files
            imageFileToDelete = null;
            //and we want to reload the current entity into the info panel
            loadEntityIntoInfoPanel(currentProject.getCurrentEntity());
            //mark this as unmodified
            modifiedController.setModified(false);
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
        
        frame.getDeleteTypeButton().addActionListener((ActionEvent e) -> {
            JComboBox typeComboBox = frame.getTypeComboBox();
            int selectedIndex = typeComboBox.getSelectedIndex();
            if (selectedIndex == 0) {
                JOptionPane.showMessageDialog(null, "Can't delete type \"" 
                        + Utils.DEFAULT_TYPE + "\"");
                return;
            }
            //get the selected type
            String selectedType = currentProject.getTypes().get(selectedIndex);
            //if the user does not want to delete this type
            if (!Utils.shouldContinue("Are you sure you wish to delete the type \""
                    + selectedType
                    + "\"?\n"
                    + "All entities of this type will be switched to \""
                    + Utils.DEFAULT_TYPE + "\"\n"
                    + "This operation cannot be undone.", frame)) {
                return;
            }
            //remove the type from the types list and change all entities of
            //that type to the default type
            currentProject.removeType(selectedIndex);
            //update the combo box
            updateTypeComboBox();
            //select the default type
            typeComboBox.setSelectedIndex(0);
        });
        
        frame.getSelectButton().addActionListener((ActionEvent e) -> {
            //Copy the color code to the clipboard
            doSelectAction();
        });
        
        frame.getResultsList().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //if the current entity has been modified
                if (modifiedController.isModified()) {
                    //if the user does not want to continue
                    if (!Utils.shouldContinue("Discard changes?", frame)) {
                        reselectEntityInList();
                        //exit this method
                        return;
                    }
                }
                
                //get the clicked index
                int index = resultsListController.getSelectedIndex();
                //if there is no selection
                if (index == -1) {
                    return;
                }
                //get the entity in the resultsList
                Entity selectedEntity 
                        = resultsListController.getEntitiesInResults().get(index);
                //put the selected entity in the current entity position
                currentProject.setCurrentEntity(selectedEntity);
                //put the current entity into the info panel
                loadEntityIntoInfoPanel(currentProject.getCurrentEntity());
                //we are no longer modifed
                modifiedController.setModified(false);
                
                //if the user double-clicked
                if(e.getClickCount()==2){
                    //Copy the color code to the clipboard
                    doSelectAction();
                }
                
                resultsListSelected = true;
                recentListController.setSelectedIndex(-1);
            }
        });
        
        frame.getRecentList().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //if the current entity has been modified
                if (modifiedController.isModified()) {
                    //if the user does not want to continue
                    if (!Utils.shouldContinue("Discard changes?", frame)) {
                        reselectEntityInList();
                        //exit this method
                        return;
                    }
                }
                
                //get the clicked index
                int index = recentListController.getSelectedIndex();
                //if there is no selection
                if (index == -1) {
                    return;
                }
                //get the entity in the resultsList
                Entity selectedEntity 
                        = recentListController.getEntitiesInRecent().get(index);
                //put the selected entity in the current entity position
                currentProject.setCurrentEntity(selectedEntity);
                //put the current entity into the info panel
                loadEntityIntoInfoPanel(currentProject.getCurrentEntity());
                //we are no longer modifed
                modifiedController.setModified(false);
                
                //if the user double-clicked
                if(e.getClickCount()==2){
                    //Copy the color code to the clipboard
                    doSelectAction();
                }
                
                resultsListSelected = false;
                resultsListController.setSelectedIndex(-1);
            }
        });
        
        frame.getChangeImageButton().addActionListener((ActionEvent e) -> {
            int result = imageChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                //get the user-selected file
                File newFile = imageChooser.getSelectedFile();
                prefs.put(Utils.IMAGE_CHOOSER_PATH, newFile.toString());
                //if the file exists
                if (newFile.exists()) {
                    String name = newFile.getName();
                    //if the file is a valid image
                    if (hasImageExtension(name)) {
                        //pass the new image to the imagePanel
                        frame.getImagePanel().setImagePath(newFile, 
                                currentProject.getCurrentEntity().getColor());
                        //set the image file
                        currentImageFile = newFile;
                        //mark this as a modified entity
                        modifiedController.setModified(true);
                        shouldCopyCurrentImageFile = true;
                    } else {
                        JOptionPane.showMessageDialog(null, 
                                "The selected image is not a supported "
                                        + "image format.", 
                                "Unsupported Format", 
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, 
                            "Could not find file:\n" 
                                    + newFile.toString(), 
                            "File Does Not Exist", 
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        frame.getDeleteImageButton().addActionListener((ActionEvent e) -> {
            //if the user does not want to delete the image
            if (!Utils.shouldContinue("Delete this image?", frame)) {
                //then stop
                return;
            }
            //if the user does choose to delete this image
            imageFileToDelete = currentImageFile;
            //set the currentImageFile to null
            currentImageFile = null;
            //make the ImagePanel show the current entity's color.
            frame.getImagePanel().setImagePath(null, 
                    currentProject.getCurrentEntity().getColor());
            modifiedController.setModified(true);
            shouldCopyCurrentImageFile = false;
        });
        
        frame.getNameTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                modifiedController.setModified(true);
            }
        });
        
        frame.getUnityPrefabTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                modifiedController.setModified(true);
            }
        });
        
        frame.getTypeComboBox().addActionListener((ActionEvent e) -> {
            if (currentProject != null) {
                modifiedController.setModified(true);
            }
        });
        
        frame.getxOffsetSpinner().addChangeListener((ChangeEvent e) -> {
            modifiedController.setModified(true);
        });
        
        frame.getyOffsetSpinner().addChangeListener((ChangeEvent e) -> {
            modifiedController.setModified(true);
        });
        
        frame.getUseCommandCheckBoxMenuItem().addActionListener((ActionEvent e) -> {
            //if we are already using a command
            if (useCommand) {
                //turn off using commands
                useCommand = false;
            }
            //if there is no command set for this project yet
            if (currentProject.getCommand() == null) {
                //if the user does not want to set the command
                if (!Utils.shouldContinue("There is no command set. Would you like\n"
                        + "to set a new command?", frame)) {
                    //deselect the menu item
                    frame.getUseCommandCheckBoxMenuItem().setSelected(false);
                    return;
                }
                //if the user wants to set the command
                setCommand();
                //if the user set a valid command
                if (currentProject.getCommand() != null) {
                    useCommand = true;
                } else {
                    //if the user did not set a valid command
                    //deselect the menu item
                    frame.getUseCommandCheckBoxMenuItem().setSelected(false);
                }
            } else {
                //if there is a command set
                //turn on using commands
                useCommand = true;
            }
        });
        
        frame.getSetCommandMenuItem().addActionListener((ActionEvent e) -> {
            setCommand();
        });
        
        frame.getGenerateScriptsMenuItem().addActionListener((ActionEvent e) -> {
            ScriptGenerator scriptGenerator 
                    = new ScriptGenerator(currentProject, frame);
            scriptGenerator.showDialog(frame);
        });
        
        frame.getPreferencesMenuItem().addActionListener((ActionEvent e) -> {
            showPreferencesDialog();
        });
        
    }
    
    //MARK: Command
    /**
     * Allows the user to set the double-click command by opening the 
     * setCommandDialog. 
     * @return True if the command was set, false if the user decided to
     * cancel setting the command or if the user tried to set the command to
     * a blank command.
     */
    private void setCommand() {
        fillSetCommandDialogContents();
        setCommandDialog.setVisible(true);
    }
    
    private void runCommand() {
        List<String> baseCommand = currentProject.getCommand();
        //this should never happen, but if it does, throw an error
        if (baseCommand == null) {
            System.err.println("Could not find command.");
            return;
        }
        
        //this should never happen, but if it does, throw an error
        if (baseCommand.isEmpty()) {
            System.err.println("Could not find command.");
            return;
        }
        
        //get the current entity's attributes as arguments according to the
        //current project's boolean command preferences.
        List<String> finalCommand = getArguments(currentProject.getCurrentEntity());
        //add the baseCommand to the front of the command
        finalCommand.addAll(0, baseCommand);
        
        
        //actually run the command now
        try {
            ProcessBuilder pb = new ProcessBuilder(finalCommand); //build a command out of a array
            Process p = pb.start();
            p.waitFor();

            BufferedReader stdin = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stderr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            
            //create a StringBuilder to hold the output
            StringBuilder output = new StringBuilder();
            
            output.append("Command output:\n");
            String line = null;
            //read in each line of the stdin input
            while ((line = stdin.readLine()) != null) {
                output.append(line);
            }

            //create a StringBuilder to hold the error output
            StringBuilder error = new StringBuilder();
            //read in each line of the stderr input
            while ((line = stderr.readLine()) != null) {
                error.append(line);
            }
            //if there is some error output
            if (error.length() < 0) {
                //add a line to indicate the start off error output
                output.append("Error:\n");
                //append the error StringBuilder
                output.append(error);
            }
            System.out.println(output.toString());
        } catch (IOException ex) {
            System.err.println("I/O Error.\n" + ex.toString());
        } catch (InterruptedException ex) {
            System.err.println("Script interrupted.\n" + ex.toString());
        }
    }
    
    private void setUpSetCommandDialogActionListeners() {
        ActionListener checkBoxActionListener = (ActionEvent e) -> {
            //set the command booleans appropriately using the checkboxes
            currentProject.setCommandBooleans(
                    setCommandDialog.getColorHexValueCheckBox().isSelected(), 
                    setCommandDialog.getRedCheckBox().isSelected(), 
                    setCommandDialog.getGreenCheckBox().isSelected(), 
                    setCommandDialog.getBlueCheckBox().isSelected(), 
                    setCommandDialog.getNameCheckBox().isSelected(), 
                    setCommandDialog.getTypeCheckBox().isSelected(), 
                    setCommandDialog.getUnityPrefabCheckBox().isSelected());
            updateExampleCommandTextField();
        };
        
        setCommandDialog.getColorHexValueCheckBox().addActionListener(
                checkBoxActionListener);
        setCommandDialog.getRedCheckBox().addActionListener(
                checkBoxActionListener);
        setCommandDialog.getGreenCheckBox().addActionListener(
                checkBoxActionListener);
        setCommandDialog.getBlueCheckBox().addActionListener(
                checkBoxActionListener);
        setCommandDialog.getNameCheckBox().addActionListener(
                checkBoxActionListener);
        setCommandDialog.getTypeCheckBox().addActionListener(
                checkBoxActionListener);
        setCommandDialog.getUnityPrefabCheckBox().addActionListener(
                checkBoxActionListener);
        
        setCommandDialog.getEnterCommandTextField().addKeyListener(
                new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                updateExampleCommandTextField();
            }
        });
        
        setCommandDialog.getSetButton().addActionListener((ActionEvent e) -> {
            //get the command the user entered
            String commandString 
                = setCommandDialog.getEnterCommandTextField().getText();
            //split the command using the spaces
            String[] commandArray = commandString.split(" ");
            //convert that split into a list
            List<String> command = new ArrayList(Arrays.asList(commandArray));
            
            //if the command is not empty
            if (!command.isEmpty()) {
                //set the current command
                currentProject.setCommand(command);
                //close the dialog
                setCommandDialog.setVisible(false);
                //save the project
                saveProject();
            } else {
                JOptionPane.showMessageDialog(null, 
                        "Command cannot be blank.", 
                        "Invalid Input", 
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        
        setCommandDialog.getCancelButton().addActionListener(
                (ActionEvent e) -> {
            //simply close the window
            setCommandDialog.setVisible(false);
        });
    }
    
    private void updateExampleCommandTextField() {
        //create an example entity
        Entity exampleEntity = new Entity(null, "Name", "Type", 
                Color.pink, "Prefab", Offset.zero);
        //get the arguments that the user desires using the exampleEntity
        List<String> arguments = getArguments(exampleEntity);
        //get the user's command
        String commandString 
                = setCommandDialog.getEnterCommandTextField().getText();
        //trim the command
        commandString = commandString.trim();
        //add the arguments to the command
        StringBuilder s = new StringBuilder(commandString);
        for (String arg : arguments) {
            s.append(" ");
            s.append(arg);
        }
        //put the full command in the example textfield
        setCommandDialog.getExampleTextField().setText(s.toString());
    }
    
    /**
     * Converts the passed in entity's attributes to arguments according to
     * the currentProject's command boolean values.
     * @param entity The entity whose attributes will be used to form the
     * arguments
     * @return A string with the entity's attributes separated by spaces (with
     * one space before the arguments).
     */
    private List<String> getArguments(Entity entity) {
        List<String> commandList = new ArrayList();
        int r = entity.getR();
        int g = entity.getG();
        int b = entity.getB();
        int a = entity.getA();
        if (currentProject.isIncludeColorHex()) {
            String hex = String.format("%02X%02X%02X%02X", r, g, b, a);
            commandList.add(hex);
        }
        if (currentProject.isIncludeRed()) {
            commandList.add(Integer.toString(r));
        }
        if (currentProject.isIncludeGreen()) {
            commandList.add(Integer.toString(g));
        }
        if (currentProject.isIncludeBlue()) {
            commandList.add(Integer.toString(b));
        }
        if (currentProject.isIncludeName()) {
            commandList.add(entity.getName());
        }
        if (currentProject.isIncludeType()) {
            commandList.add(entity.getType());
        }
        if (currentProject.isIncludeUnityPrefab()) {
            commandList.add(entity.getUnityPrefab());
        }
        return commandList;
    }
    
    /**
     * Fills the commandDialogContents with the currentProject's command and
     * command booleans.
     */
    private void fillSetCommandDialogContents() {
        List<String> commandList = currentProject.getCommand();
        
        //convert the command list into a string
        StringBuilder s = new StringBuilder();
        boolean first = true;
        for (String part : commandList) {
            //if this is not the first element in the list
            if (!first) {
                //append a space
                s.append(" ");
            }
            //append this element of the list
            s.append(part);
            //this is no longer the first element in the list
            first = false;
        }
        //put the final string in the textField
        setCommandDialog.getEnterCommandTextField().setText(s.toString());
        
        //set up the checkboxes
        setCommandDialog.getColorHexValueCheckBox().setSelected(
                currentProject.isIncludeColorHex());
        setCommandDialog.getRedCheckBox().setSelected(
                currentProject.isIncludeRed());
        setCommandDialog.getGreenCheckBox().setSelected(
                currentProject.isIncludeGreen());
        setCommandDialog.getBlueCheckBox().setSelected(
                currentProject.isIncludeBlue());
        setCommandDialog.getNameCheckBox().setSelected(
                currentProject.isIncludeName());
        setCommandDialog.getTypeCheckBox().setSelected(
                currentProject.isIncludeType());
        setCommandDialog.getUnityPrefabCheckBox().setSelected(
                currentProject.isIncludeUnityPrefab());
        
        updateExampleCommandTextField();
    }
    
    //MARK: Preferences
    /**
     * Sets up the preferences to match the user's preferences, then displays
     * the Preferences dialog.
     */
    private void showPreferencesDialog() {
        //set up the checkboxes to reflect the current preferences state
        preferencesDialog.getHashCheckBox().setSelected(
                colorPickerController.includeHashTag());
        preferencesDialog.getAlphaCheckBox().setSelected(
                colorPickerController.includeAlpha());
        preferencesDialog.getOffsetCheckBox().setSelected(includeOffset);
        
        //Make it so the user can press enter to select "OK"
        preferencesDialog.getRootPane().setDefaultButton(
                preferencesDialog.getOkButton());
        
        preferencesDialog.setVisible(true);
    }
    
    private void setUpPreferencesDialogActionListeners() {
        preferencesDialog.getCancelButton().addActionListener((ActionEvent e) -> {
            preferencesDialog.setVisible(false);
        });
        
        preferencesDialog.getOkButton().addActionListener((ActionEvent e) -> {
            applyPreferencesChanges();
            //hide the window
            preferencesDialog.setVisible(false);
        });
        
        preferencesDialog.getApplyButton().addActionListener((ActionEvent e) -> {
            applyPreferencesChanges();
        });
    }
    
    private void applyPreferencesChanges() {
        //make the booleans match the checkboxes in the preferences window
        includeHashTag = preferencesDialog.getHashCheckBox().isSelected();
        includeAlpha = preferencesDialog.getAlphaCheckBox().isSelected();
        includeOffset = preferencesDialog.getOffsetCheckBox().isSelected();
        
        //include alpha
        colorPickerController.setIncludeAlpha(includeAlpha);

        //include hashtag
        colorPickerController.setIncludeHashTag(includeHashTag);

        //include offset
        //show/hide the offset panel depending on the value the user checked
        frame.getOffsetPanel().setVisible(includeOffset);
    }
    
    /**
     * Loads all user preferences from the system
     */
    private void loadPreferences() {
        // This will define a node in which the preferences can be stored
        prefs = Preferences.userRoot().node(this.getClass().getName());

        //Get the file paths from user preferences (return the current directory if 
        //no preference was set yet):
        String imageChooserFilePath 
                = prefs.get(Utils.IMAGE_CHOOSER_PATH, 
                        System.getProperty("user.dir"));
        String newProjectChooserFilePath 
                = prefs.get(Utils.NEW_PROJECT_CHOOSER_PATH, 
                        System.getProperty("user.dir"));
        String openProjectChooserFilePath 
                = prefs.get(Utils.OPEN_PROJECT_CHOOSER_PATH, 
                        System.getProperty("user.dir"));
        
        File imageChooserFile = new File(imageChooserFilePath);
        File newProjectChooserFile = new File(newProjectChooserFilePath);
        File openProjectChooserFile = new File(openProjectChooserFilePath);
        
        imageChooser.setCurrentDirectory(imageChooserFile);
        newProjectChooser.setCurrentDirectory(newProjectChooserFile);
        openProjectChooser.setCurrentDirectory(openProjectChooserFile);
    }
    
    //MARK: Add Entity
    /**
     * Copies the passed in image and stores it in the resources folder. The
     * result of this is passed directly to the entity to replace it's image
     * value.
     * 
     * @return The name of the currentImageFile if the user wants to overwrite
     * any identically named image, or if the image was copied successfully. 
     * Null if the currentImageFile is null, if the resources folder could not
     * be found, or if the image could not be copied.
     */
    private String copyCurrentImageFileToResources() {
        //if there is no image to copy
        if (currentImageFile == null) {
            //just return null
            return null;
        }
        //get the name of the original image
        String imageName = currentImageFile.getName();
        File copiedImageFile = currentProject.getResource(imageName);
        if (copiedImageFile == null) {
            //tell the user that the resources folder does not exist
            JOptionPane.showMessageDialog(null, 
                    "The Resources folder could not be found.", 
                    "Folder Not Found", 
                    JOptionPane.ERROR_MESSAGE);
            //the file could not be copied
            return null;
        }
        
        if (copiedImageFile.exists()) {
            //if the user does NOT want to continue
            if (!Utils.shouldContinue("The image " + imageName + " already exists "
                    + "in the project Resources folder. Are you sure you wish "
                    + "to overwrite this image?", frame)) {
                //the user wants to use the already existing image as the image
                //for this entity. (this way, two entities will likely be using
                //the same image)
                return currentImageFile.getName();
            }
            //if the user wants to overwrite the existing image
        }
        //if the image does not exist in the Resources folder or
        //the user wants to overwrite the image
        //save the file
        try {
            //retrieve the image data from the currentImageFile
            BufferedImage bi = ImageIO.read(currentImageFile);
            String extension = "";
            //get the start of the extension
            int i = imageName.lastIndexOf('.');
            //if the imageName has an extension
            if (i > 0) {
                //get the extension
                extension = imageName.substring(i+1);
            }
            //save the image to the Resources folder in the copiedImageFile
            ImageIO.write(bi, extension, copiedImageFile);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Could not "
                + "copy image into Resources folder:\n" 
                + ex.toString(), "Image Read/Write Error",
                JOptionPane.ERROR_MESSAGE);
            //the image could not be copied
            return null;
        }
        //the image was copied successfully
        return currentImageFile.getName();
    }
    
    /**
     * Deletes the old image (that is about to be replaced by the new image), 
     * but only if the old image is not being used by any other entities. If
     * the old image does not exist, then this simply does nothing.
     */
    private void deleteOldImageFileFromResources() {
        Entity currentEntity = currentProject.getCurrentEntity();
        //if there is no image to delete
        if (currentEntity.getImage() == null) {
            return;
        }
        /*
        Now we have to delete the old, unused image so that we
        don't collect a bunch of useless image files.
        */
        boolean shouldDeleteImage = true;
        //cycle through all the current project's entities
        for (Entity e : currentProject.getEntities()) {
            //if e's image is null, then it does not match the currentEntity's
            //image file and we can skip this e.
            if (e.getImage() != null) {
                //if e's imageName matches currentEntity's imageName
                if (e.getImage().equals(currentEntity.getImage())) {
                    //if e is NOT the currentEntity
                    if (!e.equals(currentEntity)) {
                        //this image is being used by another entity and we
                        //should NOT delete it.
                        shouldDeleteImage = false;
                        //get out of the loop
                        break;
                    }
                }
            }
        }

        //if we should delete the image, because it's not being used by any
        //of the other entities
        if (shouldDeleteImage) {
            //get the file that we are deleting
            File imageToDelete 
                    = currentProject.getResource(currentEntity.getImage());
            //delete the image
            imageToDelete.delete();
        }
    }
    
    /**
     * Get the path representing the project Resources folder
     * @return The Path object representing the project Resources folder, or
     * null if the resources folder could not be found
     */
    private Path getResourcesFolderPath() {
        //convert the projectLocation into a Path
        Path projectLocationPath = projectLocation.toPath();
        //find the resources path within the projectLocation
        Path resourcesFolderPath 
                = Paths.get(projectLocationPath.toString(), "Resources");
        //convert the resources folder path to a string
        File resourcesFolderFile = resourcesFolderPath.toFile();
        //if the resources folder does not exist
        if (!resourcesFolderFile.exists()) {
            //the resources folder could not be found
            return null;
        }
        return resourcesFolderPath;
    }
    
    /**
     * Generates a new entity with default attributes.
     * @throws Exception if the user can't addEntity a new entity (the reason is
 in the exception's description).
     */
    private Entity generateNewDefaultEntity() {
        // Set the color of the entity
        Color newColor = getUniqueDefaultColor();
        //if newColor is null
        if (newColor == null) {
            //tell the user they have run out of colors
            return null;
        }

        String newType = currentProject.getTypes().get(0);

        String newName = getUniqueDefaultName();

        String newUnityPrefab = getUniqueDefaultUnityPrefab();

        //create and return a new entity with the attributes created above
        return new Entity(null, newName, newType, newColor, 
                newUnityPrefab, Offset.zero);
    }
    
    private Color getUniqueDefaultColor() {
        List<Entity> entities = currentProject.getEntities();
        boolean colorIsTaken = false;
        for (int b = 0; b < 255; b++) {
            for (int g = 0; g < 255; g++) {
                for (int r = 0; r < 255; r++) {
                    //create a new color from these values
                    Color color = new Color(r, g, b);
                    
                    for (Entity entity : entities) {
                        //if this color is taken
                        if (entity.getColor().equals(color)) {
                            //this color is taken
                            colorIsTaken = true;
                            //leave this loop
                            break;
                        }
                    }
                    //if the color is not taken
                    if (!colorIsTaken) {
                        return color;
                    } else {
                        //reset colorIsTaken for the next color
                        colorIsTaken = false;
                        //continue searching
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
        List<Entity> entities = currentProject.getEntities();
        String nameBase = "Entity ";
        int num = 1;
        //keep incrementing the num component until we find a unique string
        //(i.e. a name that is not already in entities)
        for (Entity entity : entities) {
            //if this entity's name matches the generated name
            //(if this name is already taken)
            if (entity.getName().equals(nameBase + num)) {
                //increment the number
                num++;
            }
        }
        //return the unique name
        return nameBase + num;
    }
    
    private String getUniqueDefaultUnityPrefab() {
        List<Entity> entities = currentProject.getEntities();
        String prefabBase = "Prefabs/Prefab";
        int num = 1;
        //keep incrementing the num component until we find a unique string
        //(i.e. a prefab that is not already in entities)
        for (Entity entity : entities) {
            //if this entity's prfab matches the generated prefab
            //(if this prefab is already taken)
            if (entity.getUnityPrefab().equals(prefabBase + num)) {
                //increment the number
                num++;
            }
        }
        //return the unique prefab string
        return prefabBase + num;
    }
    
    /**
     * When an entity is changed or a new entity is added, we must refresh the
     * search results so that the change or addition is reflected in the results
     * list JList side bar. 
     * @param entity 
     */
    private void updateSearchResultsToReflectEntityChangeOrAddition(
            Entity entity) {
        searchController.search();
        /*
        Now we need to find out if the newly created entity matches
        the search criteria
        */
        //get the entities in results
        List<Entity> entitiesInResults 
                = resultsListController.getEntitiesInResults();
        //get the index of the newEntity if it exists
        int index = entitiesInResults.indexOf(entity);
        //if the newEntity is not in the results list
        if (index == -1) {
            //clear the selection
            resultsListController.clearSelection();
        } else {
            //if the newEntity is in the results list,
            //then update the selection
            resultsListController.setSelectedIndex(index);
        }
    }
    
    /**
     * Sets up the spinner models so that the offset spinners can handle
     * decimal numbers. 
     */
    private void setUpOffsetSpinners() {
        SpinnerNumberModel xOffsetSpinnerModel 
                = new SpinnerNumberModel(0, -Double.MAX_VALUE, Double.MAX_VALUE, 0.5);
        SpinnerNumberModel yOffsetSpinnerModel 
                = new SpinnerNumberModel(0, -Double.MAX_VALUE, Double.MAX_VALUE, 0.5);
        frame.getxOffsetSpinner().setModel(xOffsetSpinnerModel);
        frame.getyOffsetSpinner().setModel(yOffsetSpinnerModel);
    }
    
    //MARK: Open Project
    private void openProject() {
        //only allow the user to open project files (.lecp)
        openProjectChooser.setFileFilter(projectFileFilter);
        //allow the user to choose a file
        int result = openProjectChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            //get the user's selected file
            File tempFile = openProjectChooser.getSelectedFile();
            //save the current directory to the user's preferences
            prefs.put(Utils.OPEN_PROJECT_CHOOSER_PATH, tempFile.toString());
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
                loadProject(tempProject, tempFile.getParentFile());
                projectOpenEnableMenuItems();
            } catch (JAXBException ex) {
                //tell the user that you couldn't
                JOptionPane.showMessageDialog(frame, "Could not deserialize"
                        + " the project file:\n" + tempFile.getAbsolutePath()
                        + "\n" + ex.toString());
            }
            
        }
        
    }
    
    private void projectOpenEnableMenuItems() {
        //enable the menu items
        frame.getAddEntityMenuItem().setEnabled(true);
        frame.getDeleteEntityMenuItem().setEnabled(true);
        frame.getSetCommandMenuItem().setEnabled(true);
        frame.getUseCommandCheckBoxMenuItem().setEnabled(true);
        frame.getGenerateScriptsMenuItem().setEnabled(true);
    }
    
    /**
     * This function is called once the project is successfully unmarshalled, 
     * or deserialized, from xml .lecp file to a project object. This sets up
     * the project for use, loads data into the JLists, sets up the GUI, etc.
     * 
     * @param newProject A project recently deserialized from .lecp xml format.
     * @param projectLocationFile The file that holds the project's location,
     * or parent directory.
     */
    private void loadProject(Project newProject, File projectLocationFile) {
        currentProject = newProject;
        searchController.setCurrentProject(newProject);
        newProject.setProjectLocation(projectLocationFile);
        newProject.createProjectLocationAndResourceFolder();
        resultsListController.setProjectResourceLocation(
                newProject.getProjectResourceFolder());
        recentListController.setProjectResourceLocation(
                newProject.getProjectResourceFolder());
        projectLocation = projectLocationFile;
        //get path to the resources folder
        Path resourcesFolderPath = getResourcesFolderPath();
        //if the resources folder could be found
        if (resourcesFolderPath != null) {
            //turn the resources folder path into a file
            File resourcesFolderFile = resourcesFolderPath.toFile();
            //give the resultsList's EntityListRenderer a reference to the
            //resources folder file
            resultsListController.setProjectResourceLocation(resourcesFolderFile);
            recentListController.setProjectResourceLocation(resourcesFolderFile);
        }
        
        projectName = newProject.getName();
        //update the typeComboBox with the new project's types
        updateTypeComboBox();
        
        //empty the searchTextField
        frame.getSearchTextField().setText("");
        //get a list of all the project's new entities
        List<Entity> allNewEntities = newProject.getEntities();
        //put all the entities in the results list
        resultsListController.setEntitiesInResults(allNewEntities);
        //if there is at least one element in the project
        if (allNewEntities.size() > 0) {
            //select the first element
            resultsListController.setSelectedIndex(0);
            //set the current entity
            newProject.setCurrentEntity(allNewEntities.get(0));
            //load the entity into the info panel
            loadEntityIntoInfoPanel(newProject.getCurrentEntity());
            setSearchElementsEnabled(true);
            setInfoElementsEnabled(true);
        }
        modifiedController.setModified(false);
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
     * @returns True if this process was successful, false if there was a
     * problem and you should stop creating a new project.
     */
    private boolean createNewProject() throws JAXBException, Exception {
        // Create the directory that the user designated for the project:
        //Fist, convert the projectLocation to a path object
        Path tempProjectLocationPath = projectLocation.toPath();
        //add the projectName to the path to get the project folder
        Path projectLocationPath = Paths.get(tempProjectLocationPath.toString(), 
                projectName);
        //and store it in a file object
        File tempProjectLocation = projectLocationPath.toFile();
        //if the project location already exists
        if (tempProjectLocation.exists()) {
            //if the user wants to continue
            if (Utils.shouldContinue("The project folder already exists at\n"
                    + tempProjectLocation.getAbsolutePath()
                    + "\nAre you sure you wish to continue?", frame)) {
                //if the projectLocation is not empty
                if (tempProjectLocation.list().length > 0) {
                    //if the user does NOT want to continue
                    if (!Utils.shouldContinue("The project folder at\n"
                            + tempProjectLocation.getAbsolutePath()
                            + "\nis not empty. Are you sure you wish to continue?", 
                            frame)) {
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
        
        // Create the project Resources folder
        //if the project Resources folder could NOT be created
        if (!createNewProjectResourcesFolder()) {
            //return without throwing an exception and without
            //creating the new project, because the user chose
            //not to continue.
            return false;
        }
        
        //At this point, the name and location are assigned.
        //create the new project using the name and location created
        //specifically for this project
        currentProject = new Project(projectName, projectLocation);
        currentProject.createProjectLocationAndResourceFolder();
        resultsListController.setProjectResourceLocation(
                currentProject.getProjectResourceFolder());
        recentListController.setProjectResourceLocation(
                currentProject.getProjectResourceFolder());
        searchController.setCurrentProject(currentProject);
        currentProject.addType(Utils.DEFAULT_TYPE);
        updateTypeComboBox();
        //serialize the new project
        serializeNewProjectToXML();
        enterNewProjectState();
        projectOpenEnableMenuItems();
        //reset the info panel
        Entity blankEntity = generateNewDefaultEntity();
        loadEntityIntoInfoPanel(blankEntity);
        //make sure the modified is set to false
        modifiedController.setModified(false);
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
            if (!Utils.shouldContinue("The file " + tempFile.getName() + "already exists "
                    + "at \n" + tempFile.getAbsolutePath() + ".\nWould you like to "
                    + "overwrite this file?\n"
                    + "Please note: Overwriting this file will erase all data "
                    + "in " + tempFile.getName() + ". This cannot be undone.", 
                    frame)) {
                return false;
            }
            //if the user does want to continue, continue
        }
        //if the tempFile does not exist or the user wants to overwrite this file
        
        //At this point, everything is correct and we can assign the projectFile
        //and the projectLocation to their temp values.
        projectLocation = tempProjectLocation;
        //get path to the resources folder
        Path resourcesFolderPath = getResourcesFolderPath();
        //if the resources folder could be found
        if (resourcesFolderPath != null) {
            //turn the resources folder path into a file
            File resourcesFolderFile = resourcesFolderPath.toFile();
            //give the resultsList's EntityListRenderer a reference to the
            //resources folder file
            resultsListController.setProjectResourceLocation(resourcesFolderFile);
            recentListController.setProjectResourceLocation(resourcesFolderFile);
        }
        return true;
    }
    
    /**
     * Creates the Resources folder in the project's directory. 
     * 
     * @return True if the folder was created with no problems, false if the
     * file could not be created for some reason.
     */
    private boolean createNewProjectResourcesFolder() {
        Path projectLocationPath = projectLocation.toPath();
        Path tempResourcesPath 
                = Paths.get(projectLocationPath.toString(), "Resources");
        File tempResourcesFile = tempResourcesPath.toFile();
        if (tempResourcesFile.exists()) {
            //if the user does NOT want to continue
            if (!Utils.shouldContinue("The folder " + tempResourcesFile.getName() 
                    + "already exists at \n" 
                    + tempResourcesFile.getAbsolutePath() 
                    + ".\nWould you like to continue?\n"
                    + "Please note: Keeping other files in the "
                    + tempResourcesFile.getName()
                    + " folder may cause problems.", frame)) {
                return false;
            }
            //if the user does want to continue, continue
        }
        //if the tempResourcesFile does not exist 
        //or the user wants to overwrite this file, then
        //create the folder
        tempResourcesFile.mkdir();
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
        Path projectLocationPath = currentProject.getProjectLocation().toPath();
        File projectFile
                = Paths.get(projectLocationPath.toString(), 
                        currentProject.getName() + ".lecp").toFile();
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
        
        //get the user's pref for project location
        String newProjectLocation = prefs.get(Utils.NEW_PROJECT_CHOOSER_PATH, 
                System.getProperty("user.dir"));
        //initialize the project location to the user's prefs
        projectLocation = new File(newProjectLocation);
        pNameTextField.setText("New Project");
        //fill in the project location that the user prefs had
        pLocationTextField.setText(newProjectLocation);
        //add on the project name
        Path defaultPath = Paths.get(newProjectLocation, "New Project");
        //update the project folder with the user's prefs
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
            newProjectChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = newProjectChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File tempProjectLocation = newProjectChooser.getSelectedFile();
                if (tempProjectLocation.exists()) {
                    if (tempProjectLocation.isDirectory()) {
                        //assign the project location.
                        projectLocation = tempProjectLocation;
                        //save the new starting directory to the user's prefs
                        prefs.put(Utils.NEW_PROJECT_CHOOSER_PATH, 
                                tempProjectLocation.toString());
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
            //if the user does not want to create a new project, then we
            //have to restore some of the variables we changed.
            projectLocation = currentProject.getProjectLocation();
            projectName = currentProject.getName();
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
    
    public void enterNewProjectState() {
        resultsListController.clearEntities();
        recentListController.clearEntities();
        setSearchElementsEnabled(false);
        setInfoElementsEnabled(false);
        modifiedController.setModified(false);
    }
    
    //MARK: Misc
    private void reselectEntityInList() {
            /*
            We have to re-select the currently selected entity in
            the results list JList:
            */

            List<Entity> listEntities;
            //if the resultsList was the last one selected
            if (resultsListSelected) {
                //get the entities in the results
                listEntities 
                        = resultsListController.getEntitiesInResults();
            } else {
                //get the entities in the recent
                listEntities 
                        = resultsListController.getEntitiesInResults();
            }

            //get the currently selected entity in the list
            Entity currentEntity = currentProject.getCurrentEntity();
            //get the index of the currently selected entity
            int index 
                    = listEntities.indexOf(currentEntity);
            //if the currentEntity is NOT in the list
            if (index == -1) {
                //exit this method
                return;
            }

            //if the results list was the last selected list
            if (resultsListSelected) {
                //if the currentEntity is in the results list
                //reset the selection to the currentEntity
                resultsListController.setSelectedIndex(index);
                recentListController.setSelectedIndex(-1);
            } else {
                //if the recent list was the last selected list
                //if the currentEntity is in the recent list
                //reset the selection to the currentEntity
                recentListController.setSelectedIndex(index);
                resultsListController.setSelectedIndex(-1);
        }
    }
    
    private void loadEntityIntoInfoPanel(Entity entity) {
        frame.getNameTextField().setText(entity.getName());
        //get the index of this entity's type
        int index = currentProject.getTypes().indexOf(entity.getType());
        //set the selected type
        frame.getTypeComboBox().setSelectedIndex(index);
        //get the image file within the Resources folder
        File imageFile = getEntityImageFile(entity);
        //load the image file into the ImagePanel
        frame.getImagePanel().setImagePath(imageFile, 
                entity.getColor());
        //set the current image
        currentImageFile = imageFile;
        //set the color values
        colorPickerController.setColor(
                entity.getR(), 
                entity.getG(),
                entity.getB(), 
                entity.getA());
        //set the offset values
        frame.getxOffsetSpinner().setValue(entity.getOffset().getX());
        frame.getyOffsetSpinner().setValue(entity.getOffset().getY());
    }
    
    /**
     * Finds the path to the entity's image within the project's Resources 
     * folder.
     * @param entity The entity for which the image must be found.
     * @return The file that points to the entity's image within the project's
     * Resources folder, or null if the entity has no image or the file could
     * not be found.
     */
    public File getEntityImageFile(Entity entity) {
        Path resourcesFolderPath = getResourcesFolderPath();
        if (resourcesFolderPath == null) {
            //tell the user that the resources folder does not exist
            JOptionPane.showMessageDialog(null, 
                    "The Resources folder could not be found.", 
                    "Folder Not Found", 
                    JOptionPane.ERROR_MESSAGE);
            //the image file could not be found
            return null;
        }
        //if the entity's image is not null
        if (entity.getImage() == null) {
            return null;
        } else {
            //get the entity's image within the Resources folder
            Path entityImagePath = Paths.get(resourcesFolderPath.toString(),
                    entity.getImage());
            //convert the path into a file
            File entityImageFile = entityImagePath.toFile();
            //if the file exists
            if (entityImageFile.exists()) {
                //return the file with the entity's image
                return entityImageFile;
            } else {
                //the image file does not exist
                return null;
            }
        }
    }
    
    public void setSearchElementsEnabled(boolean value) {
        //search panel
        frame.getTypeComboBox().setEnabled(value);
        frame.getSearchTextField().setEnabled(value);
    }
    
    private void setInfoElementsEnabled(boolean value) {
        //info panel
        frame.getSelectButton().setEnabled(value);
        frame.getNameTextField().setEnabled(value);
        frame.getTypeComboBox().setEnabled(value);
        frame.getNewTypeButton().setEnabled(value);
        frame.getDeleteTypeButton().setEnabled(value);
        frame.getChangeImageButton().setEnabled(value);
        frame.getDeleteImageButton().setEnabled(value);
        frame.getColorCodeTextField().setEnabled(value);
        frame.getRedSlider().setEnabled(value);
        frame.getRedSpinner().setEnabled(value);
        frame.getGreenSlider().setEnabled(value);
        frame.getGreenSpinner().setEnabled(value);
        frame.getBlueSlider().setEnabled(value);
        frame.getBlueSpinner().setEnabled(value);
        frame.getAlphaSlider().setEnabled(value);
        frame.getAlphaSpinner().setEnabled(value);
        frame.getUnityPrefabTextField().setEnabled(value);
        frame.getxOffsetSpinner().setEnabled(value);
        frame.getyOffsetSpinner().setEnabled(value);
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
     * Loads the user-edited information from the info panel into the project,
     * replacing the project's currentEntity info with that info.
     */
    private void loadEntityFromInfoPanelIntoProject() {
        //put the current entity in a variable for easy access
        Entity currentEntity = currentProject.getCurrentEntity();
        //initialize the newImage to the old image
        String newImage = currentEntity.getImage();
        //if the user chose a new image and we should copy the currentImageFile
        if (shouldCopyCurrentImageFile) {
            //copy the currentImageFile to the project Resources folder and
            //get the name of the file where the new copied image is (or get
            //null if the image could not be created)
            newImage = copyCurrentImageFileToResources();
            //delete the old image file if it's not being used
            deleteOldImageFileFromResources();
        }
        //if the image hasn't changed or if the image did change and we have
        //the newImage. 
        //reset the flag
        shouldCopyCurrentImageFile = false;
        
        String newName = frame.getNameTextField().getText();
        int newTypeIndex = frame.getTypeComboBox().getSelectedIndex();
        String newType = currentProject.getTypes().get(newTypeIndex);
        Color newColor = colorPickerController.getColor();
        String newUnityPrefab = frame.getUnityPrefabTextField().getText();
        Offset newOffset = getOffsetFromInfoPanel();
        //load all the new values into the currentEntity
        currentEntity.replaceValues(newImage, newName, 
                newType, newColor, newUnityPrefab, newOffset);
    }
    
    /**
     * Checks to make sure that the values in the offset panels are valid
     * and returns the appropriate offset value.
     * 
     * @return The new offset if the spinner values are valid, the old offset
     * if not.
     */
    private Offset getOffsetFromInfoPanel() {
        //"ensure manually typed values with the editor 
        //are propagated to the models"
        try {
            //add manually typed value to the models
            frame.getxOffsetSpinner().commitEdit();
            frame.getyOffsetSpinner().commitEdit();
        } catch (ParseException ex ) {
            //if the manually typed values are not formatted properly
            System.err.println(ex.toString());
            //reset the spinners to the previous offset values
            frame.getxOffsetSpinner().setValue(
                    currentProject.getCurrentEntity().getOffset().getX());
            frame.getyOffsetSpinner().setValue(
                    currentProject.getCurrentEntity().getOffset().getY());
        }
        //get the spinner values and create an Offset object from them
        double xVal = (Double) frame.getxOffsetSpinner().getValue();
        double yVal = (Double) frame.getyOffsetSpinner().getValue();
        return new Offset(xVal, yVal);
    }
    
    /**
     * Delete the image that the user chose to delete
     */
    private void deleteImageFileToDelete() {
        //if there is no image to delete
        if (imageFileToDelete == null) {
            return;
        }
        //get the path of the image to delete
        Path path = imageFileToDelete.toPath();
        try {
            Files.delete(path);
        } catch (NoSuchFileException x) {
            System.err.format("%s: no such" + " file or directory%n", path);
        } catch (DirectoryNotEmptyException x) {
            System.err.format("%s not empty%n", path);
        } catch (IOException x) {
            // File permission problems are caught here.
            System.err.println(x);
        }
        //set the file to delete to null for next time
        imageFileToDelete = null;
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

    private void doSelectAction() {
        if (useCommand) {
            runCommand();
        } else {
            copyCurrentColorCodeToClipboard();
        }
        
        if (resultsListSelected) {
            //add the currently selected entity to the recent list
            recentListController.addEntity(currentProject.getCurrentEntity());
        }
    }
    
    private void copyCurrentColorCodeToClipboard() {
        Entity currentEntity = currentProject.getCurrentEntity();
        int r = currentEntity.getR();
        int g = currentEntity.getG();
        int b = currentEntity.getB();
        int a = currentEntity.getA();
        String colorString;
        if (colorPickerController.includeHashTag()) {
            if (colorPickerController.includeAlpha()) {
                colorString = String.format("#%02X%02X%02X%02X", r, g, b, a);
            } else {
                colorString = String.format("#%02X%02X%02X", r, g, b);
            }
        } else {
            if (colorPickerController.includeAlpha()) {
                colorString = String.format("%02X%02X%02X%02X", r, g, b, a);
            } else {
                colorString = String.format("%02X%02X%02X", r, g, b);
            }
        }
        StringSelection stringSelection = new StringSelection(colorString);
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(stringSelection, null);
    }

    private boolean hasImageExtension(String fileName) {
        return fileName.matches(".*\\.png") ||
                fileName.matches(".*\\.jpe?g") ||
                fileName.matches(".*\\.gif") ||
                fileName.matches(".*\\.tiff?");
    }
    
}
