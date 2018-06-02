/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Entity;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import views.EntityListRenderer;
import views.MainFrame;

/**
 *
 * @author Scott
 */
public class RecentListController {
    private final DefaultListModel recentListModel = new DefaultListModel();
    private final JList recentList;
    private final List<Entity> entitiesInRecent;
    private int size = 5;
    
    public RecentListController(MainFrame frame) {
        recentList = frame.getRecentList();
        recentList.setModel(recentListModel);
        this.entitiesInRecent = new ArrayList();
        recentList.setCellRenderer(new EntityListRenderer(this.entitiesInRecent));
    }
    
    /**
     * Remove all entities from the recent list.
     */
    private void updateListModel() {
        recentListModel.clear();
        for (Entity e : entitiesInRecent) {
            recentListModel.addElement(e);
        }
        recentList.repaint();
    }
    
    /**
     * Adds an entity to the top of the list and removes the entity
     * at the bottom of the list, if applicable.
     * @param entity 
     */
    public void addEntity(Entity entity) {
        //if the entitiesInRecent is filled up
        if (entitiesInRecent.size() == size) {
            //remomve the last one in the list
            entitiesInRecent.remove(size - 1);
        }
        //add the new entity to the top
        entitiesInRecent.add(entity);
        //update the list model
        updateListModel();
    }
    
    public void clearEntities() {
        entitiesInRecent.clear();
        updateListModel();
    }

    public void setSelectedIndex(int index) {
        //if the passed index is a valid index
        if (0 <= index && index < recentListModel.size()) {
            recentList.setSelectedIndex(index);
            recentList.repaint();
        }
    }
    
    public int getSelectedIndex() {
        return recentList.getSelectedIndex();
    }

    public List<Entity> getEntitiesInRecent() {
        return entitiesInRecent;
    }
    
    
}
