/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import views.MainFrame;

/**
 * Handles the search engine, making it so that the user can search for objects
 * by typing into the search bar, and making sure the user can choose to
 * search by name, type, prefab, etc.
 * @author Scott
 */
public class SearchController {
    
    public SearchController(MainFrame frame) {
        frame.getSearchTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        });
    }
    
}
