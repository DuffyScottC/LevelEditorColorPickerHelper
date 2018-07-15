/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Entity;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import views.EntityListRenderer;
import views.MainFrame;


/**
 * Responsible for controlling the Results JList in the GUI. Examples include general 
 * setup (ListManagers, ListRenderers), content management, etc. None of this
 * code should be in the main Controller class.
 * @author Scott
 */
public class ResultsListController {
    
    private final DefaultListModel resultsListModel = new DefaultListModel();
    private final JList resultsList;
    private final EntityListRenderer entityListRenderer;
    /**
     * This searchMode is synced with the searchMode in the SearchController
     */
    private SearchMode searchMode = SearchMode.Name;
    /**
     * This compares passed in entities based on the current search type.
     */
    private final Comparator compareEntities = (Comparator) (Object o1, Object o2) -> {
        if ((o1 instanceof Entity) && (o2 instanceof Entity)) {
            Entity e1 = (Entity) o1;
            Entity e2 = (Entity) o2;
            switch (searchMode) {
                case Name:
                    return e1.getName().compareTo(e2.getName());
                case Color:
                    return e1.getColor().getRGB() - e2.getColor().getRGB();
                default:
                    return e1.getUnityPrefab().compareTo(e2.getUnityPrefab());
            }
        }
        return -1;
    };
    
    /**
     * All the entities that are in the resultsList, which is all the entities
     * that match the user's search string.
     */
    private final List<Entity> entitiesInResults;
    
    public ResultsListController(MainFrame frame) {
        resultsList = frame.getResultsList();
        resultsList.setModel(resultsListModel);
        resultsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.entitiesInResults = new ArrayList();
        entityListRenderer = new EntityListRenderer(this.entitiesInResults);
        resultsList.setCellRenderer(entityListRenderer);
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
     * Updates the results list model to reflect the contents of the
     * entitiesInResults list. Also selects the first entity in the list.
     */
    private void updateListModel() {
        resultsListModel.clear();
        for (Entity e : entitiesInResults) {
            resultsListModel.addElement(e);
        }
        resultsList.repaint();
        resultsList.setSelectedIndex(0);
        //sort the results
        entitiesInResults.sort(compareEntities);
    }
    
    /**
     * Remove all entities from the results list (this also adds an empty
     * entity that gives the user instructions on how to create a new
     * entity for the project.
     */
    public void clearEntities() {
        entitiesInResults.clear();
        updateListModel();
    }

    public void setSearchMode(SearchMode searchMode) {
        this.searchMode = searchMode;
    }
    
    public void setEntitiesInResults(List<Entity> entitiesInResults) {
        //clear all the elements in this.entitiesInResults
        this.entitiesInResults.clear();
        //add all the entities to the results list
        this.entitiesInResults.addAll(entitiesInResults);
        updateListModel();
    }
    
    public List<Entity> getEntitiesInResults() {
        return entitiesInResults;
    }
    
    /**
     * Sets the selected index in the visual JList
     * @param index The index to be selected (-1 if deselecting)
     */
    public void setSelectedIndex(int index) {
        //if the passed index is a valid index
        if (0 <= index && index < resultsListModel.size()) {
            resultsList.setSelectedIndex(index);
            resultsList.repaint();
        } else if (index == -1) {
            resultsList.clearSelection();
        }
    }
    
    public void clearSelection() {
        resultsList.clearSelection();
    }
    
    /**
     * Gets the selected index of the resultsList JList.
     * @return The selected index of the resultsList JList (-1 if there is
     * no selection)
     */
    public int getSelectedIndex() {
        return resultsList.getSelectedIndex();
    }

    void removeSelectedEntity() {
        int oldIndex = resultsList.getSelectedIndex();
        //remove the selected entity
        entitiesInResults.remove(oldIndex);
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
        if (newIndex < entitiesInResults.size()) {
            //select the the entity above the deleted entity
            resultsList.setSelectedIndex(newIndex);
        }
    }
    
}
