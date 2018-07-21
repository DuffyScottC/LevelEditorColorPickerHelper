/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scriptgeneration;

import controllers.Utils;
import entities.Entity;
import entities.Offset;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.FileFilter;
import java.util.HashSet;
import java.util.Set;
import javax.imageio.ImageIO;
import projects.Project;
import views.MainFrame;
import views.ScriptGeneratorDialog;

enum ScriptType {
    GameObject,
    Tilemap,
    Mixed
}

/*
    switch (scriptType) {
        case GameObject:
            break;
        case Tilemap:
            break;
        default:

    }
*/

/**
 * Handles generating scripts
 * @author Scott
 */
public class ScriptGenerator {
    
    private final Project project;
    private final ScriptGeneratorDialog dialog;
    private List<String>  types;
    private List<String>  formattedTypes;
    private ScriptType scriptType = ScriptType.GameObject;
    
    //GameObject
    private double gridSize = 1;
    
    //Tilemap
    private Three cellSize = new Three(1, 1, 0);
    private Three cellGap = new Three(0, 0, 0);
    
    /**
     * Creates a new script generator for the passed in project.
     * @param project The project to generate scripts for
     * @param frame The frame to set the dialog relative to
     */
    public ScriptGenerator(Project project, MainFrame frame) {
        this.project = project;
        dialog = new ScriptGeneratorDialog(frame, true);
        //make it so the user can press enter to generate
        dialog.getRootPane().setDefaultButton(dialog.getGenerateButton());
        
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        chooser.setMultiSelectionEnabled(false);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        //setup the combo box
        JComboBox typeComboBox = dialog.getTypeComboBox();
        //remove all items in the type combo box
        typeComboBox.removeAllItems();
        typeComboBox.addItem("GameObject");
        typeComboBox.addItem("Tilemap");
        typeComboBox.addItem("Mixed");
        
        //initialize the type as GameObject
        chooseType(ScriptType.GameObject);
        
        typeComboBox.addActionListener((ActionEvent e) -> {
            int index = typeComboBox.getSelectedIndex();
            switch (index) {
                case 0:
                    chooseType(ScriptType.GameObject);
                    break;
                case 1:
                    chooseType(ScriptType.Tilemap);
                    break;
                default:
                    chooseType(ScriptType.Mixed);
            }
        });
        
        dialog.getBrowseButton().addActionListener((ActionEvent e) -> {
            int result = chooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFolder = chooser.getSelectedFile();
                if (selectedFolder.exists()) {
                    if (selectedFolder.isDirectory()) {
                        //set the folder text field
                        dialog.getDesitnationFolderTextField().setText(
                                selectedFolder.toString());
                    }
                }
            }
        });
        
