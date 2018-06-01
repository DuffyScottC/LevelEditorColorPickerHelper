/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scriptgeneration;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        
        File resourceFolder = project.getProjectResourceFolder();
        if (resourceFolder == null) {
            resourceFolder = new File(System.getProperty("user.dir"));
        }
        JFileChooser chooser = new JFileChooser(resourceFolder);
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
    }

    public void showDialog() {
        dialog.setVisible(true);
    }
    
    /**
     * Generates the scripts for the path at the given destination.
     * @param destination The destination for the Scripts
     */
    private void generateScripts(File destination) {
        try {
            
            StringBuilder start = readResource("/resources/start.cs");
            StringBuilder end = readResource("/resources/end.cs");
            
            /*
            Add on elements of the format:
            new ColorToPrefab(new Color(#f, #f, #f, 255f))
            with commas in between
            where # is the respective r, g, b value
            */
            
        } catch (IOException ex) {
            System.err.println("I/O Exception: Could not read file\n"
                    + ex.toString());
        }
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
