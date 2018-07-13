/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scriptgeneration;

import controllers.Utils;
import entities.Entity;
import entities.Offset;
import java.awt.event.ActionEvent;
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
import projects.Project;
import views.MainFrame;
import views.ScriptGeneratorDialog;

enum ScriptType {
    GameObject,
    Tilemap
}

/**
 * Handles generating scripts
 * @author Scott
 */
public class ScriptGenerator {
    
    private final Project project;
    private final ScriptGeneratorDialog dialog;
    private ScriptType scriptType = ScriptType.GameObject;
    
    //GameObject
    private double gridSize = 32;
    
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
        
        //initialize the type as GameObject
        chooseType(ScriptType.GameObject);
        
        typeComboBox.addActionListener((ActionEvent e) -> {
            int index = typeComboBox.getSelectedIndex();
            if (index == 0) { //GameObject
                chooseType(ScriptType.GameObject);
            } else { //Tilemap
                chooseType(ScriptType.Tilemap);
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
            String desitnationFolderText 
                    = dialog.getDesitnationFolderTextField().getText();
            //if the user entered an empty string
            if (desitnationFolderText.length() == 0) {
                JOptionPane.showMessageDialog(dialog, 
                        "Cannot enter an empty string as a folder path.", 
                        "Empty Path", 
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            //convert the destination folder into a file
            File destination = new File(desitnationFolderText);
            if (!destination.exists()) {
                JOptionPane.showMessageDialog(dialog, 
                        "The designated folder does not exist.", 
                        "Folder Does Not Exist", 
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!destination.isDirectory()) {
                JOptionPane.showMessageDialog(dialog, 
                        "The designated file is not a directory.", 
                        "Not a Directory", 
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            generateScripts(destination);
            
            //hide the dialog
            dialog.setVisible(false);
        });
        
        dialog.getCancelButton().addActionListener((ActionEvent e) -> {
            //hide the dialog
            dialog.setVisible(false);
        });
        
        dialog.getLevelGeneratorCheckBox().addActionListener((ActionEvent e) -> {
            boolean enabled = dialog.getLevelGeneratorCheckBox().isSelected();
            //if we are not generating the LevelGenerator script
            if (!enabled) {
                //deselect the GroupEntitiesByType check box
                dialog.getGroupEntitiesByTypeCheckBox().setSelected(false);
            }
            //disable this if not enabled, enable it if enabled
            dialog.getGroupEntitiesByTypeCheckBox().setEnabled(enabled);
            dialog.getGridSizeTextField().setEnabled(enabled);
        });
    }

    public void showDialog(MainFrame frame) {
        dialog.getDesitnationFolderTextField().setText(System.getProperty("user.dir"));
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }
    
    /**
     * Generates the scripts for the path at the given destination directory.
     * @param destination The destination directory for the Scripts
     */
    private void generateScripts(File destination) {
        try {
            boolean shouldLevelGenerator 
                    = dialog.getLevelGeneratorCheckBox().isSelected();
            boolean shouldEntity = dialog.getEntityCheckBox().isSelected();
            String levelGeneratorFileName = "LevelGenerator.cs";
            String entityResourceFileName = "/resources/gameobject/Entity.cs";
            String entityFileName = "Entity.cs";
            if (scriptType == ScriptType.Tilemap) {
                shouldLevelGenerator 
                    = dialog.getTileLevelGeneratorCheckBox().isSelected();
                shouldEntity = dialog.getTileEntityCheckBox().isSelected();
                levelGeneratorFileName = "TileLevelGenerator.cs";
                entityResourceFileName = "/resources/tilemap/TileEntity.cs";
                entityFileName = "TileEntity.cs";
            }
            
            if (shouldLevelGenerator) {
                StringBuilder levelGenerator = getLevelGeneratorText();
                createFile(destination, levelGeneratorFileName, levelGenerator.toString());
            }
            
            if (shouldEntity) {
                StringBuilder entity = readResource(entityResourceFileName);
                createFile(destination, entityFileName, entity.toString());
            }
        } catch (IOException ex) {
            System.err.println("I/O Exception: Could not read file\n"
                    + ex.toString());
        }
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
     * @return The text for the LevelGenerator.cs file
     */
    private StringBuilder getLevelGeneratorText() {
        try {
            
            String startFileName = "/resources/gameobject/start.cs";
            String middleFileName = "/resources/gameobject/middle.cs";
            if (scriptType == ScriptType.Tilemap) {
                startFileName = "/resources/tilemap/start.cs";
                middleFileName = "/resources/tilemap/middle.cs";
            }
            
            // The LevelGenerator Sctipt
            //get the start and end text
            StringBuilder start = readResource(startFileName);
            StringBuilder middle = readResource(middleFileName);
            
            //get the project's entities
            List<Entity> projectEntities = project.getEntities();
            //this holds all the types that we want to organize the entities by
            List<String> types;
            //get whether the user wants to group entities by type
            boolean groupByType 
                    = dialog.getGroupEntitiesByTypeCheckBox().isSelected();
            //if the user wants to organize by type
            if (groupByType) {
                //set types to be all the types in the project
                types = project.getTypes();
            } else {
                //if the user does not want to organize by type
                //initialize the types list
                types = new ArrayList();
                //add a single element called entities that will hold all the
                //entities in the project.
                if (scriptType == ScriptType.GameObject) {
                    types.add("entities");
                } else {
                    types.add("tileEntities");
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
                    typeIndex = types.indexOf(e.getType());
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
                addTypeToCompleteSB(types.get(i), 
                        entityObjectSBs.get(i), complete);
            }
            //add on the middle of the file
            complete.append(middle);
            //add on the loops
            addLoopForEachTypeToCompleteSB(types, complete);
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
    private void addLoopForEachTypeToCompleteSB(List<String> types, 
            StringBuilder complete) {
        for (String type : types) {
            complete.append("\t\tforeach (");
            if (scriptType == ScriptType.GameObject) {
                complete.append("Entity entity");
            } else {
                complete.append("TileEntity tileEntity");
            }
            complete.append(" in ");
            
            //make the first letter lowercase
            String firstLetter = "" + type.charAt(0);
            firstLetter = firstLetter.toLowerCase();
            String otherLetters = type.substring(1);
            //add the type with a lowercase first letter
            complete.append(firstLetter);
            complete.append(otherLetters);
            
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
    private void addTypeToCompleteSB(String type, 
            StringBuilder entityObjects, 
            StringBuilder complete) {
        //add a comment discribing the type
        complete.append("\t//");
        complete.append(type);

        if (scriptType == ScriptType.GameObject) {
            complete.append("\n\tpublic Entity[] ");
        } else {
            complete.append("\n\tpublic TileEntity[] ");
        }
        
        // Make the first letter lowercase
        String firstLetter = "" + type.charAt(0);
        firstLetter = firstLetter.toLowerCase();
        String otherLetters = type.substring(1);
        //add the type with a lowercase first letter
        complete.append(firstLetter);
        complete.append(otherLetters);
        
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
        The elements in the colorToPrefabs array look like:
            new Entity("Name", new Color32(0, 0, 0, 255)),
        but the last element has no comma at the end
        */

        //Add to the colorToPrefabs array
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
        if (type == ScriptType.GameObject) { //GameObject
            dialog.getGamObjectScriptsPanel().setVisible(true);
            dialog.getTileScriptsPanel().setVisible(false);
        } else { //tilemap
            dialog.getGamObjectScriptsPanel().setVisible(false);
            dialog.getTileScriptsPanel().setVisible(true);
        }
    }
    
}
