/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Entity;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import views.EntityListRenderer;
import views.MainFrame;


/**
 * Responsible for controlling the Results JList in the GUI. Examples include general 
 * setup (ListManagers, ListRenderers), content management, etc. None of this
 * code should be in the main Controller class.
 * @author Scott
 */
public class ResultsListController {
    
    private final DefaultListModel listModel = new DefaultListModel();
    private final JList resultsList;
    
    public ResultsListController(MainFrame frame, List<Entity> entitiesInResults) {
        resultsList = frame.getResultsList();
        resultsList.setModel(listModel);
        resultsList.setCellRenderer(new EntityListRenderer(entitiesInResults));
    }
    
}
