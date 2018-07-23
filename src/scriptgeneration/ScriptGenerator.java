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
import java.util.prefs.Preferences;
import javax.imageio.ImageIO;
import projects.Project;
import views.MainFrame;
import views.ScriptGeneratorDialog;

enum ScriptType {
    GameObject,
    Tilemap,
    Mixed
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
    private double gridSize = 1;
    //Tilemap
    private Three cellSize = new Three(1, 1, 0);
    private Three cellGap = new Three(0, 0, 0);
    
    private final Preferences prefs;
    
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
        
        prefs = Preferences.userRoot().node(this.getClass().getName());
        
        String imageFolderChooserFilePath 
                = prefs.get(Utils.IMAGE_FOLDER_CHOOSER_PATH, 
                        System.getProperty("user.dir"));
        File imageFolderChooserFile = new File(imageFolderChooserFilePath);
        //make it the parent so they can actually CLICK the desired folder again
        JFileChooser imageFolderChooser 
                = new JFileChooser(imageFolderChooserFile.getParent());
        imageFolderChooser.setMultiSelectionEnabled(false);
        imageFolderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        imageFolderChooser.setDragEnabled(true);
        
        String destinationChooserFilePath 
                = prefs.get(Utils.DESTINATION_CHOOSER_PATH, 
                        System.getProperty("user.dir"));
        File destinationChooserFile = new File(destinationChooserFilePath);
        //make it the parent so they can actually CLICK the desired folder again
        JFileChooser destinationChooser 
                = new JFileChooser(destinationChooserFile.getParent());
        destinationChooser.setMultiSelectionEnabled(false);
        destinationChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        destinationChooser.setDragEnabled(true);
        
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
            int result = destinationChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFolder = destinationChooser.getSelectedFile();
                //save the current directory to the user's preferences
                prefs.put(Utils.DESTINATION_CHOOSER_PATH, selectedFolder.toString());
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
            int result = imageFolderChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFolder = imageFolderChooser.getSelectedFile();
                //save the current directory to the user's preferences
                prefs.put(Utils.IMAGE_FOLDER_CHOOSER_PATH, selectedFolder.toString());
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
            //Get gridSize
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
            
            //Get Cell Size and Cell Gap
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
        
        String imageFolderChooserFilePath 
                = prefs.get(Utils.IMAGE_FOLDER_CHOOSER_PATH, 
                        System.getProperty("user.dir"));
        dialog.getImageFolderTextField().setText(imageFolderChooserFilePath);
        
