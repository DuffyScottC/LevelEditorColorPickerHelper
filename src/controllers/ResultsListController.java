/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Entity;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import views.EntityListRenderer;
import views.MainFrame;


/**
 * Responsible for controlling the Results JList in the GUI. Examples include general 
 * setup (ListManagers, ListRenderers), content management, etc. None of this
 * code should be in the main Controller class.
 * @author Scott
 */
public class ResultsListController {
    
    private final MainFrame frame;
    
    private final DefaultListModel resultsListModel = new DefaultListModel();
    private final JList resultsList;
    private final List<Entity> entitiesInResults;
    
    public ResultsListController(MainFrame frame) {
        this.frame = frame;
        resultsList = frame.getResultsList();
        resultsList.setModel(resultsListModel);
        resultsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.entitiesInResults = new ArrayList();
        resultsList.setCellRenderer(new EntityListRenderer(this.entitiesInResults));
        
        resultsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadSelectedEntity();
            }
        });
    
    }
    
    /**
     * Loads the selected entity into the frame's infoPanel
     */
    public void loadSelectedEntity() {
        System.out.println("Selected " + resultsList.getSelectedValue());
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
    }
    
    /**
     * Remove all entities from the results list (this also adds an empty
     * entity that gives the user instructions on how to create a new
     * entity for the project.
     */
    public void clearEntities() {
        entitiesInResults.clear();
        entitiesInResults.add(new Entity(null, 
                "Choose \"Add Entity\" below!", null, Color.black, null));
        updateListModel();
    }
    
    public void setEntities(List<Entity> entitiesInResults) {
        //add all the entities to the results list
        this.entitiesInResults.addAll(entitiesInResults);
        updateListModel();
    }
    
    /**
     * Sets the selected index in the visual JList
     * @param index The index to be selected
     */
    public void setSelectedIndex(int index) {
        //if the passed index is a valid index
        if (0 <= index && index < resultsListModel.size()) {
            resultsList.setSelectedIndex(index);
        }
    }
    
}
