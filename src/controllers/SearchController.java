/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Entity;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import projects.Project;
import views.MainFrame;

enum SearchMode {
    Name,
    Type,
    Color,
    UnityPrefab
}

/**
 * Handles the search engine, making it so that the user can search for objects
 * by typing into the search bar, and making sure the user can choose to
 * search by name, type, prefab, etc.
 * @author Scott
 */
public class SearchController {
    
    private final MainFrame frame;
    private Project currentProject = null;
    private SearchMode searchMode = SearchMode.Name;
    
    
    public SearchController(MainFrame frame, ResultsListController resultsListController) {
        this.frame = frame;
        
        setUpSearchTypeComboBox();
        
        frame.getSearchTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                //get the search string from the user
                String text = frame.getSearchTextField().getText();
                //convert the text to lowercase
                String searchString = text.toLowerCase();
                //get the search results
                List<Entity> searchResults = search(searchString);
                //put the search results in the results JList
                resultsListController.setEntitiesInResults(searchResults);
            }
        });
    }
    
    private void setUpSearchTypeComboBox() {
        JComboBox searchModeComboBox = frame.getSearchModeComboBox();
        searchModeComboBox.addItem("Name");
        searchModeComboBox.addItem("Type");
        searchModeComboBox.addItem("Color");
        searchModeComboBox.addItem("Unity Prefab");
        
        searchModeComboBox.addActionListener((ActionEvent e) -> {
            int index = searchModeComboBox.getSelectedIndex();
            switch (index) {
                case 0:
                    searchMode = SearchMode.Name;
                    break;
                case 1:
                    searchMode = SearchMode.Type;
                    break;
                case 2:
                    searchMode = SearchMode.Color;
                    break;
                default:
                    searchMode = SearchMode.UnityPrefab;
                    break;
            }
        });
    }
    
    private List<Entity> search(String searchString) {
        switch (searchMode) {
            case Name:
                return searchByName(searchString);
            case Type:
                return searchByType(searchString);
            case Color:
                return searchByColor(searchString);
            default:
                return searchByUnityPrefab(searchString);
        }
    }
    
    private List<Entity> searchByName(String searchString) {
        List<Entity> results = new ArrayList();
        for (Entity entity : currentProject.getEntities()) {
            //convert the name to lowercase
            String nameLower = entity.getName().toLowerCase();
            //if the name of this entity partially matches the search string
            if (nameLower.contains(searchString)) {
                results.add(entity);
            }
        }
        return results;
    }
    
    private List<Entity> searchByType(String searchString) {
        return null;
    }
    
    private List<Entity> searchByColor(String searchString) {
        return null;
    }
    
    private List<Entity> searchByUnityPrefab(String searchString) {
        return null;
    }
    
    public void setCurrentProject(Project currentProject) {
        this.currentProject = currentProject;
    }
    
}