        dialog.getImageBrowseButton().addActionListener((ActionEvent e) -> {
            int result = chooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFolder = chooser.getSelectedFile();
                if (selectedFolder.exists()) {
                    if (selectedFolder.isDirectory()) {
                        //set the folder text field
                        dialog.getImageFolderTextField().setText(
                                selectedFolder.toString());
                    }
                }
            }
        });
        
        dialog.getGenerateButton().addActionListener((ActionEvent e) -> {
            try {
                String gridText = dialog.getGridSizeTextField().getText();
                //get the value of the grid size field
                double gridDouble = Double.valueOf(gridText);
                gridSize = gridDouble;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, 
                        "Please enter a valid decimal\n"
                                + "number as the Grid Size.", 
                        "Not a Number", 
                        JOptionPane.ERROR_MESSAGE);
                //reset back to last valiedgridSize
                dialog.getGridSizeTextField().requestFocus();
                return;
            }
            
            try {
                //get the values of the cell size field
                double sx = Double.valueOf(dialog.getxCellSizeTextField().getText());
                double sy = Double.valueOf(dialog.getyCellSizeTextField().getText());
                double sz = Double.valueOf(dialog.getzCellSizeTextField().getText());
                cellSize = new Three(sx, sy, sz);
                
                //get the values of the cell gap field
                double gx = Double.valueOf(dialog.getxCellGapTextField().getText());
                double gy = Double.valueOf(dialog.getyCellGapTextField().getText());
                double gz = Double.valueOf(dialog.getzCellGapTextField().getText());
                cellGap = new Three(gx, gy, gz);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, 
                        "Please enter a valid decimal number\n"
                                + "for the Cell Size and Gap.", 
                        "Not a Number", 
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            //get the user-entered text for the folder location
            String destinationFolderText 
                    = dialog.getDesitnationFolderTextField().getText();
            File destinationFolder = getFolderFile(destinationFolderText);
            //if there was an error
            if (destinationFolder == null) {
                return;
            }
            
            //initialize as null
            File imageFolder = null;
            //if the user wants to use images
            if (dialog.getUseImagesCheckBox().isSelected()) {
                //get the user-entered text for the folder location
                String imageDestinationFolderText 
                        = dialog.getImageFolderTextField().getText();
                imageFolder = getFolderFile(imageDestinationFolderText);
                //if there was an error
                if (imageFolder == null) {
                    return;
                }
            }
            
            boolean success = generateScripts(destinationFolder, imageFolder);
            if (success) {
                //hide the dialog
                dialog.setVisible(false);
            }
            //otherwise keep the dialog open so that the user can correct
            //the mistake
            
        });
        
        dialog.getCancelButton().addActionListener((ActionEvent e) -> {
            //hide the dialog
            dialog.setVisible(false);
        });
        
        dialog.getGameObjectLevelGeneratorCheckBox().addActionListener((ActionEvent e) -> {
            boolean enabled = dialog.getGameObjectLevelGeneratorCheckBox().isSelected();
            //if we are not generating the LevelGenerator script
            if (!enabled) {
                //deselect the GroupEntitiesByType check box
                dialog.getGroupEntitiesByTypeCheckBox().setSelected(false);
            }
            //disable this if not enabled, enable it if enabled
            dialog.getGroupEntitiesByTypeCheckBox().setEnabled(enabled);
            dialog.getGridSizeTextField().setEnabled(enabled);
        });
        
        dialog.getUseImagesCheckBox().addActionListener((ActionEvent e) -> {
            //enable/disable the textfield
            dialog.getImageFolderTextField().setEnabled(
                    dialog.getUseImagesCheckBox().isSelected());
            dialog.getImageBrowseButton().setEnabled(
                    dialog.getUseImagesCheckBox().isSelected());
        });
    }

    public void showDialog(MainFrame frame) {
        dialog.setSize(380, 408);
        dialog.getDesitnationFolderTextField().setText(System.getProperty("user.dir"));
        dialog.getImageFolderTextField().setText(System.getProperty("user.dir"));
        dialog.setLocationRelativeTo(frame);
        dialog.getUseImagesCheckBox().setSelected(false);
        dialog.getImageFolderTextField().setEnabled(false);
        dialog.getImageBrowseButton().setEnabled(false);
        dialog.setVisible(true);
    }
    
    /**
     * Generates the scripts for the path at the given destination directory.
     * @param destinationFolder The destination directory for the Scripts
     * @param imageFolder The directory with the images for analysis
     */
    private boolean generateScripts(File destinationFolder, File imageFolder) {
        try {
            boolean shouldLevelGenerator 
                    = dialog.getGameObjectLevelGeneratorCheckBox().isSelected();
            boolean shouldEntity = dialog.getGameObjectEntityCheckBox().isSelected();
            String levelGeneratorFileName = "GameObjectLevelGenerator.cs";
            String entityResourceFileName = "/resources/gameobject/GameObjectEntity.cs";
            String entityFileName = "GameObjectEntity.cs";
            switch (scriptType) {
                case Tilemap:
                    shouldLevelGenerator 
                        = dialog.getTileLevelGeneratorCheckBox().isSelected();
                    shouldEntity = dialog.getTileEntityCheckBox().isSelected();
                    levelGeneratorFileName = "TileLevelGenerator.cs";
                    entityResourceFileName = "/resources/tilemap/TileEntity.cs";
                    entityFileName = "TileEntity.cs";
                    break;
                default:
                    shouldLevelGenerator 
                        = dialog.getMixedLevelGeneratorCheckBox().isSelected();
                    shouldEntity = dialog.getBaseEntityCheckBox().isSelected();
                    levelGeneratorFileName = "MixedLevelGenerator.cs";
                    entityResourceFileName = "/resources/mixed/BaseEntity.cs";
                    entityFileName = "BaseEntity.cs";
            }
            
            if (shouldLevelGenerator) {
                StringBuilder levelGenerator = getLevelGeneratorText(imageFolder);
                //if the user wants to use images, but no images were found
                if (levelGenerator == null) {
                    //there was a problem
                    return false;
                }
                createFile(destinationFolder, levelGeneratorFileName, levelGenerator.toString());
            }
            
            if (shouldEntity) {
                StringBuilder entity = readResource(entityResourceFileName);
                createFile(destinationFolder, entityFileName, entity.toString());
            }
        } catch (IOException ex) {
            System.err.println("I/O Exception: Could not read file\n"
                    + ex.toString());
        }
        return true;
    }
    
    /**
     * Creates the file at the given destination with the given name and the
     * given contents.
     * @param destination
     * @param name
     * @param contents 
     */
    private void createFile(File destination, String name, String contents) {
        //create a writer up here so we can close it in the finally statement
        FileWriter writer = null;
        try {
            //convert the destination to a path
            Path destinationPath = destination.toPath();
            //create a path to the file
            Path filePath = Paths.get(destinationPath.toString(), name);
            //convert the path to a file
            File file = filePath.toFile();
            
            //if the file already exists
            if (file.exists()) {
                //if the user does NOt want to overwrite
                if (!Utils.shouldContinue(
                        "The file " + name + " already exists.\n"
                        + "Would you like to overwrite this file?", dialog)) {
                    return;
                }
            }
            
            //create a writer from the file
            writer = new FileWriter(file);
            //write the contents to the file
            writer.write(contents);
        } catch (IOException ex) {
            System.err.println("I/O Exeption: Could not create file " + name 
                    + "\n" + ex.toString());
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException ex) {
                System.err.println("I/O Exeption: Could not write to file "
                    + name + "\n" + ex.toString());
            }
        }
    }
    
    /**
     * Generates the text for the LevelGenerator script using all of the
     * entities.
     * @param imageFolder the folder that holds images for analysis
     * @return The text for the LevelGenerator.cs file. Null in the odd
     * circumstance that the user wants to use image analysis but the
     * folder specified does not contain any images.
     */
    private StringBuilder getLevelGeneratorText(File imageFolder) {
        try {
            
            String startFileName = "/resources/gameobject/start.cs";
            String middleFileName = "/resources/gameobject/middle.cs";
            switch (scriptType) {
                case Tilemap:
                    startFileName = "/resources/tilemap/start.cs";
                    middleFileName = "/resources/tilemap/middle.cs";
                    break;
                default:
                    startFileName = "/resources/mixed/start.cs";
                    middleFileName = "/resources/mixed/middle.cs";
            }
            
            // The LevelGenerator Script
            //get the start and end text
            StringBuilder start = readResource(startFileName);
            StringBuilder middle = readResource(middleFileName);
            
            //holds the entities to generate scripts with
            List<Entity> projectEntities;
            //if the user wants to use images
            if (dialog.getUseImagesCheckBox().isSelected()) {
                //use only the correct entities
                projectEntities 
                        = getUsedEntities(imageFolder, project.getEntities());
                if (projectEntities == null) {
                    JOptionPane.showMessageDialog(dialog, 
                            "No images could be found in\n" + 
                                    imageFolder.toString());
                    return null;
                }
            } else {
                //get the project's entities
                projectEntities = project.getEntities();
            }
            
            
            
            //get whether the user wants to group entities by type
            boolean groupByType 
                    = dialog.getGroupEntitiesByTypeCheckBox().isSelected();
            //if the user wants to organize by type
            if (groupByType) {
                //set types to be all the types in the project
                types = project.getTypes();
                //cycle through all the types
                for (String type : types) {
                    formattedTypes.add(formatType(type));
                }
            } else {
                //if the user does not want to organize by type
                //initialize the types list
                types = new ArrayList();
                formattedTypes = new ArrayList();
                //add a single element called entities that will hold all the
                //entities in the project.
                switch (scriptType) {
                    case GameObject:
                        types.add("GameObject Entities");
                        formattedTypes.add("gameObjectEntities");
                        break;
                    case Tilemap:
                        types.add("Tile Entities");
                        formattedTypes.add("tileEntities");
                        break;
                    default: //Mixed
                        //Mixed needs both
                        types.add("Tile Entities");
                        types.add("GameObject Entities");
                        
                        formattedTypes.add("tileEntities");
                        formattedTypes.add("gameObjectEntities");
                }
            }
            
            List<StringBuilder> entityObjectSBs = new ArrayList();
            //initialize a StringBuilder for each type in the project and
            //initialize the number of entities in each type to zero
            for (int t = 0; t < types.size(); t++) {
                entityObjectSBs.add(new StringBuilder());
            }
            
            //loop through all the entities in the project
            for (int i = 0; i < projectEntities.size(); i++) {
                //true if this is the last iteration
                boolean last = (i == projectEntities.size() - 1);
                //get the entity at index i
                Entity e = projectEntities.get(i);
                //initialize the typeIndex to 0
                int typeIndex = 0;
                //if the user wants to group by type
                if (groupByType) {
                    //get the index of this entity's type
                    typeIndex = e.getTypeIndex();
                } //leave it at 0 if the user is not grouping by type
                //put e in the StringBuilders corrosponding to the typeIndex
                addEntityToStringBuilder(e, 
                        entityObjectSBs.get(typeIndex), last);
            }
            
            //this will hold the final text of the file
            StringBuilder complete = new StringBuilder();
            //add on the start of the file
            complete.append(start);
            
            if (scriptType == ScriptType.GameObject) {
                // Add on the gridSize variable
                complete.append("\tpublic float gridSize = ");
                complete.append(gridSize);
                complete.append("f;\n");
            } else {
                //add on the cellSize variable
                complete.append("\tpublic Vector3 cellSize = new Vector3(");
                complete.append(cellSize.getX());
                complete.append("f, ");
                complete.append(cellSize.getY());
                complete.append("f, ");
                complete.append(cellSize.getZ());
                complete.append("f);\n");
                //add on the cellGap variable
                complete.append("\tpublic Vector3 cellGap = new Vector3(");
                complete.append(cellGap.getX());
                complete.append("f, ");
                complete.append(cellGap.getY());
                complete.append("f, ");
                complete.append(cellGap.getZ());
                complete.append("f);\n");
            }
            
            //loop through all the types
            for (int i = 0; i < types.size(); i++) {
                //for each type, add the array of Entity objects with the title
                //matching the corrosponding type
                addTypeToCompleteSB(i, 
                        entityObjectSBs.get(i), complete);
            }
            //add on the middle of the file
            complete.append(middle);
            //add on the loops
            addLoopForEachTypeToCompleteSB(complete);
            //return the completed text
            return complete;
        } catch (IOException ex) {
            System.err.println("I/O Exception: Could not read file\n"
                    + ex.toString());
            return null;
        }
    }
    
    /**
     * Creates a for loop for each type array in the LevelGenerator script.
     * @param types All the types in the LevelGenerator script
     * @param complete the complete StringBuilder
     */
    private void addLoopForEachTypeToCompleteSB(StringBuilder complete) {
        for (int i = 0; i < types.size(); i++) {
            complete.append("\t\tforeach (");
            if (scriptType == ScriptType.GameObject) {
                complete.append("Entity entity");
            } else {
                complete.append("TileEntity tileEntity");
            }
            complete.append(" in ");
            
            //use the formatted type name as a variable name
            complete.append(formattedTypes.get(i));
            
            //if the user wants to group entities by type
            if (dialog.getGroupEntitiesByTypeCheckBox().isSelected()) {
                //add on the word "Entities" to the name of the array
                complete.append(Utils.ARRAY_NAME_EXTENSION);   
            }
            complete.append(") {\n\t\t\t");
            if (scriptType == ScriptType.GameObject) {
                complete.append("instantiateIfMatch(entity, pixelColor, x, y);\n");
            } else {
                complete.append("placeTileIfMatch(tileEntity, pixelColor, x, y, index);\n");
            }
            complete.append("\t\t}\n");
        }
        //close the last braces
        complete.append("\t}\n");
        complete.append("}\n");
        complete.append("\n");
    }
    
    /**
     * Adds the entityObjects StringBuilder's contents associated
     * with the given type to the complete StringBuilder.
     * @param type The name of the type that we're making an array for
     * @param entityObjects
     * @param complete
     */
    private void addTypeToCompleteSB(int typeIndex, 
            StringBuilder entityObjects, 
            StringBuilder complete) {
        //add a comment discribing the type
        complete.append("\t//");
        complete.append(types.get(typeIndex));

        if (scriptType == ScriptType.GameObject) {
            complete.append("\n\tpublic Entity[] ");
        } else {
            complete.append("\n\tpublic TileEntity[] ");
        }
        
        //use the formatted type name as a variable name
        complete.append(formattedTypes.get(typeIndex));
        
        //if the user wants to group entities by type
        if (dialog.getGroupEntitiesByTypeCheckBox().isSelected()) {
            if (scriptType == ScriptType.GameObject) {
                //add on the word "Entities" to the name of the array
                complete.append(Utils.ARRAY_NAME_EXTENSION);
            }  else {
                //add on the word "TileEntities" to the name of the array
                complete.append(Utils.ARRAY_TILE_NAME_EXTENSION);
            }
        }
        complete.append(" = new ");
        if (scriptType == ScriptType.GameObject) {
            complete.append("Entity");
        }  else {
            complete.append("TileEntity");
        }
        complete.append("[] {\n");
        complete.append(entityObjects);
        complete.append("\t};\n");
        
        //add a new line between each type
        complete.append("\n");
    }
    
    /**
     * Adds the passed in Entity to the entityObject StringBuilder,
     * including a comma afterwards unless told this is the last iteration.
     * @param entity The Entity to add
     * @param entityObjects the entityObjects StringBuilder
     * @param last Specifies whether this is the last iteration
     */
    private void addEntityToStringBuilder(Entity entity, 
            StringBuilder entityObjects, boolean last) {
        /*
        The elements in the entityObjects array look like:
            new Entity("Name", new Color32(0, 0, 0, 255)),
        but the last element has no comma at the end
        */

        //Add to the entityObjects array
        entityObjects.append("\t\tnew ");
        if (scriptType == ScriptType.GameObject) {
            entityObjects.append("Entity");
        } else {
            entityObjects.append("TileEntity");
        }
        entityObjects.append("(\"");
        entityObjects.append(entity.getName());
        entityObjects.append("\", new Color32(");
        entityObjects.append(entity.getR());
        entityObjects.append(", ");
        entityObjects.append(entity.getG());
        entityObjects.append(", ");
        entityObjects.append(entity.getB());
        entityObjects.append(", ");
        entityObjects.append(entity.getA());
        if (entity.getOffset().equals(Offset.zero)) { //if it's just (0,0)
            entityObjects.append("))"); //use the color-only constructor
        } else { //if it's not (0,0)
            entityObjects.append("), new Vector2");
            entityObjects.append(entity.getOffset().toString()); //e.g. "(15, 5)"
            entityObjects.append(")");
        }
        if (!last) { //if not the last
            entityObjects.append(",");
        }
        entityObjects.append("\n");
    }
    
    /**
     * Reads the passed in resource and outputs a StringBuilder
     * @param resource The resource (e.g. "/resources/start.cs")
     * @return A StringBuilder object with the contents of the resource
     * @throws IOException If something happened with the BufferedReader and
     * we couldn't read the file
     */
    private StringBuilder readResource(String resource)
            throws IOException {
        //get the InputStream of the Resource
        InputStream stream = getClass().getResourceAsStream(resource);
        //Create a BufferedReader to read the resource line by line
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        //Create a StringBuilder to hold the contents of the resource
        StringBuilder s = new StringBuilder();
        //A string to hold each line of the resource as they come in
        String line;
        //read each line of the resource
        while ((line = reader.readLine()) != null) {
            //add each line to the StringBuilder
            s.append(line);
            s.append("\n");
        }
        //close the reader
        reader.close();
        //return the contents of the resource
        return s;
    }
    
    /**
     * Shows or hides the panels associated with the passed in type. Also
     * sets the scriptType variable.
     * @param type the script type
     */
    private void chooseType(ScriptType type) {
        scriptType = type;
        switch (scriptType) {
            case GameObject:
                dialog.getGameObjectScriptsPanel().setVisible(true);
                dialog.getTileScriptsPanel().setVisible(false);
                dialog.getMixedScriptsPanel().setVisible(false);

                dialog.getGridSizePanel().setVisible(true);
                dialog.getCellSizeGapPanel().setVisible(false);
                break;
            case Tilemap:
                dialog.getGameObjectScriptsPanel().setVisible(false);
                dialog.getTileScriptsPanel().setVisible(true);
                dialog.getMixedScriptsPanel().setVisible(false);

                dialog.getGridSizePanel().setVisible(false);
                dialog.getCellSizeGapPanel().setVisible(true);
                break;
            default:
                dialog.getGameObjectScriptsPanel().setVisible(false);
                dialog.getTileScriptsPanel().setVisible(false);
                dialog.getMixedScriptsPanel().setVisible(true);
                
                dialog.getGridSizePanel().setVisible(true);
                dialog.getCellSizeGapPanel().setVisible(true);
        }
    }

    private String formatType(String type) {
        if (type.isEmpty()) {
            return type;
        }
        char[] chars = type.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            //if this is a space
            if (chars[i] == ' ') {
                //if this is not the last char in the array
                if (i < chars.length - 1) {
                    //if it's lowercase
                    if (97 <= chars[i + 1] && chars[i + 1] <= 122) {
                        //make it uppercase
                        chars[i + 1] = (char) (chars[i + 1] - 32);
                    }
                }
            }
        }
        
        //if the first character is lowercase
        if (97 <= chars[0] && chars[0] <= 122) {
            //make it uppercase
            chars[0] = (char) (chars[0] - 32);
        }
        
        //convert the chars back into this
        String formattedType = new String(chars);
        //remove all spaces
        formattedType = formattedType.replaceAll(" ", "");
        return formattedType;
    }
    
    private File getFolderFile(String destinationFolderText) {
        //if the user entered an empty string
        if (destinationFolderText.length() == 0) {
            JOptionPane.showMessageDialog(dialog, 
                    "Cannot enter an empty string as a folder path.", 
                    "Empty Path", 
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

        //convert the destination folder into a file
        File destination = new File(destinationFolderText);
        
        if (!destination.exists()) {
            JOptionPane.showMessageDialog(dialog, 
                    destination.toString() + 
                            "\ndoes not exist.", 
                    "Folder Does Not Exist", 
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (!destination.isDirectory()) {
            JOptionPane.showMessageDialog(dialog, 
                    destination.toString() + 
                            "is not a directory.", 
                    "Not a Directory", 
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        return destination;
    }

    //MARK: Image Analysis
    /**
     * Loops through all the images in the folder and returns all the entities
     * whose colors are used in those images.
     * @param imageFolder
     * @return A list of entities whose colors are used in the images in the
     * imageFolder directory. (Null if no image files were found).
     */
    private List<Entity> getUsedEntities(File imageFolder, 
            List<Entity> projectEntities) {
        FileFilter imageFilter = new FileFilter() {
            String[] extensions = {
                "png",
                "jpeg",
                "jpg",
                "gif",
                "tiff",
                "tif"};
            
            @Override
            public boolean accept(File pathname) {
                //cycle through all the extensions
                for (String ext : extensions) {
                    //if the file name has this extension
                    if (pathname.getName().matches(".*\\." + ext)) {
                        //this is an acceptable file
                        return true;
                    }
                    //otherwise continue
                }
                return false;
            }
        };
        
        //get a list of image files in the folder
        File[] imageFiles = imageFolder.listFiles(imageFilter);
        
        //if no image files were found
        if (imageFiles.length == 0) {
            return null;
        }
        
        //this will hold all used colors
        Set<Color> usedColors = new HashSet();
        
        //loop through all the files
        for (File imageFile : imageFiles) {
            //get all the colors in this image
            Set<Color> newColors = getColors(imageFile);
            //if there were no problems
            if (newColors != null) {
                //add the unique colors from this image to the unique colors
                //accross all the images looked at so far
                usedColors.addAll(newColors);
            } else {
                //tell the user there was a problem reading this image
                JOptionPane.showMessageDialog(dialog, 
                        "Could not read the image\n" + 
                                imageFile.toString(), 
                        "Could Not Read File", 
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        
        //Get all Entities that are paired with the unique used colors:
        
        //holds all the entities which are used in the images
        List<Entity> usedEntities = new ArrayList();
        //loop through all the entities
        for (Entity entity : projectEntities) {
            //if this entity's color is contained in the set of 
            //unique used colors
            if (usedColors.contains(entity.getColor())) {
                //add this to the usedEntities list
                usedEntities.add(entity);
            }
        }
        
        //return all the entities that are used in this set of images
        return usedEntities;
    }
    
    /**
     * Finds all unique colors within the given image.
     * @param imageFile The image to search.
     * @return A HashSet of unique Color objects representing the colors of
     * the pixels in the passed in image.
     */
    private Set<Color> getColors(File imageFile) {
        try {
            BufferedImage image = ImageIO.read(imageFile);
            int w = image.getWidth();
            int h = image.getHeight();
            Set<Color> colors = new HashSet();
            //loop through all the pixels in the image
            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    //get the color at this pixel
                    int colorInt = image.getRGB(x, y);
                    //create a color from this image
                    Color color = new Color(colorInt);
                    //if the color is not completely transparent
                    if (color.getAlpha() > 0) {
                        //add it to the set
                        colors.add(color);
                    }
                }
            }
            //return the set of unique colors in this image
            return colors;
        } catch (IOException ex) {
            System.err.println("Could not read file:\n"
                            + imageFile.toString() + "\n" + ex.toString());
            return null;
        }
    }
    
}
