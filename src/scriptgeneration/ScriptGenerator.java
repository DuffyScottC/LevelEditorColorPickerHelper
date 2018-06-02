/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scriptgeneration;

import controllers.Utils;
import entities.Entity;
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
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import projects.Project;
import views.MainFrame;
import views.ScriptGeneratorDialog;

/**
 * Handles generating scripts
 * @author Scott
 */
public class ScriptGenerator {
    
    private final Project project;
    private final ScriptGeneratorDialog dialog;
    
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
            if (dialog.getLevelGeneratorCheckBox().isSelected()) {
                StringBuilder levelGenerator = getLevelGeneratorText();
                createFile(destination, "LevelGenerator.cs", levelGenerator.toString());
            }
            
            if (dialog.getEntityCheckBox().isSelected()) {
                StringBuilder entity = readResource("/resources/Entity.cs");
                createFile(destination, "Entity.cs", entity.toString());
            }
            
            if (dialog.getEntityArrayDrawerCheckBox().isSelected()) {
                StringBuilder entityArrayDrawer = readResource("/resources/EntityArrayDrawer.cs");
                createFile(destination, "EntityArrayDrawer.cs", entityArrayDrawer.toString());
            }
            
            if (dialog.getEntityArrayAttributeCheckBox().isSelected()) {
                StringBuilder entityArrayAttribute = readResource("/resources/entityArrayAttribute.cs");
                createFile(destination, "entityArrayAttribute.cs", entityArrayAttribute.toString());
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
            
            // The LevelGenerator Sctipt
            //get the start and end text
            StringBuilder start = readResource("/resources/start.cs");
            StringBuilder end = readResource("/resources/end.cs");
            
            //get the project's entities
            List<Entity> projectEntities = project.getEntities();
            
            /*
            The elements in the names array for the EntityArrayAttribute
            arguments look like: 
                "Name",
            The elements in the colorToPrefabs array look like:
                new Entity(new Color32(0, 0, 0, 255)),
            but for each list the last element has no comma
            */
            
            /*
            allSBs is a list containing both namesSBs and entitySBs, split
            evenly (since they both have the same number of elements).
            */
            List<StringBuilder> allSBs = constructEntityStringBuilders();
            /*
            The first half of allSBs. This holds all the names of the entities
            
            */
            List<StringBuilder> namesSBs 
                    = allSBs.subList(0, allSBs.size()/2 -1);
            //the second half
            List<StringBuilder> entitySBs 
                    = allSBs.subList(allSBs.size()/2, allSBs.size() - 1);
            
            
            StringBuilder complete = new StringBuilder();
            complete.append(start);
            //loop through all the StringBuilders
            for (int i = 0; i < namesSBs.size(); i++) {
                complete.append("#if UNITY_EDITOR\n");
                complete.append("\n\t[EntityArrayAttribute(new string[] {\n");
                complete.append(namesSBs.get(i));
                complete.append("\t})]\n");
                complete.append("#endif\n");
                complete.append("\tpublic Entity[] entities = new Entity[] {\n");
                complete.append(entitySBs.get(i));
                complete.append("\t};\n");
            }
            complete.append(end);
            return complete;
            
        } catch (IOException ex) {
            System.err.println("I/O Exception: Could not read file\n"
                    + ex.toString());
            return null;
        }
    }
    
    private List<StringBuilder> constructEntityStringBuilders() {
        //for each entity in the project
        for (int i = 0; i < projectEntities.size(); i++) {
            //get the current entity
            Entity entity = projectEntities.get(i);

            //true if this is the last iteration
            boolean last = (i == projectEntities.size() - 1);

            addEntityToStringBuilders(entity, names, entityObjects, last);
        }
    }
    
    /**
     * Adds the passed in Entity to the names and entityObject StringBuilders,
     * including a comma afterwards unless told this is the last iteration.
     * @param entity The Entity to add
     * @param names The names StringBuilder
     * @param entityObjects the entityObjects StringBuilder
     * @param last Specifies whether this is the last iteration
     */
    private void addEntityToStringBuilders(Entity entity, StringBuilder names, 
            StringBuilder entityObjects, boolean last) {
        //Add to the names array
        names.append("\t\t\"");
        names.append(entity.getName());
        names.append("\"");
        if (!last) { //if not the last
            names.append(",");
        }
        names.append("\n");

        //Add to the colorToPrefabs array
        entityObjects.append("\t\tnew Entity(new Color32(");
        entityObjects.append(entity.getR());
        entityObjects.append(", ");
        entityObjects.append(entity.getG());
        entityObjects.append(", ");
        entityObjects.append(entity.getB());
        entityObjects.append(", 255))");
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
    
}
