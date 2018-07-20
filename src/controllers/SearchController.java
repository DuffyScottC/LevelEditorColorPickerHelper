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
    private final ResultsListController resultsListController;
    private Project currentProject = null;
    private SearchMode searchMode = SearchMode.Name;
    
    
    public SearchController(MainFrame frame, ResultsListController resultsListController) {
        this.frame = frame;
        this.resultsListController = resultsListController;
        
        setUpSearchTypeComboBox();
        
        frame.getSearchTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                search();
            }
        });
    }
    
    private void setUpSearchTypeComboBox() {
        JComboBox searchModeComboBox = frame.getSearchModeComboBox();
        searchModeComboBox.removeAllItems();
        searchModeComboBox.addItem("Name");
        searchModeComboBox.addItem("Color");
        searchModeComboBox.addItem("Unity Prefab");
        
        searchModeComboBox.addActionListener((ActionEvent e) -> {
            int index = searchModeComboBox.getSelectedIndex();
            switch (index) {
                case 0:
                    setSearchMode(SearchMode.Name);
                    break;
                case 1:
                    setSearchMode(SearchMode.Color);
                    break;
                default:
                    setSearchMode(SearchMode.UnityPrefab);
                    break;
            }
        });
    }
    
    public void setSearchMode(SearchMode searchMode) {
        this.searchMode = searchMode;
        resultsListController.setSearchMode(searchMode);
    }
    
    public void search() {
        //get the search string from the user
        String text = frame.getSearchTextField().getText();
        //convert the text to lowercase
        String searchString = text.toLowerCase();
        List<Entity> searchResults = null;
        //if the search string is empty
        if (searchString.length() == 0) {
            //get all the entities
            searchResults = currentProject.getEntities();
        } else {
            //if the search string is NOT empty, check the searchMode
            switch (searchMode) {
                case Name:
                    searchResults = searchByName(searchString);
                    break;
                case Color:
                    searchResults = searchByColor(searchString);
                    break;
                default:
                    searchResults = searchByUnityPrefab(searchString);
            }
        }
        //put the search results in the results JList
        resultsListController.setEntitiesInResults(searchResults);
    }
    
    private List<Entity> searchByName(String searchString) {
        List<Entity> results = new ArrayList();
        for (Entity entity : currentProject.getEntities()) {
            //convert the name to lowercase
            String nameLower = entity.getName().toLowerCase();
            //if the search string partially matches the name of this entity
            if (nameLower.contains(searchString)) {
                results.add(entity);
            }
        }
        return results;
    }
    
    private List<Entity> searchByColor(String searchString) {
        List<Entity> results = new ArrayList();
        String hex = parseUserHexString(searchString);
        //if the user's hex could not be parsed
        if (hex == null) {
            //we have no matches
            return results;
        }
        //if the user hex could be parsed, get the r, g, b color values from
        //the hex string
        int r = Integer.valueOf( hex.substring( 0, 2 ), 16 );
        int g = Integer.valueOf( hex.substring( 2, 4 ), 16 );
        int b = Integer.valueOf( hex.substring( 4, 6 ), 16 );
        
        //loop through all the entities
        for (Entity entity : currentProject.getEntities()) {
            //if the color values match
            if (entity.getR() == r && entity.getG() == g && entity.getB() == b) {
                //add the entity to the results list
                results.add(entity);
            }
        }
        return results;
    }
    
    /**
     * Takes the input string and converts it into a valid color code hex
     * value (or returns null if the hex value could not be parsed)
     * @param hexString The String to be parsed
     * @return A valid hex string of 6 hexadecimal characters, or null if the
     * hex string could not be parsed
     */
    private String parseUserHexString(String hexString) {
        String hex = hexString;
        //if this has a #
        if (hex.charAt(0) == '#') {
            //remove the #
            hex = hex.substring(1, hex.length());
        }
        
        //if the hex is not of length 6
        if (hex.length() != 6) {
            //correct the size by adding or removing bits
            if (hex.length() < 6) {
                //if the hex is less than 6 characters long
                //find out how many characters are missing
                int numOfZeros = 6 - hex.length();
                //add on the appropriate number of zeros
                hex += "000000".substring(0,numOfZeros);
            } else if (hex.length() > 6) {
                //if the hex is greater than 6 characters long
                //take the first size characters
                hex = hex.substring(0, 6);
            }
        }
        //At this point, the hex is of the right size
        //if the string is NOT made up of hex characters
        if (!hex.matches("[0-9A-Fa-f]{6}")) {
            return null;
        }
        //if the string is made up of hex characters
        return hex;
    }
    
    private List<Entity> searchByUnityPrefab(String searchString) {
        List<Entity> results = new ArrayList();
        for (Entity entity : currentProject.getEntities()) {
            //convert the unity prefab to lowercase
            String unityPrefabLower = entity.getClassIndex().toLowerCase();
            //if the search string partially matches the prefab of this entity
            if (unityPrefabLower.contains(searchString)) {
                results.add(entity);
            }
        }
        return results;
    }
    
    public void setCurrentProject(Project currentProject) {
        this.currentProject = currentProject;
    }

    public SearchMode getSearchMode() {
        return searchMode;
    }
    
}
