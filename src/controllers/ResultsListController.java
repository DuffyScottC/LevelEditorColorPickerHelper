/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Entity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
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
    private final List<Entity> entitiesInResults;
    
    private final JComboBox typeComboBox;
    
    public ResultsListController(MainFrame frame) {
        resultsList = frame.getResultsList();
        resultsList.setModel(resultsListModel);
        resultsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.entitiesInResults = new ArrayList();
        resultsList.setCellRenderer(new EntityListRenderer(this.entitiesInResults));
        
        typeComboBox = frame.getTypeComboBox();
        setUpComboBox();
    }
    
    private void updateListModel() {
        resultsListModel.clear();
        for (Entity e : entitiesInResults) {
            resultsListModel.addElement(e);
        }
        resultsList.repaint();
    }
    
    /**
     * Remove all entities from the results list (this also adds an empty
     * entity that gives the user instructions on how to create a new
     * entity for the project.
     */
    public void clearEntities() {
        entitiesInResults.clear();
        entitiesInResults.add(new Entity(null, 
                "Choose \"Add Entity\" below!", Color.black, null));
        updateListModel();
    }
    
    private void setUpComboBox() {
        String[] typeList = {"All", "Add New Type"};
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(typeList);
        typeComboBox.setModel(comboBoxModel);
    }
    
}
