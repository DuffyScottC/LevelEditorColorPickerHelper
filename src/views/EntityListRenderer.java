/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.Utils;
import entities.Entity;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        File imageFile = entity.getImage();
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

}
