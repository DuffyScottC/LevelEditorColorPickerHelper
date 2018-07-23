/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scriptgeneration;

import controllers.Utils;
import entities.Entity;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.prefs.Preferences;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import projects.Project;
import views.MainFrame;

/**
 * Takes the currently open project and generates a GIMP Palette
 * file using the colors in the entities.
 * @author Scott
 */
public class PaletteGenerator {
    
    public PaletteGenerator(Project project, MainFrame frame) {
        Preferences prefs = Preferences.userRoot().node(this.getClass().getName());
        
        String destinationChooserFilePath 
                = prefs.get(Utils.GIMP_DESTINATION_CHOOSER_PATH, 
                        System.getProperty("user.dir"));
        File destinationChooserFile = new File(destinationChooserFilePath);
        //make it the parent so they can actually CLICK the desired folder again
        JFileChooser destinationChooser 
                = new JFileChooser(destinationChooserFile.getParent());
        destinationChooser.setMultiSelectionEnabled(false);
        destinationChooser.setDragEnabled(true);
        FileFilter projectFileFilter 
            = new FileNameExtensionFilter("GIMP Palette (.gpl)","gpl");
        destinationChooser.setSelectedFile(new File(
                destinationChooserFilePath + 
                        project.getName() + ".gpl"));
        
        int result = destinationChooser.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = destinationChooser.getSelectedFile();
            //add on .gpl unless the user had their own extension
            selectedFile = ensureExtension(selectedFile);
            //save the current directory to the user's preferences
            prefs.put(Utils.GIMP_DESTINATION_CHOOSER_PATH, selectedFile.toString());
            if (selectedFile.exists()) {
                //if the user does NOT want to continue
                if (!Utils.shouldContinue("The file " + selectedFile.getName() 
                        + "already exists at \n" 
                        + selectedFile.getAbsolutePath() 
                        + ".\nWould you like to overwrite this file?\n",
                        frame)) {
                    return;
                }
                //if the user does want to continue, continue
            }
            
            generateGIMPPalette(project, selectedFile);
        }
    }
    
    /**
     * Generates a GIMP Palette from the project using the file as the
     * destination.
     * @param selectedFile 
     */
    private void generateGIMPPalette(Project project, File selectedFile) {
        /*
        .gpl files look like this:
        
        GIMP Palette
        Channels: RGBA
        #
          0   0   0   0 Transparent
        254  91  89 255 Red
        247 165  71 255 Orange
        
        where the space between the last number and the name is a tab (\t)
        */
        
        StringBuilder complete = new StringBuilder();
        
        complete.append("GIMP Palette\n");
        complete.append("Channels: RGBA\n#\n");
        
        for (Entity entity : project.getEntities()) {
            complete.append(formatNumber(entity.getR()));
            complete.append(" ");
            complete.append(formatNumber(entity.getG()));
            complete.append(" ");
            complete.append(formatNumber(entity.getB()));
            complete.append(" ");
            complete.append(formatNumber(entity.getA()));
            complete.append("\t");
            complete.append(entity.getName().replaceAll("\\W", "_"));
            complete.append("\n");
        }
        
        Utils.createFile(selectedFile, complete.toString());
    }
    
    /**
     * Converts the number into a string that consists of 3 characters.
     * If it's a three-digit number, then it's three numbers.
     * if it's any less than that, any lack of digits is taken up by 
     * a white space. Examples:
     * "255",
     * " 34",
     * "  0".
     * @param number The number to be formatted
     * @return The formatted number as a string.
     */
    private String formatNumber(int number) {
        if (0 <= number && number <= 9) { //1 digit
            //add on 2 spaces
            return "  " + number;
        } else if (10 <= number && number <= 99) { //2 digits
            //add on 1 space
            return " " + number;
        } else { //3 digits
            //add on 0 spaces
            return "" + number;
        }
    }

    /**
     * Ensures that the user has a file extension of .gpl, unless they provided
     * their own extension. 
     * @param selectedFilePath The path to the file that the user chose to 
     * save their color palette as
     * @return The passed in file with the appropriate extension
     */
    private File ensureExtension(File selectedFile) {
        Path selectedFilePath = selectedFile.toPath();
        //check if the file has an extension already
        String fileName = selectedFilePath.getFileName().toString();
        //if the file name has NO extension
        if (!fileName.matches(".*\\.\\w+")) {
            //add .gpl
            String fileNameWithExtension = fileName + ".gpl";
            //use the resolveSibling method to change the old, 
            //extensionless file name to the new filename created above
            selectedFilePath = selectedFilePath.resolveSibling(fileNameWithExtension);
            //e.g. this will replace "curdir/sample2" with "curdir/sample2.graph"
        }
        //convert the path object to a file object
        return selectedFilePath.toFile();
    }
    
}