        String destinationChooserFilePath 
                = prefs.get(Utils.DESTINATION_CHOOSER_PATH, 
                        System.getProperty("user.dir"));
        dialog.getDesitnationFolderTextField().setText(destinationChooserFilePath);
        
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
            switch (scriptType) {
                case GameObject:
                    return generateGameObjectScripts(destinationFolder, imageFolder);
                case Tilemap:
                    return generateTileScripts(destinationFolder, imageFolder);
                default:
                    return generateMixedScripts(destinationFolder, imageFolder);
            }
        } catch (IOException ex) {
            System.err.println("I/O Exception: Could not read file\n"
                    + ex.toString());
        }
        return true;
    }
    
    //MARK: GameObject ----------------------------------------------------
    private boolean generateGameObjectScripts(File destinationFolder, 
            File imageFolder) throws IOException {
        
        if (dialog.getGameObjectLevelGeneratorCheckBox().isSelected()) {
            StringBuilder levelGenerator = getLevelGeneratorText(
                    imageFolder,
                    "/resources/gameobject/start.cs",
                    "/resources/gameobject/middle.cs",
                    "GameObject Entities", //singleType
                    "gameObjectEntities",  //singleFormattedType
                    "GameObjectEntity",    //className
                    "GameObject",          //basicClassName
                    "gameObject",          //entityAttribute
                    "instantiateIfMatch(entity, pixelColor, x, y);");
            //if the user wants to use images, but no images were found
            if (levelGenerator == null) {
                //there was a problem
                return false;
            }
            Utils.createFile(destinationFolder, "GameObjectLevelGenerator.cs", levelGenerator.toString());
        }

        if (dialog.getGameObjectEntityCheckBox().isSelected()) {
            StringBuilder entity = readResource("/resources/gameobject/GameObjectEntity.cs");
            Utils.createFile(destinationFolder, "GameObjectEntity.cs", entity.toString());
        }
        return true;
    }
    
    /**
     * 
     * @param complete
     * @param basicClassName "GameObject" or "TileBase"
     * @param entityAttribute "gameObject" or "tile"
     */
    private void addFillingLoopForEachTypeToCompleteSB(
            StringBuilder complete,
            List<String> formattedTypes,
            List<StringBuilder> entitySBs,
            String basicClassName,
            String entityAttribute) {
        for (int i = 0; i < formattedTypes.size(); i++) {
            if (entitySBs.get(i).length() != 0) {
                complete.append("\n\t\tfor (int i = 0; i < ");
                complete.append(formattedTypes.get(i));
                complete.append(basicClassName);
                complete.append("s.Length; i++) {\n\t\t\t");

                //use the formatted type name as a variable name
                complete.append(formattedTypes.get(i));
                //if the user wants to group entities by type
                if (dialog.getGroupEntitiesByTypeCheckBox().isSelected()) {
                    //add on the word "Entities" to the name of the array
                    complete.append(Utils.ARRAY_NAME_EXTENSION);   
                }

                complete.append("[i].");
                complete.append(entityAttribute);
                complete.append(" = ");
                String ft = formattedTypes.get(i);
                complete.append(ft);
                complete.append(basicClassName);
                complete.append("s[i];\n\t\t}\n");
            }
        }
    }
    
    /**
     * Creates a for loop for each type array in the LevelGenerator script.
     * @param types All the types in the LevelGenerator script
     * @param complete the complete StringBuilder
     * @param itemType e.g. "TileEntity entity" or "GOEntity entity"
     * @param placeFunction e.g. 
     * (for GameObject)
     * "instantiateIfMatch(entity, pixelColor, x, y);", or
     * (for Tilemap)
     * "placeTileIfMatch(entity, pixelColor, x, y, index);", or
     * (for Mixed)
     * "placeTileIfColorMatches(entity, pixelColor, x, y, index);", or
     * "placeGameObjectIfColorMatches(entity, pixelColor, x, y, index);"
     */
    private void addSearchingLoopForEachTypeToCompleteSB(
            StringBuilder complete,
            List<String> types,
            List<String> formattedTypes,
            List<StringBuilder> entitySBs,
            String itemType,
            String placeFunction) {
        for (int i = 0; i < types.size(); i++) {
            if (entitySBs.get(i).length() != 0) {
                complete.append("\t\tforeach (");
                complete.append(itemType);
                complete.append(" in ");

                //use the formatted type name as a variable name
                complete.append(formattedTypes.get(i));

                //if the user wants to group entities by type
                if (dialog.getGroupEntitiesByTypeCheckBox().isSelected()) {
                    //add on the word "Entities" to the name of the array
                    complete.append(Utils.ARRAY_NAME_EXTENSION);   
                }
                complete.append(") {\n\t\t\t");
                complete.append(placeFunction);
                complete.append("\n\t\t}\n");
            }
        }
        //close the last braces
        complete.append("\t}\n");
        complete.append("}\n");
        complete.append("\n");
    }
    
    /**
     * Adds the entitySB StringBuilder's contents associated with the given
     * type to the "complete" StringBuilder. This is used to create individual
     * arrays for each type (if the user wants to group by type) or a single
     * array for all of the entities (if the user doesn't want to group by
     * type). This function is called once per array as needed, adding on a
     * single array to the "complete" StringBuilder every time.
     * @param complete
     * @param entitySB
     * @param type The name of the type that we're making an array for
     * @param formattedType The formatted name of the type that we're making an
     * array for
     * @param className should be "GameObjectEntity", "TileEntity",
     * "TEntity", or "GOEntity".
     * @param basicClassName should be "TileBase" or "GameObject"
     */
    private void addTypeToCompleteSB(
            StringBuilder complete,
            StringBuilder entitySB,
            String type,
            String formattedType,
            String className,
            String basicClassName) {
        //add a Header discribing the type (e.g. [Header("Enemy Entities")])
        complete.append("\n\t[Header(\"");
        complete.append(type);
        complete.append(" ");
        if (dialog.getGroupEntitiesByTypeCheckBox().isSelected()) {
            complete.append(Utils.ARRAY_NAME_EXTENSION);
        }
        complete.append("\")]");
        
        complete.append("\n\tpublic ");
        complete.append(className);
        complete.append("[] ");
        
        //use the formatted type name as a variable name
        complete.append(formattedType);
        
        //if the user wants to group entities by type
        if (dialog.getGroupEntitiesByTypeCheckBox().isSelected()) {
            complete.append(Utils.ARRAY_NAME_EXTENSION);
        }
        
        complete.append(" = new ");
        complete.append(className);
        complete.append("[] {\n");
        complete.append(entitySB);
        complete.append("\t};\n");
        
        //add on the tooltip
        complete.append("\t[Tooltip(\"Must have the same size as ");
        complete.append(formattedType);
        if (dialog.getGroupEntitiesByTypeCheckBox().isSelected()) {
            complete.append(Utils.ARRAY_NAME_EXTENSION);
        }
        complete.append("\")]\n");
        
        //add on the actuall array that holds the objects to be loaded
        complete.append("\tpublic ");
        complete.append(basicClassName);
        complete.append("[] ");
        complete.append(formattedType);
        complete.append(basicClassName);
        complete.append("s;\n");
        
        //add a new line between each type
        complete.append("\n");
    }
    
    //MARK: Tile ----------------------------------------------------------
    private boolean generateTileScripts(File destinationFolder, 
            File imageFolder) throws IOException {
        boolean shouldLevelGenerator 
                   = dialog.getTileLevelGeneratorCheckBox().isSelected();
        boolean shouldEntity = dialog.getTileEntityCheckBox().isSelected();
        String levelGeneratorFileName = "TileLevelGenerator.cs";
        String entityResourceFileName = "/resources/tilemap/TileEntity.cs";
        String entityFileName = "TileEntity.cs";
        
        if (shouldLevelGenerator) {
            StringBuilder levelGenerator = getLevelGeneratorText(
                    imageFolder,
                    "/resources/tilemap/start.cs",
                    "/resources/tilemap/middle.cs",
                    "Tile Entities",    //singleType
                    "tileEntities",     //singleFormattedType
                    "TileEntity",       //className
                    "TileBase",         //basicClassName
                    "tile",             //entityAttribute
                    "placeTileIfMatch(entity, pixelColor, x, y, index);");
            //if the user wants to use images, but no images were found
            if (levelGenerator == null) {
                //there was a problem
                return false;
            }
            Utils.createFile(destinationFolder, levelGeneratorFileName, levelGenerator.toString());
        }

        if (shouldEntity) {
            StringBuilder entity = readResource(entityResourceFileName);
            Utils.createFile(destinationFolder, entityFileName, entity.toString());
        }
        return true;
    }

    
    //MARK: Mixed ---------------------------------------------------------
    private boolean generateMixedScripts(File destinationFolder, 
            File imageFolder) throws IOException {
        boolean shouldLevelGenerator 
                   = dialog.getMixedLevelGeneratorCheckBox().isSelected();
        boolean shouldEntity = dialog.getBaseEntityCheckBox().isSelected();
        String levelGeneratorFileName = "MixedLevelGenerator.cs";
        String entityResourceFileName = "/resources/mixed/BaseEntity.cs";
        String entityFileName = "BaseEntity.cs";
        
        if (shouldLevelGenerator) {
            StringBuilder levelGenerator = getLevelGeneratorTextForMixed(imageFolder);
            //if the user wants to use images, but no images were found
            if (levelGenerator == null) {
                //there was a problem
                return false;
            }
            Utils.createFile(destinationFolder, levelGeneratorFileName, levelGenerator.toString());
        }

        if (shouldEntity) {
            StringBuilder entity = readResource(entityResourceFileName);
            Utils.createFile(destinationFolder, entityFileName, entity.toString());
        }
        return true;
    }
    
    /**
     * Generates the text for the GameObjectLevelGenerator script using all of
     * the entities.
     * @param imageFolder the folder that holds images for analysis
     * @return The text for the LevelGenerator.cs file. Null in the odd
     * circumstance that the user wants to use image analysis but the
     * folder specified does not contain any images.
     */
    private StringBuilder getLevelGeneratorTextForMixed(File imageFolder) {
        try {
            // The LevelGenerator Script
            //get the start and end text
            StringBuilder start = readResource("/resources/mixed/start.cs");
            StringBuilder middle = readResource("/resources/mixed/middle.cs");
            
            //holds the entities to generate scripts with
            List<Entity> projectEntities = getProjectEntities(imageFolder);
            if (projectEntities == null) {
                return null;
            }
            
            List<String> tTypes = new ArrayList();
            List<String> tFormattedTypes = new ArrayList();
            
            List<String> goTypes = new ArrayList();
            List<String> goFormattedTypes = new ArrayList();
            //if the user wants to organize by type
            if (dialog.getGroupEntitiesByTypeCheckBox().isSelected()) {
                //cycle through all the types
                for (String type : project.getTypes()) {
                    tTypes.add("T " + type);
                    goTypes.add("GO " + type);
                    
                    //add the formatted version
                    tFormattedTypes.add(formatType("T " + type));
                    goFormattedTypes.add(formatType("GO " + type));
                }
            } else {
                tTypes.add("Tile Entities");
                tFormattedTypes.add("tileEntities");
                
                goTypes.add("GameObject Entities");
                goFormattedTypes.add("gameObjectEntities");
            }
            
            
            List<StringBuilder> tEntitySBs = new ArrayList();
            List<StringBuilder> goEntitySBs = new ArrayList();
            //initialize one empty StringBuilder for each type
            for (int t = 0; t < tTypes.size(); t++) {
                tEntitySBs.add(new StringBuilder());
                goEntitySBs.add(new StringBuilder());
            }

            //loop through all the entities in the project
            for (int i = 0; i < projectEntities.size(); i++) {
                boolean lastIteration = (i == projectEntities.size() - 1);
                Entity entity = projectEntities.get(i);
                int typeIndex = 0;
                //if the user wants to group by type
                if (dialog.getGroupEntitiesByTypeCheckBox().isSelected()) {
                    //get the index of this entity's type
                    typeIndex = entity.getTypeIndex();
                }
                
                //put entity in the StringBuilder corrosponding to the typeIndex
                /*
                Depending on entity's classIndex, entities look like this:
                    new TEntity("Barrier", new Color32(0, 0, 0, 255)),
                    new GOEntity("Barrier", new Color32(0, 0, 0, 255)),
                Everything is the same except for the name of the class.
                */
                
                //get the stringbuilder that matches this typeIndex
                StringBuilder goEntitySB = goEntitySBs.get(typeIndex);
                StringBuilder tEntitySB = tEntitySBs.get(typeIndex);
                //Add to the entityObjects array
                switch (entity.getClassIndex()) {
                    case Utils.GAMEOBJECT_INDEX:
                        goEntitySB.append("\t\tnew ");
                        goEntitySB.append("GOEntity");
                        goEntitySB.append("(\"");
                        goEntitySB.append(entity.getName());
                        goEntitySB.append("\", new Color32(");
                        goEntitySB.append(entity.getR());
                        goEntitySB.append(", ");
                        goEntitySB.append(entity.getG());
                        goEntitySB.append(", ");
                        goEntitySB.append(entity.getB());
                        goEntitySB.append(", ");
                        goEntitySB.append(entity.getA());
                        if (entity.getOffset().equals(Offset.zero)) { //if it's just (0,0)
                            goEntitySB.append("))"); //use the color-only constructor
                        } else { //if it's not (0,0)
                            goEntitySB.append("), new Vector2");
                            goEntitySB.append(entity.getOffset().toString()); //e.g. "(15, 5)"
                            goEntitySB.append(")");
                        }
                        if (!lastIteration) { //if not the last
                            goEntitySB.append(",");
                        }
                        goEntitySB.append("\n");
                        break;
                    default: //TILE_INDEX
                        tEntitySB.append("\t\tnew ");
                        tEntitySB.append("TEntity");
                        tEntitySB.append("(\"");
                        tEntitySB.append(entity.getName());
                        tEntitySB.append("\", new Color32(");
                        tEntitySB.append(entity.getR());
                        tEntitySB.append(", ");
                        tEntitySB.append(entity.getG());
                        tEntitySB.append(", ");
                        tEntitySB.append(entity.getB());
                        tEntitySB.append(", ");
                        tEntitySB.append(entity.getA());
                        if (entity.getOffset().equals(Offset.zero)) { //if it's just (0,0)
                            tEntitySB.append("))"); //use the color-only constructor
                        } else { //if it's not (0,0)
                            tEntitySB.append("), new Vector2");
                            tEntitySB.append(entity.getOffset().toString()); //e.g. "(15, 5)"
                            tEntitySB.append(")");
                        }
                        if (!lastIteration) { //if not the last
                            tEntitySB.append(",");
                        }
                        tEntitySB.append("\n");
                }
                
            }
            
            //this will hold the final text of the file
            StringBuilder mixedComplete = new StringBuilder();
            //add on the start of the file
            mixedComplete.append(start);
            
            addGridSizeToComplete(mixedComplete);
            addCellSizeAndCellGapVariablesToComplete(mixedComplete);
            
            mixedComplete.append("\n\t[Header(\"GameObject:\")]\n");
            
            //loop through all the types for GameObjects
            for (int i = 0; i < goTypes.size(); i++) {
                //if this type is not completely empty
                if (goEntitySBs.get(i).length() != 0) {
                    //Add the array of Entities with a title matching the type:
                    //add a Header discribing the type (e.g. [Header("Enemy Entities")])
                    mixedComplete.append("\n\t[Header(\"");
                    mixedComplete.append(goTypes.get(i));
                    mixedComplete.append(" ");
                    if (dialog.getGroupEntitiesByTypeCheckBox().isSelected()) {
                        mixedComplete.append(Utils.ARRAY_NAME_EXTENSION);
                    }
                    mixedComplete.append("\")]");

                    mixedComplete.append("\n\tpublic ");
                    mixedComplete.append("GOEntity");
                    mixedComplete.append("[] ");

                    //use the formatted type name as a variable name
                    mixedComplete.append(goFormattedTypes.get(i));

                    //if the user wants to group entities by type
                    if (dialog.getGroupEntitiesByTypeCheckBox().isSelected()) {
                        mixedComplete.append(Utils.ARRAY_NAME_EXTENSION);
                    }

                    mixedComplete.append(" = new ");
                    mixedComplete.append("GOEntity");
                    mixedComplete.append("[] {\n");
                    mixedComplete.append(goEntitySBs.get(i));
                    mixedComplete.append("\t};\n");

                    //add on the tooltip
                    mixedComplete.append("\t[Tooltip(\"Must have the same size as ");
                    mixedComplete.append(goFormattedTypes.get(i));
                    if (dialog.getGroupEntitiesByTypeCheckBox().isSelected()) {
                        mixedComplete.append(Utils.ARRAY_NAME_EXTENSION);
                    }
                    mixedComplete.append("\")]\n");

                    //add on the actuall array that holds the objects to be loaded
                    mixedComplete.append("\tpublic ");
                    mixedComplete.append("GameObject");
                    mixedComplete.append("[] ");
                    mixedComplete.append(goFormattedTypes.get(i));
                    mixedComplete.append("GameObject");
                    mixedComplete.append("s;\n");

                    //add a new line between each type
                    mixedComplete.append("\n");
                }
            }
            
            mixedComplete.append("\n\t[Header(\"Tilemap:\")]\n");
            
            //loop through all the types for Tiles
            for (int i = 0; i < tTypes.size(); i++) {
                //if this type is not completely empty
                if (tEntitySBs.get(i).length() != 0) {
                    //Add the array of Entities with a title matching the type:
                    //add a Header discribing the type (e.g. [Header("Enemy Entities")])
                    mixedComplete.append("\n\t[Header(\"");
                    mixedComplete.append(tTypes.get(i));
                    mixedComplete.append(" ");
                    if (dialog.getGroupEntitiesByTypeCheckBox().isSelected()) {
                        mixedComplete.append(Utils.ARRAY_NAME_EXTENSION);
                    }
                    mixedComplete.append("\")]");

                    mixedComplete.append("\n\tpublic ");
                    mixedComplete.append("TEntity");
                    mixedComplete.append("[] ");

                    //use the formatted type name as a variable name
                    mixedComplete.append(tFormattedTypes.get(i));

                    //if the user wants to group entities by type
                    if (dialog.getGroupEntitiesByTypeCheckBox().isSelected()) {
                        mixedComplete.append(Utils.ARRAY_NAME_EXTENSION);
                    }

                    mixedComplete.append(" = new ");
                    mixedComplete.append("TEntity");
                    mixedComplete.append("[] {\n");
                    mixedComplete.append(tEntitySBs.get(i));
                    mixedComplete.append("\t};\n");

                    //add on the tooltip
                    mixedComplete.append("\t[Tooltip(\"Must have the same size as ");
                    mixedComplete.append(tFormattedTypes.get(i));
                    if (dialog.getGroupEntitiesByTypeCheckBox().isSelected()) {
                        mixedComplete.append(Utils.ARRAY_NAME_EXTENSION);
                    }
                    mixedComplete.append("\")]\n");

                    //add on the actuall array that holds the objects to be loaded
                    mixedComplete.append("\tpublic ");
                    mixedComplete.append("TileBase");
                    mixedComplete.append("[] ");
                    mixedComplete.append(tFormattedTypes.get(i));
                    mixedComplete.append("TileBase");
                    mixedComplete.append("s;\n");

                    //add a new line between each type
                    mixedComplete.append("\n");
                }
            }
            
            mixedComplete.append("\tpublic void Start() {");
            
            //add on the filling loops for GameObjects
            for (int i = 0; i < goFormattedTypes.size(); i++) {
                if (goEntitySBs.get(i).length() != 0) {
                    mixedComplete.append("\n\t\tfor (int i = 0; i < ");
                    mixedComplete.append(goFormattedTypes.get(i));
                    mixedComplete.append("GameObject");
                    mixedComplete.append("s.Length; i++) {\n\t\t\t");

                    //use the formatted type name as a variable name
                    mixedComplete.append(goFormattedTypes.get(i));
                    //if the user wants to group entities by type
                    if (dialog.getGroupEntitiesByTypeCheckBox().isSelected()) {
                        //add on the word "Entities" to the name of the array
                        mixedComplete.append(Utils.ARRAY_NAME_EXTENSION);   
                    }

                    mixedComplete.append("[i].");
                    mixedComplete.append("gameObject");
                    mixedComplete.append(" = ");
                    String ft = goFormattedTypes.get(i);
                    mixedComplete.append(ft);
                    mixedComplete.append("GameObject");
                    mixedComplete.append("s[i];\n\t\t}\n");
                }
            }
            
            //add on the filling loops for Tiles
            for (int i = 0; i < tFormattedTypes.size(); i++) {
                if (tEntitySBs.get(i).length() != 0) {
                    mixedComplete.append("\n\t\tfor (int i = 0; i < ");
                    mixedComplete.append(tFormattedTypes.get(i));
                    mixedComplete.append("TileBase");
                    mixedComplete.append("s.Length; i++) {\n\t\t\t");

                    //use the formatted type name as a variable name
                    mixedComplete.append(tFormattedTypes.get(i));
                    //if the user wants to group entities by type
                    if (dialog.getGroupEntitiesByTypeCheckBox().isSelected()) {
                        //add on the word "Entities" to the name of the array
                        mixedComplete.append(Utils.ARRAY_NAME_EXTENSION);   
                    }

                    mixedComplete.append("[i].");
                    mixedComplete.append("tile");
                    mixedComplete.append(" = ");
                    String ft = tFormattedTypes.get(i);
                    mixedComplete.append(ft);
                    mixedComplete.append("TileBase");
                    mixedComplete.append("s[i];\n\t\t}\n");
                }
            }
            
            //add on the middle of the file
            mixedComplete.append(middle);
            
            
            //add on the searching loops for GameObject
            for (int i = 0; i < goTypes.size(); i++) {
                if (goEntitySBs.get(i).length() != 0) {
                    mixedComplete.append("\t\tforeach (");
                    mixedComplete.append("GOEntity entity");
                    mixedComplete.append(" in ");

                    //use the formatted type name as a variable name
                    mixedComplete.append(goFormattedTypes.get(i));

                    //if the user wants to group entities by type
                    if (dialog.getGroupEntitiesByTypeCheckBox().isSelected()) {
                        //add on the word "Entities" to the name of the array
                        mixedComplete.append(Utils.ARRAY_NAME_EXTENSION);   
                    }
                    mixedComplete.append(") {\n\t\t\t");
                    mixedComplete.append("placeGameObjectIfColorMatches(entity, ");
                    mixedComplete.append("pixelColor, x, y, index);");
                    mixedComplete.append("\n\t\t}\n");
                }
            }
            
            //add on the searching loops for Tile
            for (int i = 0; i < tTypes.size(); i++) {
                if (tEntitySBs.get(i).length() != 0) {
                    mixedComplete.append("\t\tforeach (");
                    mixedComplete.append("TEntity entity");
                    mixedComplete.append(" in ");

                    //use the formatted type name as a variable name
                    mixedComplete.append(tFormattedTypes.get(i));

                    //if the user wants to group entities by type
                    if (dialog.getGroupEntitiesByTypeCheckBox().isSelected()) {
                        //add on the word "Entities" to the name of the array
                        mixedComplete.append(Utils.ARRAY_NAME_EXTENSION);   
                    }
                    mixedComplete.append(") {\n\t\t\t");
                    mixedComplete.append("placeTileIfColorMatches(entity, ");
                    mixedComplete.append("pixelColor, x, y, index);");
                    mixedComplete.append("\n\t\t}\n");
                }
            }
            
            //close the last braces
            mixedComplete.append("\t}\n");
            mixedComplete.append("}\n");
            mixedComplete.append("\n");
            
            //return the completed text
            return mixedComplete;
        } catch (IOException ex) {
            System.err.println("I/O Exception: Could not read file\n"
                    + ex.toString());
            return null;
        }
    }
    
    //MARK: All
    /**
     * Generates the text for the GameObjectLevelGenerator script using all of
     * the entities.
     * @param imageFolder the folder that holds images for analysis
     * @param startFileName
     * @param middleFileName
     * @param singleType If the user does not want to sort the entities by
     * type, then we will need a string to use as a single "type" to hold
     * all of the entities
     * @param singleFormattedType The formatted name of the type that we're
     * making an array for
     * @param className should be "GameObjectEntity", "TileEntity",
     * @param basicClassName "GameObject" or "TileBase"
     * @param basicClassArrayName "gameObjects" or "tiles"
     * @param entityAttribute "gameObject" or "tile"
     * @param placeFunction e.g. 
     * (for GameObject)
     * "instantiateIfMatch(entity, pixelColor, x, y);", or
     * (for Tilemap)
     * "placeTileIfMatch(entity, pixelColor, x, y, index);", or
     * (for Mixed)
     * "placeTileIfColorMatches(entity, pixelColor, x, y, index);", or
     * "placeGameObjectIfColorMatches(entity, pixelColor, x, y, index);"
     * @return The text for the LevelGenerator.cs file. Null in the odd
     * circumstance that the user wants to use image analysis but the
     * folder specified does not contain any images.
     */
    private StringBuilder getLevelGeneratorText(
            File imageFolder,
            String startFileName,
            String middleFileName,
            String singleType,
            String singleFormattedType,
            String className,
            String basicClassName,
            String entityAttribute,
            String placeFunction) {
        try {
            
            // The LevelGenerator Script
            //get the start and end text
            StringBuilder start = readResource(startFileName);
            StringBuilder middle = readResource(middleFileName);
            
            //holds the entities to generate scripts with
            List<Entity> projectEntities = getProjectEntities(imageFolder);
            if (projectEntities == null) {
                return null;
            }
            
            List<String> types;
            List<String> formattedTypes = new ArrayList();
            //if the user wants to organize by type
            if (dialog.getGroupEntitiesByTypeCheckBox().isSelected()) {
                //set types to be all the types in the project
                types = project.getTypes();
                //cycle through all the types
                for (String type : types) {
                    //add the formatted version
                    formattedTypes.add(formatType(type));
                }
            } else {
                //initialize the types list
                types = new ArrayList();
                types.add(singleType);
                formattedTypes.add(singleFormattedType);
            }
            
            List<StringBuilder> entitySBs 
                    = constructEntityStringBuilder(
                            types, projectEntities, className);
            
            //this will hold the final text of the file
            StringBuilder complete = new StringBuilder();
            //add on the start of the file
            complete.append(start);
            
            switch (scriptType) {
                case GameObject:
                    addGridSizeToComplete(complete);
                    break;
                default:
                    addCellSizeAndCellGapVariablesToComplete(complete);
            }
            
            //loop through all the types
            for (int i = 0; i < types.size(); i++) {
                //if this type is not completely empty
                if (entitySBs.get(i).length() != 0) {
                    //add the array of Entities with a title matching the type
                    addTypeToCompleteSB(
                            complete, 
                            entitySBs.get(i), 
                            types.get(i), 
                            formattedTypes.get(i),
                            className,
                            basicClassName);
                }
            }
            
            complete.append("\tpublic void Start() {");
            
            //add on the filling loops
            addFillingLoopForEachTypeToCompleteSB(
                complete,
                formattedTypes,
                entitySBs,
                basicClassName,
                entityAttribute);
            
            //add on the middle of the file
            complete.append(middle);
            //add on the searching loops
            addSearchingLoopForEachTypeToCompleteSB(
                    complete, 
                    types, 
                    formattedTypes,
                    entitySBs,
                    className + " entity",
                    placeFunction);
            //return the completed text
            return complete;
        } catch (IOException ex) {
            System.err.println("I/O Exception: Could not read file\n"
                    + ex.toString());
            return null;
        }
    }
    
    /**
     * Constructs as many comma-separated lists of entity objects (be they
     * GameObjectEntities, TileEntities, TEntities, or GOEntities, depending
     * on the type of entity (Tile or GameObject) and on the ScriptType) as
     * there are types and puts them into a List of StringBuilder objects, so
     * that each element of the returned list is a comma-separated list of
     * entity objects that share a common type.
     * @param types The types that the entities are to be grouped by.
     * @param projectEntities The entities to be added to the StringBuilder.
     * @param className should be "GameObjectEntity", "TileEntity",
     * "TEntity", or "GOEntity".
     * @return A StringBuilder object with the comma-separated list of
     * entity objects.
     */
    private List<StringBuilder> constructEntityStringBuilder(
            List<String> types, List<Entity> projectEntities, String className) {
        
        List<StringBuilder> entitySBs = new ArrayList();
        //initialize an empty StringBuilder for each type
        for (int t = 0; t < types.size(); t++) {
            entitySBs.add(new StringBuilder());
        }

        //loop through all the entities in the project
        for (int i = 0; i < projectEntities.size(); i++) {
            boolean lastIteration = (i == projectEntities.size() - 1);
            Entity e = projectEntities.get(i);
            int typeIndex = determineTypeIndex(e);
            //put e in the StringBuilder corrosponding to the typeIndex
            addEntityToStringBuilder(e, 
                    entitySBs.get(typeIndex), className, lastIteration);
        }
        
        return entitySBs;
    }
    
    /**
     * Adds the passed in Entity to the entitySB StringBuilder as
     * GameObjectEntities, TileEntities, TEntities, or GOEntities, depending
     * on the type of entity (Tile or GameObject) and on the ScriptType.
     * Includes a comma afterwards unless told this is the last iteration.
     * @param entity The Entity to add
     * @param entitySB the entitySB StringBuilder that will hold the list
     * of comma-separated Entity objects as we continue to call this function.
     * @param className should be "GameObjectEntity", "TileEntity",
     * "TEntity", or "GOEntity".
     * @param lastIteration Specifies whether this is the last iteration. If
     * not, add a comma. If so, forgo the comma.
     */
    private void addEntityToStringBuilder(Entity entity, 
            StringBuilder entitySB, String className, boolean lastIteration) {
        /*
        Depending on ScriptType, entities look like this:
            new GameObjectEntity("Barrier", new Color32(0, 0, 0, 255)),
            new TileEntity("Barrier", new Color32(0, 0, 0, 255)),
            new TEntity("Barrier", new Color32(0, 0, 0, 255)),
            new GOEntity("Barrier", new Color32(0, 0, 0, 255)),
        Everything is the same except for the name of the class.
        */
        
        //Add to the entityObjects array
        entitySB.append("\t\tnew ");
        entitySB.append(className);
        entitySB.append("(\"");
        entitySB.append(entity.getName());
        entitySB.append("\", new Color32(");
        entitySB.append(entity.getR());
        entitySB.append(", ");
        entitySB.append(entity.getG());
        entitySB.append(", ");
        entitySB.append(entity.getB());
        entitySB.append(", ");
        entitySB.append(entity.getA());
        if (entity.getOffset().equals(Offset.zero)) { //if it's just (0,0)
            entitySB.append("))"); //use the color-only constructor
        } else { //if it's not (0,0)
            entitySB.append("), new Vector2");
            entitySB.append(entity.getOffset().toString()); //e.g. "(15, 5)"
            entitySB.append(")");
        }
        if (!lastIteration) { //if not the last
            entitySB.append(",");
        }
        entitySB.append("\n");
    }
    
    /**
     * Uses the gridSize field to create a float that represents this value
     * in C# and adds it to the "complete" StringBuilder.
     * @param complete The "complete" StringBuilder
     */
    private void addGridSizeToComplete(StringBuilder complete) {
        // Add on the gridSize variable
        complete.append("\tpublic float gridSize = ");
        complete.append(gridSize);
        complete.append("f;\n");
    }
    
    /**
     * Uses the cellSize and cellGap fields to create a set of Vector3 objects
     * that represent these values in C# and adds them to the "complete"
     * StringBuilder.
     * @param complete The "complete" StringBuilder
     */
    private void addCellSizeAndCellGapVariablesToComplete(
            StringBuilder complete) {
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
                dialog.setSize(380, 375);
                dialog.getGameObjectScriptsPanel().setVisible(true);
                dialog.getTileScriptsPanel().setVisible(false);
                dialog.getMixedScriptsPanel().setVisible(false);

                dialog.getGridSizePanel().setVisible(true);
                dialog.getCellSizeGapPanel().setVisible(false);
                break;
            case Tilemap:
                dialog.setSize(380, 420);
                dialog.getGameObjectScriptsPanel().setVisible(false);
                dialog.getTileScriptsPanel().setVisible(true);
                dialog.getMixedScriptsPanel().setVisible(false);

                dialog.getGridSizePanel().setVisible(false);
                dialog.getCellSizeGapPanel().setVisible(true);
                break;
            default:
                dialog.setSize(380, 460);
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
        
        //if the first character is uppercase
        if (65 <= chars[0] && chars[0] <= 90) {
            //make it lowercase
            chars[0] = (char) (chars[0] + 32);
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
    
    /**
     * Gets a list of entities to use for generating scripts
     * @param imageFolder The image folder, in case the user wants to use
     * images.
     * @return A list of entities to generate scripts for. Null if the user
     * wants to use images, but no images could be found.
     */
    private List<Entity> getProjectEntities(File imageFolder) {
        //if the user wants to use images
        if (dialog.getUseImagesCheckBox().isSelected()) {
            //use only the correct entities
            List<Entity> projectEntities 
                    = getUsedEntities(imageFolder, project.getEntities());
            //if no image files were found
            if (projectEntities == null) {
                JOptionPane.showMessageDialog(dialog, 
                        "No images could be found in\n" + 
                                imageFolder.toString());
                return null;
            } else {
                //otherwise return the used entities
                return projectEntities;
            }
        } else {
            //get the project's entities
            return project.getEntities();
        }
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
    
    /**
     * Convenience method to improve readability and reduce duplicate code.
     * Returns the entity's typeIndex if the user wants to group by type,
     * or 0 if the user does not want to group by type.
     * @param entity The entity for which we might need it's typeIndex, but
     * only if the user wants to group by type.
     * @return the entity's typeIndex if the user wants to group by type,
     * or 0 if the user does not want to group by type.
     */
    private int determineTypeIndex(Entity entity) {
        //if the user wants to group by type
        if (dialog.getGroupEntitiesByTypeCheckBox().isSelected()) {
            //get the index of this entity's type
            return entity.getTypeIndex();
        } 
        //leave it at 0 if the user is not grouping by type
        return 0;
    }
}
