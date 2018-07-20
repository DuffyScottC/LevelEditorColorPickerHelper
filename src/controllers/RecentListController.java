/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Entity;
import java.io.File;
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
    private final EntityListRenderer entityListRenderer;
    
    public RecentListController(MainFrame frame) {
        recentList = frame.getRecentList();
        recentList.setModel(recentListModel);
        this.entitiesInRecent = new ArrayList();
        entityListRenderer = new EntityListRenderer(this.entitiesInRecent);
        recentList.setCellRenderer(entityListRenderer);
    }
    
    /**
     * Gives the list ResultsListController's resultsList's
     * EntityListRenderer a reference to the project Resource location so
     * that it can access the entity image files
     * @param projectResourceLocation 
     */
    public void setProjectResourceLocation(File projectResourceLocation) {
        entityListRenderer.setProjectResourceLocation(projectResourceLocation);
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
        int index = entitiesInRecent.indexOf(entity);
        //if this entity is already in the list
        if (index != -1) {
            //remove the entity from the list
            entitiesInRecent.remove(index);
        } else {
            //if the entitiesInRecent is filled up
            if (entitiesInRecent.size() == size) {
                //remove the last one in the list
                entitiesInRecent.remove(size - 1);
            }
        }
        //add the new entity to the top
        entitiesInRecent.add(0, entity);
        //update the list model
        updateListModel();
    }
    
    public void clearEntities() {
        entitiesInRecent.clear();
        updateListModel();
    }

    /**
     * Sets the selected index in the visual JList
     * @param index The index to be selected (-1 if deselecting)
     */
    public void setSelectedIndex(int index) {
        //if the passed index is a valid index
        if (0 <= index && index < recentListModel.size()) {
            recentList.setSelectedIndex(index);
            recentList.repaint();
        } else if (index == -1) {
            recentList.clearSelection();
        }
    }
    
    public int getSelectedIndex() {
        return recentList.getSelectedIndex();
    }
    
    public void removeSelectedEntity() {
        int oldIndex = recentList.getSelectedIndex();
        //if none selected, stop
        if (oldIndex == -1) {
            return;
        }
        //remove the selected entity
        entitiesInRecent.remove(oldIndex);
        //update the list model
        updateListModel();
        //set the index to one less than what it was
        int newIndex = oldIndex - 1;
        //if the index is less than 0
        if (newIndex < 0) {
            //set it to 0
            newIndex = 0;
        }
        //if there are entities left
        if (newIndex < entitiesInRecent.size()) {
            //select the the entity above the deleted entity
            recentList.setSelectedIndex(newIndex);
        }
    }

    public List<Entity> getEntitiesInRecent() {
        return entitiesInRecent;
    }
    
    public void clearSelection() {
        recentList.clearSelection();
    }
    
}
