/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Entity;
import java.awt.Component;
import java.net.URL;
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
    
    List<Entity> entitiesInList;
    
    /**
     * This is to be used in the lists that display the entities.
     * You must pass in a reference to the list that holds the
     * entities contained in the element. 
     * @param entitiesInList 
     */
    public EntityListRenderer (List<Entity> entitiesInList) {
        this.entitiesInList = entitiesInList;
    }
    
    @Override
    public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(
                list, value, index, isSelected, cellHasFocus);
        
        ImageIcon imageIcon;
        String path = entitiesInList.get(index).getImage();
        URL imgURL = EntityListRenderer.class.getResource(path);
        if (imgURL != null) {
            imageIcon = new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            imageIcon = null;
        }
        
        
        return label;
    }

}
