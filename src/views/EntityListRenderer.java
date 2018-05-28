/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.Utils;
import entities.Entity;
import java.awt.Component;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 *
 * @author Scott
 */
public class EntityListRenderer extends DefaultListCellRenderer {

    /**
     * A reference to the entities in the result list
     */
    private List<Entity> entitiesInList;
    private Font font = new Font("helvitica", Font.PLAIN, 15);
    /**
     * Points to the project Resource folder
     */
    private File projectResourceLocation = null;

    /**
     * This is to be used in the lists that display the entities. You must pass
     * in a reference to the list that holds the entities contained in the
     * element.
     *
     * @param entitiesInList
     */
    public EntityListRenderer(List<Entity> entitiesInList) {
        this.entitiesInList = entitiesInList;
    }

    @Override
    public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {
        //Call the original super method
        JLabel label = (JLabel) super.getListCellRendererComponent(
                list, value, index, isSelected, cellHasFocus);
        Entity entity = entitiesInList.get(index);
        
        //get the path to the image associated with the entity at this index
        File imageFile = getEntityImageFile(entity);
        
        BufferedImage newImage = null;
        if (imageFile != null) {
            //check if the image exists
            if (imageFile.exists()) {
                try {
                    newImage = ImageIO.read(imageFile);
                } catch (IOException ex) {
                    System.err.println("Trouble reading file:\n" + imageFile.toString()
                            + "\n" + ex.toString());
                }
            } else {
                //Create a blank buffered image
                newImage = Utils.getBlankBufferedImage(32, 32, entity.getColor());
            }
        } else {
            //Create a blank buffered image
            newImage = Utils.getBlankBufferedImage(32, 32, entity.getColor());
        }
        
        //create an image icon from the new image
        ImageIcon imageIcon = new ImageIcon(newImage);
        //set the label's icon to this image
        label.setIcon(imageIcon);
        
        label.setHorizontalTextPosition(JLabel.RIGHT);
        label.setFont(font);

        return label;
    }
    
    /**
     * Gets the file where the entity's image should be
     * @param entity
     * @return A file object with the entity's image, null if the entity has no
     * image or if the project Resource folder does not exist or is set to null.
     */
    private File getEntityImageFile(Entity entity) {
        if (projectResourceLocation != null) {
            if (projectResourceLocation.exists()) {
                Path projectResourcePath = projectResourceLocation.toPath();
                Path entityImageFile = Paths.get(projectResourcePath.toString(),
                        entity.getImage());
                return entityImageFile.toFile();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
    
    /**
     * Gives the list renderer a reference to the project Resource location so
     * that it can access the entity image files
     * @param projectResourceLocation 
     */
    public void setProjectResourceLocation(File projectResourceLocation) {
        this.projectResourceLocation = projectResourceLocation;
    }
    
    

}
