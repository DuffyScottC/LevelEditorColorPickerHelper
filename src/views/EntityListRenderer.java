/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Entity;
import java.awt.Component;
import java.awt.Font;
import java.io.File;
import java.util.List;
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
        //get the path to the image associated with the entity at this index
        String imagePath = entitiesInList.get(index).getImage();
        //check if the image exists
        if (imageFileExists(imagePath)) {
            //Create an ImageIcon with this image path
            ImageIcon imageIcon = new ImageIcon(imagePath);
            //set the label's icon to this image
            label.setIcon(imageIcon);
        }
        
        label.setHorizontalTextPosition(JLabel.RIGHT);
        label.setFont(font);

        return label;
    }

    private boolean imageFileExists(String imagePath) {
        File file = new File(imagePath);
        return file.exists();
    }

}
